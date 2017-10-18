package com.bsoft.domain;

import java.util.Date;

public class HeadBean {
	private String serviceCode;
	private String serviceDesc;
	private String clientRunningNum;
	private String partnerID;
	private String clientType;
	private String terminalCode;

	private String userID;
	private String timeStamp;
	private String servicePassword;
	private String serverRunningNum;
	private String resultCode;
	private String resultMessage;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getClientRunningNum() {
		return clientRunningNum;
	}

	public void setClientRunningNum(String clientRunningNum) {
		this.clientRunningNum = clientRunningNum;
	}

	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getServicePassword() {
		return servicePassword;
	}

	public void setServicePassword(String servicePassword) {
		this.servicePassword = servicePassword;
	}

	public String getServerRunningNum() {
		return serverRunningNum;
	}

	public void setServerRunningNum(String serverRunningNum) {
		this.serverRunningNum = serverRunningNum;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	@Override
	public String toString() {
		return "HeadBean [serviceCode=" + serviceCode + ", serviceDesc=" + serviceDesc + ", clientRunningNum="
				+ clientRunningNum + ", partnerID=" + partnerID + ", clientType=" + clientType + ", terminalCode="
				+ terminalCode + ", userID=" + userID + ", timeStamp=" + timeStamp + ", servicePassword="
				+ servicePassword + ", serverRunningNum=" + serverRunningNum + ", resultCode=" + resultCode
				+ ", resultMessage=" + resultMessage + "]";
	}

}
