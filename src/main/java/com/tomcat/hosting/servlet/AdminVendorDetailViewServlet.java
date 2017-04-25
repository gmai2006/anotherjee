package com.tomcat.hosting.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.tomcat.hosting.dao.AssignedCompanyV;
import com.tomcat.hosting.dao.CompanyV;
import com.tomcat.hosting.dao.ZipcodeGeo;

public class AdminVendorDetailViewServlet extends AdminServlet {
	private static final long serialVersionUID = 16366L;

	@Override
	protected ST getPage(HttpServletRequest req, HttpServletResponse resp, STGroup group)
			throws IOException {
		ST page = group.getInstanceOf("adminvendordetailview");
		String id = req.getParameter("id");
		CompanyV company = service.getCompanyById(Integer.valueOf(id));
		List<CompanyV> clients = 
				service.getAssignedClients(Integer.valueOf(id));
		ZipcodeGeo geo = service.getGeoByZipcode(company.getZipcode());
		page.add("lat", (double)geo.getLat());
		page.add("lng", (double)geo.getLng());
		page.add("company", company);
		page.add("closingCurlyBraclet", "}");
		page.add("openCurlyBraclet", "{");
		int[] radius = new int[10];
		for (int i = 0; i < radius.length; i++) radius[i] = (i+1)*5;
		page.add("radius", radius);
		page.add("clients", clients);
		return page;
	}
	
	@Override
	protected String getPageStyle() {
		return "vendor";
	}
	
}
