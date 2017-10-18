package com.bsoft.register.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MtcService {

	public String changePwd(String number, String inputOldPwd, String inputNewPwd);
	
	public String login(HttpServletRequest request, HttpServletResponse response, Map<String, Object> param);

}
