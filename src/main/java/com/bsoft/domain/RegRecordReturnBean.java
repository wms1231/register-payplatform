package com.bsoft.domain;

/**
 * 预约记录返回老接口
 * @author admin
 *
 */
public class RegRecordReturnBean {
	private String hisOrdNum;
	
	private String patIndex;
	private String serialNum;
	
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

	public String getPatIndex() {
		return patIndex;
	}

	public void setPatIndex(String patIndex) {
		this.patIndex = patIndex;
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


	@Override
	public String toString() {
		return "RegRecordReturnBean [hisOrdNum=" + hisOrdNum + ", patIndex=" + patIndex + ", serialNum=" + serialNum
				+ ", regChannel=" + regChannel + ", regChannelDesc=" + regChannelDesc + ", payChannel=" + payChannel
				+ ", payChannelDesc=" + payChannelDesc + ", isPaid=" + isPaid + ", status=" + status + ", deptCode="
				+ deptCode + ", deptName=" + deptName + ", doctorCode=" + doctorCode + ", doctorName=" + doctorName
				+ ", scheduleDate=" + scheduleDate + ", timeFlag=" + timeFlag + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + ", regFee=" + regFee +  "]";
	}

	
}
