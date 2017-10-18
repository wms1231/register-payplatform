/**
 * @(#)Constants.java Created on Aug 19", 2009 11:35:35 AM
 * 
 * 版权：版权所有 bsoft.com.cn 保留所有权力。
 */
package com.bsoft.tools.c3p0;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * 
 * @author <a href="mailto:zhengs@bsoft.com.cn">zhengshi</a>
 */
public class Constants {
	
	public static final Map<String, String> PIX_MSG = new HashMap<String, String>();
	static {
		PIX_MSG.put("ERR200", "请求执行成功。");
		PIX_MSG.put("ERR300", "XML解析失败!");
		PIX_MSG.put("ERR301", "入参为空");
		PIX_MSG.put("ERR400", "获取返回值失败,返回值全部为空!");
		PIX_MSG.put("ERR500", "获取blob字段失败!");
		PIX_MSG.put("ERR600", "调用存储过程失败!");
		PIX_MSG.put("ERR900", "网络错误!");
		PIX_MSG.put("ERR999", "未知错误！");
	}

	private Constants() {

	}
}
