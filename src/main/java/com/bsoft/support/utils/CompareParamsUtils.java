package com.bsoft.support.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/*
 * 比较接收参数是否存在只适用于String和Int类型参数
 * 
 * */
public class CompareParamsUtils {
	/*
	 * params：传递的参数集合Map
	 * paramArray：接口文档标准的参数集合 key：参数名，value:参数类型，如果标准参数类型是Int，判断是否为空或者非数字，String不判断
	 * 
	 * */
	public boolean isAllRightParam(Map<String, Object> params, Map<String, Object> paramArray) {

		if (params.size() < paramArray.size()) {
			return false;
		} else {
			Set<String> keys = paramArray.keySet();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				if (paramArray.get(key) != null && params.get(key) != null) {// 根据Key取值都不为空
					if (paramArray.get(key) == "Int") {
						String param = params.get(key).toString();
						if (!isNumeric(param)) {
							return false;
						}
					}
					String param = params.get(key).toString();
					System.out.println(key);
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isNumeric(String str) {
		if (str.length() > 0) {
			for (int i = 0; i < str.length(); i++) {
				System.out.println(str.charAt(i));
				if (!Character.isDigit(str.charAt(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
