package com.bsoft.constant;

public enum OrderTypeEnum {

	ORDER_EFFECTIVE("0", "有效"), ORDER_TIMEOUT("1", "超时 "), ORDER_CANCELLED("2", "被医院取消 "), ORDER_COMPLETED("3",
			"已完成预约 "), ORDER_ACTIVE_CANCELL("4", "患者违约"), ORDER_BREAK("5", "患者违约时");

	private String code = null;
	private String content;

	OrderTypeEnum(String errno, String msg) {
		this.code = errno;
		this.content = msg;
	}

	public String getCode() {
		return code;
	}

	public String getContent() {
		return content;
	}

	public static OrderTypeEnum getEnumByName(String enumName) {
		OrderTypeEnum[] arry = OrderTypeEnum.values();
		for (int i = 0; i < arry.length; i++) {
			if (arry[i].name().equalsIgnoreCase(enumName)) {
				return arry[i];
			}
		}
		return null;
	}

	public static String getEnumValueByName(String enumName) {
		OrderTypeEnum[] arry = OrderTypeEnum.values();
		for (int i = 0; i < arry.length; i++) {
			if (arry[i].name().equalsIgnoreCase(enumName)) {
				return arry[i].getContent();
			}
		}
		return null;
	}

	public static String getEnumKeyByName(String enumName) {
		OrderTypeEnum[] arry = OrderTypeEnum.values();
		for (int i = 0; i < arry.length; i++) {
			if (arry[i].name().equalsIgnoreCase(enumName)) {
				return arry[i].getCode();
			}
		}
		return null;
	}

}