package com.bsoft.domain;

public class DoctorReturnBean {
	private String parentDeptCode;
	private String parentDeptName;
	private String deptCode;
	private String deptName;
	private String doctorCode;
	private String doctorName;
	private String doctorsKill;
	private String doctorIntrodution;
	private String doctorTitle;
	private String deptType;// 添加科室类别;

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
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

	public String getDoctorsKill() {
		return doctorsKill;
	}

	public void setDoctorsKill(String doctorsKill) {
		this.doctorsKill = doctorsKill;
	}

	public String getDoctorIntrodution() {
		return doctorIntrodution;
	}

	public void setDoctorIntrodution(String doctorIntrodution) {
		this.doctorIntrodution = doctorIntrodution;
	}

	public String getDoctorTitle() {
		return doctorTitle;
	}

	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}

	
}
