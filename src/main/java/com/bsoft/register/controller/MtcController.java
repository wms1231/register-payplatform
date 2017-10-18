package com.bsoft.register.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.register.service.MtcService;

@Controller
@RequestMapping("/user")
public class MtcController {

	@Autowired
	private MtcService mtcService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Map<String, Object> param) {
		return mtcService.login(request, response, param);
	}

	/**
	 * 修改密码
	 * 
	 * @param number
	 * @param inputOldPwd
	 * @param inputNewPwd
	 * @return
	 */
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String changePwd(@RequestParam(value = "number", required = true) String number,
			@RequestParam(value = "inputOldPwd", required = true) String inputOldPwd,
			@RequestParam(value = "inputNewPwd", required = true) String inputNewPwd) {
		
		return mtcService.changePwd(number, inputOldPwd, inputNewPwd);
	}

}
