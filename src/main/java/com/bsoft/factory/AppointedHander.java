package com.bsoft.factory;

import java.util.Map;
import com.bsoft.constant.CommonConst;
import com.bsoft.tools.RequestDataUtil;

/**
 * 预约服务助手
 * 
 * @author wms1231
 *
 */
public class AppointedHander {

	/**
	 * 构建请求头
	 * 
	 * @param map
	 * @return
	 */
	public static String buildRequestData(Map<String, Object> map, String serviceCode) {
		XmlServiceImplBuilder xmlBuilder = new XmlServiceImplBuilder();
		XmlServiceDirectorClient xmlClient = new XmlServiceDirectorClient(xmlBuilder);
		if (map == null) {
			return "";
		}
		if (CommonConst.SERVICE_CODE_PATIENTINDEX.equals(serviceCode)) {
			String patCardNo = RequestDataUtil.getValueForKey(map, "patCardNo");
			xmlClient.constructPatientIndexBean(patCardNo);
			return xmlBuilder.getResult().toString();
		} else if (CommonConst.SERVICE_CODE_DEPTINFO.equals(serviceCode)) {
			String deptCode = RequestDataUtil.getValueForKey(map, "deptCode");
			String parentdeptCode = RequestDataUtil.getValueForKey(map, "parentdeptCode");
			xmlClient.constructDeptInfoBean(deptCode, parentdeptCode);
			return xmlBuilder.getResult().toString();
		} else if (CommonConst.SERVICE_CODE_DEPT.equals(serviceCode)) {
			xmlClient.constructDeptInfo();
			return xmlBuilder.getResult().toString();

		} else if (CommonConst.SERVICE_CODE_DOCTORINFO.equals(serviceCode)) {
			String deptCode = RequestDataUtil.getValueForKey(map, "deptCode");
			String parentdeptCode = RequestDataUtil.getValueForKey(map, "parentdeptCode");
			String doctorCode = RequestDataUtil.getValueForKey(map, "doctorCode");
			xmlClient.constructDoctorInfoBean(parentdeptCode, deptCode, doctorCode);
			return xmlBuilder.getResult().toString();
		} else if (CommonConst.SERVICE_CODE_DOCTOR.equals(serviceCode)) {
			String doctorName = RequestDataUtil.getValueForKey(map, "doctorName");
			xmlClient.constructDoctorInfoBean2(doctorName);
			return xmlBuilder.getResult().toString();
			
		} else if (CommonConst.SERVICE_CODE_REGORDER.equals(serviceCode)) {// 预约挂号
			String hisOrdNum = RequestDataUtil.getValueForKey(map, "hisOrdNum");
			String psOrdNum = RequestDataUtil.getValueForKey(map, "psOrdNum");
			String patIndex = RequestDataUtil.getValueForKey(map, "patIndex");
			String phonenum = RequestDataUtil.getValueForKey(map, "phonenum");
			String regChannel = RequestDataUtil.getValueForKey(map, "regChannel");
			xmlClient.constructRegOrderBean(hisOrdNum, psOrdNum, patIndex, phonenum, regChannel);
			return xmlBuilder.getResult().toString();
		} else if (CommonConst.SERVICE_CODE_ORDERSOURCE.equals(serviceCode)) {
			String beginDate = RequestDataUtil.getValueForKey(map, "beginDate");
			String endDate = RequestDataUtil.getValueForKey(map, "endDate");
			String deptCode = RequestDataUtil.getValueForKey(map, "deptCode");
			String doctorCode = RequestDataUtil.getValueForKey(map, "doctorCode");
			xmlClient.constructOrderSourceBean(beginDate, endDate, deptCode, doctorCode);
			return xmlBuilder.getResult().toString();
		} else if (CommonConst.SERVICE_CODE_ORDERSOURCE_NEW.equals(serviceCode)) {
			String beginDate = RequestDataUtil.getValueForKey(map, "beginDate");
			String endDate = RequestDataUtil.getValueForKey(map, "endDate");
			String parentDeptCode = RequestDataUtil.getValueForKey(map, "parentDeptCode");
			String doctorCode = RequestDataUtil.getValueForKey(map, "doctorCode");
			xmlClient.constructOrderSourceBeannew(beginDate, endDate, parentDeptCode, doctorCode);
			return xmlBuilder.getResult().toString();
		} else if (CommonConst.SERVICE_CODE_REGORDER_CANCEL.equals(serviceCode)) {
			String hisOrdNum = RequestDataUtil.getValueForKey(map, "hisOrdNum");
			String psOrdNum = RequestDataUtil.getValueForKey(map, "psOrdNum");
			String patIndex = RequestDataUtil.getValueForKey(map, "patIndex");
			String srialNum = RequestDataUtil.getValueForKey(map, "srialNum");
			String cancelReason = RequestDataUtil.getValueForKey(map, "cancelReason");

			xmlClient.constructOrderCancelBean(hisOrdNum, psOrdNum, patIndex, srialNum, cancelReason);
			return xmlBuilder.getResult().toString();
		} else if (CommonConst.SERVICE_CODE_REGRECORD.equals(serviceCode)) {
			String patIndex = RequestDataUtil.getValueForKey(map, "patIndex");
			String hisOrdNum = RequestDataUtil.getValueForKey(map, "hisOrdNum");
			xmlClient.constructRegRecordBean(patIndex, hisOrdNum);
			return xmlBuilder.getResult().toString();
		} else if (CommonConst.SERVICE_CODE_GET_REGORDER.equals(serviceCode)) {
			String scheduleDate = RequestDataUtil.getValueForKey(map, "scheduleDate");
			String parentdeptCode = RequestDataUtil.getValueForKey(map, "parentdeptCode");
			String deptCode = RequestDataUtil.getValueForKey(map, "deptCode");
			String doctorCode = RequestDataUtil.getValueForKey(map, "doctorCode");
			String patIndex = RequestDataUtil.getValueForKey(map, "patIndex");
			xmlClient.constructOrderRecordBean(scheduleDate, parentdeptCode, deptCode, doctorCode, patIndex);
			return xmlBuilder.getResult().toString();
		} else if (CommonConst.SERVICE_CODE_CREATE_PATIENT.equals(serviceCode)) {
			String patIDType = RequestDataUtil.getValueForKey(map, "patIDType");
			String patID = RequestDataUtil.getValueForKey(map, "patID");
			String patName = RequestDataUtil.getValueForKey(map, "patName");
			String patGender = RequestDataUtil.getValueForKey(map, "patGender");
			String patTel = RequestDataUtil.getValueForKey(map, "patTel");
			String patAddress = RequestDataUtil.getValueForKey(map, "patAddress");
			String patAge = RequestDataUtil.getValueForKey(map, "patAge");
			xmlClient.constructPatientBean(patIDType, patID, patName, patGender, patTel, patAddress, patAge);
			return xmlBuilder.getResult().toString();
		} else if (CommonConst.SERVICE_CODE_NOTIFICATION.equals(serviceCode)) {
			String hisOrdNum = RequestDataUtil.getValueForKey(map, "hisOrdNum");
			String psOrdNum = RequestDataUtil.getValueForKey(map, "psOrdNum");
			String payMode = RequestDataUtil.getValueForKey(map, "payMode");
			String payAmt = RequestDataUtil.getValueForKey(map, "payAmt");
			String agtOrdNum = RequestDataUtil.getValueForKey(map, "agtOrdNum");
			String payTime = RequestDataUtil.getValueForKey(map, "payTime");
			String payFlag = RequestDataUtil.getValueForKey(map, "payFlag");
			xmlClient.constructNotificationBean(hisOrdNum, psOrdNum, payMode, payAmt, agtOrdNum, payTime, payFlag);
			return xmlBuilder.getResult().toString();
		} else if (CommonConst.SERVICE_CODE_REFUNDNOTIFICATION.equals(serviceCode)) {
			String hisOrdNum = RequestDataUtil.getValueForKey(map, "hisOrdNum");
			String payAmt = RequestDataUtil.getValueForKey(map, "payAmt");
			String payTime = RequestDataUtil.getValueForKey(map, "payTime");
			xmlClient.constructrefundNotificationBean(hisOrdNum, payAmt, payTime);
			return xmlBuilder.getResult().toString();
		}

		return "";
	}

