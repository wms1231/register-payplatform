package com.bsoft.support.validator.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Node;

import com.bsoft.support.utils.ValidatorUtils;
import com.bsoft.support.validator.AbstractValidator;
import com.bsoft.support.validator.Error;
import com.bsoft.support.validator.ValResult.ErrorEnum;

/**
 * String型XML validator
 * 
 * @author cuiweizheng
 * @date 2017年1月18日上午10:59:02
 * @version 1.0
 */
public class StringValidator extends AbstractValidator {

	/**
	 * 数据合法性验证
	 * 
	 * @param node
	 * @return
	 * @see com.xml.validator.service.Validator#handle(Node, Map)
	 */
	@Override
	public Error handle(Map<String, Object> paramMap) {
		// 判断xml name属性是否为空
		if (name == null) {
			return null;
		}
		String value = (String) paramMap.get(name);
		if (required != null && required.equals("true")) {
			// 判断所传参数是否为空
			if (StringUtils.isBlank(value)) {
				return ValidatorUtils.result(ErrorEnum.E_REQUIRED, msg, name,value);
			}
		}
		// 判断传入参数是否在给定范围内
		if (StringUtils.isNotBlank(value) && max != null) {
			if (value.length() > max.intValue())
				return ValidatorUtils.result(ErrorEnum.E_RANGE, msg, name,value);
		}
		if (StringUtils.isNotBlank(value) && min != null) {
			if (value.length() < min.intValue())
				return ValidatorUtils.result(ErrorEnum.E_RANGE, msg, name,value);
		}
		return null;
	}

}
