package com.bsoft.support.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by wjtc8 on 2016/8/23.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 取得当前使用那个数据源。
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicContextHolder.getContextType();
    }

}
