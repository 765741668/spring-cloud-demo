package com.hooray.common.utils.api;

/**
 * Http请求返回统一格式-Util
 * 
 * @author yangfeng
 * @date 2017年10月31日 上午10:36:45	
 * Email: yangfeng@hooray.cn
 */
public class APIResultUtil {
	
	/**
	 * 请求成功
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static APIResult success(Object o) {
		APIResult apiResult = new APIResult();
		apiResult.setCode(APIResultEnum.SUCCESS.getCode());
		apiResult.setMsg(APIResultEnum.SUCCESS.getMsg());
		apiResult.setData(o);
		
		return apiResult;
	}
	
	/**
	 * 请求成功提示信息
	 */
	@SuppressWarnings("rawtypes")
	public static APIResult success(Integer code, String msg) {
		APIResult apiResult = new APIResult();
		apiResult.setCode(code);
		apiResult.setMsg(msg);
		
		return apiResult;
	}

	/**
	 * 请求成功提示信息
	 */
	public static APIResult success(Integer code, String msg,Object data) {
		return new APIResult(code,msg,data);
	}
	
	/**
	 * 请求失败
	 */
	@SuppressWarnings("rawtypes")
	public static APIResult error(Integer code, String msg) {
		APIResult apiResult = new APIResult();
		apiResult.setCode(code);
		apiResult.setMsg(msg);
		
		return apiResult;
	}
	
	/**
	 * 请求失败
	 */
	public static APIResult error(APIResultEnum apiResultEnum, Object data) {
        return new APIResult(apiResultEnum.getCode(), apiResultEnum.getMsg(), data);
    }
	
	/**
	 * 请求失败提示信息
	 */
	public static APIResult error(Integer code, String msg,Object data) {
		return new APIResult(code,msg,data);
	}

}
