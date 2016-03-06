package com.autentia.coursesapp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.autentia.coursesapp.dao.TeacherDao;
import com.autentia.coursesapp.mappers.TeacherMapper;
import com.autentia.coursesapp.model.Teacher;

@Repository
public class TeacherDaoImpl implements TeacherDao{
	
	@Autowired
	TeacherMapper teacherMapper;

	public List<Teacher> teachers() {
		return teacherMapper.teachers();
	}

}
