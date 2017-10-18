package com.bsoft.support.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * validator返回结果
 *
 * @author cuiweizheng
 */
public class ValResult {

    // 定内部枚举
    public enum ErrorEnum {
        E_REQUIRED(401, "字段不能为空"), E_FORMATER(402, "字段格式错误"), E_RANGE(403, "字段超出规定范围");
        private int state;
        private String stateInfo;

        private ErrorEnum(int state, String stateInfo) {
            this.state = state;
            this.stateInfo = stateInfo;
        }

        public int getState() {
            return state;
        }

        public String getStateInfo() {
            return stateInfo;
        }

        public static ErrorEnum stateOf(int index) {
            for (ErrorEnum stateEnum : values()) {
                if (stateEnum.getState() == index) {
                    return stateEnum;
                }
            }
            return null;
        }
    }

    private List<Error> errorList = new ArrayList<>();

    public List<Error> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Error> errorList) {
        this.errorList = errorList;
    }

    public boolean isValid() {
        if (errorList != null && errorList.size() > 0) {
            return false;
        }
        return true;
    }

    public int getErrorCount() {
        if (errorList != null) {
            return errorList.size();
        }
        return 0;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "验证结果无法转换成json,请注意！";
        }

    }

}
