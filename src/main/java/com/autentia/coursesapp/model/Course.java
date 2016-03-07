package com.autentia.coursesapp.model;

import java.io.Serializable;

public class Course implements Serializable {
	private String title;
	private Integer hours;
	private Teacher teacher;
	private Boolean active;
	private Level level;
	
	public Course() {
		super();
	}

	public Course(String title, Integer hours, Teacher teacher, Level level, Boolean active) {
		super();
		this.title = title;
		this.hours = hours;
		this.teacher = teacher;
		this.active = active;
		this.level = level;
	}
	
	public static Course getInstance(String title, Integer hours, Integer teacherId, String level, Boolean active) {		
		return new Course(title, hours, Teacher.getInstance(teacherId), Level.valueOf(level), active);
	}
	
	@Override
	public String toString() {
		return "Course [title=" + title + ", hours=" + hours + ", teacher=" + teacher + ", active=" + active
				+ ", level=" + level + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}	
}