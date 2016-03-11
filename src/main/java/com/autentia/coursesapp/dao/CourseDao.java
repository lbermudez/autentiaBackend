package com.autentia.coursesapp.dao;

import java.util.List;

import com.autentia.coursesapp.model.Course;

public interface CourseDao {

	public List<Course> findCoursesByActiveAndSort(Boolean active, String sort);
	public List<Course> findCoursesByActiveAndSortAndPage(Boolean active, String sort, Integer offset, Integer count );
	public void insertCourse(Course course);
	public Integer getCountCourses(Boolean active);
}
