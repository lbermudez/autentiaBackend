package com.autentia.coursesapp.restservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.autentia.coursesapp.dao.CourseDao;
import com.autentia.coursesapp.model.Course;

// TODO: Habria que meter validadores de para los parametros.
@Component
@Path("/courses")
public class Courses {
	private static final Logger log = LoggerFactory.getLogger(Courses.class);
	public static final Boolean ACTIVES = true;
	public static final Boolean NOT_ACTIVES = false;
	public static final String ASC_SORT = "asc";
	public static final String DESC_SORT = "desc";
	
	@Autowired
	private CourseDao courseDao;
	

	@GET @Path("/{active}/{sort}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCourses(@PathParam("active") Boolean active,
							   @PathParam("sort") String sort) {		
		List<Course> courses;
		log.debug("Retrieving courses");
		try {
			courses = courseDao.findCoursesByActiveAndSort(active, sort);
			return Response.status(200).entity(courses).build();
		} catch(Exception e) {
				return Response.status(500).entity("Error").build();
		}		
	}
	
	

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Transactional
	public Response createCourseFromForm(@FormParam("title") String title,
										 @FormParam("hours") Integer hours,
										 @FormParam("teacherId") Integer teacherId,
										 @FormParam("level") String level,
										 @FormParam("active") Boolean active) {
		log.debug("Creating course");
		try {
			courseDao.insertCourse(Course.getInstance(title, hours, teacherId, level, active));
			return Response.status(200).entity("Course created successfully").build();
		} catch(Exception e) {
			return Response.status(500).entity("Error").build();
		}
	}
	
	
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Transactional
	public Response createCourse(Course course) {
		log.info("Creating course");
		try {
			courseDao.insertCourse(course);
			return Response.status(200).entity("Course created successfully").build();
		} catch(Exception e) {
			return Response.status(400).entity("error").build();
		}
	}
}
