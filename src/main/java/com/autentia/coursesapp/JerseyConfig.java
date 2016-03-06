package com.autentia.coursesapp;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.autentia.coursesapp.restservices.Courses;
import com.autentia.coursesapp.restservices.Teachers;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(Courses.class);
		register(Teachers.class);
	}

}
