package com.tomcat.hosting;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tomcat.hosting.mongo.service.MorphiaService;

public class TestMorphiaService {
	MorphiaService service = null;
	Injector injector = Guice.createInjector(new StandaloneModule());
	@Before
	public void setUp() {
		if (null == service) {
			service = injector.getInstance(MorphiaService.class);
		}
	}
	@Test
	public void testgetVendors() throws Exception {
		service.getVendors();
	}
	@Test
	public void testgetClients() throws Exception {
		service.getClients();
	}
}
