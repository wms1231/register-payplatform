package com.bsoft.register.service;

import java.util.List;

import com.bsoft.client.schema.sms.MessageFormat;
import com.bsoft.client.schema.sms.SendMethodType;

public interface SendMessageService {

	public String sendNormalMess(String message, String phone);

	public String sendLongMess(String message, String phone);

	public String sendMess(String message, String phone, SendMethodType methodType);

	public String sendMess(String message, String phone, SendMethodType methodType, MessageFormat messageFormat);

	public String sendMess(String message, String phone, SendMethodType methodType, MessageFormat messageFormat,
			boolean isDeliveryResultRequest);

	public String sendMess(String message, List<String> phone, SendMethodType methodType, MessageFormat messageFormat,
			boolean isDeliveryResultRequest);

}
