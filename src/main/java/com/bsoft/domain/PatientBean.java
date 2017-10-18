package com.bsoft.domain;

/**
 * 创建病人主索引
 * 
 * @author admin
 *
 */
public class PatientBean {
	private String patIDType;
	private String patID;
	private String patName;
	private String patGender;
	private String patTel;
	private String patAddress;
	private String patAge;

	public PatientBean() {
		super();
	}

	public String getPatIDType() {
		return patIDType;
	}

	public void setPatIDType(String patIDType) {
		this.patIDType = patIDType;
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

	public String getPatGender() {
		return patGender;
	}

	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}

	public String getPatTel() {
		return patTel;
	}

	public void setPatTel(String patTel) {
		this.patTel = patTel;
	}

	public String getPatAddress() {
		return patAddress;
	}

	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
	}

	public String getPatAge() {
		return patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}

	@Override
	public String toString() {
		return "Patientbean [patIDType=" + patIDType + ", patID=" + patID + ", patName=" + patName + ", patGender="
				+ patGender + ", patTel=" + patTel + ", patAddress=" + patAddress + ", patAge=" + patAge + "]";
	}

}
