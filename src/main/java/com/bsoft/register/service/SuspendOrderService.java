package com.bsoft.register.service;

import java.util.List;
import java.util.Map;

import com.bsoft.support.Pager;

public interface SuspendOrderService {

	/**
	 * 排版科室查询
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param param
	 * @param orders
	 * @return
	 */
	List<Map<String, Object>> departQuery(String sqlKey, String dataSource, Object param, String[] orders);

	/**
	 * 排班医生查询
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param param
	 * @param orders
	 * @return
	 */
	List<Map<String, Object>> doctorsQuery(String sqlKey, String dataSource, Object param, String[] orders);

	/**
	 * 停诊订单查询
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @param orders
	 * @return
	 */
	Pager suspendOrderQuery(String sqlKey, String dataSource, Map<String, Object> param, int pageNo, int pageSize,
			String[] orders);
	
	List<Map<String, Object>> deptMutiQuery(String sqlKey, String dataSource, Object param, String[] orders);

}
