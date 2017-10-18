package com.bsoft.register.controller;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsoft.constant.AppCommonConstant;
import com.bsoft.constant.CommonConstant;
import com.bsoft.exception.HandPayException;
import com.bsoft.register.service.HandPayService;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.RequestDataUtil;
import com.bsoft.tools.ResultMessageUtil;

/**
 * 
 * 接入东华MS_CF门诊费用信息服务接口,提供掌上支付相关服务
 * 
 * @author wms1231
 *
 */
@Controller
@RequestMapping("/hand")
public class HandPayController {
	private static Logger logger = Logger.getLogger(HandPayController.class);
	@Autowired
	private HandPayService handPayService;

	@RequestMapping(value = "/outpatientFee", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String outpatientFee(HttpServletRequest request) {
		return handPayService.outpatientFee(request);
	}

	/**
	 * 获得待支付列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getPayList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getPayList(HttpServletRequest request) {
		Date beginTime = new Date();
		String methodName = "getPayList";
		// 安全校验
		Map<String, Object> requestMap = handPayService.requestCheck(beginTime, methodName, request);
		if (!"200".equals(RequestDataUtil.getValueForKey(requestMap, "code2"))) {
			return FastJsonUtil.toJSONString(requestMap);
		}
		requestMap.remove("code2");

		return handPayService.queryPayList(requestMap, methodName, beginTime);
	}

	@RequestMapping(value = "/getPaidList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getPaidList(HttpServletRequest request) {
		Date beginTime = new Date();
		String methodName = "getPaidList";
		// 安全校验
		Map<String, Object> requestMap = handPayService.requestCheck(beginTime, methodName, request);
		if (!"200".equals(RequestDataUtil.getValueForKey(requestMap, "code2"))) {
			return FastJsonUtil.toJSONString(requestMap);
		}
		requestMap.remove("code2");

		return handPayService.invokeCommonService(methodName, AppCommonConstant.SYSTEM_APP_USP_GETPAIDLIST,
				AppCommonConstant.SYSTEM_HAND_PROCEDURE_NAME, CommonConstant.SYSTEM_PROCEDURE_SQLKEY, requestMap,
				beginTime);
	}

	@RequestMapping(value = "/getFeeList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getFeeList(HttpServletRequest request) {
		Date beginTime = new Date();
		String methodName = "getFeeList";
		// 安全校验
		Map<String, Object> requestMap = handPayService.requestCheck(beginTime, methodName, request);
		if (!"200".equals(RequestDataUtil.getValueForKey(requestMap, "code2"))) {
			return FastJsonUtil.toJSONString(requestMap);
		}
		requestMap.remove("code2");

		return handPayService.invokeCommonService(methodName, AppCommonConstant.SYSTEM_APP_USP_GETFEELIST,
				AppCommonConstant.SYSTEM_HAND_PROCEDURE_NAME, CommonConstant.SYSTEM_PROCEDURE_SQLKEY, requestMap,
				beginTime);
	}

	@RequestMapping(value = "/saveBillChargePre", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveBillChargePre(HttpServletRequest request) {
		Date beginTime = new Date();
		String methodName = "saveBillChargePre";
		// 安全校验
		Map<String, Object> requestMap = handPayService.requestCheck(beginTime, methodName, request);
		if (!"200".equals(RequestDataUtil.getValueForKey(requestMap, "code2"))) {
			return FastJsonUtil.toJSONString(requestMap);
		}
		requestMap.remove("code2");

		return handPayService.invokeCommonService(methodName, null,
				AppCommonConstant.SYSTEM_HAND_PROCEDURE_NAME_SAVE_BILLCHARGE_PRE, "callpro.saveBill", requestMap,
				beginTime);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveBillCharge", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveBillCharge(HttpServletRequest request) {
		Date beginTime = new Date();
		String methodName = "saveBillCharge";
		// 安全校验
		try {
			Map<String, Object> requestMap = handPayService.requestCheck(beginTime, methodName, request);
			if (!"200".equals(RequestDataUtil.getValueForKey(requestMap, "code2"))) {
				return FastJsonUtil.toJSONString(requestMap);
			}
			requestMap.remove("code2");

			String source = RequestDataUtil.getValueForKey(requestMap, "source");

			String billResult = handPayService.invokeCommonService(methodName, null,
					AppCommonConstant.SYSTEM_HAND_PROCEDURE_NAME_SAVE_BILLCHARGE, "callpro.saveBill", requestMap,
					beginTime);

			Map<String, Object> billResultMap = FastJsonUtil.toJSONObject(billResult, Map.class);

			if (billResultMap == null) {
				logger.error("确认费用结算处理返回json异常");
				return ResultMessageUtil.getSpecialServiceFail(null, "返回json异常");
			}

			if (!"200".equals(RequestDataUtil.getValueForKey(billResultMap, "code"))) {
				logger.error("预结算失败,失败原因" + billResult);
				// 预结算失败直接返回
				return billResult;
			}

			// 如果预结算成功
			boolean isSyscSucc = handPayService.propelPayInfo(source, billResultMap, requestMap);

			int counter = 1;

			while (counter < CommonConstant.NOTIFICATION_12580_TIMES) {
				Log.info("确认费用结算同步信息次数:" + counter);
				if (!isSyscSucc) {
					counter++;
					isSyscSucc = handPayService.propelPayInfo(source, billResultMap, requestMap);
				}
				break;
			}

			if (isSyscSucc) {
				return billResult;
			}
			logger.error("消息最终推送失败推送失败");
			return ResultMessageUtil.getSpecialServiceFail(null, "消息推送失败");
		} catch (Exception e) {
			if (e instanceof HandPayException) {
				logger.error("发生异常=>" + e.getMessage());
				HandPayException handPayException = (HandPayException) e;
				return ResultMessageUtil.getSpecialServiceFail(null, handPayException.getMessage());
			} else {
				throw e;
			}
		}
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public String exceptionHander(Exception ex, HttpServletRequest request) {
		System.out.println("hello,git");
		logger.error("其它异常=>" + ex.getMessage());
		return ResultMessageUtil.getSpecialServiceFail(null, "其它错误");
	}
}
