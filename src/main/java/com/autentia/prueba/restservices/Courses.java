package com.autentia.prueba.restservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autentia.prueba.dao.impl.CourseDaoImpl;
import com.autentia.prueba.model.Course;

@Component
@Path("/courses")
public class Courses {
	private static final Logger log = LoggerFactory.getLogger(Courses.class);
	
	@Autowired	
	private CourseDaoImpl courseManager;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Course> getCourses(@QueryParam("active") Boolean active, @QueryParam("sort") String sort) {
		log.debug("Retrieving courses");
		List<Course> courses = courseManager.courses(active, sort);				
		return courses;
	}
}
