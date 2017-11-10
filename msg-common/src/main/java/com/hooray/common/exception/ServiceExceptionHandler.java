package com.hooray.common.exception;

import com.hooray.common.utils.api.APIResult;
import com.hooray.common.utils.api.APIResultEnum;
import com.hooray.common.utils.api.APIResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常类捕获处理: 捕获service已知异常，controller往外抛未知异常信息
 *
 *@author  Orochi-Yzh
 *@dateTime  2017/11/9 12:53
 *@updatetor
 */
@RestControllerAdvice
public class ServiceExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

	/**
	 * 处理已知service的异常
	 *@param  e 异常
	 *@return  APIResult
	 *
	 *@author  Orochi-Yzh
	 *@dateTime  2017/11/9 14:09
	 *@updatetor
	 */
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public APIResult handle(Exception e) {
		logger.error(e.getMessage(),e);
		if(e instanceof ServiceException){
			ServiceException serviceException = (ServiceException) e;
			return APIResultUtil.error(serviceException.getErrorCode(), serviceException.getMessage());
		}else if(e instanceof DuplicateKeyException){
			return APIResultUtil.error(APIResultEnum.FWK_DUPLICATEKEY.getCode(), APIResultEnum.FWK_DUPLICATEKEY.getMsg() + ": " + e.getMessage());
		}
		// 其他异常
		return APIResultUtil.error(APIResultEnum.UNKOWM_ERROR.getCode(), APIResultEnum.UNKOWM_ERROR.getMsg() + ": " + e.getMessage());
	}

}
