package com.bsoft.domain;


public class PatientIndexReturnBean {
	private String patIndex; 
	private String patName; 
	private String patGender; 
	private String patTel; 
	private String patAddress;
	private String patAge; 
	private String patID; 

	public PatientIndexReturnBean() {
		super();
	}

	public PatientIndexReturnBean(String patIndex, String patName, String patGender, String patTel, String patAddress,
			String patAge, String patID) {
		super();
		this.patIndex = patIndex;
		this.patName = patName;
		this.patGender = patGender;
		this.patTel = patTel;
		this.patAddress = patAddress;
		this.patAge = patAge;
		this.patID = patID;
	}

	public String getPatIndex() {
		return patIndex;
	}

	public void setPatIndex(String patIndex) {
		this.patIndex = patIndex;
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

	public String getPatID() {
		return patID;
	}

	public void setPatID(String patID) {
		this.patID = patID;
	}

}
