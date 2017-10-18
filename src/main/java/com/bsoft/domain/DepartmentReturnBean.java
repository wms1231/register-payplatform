package com.bsoft.domain;

/**
 * 大科室返回
 * @author admin
 *
 */
public class DepartmentReturnBean {
	
	private String parentDeptCode;
	private String parentDeptNam;
	private String deptType;
	public String getParentDeptCode() {
		return parentDeptCode;
	}
	public void setParentDeptCode(String parentDeptCode) {
		this.parentDeptCode = parentDeptCode;
	}
	public String getParentDeptNam() {
		return parentDeptNam;
	}
	public void setParentDeptNam(String parentDeptNam) {
		this.parentDeptNam = parentDeptNam;
	}
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	@Override
	public String toString() {
		return "DepartmentReturnBean [parentDeptCode=" + parentDeptCode + ", parentDeptNam=" + parentDeptNam
				+ ", deptType=" + deptType + "]";
	}
	
	

}
