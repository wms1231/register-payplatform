package com.bsoft.register.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.register.service.PayService;
import com.bsoft.tools.RequestDataUtil;

@Controller
@RequestMapping(value = "/pay", produces = "text/html;charset=UTF-8")
public class PayController {

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
		return payService.refundmessage(request, response);
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
		return payService.callback(request, response);
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
		return payService.callCreamBack(request, response);
	}
}
