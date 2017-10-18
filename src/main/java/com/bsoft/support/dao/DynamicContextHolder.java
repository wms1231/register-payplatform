package com.bsoft.support.dao;

/**
 * Created by wjtc8 on 2016/8/23.
 */
public class DynamicContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    private static boolean open = false;

    public static void setContextType(String contextType) {
        if (open)
            contextHolder.set(contextType);
    }

    public static String getContextType() {
        return contextHolder.get();
    }

    public static void clearContextType() {
        contextHolder.remove();
    }
}
