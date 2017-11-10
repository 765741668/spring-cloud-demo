package com.hooray.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hooray.client.UserServiceClient;
import com.hooray.entity.User;

@RestController
public class SampleController {
	
	@Autowired
	private UserServiceClient userServiceClient;
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Long id) {
		return userServiceClient.findById(id);
	}
}
