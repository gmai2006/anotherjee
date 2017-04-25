package com.tomcat.hosting.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class ClientGenericServlet extends ClientServlet {
	private static final long serialVersionUID = 16356L;
	protected Log logger = LogFactory.getLog(ClientGenericServlet.class);

	@Override
	protected ST getPage(HttpServletRequest req, HttpServletResponse resp, STGroup group)
			throws IOException {
		String pageName =  getPageName(req);
		logger.info("page name:" + pageName);
		ST page = group.getInstanceOf(pageName);
		return page;
	}

	@Override
	protected String getPageStyle() {
		return "pageName";
	}
	
}
