package com.bsoft.register.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bsoft.domain.DepartmentReturnBean;
import com.bsoft.domain.DeptReturnBean;
import com.bsoft.domain.DoctorReturnBean;
import com.bsoft.domain.HeadBean;
import com.bsoft.domain.NotificationReturnBean;
import com.bsoft.domain.OrderRecordReturnBean;
import com.bsoft.domain.OrderSourceReturnBean;
import com.bsoft.domain.PatientIndexReturnBean;
import com.bsoft.domain.PatientReturnBean;
import com.bsoft.domain.RegOrderReturnBean;
import com.bsoft.factory.BeanService;
import com.bsoft.register.service.AppointedService;
import com.bsoft.register.service.RegisterService;
import com.bsoft.register.service.ReturnService;
import com.bsoft.support.service.ICommonService;
import com.bsoft.tools.DateFormatUtils;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.IdcardValidator;
import com.bsoft.tools.RequestDataUtil;

/**
 * 提供预约挂号相关服务
 * 
 * @author wms1231
 *
 */
@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private AppointedService appointedService;
	@Autowired
	private ReturnService returnService;

	@Autowired
	private ICommonService commonService;

	private static Logger logger = Logger.getLogger(RegisterServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public String createPatientInfo(String patIDType, String patID, String patName, String patGender, String patTel,
			String patAge) {

		List<PatientReturnBean> list = new ArrayList<PatientReturnBean>();

		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");

		// 如果是身份证类型，需要校验，并且获取它的性別和出生日期
		if (patIDType.equals("1")) {
			boolean value = IdcardValidator.isValidatedAllIdcard(patID);
			if (!value) {
				json.put("code", -1);
				json.put("msg", "身份证校验失败");
				return json.toJSONString();
			} else {
				// 获取性别
				if (patID.length() < 18) {
					json.put("code", -1);
					json.put("msg", "不支持第一代身份证");
					return json.toJSONString();
				}
				patGender = patID.substring(16, 17);

				// 获取出生日期
				String birthday = patID.substring(6, 14);
				patAge = birthday.substring(0, 4) + "-" + birthday.substring(4, 6) + "-" + birthday.substring(6, 8);
			}
		}
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.createPatientInfo(patIDType, patID, patName, patGender, patTel, "", patAge);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}

			return json.toJSONString();
		}
		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
			return json.toJSONString();
		} else {
			list = (List<PatientReturnBean>) bshisservice.getData();
			json.put("data", list);
		}

		return json.toJSONString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getDepInfoWeb(String parentdeptCode, String deptCode) {
		List<DeptReturnBean> list = new ArrayList<DeptReturnBean>();

		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");

		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.queryDeptInfo(deptCode, parentdeptCode);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}

			return json.toJSONString();
		}
		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		} else {
			list = (List<DeptReturnBean>) bshisservice.getData();
			json.put("data", returnService.getDeptInfoForWeb(list));

		}

		return json.toJSONString();

	}

	@SuppressWarnings("unchecked")
	@Override
	public String getDepInfo(HttpServletRequest request, HttpServletResponse response) {
		List<DepartmentReturnBean> list = new ArrayList<DepartmentReturnBean>();

		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");

		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.queryDept();
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}

			return json.toJSONString();
		}
		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		} else {
			list = (List<DepartmentReturnBean>) bshisservice.getData();
			json.put("data", returnService.getDeptInfo(list));

		}

		return json.toJSONString();

	}

	@SuppressWarnings("unchecked")
	@Override
	public String getHisOrderInfo(String deptCode, String parentDeptCode, String doctorCode) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");

		List<String> week = DateUtils.getweek(new Date());
		String beginDate = week.get(0);
		String endDate = week.get(week.size() - 1);
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.appointmentRegOrderSourceQuery(beginDate, endDate, deptCode, parentDeptCode,
					doctorCode);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}

			return json.toJSONString();
		}
		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		} else {
			List<OrderSourceReturnBean> list = (List<OrderSourceReturnBean>) bshisservice.getData();
			List<OrderSourceReturnBean> orderList = list.stream().filter(o -> !"5".equals(o.getDeptType()))
					.collect(Collectors.toList());
			json.put("data", returnService.getRegSourceResponse(orderList));
		}
		return json.toJSONString();

	}

	@SuppressWarnings("unchecked")
	@Override
	public String getScriptOrderInfo(String deptCode, String parentDeptCode, String doctorCode) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");

		List<String> week = DateUtils.getweek(new Date());
		String beginDate = week.get(0);
		String endDate = week.get(week.size() - 1);
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.appointmentRegOrderSourceQuery(beginDate, endDate, deptCode, parentDeptCode,
					doctorCode);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}

			return json.toJSONString();
		}
		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		} else {
			List<OrderSourceReturnBean> list = (List<OrderSourceReturnBean>) bshisservice.getData();
			// java8 代码 过滤集合对象
			List<OrderSourceReturnBean> orderList = list.stream().filter(o -> "5".equals(o.getDeptType()))
					.collect(Collectors.toList());
			json.put("data", returnService.getRegSourceResponse(orderList));
		}

		return json.toJSONString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getDocInfo(String doctorName) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.fuzzyQueryDoctor(doctorName);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}
			
			return json.toJSONString();
		}
		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		} else {
			// 保留原始集合，以便需求变动
			List<DoctorReturnBean> list = (List<DoctorReturnBean>) bshisservice.getData();

			// 获得普通预约列表
			List<DoctorReturnBean> orderList = list.stream().filter(e -> !"5".equals(e.getDeptType()))
					.collect(Collectors.toList());
			 
			//进一步判断是否有排班
			//List<DoctorReturnBean> finalOrderList = new ArrayList<DoctorReturnBean>();
			// rm
			/*for (DoctorReturnBean doctorReturnItem : orderList) {
				List<OrderSourceReturnBean> queryDeptOrderList = queryDeptOrder(null,
						doctorReturnItem.getParentDeptCode(), doctorReturnItem.getDoctorCode());

				if (queryDeptOrderList != null && queryDeptOrderList.size() != 0) {
					// 预约是否有普通排班
					List<OrderSourceReturnBean> tempOrderList = queryDeptOrderList.stream()
							.filter(r -> !"5".equals(r.getDeptType())).collect(Collectors.toList());
					if (tempOrderList != null && tempOrderList.size() != 0) {
						finalOrderList.add(doctorReturnItem);
					}
				}
			}*/
			
			json.put("data", orderList);

		}
		return json.toJSONString();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public String getCreamDeptSearch(String doctorName) {
		List<DoctorReturnBean> list = new ArrayList<DoctorReturnBean>();
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.fuzzyQueryDoctor(doctorName);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}

			return json.toJSONString();
		}
		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		} else {
			list = (List<DoctorReturnBean>) bshisservice.getData();
			// 获得膏方列表
			List<DoctorReturnBean> creamList = list.stream().filter(e -> "5".equals(e.getDeptType()))
					.collect(Collectors.toList());
			/*List<DoctorReturnBean> finalCreamList = new ArrayList<DoctorReturnBean>();
			// lfm
			for (DoctorReturnBean doctorReturnBean : creamList) {
				List<OrderSourceReturnBean> queryDeptOrderList = queryDeptOrder(null,
						doctorReturnBean.getParentDeptCode(), doctorReturnBean.getDoctorCode());

				if (queryDeptOrderList != null && queryDeptOrderList.size() != 0) {
					// 预约是否有膏方排班
					List<OrderSourceReturnBean> tempCreamList = queryDeptOrderList.stream()
							.filter(r -> "5".equals(r.getDeptType())).collect(Collectors.toList());
					if (tempCreamList != null && tempCreamList.size() != 0) {
						finalCreamList.add(doctorReturnBean);
					}
				}
			}*/
			json.put("data", creamList);
		}

		return json.toJSONString();
	}

	/**
	 * 查询科室排班情况
	 * 
	 * @param deptCode
	 * @param parentDeptCode
	 * @param doctorCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderSourceReturnBean> queryDeptOrder(String deptCode, String parentDeptCode, String doctorCode) {
		List<String> week = DateUtils.getweek(new Date());
		String beginDate = week.get(0);
		String endDate = week.get(week.size() - 1);
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.appointmentRegOrderSourceQuery(beginDate, endDate, deptCode, parentDeptCode,
					doctorCode);
		} catch (Exception e) {
			return new ArrayList<OrderSourceReturnBean>();
		}
		return (List<OrderSourceReturnBean>) bshisservice.getData();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String hisRegister(String hisOrdNum, String phonenum, String patIndex, String regChannel) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");
		// 预约
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.appointmentRegOrder(hisOrdNum, new Date().getTime() + "", patIndex,
					phonenum, regChannel);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}
			
			return json.toJSONString();
		}
		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		} else {
			List<RegOrderReturnBean> listregorder = (List<RegOrderReturnBean>) bshisservice.getData();
			json.put("data", listregorder);
		}
		return json.toJSONString();
	}

	@Override
	public String querypatientinfo(String cardtype, String cardNo) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");
		// 如果是身份证类型，需要校验并且获取他的性别和出生日期
		if (cardtype.equals("1")) {
			boolean value = IdcardValidator.isValidatedAllIdcard(cardNo);
			if (!value) {
				json.put("code", -1);
				json.put("msg", "身份证校验失败");
				return json.toJSONString();
			}

		}
		// 获取病员索引
		PatientIndexReturnBean patientIndexReturnBean = null;
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.queryPatientIndex(cardNo);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}
			return json.toJSONString();
		}

		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		} else {
			patientIndexReturnBean = (PatientIndexReturnBean) bshisservice.getData().get(0);
			json.put("data", patientIndexReturnBean);
		}
		return json.toJSONString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String cancelhisRegister(String hisOrdNum, String patIndex, String serialNum, String cancelReason) {

		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");
		// 预约
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.appointmentRegOrderCancel(hisOrdNum, null, patIndex, serialNum,
					cancelReason);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}
			return json.toJSONString();
		}

		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		} else {
			List<RegOrderReturnBean> listregorder = (List<RegOrderReturnBean>) bshisservice.getData();
			json.put("data", listregorder);
		}
		return json.toJSONString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String orderRecord(String scheduleDate, String parentdeptCode, String deptCode, String doctorCode,
			String patIndex) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.getOrderRecordList(scheduleDate, parentdeptCode, deptCode, doctorCode,
					patIndex);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}
			return json.toJSONString();
		}

		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
			return json.toJSONString();
		} else {
			// 保留原始集合
			List<OrderRecordReturnBean> recordList = new ArrayList<>();
			List<OrderRecordReturnBean> recordSourceList = (List<OrderRecordReturnBean>) bshisservice.getData();

			for (OrderRecordReturnBean record : recordSourceList) {
				if (StringUtils.isBlank(record.getHisOrdNum())) {
					record.setRegister("");
					record.setRefunder("");
				} else {
					Map<String, Object> param = RequestDataUtil.getMapByInputParam(Arrays.asList("HOSPNO"),
							Arrays.asList(record.getHisOrdNum()));
					Map<String, Object> map = commonService.selectOne("msginfo.queryOprator", null, param);
					record.setRegister(RequestDataUtil.getValueForKey(map, "REGISTER"));
					record.setRegisttime(RequestDataUtil.getValueForKey(map, "REGISTTIME"));

					record.setRefunder(RequestDataUtil.getValueForKey(map, "REFUNDER"));
					record.setRefundtime(RequestDataUtil.getValueForKey(map, "REFUNDTIME"));
				}

				recordList.add(record);
			}

			json.put("data", recordList);
		}
		return json.toJSONString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String notification(String hisOrdNum, String payMode, String payAmt, String payFlag) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");
		// 查询
		String payTime = DateFormatUtils.format(new Date(), DateFormatUtils.DATE_TIME_PATTERN);
		if (payMode.equals("1")) {
			payMode = "ZFBPay";
		} else if (payMode.equals("2")) {
			payMode = "WXPay";
		} else {
			payMode = "YFK";
		}
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.Notification(hisOrdNum, null, payMode, payAmt, hisOrdNum, payTime, payFlag);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}
			return json.toJSONString();
		}

		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		} else {
			List<NotificationReturnBean> listregorder = (List<NotificationReturnBean>) bshisservice.getData();
			json.put("data", listregorder);
		}
		return json.toJSONString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String refundnotification(String hisOrdNum, String payAmt, String refunder, String cancelTime) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");
		String payTime = DateFormatUtils.format(new Date(), DateFormatUtils.DATE_TIME_PATTERN);
		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.RefundNotification(hisOrdNum, payAmt, payTime);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}
			return json.toJSONString();
		}

		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
			return json.toJSONString();
		} else {
			// 退款通知成功
			Map<String, Object> param = RequestDataUtil.getMapByInputParam(
					Arrays.asList("refunder", "refundtime", "hospNo"), Arrays.asList(refunder, cancelTime, hisOrdNum));
			if (commonService.update("msginfo.updateRefunder", null, param) != 1) {
				json.put("code", -1);
				json.put("msg", "退款受理人保存失败");
				return json.toJSONString();
			}

			List<NotificationReturnBean> listregorder = (List<NotificationReturnBean>) bshisservice.getData();
			json.put("data", listregorder);
		}
		return json.toJSONString();
	}

	@Override
	public String getServertime() {
		return Long.toString(new Date().getTime());
	}



}
