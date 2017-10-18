package com.bsoft.factory;

import java.util.Date;

import com.bsoft.constant.CommonConstant;
import com.bsoft.domain.DeptBean;
import com.bsoft.domain.DoctorBean;
import com.bsoft.domain.DoctorInfoquery;
import com.bsoft.domain.HeadBean;
import com.bsoft.domain.NotificationBean;
import com.bsoft.domain.OrderCancel;
import com.bsoft.domain.OrderRecordBean;
import com.bsoft.domain.OrderSourceBean;
import com.bsoft.domain.PatientBean;
import com.bsoft.domain.PatientIndexBean;
import com.bsoft.domain.RefundNotification;
import com.bsoft.domain.RegOrderBean;
import com.bsoft.domain.RegRecordBean;
import com.bsoft.tools.RequestDataUtil;

public class XmlServiceDirectorClient {

	private XmlServiceBuilder buider;

	public XmlServiceDirectorClient(XmlServiceBuilder buider) {
		super();
		this.buider = buider;
	}

	

	public void constructPatientIndexBean( String patCardNo) {
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_PATIENTINDEX);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_PATIENTINDEX_DESC);
		constuctCommonHead(headBean);

		PatientIndexBean patientIndexBean = new PatientIndexBean();
		patientIndexBean.setPatCardNo(patCardNo);
		buider.buildRequestHeader(headBean);
		buider.buildRequestData(patientIndexBean, CommonConstant.SERVICE_CODE_PATIENTINDEX);
	}

	/**
	 * 获取挂号科室
	 * @param deptCode
	 * @param parentdeptCode
	 */
	public void constructDeptInfoBean(String deptCode, String parentdeptCode) {
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_DEPTINFO);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_DEPTINFO_DESC);
		constuctCommonHead(headBean);
		DeptBean deptBean = new DeptBean();
		deptBean.setDeptCode(deptCode);
		deptBean.setParentdeptCode(parentdeptCode);
		buider.buildRequestHeader(headBean);
		buider.buildRequestData(deptBean, CommonConstant.SERVICE_CODE_DEPTINFO);
	}
	/**
	 * 获取大科室
	 * @param deptCode
	 * @param parentdeptCode
	 */
	public void constructDeptInfo() {
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_DEPT);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_DEPT_DESC);

		constuctCommonHead(headBean);

		buider.buildRequestHeader(headBean);
		buider.buildRequestData(null, null);
	}
	
	private void constuctCommonHead(HeadBean headBean) {
		headBean.setClientRunningNum(new Date().getTime() + "");
		headBean.setPartnerID(CommonConstant.SERVICE_PARTNER_ID);
		headBean.setUserID(CommonConstant.SERVICE_USER_ID);

		headBean.setTimeStamp(new Date() + "");
		headBean.setServicePassword(CommonConstant.SERVICE_SERVICE_PASSWORD);
		headBean.setServerRunningNum(new Date().getTime() + "");
		
		headBean.setResultCode("");
		headBean.setResultMessage("");
	}

	
	public void constructDoctorInfoBean(String parentdeptCode, String deptCode, String doctorCode) {
		
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_DOCTORINFO);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_DOCTORINFO_DESC);
		
		constuctCommonHead(headBean);

		DoctorBean doctorBean = new DoctorBean();
		doctorBean.setDeptCode(deptCode);
		doctorBean.setParentdeptCode(parentdeptCode);
		doctorBean.setDoctorCode(doctorCode);

		buider.buildRequestHeader(headBean);
		buider.buildRequestData(doctorBean, CommonConstant.SERVICE_CODE_DOCTORINFO);
	}

	
	public void constructDoctorInfoBean2(String doctorName) {
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_DOCTOR);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_DOCTOR_DESC);
		
		constuctCommonHead(headBean);

		DoctorInfoquery doctorInfoquery = new DoctorInfoquery();
		doctorInfoquery.setDoctorName(doctorName);

		buider.buildRequestHeader(headBean);
		buider.buildRequestData(doctorInfoquery, CommonConstant.SERVICE_CODE_DOCTOR);
	}
	
	public void constructOrderSourceBean(String beginDate, String endDate, String deptCode, String doctorCode) {
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_ORDERSOURCE);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_ORDERSOURCE_DESC);
		
		constuctCommonHead(headBean);

		OrderSourceBean orderSourceBean = new OrderSourceBean();
		orderSourceBean.setBeginDate(beginDate);
		orderSourceBean.setEndDate(endDate);
		orderSourceBean.setDeptCode(deptCode);
		orderSourceBean.setDoctorCode(doctorCode);

		buider.buildRequestHeader(headBean);
		buider.buildRequestData(orderSourceBean, CommonConstant.SERVICE_CODE_ORDERSOURCE);
	}

	
	public void constructOrderSourceBeannew(String beginDate, String endDate, String parentDeptCode, String doctorCode) {
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_ORDERSOURCE_NEW);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_ORDERSOURCE_NEW_DESC);
		
		constuctCommonHead(headBean);

		OrderSourceBean orderSourceBean = new OrderSourceBean();
		orderSourceBean.setBeginDate(beginDate);
		orderSourceBean.setEndDate(endDate);
		orderSourceBean.setParentDeptCode(parentDeptCode);
		orderSourceBean.setDoctorCode(doctorCode);

		buider.buildRequestHeader(headBean);
		buider.buildRequestData(orderSourceBean, CommonConstant.SERVICE_CODE_ORDERSOURCE);
	}
	
	public void constructRegRecordBean(String patIndex, String hisOrdNum) {
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_REGRECORD);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_REGRECORD_DESC);
		
		constuctCommonHead(headBean);

		RegRecordBean orderSourceBean = new RegRecordBean();
		orderSourceBean.setPatIndex(patIndex);
		orderSourceBean.setHisOrdNum(hisOrdNum);

		buider.buildRequestHeader(headBean);
		buider.buildRequestData(orderSourceBean, CommonConstant.SERVICE_CODE_REGRECORD);
	}

	
	public void constructRegOrderBean(String hisOrdNum, String psOrdNum, String patIndex, String phonenum,String regChannel) {
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_REGORDER);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_REGORDER_DESC);
	
		constuctCommonHead(headBean);

		RegOrderBean regOrderBean = new RegOrderBean();
		regOrderBean.setHisOrdNum(hisOrdNum);
		regOrderBean.setPsOrdNum(psOrdNum);
		regOrderBean.setPatIndex(patIndex);
		regOrderBean.setPhonenum(phonenum);
		regOrderBean.setRegChannel(regChannel);

		buider.buildRequestHeader(headBean);
		buider.buildRequestData(regOrderBean, CommonConstant.SERVICE_CODE_REGORDER);
	}

	public void constructOrderCancelBean(String hisOrdNum, String psOrdNum, String patIndex, String srialNum,
			String cancelReason) {
		
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_REGORDER_CANCEL);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_REGORDER_CANCEL_DESC);
		
		constuctCommonHead(headBean);

		OrderCancel orderCancel = new OrderCancel();
		orderCancel.setHisOrdNum(hisOrdNum);
		orderCancel.setPsOrdNum(psOrdNum);
		orderCancel.setPatIndex(patIndex);
		orderCancel.setSrialNum(srialNum);
		orderCancel.setCancelReason(cancelReason);
		
		buider.buildRequestHeader(headBean);
		buider.buildRequestData(orderCancel, CommonConstant.SERVICE_CODE_REGORDER_CANCEL);
	}

	public void constructOrderRecordBean(String scheduleDate, String parentdeptCode, String deptCode, String doctorCode,
			String patIndex) {
		
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_GET_REGORDER);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_GET_REGORDERL_DESC);
		
		constuctCommonHead(headBean);

		OrderRecordBean orderRecordBean = new OrderRecordBean();
		orderRecordBean.setDeptCode(deptCode);
		orderRecordBean.setDoctorCode(doctorCode);
		orderRecordBean.setParentdeptCode(parentdeptCode);
		orderRecordBean.setPatIndex(patIndex);
		orderRecordBean.setScheduleDate(scheduleDate);

		buider.buildRequestHeader(headBean);
		buider.buildRequestData(orderRecordBean, CommonConstant.SERVICE_CODE_GET_REGORDER);
	}
	
	/**
	 * 创建病人主索引
	 * @param scheduleDate
	 * @param parentdeptCode
	 * @param deptCode
	 * @param doctorCode
	 * @param patIndex
	 */
	public void constructPatientBean(String patIDType, String patID, String patName, String patGender,
			String patTel,String patAddress,String patAge) {
		
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_CREATE_PATIENT);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_CREATE_PATIENT_DESC);
		
		constuctCommonHead(headBean);

		PatientBean patientBean = new PatientBean();
		patientBean.setPatAddress(patAddress);
		patientBean.setPatAge(patAge);
		patientBean.setPatGender(patGender);
		
		patientBean.setPatID(patID);
		patientBean.setPatIDType(patIDType);
		patientBean.setPatName(patName);
		patientBean.setPatTel(patTel);
		
		
		buider.buildRequestHeader(headBean);
		buider.buildRequestData(patientBean, CommonConstant.SERVICE_CODE_CREATE_PATIENT);
	}
	

	public void constructNotificationBean(String hisOrdNum, String psOrdNum, String payMode, String payAmt,
			String agtOrdNum,String payTime,String payFlag) {
		
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_NOTIFICATION);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_NOTIFICATION_DESC);
		
		constuctCommonHead(headBean);

		NotificationBean notificationBean = new NotificationBean();
		notificationBean.setAgtOrdNum(agtOrdNum);
		notificationBean.setHisOrdNum(hisOrdNum);
		notificationBean.setPayAmt(payAmt);
		notificationBean.setPayMode(payMode);
		notificationBean.setPayTime(payTime);
		notificationBean.setPsOrdNum(psOrdNum);
		notificationBean.setPayFlag(payFlag);
				
		buider.buildRequestHeader(headBean);
		buider.buildRequestData(notificationBean, CommonConstant.SERVICE_CODE_NOTIFICATION);
	}
	
	public void constructrefundNotificationBean(String hisOrdNum, String payAmt,String payTime) {
		
		HeadBean headBean = new HeadBean();
		headBean.setServiceCode(CommonConstant.SERVICE_CODE_REFUNDNOTIFICATION);
		headBean.setServiceDesc(CommonConstant.SERVICE_CODE_REFUNDNOTIFICATION_DESC);
		
		constuctCommonHead(headBean);

		RefundNotification notificationBean = new RefundNotification();
		notificationBean.setHisOrdNum(hisOrdNum);
		notificationBean.setPayAmt(payAmt);
		notificationBean.setPayTime(payTime);
				
		buider.buildRequestHeader(headBean);
		buider.buildRequestData(notificationBean, CommonConstant.SERVICE_CODE_REFUNDNOTIFICATION);
	}
}
