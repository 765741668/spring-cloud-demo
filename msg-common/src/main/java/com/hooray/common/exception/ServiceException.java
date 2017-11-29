package com.hooray.common.exception;

import com.hooray.common.utils.api.APIResultEnum;

/**
 * service异常
 *
 *@author  Orochi-Yzh
 *@dateTime  2017/11/9 12:53
 *@updatetor
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 656596713468286496L;
	
	/** 异常代码 */
	private Integer errorCode;
	/** 异常信息 */
	private String errorMsg;
	/** 根异常，保持异常链 */
	private Throwable caused;

	public ServiceException(APIResultEnum resultEnum, Throwable caused){
		super(caused);
		this.errorCode = resultEnum.getCode();
		this.errorMsg = resultEnum.getMsg();
	}


	public ServiceException(Integer errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public ServiceException(Integer errorCode, Throwable caused) {
		super(caused);
		this.errorCode = errorCode;
		this.caused = caused;
	}

	public ServiceException(Integer errorCode, String errorMsg,
			Throwable caused) {
		super(errorMsg, caused);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.caused = caused;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public Throwable getCaused() {
		return caused;
	}
	
	
}