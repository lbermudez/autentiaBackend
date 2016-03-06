package com.autentia.coursesapp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.autentia.coursesapp.model.Teacher;

public interface TeacherMapper {
	@Select("select * from teachers t")	
	public List<Teacher> teachers();
}
