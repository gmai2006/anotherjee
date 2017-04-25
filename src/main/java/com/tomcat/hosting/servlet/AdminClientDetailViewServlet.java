package com.tomcat.hosting.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.tomcat.hosting.dao.CompanyV;
import com.tomcat.hosting.dao.VendorClient;
import com.tomcat.hosting.dao.VendorClientPK;

public class AdminClientDetailViewServlet extends AdminServlet {
	private static final long serialVersionUID = 16366L;

	@Override
	protected ST getPage(HttpServletRequest req, HttpServletResponse resp, STGroup group)
			throws IOException {
		ST page = group.getInstanceOf("adminclientdetailview");
		String clientId = req.getParameter("id");
		String vendorId = req.getParameter("vendor");
		if (StringUtils.isNotEmpty(clientId)
				&& StringUtils.isNotEmpty(vendorId))
		{
			boolean assigned = assignVendor(req);
			String output = "";
			String cssId = "";
			if (assigned) {
				output = "Vendor has been assigned!";
				cssId = "alert-success";
			} else {
				output = "Missing required information.  Please try again";
				cssId = "alert-warning";
			}
			
			page.add("message", output);
			page.add("cssId", cssId);
		}
		String id = req.getParameter("id");
		String dist = req.getParameter("distance");
		if (null == dist) dist = "45";
		CompanyV company = service.getCompanyById(Integer.valueOf(id));
		List<CompanyV> assignedVendors = 
				service.getAssignedVendors(Integer.valueOf(id));
		List<CompanyV> vendors = service.getNearestVendors(Integer.valueOf(id), Integer.valueOf(dist));
		
		page.add("assignedVendors", assignedVendors);
		page.add("vendors", vendors);
		page.add("company", company);
		page.add("lat", (double)company.getLat());
		page.add("lng", (double)company.getLng());
		page.add("closingCurlyBraclet", "}");
		page.add("openCurlyBraclet", "{");
		return page;
	}
	
	private boolean assignVendor(HttpServletRequest req) {
		String clientId = req.getParameter("id");
		String vendorId = req.getParameter("vendor");
		VendorClientPK pk = new VendorClientPK();
		pk.setClientId(Integer.valueOf(clientId));
		pk.setVendorId(Integer.valueOf(vendorId));
		
		VendorClient assoc = new VendorClient();
		assoc.setAssignmentDate(new java.sql.Date(System.currentTimeMillis()));
		assoc.setJobType("Snow - ice");
		assoc.setId(pk);
		try {
		service.saveOrUpdate(assoc);
		} catch (Exception e) {
			catchException(e, AdminClientDetailViewServlet.class.getName());
			return false;
		}
		return true;
	}
	
	@Override
	protected String getPageStyle() {
		return "";
	}
	
}
