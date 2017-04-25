package com.tomcat.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.stringtemplate.v4.NumberRenderer;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STRawGroupDir;

public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1666L;
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
	
	private void process(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		STGroup group = Utils.getSTGroup(getServletContext());
		group.registerRenderer(Number.class, new NumberRenderer());
		ST page = group.getInstanceOf("contact");
		ST container = group.getInstanceOf("layout");
		container.add("contact", "active");
		PrintWriter out = resp.getWriter();
		String email = req.getParameter("email");
		String message = req.getParameter("message");
		String output = "";
		String cssId = "";
		if (StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(message)) {
			EmailClient emailer = new EmailClient("support@tomcathostingservice.com", 
					new String[] {"support@tomcathostingservice.com"}, "duhoc", "["+ email + "]:\n" + message);
			new Thread(emailer).run();
			output = "Thank you for your inquiry.  We will respond to your inquiry as soon as we can";
			cssId = "alert-success";
		}
		else {
			output = "Missing your email.  Please provide your email address so we can respond to your inquiry";
			cssId = "alert-warning";
		}
		page.add("message", output);
		page.add("cssId", cssId);
		container.add("body", page);
		container.add("contact", "current");
		out.print(container.render());
		out.flush();
	}
}
