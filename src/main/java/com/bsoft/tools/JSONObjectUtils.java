package com.bsoft.tools;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.bsoft.domain.DeptReturnBean;
import com.bsoft.exception.MessageException;

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
	public static String getSuccessJson(int code, String msg) {
		jObj.put("code", code);
		jObj.put("msg", msg);
		return jObj.toJSONString();
	}

	public static <E> String getSuccessJsonWithList(int code, String msg, String nodeName, List<E> data) {
		jObj.put("code", code);
		jObj.put("msg", msg);
		jObj.put(nodeName, data);
		return jObj.toJSONString();
	}


	public static <E> String getSuccessJsonWithMap(int code, String msg, String nodeName, Map<String, List<E>> data) {
		jObj.put("code", code);
		jObj.put("msg", msg);
		jObj.put(nodeName, data);
		return jObj.toJSONString();
	}

	public static <E> String getSuccessJsonWitObj(int code, String msg, String nodeName, E e) {
		jObj.put("code", code);
		jObj.put("msg", msg);
		jObj.put(nodeName, e);
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

	public static String getReturnMsg(String content) {
		if (StringUtils.isNotBlank(content)) {
			return JSONObjectUtils.getDefSuccessJson();
		}
		return JSONObjectUtils.getDefFailJson();
	}

}
