package com.bsoft.factory;

import org.apache.commons.lang3.StringUtils;

import com.bsoft.constant.CommonConst;
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

public class XmlServiceImplBuilder implements XmlServiceBuilder {
	private StringBuffer buffer = new StringBuffer();

	@Override
	public void buildRequestHeader(HeadBean headBean) {
		buffer.append("<?xml version='1.0' encoding='utf-8'?>\n");
		buffer.append("<BSHisService>\n");

		buffer.append("<Head>\n");

		if (StringUtils.isNotBlank(headBean.getServiceCode())) {
			buffer.append("  <serviceCode>" + headBean.getServiceCode() + "</serviceCode>\n");
		}
		if (StringUtils.isNotBlank(headBean.getServiceDesc())) {
			buffer.append("  <serviceDesc>" + headBean.getServiceDesc() + "</serviceDesc>\n");
		}
		if (StringUtils.isNotBlank(headBean.getClientRunningNum())) {
			buffer.append("  <clientRunningNum>" + headBean.getClientRunningNum() + "</clientRunningNum>\n");
		}

		if (StringUtils.isNotBlank(headBean.getPartnerID())) {
			buffer.append("  <partnerID>" + headBean.getPartnerID() + "</partnerID>\n");

		}
		if (StringUtils.isNotBlank(headBean.getClientType())) {
			buffer.append("  <clientType>" + headBean.getClientType() + "</clientType>\n");
		}
		if (StringUtils.isNotBlank(headBean.getTerminalCode())) {
			buffer.append("  <terminalCode>" + headBean.getTerminalCode() + "</terminalCode>\n");
		}

		if (StringUtils.isNotBlank(headBean.getUserID())) {
			buffer.append("  <userID>" + headBean.getUserID() + "</userID>\n");

		}
		if (StringUtils.isNotBlank(headBean.getTimeStamp())) {
			buffer.append("  <timeStamp>" + headBean.getTimeStamp() + "</timeStamp>\n");

		}
		if (StringUtils.isNotBlank(headBean.getServicePassword())) {
			buffer.append("  <servicePassword>" + headBean.getServicePassword() + "</servicePassword>\n");
		}

		if (StringUtils.isNotBlank(headBean.getServerRunningNum())) {
			buffer.append("  <serverRunningNum>" + headBean.getServerRunningNum() + "</serverRunningNum>\n");
		}

		if (StringUtils.isNotBlank(headBean.getResultCode())) {
			buffer.append("  <resultCode>" + headBean.getResultCode() + "</resultCode>\n");
		} else {
			buffer.append("  <resultCode>" + " " + "</resultCode>\n");
		}

		if (StringUtils.isNotBlank(headBean.getResultMessage())) {
			buffer.append("  <resultMessage>" + headBean.getResultMessage() + "</resultMessage>\n");
		} else {

			buffer.append("  <resultMessage>" + " " + "</resultMessage>\n");
		}

		buffer.append("</Head>\n");

	}

	@Override

	public void buildRequestData(Object dataBean, String serviceCode) {
		if (CommonConst.SERVICE_CODE_PATIENTINDEX.equals(serviceCode)) {
			buildPatientIndexRequestData(dataBean);
		} else if (CommonConst.SERVICE_CODE_DEPTINFO.equals(serviceCode)) {
			buildDeptInfoRequestData(dataBean);
		} else if (CommonConst.SERVICE_CODE_DOCTORINFO.equals(serviceCode)) {
			buildDoctorInfoRequestData(dataBean);
		} else if (CommonConst.SERVICE_CODE_ORDERSOURCE.equals(serviceCode)) {
			buildOrderSourceRequestData(dataBean);
		} else if (CommonConst.SERVICE_CODE_REGRECORD.equals(serviceCode)) {
			buildRegRecordRequestData(dataBean);
		} else if (CommonConst.SERVICE_CODE_REGORDER.equals(serviceCode)) {
			buildRegOrderRequestData(dataBean);
		} else if (CommonConst.SERVICE_CODE_REGORDER_CANCEL.equals(serviceCode)) {
			buildOrderCancelRequestData(dataBean);
		}else if (CommonConst.SERVICE_CODE_GET_REGORDER.equals(serviceCode)) {
			buildOrderRecordBeanData(dataBean);
		}else if (CommonConst.SERVICE_CODE_CREATE_PATIENT.equals(serviceCode)) {
			buildPatientBeanData(dataBean);
		}
		else if (CommonConst.SERVICE_CODE_NOTIFICATION.equals(serviceCode)) {
			buildNotificationBeanData(dataBean);
		}
		else if (CommonConst.SERVICE_CODE_REFUNDNOTIFICATION.equals(serviceCode)) {
			buildRefundNotificationBeanData(dataBean);
		}
		else if (CommonConst.SERVICE_CODE_DOCTOR.equals(serviceCode)) {
			buildDoctorInfoRequestData2(dataBean);
		}
		
		else
		{
			buildNullData(null);

		}
	}

