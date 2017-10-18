package com.bsoft.tools;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * json工具类
 * 
 * @author xy
 *
 */
public class FastJsonUtil {

	/**
	 * 将对象转成json串,转换失败返回空字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		// DisableCircularReferenceDetect来禁止循环引用检测
		String returnMsg = "";
		if (object == null) {
			return returnMsg;
		}

		try {
			returnMsg = JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
		} catch (Exception e) {
			return "";
		}
		return returnMsg;
	}

	/**
	 * 将json串转成对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toJSONObject(String json, Class<T> clazz) {
		T obj = null;
		if (json == null || StringUtils.isBlank(json)) {
			return obj;
		}

		if (clazz == null) {
			return obj;
		}

		try {
			obj = JSON.parseObject(json, clazz);
		} catch (Exception e) {
			return null;
		}

		return obj;

	}

	/**
	 * 将json转为对象,如果转换异常返回null
	 * 
	 * @param json
	 * @return
	 */
	public static Object toJSONObject(String json) {

		Object obj = null;

		if (json == null || StringUtils.isBlank(json)) {
			return obj;
		}

		try {
			obj = JSON.parse(json);
		} catch (Exception e) {
			return null;
		}
		return obj;
	}

	// 输出json
	public static void write_json(HttpServletResponse response, String jsonString) {
		response.setContentType("application/json;utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ajax提交后回调的json字符串
	 * 
	 * @return
	 */
	public static String ajaxResult(boolean success, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", success);// 是否成功
		map.put("message", message);// 文本消息
		String json = JSON.toJSONString(map);
		return json;
	}

	/**
	 * JSON串自动加前缀
	 * 
	 * @param json
	 *            原json字符串
	 * @param prefix
	 *            前缀
	 * @return 加前缀后的字符串
	 */

	public static String JsonFormatterAddPrefix(String json, String prefix, Map<String, Object> newmap) {
		if (newmap == null) {
			newmap = new HashMap<String, Object>();
		}
		Map<String, Object> map = (Map) JSON.parse(json);

		for (String key : map.keySet()) {
			Object object = map.get(key);
			if (isEntity(object)) {
				String jsonString = JSON.toJSONString(object);
				JsonFormatterAddPrefix(jsonString, prefix + key + ".", newmap);

			} else {
				newmap.put(prefix + key, object);
			}

		}
		return JSON.toJSONString(newmap);
	}

	/**
	 * 判断某对象是不是实体
	 * 
	 * @param object
	 * @return
	 */
	private static boolean isEntity(Object object) {
		if (object instanceof String) {
			return false;
		}
		if (object instanceof Integer) {
			return false;
		}
		if (object instanceof Long) {
			return false;
		}
		if (object instanceof java.math.BigDecimal) {
			return false;
		}
		if (object instanceof Date) {
			return false;
		}
		if (object instanceof java.util.Collection) {
			return false;
		}
		return true;

	}

	public static void main(String[] args) {
		String param = "{\"body\":{\"feeRecords\":[{\"barCode\":\"APP231\",\"feeNo\":\"50819741\",\"feeTypeCode\":\"1\",\"totalFee\":0.42}],\"hcnTradeNo\":\"2017101021001004150258358622\",\"precalId\":\"316145\",\"resultId\":\"316145\",\"status\":\"1\"},\"code\":\"200\",\"msg\":\"\"}";
		Map<String,Object> jsonObject = FastJsonUtil.toJSONObject(param, Map.class);
		Map<String,Object> jsonBodyObject =  (Map<String, Object>) jsonObject.get("body");
		List<Object> feeRecords = (List<Object>) jsonBodyObject.get("feeRecords");
		
		for (Object object : feeRecords) {
			Map<String,Object> map = (Map<String, Object>) object;
			BigDecimal monery = (BigDecimal) map.get("totalFee");
			System.out.println(monery);
		}
		
		//System.out.println(jsonBodyObject);
	}

}
