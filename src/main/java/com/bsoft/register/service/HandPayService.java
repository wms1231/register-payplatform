package com.bsoft.register.service;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface HandPayService {

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
	 * 插入处方
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param requestMap
	 * @param param
	 * @return
	 */
	public String insertRecipe(String sqlKey, String dataSource, String requestStr, Map<String, Object> param);

	/**
	 * 结算和预结算
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param requestStr
	 * @param param
	 * @return
	 */
	public String saveBill(String sqlKey, String dataSource, String requestStr, Map<String, Object> param);

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

	/**
	 * 获取待支付列表
	 * 
	 * @param requestMap
	 * @param methodName
	 * @param beginTime
	 * @return
	 */
	public String queryPayList(Map<String, Object> requestMap, String methodName, Date beginTime);
	
	/**
	 * 查询渠道
	 * 
	 * @param requestMap
	 * @param methodName
	 * @param beginTime
	 * @return
	 */
	public String queryChannel(String sqlKey, String dataSource,String source);
	
	
	/**
	 * 推送信息
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param channelInfo
	 * @param billResult
	 * @param requestMap
	 * @return
	 */
	public boolean propelPayInfo(String source,Map<String, Object> billResultMap,Map<String, Object> requestMap);
	

	/**
	 * 调用远程服务【测试使用】
	 * 
	 * @param request
	 * @return
	 */
	public String outpatientFee(HttpServletRequest request);
	
}
