package com.tomcat.hosting.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.tomcat.hosting.service.UserService;

public class LogoutServlet extends HttpServlet {
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
		req.getSession().invalidate();
		try {
			RequestDispatcher rd= req.getRequestDispatcher("/index.xhtml");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
