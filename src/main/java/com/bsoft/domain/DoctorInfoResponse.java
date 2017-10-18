package com.bsoft.domain;

/**
 * 模糊查询医生信息返回结构
 * 
 * @author admin
 *
 */
public class DoctorInfoResponse {
	private String parentdeptCode;
	private String parentdeptName;
	private String doctorCode;
	private String doctorName;
	private String doctorTitle;
	

	public String getParentdeptCode() {
		return parentdeptCode;
	}

	public void setParentdeptCode(String parentdeptCode) {
		this.parentdeptCode = parentdeptCode;
	}

	public String getParentdeptName() {
		return parentdeptName;
	}

	public void setParentdeptName(String parentdeptName) {
		this.parentdeptName = parentdeptName;
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

	public String getDoctorTitle() {
		return doctorTitle;
	}

	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}

}
