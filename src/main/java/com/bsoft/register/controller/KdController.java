package com.bsoft.register.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.constant.AppCommonConstant;
import com.bsoft.constant.CommonConstant;
import com.bsoft.register.service.KdService;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.RequestDataUtil;
import com.bsoft.tools.ResultMessageUtil;

/**
 * 快递信息转发
 * 
 * @author lmy
 */

@Controller
@RequestMapping("/kd")
public class KdController {

	private static Logger logger = Logger.getLogger(HandPayController.class);
	@Autowired
	private KdService kdService;

	@RequestMapping(value = "/kdCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String kdCheck(HttpServletRequest request) {
		Date beginTime = new Date();
		String methodName = "kdCheck";
		// 安全校验
		Map<String, Object> requestMap = kdService.requestCheck(beginTime, methodName, request);
		if (!"200".equals(RequestDataUtil.getValueForKey(requestMap, "code2"))) {
			return FastJsonUtil.toJSONString(requestMap);
		}
		requestMap.remove("code2");

		return kdService.invokeCommonService(methodName, AppCommonConstant.SYSTEM_APP_USP_KD,
				AppCommonConstant.SYSTEM_HAND_PROCEDURE_NAME, CommonConstant.SYSTEM_PROCEDURE_SQLKEY, requestMap,
				beginTime);
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public String exceptionHander(Exception ex, HttpServletRequest request) {
		logger.error("其它异常=>" + ex.getMessage());
		return ResultMessageUtil.getSpecialServiceFail(null, "其它错误");
	}

}
