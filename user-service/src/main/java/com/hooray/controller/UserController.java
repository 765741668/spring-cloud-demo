package com.hooray.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hooray.dao.UserRepository;
import com.hooray.domain.User;
import com.hooray.mq.sender.Sender;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private Sender sender;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		int i = RandomUtils.nextInt(10);
		if (i <= 5) {
			throw new RuntimeException("Failed.");
		}
		
		User user = userRepository.findOne(id);
		return user;
	}
	
	@PostMapping("/save")
	public User save(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/name/{name}")
	public User findByName(@PathVariable String name) {
		return userRepository.findByName(name);
	}
	
	@PostMapping("/userInfo")
	public User findByUser(@RequestBody User user) {
		return userRepository.findOne(user.getId());
	}
	
	@PostMapping("/send")
	public User send(@RequestBody User user) {
		sender.sendMsg(user);
		return user;
	}
}
