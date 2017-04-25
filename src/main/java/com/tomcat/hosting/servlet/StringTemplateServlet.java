package com.tomcat.hosting.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.stringtemplate.v4.NumberRenderer;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.google.inject.Inject;
import com.tomcat.hosting.dao.ZipcodeGeo;
import com.tomcat.hosting.service.UserService;

public class StringTemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected Log logger = LogFactory.getLog(StringTemplateServlet.class);
	@Inject
	UserService service;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		STGroup group = Utils.getSTGroup(getServletContext());
		group.registerRenderer(Number.class, new NumberRenderer());
		// removes the leading "/" and the trailing ".st"
		String pageName = getPageName(request);
		
		ST page = group.getInstanceOf(pageName);
		ST container = group.getInstanceOf("layout");
		container.add("context", getContextPath(request));
		container.add(pageName, "active");
		container.add("body", page);
		
		String search = request.getParameter("search");
				
		JSONObject geo = null;
		try {
			if (null == search) {
				geo = getGeoLocation();
				if (null != geo) {
					String loc = (String)geo.get("loc");
					String[] geoPos = loc.split(",");
					container.add("lat", geoPos[0]);
					container.add("long", geoPos[1]);
					container.add("name", (String)geo.get("city") + " " + (String)geo.get("region"));
				}
			} else {
				try {
					int zipcode = Integer.valueOf(search);
					//check local database first
					ZipcodeGeo zipcodeGeo = service.getGeoByZipcode(zipcode);
					if (null != zipcodeGeo) {
						container.add("lat", zipcodeGeo.getLat());
						container.add("long", zipcodeGeo.getLng());
						container.add("name", zipcodeGeo.getAddress());
					} else {
						logger.info("GEO NOT FOUND pull from google");
						JSONObject json = Utils.getGeoLocationFromZipcode(search);
						JSONArray array = (JSONArray)json.get("results");
						JSONObject entity = (JSONObject)array.get(0);
//						System.out.println(entity.get("formatted_address"));
						JSONObject geometry = (JSONObject)entity.get("geometry");
						JSONObject location = (JSONObject)geometry.get("location");
						
						container.add("lat", location.get("lat"));
						container.add("long", location.get("lng"));
						container.add("name", entity.get("formatted_address"));
						service.addZipCodeGeo(zipcode, 
								(Double)location.get("lat"), 
								(Double)location.get("lng"), 
								(String)entity.get("formatted_address"));
					}
				} catch (NumberFormatException nfe) {
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// if ("index".equals(pageName)) container.add("index", true);
		out.print(container.render());
		out.flush();
	}

	private String getContextPath(HttpServletRequest req) {
		String contextpath = req.getContextPath();
		contextpath = (contextpath.endsWith("/")) ? contextpath : contextpath
				.concat("/");
		return contextpath;
	}

	protected String getPageName(HttpServletRequest req) {
		String pageName = req.getRequestURI().trim();
		System.out.println(this.getClass().getName() + " calling page "
				+ pageName);
		if ("/".equals(pageName) || "".equals(pageName))
			return "index";
		pageName = pageName.substring(pageName.lastIndexOf("/") + 1);
		if ("/".equals(pageName) || "".equals(pageName))
			return "index";
		if (pageName.lastIndexOf(".") != -1) {
			pageName = pageName.substring(0, pageName.lastIndexOf("."));
		}
		return pageName;
	}

	private JSONObject getGeoLocation() throws Exception {

		String url = "http://ipinfo.io/geo";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		Object output = JSONValue.parse(response.toString());

		return (JSONObject)output;
	}
	
	private JSONObject getGeoLocation(String ip) throws Exception {

		String url = "http://ipinfo.io/" + ip + "/geo";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		Object output = JSONValue.parse(response.toString());

		return (JSONObject)output;
	}
	
}
