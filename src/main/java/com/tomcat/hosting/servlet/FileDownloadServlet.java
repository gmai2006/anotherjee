package com.tomcat.hosting.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.tomcat.hosting.dao.User;

public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1666L;
	private static final int SIZE = 10*1024;
	private String path;
	@Inject
	public FileDownloadServlet(@Named("vendors.dir") String path) {
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
		String fileName = req.getParameter("fileName");
		
		try {
			File storage = null;
			User user = (User)req.getSession().getAttribute("user");
			if (2 == user.getUserRole()) { //admin
				String userId = req.getParameter("userId");
				storage = new File(path, userId);
			} else {
				storage = new File(path, user.getUserId());		
			}
			System.out.println("directory: " + storage);
			File download = new File(storage, fileName);
	        String mimeType = Utils.getMimeType(download.getName());
			resp.setContentType(mimeType);
			if (mimeType.equals("text/plain"))
				resp.setHeader("Content-Disposition", "inline;filename=\"temp.txt\"");
			else
				resp.setHeader ("Content-Disposition", "inline;filename=\""+download.getName()+"\"");	
			ByteArrayOutputStream b = new ByteArrayOutputStream();
	        StreamCopier.copy(new BufferedInputStream( new FileInputStream(download)), b);
	        b.writeTo(resp.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
