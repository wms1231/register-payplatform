package com.bsoft.domain;

/**
 * 预约记录查询 - 新
 * @author admin
 *
 */
public class OrderRecordBean {
	
	private String scheduleDate;
	private String parentdeptCode;
	private  String deptCode;
	private String doctorCode;
	private String patIndex;
	
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getParentdeptCode() {
		return parentdeptCode;
	}
	public void setParentdeptCode(String parentdeptCode) {
		this.parentdeptCode = parentdeptCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	public String getPatIndex() {
		return patIndex;
	}
	public void setPatIndex(String patIndex) {
		this.patIndex = patIndex;
	}
	
	@Override
	public String toString() {
		return "OrderRecordBean [scheduleDate=" + scheduleDate + ", parentdeptCode=" + parentdeptCode + ", deptCode="
				+ deptCode + ", doctorCode=" + doctorCode + ", patIndex=" + patIndex + "]";
	}

}
