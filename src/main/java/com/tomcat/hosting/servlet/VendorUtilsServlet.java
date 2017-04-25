package com.tomcat.hosting.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.tomcat.hosting.dao.CompanyV;
import com.tomcat.hosting.dao.User;

public class VendorUtilsServlet extends VendorServlet {
	private static final long serialVersionUID = 16366L;

	@Override
	protected ST getPage(HttpServletRequest req, HttpServletResponse resp, STGroup group)
			throws IOException {
		ST page = group.getInstanceOf("vendorclientview");
		User user = (User)req.getSession().getAttribute("user");
		List<CompanyV> companies = service.getAssignedClients(user.getCompanyId());
		page.add("companies", companies);
		return page;
	}

	@Override
	protected String getPageStyle() {
		return "client";
	}
	
}
