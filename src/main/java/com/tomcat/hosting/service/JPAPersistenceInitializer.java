package com.tomcat.hosting.service;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class JPAPersistenceInitializer {
	@Inject
	JPAPersistenceInitializer(PersistService service) {
		service.start();
	}
}
