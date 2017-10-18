package com.bsoft.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

import com.bsoft.support.utils.CharacterEncodingUtil;
import com.github.pagehelper.util.StringUtil;

public class MessageGenerator {

	/**
	 * 組裝短信息内容
	 * 
	 * @param request
	 * @param URL
	 * @return
	 * @throws ParseException
	 */
	public static String generatorMessageContent(HttpServletRequest request, String url, String msg) {
		String content = msg;
		String deptName = getRequestParm(request, "deptName");
		String doctorName = getRequestParm(request, "doctorName");
		String scheduleDate = getRequestParm(request, "scheduleDate");
		String timeFlag = getRequestParm(request, "timeFlag");
		String serialNum = getRequestParm(request, "serialNum");

		if (!StringUtil.isEmpty(deptName)) {
			content = content.replace("@deptName", deptName);
		} else {
			content = content.replace("@deptName", "");
		}

		if (!StringUtil.isEmpty(doctorName)) {
			content = content.replace("@doctorName", doctorName + "医生");
		} else {
			content = content.replace("@doctorName", "");
		}

		if (!StringUtil.isEmpty(scheduleDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			int month = 0;
			int day = 0;
			try {
				month = format.parse(scheduleDate).getMonth() + 1;
				day = format.parse(scheduleDate).getDate();
			} catch (ParseException e) {
				content = content.replace("@scheduleDate", "");
			}
			content = content.replace("@scheduleDate", month + "月" + day + "日");
		} else {
			content = content.replace("@scheduleDate", "");
		}

		if (!StringUtil.isEmpty(timeFlag)) {
			if (timeFlag.equals("1")) {
				content = content.replace("@timeFlag", "上午");
			} else {
				content = content.replace("@timeFlag", "下午");

			}

		} else {
			content = content.replace("@timeFlag", "");
		}

		if (!StringUtil.isEmpty(serialNum)) {

			content = content.replace("@serialNum", serialNum + "号");
		} else {
			content = content.replace("@serialNum", "");
		}

		if (!StringUtil.isEmpty(url)) {
			content = content.replace("@url", url);
		} else {
			content = content.replace("@url", "");
		}

		return content;
	}

	/**
	 * 组装短信内容
	 * 
	 * @param map
	 * @return
	 */
	public static String generatorMessageContentEx(Map<String, Object> map, String msg1) {
		String content = msg1;
		String deptName = (String) map.get("DEPTNAME");
		String doctorName = (String) map.get("DOCTORNAME");
		String scheduleDate = (String) map.get("SCHEDULEDATE");
		String timeFlag = (String) map.get("TIMEFLAG");
		String serialNum = (String) map.get("SERIALNUM");
		String beginTime = (String) map.get("BEGINTIME");
		String endTime = (String) map.get("ENDTIME");
		deptName = CharacterEncodingUtil.isotogb(deptName);
		doctorName = CharacterEncodingUtil.isotogb(doctorName);
		String patname = (String) map.get("PATNAME");
		patname = CharacterEncodingUtil.isotogb(patname);
		String patindex = (String) map.get("PATINDEX");

		if (!StringUtil.isEmpty(deptName)) {
			content = content.replace("@deptName", deptName);
		} else {
			content = content.replace("@deptName", "");
		}

		if (!StringUtil.isEmpty(doctorName)) {
			content = content.replace("@doctorName", doctorName + "医生");
		} else {
			content = content.replace("@doctorName", "");
		}

		if (!StringUtil.isEmpty(scheduleDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			int month = 0;
			int day = 0;
			try {
				month = format.parse(scheduleDate).getMonth() + 1;
				day = format.parse(scheduleDate).getDate();
			} catch (ParseException e) {
				content = content.replace("@scheduleDate", "");
			}
			content = content.replace("@scheduleDate", month + "月" + day + "日");
		} else {
			content = content.replace("@scheduleDate", "");
		}

		if (!StringUtil.isEmpty(timeFlag)) {
			if (timeFlag.equals("1")) {
				content = content.replace("@timeFlag", "上午");
			} else {
				content = content.replace("@timeFlag", "下午");

			}

		} else {
			content = content.replace("@timeFlag", "");
		}

		if (!StringUtil.isEmpty(serialNum)) {
			content = content.replace("@serialNum", serialNum + "号");
		} else {
			content = content.replace("@serialNum", "");
		}
		if (!StringUtil.isEmpty(beginTime) && !StringUtil.isEmpty(endTime)) {
			content = content.replace("@time", beginTime.substring(0,5) + "-" + endTime.substring(0,5));
		} else {
			content = content.replace("@time", "");
		}
		if (!StringUtil.isEmpty(patname)) {
			content = content.replace("@patname", patname);
		} else {
			content = content.replace("@patname", "");
		}
		if (!StringUtil.isEmpty(patindex)) {
			content = content.replace("@patindex", patindex);
		} else {
			content = content.replace("@patindex", "");
		}
		return content;
	}

	private static String getRequestParm(HttpServletRequest request, String name) {
		if (StringUtil.isEmpty(request.getParameter(name))) {
			return "";
		} else {
			return request.getParameter(name);
		}
	}

}
