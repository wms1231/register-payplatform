package com.bsoft.tools.http;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.bsoft.exception.PayException;
import com.bsoft.tools.RequestDataUtil;
import com.xiaoleilu.hutool.http.HttpException;
import com.xiaoleilu.hutool.http.HttpUtil;

public class PayHttpUtil {
	private static Logger logger = Logger.getLogger(PayHttpUtil.class);

	public static String sendPost(String reqUrl, Map<String, String> reqParams, String charset, String errMsg) {
		try {
			if (StringUtils.isBlank(charset)) {
				charset = "UTF-8";
			}
			String requestparams = RequestDataUtil.generatorRequestXml(reqParams);
			logger.debug("请求xml=>" + requestparams);
			String result = HttpUtil.post(reqUrl, requestparams);
			logger.debug("请求结果xml=>" + result);
			return result;

		} catch (HttpException e) {
			logger.error("支付异常", e);
			if (StringUtils.isNotBlank(errMsg)) {
				throw new PayException("-1", errMsg);
			}
			throw new PayException("-1", e.getMessage());
		} catch (Exception e) {
			logger.error("支付异常", e);
			throw new PayException("-1", "未知支付错误");
		}
	}
}
