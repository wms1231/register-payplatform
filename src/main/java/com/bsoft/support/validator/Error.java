package com.bsoft.support.validator;

import java.util.List;

import com.bsoft.support.validator.ValResult.ErrorEnum;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 验证返回结果
 *
 * @author cuiweizheng
 * @version 1.0
 * @date 2017年1月17日下午5:36:10
 */
public class Error {
    // 定义jackson对象
//	private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private ErrorEnum status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private String fieldName;

    private Object fieldValue;

    public Error() {

    }

    public Error(ErrorEnum eRequired, String msg, String fieldName, Object fieldValue) {
        this.status = eRequired;
        this.msg = msg;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public Integer getStatus() {
        return status.getState();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}
