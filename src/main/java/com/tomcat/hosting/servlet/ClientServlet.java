package com.tomcat.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.NumberRenderer;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.tomcat.hosting.dao.CompanyV;
import com.tomcat.hosting.dao.User;

public abstract class ClientServlet extends AdminServlet {
	private static final long serialVersionUID = 1666L;
	
	@Override
	protected void process(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		STGroup group = Utils.getSTGroup(getServletContext());
		group.registerRenderer(Number.class, new NumberRenderer());
		ST page = group.getInstanceOf("client");
		ST container = group.getInstanceOf("layout");
		
		PrintWriter out = resp.getWriter();		
		
		User user = (User)req.getSession().getAttribute("user");
		List<CompanyV> companies = service.getAssignedVendors(user.getCompanyId());
		page.add("companies", companies);
		
		String context = Utils.getContextPath(req);
		page.add("message", message);
		page.add("cssId", cssId);
		page.add("clients", companies.size());
		page.add("page", getPage(req, resp, group));
		container.add("body", page);
		container.add(getPageStyle(), "active");
		container.add("context", context);
		out.print(container.render());
		out.flush();
	}

}
