package com.bsoft.tools.http;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by sixtynine on 2017/3/28.
 */
public class AesUtil {

	private static final int BYTE_LENGTH = 128;

	public static byte[] encrypt(byte[] content, String key) throws AesException {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(key.getBytes());
			keyGenerator.init(BYTE_LENGTH, secureRandom);

			SecretKey secretKey = keyGenerator.generateKey();
			byte[] encodeFormat = secretKey.getEncoded();

			SecretKeySpec secretKeySpec = new SecretKeySpec(encodeFormat, "AES");

			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

			// 正式执行加密操作
			return cipher.doFinal(content);

		} catch (Throwable e) {
			throw new AesException(e);
		}
	}

	public static byte[] decrypt(byte[] content, String key) throws AesException {

		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");

			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(key.getBytes());
			kgen.init(BYTE_LENGTH, secureRandom);

			SecretKey secretKey = kgen.generateKey();
			byte[] encodeFormat = secretKey.getEncoded();
			SecretKeySpec secretKeySpec = new SecretKeySpec(encodeFormat, "AES");
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("AES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			// 正式执行解密操作
			return cipher.doFinal(content);
		} catch (Throwable e) {
			throw new AesException(e);
		}

	}

	public static class AesException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AesException() {
			super();
		}

		public AesException(String msg) {
			super(msg);
		}

		public AesException(String msg, Throwable throwable) {
			super(msg, throwable);
		}

		public AesException(Throwable throwable) {
			super(throwable);
		}

		public AesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
	}

	public static void main(String[] args) throws AesException, UnsupportedEncodingException {
		byte[] content = "test".getBytes("UTF-8");
		String key = "438o8434383497493048";
		System.out.println("加密前：" + new String(content));

		// 加密
		byte[] resultBytes = encrypt(content, key);
		String resultStr = Base64.encodeBase64String(resultBytes);

		System.out.println("加密后：" + resultStr);

		// 解密
		byte[] deResult = Base64.decodeBase64(resultStr.getBytes("UTF-8"));
		byte[] result = decrypt(deResult, key);

		System.out.println("\n解密后：" + new String(result, "UTF-8"));
	}
}
