package com.tomcat.hosting;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tomcat.hosting.mongo.service.MongoService;

public class TestMongoService {
	MongoService service = null;
	Injector injector = Guice.createInjector(new StandaloneModule());
	@Before
	public void setUp() {
		if (null == service) {
			service = injector.getInstance(MongoService.class);
		}
	}
	@Test
	public void testDistance() throws Exception {
		service.execute();
	}
}
