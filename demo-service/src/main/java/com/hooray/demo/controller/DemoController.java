package com.hooray.demo.controller;

import com.hooray.common.utils.api.APIResult;
import com.hooray.common.utils.api.APIResultUtil;
import com.hooray.demo.remote.feign.client.UserServiceClient;
import com.hooray.demo.remote.rest.UserServiceRestClient;
import com.hooray.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

	@Autowired
	private UserServiceRestClient userServiceRestClient;

	@Autowired
	private UserServiceClient userServiceClient;
	
	@GetMapping("/user/{id}")
	public APIResult findById(@PathVariable Long id) {
		return userServiceClient.findById(id);
	}

	@GetMapping("/user2/{id}")
	public APIResult findById2(@PathVariable Long id) {
		return userServiceRestClient.findById(id);
	}

	@GetMapping("/name/{name}")
	public APIResult findByName(@PathVariable String name) {
		return userServiceRestClient.findByName(name);
	}

	@PostMapping("/userInfo")
	public APIResult findByUser(@RequestBody UserVO user) {
		return userServiceRestClient.findByUser(user);
	}

	@PostMapping("/save")
	public APIResult save(@RequestBody UserVO user) {
		return userServiceRestClient.save(user);
	}

	@PutMapping("/update")
	public APIResult update(@RequestBody UserVO user) {
		userServiceRestClient.update(user);
		return APIResultUtil.success(null);
	}

	@PutMapping("/update2")
	public APIResult update2(@RequestBody UserVO user) {
		userServiceRestClient.update2(user);
		return APIResultUtil.success(null);
	}

	@DeleteMapping("/delete/{id}")
	public APIResult delete(@PathVariable Long id) {
		userServiceRestClient.delete(id);
		return APIResultUtil.success(null);
	}
}