	private void buildOrderCancelRequestData(Object dataBean) {

		if (dataBean instanceof OrderCancel) {
			OrderCancel orderCancel = (OrderCancel) dataBean;

			buffer.append("<Data>\n");
			if (StringUtils.isNotBlank(orderCancel.getHisOrdNum())) {
				buffer.append("  <hisOrdNum>" + orderCancel.getHisOrdNum() + "</hisOrdNum>\n");
			} else {
				buffer.append("  <hisOrdNum>" + "" + "</hisOrdNum>\n");
			}

			if (StringUtils.isNotBlank(orderCancel.getPsOrdNum())) {
				buffer.append("  <psOrdNum>" + orderCancel.getPsOrdNum() + "</psOrdNum>\n");
			} else {
				buffer.append("  <psOrdNum>" + "" + "</psOrdNum>\n");
			}

			if (StringUtils.isNotBlank(orderCancel.getPatIndex())) {
				buffer.append("  <patIndex>" + orderCancel.getPatIndex() + "</patIndex>\n");
			} else {
				buffer.append("  <patIndex>" + "" + "</patIndex>\n");
			}

			if (StringUtils.isNotBlank(orderCancel.getSrialNum())) {
				buffer.append("  <serialNum>" + orderCancel.getSrialNum() + "</serialNum>\n");
			} else {
				buffer.append("  <serialNum>" + "" + "</serialNum>\n");
			}

			if (StringUtils.isNotBlank(orderCancel.getCancelReason())) {
				buffer.append("  <cancelReason>" + orderCancel.getCancelReason() + "</cancelReason>\n");
			} else {
				buffer.append("  <cancelReason>" + "" + "</cancelReason>\n");
			}

			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}

	}

	private void buildRegOrderRequestData(Object dataBean) {

		if (dataBean instanceof RegOrderBean) {
			RegOrderBean regOrderBean = (RegOrderBean) dataBean;

			buffer.append("<Data>\n");
			if (StringUtils.isNotBlank(regOrderBean.getHisOrdNum())) {
				buffer.append("  <hisOrdNum>" + regOrderBean.getHisOrdNum() + "</hisOrdNum>\n");
			} else {
				buffer.append("  <hisOrdNum>" + "" + "</hisOrdNum>\n");
			}

			if (StringUtils.isNotBlank(regOrderBean.getPsOrdNum())) {
				buffer.append("  <psOrdNum>" + regOrderBean.getPsOrdNum() + "</psOrdNum>\n");
			} else {
				buffer.append("  <psOrdNum>" + "" + "</psOrdNum>\n");
			}

			if (StringUtils.isNotBlank(regOrderBean.getPatIndex())) {
				buffer.append("  <patIndex>" + regOrderBean.getPatIndex() + "</patIndex>\n");
			} else {
				buffer.append("  <patIndex>" + "" + "</patIndex>\n");
			}
			if (StringUtils.isNotBlank(regOrderBean.getPhonenum())) {
				buffer.append("  <phonenum>" + regOrderBean.getPhonenum() + "</phonenum>\n");
			} else {
				buffer.append("  <phonenum>" + "" + "</phonenum>\n");
			}
			if (StringUtils.isNotBlank(regOrderBean.getRegChannel())) {
				buffer.append("  <regChannel>" + regOrderBean.getRegChannel() + "</regChannel>\n");
			} else {
				buffer.append("  <regChannel>" + "" + "</regChannel>\n");
			}
			
			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}

	}

