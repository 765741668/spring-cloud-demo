package com.hooray.demo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {
	
	private static final long serialVersionUID = -22710316944602111L;

	private Long id;
	
	private String name;
	
	private int age;
}
