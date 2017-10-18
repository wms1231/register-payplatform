package com.bsoft.support;

import com.bsoft.support.utils.ResultMessageUtils;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by wjtc8 on 2017/1/18.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult {

    private String msg;

    private Object content;

    private String code;

    public String getMsg() {
        if (msg == null) {
            return ResultMessageUtils.getBundleMessage(code);
        }
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
