package com.tomcat.hosting.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.tomcat.hosting.dao.CompanyV;
import com.tomcat.hosting.dao.User;

public class VendorClientDetailViewServlet extends VendorServlet {
	private static final long serialVersionUID = 16366L;
	private String path;
	@Inject
	public VendorClientDetailViewServlet(@Named("vendors.dir") String path) {
		this.path = path;
	}
	
	@Override
	protected ST getPage(HttpServletRequest req, HttpServletResponse resp, STGroup group)
			throws IOException {
		ST page = group.getInstanceOf("vendorclientdetailview");
		User user = (User)req.getSession().getAttribute("user");
		CompanyV company = service.getCompanyById(user.getCompanyId());
		String id = req.getParameter("id");
		CompanyV client = service.getCompanyById(Integer.valueOf(id));
		
		File dir = new File(path, String.valueOf(user.getCompanyId()));
		if (!dir.exists()) dir.mkdir();
		File clientDir = new File(dir, id);
		if (!clientDir.exists()) clientDir.mkdir();
		String cmd = req.getParameter("cmd");
		if ("delete".equals(cmd)) {
			String filename = req.getParameter("fileName");
			File deletefile = new File(clientDir, filename);
			deletefile.delete();
			logger.info("file deleted:" + deletefile.getAbsolutePath());
		}
		File[] files = clientDir.listFiles();
		
		if (null != files) {
			SimpleFileWrapper[] simpleFiles = new SimpleFileWrapper[files.length];
			for (int i = 0;  i < files.length; i++) {
				simpleFiles[i] = new SimpleFileWrapper(files[i].getName());
				simpleFiles[i].setDate(SimpleFileWrapper.FORMATER.format(files[i].lastModified()));
				simpleFiles[i].setSize(SimpleFileWrapper.convertFileSize(files[i].length()));
			}
			page.add("files", simpleFiles);
		}
		page.add("company", company);
		page.add("client", client);
		page.add("closingCurlyBraclet", "}");
		page.add("openCurlyBraclet", "{");
		
		return page;
	}

	
	@Override
	protected String getPageStyle() {
		return "";
	}
	
}
