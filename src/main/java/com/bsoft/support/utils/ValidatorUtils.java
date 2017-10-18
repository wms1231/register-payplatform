package com.bsoft.support.utils;

import com.bsoft.support.validator.Error;
import com.bsoft.support.validator.ValResult;
import com.bsoft.support.validator.ValResult.ErrorEnum;
import com.bsoft.support.validator.ValidatorBuilder;

import java.util.Map;

/**
 * 数据验证工具类
 *
 * @author cuiweizheng
 */
public class ValidatorUtils {

    public static ValResult getValidateResult(String XMLPath, Map<String, Object> paramMap) {
        return new ValidatorBuilder().build(XMLPath).doHandle(paramMap);
    }

    public static void validate(String XMLPath, Map<String, Object> paramMap) {
        ValResult result = new ValidatorBuilder().build(XMLPath).doHandle(paramMap);
        if (!result.isValid()) {
            ExceptionUtils.sendError("400", result.toString());
        }
    }

    public static boolean isValid(String XMLPath, Map<String, Object> paramMap) {
        return new ValidatorBuilder().build(XMLPath).doHandle(paramMap).isValid();
    }

    /**
     * @param e
     * @param msg
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public static Error result(ErrorEnum e, String msg, String fieldName, Object fieldValue) {
        if (msg != null) {
            return new Error(e, msg, fieldName, fieldValue);
        } else {
            return new Error(e, e.getStateInfo(), fieldName, fieldValue);
        }
    }

}
