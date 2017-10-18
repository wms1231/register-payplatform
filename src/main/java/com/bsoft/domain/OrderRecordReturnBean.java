package com.bsoft.domain;

/**
 * 预约记录返回 新
 * 
 * @author admin
 *
 */
public class OrderRecordReturnBean {

	private String hisOrdNum;
	private String patIndex;
	private String patID;
	private String patName;
	private String regChannel;
	private String regChannelDesc;
	private String payChannel;
	private String payChannelDesc;
	private String isPaid;
	private String status;
	private String deptCode;
	private String deptName;
	private String doctorCode;
	private String doctorName;
	private String scheduleDate;
	private String timeFlag;
	private String beginTime;
	private String endTime;
	private String regFee;
	private String serialNum;
	private String phonenum;
	private String appointmentTime;
	private String parentDeptCode;
	private String parentDeptName;

	// 新增预约人和取消人
	private String register;
	private String refunder;

	private String registtime;
	private String refundtime;

	public OrderRecordReturnBean() {
		super();
	}

	public String getRegisttime() {
		return registtime;
	}

	public void setRegisttime(String registtime) {
		this.registtime = registtime;
	}

	public String getRefundtime() {
		return refundtime;
	}

	public void setRefundtime(String refundtime) {
		this.refundtime = refundtime;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getRefunder() {
		return refunder;
	}

	public void setRefunder(String refunder) {
		this.refunder = refunder;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getHisOrdNum() {
		return hisOrdNum;
	}

	public void setHisOrdNum(String hisOrdNum) {
		this.hisOrdNum = hisOrdNum;
	}

	public String getPatIndex() {
		return patIndex;
	}

	public void setPatIndex(String patIndex) {
		this.patIndex = patIndex;
	}

	public String getPatID() {
		return patID;
	}

	public void setPatID(String patID) {
		this.patID = patID;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getRegChannel() {
		return regChannel;
	}

	public void setRegChannel(String regChannel) {
		this.regChannel = regChannel;
	}

	public String getRegChannelDesc() {
		return regChannelDesc;
	}

	public void setRegChannelDesc(String regChannelDesc) {
		this.regChannelDesc = regChannelDesc;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getPayChannelDesc() {
		return payChannelDesc;
	}

	public void setPayChannelDesc(String payChannelDesc) {
		this.payChannelDesc = payChannelDesc;
	}

	public String getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRegFee() {
		return regFee;
	}

	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getParentDeptCode() {
		return parentDeptCode;
	}

	public void setParentDeptCode(String parentDeptCode) {
		this.parentDeptCode = parentDeptCode;
	}

	public String getParentDeptName() {
		return parentDeptName;
	}

	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}

	@Override
	public String toString() {
		return "OrderRecordReturnBean [hisOrdNum=" + hisOrdNum + ", patIndex=" + patIndex + ", patID=" + patID
				+ ", patName=" + patName + ", regChannel=" + regChannel + ", regChannelDesc=" + regChannelDesc
				+ ", payChannel=" + payChannel + ", payChannelDesc=" + payChannelDesc + ", isPaid=" + isPaid
				+ ", status=" + status + ", deptCode=" + deptCode + ", deptName=" + deptName + ", doctorCode="
				+ doctorCode + ", doctorName=" + doctorName + ", scheduleDate=" + scheduleDate + ", timeFlag="
				+ timeFlag + ", beginTime=" + beginTime + ", endTime=" + endTime + ", regFee=" + regFee + ", serialNum="
				+ serialNum + ", phonenum=" + phonenum + "]";
	}

}
