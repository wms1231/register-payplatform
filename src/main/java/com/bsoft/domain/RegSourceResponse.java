package com.bsoft.domain;

import java.util.List;

public class RegSourceResponse {

	private String deptCode;
	private String deptName;
	//添加科室类别
	private String deptType;
	private List<Regsourcefordep> Regsourcefordep;

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
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

	public List<Regsourcefordep> getRegsourcefordep() {
		return Regsourcefordep;
	}

	public void setRegsourcefordep(List<Regsourcefordep> regsourcefordep) {
		Regsourcefordep = regsourcefordep;
	}

	public void addRegsourcefordep(Regsourcefordep regsourcefordep) {
		Regsourcefordep.add(regsourcefordep);
	}

}
