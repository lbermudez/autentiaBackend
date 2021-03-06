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

	@Override
	public List<Course> findCoursesByActiveAndSortAndPage(Boolean active, String sort, Integer offset, Integer count) {
		return courseMapper.coursesByActiveAndSortAndPage(active, sort, offset, count);
	}

	@Override
	public Integer getCountCourses(Boolean active) {
		return courseMapper.countCourses(active);
	}

}
