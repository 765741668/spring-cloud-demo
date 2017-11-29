package com.hooray.demo.remote.feign.client;

import com.hooray.common.utils.api.APIResult;
import com.hooray.demo.remote.feign.fallback.UserServiceClientFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",
		fallbackFactory = UserServiceClientFallbackFactory.class)
public interface UserServiceClient {

	@GetMapping(value = "/user/{id}")
	APIResult findById(@PathVariable("id") Long id);
}
