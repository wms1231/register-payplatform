package com.bsoft.register.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 提供预约挂号相关服务
 * 
 * @author wms1231
 *
 */
public interface RegisterService {
	public String createPatientInfo(String patIDType, String patID, String patName, String patGender, String patTel,
			String patAge);

	public String getDepInfoWeb(String parentdeptCode, String deptCode);

	public String getDepInfo(HttpServletRequest request, HttpServletResponse response);

	public String getHisOrderInfo(String deptCode, String parentDeptCode, String doctorCode);
	
	/**
	 * 获取膏方排班信息
	 * 
	 * @param deptCode
	 * @param parentDeptCode
	 * @param doctorCode
	 * @return
	 */
	public String getScriptOrderInfo(String deptCode, String parentDeptCode, String doctorCode);
	

	/**
	 * 12580科室搜索
	 * 
	 * @param doctorName
	 * @return
	 */
	public String getDocInfo(String doctorName);
	
	
	
	/**
	 * 膏方科室搜索
	 * 
	 * @param doctorName
	 * @return
	 */
	public String getCreamDeptSearch(String doctorName);
	

	public String hisRegister(String hisOrdNum, String phonenum, String patIndex, String regChannel);

	public String querypatientinfo(String cardtype, String cardNo);

	public String cancelhisRegister(String hisOrdNum, String patIndex, String serialNum, String cancelReason);

	/**
	 * 订单查询
	 * 
	 * @param scheduleDate
	 * @param parentdeptCode
	 * @param deptCode
	 * @param doctorCode
	 * @param patIndex
	 * @return
	 */
	public String orderRecord(String scheduleDate, String parentdeptCode, String deptCode, String doctorCode,
			String patIndex);

	
	public String notification(String hisOrdNum, String payMode, String payAmt, String payFlag);

	/**
	 * 退款通知
	 * 
	 * @param hisOrdNum
	 * @param payAmt
	 * @param refunder
	 * @param cancelTime
	 * @return
	 */
	public String refundnotification(String hisOrdNum, String payAmt,String refunder,String cancelTime);

	/**
	 * 获得服务器时间
	 * 
	 * @return
	 */
	public String getServertime();
}
