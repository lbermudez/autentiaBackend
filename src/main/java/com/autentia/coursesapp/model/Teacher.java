package com.autentia.coursesapp.model;

import java.io.Serializable;

public class Teacher implements Serializable {
	private Integer id;
	private String name;
	private String lastName1;
	private String lastName2;
	
	public Teacher() {
		super();		
	}

	public Teacher(Integer id, String name, String lastName1, String lastName2) {
		super();
		this.id = id;
		this.name = name;
		this.lastName1 = lastName1;
		this.lastName2 = lastName2;
	}

	public static Teacher getInstance(Integer teacherId) {
		return new Teacher(teacherId, null, null, null);
	}	

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", lastName1=" + lastName1 + ", lastName2=" + lastName2 + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName1() {
		return lastName1;
	}

	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}
}
