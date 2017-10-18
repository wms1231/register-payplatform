package com.bsoft.support.validator;

import java.util.Map;

import org.dom4j.Node;

/**
 * xml验证接口
 * 
 * @author cuiweizheng
 * @date 2017年1月17日下午5:34:48
 * @version 1.0
 */
public interface Validator {

	void setValidator(Validator validator);

	Validator getValidator();

	ValResult doHandle(Map<String, Object> paramMap);
	
	void parse(Node node);

}
