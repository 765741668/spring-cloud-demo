package com.hooray.controller;

import com.hooray.common.exception.ServiceException;
import com.hooray.common.utils.api.APIResult;
import com.hooray.common.utils.api.APIResultUtil;
import com.hooray.dao.UserRepository;
import com.hooray.domain.User;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	//TODO 增加Service层事物回滚，demo就不写了
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{id}")
	public APIResult findById(@PathVariable Long id) {
		int i = RandomUtils.nextInt(10);
		if (i <= 5) {
			throw new ServiceException(-1,"随机到id<=5,给远程调用模拟异常");
		}
		
		User result = userRepository.findOne(id);
		return APIResultUtil.success(result);
	}
	
	@GetMapping("/name/{name}")
	public APIResult findByName(@PathVariable String name) {
		User result = userRepository.findByName(name);
		return APIResultUtil.success(result);
	}
	
	@PostMapping("/userInfo")
	public APIResult findByUser(@RequestBody User user) {
		User result = userRepository.findOne(user.getId());
		return APIResultUtil.success(result);
	}

	@PostMapping("/save")
	public APIResult save(@RequestBody User user) {
		User result = userRepository.save(user);
		return APIResultUtil.success(result);
	}

	@PutMapping("/update")
	public APIResult update(@RequestBody User user) {
		User result = userRepository.save(user);
		return APIResultUtil.success(result);
	}

	@DeleteMapping("/delete/{id}")
	public APIResult deleteById(@PathVariable Long id) {
		userRepository.delete(id);
		return APIResultUtil.success(null);
	}

}
