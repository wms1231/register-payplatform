package com.bsoft.register.service;

import org.dom4j.DocumentException;

/**
 * 提供检查报告查询
 * 
 * @author xy
 *
 */
public interface CheckReportService {

	public String checkReportQuery(String responseXml, Long beginTime);

	/**
	 * 调用存储过程，返回xml数据
	 * 
	 * @param beginTime
	 * @param requestStr
	 * @return
	 * @throws DocumentException
	 */
	public String invokeProcedure(long beginTime, String requestStr, String proName);

}
