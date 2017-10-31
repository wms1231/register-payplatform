package com.bsoft.tools;

import com.alibaba.fastjson.JSONObject;

public class JSONObjectUtils {
	public final static JSONObject jObj = new JSONObject();

	/**
	 * 返回默认的成功json
	 * 
	 * @return
	 */
	public static String getDefSuccessJson() {
		jObj.put("code", 0);
		jObj.put("msg", "success");
		return jObj.toJSONString();
	}

	/**
	 * 返回成功json
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static String getDefSuccessJson(int code, String msg) {
		jObj.put("code", code);
		jObj.put("msg", msg);
		return jObj.toJSONString();
	}

	/**
	 * 返回默认的错误json
	 * 
	 * @return
	 */
	public static String getDefFailJson() {
		jObj.put("code", -1);
		jObj.put("msg", "fail");
		return jObj.toJSONString();
	}

	/**
	 * 返回默认的json
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static String getFailJson(int code, String msg) {
		jObj.put("code", code);
		jObj.put("msg", msg);
		return jObj.toJSONString();
	}

}
