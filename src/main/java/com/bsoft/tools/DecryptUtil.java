package com.bsoft.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

import com.sun.org.apache.xml.internal.security.utils.Base64;

@SuppressWarnings("restriction")
public class DecryptUtil {
	// TODO 这个key是变化的怎么验证
	public static final String key = "sddssfdxxccxdsdsbfdf43345ttrh" + "&" + System.currentTimeMillis();

	/**
	 * 解密字符串并以字符串返回
	 * 
	 * @param key
	 * @param acl
	 * @return
	 * @throws Exception
	 */
	public static String decryptToString(String key, String acl) throws Exception {
		String result = null;

		if (StringUtils.isBlank(key)) {
			key = DecryptUtil.key;
		}

		if (StringUtils.isBlank(key)) {
			return result;
		}

		String decodeparamsStr = URLDecoder.decode(acl, "UTF-8");
		byte[] decodeStr = Base64.decode(decodeparamsStr);

		result = new String(AesUtil.decrypt(decodeStr, key), "UTF-8");

		return result;

	}

	/**
	 * 加密
	 * 
	 * @param key
	 * @param acl
	 * @return
	 * @throws Exception
	 */
	public static String encryptToString(String key, String acl) throws Exception {
		String result = null;

		if (StringUtils.isBlank(key)) {
			result = DecryptUtil.key;
		}

		if (StringUtils.isBlank(key)) {
			return result;
		}

		byte[] paramAes = AesUtil.encrypt(key.getBytes("UTF-8"), key);
		byte[] paramEncode = Base64.encode(paramAes).getBytes("UTF-8");
		result  = URLEncoder.encode(new String(paramEncode), "UTF-8");
		
		return result;

	}
	
	public static String getACL(String appId,String timestamp, String postStr,String key) {
		Charset UTF_8 = StandardCharsets.UTF_8;

		//String key = "sddssfdxxccxdsdsbfdf43345ttrh" + "&" + timestamp;

		String aclPostStr = postStr;
		if (aclPostStr.length() > 64) {
			aclPostStr = aclPostStr.substring(0, 64);
		}
		
		byte[] aclAes = AesUtil.encrypt((appId + "&" + aclPostStr).getBytes(UTF_8), key);

		try {
			return URLEncoder.encode(Base64.encode(aclAes), UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return  null;
	}

}
