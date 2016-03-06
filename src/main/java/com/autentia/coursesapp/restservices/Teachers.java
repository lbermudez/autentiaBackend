package com.autentia.coursesapp.restservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autentia.coursesapp.dao.impl.TeacherDaoImpl;
import com.autentia.coursesapp.model.Teacher;

@Component
@Path("/teachers")
public class Teachers {
	private static final Logger log = LoggerFactory.getLogger(Teachers.class);
	
	@Autowired	
	private TeacherDaoImpl teacherDao;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })	
	public Response getTeachers() {
		log.debug("Retrieving teachers");
		List<Teacher> teachers = teacherDao.teachers();
		if (teachers != null) {
			return Response.status(200).entity(teachers).build();
		} else {
			return Response.status(400).entity("error").build();
		}		
	}
}
