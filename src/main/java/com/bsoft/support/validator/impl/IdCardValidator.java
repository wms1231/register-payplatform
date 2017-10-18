package com.bsoft.support.validator.impl;

import java.util.Map;

import com.bsoft.support.utils.IDCardUtils;
import org.apache.commons.lang3.StringUtils;

import com.bsoft.support.utils.ValidatorUtils;
import com.bsoft.support.validator.AbstractValidator;
import com.bsoft.support.validator.Error;
import com.bsoft.support.validator.ValResult.ErrorEnum;

public class IdCardValidator extends AbstractValidator {
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
                return ValidatorUtils.result(ErrorEnum.E_REQUIRED, msg, name, value);
            }
        }
        if (StringUtils.isNotBlank(value)) {
            //判断身份证号码是否合法
            if (!IDCardUtils.cardCodeVerifySimple(value)) {
                return ValidatorUtils.result(ErrorEnum.E_FORMATER, msg, name, value);
            }
            //验证第二代身份证是否符合国家规范
            if (IDCardUtils.cardCodeVerifySimple18(value)) {
                if (!IDCardUtils.cardCodeVerify(value)) {
                    return ValidatorUtils.result(ErrorEnum.E_FORMATER, msg, name, value);
                }
            }
        }
        return null;
    }

}
