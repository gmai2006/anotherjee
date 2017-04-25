package com.tomcat.hosting.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

public class MainModule extends ServletModule {
	@Override
	protected void configureServlets() {
		Properties properties = new Properties();
		try {
			install(new JpaPersistModule("snowJPA"));
			filter("/*").through(PersistFilter.class);
			InputStream in = MainModule.class.getResourceAsStream("/application.properties");
			properties.load(in);
			Names.bindProperties(binder(), properties);
		} catch (FileNotFoundException e) {
			System.out
					.println("The configuration file application.properties can not be found");
		} catch (IOException e) {
			System.out.println("I/O Exception during loading configuration");
		}
		bind(StringTemplateServlet.class).in(Singleton.class);
		bind(MailServlet.class).in(Singleton.class);
		bind(LoginServlet.class).in(Singleton.class);
		bind(AdminClientViewServlet.class).in(Singleton.class);
		bind(AdminClientDetailViewServlet.class).in(Singleton.class);
		bind(CsvReportServlet.class).in(Singleton.class);
		bind(AdminVendorViewServlet.class).in(Singleton.class);
		bind(AdminVendorDetailViewServlet.class).in(Singleton.class);
		bind(AdminReportViewServlet.class).in(Singleton.class);
		bind(AdminSmsServlet.class).in(Singleton.class);
		bind(AdminGenericServlet.class).in(Singleton.class);
		bind(ClientViewServlet.class).in(Singleton.class);
		bind(ClientVendorDetailViewServlet.class).in(Singleton.class);
		bind(ClientCsvReportServlet.class).in(Singleton.class);
		bind(ClientGenericServlet.class).in(Singleton.class);
		bind(FileUploadServlet.class).in(Singleton.class);
		bind(FileDownloadServlet.class).in(Singleton.class);
		bind(VendorClientViewServlet.class).in(Singleton.class);
		bind(VendorClientDetailViewServlet.class).in(Singleton.class);
		bind(VendorGenericServlet.class).in(Singleton.class);
		bind(VendorCsvReportServlet.class).in(Singleton.class);
		bind(LogoutServlet.class).in(Singleton.class);
		
		serve("/").with(StringTemplateServlet.class);
		serve("/mail").with(MailServlet.class);
		serve("/admin/clientview").with(AdminClientViewServlet.class);
		serve("/admin/clientdetailview").with(AdminClientDetailViewServlet.class);
		serve("/admin/vendorview").with(AdminVendorViewServlet.class);
		serve("/admin/vendordetailview").with(AdminVendorDetailViewServlet.class);
		serve("/admin/reports").with(AdminReportViewServlet.class);
		serve("/admin/csvreport").with(CsvReportServlet.class);
		serve("/admin/sms").with(AdminSmsServlet.class);
		serve("/admin/*").with(AdminGenericServlet.class);
		serve("/vendor/clientview").with(VendorClientViewServlet.class);
		serve("/vendor/clientdetailview").with(VendorClientDetailViewServlet.class);
		serve("/vendor/upload").with(FileUploadServlet.class);
		serve("/vendor/csvreport").with(VendorCsvReportServlet.class);
		serve("/vendor/*").with(VendorGenericServlet.class);
		serve("/client/vendorview").with(ClientViewServlet.class);
		serve("/client/vendordetailview").with(ClientVendorDetailViewServlet.class);
		serve("/client/csvreport").with(ClientCsvReportServlet.class);
		serve("/client/*").with(ClientGenericServlet.class);
		serve("/login").with(LoginServlet.class);
		serve("/download").with(FileDownloadServlet.class);
		serve("/logout").with(LogoutServlet.class);	
		serve("*.xhtml").with(StringTemplateServlet.class);	
	}
}
