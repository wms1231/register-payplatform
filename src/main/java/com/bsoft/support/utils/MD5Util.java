package com.bsoft.support.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {

	public static String md5Encode_bak(String before)  {
		// 加密后的字符串
		String after = before;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			after = base64en.encode(md5.digest(before.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return after;
	}
	
	 public static String md5Encode(String message) {  
	        String md5 = "";  
	        try {  
	            MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象  
	            byte[] messageByte = message.getBytes("UTF-8");  
	            byte[] md5Byte = md.digest(messageByte);              // 获得MD5字节数组,16*8=128位  
	            md5 = bytesToHex(md5Byte);                            // 转换为16进制字符串  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return md5;  
	    }  
	   
	     // 二进制转十六进制  
	    public static String bytesToHex(byte[] bytes) {  
	        StringBuffer hexStr = new StringBuffer();  
	        int num;  
	        for (int i = 0; i < bytes.length; i++) {  
	            num = bytes[i];  
	             if(num < 0) {  
	                 num += 256;  
	            }  
	            if(num < 16){  
	                hexStr.append("0");  
	            }  
	            hexStr.append(Integer.toHexString(num));  
	        }  
	        return hexStr.toString().toUpperCase();  
	    }  
	
	
	public static void main(String[] args) {
		System.out.println(md5Encode("admin123456"));
	}
}
