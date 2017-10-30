package com.bsoft.register.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PayService {
	/**
	 * 刷卡支付
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String trade_pay(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 查询订单状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String orderquery(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 退款接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String refund(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 退款成功后短信通知
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String refundmessage(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 12580 预约成功后 获取URL并发送短息通知,并支持重复发送短信 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String sendmessage(HttpServletRequest request, HttpServletResponse response,String callBackUrl);
	
	

	/**
	 * 支付宝回调地址, 注意:支付平台会异步通知预约平台,回调是不需要反参的
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public void callback(HttpServletRequest request, HttpServletResponse response,int flag);
	
	public void callCreamBack(HttpServletRequest request, HttpServletResponse response);

}
