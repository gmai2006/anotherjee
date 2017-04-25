package com.tomcat.hosting.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tomcat.hosting.dao.User;

public class SecurityFilter implements Filter {
	protected static final Log logger = LogFactory.getLog(SecurityFilter.class);
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		if (false == securityCheck(req, response)) {
			response.sendRedirect(getContextPath(req) + "index.xhtml");
		}
		else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}
	
	private boolean securityCheck(HttpServletRequest req, HttpServletResponse resp) {
		String path = req.getServletPath();
		if (path.indexOf("admin/") != -1 || path.indexOf("vendor/") != -1
				|| path.indexOf("client/") != -1) {
		User user = (User)req.getSession().getAttribute("user");
			if (null == user) {
				logger.error("User not found ");
				return false;
			}
			if (path.indexOf("admin/") != -1 && user.getUserRole() < 2) {
				logger.error("Unathorized accessing admin area " + user.getUserId());
				return false;
			}
			else if (path.indexOf("vendor/") != -1 && user.getUserRole() < 1) {
				logger.error("Unathorized accessing vendor area " + user.getUserId());
				return false;
			}
		}
		return true;
	}
	private static String getContextPath(HttpServletRequest req) {
		String contextpath = req.getContextPath();
		contextpath = (contextpath.endsWith("/")) ? contextpath : contextpath.concat("/");
		return contextpath;
	}

}
