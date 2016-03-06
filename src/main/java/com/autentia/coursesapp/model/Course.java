package com.autentia.coursesapp.model;

import java.io.Serializable;

public class Course implements Serializable {
	private String title;
	private Integer hours;
	private Teacher teacher;
	private Boolean active;
	private Level level;

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