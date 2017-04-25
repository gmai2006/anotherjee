package com.tomcat.hosting.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.NumberRenderer;
import org.stringtemplate.v4.STGroup;

import com.google.inject.Inject;
import com.tomcat.hosting.dao.User;
import com.tomcat.hosting.service.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1666L;

	@Inject
	UserService service;
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process (request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process (request, response);
	}
	
	private void process(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		STGroup group = Utils.getSTGroup(getServletContext());
		group.registerRenderer(Number.class, new NumberRenderer());
		String pageName = "index.xhtml";
		int result = authenticate(req);
		if (2 == result) pageName = "admin/clientview";
		else if (1 == result) pageName = "vendor/clientview";
		else if (0 == result) pageName = "client/vendorview";
		try {
			RequestDispatcher rd= req.getRequestDispatcher("/" + pageName);
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int authenticate(HttpServletRequest req) {
		try {
			String userId = req.getParameter("userId");
			String password = req.getParameter("password");
			User temp = new User();
			temp.setUserId(userId);
			temp.setPassword(password);
			System.out.println("coming user Id:" + temp.getUserId() + ":" + temp.getPassword());
			User user = service.getUserbyId(userId);
			if (null == user) { System.out.println("user NOT FOUND:" + temp.getUserId()) ; return -1; }
			
			if (!temp.equals(user)) 
			{ 
				System.out.println("user NOT MATCHED:" + temp.toString() + "!=" + user.toString()) ; 
				return -1; 
			}
			req.getSession().setAttribute("user", user);
			return user.getUserRole();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
}
