package com.tomcat.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tomcat.hosting.utils.EmailClient;

public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1766L;
	protected Log logger = LogFactory.getLog(BaseServlet.class);
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse resp) throws ServletException,  IOException {
		process (request, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse resp) throws ServletException,  IOException {
		process (request, resp);
	}
	
	protected abstract void process(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException;
	
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
}
