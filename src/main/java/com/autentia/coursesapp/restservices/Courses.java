package com.autentia.coursesapp.restservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autentia.coursesapp.dao.impl.CourseDaoImpl;
import com.autentia.coursesapp.model.Course;

@Component
@Path("/courses")
public class Courses {
	private static final Logger log = LoggerFactory.getLogger(Courses.class);
	
	@Autowired	
	private CourseDaoImpl courseDao;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })	
	public Response getCourses(@QueryParam("active") Boolean active, @QueryParam("sort") String sort) {
		log.debug("Retrieving courses");
		List<Course> courses = courseDao.findCoursesByActiveAndSort(active, sort);
		if (courses != null) {
			return Response.status(200).entity(courses).build();
		} else {
			return Response.status(400).entity("error").build();
		}		
	}
}
