package com.tomcat.hosting.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsefa.Serializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;

import com.google.inject.Inject;
import com.tomcat.hosting.dao.CompanyV;
import com.tomcat.hosting.service.UserService;

public class CsvReportServlet extends BaseServlet {
	private static final long serialVersionUID = 1666L;
	private static final int SIZE = 10*1024;
	@Inject
	protected UserService service;
	@Override
	protected void process(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
			List<CompanyV> companies = new ArrayList<CompanyV>();
		try {
				String type = req.getParameter("type");
				int customerType = Integer.valueOf(type).intValue();
				companies = service.getCompanyByType(customerType);
				
				resp.setHeader("Expires", "0");
				resp.setHeader("Cache-Control",
						"must-revalidate, post-check=0, pre-check=0");
				resp.setHeader("Pragma", "public");
				
				resp.setContentType("application/vnd.ms-excel");
				
				CsvConfiguration config = new CsvConfiguration();
				config.setFieldDelimiter(',');
				Serializer serializer = CsvIOFactory.createFactory(config, CompanyV.class).createSerializer();
				
				serializer.open(resp.getWriter());
				// call serializer.write for every object to serialize
				for (CompanyV c: companies) {
					serializer.write(c);
				}
				resp.getWriter().flush();
				serializer.close(true);
		} catch (Exception ex) {
			catchException(ex, this.getClass().getName());
		}
	}
}
