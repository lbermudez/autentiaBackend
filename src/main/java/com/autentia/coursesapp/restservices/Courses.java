package com.autentia.coursesapp.restservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	public Response getCourses(@QueryParam("active") Boolean active,
							   @QueryParam("sort") String sort) {
		log.debug("Retrieving courses");
		List<Course> courses = courseDao.findCoursesByActiveAndSort(active, sort);
		if (courses != null) {
			return Response.status(200).entity(courses).build();
		} else {
			return Response.status(400).entity("error").build();
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Transactional
	public Response createCourse(@FormParam("title") String title,
								 @FormParam("hours") Integer hours,
								 @FormParam("teacherId") Integer teacherId,
								 @FormParam("level") String level,
								 @FormParam("active") Boolean active) {

		log.debug("Creating course");
		courseDao.insertCourse(Course.getInstance(title, hours, teacherId, level, active));
		return Response.status(201).entity("Course create success").build();
	}
}
