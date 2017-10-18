package com.bsoft.support.utils;

import com.bsoft.support.exception.HandlerException;

/**
 * Created by wjtc8 on 2017/1/18.
 */
public class ExceptionUtils {
    public static void sendError(String code) {
        throw new HandlerException(code);
    }

    public static void sendError(String code, String msg) {
        throw new HandlerException(code, msg);
    }
}
