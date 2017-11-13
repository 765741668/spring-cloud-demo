package com.hooray.entity;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = -22710316944602111L;

	private Long id;
	
	private String name;
	
	private int age;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
