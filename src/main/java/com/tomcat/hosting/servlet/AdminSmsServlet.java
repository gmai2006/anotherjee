package com.tomcat.hosting.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.tomcat.hosting.dao.Company;
import com.tomcat.hosting.dao.CompanyV;

public class AdminSmsServlet extends AdminServlet {
	private static final long serialVersionUID = 16356L;
	protected Log logger = LogFactory.getLog(AdminSmsServlet.class);

	@Override
	protected ST getPage(HttpServletRequest req, HttpServletResponse resp, STGroup group)
			throws IOException {
		String type = req.getParameter("type");
		String subject = req.getParameter("subject");
		String message = req.getParameter("message");
		String output = "";
		String cssId = "";
		List<CompanyV> companies = null;
		if (StringUtils.isNotEmpty(type)) {
			if (StringUtils.isNotEmpty(subject) 
					&& StringUtils.isNotEmpty(message)) {
				companies = service.getCompanyByType(Integer.valueOf(type));
				
//				EmailClient emailer = new EmailClient("support@tomcathostingservice.com", 
//						createDL(companies), subject, message);
//				new Thread(emailer).run();
				String recipient = "clients";
				if (Company.VENDOR == Integer.valueOf(type)) recipient = "vendors";
				output = "An email has been sent  to " + recipient;
				cssId = "alert-success";
			}
			else {
				output = "Missing required information.  Please try again";
				cssId = "alert-warning";
			}
		}
		
		String pageName =  "sms";//getPageName(req);
		logger.info("page name:" + pageName);
		ST page = group.getInstanceOf(pageName);
		page.add("message", output);
		page.add("cssId", cssId);
		return page;
	}

	@Override
	protected String getPageStyle() {
		return "sms";
	}
	
	private String[] createDL(List<CompanyV> companies) {
		String[] emails = new String[companies.size()]; 
		int i = 0;
		for (CompanyV c : companies) {
			emails[i++] = c.getEmailAddress();
		}
		return emails;
		
	}
	
}
