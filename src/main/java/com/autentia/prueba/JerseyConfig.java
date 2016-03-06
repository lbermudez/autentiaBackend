package com.autentia.prueba;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.autentia.prueba.restservices.Courses;
import com.autentia.prueba.restservices.ReverseEndpoint;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(Courses.class);
		register(ReverseEndpoint.class);
	}

}
