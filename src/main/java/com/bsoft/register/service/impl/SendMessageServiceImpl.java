package com.bsoft.register.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.bsoft.client.schema.sms.MessageFormat;
import com.bsoft.client.schema.sms.SendMethodType;
import com.bsoft.client.schema.sms.SendSmsRequest;
import com.bsoft.client.schema.sms.SendSmsResponse;
import com.bsoft.client.service.CmccMasWbs;
import com.bsoft.constant.MessTypeConst;
import com.bsoft.exception.MessageException;
import com.bsoft.register.service.SendMessageService;

/**
 * 发送短信服务
 * 
 * @author wms1231
 *
 */
@Service
public class SendMessageServiceImpl implements SendMessageService {

	private static Logger logger = Logger.getLogger(SendMessageServiceImpl.class);

	@Resource(name = "sendMessageDao")
	private CmccMasWbs sendMessageDao;

	@Override
	public String sendNormalMess(String message, String phone) {
		return sendMess(message, phone, SendMethodType.NORMAL, MessageFormat.GB_2312, true);
	}

	@Override
	public String sendLongMess(String message, String phone) {
		return sendMess(message, phone, SendMethodType.LONG, MessageFormat.GB_2312, true);
	}

	private void validMess(String message, List<String> phone) {
		for (String p : phone) {
			if(StringUtils.isBlank(p)){
				throw new MessageException("-1", "电话号码为空");
			}
		}
		
		if(StringUtils.isBlank(message)){
			throw new MessageException("-1", "发送短信内容为空");
		}
	}

	@Override
	public String sendMess(String message, String phone, SendMethodType methodType) {
		if (methodType == null) {
			methodType = SendMethodType.LONG;
		}
		return sendMess(message, phone, methodType, MessageFormat.GB_2312, true);
	}

	@Override
	public String sendMess(String message, String phone, SendMethodType methodType, MessageFormat messageFormat) {
		if (methodType == null) {
			methodType = SendMethodType.LONG;
		}
		return sendMess(message, phone, methodType, messageFormat, true);
	}

	@Override
	public String sendMess(String message, String phone, SendMethodType methodType, MessageFormat messageFormat,
			boolean isDeliveryResultRequest) {
		List<String> phoneList = new ArrayList<>();
		phoneList.add(MessTypeConst.PHONE_PREFIX + phone);
		return sendMess(message, phoneList, methodType, messageFormat, isDeliveryResultRequest);
	}

	@Override
	public String sendMess(String message, List<String> phone, SendMethodType methodType, MessageFormat messageFormat,
			boolean isDeliveryResultRequest) {
		validMess(message, phone);
		SendSmsRequest sendSmsRequest = new SendSmsRequest();
		sendSmsRequest.setApplicationID(MessTypeConst.APPLICATION_ID);
		sendSmsRequest.setDeliveryResultRequest(isDeliveryResultRequest);
		sendSmsRequest.setExtendCode(MessTypeConst.EXTEND_CODE);
		sendSmsRequest.setMessage(message);
		sendSmsRequest.setMessageFormat(messageFormat);
		sendSmsRequest.setSendMethod(methodType);
		sendSmsRequest.setDestinationAddresses(phone);
		SendSmsResponse sendSmsResponse = null;
		try {
			sendSmsResponse = sendMessageDao.sendSms(sendSmsRequest);
			for (String p : phone) {
				logger.info("成功发送短信电话=>" + p + "短信内容=>" + message);
			}

		} catch (Exception e) {
			logger.error("短信发送异常," + e.getMessage());
			throw new MessageException("-1", "短信发送失败");
		}
		return sendSmsResponse.getRequestIdentifier();
	}

}
