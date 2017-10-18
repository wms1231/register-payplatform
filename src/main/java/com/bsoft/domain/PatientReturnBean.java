package com.bsoft.domain;

/**
 * 創建主索引返回結果
 * @author admin
 *
 */
public class PatientReturnBean {

	private String patIndex;

	public String getPatIndex() {
		return patIndex;
	}

	public void setPatIndex(String patIndex) {
		this.patIndex = patIndex;
	}

	@Override
	public String toString() {
		return "PatientReturnBean [patIndex=" + patIndex + "]";
	}
	
}
