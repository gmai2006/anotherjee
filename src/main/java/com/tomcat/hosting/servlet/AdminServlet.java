package com.tomcat.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.stringtemplate.v4.NumberRenderer;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.google.inject.Inject;
import com.tomcat.hosting.service.UserService;
import com.tomcat.hosting.utils.EmailClient;

public abstract class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1666L;
	protected Log logger = LogFactory.getLog(AdminServlet.class);
	protected String cssId = null;
	protected String message = null;
	
	@Inject
	protected UserService service;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		process (request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		process (request, response);
	}
	@Override
	protected void process(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		STGroup group = Utils.getSTGroup(getServletContext());
		group.registerRenderer(Number.class, new NumberRenderer());
		ST page = group.getInstanceOf("admin");
		ST container = group.getInstanceOf("layout");
		
		PrintWriter out = resp.getWriter();		
		
		int clients = service.getNumberofClients();
		int vendors = service.getNumberofVendors();
		
		String context = Utils.getContextPath(req);
		page.add("message", message);
		page.add("cssId", cssId);
		page.add("clients", clients);
		page.add("vendors", vendors);
		page.add("page", getPage(req, resp, group));
		container.add("body", page);
		container.add(getPageStyle(), "active");
		container.add("context", context);
		out.print(container.render());
		out.flush();
	}
	
	protected void catchException (Exception e, String className) {
		StringWriter output = new StringWriter();
		PrintWriter writer = new PrintWriter(output);
		if (null != e) {
			e.printStackTrace(writer);
		} else {
			writer.write("Null exception");
		}
		writer.flush();
		writer.close();
		logger.error("ERROR: " + output.toString());
		notifyAdmin(className + ": ERROR ", output.toString());
	}
	
	protected void notifyAdmin(String subject, String message)
	{
		EmailClient emailer = new EmailClient("support@tomcathostingservice.com", 
				new String[] {"support@tomcathostingservice.com"}, subject, message);
		new Thread(emailer).run();
	}
	
	protected abstract ST getPage(HttpServletRequest req,
			HttpServletResponse resp, STGroup group) throws IOException;
	
	protected abstract String getPageStyle();
	
	protected String getPageName(HttpServletRequest req) {
		String pageName = req.getRequestURI().trim();
		System.out.println(this.getClass().getName() + " calling page "
				+ pageName);
		if ("/".equals(pageName) || "".equals(pageName)) return "index";
		pageName = pageName.substring(pageName.lastIndexOf("/") + 1);
		if ("/".equals(pageName) || "".equals(pageName)) return "index";
		if (pageName.lastIndexOf(".") != -1) {
			pageName = pageName.substring(0, pageName.lastIndexOf("."));
		}
		return pageName;
	}
}
