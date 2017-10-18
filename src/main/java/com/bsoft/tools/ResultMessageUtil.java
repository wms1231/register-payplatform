package com.bsoft.tools;

import java.util.HashMap;
import java.util.Map;

import com.bsoft.constant.CommonConstant;
import com.bsoft.support.ResultMessage;

public class ResultMessageUtil {
	/*
	 * public static final int APP_REQUEST_SUCCESS_CODE = 200;// 200成功 public
	 * static final int APP_REQUEST_DECRYPT_FAIL_CODE = 201;// 201非法请求（解密失败）
	 * public static final int APP_REQUEST_PARAM_ERROR_CODE = 203;// 203参数错误
	 * public static final int APP_REQUEST_ERROR_CODE = 204 ;// 其它错误
	 */
	private static Map<String, Object> body = new HashMap<>();
	private static ResultMessage resultMessage = new ResultMessage();

	public static ResultMessage getDefaultResultMessage(String tag, Object obj, int code, String desc) {
		resultMessage.setCode(code);
		resultMessage.setMsg(desc);
		body.clear();
		body.put(tag, obj);

		resultMessage.setBody(body);
		return resultMessage;
	}

	public static ResultMessage getDefaultSuccess(String tag, Object obj) {
		resultMessage.setCode(CommonConstant.APP_REQUEST_SUCCESS_CODE);
		resultMessage.setMsg(CommonConstant.LOGIN_SUCCESS_RETURN_MESSAGE);
		body.clear();
		body.put(tag, obj);

		resultMessage.setBody(body);
		return resultMessage;
	}

	public static ResultMessage getDefaultParamError(String tag, Object obj) {
		resultMessage.setCode(CommonConstant.APP_REQUEST_PARAM_ERROR_CODE);
		resultMessage.setMsg(CommonConstant.APP_REQUEST_PARAM_ERROR_CODE_DESC);
		body.clear();
		body.put(tag, obj);
		resultMessage.setBody(body);

		return resultMessage;
	}

	public static ResultMessage getDefaultOtherError(String tag, Object obj) {
		// code
		resultMessage.setCode(CommonConstant.APP_REQUEST_ERROR_CODE);
		resultMessage.setMsg(CommonConstant.APP_REQUEST_ERROR_CODE_DESC);
		body.clear();
		body.put(tag, obj);
		resultMessage.setBody(body);

		return resultMessage;
	}

	/**
	 * 用户返回提示异常信息
	 * 
	 * @param tag
	 * @param obj
	 * @param message
	 * @return
	 */
	public static ResultMessage getSpecialOtherError(String tag, Object obj, String message) {
		// code
		body.clear();
		resultMessage.setCode(CommonConstant.APP_REQUEST_ERROR_CODE);
		resultMessage.setMsg(message);

		body.put(tag, obj);
		resultMessage.setBody(body);

		return resultMessage;
	}

	public static ResultMessage getDefaultDecryptFail(String tag, Object obj) {
		body.clear();
		resultMessage.setCode(CommonConstant.APP_REQUEST_DECRYPT_FAIL_CODE);// 成功200
		resultMessage.setMsg(CommonConstant.APP_REQUEST_DECRYPT_FAIL_CODE_DESC);

		body.put(tag, obj);
		resultMessage.setBody(body);

		return resultMessage;
	}

	public static Map<String, Object> getSuccessMap(Object content) {
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("code", CommonConstant.APP_REQUEST_SUCCESS_CODE);
		returnMap.put("msg", CommonConstant.LOGIN_SUCCESS_RETURN_MESSAGE);
		returnMap.put("body", content);

		return returnMap;
	}

	public static Map<String, Object> getParamErrorMap(Object content) {
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("code", CommonConstant.APP_REQUEST_PARAM_ERROR_CODE);
		returnMap.put("msg", CommonConstant.APP_REQUEST_PARAM_ERROR_CODE_DESC);
		returnMap.put("body", content);

		return returnMap;
	}
	
	public static Map<String, Object> getParamErrorMap(String message,Object content) {
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("code", CommonConstant.APP_REQUEST_PARAM_ERROR_CODE);
		returnMap.put("msg", message);
		returnMap.put("body", content);

		return returnMap;
	}

	public static Map<String, Object> getOtherErrorMap(Object content) {
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("code", CommonConstant.APP_REQUEST_ERROR_CODE);
		returnMap.put("msg", CommonConstant.APP_REQUEST_ERROR_CODE_DESC);
		returnMap.put("body", content);

		return returnMap;
	}

	public static Map<String, Object> getSpecialOtherErrorMap(Object content, String message) {
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("code", CommonConstant.APP_REQUEST_ERROR_CODE);
		returnMap.put("msg", message);
		returnMap.put("body", content);

		return returnMap;
	}

	public static Map<String, Object> getDecryptFailMap(Object content) {
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("code", CommonConstant.APP_REQUEST_DECRYPT_FAIL_CODE);
		returnMap.put("msg", CommonConstant.APP_REQUEST_DECRYPT_FAIL_CODE_DESC);
		returnMap.put("body", content);

		return returnMap;
	}

	public static Map<String, Object> getServiceFailMap(Object content) {
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("code", CommonConstant.APP_SERVICE_ERROR_CODE);
		returnMap.put("msg", CommonConstant.APP_SERVICE_ERROR_CODE_DESC);
		returnMap.put("body", content);

		return returnMap;
	}
	public static Map<String, Object> getSpecialServiceFailMap(Object content,String msg) {
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("code", CommonConstant.APP_SERVICE_ERROR_CODE);
		returnMap.put("msg", msg);
		returnMap.put("body", content);
		
		return returnMap;
	}
	
	
	//返回字符串的形式===================
	public static String getSpecialServiceFail(Object content,String msg) {
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("code", CommonConstant.APP_SERVICE_ERROR_CODE);
		returnMap.put("msg", msg);
		returnMap.put("body", content);
		return FastJsonUtil.toJSONString(returnMap);
	}
	
	
	public static String getParamError(String message,Object content) {
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("code", CommonConstant.APP_REQUEST_PARAM_ERROR_CODE);
		returnMap.put("msg", message);
		returnMap.put("body", content);
		return FastJsonUtil.toJSONString(returnMap);
	}

	public static String getSpecialOtherError(Object content, String message) {
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("code", CommonConstant.APP_REQUEST_ERROR_CODE);
		returnMap.put("msg", message);
		returnMap.put("body", content);
		
		return FastJsonUtil.toJSONString(returnMap);
	}
	
	public static String getSuccess(Object content) {
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("code", CommonConstant.APP_REQUEST_SUCCESS_CODE);
		returnMap.put("msg", CommonConstant.LOGIN_SUCCESS_RETURN_MESSAGE);
		returnMap.put("body", content);

		return FastJsonUtil.toJSONString(returnMap);
	}
}
