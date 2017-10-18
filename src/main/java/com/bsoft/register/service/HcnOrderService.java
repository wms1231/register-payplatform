package com.bsoft.register.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HcnOrderService {

	public Map<String, Object> callOrderPro(String sqlKey, String dataSource, Map<String, Object> param);

	/**
	 * 调用存储过程
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param requestMap
	 * @param param
	 * @return
	 */
	public String invokeProc(String sqlKey, String dataSource, Map<String, Object> requestMap,
			Map<String, Object> param);

	/**
	 * 请求验证
	 * 
	 * @param beginTime
	 * @param methodName
	 * @param request
	 * @return
	 */
	public Map<String, Object> requestCheck(Date beginTime, String methodName, HttpServletRequest request);

	/**
	 * 取消支付
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param param
	 * @return
	 */
	public Map<String, Object> cancelPay(String sqlKey, String dataSource, Map<String, Object> param);

	/**
	 * App退款服务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String refund(HttpServletRequest request, HttpServletResponse response, String organizationCode,
			String computerName, String ip);

	/**
	 * 推送数据到支付平台，包含app、官网、微信公众号
	 * 
	 * @param requestMap
	 * @param timestampValue
	 */
	public String paySync(Map<String, Object> returnPayMap, String organizationCode, String computerName, String ip);

	/**
	 * 发送短信
	 * 
	 * @param returnPayMap
	 */
	public String sendMessage(Map<String, Object> messageMap);

}
