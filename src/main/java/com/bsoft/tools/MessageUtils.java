package com.bsoft.tools;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.royasoft.mas.api.SMSAgent;
import com.royasoft.mas.api.model.SMSSubmitMessage;

public class MessageUtils {

	/**
	 * 生成短信内容
	 * 
	 * @param messageMap
	 * @param messageTemplate
	 * @return
	 */

	public static String generateMessage(Map<String, Object> messageMap, String messageTemplate) {
		if (messageMap == null) {
			return "";
		}
		if (StringUtils.isBlank(messageTemplate)) {
			return "";
		}
		String message = messageTemplate;
		String deptName = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "DEPTNAME"));
		String patName = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "PATNAME"));// 查询病人姓名
		String patNo = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "PATNO"));// 门诊号码
		String doctorName = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "DOCTORNAME"));
		String scheduleDate = RequestDataUtil.getValueForKey(messageMap, "SCHEDULEDATE");
		BigDecimal flag = (BigDecimal) messageMap.get("TIMEFLAG");// , 1, '上午',
																	// 2,'下午',
																	// '其他')
		String timeFlag = "其它";
		if (1 == flag.intValue()) {
			timeFlag = "上午";
		} else if (2 == flag.intValue()) {
			timeFlag = "下午";
		}
		String time = RequestDataUtil.getValueForKey(messageMap, "STARTTIME") + "-"
				+ RequestDataUtil.getValueForKey(messageMap, "ENDTIME");

		String serialNum = RequestDataUtil.getValueForKey(messageMap, "SERIALNUM");

		if (StringUtils.isNotBlank(patName)) {
			message = message.replace("@patName", patName);
		} else {
			message = message.replace("@patName", "");
		}

		if (StringUtils.isNotBlank(deptName)) {
			message = message.replace("@deptName", deptName);
		} else {
			message = message.replace("@deptName", "");
		}

		if (StringUtils.isNotBlank(doctorName)) {
			message = message.replace("@doctorName", doctorName);
		} else {
			message = message.replace("@doctorName", "");
		}

		if (StringUtils.isNotBlank(scheduleDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			int month = 0;
			int day = 0;
			try {
				month = format.parse(scheduleDate).getMonth() + 1;
				day = format.parse(scheduleDate).getDate();
			} catch (ParseException e) {
				message = message.replace("@scheduleDate", "");
			}
			message = message.replace("@scheduleDate", month + "月" + day + "日");

		} else {
			message = message.replace("@scheduleDate", "");
		}
		if (StringUtils.isNotBlank(timeFlag)) {
			message = message.replace("@timeFlag", timeFlag);
		} else {
			message = message.replace("@timeFlag", "");
		}
		if (StringUtils.isNotBlank(serialNum)) {
			message = message.replace("@serialNum", serialNum);
		} else {
			message = message.replace("@serialNum", "");
		}

		if (StringUtils.isNotBlank(patNo)) {
			message = message.replace("@patNo", patNo);
		} else {
			message = message.replace("@patNo", "");
		}

		if (StringUtils.isNotBlank(time)) {
			message = message.replace("@time", time);
		} else {
			message = message.replace("@time", "");
		}

		return message;
	}

	/**
	 * 专门为12580提供短信生成，并且还有hch支付成功回调
	 * 
	 * @param messageMap
	 * @param messageTemplate
	 * @return
	 */
	public static String generateMutiMessage(Map<String, Object> messageMap, String messageTemplate) {
		if (messageMap == null) {
			return "";
		}

		if (StringUtils.isBlank(messageTemplate)) {
			return "";
		}

		String message = messageTemplate;
		String deptName = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "DEPTNAME"));
		String patName = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "PATNAME"));// 查询病人姓名
		String patNo = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "PATNO"));// 门诊号码
		String doctorName = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "DOCTORNAME"));
		String scheduleDate = RequestDataUtil.getValueForKey(messageMap, "SCHEDULEDATE");
		String flag = RequestDataUtil.getValueWithBigDecimalOrString(messageMap, "TIMEFLAG");// ,
																								// 1,
																								// '上午',
																								// 2,'下午',
																								// '其他')
		String timeFlag = "其它";
		if ("1".equals(flag)) {
			timeFlag = "上午";
		} else if ("2".equals(flag)) {
			timeFlag = "下午";
		}
		String time = RequestDataUtil.getValueForKey(messageMap, "STARTTIME") + "-"
				+ RequestDataUtil.getValueForKey(messageMap, "ENDTIME");

		String serialNum = RequestDataUtil.getValueForKey(messageMap, "SERIALNUM");
		// 添加就诊地址
		String address = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "ADDRESS"));

		if (StringUtils.isNotBlank(patName)) {
			message = message.replace("@patName", patName);
		} else {
			message = message.replace("@patName", "");
		}

		if (StringUtils.isNotBlank(deptName)) {
			message = message.replace("@deptName", deptName);
		} else {
			message = message.replace("@deptName", "");
		}

		if (StringUtils.isNotBlank(doctorName)) {
			message = message.replace("@doctorName", doctorName);
		} else {
			message = message.replace("@doctorName", "");
		}

		if (StringUtils.isNotBlank(scheduleDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			int month = 0;
			int day = 0;
			try {
				month = format.parse(scheduleDate).getMonth() + 1;
				day = format.parse(scheduleDate).getDate();
			} catch (ParseException e) {
				message = message.replace("@scheduleDate", "");
			}
			message = message.replace("@scheduleDate", month + "月" + day + "日");

		} else {
			message = message.replace("@scheduleDate", "");
		}
		if (StringUtils.isNotBlank(timeFlag)) {
			message = message.replace("@timeFlag", timeFlag);
		} else {
			message = message.replace("@timeFlag", "");
		}
		if (StringUtils.isNotBlank(serialNum)) {
			message = message.replace("@serialNum", serialNum);
		} else {
			message = message.replace("@serialNum", "");
		}

		if (StringUtils.isNotBlank(patNo)) {
			message = message.replace("@patNo", patNo);
		} else {
			message = message.replace("@patNo", "");
		}

		if (StringUtils.isNotBlank(time)) {
			message = message.replace("@time", time);
		} else {
			message = message.replace("@time", "");
		}

		// 添加地点
		if (StringUtils.isNotBlank(address)) {
			message = message.replace("@address", address);
		} else {
			message = message.replace("@address", "江苏省中医院");
		}

		return message;
	}

	/**
	 * 生成膏方短信模板
	 * 
	 * @param messageMap
	 * @param messageTemplate
	 * @return
	 */
	public static String generateCreamMessage(Map<String, Object> messageMap, String messageTemplate) {
		if (messageMap == null) {
			return "";
		}
		if (StringUtils.isBlank(messageTemplate)) {
			return "";
		}
		String message = messageTemplate;
		String deptName = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "DEPTNAME"));
		String patName = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "PATNAME"));// 查询病人姓名
		String patNo = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "PATNO"));// 门诊号码
		String doctorName = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(messageMap, "DOCTORNAME"));
		String scheduleDate = RequestDataUtil.getValueForKey(messageMap, "SCHEDULEDATE");
		//flag: 1,'上午',2,'下午','其他'
		String flag = RequestDataUtil.getValueWithBigDecimalOrString(messageMap, "TIMEFLAG");
		String timeFlag = "其它";
		if ("1".equals(flag)) {
			timeFlag = "上午";
		} else if ("2".equals(flag)) {
			timeFlag = "下午";
		}
		String serialNum = RequestDataUtil.getValueForKey(messageMap, "SERIALNUM");
		if (StringUtils.isNotBlank(patName)) {
			message = message.replace("@patName", patName);
		} else {
			message = message.replace("@patName", "");
		}

		//去掉科室
		
		/*if (StringUtils.isNotBlank(deptName)) {
			message = message.replace("@deptName", deptName);
		} else {
			message = message.replace("@deptName", "");
		}*/

		if (StringUtils.isNotBlank(doctorName)) {
			message = message.replace("@doctorName", doctorName);
		} else {
			message = message.replace("@doctorName", "");
		}

		if (StringUtils.isNotBlank(scheduleDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			int month = 0;
			int day = 0;
			try {
				month = format.parse(scheduleDate).getMonth() + 1;
				day = format.parse(scheduleDate).getDate();
			} catch (ParseException e) {
				message = message.replace("@scheduleDate", "");
			}
			message = message.replace("@scheduleDate", month + "月" + day + "日");

		} else {
			message = message.replace("@scheduleDate", "");
		}
		if (StringUtils.isNotBlank(timeFlag)) {
			message = message.replace("@timeFlag", timeFlag);
		} else {
			message = message.replace("@timeFlag", "");
		}
		if (StringUtils.isNotBlank(serialNum)) {
			message = message.replace("@serialNum", serialNum);
		} else {
			message = message.replace("@serialNum", "");
		}

		if (StringUtils.isNotBlank(patNo)) {
			message = message.replace("@patNo", patNo);
		} else {
			message = message.replace("@patNo", "");
		}

		return message;
	}

	/**
	 * 发送短信工具类
	 * 
	 * @param msgPropName
	 * @param phoneNum
	 * @param content
	 * @param msgFmt
	 * @param extCode
	 * @param sendTime
	 * @param applicationID
	 * @param siSmsID
	 * @param reqDeliveryReport
	 * @param sendMethod
	 * @param clazz
	 * @throws Exception
	 */
	public static void sendMessage(String msgPropName, String phoneNum, String content, byte msgFmt, String extCode,
			Timestamp sendTime, String applicationID, String siSmsID, int reqDeliveryReport, int sendMethod,
			Class<?> clazz) throws Exception {
		msgPropName = "properties/jdbc.properties";

		if (StringUtils.isBlank(msgPropName) || StringUtils.isBlank(phoneNum)) {// 为发送消息设置默认值
			throw new Exception("电话号码不能为空");
		}
		if (StringUtils.isBlank(content)) {
			throw new Exception("发送内容不能为空");
		}
		if (clazz == null) {
			throw new Exception("clazz不能为空");
		}
		if (msgFmt < 0) {// 消息类型写入的是默认值
			msgFmt = 15;
		}
		if (extCode == null) {
			extCode = "";
		}
		if (sendTime == null) {
			sendTime = new Timestamp(System.currentTimeMillis());
		}
		if (StringUtils.isBlank(applicationID)) {
			applicationID = "P000000000000041";
		}
		if (StringUtils.isBlank(siSmsID)) {
			siSmsID = phoneNum + System.nanoTime();
		}
		if (reqDeliveryReport < 0) {
			reqDeliveryReport = 1;
		}
		if (sendMethod < 0) {
			sendMethod = 2;
		}
		URL configpath = clazz.getClassLoader().getResource(msgPropName);
		SMSAgent.initialize(configpath.getPath());
		SMSSubmitMessage message = new SMSSubmitMessage(phoneNum, content, msgFmt, "", sendTime, applicationID, siSmsID,
				reqDeliveryReport, sendMethod);
		SMSAgent.sendSMS(message);
	}

}
