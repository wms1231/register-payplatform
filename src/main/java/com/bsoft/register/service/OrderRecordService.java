package com.bsoft.register.service;

import java.util.List;
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
	 * 下载预约报表导出Excel,测试
	 * 
	 * @param request
	 * @param response
	 */
	public void download(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 下载指定列报表导出Excel
	 * 
	 * @param response
	 * @param register
	 * @param beginTime
	 * @param endTime
	 * @param orderStatus
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @param sqlKey
	 * @param orders
	 */
	public void download(HttpServletResponse response, String register, String beginTime, String endTime,
			String orderStatus, Map<String, Object> param, String sqlKey, String[] orders);

	/**
	 * 预约记录分页查询
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
	public Pager orderRecordQueryWithPage(String register, String beginTime, String endTime, String orderStatus,
			Map<String, Object> param, Integer pageNo, Integer pageSize, String sqlKey, String[] orders);

	/**
	 * 预约记录查询
	 * 
	 * @param register
	 * @param beginTime
	 * @param endTime
	 * @param orderStatus
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @param sqlKey
	 * @param orders
	 * @return
	 */
	public List<Map<String, Object>> orderRecordQuery(String register, String beginTime, String endTime,
			String orderStatus, Map<String, Object> param, String sqlKey, String[] orders);

	/**
	 * 有效值校验[日后可以利用框架取代有效值手工编码校验]
	 * 
	 * @param register
	 * @param orders
	 * @param beginTime
	 * @param endTime
	 * @param orderStatus
	 */
	public void valid(String register, String[] orders, String beginTime, String endTime, String orderStatus);

	public void  orderExport(List<Map<String, Object>> orderRecordQueryList,List<String> headList,HttpServletResponse response);
}
