package com.bsoft.support.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResultMessageUtils {

	private static ResourceBundle resb = ResourceBundle.getBundle("system", Locale.getDefault());

	public static String getBundleMessage(String key) {
		try {
			return resb.getString(key);
		} catch (MissingResourceException e) {
			return null;
		}

	}
}
