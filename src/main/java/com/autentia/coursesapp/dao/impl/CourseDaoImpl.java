package com.autentia.coursesapp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.autentia.coursesapp.dao.CourseDao;
import com.autentia.coursesapp.mappers.CourseMapper;
import com.autentia.coursesapp.model.Course;

@Repository
public class CourseDaoImpl implements CourseDao {
	
	@Autowired
	CourseMapper courseMapper;

	@Override	
	public List<Course> findCoursesByActiveAndSort(Boolean active, String sort) {
		return courseMapper.coursesByActiveAndSort(active, sort);
	}

	@Override
	public void insertCourse(Course course) {
		courseMapper.insertCourse(course);		
	}

}
