package com.tomcat.hosting.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.tomcat.hosting.dao.AssignedCompanyV;
import com.tomcat.hosting.dao.Company;
import com.tomcat.hosting.dao.CompanyV;

public class AdminVendorViewServlet extends AdminServlet {
	private static final long serialVersionUID = 16366L;

	@Override
	protected ST getPage(HttpServletRequest req, HttpServletResponse resp, STGroup group)
			throws IOException {
		ST page = group.getInstanceOf("adminvendorview");
		List<CompanyV> companies = service.getCompanyByType(Company.VENDOR);
		
		page.add("companies", companies);
		return page;
	}
	
	@Override
	protected String getPageStyle() {
		return "vendor";
	}
	
}
