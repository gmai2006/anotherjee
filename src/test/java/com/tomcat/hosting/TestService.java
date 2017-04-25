package com.tomcat.hosting;

import java.io.StringWriter;
import java.util.List;

import net.sf.jsefa.Serializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tomcat.hosting.dao.Company;
import com.tomcat.hosting.dao.CompanyV;
import com.tomcat.hosting.dao.User;
import com.tomcat.hosting.dao.ZipcodeGeo;
import com.tomcat.hosting.service.JPAPersistenceInitializer;
import com.tomcat.hosting.service.UserService;

public class TestService {
	Injector injector = Guice.createInjector(new StandaloneModule());
	JPAPersistenceInitializer jpa = null;
	UserService service = null;
	@Before
	public void setUp() {
		if (null == jpa) {
			jpa = injector.getInstance(JPAPersistenceInitializer.class);
		}
		if (null == service) {
			service = injector.getInstance(UserService.class);
		}
	}
	@Test
	public void testDistance() throws Exception {
		System.out.println("distance:" + service.distance(60290, 60632));
	}
	
		
	@Test
	public void testgetUserById() throws Exception {
		User user = service.getUserbyId("admin@snow.com");
		Assert.assertEquals("Expected match userid", "admin@snow.com",
				user.getUserId());
	}
	
	@Test
	public void testgetClientsCount() throws Exception {
		int count = service.getNumberofClients();
		Assert.assertEquals("Expected match ", 3, count);
	}
	
	@Test
	public void testgetVendorsCount() throws Exception {
		int count = service.getNumberofVendors();
		Assert.assertEquals("Expected match ", 3, count);
	}
	@Test
	public void testgetCompanyByType() throws Exception {
		List<CompanyV> companies = service.getCompanyByType(Company.CLIENT);
		Assert.assertEquals("Expected match ", 3, companies.size());
		CsvConfiguration config = new CsvConfiguration();
		config.setFieldDelimiter(',');
//		config.setQuoteCharacter('"');
		Serializer serializer = CsvIOFactory.createFactory(config, CompanyV.class).createSerializer();
		StringWriter writer = new StringWriter();
		serializer.open(writer);
		// call serializer.write for every object to serialize
		for (CompanyV c: companies) {
			serializer.write(c);
		}
		serializer.close(true);
		System.out.println(writer.toString());
	}
	
	@Test
	public void testCreateGeo() throws Exception {
//		JSONObject obj = Utils.getGeoLocationFromZipcode("98037");
//		JSONArray array = (JSONArray)obj.get("results");
//		JSONObject entity = (JSONObject)array.get(0);
//		System.out.println(entity);
//		System.out.println((String)entity.get("formatted_address"));
//		JSONObject geometry = (JSONObject)entity.get("geometry");
//		JSONObject bounds = (JSONObject)geometry.get("bounds");
//		JSONObject viewport = (JSONObject)geometry.get("viewport");
//		JSONObject location = (JSONObject)geometry.get("location");
//		System.out.println(geometry);
//		System.out.println(bounds);
//		System.out.println(viewport);
//		System.out.println(location);
//		System.out.println((Double)location.get("lat"));
//		System.out.println((Double)location.get("lng"));
//		System.out.println(((Double)location.get("lat")));
//		System.out.println(((Double)location.get("lng")));
//		service.addZipCodeGeo(98037, 
//				(Double)location.get("lat"), 
//				(Double)location.get("lng"), 
//				(String)entity.get("formatted_address"));
//		System.out.println("Successfully add geo");
		ZipcodeGeo test = service.getGeoByZipcode(98037);
		System.out.println("lat" + (double)test.getLat());
		System.out.println("lng" + (double)test.getLng());
	}

}
