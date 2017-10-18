package com.bsoft.exception;

/**
 * 掌上支付异常处理
 * 
 * @author wms1231
 *
 */

public class HandPayException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public String message;

	public HandPayException() {
		super();
	}

	public HandPayException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
