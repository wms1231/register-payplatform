package com.bsoft.register.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bsoft.exception.MessageException;
import com.bsoft.exception.PayException;
import com.bsoft.register.service.PayService;
import com.bsoft.tools.RequestDataUtil;

@Controller
@RequestMapping(value = "/pay", produces = "text/html;charset=UTF-8")
public class PayController {

	private static Logger logger = Logger.getLogger(PayController.class);

	@Autowired
	private PayService payService;

	/**
	 * 刷卡支付
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("trade_pay")
	@ResponseBody
	public String trade_pay(HttpServletRequest request, HttpServletResponse response) {
		return payService.trade_pay(request, response);
	}

	/**
	 * 查询订单状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "orderquery", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String orderquery(HttpServletRequest request, HttpServletResponse response) {
		return payService.orderquery(request, response);
	}

	/**
	 * 退款接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "refund", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String refund(HttpServletRequest request, HttpServletResponse response) {
		return payService.refund(request, response);

	}

	/**
	 * 退款成功后短信通知
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "refundmessage", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String refundmessage(HttpServletRequest request, HttpServletResponse response) {
		payService.refundmessage(request, response);
		return "{\"code\":\"0\",\"msg\":\"success\"}";
	}

	/**
	 * 12580 预约成功后 获取URL并发送短息通知,并支持重复发送短信 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "sendmessage", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String sendmessage(HttpServletRequest request, HttpServletResponse response) {
		String orderType = RequestDataUtil.getRequestParmByParameter(request, "orderType");
		if (StringUtils.isBlank(orderType)) {
			orderType = "1";// 默认设置为膏方预约
		}
		return payService.sendmessage(request, response, orderType);
	}

	/**
	 * 支付宝回调地址, 注意:支付平台会异步通知预约平台,回调是不需要反参的
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "callback")
	public String callback(HttpServletRequest request, HttpServletResponse response) {
		payService.callback(request, response, 1);
		return "{\"code\":\"0\",\"msg\":\"success\"}";
	}

	/**
	 * 支付宝膏方回调地址, 注意:支付平台会异步通知预约平台,回调是不需要反参的
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "callCreamBack")
	public String callCreamBack(HttpServletRequest request, HttpServletResponse response) {
		payService.callCreamBack(request, response);
		return "{\"code\":\"0\",\"msg\":\"success\"}";

	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public String exceptionHander(Exception ex, HttpServletRequest request) {
		JSONObject jObj = new JSONObject();
		if (ex instanceof PayException) {
			PayException e = (PayException) ex;
			jObj.put("code", -1);
			jObj.put("msg", e.getMessage());
			return jObj.toJSONString();
		}

		if (ex instanceof MessageException) {
			MessageException e = (MessageException) ex;
			jObj.put("code", -1);
			jObj.put("msg", e.getMessage());
			return jObj.toJSONString();
		}

		jObj.put("code", -1);
		jObj.put("msg", ex.getMessage());
		return jObj.toJSONString();
	}
}
