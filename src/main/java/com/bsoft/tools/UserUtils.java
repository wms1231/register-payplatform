package com.bsoft.tools;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class UserUtils {

	public static String getCurrentUser(HttpServletRequest request) {
		String refunder = "admin";
		String cookieValue = CookieUtils.getCookieValue(request, "userAccount");

		if (StringUtils.isNotBlank(cookieValue)) {
			refunder = cookieValue.split("#")[0];
		}
		return refunder;
	}

}
