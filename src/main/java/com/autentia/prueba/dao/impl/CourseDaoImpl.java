package com.autentia.prueba.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.autentia.prueba.dao.CourseDao;
import com.autentia.prueba.mappers.CourseMapper;
import com.autentia.prueba.model.Course;

@Repository
public class CourseDaoImpl implements CourseDao {
	
	@Autowired
	CourseMapper courseMapper;

	@Override
	public List<Course> courses(Boolean active, String sort) {
		List<Course> courses = courseMapper.courses(true);		
		return courses;
	}

}
