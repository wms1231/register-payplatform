package com.bsoft.register.service;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface KdService {

	/**
	 * 调用诊间支付存储过程
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param param
	 * @return
	 */
	public Map<String, Object> callHandPayPro(String sqlKey, String dataSource, Map<String, Object> param);

	

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
	 * 安全监测
	 * 
	 * @param beginTime
	 * @param methodName
	 * @param request
	 * @return
	 */
	public Map<String, Object> requestCheck(Date beginTime, String methodName, HttpServletRequest request);

	/**
	 * 调用公共的服务
	 * 
	 * @param methodName
	 * @param proNum
	 * @param proName
	 * @param sqlKey
	 * @return
	 */
	public String invokeCommonService(String methodName, String proNum, String proName, String sqlKey,
			Map<String, Object> requestMap, Date beginTime);

}
