package com.tomcat.hosting.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.tomcat.hosting.dao.User;

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1666L;
	private static final int SIZE = 10*1024;
	private String path;
	@Inject
	public FileUploadServlet(@Named("vendors.dir") String path) {
		this.path = path;
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,  IOException {
		process (request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,  IOException {
		process (request, response);
	}
	
	private void process(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		
		File uploadFile = null;
		String id = null;
		try {
			User user = (User)req.getSession().getAttribute("user");
			File storage = new File(path, String.valueOf(user.getCompanyId()));
			if (!storage.exists()) {
				System.out.println("storage NOT found");
				storage.mkdir();
				System.out.println("createtemp storage:" + storage.getName());
			}
			
			System.out.println("destinate directory: " + storage);
		    
//			 Create a factory for disk-based file items
	    	DiskFileItemFactory factory = new DiskFileItemFactory();

//	    	 Set factory constraints
	    	factory.setSizeThreshold(SIZE);
	    	factory.setRepository(storage);
	    	
//	    	 Create a new file upload handler
	    	ServletFileUpload upload = new ServletFileUpload(factory);

//	    	 Parse the request
	    	List<FileItem> /* FileItem */ items = upload.parseRequest(req);
	    	System.out.println("uploaded " + items.size());
	    	
	    	for (FileItem item : items)
	    	{
	    		if (item.isFormField() && "id".equals(item.getFieldName()))
	    		{
	    			id = item.getString();
	    			break;
	    		}
	    	}
	    	File uploadfile = new File(storage, id);
	    	
			FileItem textFilefield = findFileField(items, "userfile");
			if (null != textFilefield && (StringUtils.isNotEmpty(textFilefield.getName())))
			{		
				processUploadedFile(uploadfile, textFilefield);	
			}
			
			uploadFile = new File(uploadfile, textFilefield.getName());
			if (!uploadFile.exists())
			{
				System.out.println("Unable to upload:" + uploadFile.getName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String returnPage = "clientdetailview?id=" + id;
		System.out.println("returned page " + returnPage);
//		RequestDispatcher rd= req.getRequestDispatcher(returnPage);
//		rd.forward(req, resp);
		resp.sendRedirect(req.getHeader("referer"));
	}
	
	FileItem findField(List<FileItem> items, String keyword)
	{
		for (FileItem item : items)
		{
			if (item.isFormField())
			{
//				System.out.println(item.getFieldName() + ": " + item.getString());
				if (item.getFieldName().equals(keyword)) return item;
			}
			else continue;
		}
		return null;
	}
	
	FileItem findFileField(List<FileItem> items, String keyword)
	{
		for (FileItem item : items)
		{
			if (!item.isFormField())
			{
//				System.out.println(item.getFieldName() + ": " + item.getString());
				if (item.getFieldName().equals(keyword) 
						&& (StringUtils.isNotEmpty(item.getName()))) return item;
			}
			else continue;
		}
		return null;
	}
	
	private int processUploadedFile(File dir, FileItem item)
	  {
		  BufferedInputStream input = null;
		  BufferedOutputStream output = null;

		  try
		  {
		    String fileName = item.getName();
		    System.out.println("uploaded file name " + fileName + " type " + fileName.lastIndexOf("."));
		    System.out.println("upload file " + fileName + " to " + dir.getAbsolutePath());
		    File file = new File(dir, fileName);
			input = new BufferedInputStream(item.getInputStream());
			output = new BufferedOutputStream(new FileOutputStream(file));
			StreamCopier.copy(input, output);	
			
		  }
		  catch(Exception e)
		  {
			  System.out.println("ERROR: process upload file due to\n" + e.getMessage());
			  return -1;
		  }
		  finally {
			  try {
				
				  if (null != input)
				  {
					  input.close();
				  }
				  if (null != output)
				  {
					  output.flush();
					  output.close();
				  }
			  } catch (Exception ignored) {}
		  }
		  return 0;
	  }

}