	private void buildRegRecordRequestData(Object dataBean) {

		if (dataBean instanceof RegRecordBean) {
			RegRecordBean regRecordBean = (RegRecordBean) dataBean;

			buffer.append("<Data>\n");
			if (StringUtils.isNotBlank(regRecordBean.getPatIndex())) {
				buffer.append("  <patIndex>" + regRecordBean.getPatIndex() + "</patIndex>\n");
			} else {
				buffer.append("  <patIndex>" + "" + "</patIndex>\n");
			}

			if (StringUtils.isNotBlank(regRecordBean.getHisOrdNum())) {
				buffer.append("  <hisOrdNum>" + regRecordBean.getHisOrdNum() + "</hisOrdNum>\n");
			} else {
				buffer.append("  <hisOrdNum>" + "" + "</hisOrdNum>\n");
			}

			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}

	}

	private void buildOrderSourceRequestData(Object dataBean) {

		if (dataBean instanceof OrderSourceBean) {
			OrderSourceBean doctorBean = (OrderSourceBean) dataBean;

			buffer.append("<Data>\n");
			if (StringUtils.isNotBlank(doctorBean.getBeginDate())) {
				buffer.append("  <beginDate>" + doctorBean.getBeginDate() + "</beginDate>\n");
			} else {
				buffer.append("  <beginDate>" + "" + "</beginDate>\n");
			}

			if (StringUtils.isNotBlank(doctorBean.getEndDate())) {
				buffer.append("  <endDate>" + doctorBean.getEndDate() + "</endDate>\n");
			} else {
				buffer.append("  <endDate>" + "" + "</endDate>\n");
			}

			if (StringUtils.isNotBlank(doctorBean.getDeptCode())) {
				buffer.append("  <deptCode>" + doctorBean.getDeptCode() + "</deptCode>\n");
			} else {
				buffer.append("  <deptCode>" + "" + "</deptCode>\n");
			}
			if (StringUtils.isNotBlank(doctorBean.getParentDeptCode())) {
				buffer.append("  <parentDeptCode>" + doctorBean.getParentDeptCode() + "</parentDeptCode>\n");
			} else {
				buffer.append("  <parentDeptCode>" + "" + "</parentDeptCode>\n");
			}
			if (StringUtils.isNotBlank(doctorBean.getDoctorCode())) {
				buffer.append("  <doctorCode>" + doctorBean.getDoctorCode() + "</doctorCode>\n");
			} else {
				buffer.append("  <doctorCode>" + "" + "</doctorCode>\n");
			}

			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}

	}

	private void buildDoctorInfoRequestData(Object dataBean) {
		if (dataBean instanceof DoctorBean) {
			DoctorBean doctorBean = (DoctorBean) dataBean;

			buffer.append("<Data>\n");

			if (StringUtils.isNotBlank(doctorBean.getParentdeptCode())) {
				buffer.append("  <parentdeptCode>" + doctorBean.getParentdeptCode() + "</parentdeptCode>\n");
			} else {
				buffer.append("  <parentdeptCode>" + "" + "</parentdeptCode>\n");
			}

			if (StringUtils.isNotBlank(doctorBean.getDeptCode())) {
				buffer.append("  <deptCode>" + doctorBean.getDeptCode() + "</deptCode>\n");
			} else {
				buffer.append("  <deptCode>" + "" + "</deptCode>\n");
			}

			if (StringUtils.isNotBlank(doctorBean.getDoctorCode())) {
				buffer.append("  <doctorCode>" + doctorBean.getDoctorCode() + "</doctorCode>\n");
			} else {
				buffer.append("  <doctorCode>" + "" + "</doctorCode>\n");
			}

			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}

	}
	private void buildDoctorInfoRequestData2(Object dataBean) {
		if (dataBean instanceof DoctorInfoquery) {
			DoctorInfoquery doctorInfoquery = (DoctorInfoquery) dataBean;

			buffer.append("<Data>\n");

			if (StringUtils.isNotBlank(doctorInfoquery.getDoctorName())) {
				buffer.append("  <doctorName>" + doctorInfoquery.getDoctorName() + "</doctorName>\n");
			} else {
				buffer.append("  <doctorName>" + "" + "</doctorName>\n");
			}
			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}

	}
	private void buildDeptInfoRequestData(Object dataBean) {
		if (dataBean instanceof DeptBean) {
			DeptBean deptBean = (DeptBean) dataBean;

			buffer.append("<Data>\n");

			if (StringUtils.isNotBlank(deptBean.getDeptCode())) {
				buffer.append("  <deptCode>" + deptBean.getDeptCode() + "</deptCode>\n");
			} else {
				buffer.append("  <deptCode>" + "" + "</deptCode>\n");
			}

			if (StringUtils.isNotBlank(deptBean.getParentdeptCode())) {
				buffer.append("  <parentdeptCode>" + deptBean.getParentdeptCode() + "</parentdeptCode>\n");
			} else {
				buffer.append("  <parentdeptCode>" + "" + "</parentdeptCode>\n");
			}

			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}

	}

