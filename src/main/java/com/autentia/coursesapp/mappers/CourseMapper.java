package com.autentia.coursesapp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.autentia.coursesapp.model.Course;
import com.autentia.coursesapp.model.Teacher;

public interface CourseMapper {
	
	@Select("select count(*)"
			+ " from courses c"
			+ " where c.active = #{active}")
	public Integer countCourses(@Param("active") Boolean active);
	
	

	@Select("select c.title, c.hours, c.teacherId, c.active, c.level"
			+ " from courses c"
			+ " where c.active = #{active} order by c.title ${sort}")
	@Results(value = {
	        @Result(property = "title", column = "title"),
	        @Result(property = "hours", column = "hours"),
	        @Result(property = "teacher", column = "teacherId", one=@One(select = "getTeacher")),
	        @Result(property = "active", column = "active"),
	        @Result(property = "level", column = "level")
	})
	public List<Course> coursesByActiveAndSort(
			@Param("active") Boolean active,
			@Param("sort") String sort);	

	
	
	@Select("select c.title, c.hours, c.teacherId, c.active,c.level"
			+ " from courses c"
			+ " where c.active = #{active} order by c.title ${sort} limit #{offset}, #{count}")
	@Results(value = {
	        @Result(property = "title", column = "title"),
	        @Result(property = "hours", column = "hours"),
	        @Result(property = "teacher", column = "teacherId", one=@One(select = "getTeacher")),
	        @Result(property = "active", column = "active"),
	        @Result(property = "level", column = "level")
	})
	public List<Course> coursesByActiveAndSortAndPage(
			@Param("active")Boolean active,
			@Param("sort") String sort,
			@Param("offset") Integer offset,
			@Param("count") Integer count);
	
	
	
	
	@Insert("insert into courses(title, hours, teacherId, active, level)"
			+ " values(#{title}, #{hours}, #{teacher.id}, #{active}, #{level})")
	public void insertCourse(Course course);
	
	
	
	
	@Select("select t.name,t.lastName1,t.lastName2 from teachers t where t.id = #{teacherId}")
	public Teacher getTeacher(Integer teacherId);	
}
