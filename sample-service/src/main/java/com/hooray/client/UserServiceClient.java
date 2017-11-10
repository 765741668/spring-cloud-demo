package com.hooray.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hooray.entity.User;

@FeignClient(name = "user-service", fallback = UserServiceClient.UserServiceClientFallback.class)
public interface UserServiceClient {
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable("id") Long id);

	@Component
	class UserServiceClientFallback implements UserServiceClient {
		
		private Logger logger = LoggerFactory.getLogger(UserServiceClientFallback.class);

		@Override
		public User findById(Long id) {
			logger.info("调用UserServiceClient发生异常");
			User user = new User();
			user.setId(0L);
			return user;
		}

	}
}
