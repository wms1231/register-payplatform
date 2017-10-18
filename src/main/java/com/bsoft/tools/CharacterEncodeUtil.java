package com.bsoft.tools;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import oracle.sql.CLOB;

/**
 * 字符编码工具
 * 
 * @author wms1231
 *
 */
public class CharacterEncodeUtil {

	/**
	 * 请求中文处理
	 * 
	 * @param param
	 * @return
	 */
	public static String requestEncode(String param) {
		if (StringUtils.isBlank(param)) {
			return "";
		}
		try {
			param = new String(param.getBytes("GBK"), "ISO-8859-1");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		//返回正确的结果
		return param;
	}

	/**
	 * 针对String返参处理中文显示处理
	 * 
	 * @param param
	 * @return
	 */
	public static String returnEncode(String param) {
		if (StringUtils.isBlank(param)) {
			return  "";
		}
		try {
			param = new String(param.getBytes("ISO-8859-1"), "GBK");
		} catch (Exception e) {
			e.printStackTrace();
			return  "";
		}
		
		return param;
	}

	/**
	 * 针对blob返参处理中文显示处理
	 * 
	 * @param blob
	 * @return
	 */
	public static String returnEncode(Blob blob) {
		String param = "";
		if (blob == null) {
			return param;
		}
		try {
			param = new String(blob.getBytes((long) 1, (int) blob.length()), "GBK");
		} catch (Exception e) {
			e.printStackTrace();
			param = "";
			return param;
		}
		
		return param;
	}
	public static String returnEncode(CLOB clob) {
		try {
			//String clobString = clob.getSubString(1, (int) clob.length());
			String param ="";
			try {
				param = new String(clob.getSubString(1, (int) clob.length()).getBytes("ISO-8859-1"), "gbk");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return param;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}

}
