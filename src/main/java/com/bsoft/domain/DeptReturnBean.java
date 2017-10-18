package com.bsoft.domain;

public class DeptReturnBean {
	private String deptCode;
	private String deptName;
	private String hasChild;
	private String parentDeptCode;
	private String parentDeptName;

	private String deptDescription;
	private String deptLocation;
	private String pydm;
	


	public String getPydm() {
		return pydm;
	}

	public void setPydm(String pydm) {
		this.pydm = pydm;
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

	public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	public String getParentDeptCode() {
		return parentDeptCode;
	}

	public void setParentDeptCode(String parentDeptCode) {
		this.parentDeptCode = parentDeptCode;
	}

	public String getDeptDescription() {
		return deptDescription;
	}

	public void setDeptDescription(String deptDescription) {
		this.deptDescription = deptDescription;
	}

	public String getDeptLocation() {
		return deptLocation;
	}

	public void setDeptLocation(String deptLocation) {
		this.deptLocation = deptLocation;
	}

	public String getParentDeptName() {
		return parentDeptName;
	}

	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}

	@Override
	public String toString() {
		return "DeptReturnBean [deptCode=" + deptCode + ", deptName=" + deptName + ", hasChild=" + hasChild
				+ ", parentDeptCode=" + parentDeptCode + ", parentDeptName=" + parentDeptName + ", deptDescription="
				+ deptDescription + ", deptLocation=" + deptLocation + ", pydm=" + pydm + "]";
	}

}
