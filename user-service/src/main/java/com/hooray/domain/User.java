package com.hooray.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = -22710316944602111L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;

	@Column
	@ElementCollection
	private List<String> interests;

	@Column
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

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}
}
