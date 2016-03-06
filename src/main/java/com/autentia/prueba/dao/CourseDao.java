package com.autentia.prueba.dao;

import java.util.List;

import com.autentia.prueba.model.Course;

public interface CourseDao {

	List<Course> courses(Boolean active, String sort);

}
