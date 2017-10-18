package com.bsoft.support;

import java.util.Map;


public class ResultMessage {

	private Integer code;

	private String msg;
	private Map<String, Object> body;

	public ResultMessage() {
		super();
	}

	public ResultMessage(Integer code, String msg, Map<String, Object> body) {
		super();
		this.code = code;
		this.msg = msg;
		this.body = body;
	}
	
	
	

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

}
