package com.autentia.coursesapp.dao;

import java.util.List;

import com.autentia.coursesapp.model.Course;

public interface CourseDao {

	List<Course> findCoursesByActiveAndSort(Boolean active, String sort);

}
