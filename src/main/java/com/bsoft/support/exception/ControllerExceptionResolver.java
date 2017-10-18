package com.bsoft.support.exception;

import com.bsoft.support.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理
 * 
 */
public class ControllerExceptionResolver extends SimpleMappingExceptionResolver {

	private static Logger log = LoggerFactory.getLogger(ControllerExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		response.setStatus(500); // 设置状态码
		response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
		response.setCharacterEncoding("UTF-8"); // 避免乱码
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.setCode("500");
		if (ex instanceof HandlerException) {
			jsonResult.setCode(((HandlerException) ex).getErrorCode());
		}
		jsonResult.setMsg(ex.getMessage());
		ObjectMapper mapper = new ObjectMapper();
		/* 使用response返回 */
		try {
			response.getWriter().write(mapper.writeValueAsString(jsonResult));
		} catch (IOException e) {
			log.error("与客户端通讯异常:" + e.getMessage(), e);
		}
		log.debug("异常:" + ex.getMessage(), ex);
		
		return mv;
	}
}
