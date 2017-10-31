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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsoft.domain.DepartmentReturnBean;
import com.bsoft.domain.DeptReturnBean;
import com.bsoft.domain.DoctorReturnBean;
import com.bsoft.domain.NotificationReturnBean;
import com.bsoft.domain.OrderRecordReturnBean;
import com.bsoft.domain.OrderSourceReturnBean;
import com.bsoft.domain.PatientIndexReturnBean;
import com.bsoft.domain.PatientReturnBean;
import com.bsoft.domain.RegOrderReturnBean;
import com.bsoft.domain.RegSourceResponse;
import com.bsoft.exception.RegisterException;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<PatientReturnBean> createPatientInfo(String patIDType, String patID, String patName, String patGender,
			String patTel, String patAge) {
		// 如果是身份证类型，需要校验，并且获取它的性別和出生日期
		if (patIDType.equals("1")) {
			boolean value = IdcardValidator.isValidatedAllIdcard(patID);
			if (!value) {
				throw new RegisterException("-1", "身份证校验失败");
			} else {
				// 获取性别
				if (patID.length() < 18) {
					throw new RegisterException("-1", "不支持第一代身份证");
				}
				patGender = patID.substring(16, 17);

				// 获取出生日期
				String birthday = patID.substring(6, 14);
				patAge = birthday.substring(0, 4) + "-" + birthday.substring(4, 6) + "-" + birthday.substring(6, 8);
			}
		}
		return (List<PatientReturnBean>) appointedService
				.createPatientInfo(patIDType, patID, patName, patGender, patTel, "", patAge).getData();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<DeptReturnBean>> getDepInfoWeb(String parentdeptCode, String deptCode) {
		BeanService bshisservice = appointedService.queryDeptInfo(deptCode, parentdeptCode);
		List<DeptReturnBean> list = (List<DeptReturnBean>) bshisservice.getData();
		return returnService.getDeptInfoForWeb(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<DepartmentReturnBean>> getDepInfo(HttpServletRequest request,
			HttpServletResponse response) {
		List<DepartmentReturnBean> list = (List<DepartmentReturnBean>) appointedService.queryDept().getData();
		return returnService.getDeptInfo(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegSourceResponse> getHisOrderInfo(String deptCode, String parentDeptCode, String doctorCode) {

		List<String> week = DateUtils.getweek(new Date());
		String beginDate = week.get(0);
		String endDate = week.get(week.size() - 1);
		BeanService bshisservice = appointedService.appointmentRegOrderSourceQuery(beginDate, endDate, deptCode,
				parentDeptCode, doctorCode);

		List<OrderSourceReturnBean> list = (List<OrderSourceReturnBean>) bshisservice.getData();
		List<OrderSourceReturnBean> orderList = list.stream().filter(o -> !"5".equals(o.getDeptType()))
				.collect(Collectors.toList());
		return returnService.getRegSourceResponse(orderList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegSourceResponse> getScriptOrderInfo(String deptCode, String parentDeptCode, String doctorCode) {
		List<String> week = DateUtils.getweek(new Date());
		String beginDate = week.get(0);
		String endDate = week.get(week.size() - 1);
		BeanService bshisservice = appointedService.appointmentRegOrderSourceQuery(beginDate, endDate, deptCode,
				parentDeptCode, doctorCode);
		List<OrderSourceReturnBean> list = (List<OrderSourceReturnBean>) bshisservice.getData();
		// java8 代码 过滤集合对象
		List<OrderSourceReturnBean> orderList = list.stream().filter(o -> "5".equals(o.getDeptType()))
				.collect(Collectors.toList());
		return returnService.getRegSourceResponse(orderList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DoctorReturnBean> getDocInfo(String doctorName) {
		// 保留原始集合，以便需求变动
		List<DoctorReturnBean> list = (List<DoctorReturnBean>) appointedService.fuzzyQueryDoctor(doctorName).getData();
		// 获得普通预约列表
		List<DoctorReturnBean> orderList = list.stream().filter(e -> !"5".equals(e.getDeptType()))
				.collect(Collectors.toList());
		return orderList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DoctorReturnBean> getCreamDeptSearch(String doctorName) {
		List<DoctorReturnBean> list = (List<DoctorReturnBean>) appointedService.fuzzyQueryDoctor(doctorName).getData();
		// 获得膏方列表
		List<DoctorReturnBean> creamList = list.stream().filter(e -> "5".equals(e.getDeptType()))
				.collect(Collectors.toList());
		return creamList;
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
		BeanService bshisservice = appointedService.appointmentRegOrderSourceQuery(beginDate, endDate, deptCode,
				parentDeptCode, doctorCode);
		return (List<OrderSourceReturnBean>) bshisservice.getData();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegOrderReturnBean> hisRegister(String hisOrdNum, String phonenum, String patIndex, String regChannel) {
		BeanService bshisservice = appointedService.appointmentRegOrder(hisOrdNum, new Date().getTime() + "", patIndex,
				phonenum, regChannel);
		return (List<RegOrderReturnBean>) bshisservice.getData();
	}

	@Override
	public PatientIndexReturnBean querypatientinfo(String cardtype, String cardNo) {
		// 如果是身份证类型，需要校验并且获取他的性别和出生日期
		if (cardtype.equals("1")) {
			boolean value = IdcardValidator.isValidatedAllIdcard(cardNo);
			if (!value) {
				throw new RegisterException("-1", "身份证校验失败");
			}
		}
		// 获取病员索引
		BeanService bshisservice = appointedService.queryPatientIndex(cardNo);
		return (PatientIndexReturnBean) bshisservice.getData().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegOrderReturnBean> cancelhisRegister(String hisOrdNum, String patIndex, String serialNum,
			String cancelReason) {
		BeanService bshisservice = appointedService.appointmentRegOrderCancel(hisOrdNum, null, patIndex, serialNum,
				cancelReason);
		return (List<RegOrderReturnBean>) bshisservice.getData();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderRecordReturnBean> orderRecord(String scheduleDate, String parentdeptCode, String deptCode,
			String doctorCode, String patIndex) {

		BeanService bshisservice = appointedService.getOrderRecordList(scheduleDate, parentdeptCode, deptCode,
				doctorCode, patIndex);

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

		return recordList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationReturnBean> notification(String hisOrdNum, String payMode, String payAmt, String payFlag) {
		// 查询
		String payTime = DateFormatUtils.format(new Date(), DateFormatUtils.DATE_TIME_PATTERN);
		if (payMode.equals("1")) {
			payMode = "ZFBPay";
		} else if (payMode.equals("2")) {
			payMode = "WXPay";
		} else {
			payMode = "YFK";
		}
		BeanService bshisservice = appointedService.Notification(hisOrdNum, null, payMode, payAmt, hisOrdNum, payTime,
				payFlag);

		return (List<NotificationReturnBean>) bshisservice.getData();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationReturnBean> refundnotification(String hisOrdNum, String payAmt, String refunder,
			String cancelTime) {

		String payTime = DateFormatUtils.format(new Date(), DateFormatUtils.DATE_TIME_PATTERN);
		BeanService bshisservice = appointedService.RefundNotification(hisOrdNum, payAmt, payTime);
		// 退款通知成功
		Map<String, Object> param = RequestDataUtil.getMapByInputParam(
				Arrays.asList("refunder", "refundtime", "hospNo"), Arrays.asList(refunder, cancelTime, hisOrdNum));
		if (commonService.update("msginfo.updateRefunder", null, param) != 1) {
			throw new RegisterException("-1", "退款受理人保存失败");
		}
		return (List<NotificationReturnBean>) bshisservice.getData();
	}

	@Override
	public String getServertime() {
		return Long.toString(new Date().getTime());
	}

}
