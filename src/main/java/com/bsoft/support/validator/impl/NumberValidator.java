package com.bsoft.support.validator.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.dom4j.Node;

import com.bsoft.support.utils.ValidatorUtils;
import com.bsoft.support.validator.AbstractValidator;
import com.bsoft.support.validator.Error;
import com.bsoft.support.validator.ValResult.ErrorEnum;

/**
 * Number型 xml validator
 * 
 * @author cuiweizheng
 * @date 2017年1月18日下午2:06:12
 * @version 1.0
 */
public class NumberValidator extends AbstractValidator {

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
		// 判断是否为数值
		String value = (String) paramMap.get(name);
		if (required != null && required.equals("true")) {
			// 判断所传参数是否为空
			if (StringUtils.isBlank(value)) {
				return ValidatorUtils.result(ErrorEnum.E_REQUIRED, msg, name,value);
			}
		}
		if (StringUtils.isNotBlank(value)) {
			if (!NumberUtils.isNumber(value)) {
				return ValidatorUtils.result(ErrorEnum.E_FORMATER, msg, name,value);
			}
			// 将所传类型转化为Number
			Number valueNum = NumberUtils.createNumber(value);
			// 与所传属性进行比较
			if (max != null) {
				if (max.doubleValue() != NumberUtils.max(new double[] { valueNum.doubleValue(), max.doubleValue() })) {
					return ValidatorUtils.result(ErrorEnum.E_RANGE, msg, name,value);
				}
			}
			// 与所传属性进行比较
			if (min != null) {
				if (min.doubleValue() != NumberUtils.min(new double[] { valueNum.doubleValue(), min.doubleValue() })) {
					return ValidatorUtils.result(ErrorEnum.E_RANGE, msg, name,value);
				}
			}
		}
		return null;
	}

}
