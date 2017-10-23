package com.bsoft.register.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bsoft.support.Pager;

/**
 * 查询12580预约记录
 * 
 * @author xy
 *
 */
public interface OrderRecordService {
	/**
	 * 下载预约报表导出Excel
	 * 
	 * @param request
	 * @param response
	 */
	public void download(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 预约记录查询
	 * 
	 * @param register
	 * @param beginTime
	 * @param endTime
	 * @param orderStatus
	 * @param pageNo
	 * @param pageSize
	 * @param orders
	 * @return
	 */
	public Pager orderRecordQuery(String register, String beginTime, String endTime, String orderStatus,
			Map<String, Object> param, Integer pageNo, Integer pageSize, String sqlKey,String[] orders);

	/**
	 * 有效值校验
	 * 
	 * @param orders
	 * @param beginTime
	 * @param endTime
	 * @param orderStatus
	 */
	public void valid(String[] orders, String beginTime, String endTime, String orderStatus);
}
