package com.autentia.prueba.mappers;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.autentia.prueba.model.Course;
import com.autentia.prueba.model.Teacher;

public interface CourseMapper {

	@Select("select c.title,c.hours,c.teacherId,c.active,c.level from courses c where c.active = #{active}")
	@Results(value = {
	        @Result(property = "title", column = "title"),
	        @Result(property = "hours", column = "hours"),
	        @Result(property = "teacher", column = "teacherId", one=@One(select = "getTeacher")),
	        @Result(property = "active", column = "active"),
	        @Result(property = "level", column = "level")
	})
	public List<Course> courses(@Param("active") Boolean active);
	
	@Select("select t.name,t.lastName1,t.lastName2 from teachers t where t.id = #{teacherId}")
	public Teacher getTeacher(Integer teacherId);	
}
