package com.bsoft.register.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsoft.domain.DepartmentReturnBean;
import com.bsoft.domain.DeptReturnBean;
import com.bsoft.domain.DoctorReturnBean;
import com.bsoft.domain.NotificationReturnBean;
import com.bsoft.domain.OrderRecordReturnBean;
import com.bsoft.domain.PatientIndexReturnBean;
import com.bsoft.domain.PatientReturnBean;
import com.bsoft.domain.RegOrderReturnBean;
import com.bsoft.domain.RegSourceResponse;

/**
 * 提供预约挂号相关服务
 * 
 * @author wms1231
 *
 */
public interface RegisterService {
	public List<PatientReturnBean> createPatientInfo(String patIDType, String patID, String patName, String patGender, String patTel,
			String patAge);

	public Map<String, List<DeptReturnBean>> getDepInfoWeb(String parentdeptCode, String deptCode);

	public Map<String, List<DepartmentReturnBean>> getDepInfo(HttpServletRequest request, HttpServletResponse response);

	public List<RegSourceResponse> getHisOrderInfo(String deptCode, String parentDeptCode, String doctorCode);
	
	/**
	 * 获取膏方排班信息
	 * 
	 * @param deptCode
	 * @param parentDeptCode
	 * @param doctorCode
	 * @return
	 */
	public List<RegSourceResponse> getScriptOrderInfo(String deptCode, String parentDeptCode, String doctorCode);
	

	/**
	 * 12580科室搜索
	 * 
	 * @param doctorName
	 * @return
	 */
	public List<DoctorReturnBean> getDocInfo(String doctorName);
	
	
	
	/**
	 * 膏方科室搜索
	 * 
	 * @param doctorName
	 * @return
	 */
	public List<DoctorReturnBean> getCreamDeptSearch(String doctorName);
	

	public List<RegOrderReturnBean> hisRegister(String hisOrdNum, String phonenum, String patIndex, String regChannel);

	public PatientIndexReturnBean querypatientinfo(String cardtype, String cardNo);

	public List<RegOrderReturnBean> cancelhisRegister(String hisOrdNum, String patIndex, String serialNum, String cancelReason);

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
	public List<OrderRecordReturnBean> orderRecord(String scheduleDate, String parentdeptCode, String deptCode, String doctorCode,
			String patIndex);

	
	public List<NotificationReturnBean> notification(String hisOrdNum, String payMode, String payAmt, String payFlag);

	/**
	 * 退款通知
	 * 
	 * @param hisOrdNum
	 * @param payAmt
	 * @param refunder
	 * @param cancelTime
	 * @return
	 */
	public List<NotificationReturnBean> refundnotification(String hisOrdNum, String payAmt,String refunder,String cancelTime);

	/**
	 * 获得服务器时间
	 * 
	 * @return
	 */
	public String getServertime();
}
