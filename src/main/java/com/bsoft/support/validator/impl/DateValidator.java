package com.bsoft.support.validator.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.bsoft.support.utils.ValidatorUtils;
import com.bsoft.support.validator.AbstractValidator;
import com.bsoft.support.validator.Error;
import com.bsoft.support.validator.ValResult;
import com.bsoft.support.validator.ValResult.ErrorEnum;

/**
 * date型 xml validator
 * 
 * @author cuiweizheng
 * @date 2017年1月18日下午2:06:48
 * @version 1.0
 */
public class DateValidator extends AbstractValidator {
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
		// 如果value不为空
		if (StringUtils.isNotBlank(value)) {
			// 判断pattern属性
			if (pattern != null) {
				// 判断传入时间格式是否合法
				try {
					DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
					DateTime.parse(value, format);
				} catch (Exception e) {
					e.printStackTrace();
					return ValidatorUtils.result(ErrorEnum.E_FORMATER, msg, name,value);
				}
			} else {
				try {
					DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
					DateTime.parse(value, format);
				} catch (Exception e) {
					return ValidatorUtils.result(ErrorEnum.E_FORMATER, msg, name,value);
				}
			}
		}
		return null;
	}

}
