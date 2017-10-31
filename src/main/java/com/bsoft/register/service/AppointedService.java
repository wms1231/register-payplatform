package com.bsoft.register.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;

import com.bsoft.factory.BeanService;

/**
 * 提供预约挂号相关服务
 * 
 * @author wms1231
 *
 */
public interface AppointedService {

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
	 * @return
	 * @throws Exception
	 */
	public BeanService createPatientInfo(String patIDType, String patID, String patName, String patGender,
			String patTel, String patAddress, String patAge);

	/**
	 * 查询病人主索引
	 * 
	 * @param patCardNo
	 * @return
	 * @throws Exception
	 */
	public BeanService queryPatientIndex(String patCardNo);

	/**
	 * 查询科室信息
	 * 
	 * @param deptCode
	 * @param parentdeptCode
	 * @return
	 * @throws Exception
	 */
	public BeanService queryDeptInfo(String deptCode, String parentdeptCode);

	/**
	 * 大科室查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public BeanService queryDept();

	/**
	 * 医生信息查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public BeanService queryDoctorInfo(String parentdeptCode, String deptCode, String doctorCode);

	
	/**
	 * 模糊查询医生信息
	 * 
	 * @param doctorName
	 * @return
	 * @throws Exception
	 */
	public BeanService fuzzyQueryDoctor(String doctorName);

	/**
	 * 预约挂号
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */

	public BeanService appointmentRegOrder(String hisOrdNum, String psOrdNum, String patIndex, String phonenum,
			String regChannel);

	/**
	 * 挂号科室预约号源信息查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public BeanService appointmentRegOrderSourceQuery(String beginDate, String endDate, String deptCode,
			String parentDeptCode, String doctorCode);

	/**
	 * 取消预约挂号
	 * 
	 * @return
	 * @throws Exception
	 */
	public BeanService appointmentRegOrderCancel(String hisOrdNum, String psOrdNum, String patIndex, String srialNum,
			String cancelReason);

	/**
	 * 预约挂号记录查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public BeanService appointmentRegRecordQuery(String patIndex, String hisOrdNum);

	/**
	 * 查询预约记录
	 * 
	 * @param scheduleDate
	 * @param parentdeptCode
	 * @param deptCode
	 * @param doctorCode
	 * @param patIndex
	 * @return
	 * @throws Exception
	 */
	public BeanService getOrderRecordList(String scheduleDate, String parentdeptCode, String deptCode,
			String doctorCode, String patIndex);

	/**
	 * 支付完成后通知存储过程
	 * 
	 * @param hisOrdNum
	 * @param psOrdNum
	 * @param payMode
	 * @param payAmt
	 * @param agtOrdNum
	 * @param patAddress
	 * @return
	 * @throws Exception
	 */
	public BeanService Notification(String hisOrdNum, String psOrdNum, String payMode, String payAmt, String agtOrdNum,
			String payTime, String payFlag);

	/**
	 * 退款通知
	 * 
	 * @param hisOrdNum
	 * @param payAmt
	 * @param payTime
	 * @return
	 * @throws Exception
	 */
	public BeanService RefundNotification(String hisOrdNum, String payAmt, String payTime);

	/**
	 * 保存短信
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public int saveMessinfo(HttpServletRequest request, String url);

	/**
	 * 将相短信信息保存到数据库，待用户支付完后还需要再次短信通知
	 * 
	 * @param request
	 */
	public Map<String, Object> getMessinfo(HttpServletRequest request);

	/**
	 * 更新短信
	 * 
	 * @param request
	 * @return
	 */
	public int updateMessinfo(HttpServletRequest request);

	/**
	 * 调用存储过程
	 * 
	 * @param beginTime
	 * @param requestStr
	 * @return
	 * @throws DocumentException
	 */
	public String invoke(String requestStr);

}
