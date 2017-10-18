package com.bsoft.interceptor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bsoft.tools.CookieUtils;
import com.bsoft.tools.DateFormatUtils;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.IPUtils;

public class UnifyInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(UnifyInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		
		String ip = IPUtils.getIpAddr(request);
		String method = request.getMethod();
		String requestUri = request.getRequestURI();
		String cookieValue = CookieUtils.getCookieValue(request, "userAccount");
		String operatorName = "admin";
		if (requestUri.contains("/user/login")) {
			operatorName = "userLogin";
		}

		if (StringUtils.isNotBlank(cookieValue)) {
			operatorName = cookieValue.split("#")[0];
		}

		Map<String, String[]> parameterMap = request.getParameterMap();
		String dateFormat = DateFormatUtils.format(new Date(), DateFormatUtils.DATE_TIME_LOCAL_PATTERN);

		logger.info("调用服务:operatorName=>" + operatorName + " time=>" + dateFormat + "method=>" + method + "url=>"
				+ requestUri + " requestParam=>" + FastJsonUtil.toJSONString(parameterMap) + "ip=>" + ip);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("调用handler结束!");
	}
}
