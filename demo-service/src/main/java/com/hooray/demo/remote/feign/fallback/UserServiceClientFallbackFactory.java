package com.hooray.demo.remote.feign.fallback;

import com.hooray.common.utils.api.APIResult;
import com.hooray.common.utils.api.APIResultEnum;
import com.hooray.common.utils.api.APIResultUtil;
import com.hooray.demo.remote.feign.client.UserServiceClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClientFallbackFactory implements FallbackFactory<UserServiceClient> {

	private Logger logger = LoggerFactory.getLogger(UserServiceClientFallbackFactory.class);

	@Override
	public UserServiceClient create(Throwable throwable) {
		return new UserServiceClient() {
			@Override
			public APIResult findById(Long id) {
				logger.info("调用user-service发生异常");
				return APIResultUtil.error(APIResultEnum.FAIL,null);
			}
		};
	}
}