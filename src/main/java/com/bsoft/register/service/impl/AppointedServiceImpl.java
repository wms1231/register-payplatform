package com.bsoft.register.service.impl;

import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsoft.constant.CommonConst;
import com.bsoft.domain.HeadBean;
import com.bsoft.exception.WebException;
import com.bsoft.factory.AppointedHander;
import com.bsoft.factory.BeanService;
import com.bsoft.register.service.AppointedService;
import com.bsoft.support.service.ICommonService;
import com.bsoft.support.utils.CharacterEncodingUtil;
import com.bsoft.tools.CharacterEncodeUtil;
import com.bsoft.tools.DateFormatUtils;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.RequestDataUtil;
import com.bsoft.tools.UserUtils;

/**
 * 提供预约挂号相关服务
 * 
 * @author wms1231
 *
 */
@Service
public class AppointedServiceImpl implements AppointedService {
	private static Logger logger = Logger.getLogger(AppointedServiceImpl.class);
	@Autowired
	private ICommonService commonService;

	private static final String EMPTY_VALUE = "";

	/**
	 * 创建主索引
	 * 
	 * @param patIDType
	 * @param patID
	 * @param patName
	 * @param patGender
	 * @param patTel
	 * @param patAddress
	 * @param patAge
	 * @return @
	 */
	public BeanService createPatientInfo(String patIDType, String patID, String patName, String patGender,
			String patTel, String patAddress, String patAge) {
		Map<String, Object> map = RequestDataUtil.getMapByInputParam(
				Arrays.asList("patIDType", "patID", "patName", "patGender", "patTel", "patAddress", "patAge"),
				Arrays.asList(patIDType, patID, patName, patGender, patTel, patAddress, patAge));
		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_CREATE_PATIENT);

		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_CREATE_PATIENT);
	}

	/**
	 * 查询病人主索引
	 * 
	 * @param patCardNo
	 * @return @
	 */
	public BeanService queryPatientIndex(String patCardNo) {
		Map<String, Object> map = RequestDataUtil.getMapByInputParam(Arrays.asList("patCardNo"),
				Arrays.asList(patCardNo));
		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_PATIENTINDEX);

		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_PATIENTINDEX);
	}

	/**
	 * 查询科室信息
	 * 
	 * @param deptCode
	 * @param parentdeptCode
	 * @return @
	 */
	public BeanService queryDeptInfo(String deptCode, String parentdeptCode) {
		Map<String, Object> map = RequestDataUtil.getMapByInputParam(Arrays.asList("deptCode", "parentdeptCode"),
				Arrays.asList(deptCode, parentdeptCode));
		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_DEPTINFO);

		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_DEPTINFO);
	}

	/**
	 * 大科室查询
	 * 
	 * @return @
	 */
	public BeanService queryDept() {
		Map<String, Object> map = RequestDataUtil.getMapByInputParam(null, null);
		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_DEPT);
		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_DEPT);
	}

	/**
	 * 医生信息查询
	 * 
	 * @return @
	 */
	public BeanService queryDoctorInfo(String parentdeptCode, String deptCode, String doctorCode) {
		Map<String, Object> map = RequestDataUtil.getMapByInputParam(
				Arrays.asList("parentdeptCode", "deptCode", "doctorCode"),
				Arrays.asList(parentdeptCode, deptCode, doctorCode));
		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_DOCTORINFO);

		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_DOCTORINFO);
	}

	/**
	 * 模糊查询医生信息
	 * 
	 * @param doctorName
	 * @return @
	 */
	public BeanService fuzzyQueryDoctor(String doctorName) {
		Map<String, Object> map = RequestDataUtil.getMapByInputParam(Arrays.asList("doctorName"),
				Arrays.asList(doctorName));
		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_DOCTOR);

		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_DOCTOR);
	}

	/**
	 * 预约挂号
	 * 
	 * @param request
	 * @return @
	 */

	public BeanService appointmentRegOrder(String hisOrdNum, String psOrdNum, String patIndex, String phonenum,
			String regChannel) {
		Map<String, Object> map = RequestDataUtil.getMapByInputParam(
				Arrays.asList("hisOrdNum", "psOrdNum", "patIndex", "phonenum", "regChannel"),
				Arrays.asList(hisOrdNum, psOrdNum, patIndex, phonenum, regChannel));

		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_REGORDER);

		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_REGORDER);
	}

	/**
	 * 挂号科室预约号源信息查询
	 * 
	 * @return @
	 */
	public BeanService appointmentRegOrderSourceQuery(String beginDate, String endDate, String deptCode,
			String parentDeptCode, String doctorCode) {

		Map<String, Object> map = RequestDataUtil.getMapByInputParam(
				Arrays.asList("beginDate", "endDate", "deptCode", "parentDeptCode", "doctorCode"),
				Arrays.asList(beginDate, endDate, deptCode, parentDeptCode, doctorCode));
		String requestStr = AppointedServiceImpl.EMPTY_VALUE;
		if (StringUtils.isNotBlank(deptCode)) {
			requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_ORDERSOURCE);
			return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_ORDERSOURCE);
		}

		requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_ORDERSOURCE_NEW);

		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_ORDERSOURCE_NEW);

	}

	/**
	 * 取消预约挂号
	 * 
	 * @return @
	 */
	public BeanService appointmentRegOrderCancel(String hisOrdNum, String psOrdNum, String patIndex, String srialNum,
			String cancelReason) {

		Map<String, Object> map = RequestDataUtil.getMapByInputParam(
				Arrays.asList("hisOrdNum", "psOrdNum", "patIndex", "srialNum", "cancelReason"),
				Arrays.asList(hisOrdNum, psOrdNum, patIndex, srialNum, cancelReason));

		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_REGORDER_CANCEL);

		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_REGORDER_CANCEL);
	}

	/**
	 * 预约挂号记录查询
	 * 
	 * @return @
	 */
	public BeanService appointmentRegRecordQuery(String patIndex, String hisOrdNum) {
		Map<String, Object> map = RequestDataUtil.getMapByInputParam(Arrays.asList("patIndex", "hisOrdNum"),
				Arrays.asList(patIndex, hisOrdNum));
		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_REGRECORD);
		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_REGRECORD);
	}

	/**
	 * 查询预约记录
	 * 
	 * @param scheduleDate
	 * @param parentdeptCode
	 * @param deptCode
	 * @param doctorCode
	 * @param patIndex
	 * @return @
	 */
	public BeanService getOrderRecordList(String scheduleDate, String parentdeptCode, String deptCode,
			String doctorCode, String patIndex) {
		Map<String, Object> map = RequestDataUtil.getMapByInputParam(
				Arrays.asList("scheduleDate", "parentdeptCode", "deptCode", "doctorCode", "patIndex"),
				Arrays.asList(scheduleDate, parentdeptCode, deptCode, doctorCode, patIndex));
		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_GET_REGORDER);
		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_GET_REGORDER);
	}

	/**
	 * 支付完成后通知存储过程
	 * 
	 * @param hisOrdNum
	 * @param psOrdNum
	 * @param payMode
	 * @param payAmt
	 * @param agtOrdNum
	 * @param patAddress
	 * @return @
	 */
	public BeanService Notification(String hisOrdNum, String psOrdNum, String payMode, String payAmt, String agtOrdNum,
			String payTime, String payFlag) {

		Map<String, Object> map = RequestDataUtil.getMapByInputParam(
				Arrays.asList("hisOrdNum", "psOrdNum", "payMode", "payAmt", "agtOrdNum", "payTime", "payFlag"),
				Arrays.asList(hisOrdNum, psOrdNum, payMode, payAmt, agtOrdNum, payTime, payFlag));
		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_NOTIFICATION);
		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_NOTIFICATION);
	}

	/**
	 * 退款通知
	 * 
	 * @param hisOrdNum
	 * @param payAmt
	 * @param payTime
	 * @return @
	 */
	public BeanService RefundNotification(String hisOrdNum, String payAmt, String payTime) {
		Map<String, Object> map = RequestDataUtil.getMapByInputParam(Arrays.asList("hisOrdNum", "payAmt", "payTime"),
				Arrays.asList(hisOrdNum, payAmt, payTime));
		String requestStr = AppointedHander.buildRequestData(map, CommonConst.SERVICE_CODE_REFUNDNOTIFICATION);

		return getBeanServiceByServiceCode(requestStr, CommonConst.SERVICE_CODE_REFUNDNOTIFICATION);
	}

	/**
	 * 保存短信内容
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public int saveMessinfo(HttpServletRequest request, String url) {
		String deptName = RequestDataUtil.getRequestParmByParameter(request, "deptName");
		String doctorName = RequestDataUtil.getRequestParmByParameter(request, "doctorName");
		String scheduleDate = RequestDataUtil.getRequestParmByParameter(request, "scheduleDate");
		// timeFlag 1 上午 2 下午
		String timeFlag = RequestDataUtil.getRequestParmByParameter(request, "timeFlag");
		String serialNum = RequestDataUtil.getRequestParmByParameter(request, "serialNum");
		String hospNo = RequestDataUtil.getRequestParmByParameter(request, "hospNo");
		String phonenum = RequestDataUtil.getRequestParmByParameter(request, "phonenum");
		String beginTime = RequestDataUtil.getRequestParmByParameter(request, "beginTime");
		String endTime = RequestDataUtil.getRequestParmByParameter(request, "endTime");
		String patName = RequestDataUtil.getRequestParmByParameter(request, "patName");
		String patIndex = RequestDataUtil.getRequestParmByParameter(request, "patIndex");

		deptName = CharacterEncodingUtil.gbtoiso(deptName);
		doctorName = CharacterEncodingUtil.gbtoiso(doctorName);
		patName = CharacterEncodingUtil.gbtoiso(patName);

		Map<String, Object> param = new HashMap<>();
		param.put("DEPTNAME", deptName);
		param.put("DOCTORNAME", doctorName);
		param.put("SCHEDULEDATE", scheduleDate);
		param.put("TIMEFLAG", timeFlag);
		param.put("SERIALNUM", serialNum);
		param.put("HOSPNO", hospNo);
		param.put("PHONENUM", phonenum);
		param.put("BEGINTIME", beginTime);
		param.put("ENDTIME", endTime);
		param.put("PATNAME", patName);
		param.put("PATINDEX", patIndex);
		param.put("URL", url);
		// 添加预约人以及预约时间
		param.put("REGISTER", UserUtils.getCurrentUser(request));
		param.put("REGISTTIME", DateFormatUtils.format(new Date(), DateFormatUtils.DATE_TIME_PATTERN));
		param.put("REFUNDER", "");
		param.put("REFUNDTIME", "");
		return commonService.insert("msginfo.saveMessageInfo", null, param);
	}

	/**
	 * 将相短信信息保存到数据库，待用户支付完后还需要再次短信通知
	 * 
	 * @param request
	 */
	public Map<String, Object> getMessinfo(HttpServletRequest request) {
		String hospNo = RequestDataUtil.getRequestParmByParameter(request, "hospNo");
		Map<String, Object> param = RequestDataUtil.getMapByInputParam(Arrays.asList("HOSPNO"), Arrays.asList(hospNo));
		return commonService.selectOne("msginfo.getmsginfo", null, param);
	}

	/**
	 * 更新短信
	 * 
	 * @param request
	 * @return
	 */
	public int updateMessinfo(HttpServletRequest request) {
		String hospNo = RequestDataUtil.getRequestParmByParameter(request, "hospNo");
		String phonenum = RequestDataUtil.getRequestParmByParameter(request, "phonenum");
		Map<String, Object> param = RequestDataUtil.getMapByInputParam(Arrays.asList("hospNo", "phonenum"),
				Arrays.asList(hospNo, phonenum));
		return commonService.update("msginfo.updatemsginfo", null, param);
	}

	/**
	 * 获得反参结果
	 * 
	 * @param requestStr
	 * @param serviceCode
	 * @return
	 */
	private BeanService getBeanServiceByServiceCode(String requestStr, String serviceCode) {
		BeanService bshisservice = null;
		try {
			bshisservice = AppointedHander.buildResultData(invoke(requestStr), serviceCode);
		} catch (Exception e) {
			throw new WebException("-1", e.getMessage());
		}
		if (bshisservice != null && bshisservice.getHead() != null) {
			HeadBean bean = bshisservice.getHead();
			if (!bean.getResultCode().equals("0000")) {
				throw new WebException("-1", bean.getResultMessage());
			}
		}

		return bshisservice;
	}

	/**
	 * 调用存储过程
	 * 
	 * @param beginTime
	 * @param requestStr
	 * @return
	 * @throws DocumentException
	 */
	public String invoke(String requestStr) {
		long beginTime = System.currentTimeMillis();
		// 打印信息
		logger.info("=>开始调用存储过程,请求信息为=>" + requestStr + "时间为:" + DateUtils.getCurrentDate_YYYYMMDDHHMMSS() + ",时间标记为["
				+ beginTime + "]");

		Map<String, Object> param = new HashMap<>();
		param.put("input", CharacterEncodeUtil.requestEncode(requestStr));
		param.put("msg", "");
		param.put("output", new Object());
		param.put("proName", CommonConst.SYSTEM_PROCEDURE_PARAMETER);

		try {
			commonService.selectOne("callpro.order", null, param);
		} catch (Exception e) {
			logger.error("调用存储过程异常=>" + e.getMessage());
			throw new WebException("-1", "调用存储过程异常");
		}

		param.put("msg", CharacterEncodeUtil.returnEncode((String) param.get("msg")));
		param.put("output", CharacterEncodeUtil.returnEncode((Blob) param.get("output")));

		String output = RequestDataUtil.getValueForKey(param, "output");
		String msg = RequestDataUtil.getValueForKey(param, "msg");

		String returnMsg = "";
		if (StringUtils.isNotBlank(output)) {
			returnMsg = output;
		} else if (StringUtils.isNotBlank(msg)) {
			returnMsg = msg;
		}
		Date endTime = new Date();
		logger.info("<=结束存储过程调用，返回异常信息=>" + msg + "结束,时间为:" + DateUtils.getCurrentDate_YYYYMMDDHHMMSS() + ",花费了["
				+ (endTime.getTime() - beginTime) + "毫秒]");
		return returnMsg;
	}

}
