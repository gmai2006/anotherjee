package com.tomcat.hosting.servlet;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.tomcat.hosting.dao.CompanyV;
import com.tomcat.hosting.dao.User;

public class ClientVendorDetailViewServlet extends ClientServlet {
	private static final long serialVersionUID = 15366L;
	private String path;
	@Inject
	public ClientVendorDetailViewServlet(@Named("vendors.dir") String path) {
		this.path = path;
	}
	@Override
	protected ST getPage(HttpServletRequest req, HttpServletResponse resp, STGroup group)
			throws IOException {
		ST page = group.getInstanceOf("clientvendordetailview");
		User user = (User)req.getSession().getAttribute("user");
		CompanyV company = service.getCompanyById(user.getCompanyId());
		String id = req.getParameter("id");
		CompanyV vendor = service.getCompanyById(Integer.valueOf(id));
		
		File dir = new File(path, id);
		if (!dir.exists()) dir.mkdir();
		File clientDir = new File(dir, String.valueOf(user.getCompanyId()));
		
		if (clientDir.exists()) {
			File[] files = clientDir.listFiles();
//					new FileFilter() {
//						@Override
//						public boolean accept(File pathname) {
//							return (SimpleFileWrapper.IMAGE.indexOf(pathname.getName()) != -1);
//						}
//					});
			if (null != files) {
				SimpleFileWrapper[] simpleFiles = new SimpleFileWrapper[files.length];
				for (int i = 0;  i < files.length; i++) {
					String name = files[i].getAbsolutePath().substring("/home/tomcat/apps/snow/src/main/webapps/".length()-1);
					logger.info("file name:" + name);
					simpleFiles[i] = new SimpleFileWrapper(name);
					simpleFiles[i].setDate(SimpleFileWrapper.FORMATER.format(files[i].lastModified()));
					simpleFiles[i].setSize(SimpleFileWrapper.convertFileSize(files[i].length()));
				}
				page.add("files", simpleFiles);
			}
		}
		
		page.add("company", company);
		page.add("vendor", vendor);
		page.add("closingCurlyBraclet", "}");
		page.add("openCurlyBraclet", "{");
		return page;
	}

	
	@Override
	protected String getPageStyle() {
		return "";
	}
	
}
