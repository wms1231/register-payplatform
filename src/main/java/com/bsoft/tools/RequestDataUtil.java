package com.bsoft.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;

/**
 * @author wms1231
 *
 */
public class RequestDataUtil {

	/**
	 * request对象获取
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getRequestParmByAttribute(HttpServletRequest request, String name) {
		String namesStr = (String) request.getAttribute(name);
		if (StringUtil.isEmpty(namesStr)) {
			return "";
		} else {
			return namesStr;
		}
	}

	/**
	 * 通过parameter获取值
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getRequestParmByParameter(HttpServletRequest request, String name) {
		if (StringUtil.isEmpty(request.getParameter(name))) {
			return "";
		} else {
			return request.getParameter(name);
		}
	}

	/**
	 * 将参数组装成xml
	 * 
	 * @param map
	 * @return
	 */
	public static String generatorRequestXml(Map<String, String> map) {
		StringBuffer data = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <body> ");
		for (Entry<String, String> entry : map.entrySet()) {
			data.append("<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">");

		}
		data.append("</body>");
		return data.toString();
	}

	/**
	 * 根据key返回value
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getValueForKey(Map<String, Object> map, String key) {
		if (map == null) {
			return "";
		}
		try {
			Object codeObj = map.get(key);
			if (codeObj != null) {
				return (String) map.get(key);
			} else {
				// 转化为大写继续转换
				key = key.toUpperCase();
				codeObj = map.get(key);
				if (codeObj != null) {
					return (String) map.get(key);
				}
			}

		} catch (Exception e) {
			try {
				return (Integer) map.get(key) + "";
			} catch (Exception e1) {
				BigDecimal monery = (BigDecimal) map.get(key);
				monery = monery.abs();// 取绝对值
				return monery + "";
			}
		}

		return "";

	}

	public static String getValueWithBigDecimalOrString(Map<String, Object> map, String key) {
		if (map == null) {
			return "";
		}

		String value = "";
		try {
			value = (String) map.get(key);
		} catch (Exception e) {
			BigDecimal tempValue = (BigDecimal) map.get(key);
			value = tempValue.toString();
		}

		return value;

	}

	/**
	 * 接收post请求request中的数据，并且转换成Map数据
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static JSONObject getRequestData(HttpServletRequest request) throws IOException, DocumentException {
		BufferedReader reader = request.getReader();
		char[] buf = new char[512];
		int len = 0;
		StringBuffer contentBuffer = new StringBuffer();
		while ((len = reader.read(buf)) != -1) {
			contentBuffer.append(buf, 0, len);
		}
		String content = contentBuffer.toString();
		if (content == null) {
			content = "";
		}
		JSONObject obj = JSON.parseObject(content);
		return obj;
	}

	/**
	 * 替换域名和端口
	 * 
	 * @param domain
	 * @param port
	 * @param url
	 * @return
	 */
	public static String replaceDomainAndPort(String domain, String port, String url) {
		String url_bak = "";
		if (url.indexOf("//") != -1) {
			String[] splitTemp = url.split("//");
			url_bak = splitTemp[0] + "//";
			if (!StringUtil.isEmpty(port)) {
				url_bak = url_bak + domain + ":" + port;
			} else {
				url_bak = url_bak + domain;
			}
			if (splitTemp.length >= 1 && splitTemp[1].indexOf("/") != -1) {
				String[] urlTemp2 = splitTemp[1].split("/");
				if (urlTemp2.length > 1) {
					for (int i = 1; i < urlTemp2.length; i++) {
						url_bak = url_bak + "/" + urlTemp2[i];
					}
				}
				System.out.println("url_bak:" + url_bak);
			} else {
				System.out.println("url_bak:" + url_bak);
			}
		}
		return url_bak;
	}

	/**
	 * 从jsonObject中获取值
	 * 
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public static String getStringByKey(JSONObject jsonObject, String key) {
		if (jsonObject == null) {
			return "";
		}

		String value = jsonObject.getString(key);
		if (StringUtils.isBlank(value)) {
			return "";
		}

		return value;
	}

	/**
	 * 根据输入参数组合map
	 * 
	 * @param <E>
	 * 
	 * @param names
	 * @param values
	 * @return
	 */
	public static <E> Map<String, Object> getMapByInputParam(List<String> names, List<E> values) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (names == null || values == null) {
			return map;
		}

		for (int i = 0; i < names.size(); i++) {
			map.put(names.get(i), values.get(i));
		}
		return map;
	}

	public static void main(String[] args) {
		Map<String, Object> mapByInputParam = RequestDataUtil.getMapByInputParam(Arrays.asList("name", "age"),
				Arrays.asList("wms", "19"));
		System.out.println(mapByInputParam);
	}

}