	/**
	 * 构建返回结果
	 * 
	 * @param map
	 * @param responseStr
	 * @return
	 * @throws Exception
	 */
	public static BeanService buildResultData(String responseStr, String serviceCode) throws Exception {
		BeanServiceDirectorClient beanClient = new BeanServiceDirectorClient();

		if (CommonConst.SERVICE_CODE_PATIENTINDEX.equals(serviceCode)) {
			return beanClient.parsePatientResponseStr(responseStr);
		}

		if (CommonConst.SERVICE_CODE_DEPTINFO.equals(serviceCode)) {
			return beanClient.parseDeptInfoResponseStr(responseStr);
		}

		if (CommonConst.SERVICE_CODE_DEPT.equals(serviceCode)) {
			return beanClient.parseDeptResponseStr(responseStr);
		}
		if (CommonConst.SERVICE_CODE_DOCTORINFO.equals(serviceCode)) {
			return beanClient.parseDoctorInfoResponseStr(responseStr);
		}
		if (CommonConst.SERVICE_CODE_DOCTOR.equals(serviceCode)) {
			return beanClient.parseDoctorInfoResponseStr2(responseStr);
		}
		if (CommonConst.SERVICE_CODE_REGORDER.equals(serviceCode)) {
			return beanClient.parseRegOrderResponseStr(responseStr);
		}

		if (CommonConst.SERVICE_CODE_ORDERSOURCE.equals(serviceCode)
				|| CommonConst.SERVICE_CODE_ORDERSOURCE_NEW.equals(serviceCode)) {
			return beanClient.parseOrderSourceResponseStr(responseStr);
		}
		if (CommonConst.SERVICE_CODE_REGORDER_CANCEL.equals(serviceCode)) {
			return beanClient.parseOrderCancelResponseStr(responseStr);
		}
		if (CommonConst.SERVICE_CODE_REGRECORD.equals(serviceCode)) {
			return beanClient.parseRegRecordResponseStr(responseStr);
		}
		if (CommonConst.SERVICE_CODE_GET_REGORDER.equals(serviceCode)) {
			return beanClient.parseOrderRecordResponseStr(responseStr);
		}
		if (CommonConst.SERVICE_CODE_CREATE_PATIENT.equals(serviceCode)) {
			return beanClient.parseCreatePatientResponseStr(responseStr);
		}
		if (CommonConst.SERVICE_CODE_NOTIFICATION.equals(serviceCode)) {
			return beanClient.parseNotificationRsponseStr(responseStr);
		}
		if (CommonConst.SERVICE_CODE_REFUNDNOTIFICATION.equals(serviceCode)) {
			return beanClient.parseRefundNotificationRsponseStr(responseStr);
		}

		return null;
	}

}