	private void buildPatientIndexRequestData(Object dataBean) {
		if (dataBean instanceof PatientIndexBean) {
			PatientIndexBean patientIndexBean = (PatientIndexBean) dataBean;

			buffer.append("<Data>\n");
			
			if (StringUtils.isNotBlank(patientIndexBean.getPatCardNo())) {
				buffer.append("  <patCardNo>" + patientIndexBean.getPatCardNo() + "</patCardNo>\n");
			} else {
				buffer.append("  <patCardNo>" + " " + "</patCardNo>\n");
			}

			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}
	}

	private void buildOrderRecordBeanData(Object dataBean) {
		if (dataBean instanceof OrderRecordBean) {
			OrderRecordBean orderRecordBean = (OrderRecordBean) dataBean;

			buffer.append("<Data>\n");
			if (StringUtils.isNotBlank(orderRecordBean.getDeptCode())) {
				buffer.append("  <scheduleDate>" + orderRecordBean.getDeptCode() + "</scheduleDate>\n");
			} else {
				buffer.append("  <scheduleDate>" + " " + "</scheduleDate>\n");
			}
			if (StringUtils.isNotBlank(orderRecordBean.getDoctorCode())) {
				buffer.append("  <doctorCode>" + orderRecordBean.getDoctorCode() + "</doctorCode>\n");
			} else {
				buffer.append("  <doctorCode>" + " " + "</doctorCode>\n");
			}

			if (StringUtils.isNotBlank(orderRecordBean.getParentdeptCode())) {
				buffer.append("  <parentdeptCode>" + orderRecordBean.getParentdeptCode() + "</parentdeptCode>\n");
			} else {
				buffer.append("  <parentdeptCode>" + " " + "</parentdeptCode>\n");
			}
			
			if (StringUtils.isNotBlank(orderRecordBean.getPatIndex())) {
				buffer.append("  <patIndex>" + orderRecordBean.getPatIndex() + "</patIndex>\n");
			} else {
				buffer.append("  <patIndex>" + " " + "</patIndex>\n");
			}
			
			if (StringUtils.isNotBlank(orderRecordBean.getScheduleDate())) {
				buffer.append("  <scheduleDate>" + orderRecordBean.getScheduleDate() + "</scheduleDate>\n");
			} else {
				buffer.append("  <scheduleDate>" + " " + "</scheduleDate>\n");
			}

			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}
	}

	/**
	 * 创建支付成功通知消息
	 * @param dataBean
	 */
	private void buildNotificationBeanData(Object dataBean) {
		if (dataBean instanceof NotificationBean) {
			NotificationBean notificationBean = (NotificationBean) dataBean;

			buffer.append("<Data>\n");
			if (StringUtils.isNotBlank(notificationBean.getAgtOrdNum())) {
				buffer.append("  <agtOrdNum>" + notificationBean.getAgtOrdNum() + "</agtOrdNum>\n");
			} else {
				buffer.append("  <agtOrdNum>" + " " + "</agtOrdNum>\n");
			}
			if (StringUtils.isNotBlank(notificationBean.getHisOrdNum())) {
				buffer.append("  <hisOrdNum>" + notificationBean.getHisOrdNum() + "</hisOrdNum>\n");
			} else {
				buffer.append("  <hisOrdNum>" + " " + "</hisOrdNum>\n");
			}

			if (StringUtils.isNotBlank(notificationBean.getPayAmt())) {
				buffer.append("  <payAmt>" + notificationBean.getPayAmt() + "</payAmt>\n");
			} else {
				buffer.append("  <payAmt>" + " " + "</payAmt>\n");
			}
			
			if (StringUtils.isNotBlank(notificationBean.getPayMode())) {
				buffer.append("  <payMode>" + notificationBean.getPayMode() + "</payMode>\n");
			} else {
				buffer.append("  <payMode>" + " " + "</payMode>\n");
			}
			
			if (StringUtils.isNotBlank(notificationBean.getPayTime())) {
				buffer.append("  <payTime>" + notificationBean.getPayTime() + "</payTime>\n");
			} else {
				buffer.append("  <payTime>" + " " + "</payTime>\n");
			}

			if (StringUtils.isNotBlank(notificationBean.getPsOrdNum())) {
				buffer.append("  <psOrdNum>" + notificationBean.getPsOrdNum() + "</psOrdNum>\n");
			} else {
				buffer.append("  <psOrdNum>" + " " + "</psOrdNum>\n");
			}
			
			if (StringUtils.isNotBlank(notificationBean.getPayFlag())) {
				buffer.append("  <payFlag>" + notificationBean.getPayFlag() + "</payFlag>\n");
			} else {
				buffer.append("  <payFlag>" + " " + "</payFlag>\n");
			}
			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}
	}
	
