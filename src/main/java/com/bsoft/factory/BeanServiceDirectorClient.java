package com.bsoft.factory;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.bsoft.domain.DepartmentReturnBean;
import com.bsoft.domain.DeptReturnBean;
import com.bsoft.domain.DoctorReturnBean;
import com.bsoft.domain.NotificationReturnBean;
import com.bsoft.domain.OrderCancelReturnBean;
import com.bsoft.domain.OrderRecordReturnBean;
import com.bsoft.domain.OrderSourceReturnBean;
import com.bsoft.domain.PatientIndexReturnBean;
import com.bsoft.domain.PatientReturnBean;
import com.bsoft.domain.RegOrderReturnBean;
import com.bsoft.domain.RegRecordReturnBean;
import com.bsoft.factory.BeanService.BSHiServiceBuilder;

public class BeanServiceDirectorClient {
	private BSHiServiceBuilder builder = null;

	public BeanService parsePatientResponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {
			return builder.build();
		}
		
		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}
		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parsePatientData(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	public BeanService parseDeptInfoResponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {
			return builder.build();
		}

		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}
		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parseDeptInfoData(rootElt);

		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	/**
	 * 解析大科室返回字符串
	 * 
	 * @param responseStr
	 * @return
	 * @throws DocumentException
	 */
	public BeanService parseDeptResponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {
			return builder.build();
		}
		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}
		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parseDeptData(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	public BeanService parseDoctorInfoResponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {
			return builder.build();
		}
		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}
		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parseDoctorInfoData(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	public BeanService parseDoctorInfoResponseStr2(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {
			return builder.build();
		}
		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}
		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parseDoctorInfoData2(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	public BeanService parseOrderSourceResponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {
			return builder.build();
		}
		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}
		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parseOrderSourceData(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	public BeanService parseRegRecordResponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {

			return builder.build();
		}
		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}
		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parseRegRecordData(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	public BeanService parseRegOrderResponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {
			return builder.build();
		}

		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}

		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parseRegOrderData(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	public BeanService parseOrderCancelResponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {
			return builder.build();
		}
		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}
		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parseOrderCancelReturnData(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	public BeanService parseCreatePatientResponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {

			return builder.build();
		}

		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}

		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parseCreatePatientData(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	public BeanService parseOrderRecordResponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {
			return builder.build();
		}
		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}
		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parseOrderRecordData(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	/**
	 * 解析调用支付通知 后存储过程的返回值
	 * 
	 * @param responseStr
	 * @return
	 * @throws DocumentException
	 */
	public BeanService parseNotificationRsponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {
			return builder.build();
		}
		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}
		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		parseNotificationReturnData(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	/**
	 * 解析退款后 通知存储过程的返回值
	 * 
	 * @param responseStr
	 * @return
	 * @throws DocumentException
	 */
	public BeanService parseRefundNotificationRsponseStr(String responseStr) throws DocumentException {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		if (StringUtils.isBlank(responseStr)) {
			return builder.build();
		}
		if (doc == null) {
			doc = DocumentHelper.parseText(responseStr);
		}
		Element rootElt = doc.getRootElement();
		parseHead(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	@SuppressWarnings("unchecked")
	private void parseRegOrderData(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					RegOrderReturnBean regOrderReturnBean = new RegOrderReturnBean();
					String hisOrdNumText = null;
					String serialNumText = null;
					String visitPositionText = null;
					String visitDescText = null;
					String patName = null;
					if (StringUtils.isNotBlank(item.elementTextTrim("hisOrdNum"))) {
						hisOrdNumText = item.elementTextTrim("hisOrdNum");
						serialNumText = item.elementTextTrim("serialNum");
						visitPositionText = item.elementTextTrim("visitPosition");
						visitDescText = item.elementTextTrim("visitDesc");
						patName = item.elementTextTrim("patName");
					} else {
						hisOrdNumText = item.elementTextTrim("HISORDNUM");
						serialNumText = item.elementTextTrim("SERIALNUM");
						visitPositionText = item.elementTextTrim("VISITPOSITION");
						visitDescText = item.elementTextTrim("VISITDESC");
						patName = item.elementTextTrim("PATNAME");
					}

					regOrderReturnBean.setHisOrdNum(hisOrdNumText);
					regOrderReturnBean.setSerialNum(serialNumText);
					regOrderReturnBean.setVisitPosition(visitPositionText);
					regOrderReturnBean.setVisitDesc(visitDescText);
					regOrderReturnBean.setPatName(patName);

					builder.setItem(regOrderReturnBean);
				} // end for

			}
		}

	}

	@SuppressWarnings("unchecked")
	private void parseRegRecordData(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					RegRecordReturnBean regRecordReturnBean = new RegRecordReturnBean();
					String hisOrdNumText = null;
					String regChannelText = null;
					String regChannelDescText = null;
					String payChannelText = null;
					String payChannelDescText = null;
					String isPaidText = null;
					String statusText = null;
					String deptCodeText = null;
					String deptNameText = null;
					String doctorCodeText = null;
					String doctorNameText = null;
					String scheduleDateText = null;
					String timeFlagText = null;
					String beginTimeText = null;
					String endTimeText = null;
					String regFeeText = null;
					String patIndexText = null;
					String serialNumText = null;
					if (StringUtils.isNotBlank(item.elementTextTrim("hisOrdNum"))
							|| StringUtils.isNotBlank(item.elementTextTrim("patIndex"))) {
						hisOrdNumText = item.elementTextTrim("hisOrdNum");
						regChannelText = item.elementTextTrim("regChannel");
						regChannelDescText = item.elementTextTrim("regChannelDesc");
						payChannelText = item.elementTextTrim("payChannel");

						payChannelDescText = item.elementTextTrim("payChannelDesc");
						isPaidText = item.elementTextTrim("isPaid");
						statusText = item.elementTextTrim("status");
						deptCodeText = item.elementTextTrim("deptCode");

						deptNameText = item.elementTextTrim("deptName");
						doctorCodeText = item.elementTextTrim("doctorCode");
						doctorNameText = item.elementTextTrim("doctorName");
						scheduleDateText = item.elementTextTrim("scheduleDate");

						timeFlagText = item.elementTextTrim("timeFlag");
						beginTimeText = item.elementTextTrim("beginTime");
						endTimeText = item.elementTextTrim("endTime");
						regFeeText = item.elementTextTrim("regFee");

						patIndexText = item.elementTextTrim("patIndex");
						serialNumText = item.elementTextTrim("serialNumText");
					} else {
						hisOrdNumText = item.elementTextTrim("HISORDNUM");
						regChannelText = item.elementTextTrim("REGCHANNEL");
						regChannelDescText = item.elementTextTrim("REGCHANNELDESC");
						payChannelText = item.elementTextTrim("PAYCHANNEL");

						payChannelDescText = item.elementTextTrim("PAYCHANNELDESC");
						isPaidText = item.elementTextTrim("ISPAID");
						statusText = item.elementTextTrim("STATUS");
						deptCodeText = item.elementTextTrim("DEPTCODE");

						deptNameText = item.elementTextTrim("DEPTNAME");
						doctorCodeText = item.elementTextTrim("DOCTORCODE");
						doctorNameText = item.elementTextTrim("DOCTORNAME");
						scheduleDateText = item.elementTextTrim("SCHEDULEDATE");

						timeFlagText = item.elementTextTrim("TIMEFLAG");
						beginTimeText = item.elementTextTrim("BEGINTIME");
						endTimeText = item.elementTextTrim("ENDTIME");
						regFeeText = item.elementTextTrim("REGFEE");

						patIndexText = item.elementTextTrim("PATINDEX");
						serialNumText = item.elementTextTrim("SERIALNUMTEXT");
					}

					regRecordReturnBean.setHisOrdNum(hisOrdNumText);
					regRecordReturnBean.setRegChannel(regChannelText);
					regRecordReturnBean.setRegChannelDesc(regChannelDescText);
					regRecordReturnBean.setPayChannel(payChannelText);

					regRecordReturnBean.setPayChannelDesc(payChannelDescText);
					regRecordReturnBean.setIsPaid(isPaidText);
					regRecordReturnBean.setStatus(statusText);
					regRecordReturnBean.setDeptCode(deptCodeText);

					regRecordReturnBean.setDeptName(deptNameText);
					regRecordReturnBean.setDoctorCode(doctorCodeText);
					regRecordReturnBean.setDoctorName(doctorNameText);
					regRecordReturnBean.setScheduleDate(scheduleDateText);

					regRecordReturnBean.setTimeFlag(timeFlagText);
					regRecordReturnBean.setBeginTime(beginTimeText);
					regRecordReturnBean.setEndTime(endTimeText);
					regRecordReturnBean.setRegFee(regFeeText);

					regRecordReturnBean.setPatIndex(patIndexText);
					regRecordReturnBean.setSerialNum(serialNumText);

					builder.setItem(regRecordReturnBean);
				} // end for

			}
		}

	}

	@SuppressWarnings("unchecked")
	private void parseOrderSourceData(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					OrderSourceReturnBean orderSourceReturnBean = new OrderSourceReturnBean();
					String hisOrdNumText = null;
					String deptCodeText = null;
					String deptNameText = null;
					String doctorCodeText = null;
					String scheduleDateText = null;
					String timeFlagText = null;
					String beginTimeText = null;
					String endTimeText = null;
					String workStatusText = null;
					String regFeeText = null;
					String totalNumText = null;
					String leftNumText = null;
					String deptType = null;
					if (StringUtils.isNotBlank(item.elementTextTrim("hisOrdNum"))) {
						hisOrdNumText = item.elementTextTrim("hisOrdNum");
						// 这里返回的不是驼峰
						deptCodeText = item.elementTextTrim("deptcode");
						doctorCodeText = item.elementTextTrim("doctorCode");
						scheduleDateText = item.elementTextTrim("scheduleDate");

						timeFlagText = item.elementTextTrim("timeFlag");
						beginTimeText = item.elementTextTrim("beginTime");
						endTimeText = item.elementTextTrim("endTime");
						workStatusText = item.elementTextTrim("workStatus");

						regFeeText = item.elementTextTrim("regFee");
						totalNumText = item.elementTextTrim("totalNum");
						leftNumText = item.elementTextTrim("leftNum");
						deptNameText = item.elementTextTrim("deptName");
						// 添加科室类型
						deptType = item.elementTextTrim("deptType");
					} else {
						hisOrdNumText = item.elementTextTrim("HISORDNUM");
						deptCodeText = item.elementTextTrim("DEPTCODE");
						doctorCodeText = item.elementTextTrim("DOCTORCODE");
						scheduleDateText = item.elementTextTrim("SCHEDULEDATE");

						timeFlagText = item.elementTextTrim("TIMEFLAG");
						beginTimeText = item.elementTextTrim("BEGINTIME");
						endTimeText = item.elementTextTrim("ENDTIME");
						workStatusText = item.elementTextTrim("WORKSTATUS");

						regFeeText = item.elementTextTrim("REGFEE");
						totalNumText = item.elementTextTrim("TOTALNUM");
						leftNumText = item.elementTextTrim("LEFTNUM");
						deptNameText = item.elementTextTrim("DEPTNAME");
						// 添加科室类型
						deptType = item.elementTextTrim("DEPTTYPE");
					}

					orderSourceReturnBean.setHisOrdNum(hisOrdNumText);
					orderSourceReturnBean.setDeptCode(deptCodeText);
					orderSourceReturnBean.setDoctorCode(doctorCodeText);
					orderSourceReturnBean.setScheduleDate(scheduleDateText);

					orderSourceReturnBean.setTimeFlag(timeFlagText);
					orderSourceReturnBean.setBeginTime(beginTimeText);
					orderSourceReturnBean.setEndTime(endTimeText);
					orderSourceReturnBean.setWorkStatus(workStatusText);

					orderSourceReturnBean.setRegFee(regFeeText);
					orderSourceReturnBean.setTotalNum(totalNumText);
					orderSourceReturnBean.setLeftNum(leftNumText);
					orderSourceReturnBean.setDeptName(deptNameText);
					orderSourceReturnBean.setDeptType(deptType);

					builder.setItem(orderSourceReturnBean);
				} // end for
			}
		}

	}
	@SuppressWarnings("unchecked")
	private void parseDoctorInfoData(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					DoctorReturnBean doctorReturnBean = new DoctorReturnBean();
					String parentDeptCodeText = null;
					String parentDeptNameText = null;
					String deptCodeText = null;
					String deptNameText = null;
					String doctorCodeText = null;
					String doctorNameText = null;
					String doctorsKillText = null;
					String doctorIntrodutionText = null;
					String doctorTitle = null;
					if (StringUtils.isNotBlank(item.elementTextTrim("doctorCode"))) {
						parentDeptCodeText = item.elementTextTrim("doctorCode");
						parentDeptNameText = item.elementTextTrim("parentDeptName");

						deptCodeText = item.elementTextTrim("deptCode");
						deptNameText = item.elementTextTrim("deptName");

						doctorCodeText = item.elementTextTrim("doctorCode");
						doctorNameText = item.elementTextTrim("doctorName");

						doctorsKillText = item.elementTextTrim("doctorsKill");
						doctorIntrodutionText = item.elementTextTrim("doctorIntrodutionText");
						doctorTitle = item.elementTextTrim("doctorTitle");
					} else {
						parentDeptCodeText = item.elementTextTrim("PARENTDEPTCODE");
						parentDeptNameText = item.elementTextTrim("PARENTDEPTNAME");

						deptCodeText = item.elementTextTrim("DEPTCODE");
						deptNameText = item.elementTextTrim("DEPTNAME");

						doctorCodeText = item.elementTextTrim("DOCTORCODE");
						doctorNameText = item.elementTextTrim("DOCTORNAME");

						doctorsKillText = item.elementTextTrim("DOCTORSKILL");
						doctorIntrodutionText = item.elementTextTrim("DOCTORINTRODUTION");
						doctorTitle = item.elementTextTrim("DOCTORTITLE");
					}

					doctorReturnBean.setParentDeptCode(parentDeptCodeText);
					doctorReturnBean.setParentDeptName(parentDeptNameText);

					doctorReturnBean.setDeptCode(deptCodeText);
					doctorReturnBean.setDeptName(deptNameText);

					doctorReturnBean.setDoctorCode(doctorCodeText);
					doctorReturnBean.setDoctorName(doctorNameText);

					doctorReturnBean.setDoctorsKill(doctorsKillText);
					doctorReturnBean.setDoctorIntrodution(doctorIntrodutionText);
					doctorReturnBean.setDoctorTitle(doctorTitle);
					builder.setItem(doctorReturnBean);
				} // end for
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void parseDoctorInfoData2(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					//原：DoctorInfoResponse
					DoctorReturnBean doctorReturnBean = new DoctorReturnBean();
					String parentdeptCode = null;
					String parentdeptName = null;
					String doctorCode = null;
					String doctorName = null;
					String doctorTitle = null;
					//添加科室类别
					String deptType = null;
					if (StringUtils.isNotBlank(item.elementTextTrim("parentdeptCode"))) {
						parentdeptCode = item.elementTextTrim("parentdeptCode");
						parentdeptName = item.elementTextTrim("parentdeptName");
						doctorCode = item.elementTextTrim("doctorCode");
						doctorName = item.elementTextTrim("doctorName");
						doctorTitle = item.elementTextTrim("doctorTitle");
						//添加科室类别
						deptType = item.elementTextTrim("deptType");
					} else {
						parentdeptCode = item.elementTextTrim("PARENTDEPTCODE");
						parentdeptName = item.elementTextTrim("PARENTDEPTNAME");
						doctorCode = item.elementTextTrim("DOCTORCODE");
						doctorName = item.elementTextTrim("DOCTORNAME");
						doctorTitle = item.elementTextTrim("DOCTORTITLE");
						//添加科室类别
						deptType = item.elementTextTrim("DEPTTYPE");
					}
					doctorReturnBean.setDoctorCode(doctorCode);
					doctorReturnBean.setDoctorName(doctorName);
					doctorReturnBean.setDoctorTitle(doctorTitle);
					doctorReturnBean.setParentDeptCode(parentdeptCode);
					doctorReturnBean.setParentDeptName(parentdeptName);
					//设置depType
					doctorReturnBean.setDeptType(deptType);
					builder.setItem(doctorReturnBean);
				} // end for

			}
		}

	}

	@SuppressWarnings("unchecked")
	private void parseDeptInfoData(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					DeptReturnBean deptReturnBean = new DeptReturnBean();
					String deptCodeText = null;
					String deptNameText = null;
					String hasChildText = null;
					String parentDeptCodeText = null;
					String parentDeptNameText = null;
					String deptDescriptionText = null;
					String deptLocationText = null;
					String pydm = null;
					
					if (StringUtils.isNotBlank(item.elementTextTrim("deptCode"))) {
						deptCodeText = item.elementTextTrim("deptCode");
						deptNameText = item.elementTextTrim("deptName");
						hasChildText = item.elementTextTrim("hasChild");
						parentDeptCodeText = item.elementTextTrim("parentDeptCode");
						parentDeptNameText = item.elementTextTrim("parentDeptName");
						deptDescriptionText = item.elementTextTrim("deptDescription");
						deptLocationText = item.elementTextTrim("deptLocation");
						pydm = item.elementTextTrim("pydm");
					} else {
						deptCodeText = item.elementTextTrim("DEPTCODE");
						deptNameText = item.elementTextTrim("DEPTNAME");
						hasChildText = item.elementTextTrim("HASCHILD");
						parentDeptCodeText = item.elementTextTrim("PARENTDEPTCODE");
						parentDeptNameText = item.elementTextTrim("PARENTDEPTNAME");
						deptDescriptionText = item.elementTextTrim("PARENTDEPTNAME");
						deptLocationText = item.elementTextTrim("DEPTLOCATION");
						pydm = item.elementTextTrim("PYDM");
					}
					deptReturnBean.setDeptCode(deptCodeText);
					deptReturnBean.setDeptName(deptNameText);
					deptReturnBean.setHasChild(hasChildText);
					deptReturnBean.setParentDeptCode(parentDeptCodeText);
					deptReturnBean.setParentDeptName(parentDeptNameText);
					deptReturnBean.setDeptDescription(deptDescriptionText);
					deptReturnBean.setDeptLocation(deptLocationText);
					deptReturnBean.setPydm(pydm);
					builder.setItem(deptReturnBean);
				} // end for

			}
		}

	}
	
	@SuppressWarnings("unchecked")
	private void parseDeptData(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					DepartmentReturnBean deptReturnBean = new DepartmentReturnBean();
					String parentDeptCode = null;
					String parentDeptNam = null;
					String deptType = null;
					if (StringUtils.isNotBlank(item.elementTextTrim("parentDeptCode"))) {
						parentDeptCode = item.elementTextTrim("parentDeptCode");
						parentDeptNam = item.elementTextTrim("parentDeptNam");
						deptType = item.elementTextTrim("deptType");
					} else {
						parentDeptCode = item.elementTextTrim("PARENTDEPTCODE");
						parentDeptNam = item.elementTextTrim("PARENTDEPTNAM");
						deptType = item.elementTextTrim("DEPTTYPE");
					}
					deptReturnBean.setDeptType(deptType);
					deptReturnBean.setParentDeptCode(parentDeptCode);
					deptReturnBean.setParentDeptNam(parentDeptNam);
					builder.setItem(deptReturnBean);
				} // end for

			}
		}

	}
	
	@SuppressWarnings("unchecked")
	private void parseNotificationReturnData(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					NotificationReturnBean notificationReturnBean = new NotificationReturnBean();
					String serialNum = null;
					String visitPosition = null;
					String visitDesc = null;
					if (StringUtils.isNotBlank(item.elementTextTrim("serialNum"))) {
						serialNum = item.elementTextTrim("serialNum");
						visitPosition = item.elementTextTrim("visitPosition");
						visitDesc = item.elementTextTrim("visitDesc");
					} else {
						serialNum = item.elementTextTrim("SERIALNUM");
						visitPosition = item.elementTextTrim("VISITPOSITION");
						visitDesc = item.elementTextTrim("VISITDESC");
					}
					notificationReturnBean.setSerialNum(serialNum);
					notificationReturnBean.setVisitDesc(visitDesc);
					notificationReturnBean.setVisitPosition(visitPosition);
					builder.setItem(notificationReturnBean);
				} // end for
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void parseOrderCancelReturnData(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					OrderCancelReturnBean orderCancelReturnBean = new OrderCancelReturnBean();
					String hospno = null;
					if (StringUtils.isNotBlank(item.elementTextTrim("hospno"))) {
						hospno = item.elementTextTrim("hospno");
					} else {
						hospno = item.elementTextTrim("HOSPNO");
					}
					orderCancelReturnBean.setHospno(hospno);
					builder.setItem(orderCancelReturnBean);
				} // end for
			}
		}
	}

	public BeanService parseCommonException(String responseStr) {
		BeanService bsHisServiceBean = null;
		Document doc = null;
		try {
			if (doc == null) {
				doc = DocumentHelper.parseText(responseStr);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element rootElt = doc.getRootElement();
		parseCommonExceptionHead(rootElt);
		bsHisServiceBean = builder.build();
		return bsHisServiceBean;
	}

	@SuppressWarnings("unchecked")
	private void parsePatientData(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					PatientIndexReturnBean patientIndexReturnBean = new PatientIndexReturnBean();
					String patIndexText = null;
					String patNameText = null;
					String patGenderText = null;
					String patTelText = null;
					String patAddressText = null;
					String patAgeText = null;
					String patIDText = null;
					if (StringUtils.isNotBlank(item.elementTextTrim("patIndex"))) {
						patIndexText = item.elementTextTrim("patIndex");
						patNameText = item.elementTextTrim("patName");
						patGenderText = item.elementTextTrim("patGender");
						patTelText = item.elementTextTrim("patTel");
						patAddressText = item.elementTextTrim("patAddress");
						patAgeText = item.elementTextTrim("patAge");
						patIDText = item.elementTextTrim("patID");
					} else {
						patIndexText = item.elementTextTrim("PATINDEX");
						patNameText = item.elementTextTrim("PATNAME");
						patGenderText = item.elementTextTrim("PATGENDER");
						patTelText = item.elementTextTrim("PATTEL");
						patAddressText = item.elementTextTrim("PATADDRESS");
						patAgeText = item.elementTextTrim("PATAGE");
						patIDText = item.elementTextTrim("PATID");
					}
					patientIndexReturnBean.setPatIndex(patIndexText);
					patientIndexReturnBean.setPatName(patNameText);
					patientIndexReturnBean.setPatGender(patGenderText);
					patientIndexReturnBean.setPatTel(patTelText);
					patientIndexReturnBean.setPatAddress(patAddressText);
					patientIndexReturnBean.setPatAge(patAgeText);
					patientIndexReturnBean.setPatID(patIDText);
					builder.setItem(patientIndexReturnBean);
				} // end for

			}
		}

	}

	/**
	 * 解析预约记录xml
	 * 
	 * @param rootElt
	 */
	@SuppressWarnings("unchecked")
	private void parseOrderRecordData(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					OrderRecordReturnBean orderRecordReturnBean = new OrderRecordReturnBean();
					String hisOrdNum = null;
					String patIndex = null;
					String patID = null;
					String patName = null;
					String serialNum = null;
					String regChannel = null;
					String regChannelDesc = null;
					String payChannel = null;
					String payChannelDesc = null;
					String isPaid = null;
					String status = null;
					String deptCode = null;
					String deptName = null;
					String doctorCode = null;
					String doctorName = null;
					String scheduleDate = null;
					String timeFlag = null;
					String beginTime = null;
					String endTime = null;
					String regFee = null;
					String phonenum = null;
					String appointmentTime = null;
					String parentDeptCode = null;
					String parentDeptName = null;
					if (StringUtils.isNotBlank(item.elementTextTrim("hisOrdNum"))) {
						hisOrdNum = item.elementTextTrim("hisOrdNum");
						patIndex = item.elementTextTrim("patIndex");

						patID = item.elementTextTrim("patID");
						patName = item.elementTextTrim("patName");

						regChannel = item.elementTextTrim("regChannel");
						regChannelDesc = item.elementTextTrim("regChannelDesc");
						payChannel = item.elementTextTrim("payChannel");
						payChannelDesc = item.elementTextTrim("payChannelDesc");
						isPaid = item.elementTextTrim("isPaid");

						status = item.elementTextTrim("status");
						deptCode = item.elementTextTrim("deptCode");
						deptName = item.elementTextTrim("deptName");

						doctorCode = item.elementTextTrim("doctorCode");
						doctorName = item.elementTextTrim("doctorName");

						scheduleDate = item.elementTextTrim("scheduleDate");
						timeFlag = item.elementTextTrim("timeFlag");
						beginTime = item.elementTextTrim("beginTime");

						endTime = item.elementTextTrim("endTime");
						regFee = item.elementTextTrim("regFee");
						serialNum = item.elementTextTrim("serialNum");
						phonenum = item.elementTextTrim("phonenum");
						appointmentTime = item.elementText("appointmentTime");
						parentDeptCode = item.elementText("parentDeptCode");
						parentDeptName = item.elementText("parentDeptName");

					} else {
						hisOrdNum = item.elementTextTrim("HISORDNUM");
						patIndex = item.elementTextTrim("PATINDEX");

						patID = item.elementTextTrim("PATID");
						patName = item.elementTextTrim("PATNAME");

						regChannel = item.elementTextTrim("REGCHANNEL");
						regChannelDesc = item.elementTextTrim("REGCHANNELDESC");
						payChannel = item.elementTextTrim("PAYCHANNEL");

						payChannelDesc = item.elementTextTrim("PAYCHANNELDESC");
						isPaid = item.elementTextTrim("ISPAID");

						status = item.elementTextTrim("STATUS");
						deptCode = item.elementTextTrim("DEPTCODE");
						deptName = item.elementTextTrim("DEPTNAME");

						doctorCode = item.elementTextTrim("DOCTORCODE");
						doctorName = item.elementTextTrim("DOCTORNAME");

						scheduleDate = item.elementTextTrim("SCHEDULEDATE");
						timeFlag = item.elementTextTrim("TIMEFLAG");
						beginTime = item.elementTextTrim("BEGINTIME");

						endTime = item.elementTextTrim("ENDTIME");
						regFee = item.elementTextTrim("REGFEE");
						serialNum = item.elementTextTrim("SERIALNUM");
						phonenum = item.elementTextTrim("PHONENUM");
						appointmentTime = item.elementText("APPOINTMENTTIME");
						parentDeptCode = item.elementText("PARENTDEPTCODE");
						parentDeptName = item.elementText("PARENTDEPTNAME");
					}
					orderRecordReturnBean.setBeginTime(beginTime);
					orderRecordReturnBean.setDeptCode(deptCode);
					orderRecordReturnBean.setDeptName(deptName);
					orderRecordReturnBean.setDoctorCode(doctorCode);
					orderRecordReturnBean.setDoctorName(doctorName);
					orderRecordReturnBean.setEndTime(endTime);
					orderRecordReturnBean.setHisOrdNum(hisOrdNum);
					orderRecordReturnBean.setIsPaid(isPaid);
					orderRecordReturnBean.setPatID(patID);
					orderRecordReturnBean.setPatIndex(patIndex);
					orderRecordReturnBean.setPatName(patName);
					orderRecordReturnBean.setPayChannel(payChannel);
					orderRecordReturnBean.setRegChannelDesc(regChannelDesc);
					orderRecordReturnBean.setRegFee(regFee);
					orderRecordReturnBean.setScheduleDate(scheduleDate);
					orderRecordReturnBean.setStatus(status);
					orderRecordReturnBean.setTimeFlag(timeFlag);
					orderRecordReturnBean.setRegChannel(regChannel);
					orderRecordReturnBean.setPayChannelDesc(payChannelDesc);
					orderRecordReturnBean.setSerialNum(serialNum);
					orderRecordReturnBean.setPhonenum(phonenum);
					orderRecordReturnBean.setAppointmentTime(appointmentTime);
					orderRecordReturnBean.setParentDeptCode(parentDeptCode);
					orderRecordReturnBean.setParentDeptName(parentDeptName);
					builder.setItem(orderRecordReturnBean);
				} // end for

			}
		}

	}

	/**
	 * 解析创建主索引返回值
	 * 
	 * @param rootElt
	 */
	@SuppressWarnings("unchecked")
	private void parseCreatePatientData(Element rootElt) {
		Iterator<?> dataIter = rootElt.elementIterator("Data");
		Element dataEle = null;
		if (dataIter.hasNext()) {
			dataEle = (Element) dataIter.next();
		}
		if (dataEle != null) {
			List<Element> itemsEle = dataEle.elements();
			if (itemsEle != null && itemsEle.size() > 0) {
				for (Element item : itemsEle) {
					PatientReturnBean patientReturnBean = new PatientReturnBean();

					String patIndex = null;

					if (StringUtils.isNotBlank(item.elementTextTrim("patIndex"))) {
						patIndex = item.elementTextTrim("patIndex");
					} else {
						patIndex = item.elementTextTrim("PATINDEX");
					}
					patientReturnBean.setPatIndex(patIndex);
					builder.setItem(patientReturnBean);
				} // end for

			}
		}

	}

	private void parseHead(Element rootElt) {
		Iterator<?> HeadIter = rootElt.elementIterator("Head");
		Element headrecordEle = null;
		if (HeadIter.hasNext()) {
			headrecordEle = (Element) HeadIter.next();
		}
		String serviceCodeText = headrecordEle.elementTextTrim("serviceCode");
		String serviceDescText = headrecordEle.elementTextTrim("serviceDesc");
		String clientRunningNumText = headrecordEle.elementTextTrim("clientRunningNum");
		String partnerIDText = headrecordEle.elementTextTrim("partnerID");
		String clientTypeText = headrecordEle.elementTextTrim("clientType");
		String terminalCodeText = headrecordEle.elementTextTrim("terminalCode");

		String userIDText = headrecordEle.elementTextTrim("userID");
		String timeStampText = headrecordEle.elementTextTrim("timeStamp");
		String servicePasswordText = headrecordEle.elementTextTrim("servicePassword");
		String serverRunningNumText = headrecordEle.elementTextTrim("serverRunningNum");
		String resultCodeText = headrecordEle.elementTextTrim("resultCode");
		String resultMessageText = headrecordEle.elementTextTrim("resultMessage");

		BeanService.BSHiServiceBuilder builder = new BeanService.BSHiServiceBuilder(serviceCodeText, serviceDescText,
				clientRunningNumText, partnerIDText, clientTypeText, terminalCodeText, userIDText, timeStampText,
				servicePasswordText, serverRunningNumText, resultCodeText, resultMessageText);
		this.builder = builder;
	}

	private void parseCommonExceptionHead(Element rootElt) {
		Iterator<?> HeadIter = rootElt.elementIterator("Head");
		Element headrecordEle = null;
		if (HeadIter.hasNext()) {
			headrecordEle = (Element) HeadIter.next();
		}

		String serviceCodeText = headrecordEle.elementTextTrim("serviceCode");
		String serviceDescText = headrecordEle.elementTextTrim("serviceDesc");
		String clientRunningNumText = headrecordEle.elementTextTrim("clientRunningNum");
		String partnerIDText = headrecordEle.elementTextTrim("partnerID");
		String clientTypeText = headrecordEle.elementTextTrim("clientType");
		String terminalCodeText = headrecordEle.elementTextTrim("terminalCode");

		String userIDText = headrecordEle.elementTextTrim("userID");
		String timeStampText = headrecordEle.elementTextTrim("timeStamp");
		String servicePasswordText = headrecordEle.elementTextTrim("servicePassword");
		String serverRunningNumText = headrecordEle.elementTextTrim("serverRunningNum");

		String resultCodeText = "-1";
		String resultMessageText = "解析XMl发生异常";

		BeanService.BSHiServiceBuilder builder = new BeanService.BSHiServiceBuilder(serviceCodeText, serviceDescText,
				clientRunningNumText, partnerIDText, clientTypeText, terminalCodeText, userIDText, timeStampText,
				servicePasswordText, serverRunningNumText, resultCodeText, resultMessageText);
		this.builder = builder;
	}

}
