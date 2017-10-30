package com.bsoft.exception;

import com.bsoft.support.utils.ResultMessageUtils;

/**
 * 短信异常处理
 * 
 * @author wms1231
 *
 */
public class MessageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String message;

	public MessageException(String errorCode, String message) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public MessageException(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		if (message == null)
			message = ResultMessageUtils.getBundleMessage(errorCode);
		return message;
	}

}