	/**
	 * 创建退款成功通知消息
	 * @param dataBean
	 */
	private void buildRefundNotificationBeanData(Object dataBean) {
		if (dataBean instanceof RefundNotification) {
			RefundNotification notificationBean = (RefundNotification) dataBean;

			buffer.append("<Data>\n");
			if (StringUtils.isNotBlank(notificationBean.getHisOrdNum())) {
				buffer.append("  <hisOrdNum>" + notificationBean.getHisOrdNum() + "</hisOrdNum>\n");
			} else {
				buffer.append("  <hisOrdNum>" + " " + "</hisOrdNum>\n");
			}
			if (StringUtils.isNotBlank(notificationBean.getPayTime())) {
				buffer.append("  <payTime>" + notificationBean.getPayTime() + "</payTime>\n");
			} else {
				buffer.append("  <payTime>" + " " + "</payTime>\n");
			}

			if (StringUtils.isNotBlank(notificationBean.getPayAmt())) {
				buffer.append("  <payAmt>" + notificationBean.getPayAmt() + "</payAmt>\n");
			} else {
				buffer.append("  <payAmt>" + " " + "</payAmt>\n");
			}
			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}
	}
	
	/**
	 * 构建空数据
	 * @param dataBean
	 */
	private void buildNullData(Object dataBean) {
			buffer.append("<Data>\n");
			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}	
	
	
	/**
	 * 創建病人主索引，創建請求xml 
	 * @param dataBean
	 */
	private void buildPatientBeanData(Object dataBean) {
		if (dataBean instanceof PatientBean) {
			PatientBean patientBean = (PatientBean) dataBean;

			buffer.append("<Data>\n");
			if (StringUtils.isNotBlank(patientBean.getPatAddress())) {
				buffer.append("  <patAddress>" + patientBean.getPatAddress() + "</patAddress>\n");
			} else {
				buffer.append("  <patAddress>" + "" + "</patAddress>\n");
			}
			if (StringUtils.isNotBlank(patientBean.getPatAge())) {
				buffer.append("  <patAge>" + patientBean.getPatAge() + "</patAge>\n");
			} else {
				buffer.append("  <patAge>" + "" + "</patAge>\n");
			}

			if (StringUtils.isNotBlank(patientBean.getPatGender())) {
				buffer.append("  <patGender>" + patientBean.getPatGender() + "</patGender>\n");
			} else {
				buffer.append("  <patGender>" + "" + "</patGender>\n");
			}
			
			if (StringUtils.isNotBlank(patientBean.getPatID())) {
				buffer.append("  <patID>" + patientBean.getPatID() + "</patID>\n");
			} else {
				buffer.append("  <patID>" + "" + "</patID>\n");
			}
			
			if (StringUtils.isNotBlank(patientBean.getPatIDType())) {
				buffer.append("  <patIDType>" + patientBean.getPatIDType() + "</patIDType>\n");
			} else {
				buffer.append("  <patIDType>" + "" + "</patIDType>\n");
			}

			if (StringUtils.isNotBlank(patientBean.getPatName())) {
				buffer.append("  <patName>" + patientBean.getPatName() + "</patName>\n");
			} else {
				buffer.append("  <patName>" + "" + "</patName>\n");
			}
			
			if (StringUtils.isNotBlank(patientBean.getPatTel())) {
				buffer.append("  <patTel>" + patientBean.getPatTel() + "</patTel>\n");
			} else {
				buffer.append("  <patTel>" + "" + "</patTel>\n");
			}
			
			buffer.append("</Data>\n");
			buffer.append("</BSHisService>\n");
		}
	}
	
	public StringBuffer getResult() {
		return buffer;
	}

}
