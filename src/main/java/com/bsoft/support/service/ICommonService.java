package com.bsoft.support.service;

import com.bsoft.support.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by wjtc8 on 2016/8/16.
 */
public interface ICommonService {

    /**
     * @param sqlKey sql id
     * @param param  参数
     * @return
     */
    List<Map<String, Object>> selectList(String sqlKey, String dataSource, Object param, String[] orders);

    Map<String, Object> selectOne(String sqlKey, String dataSource, Object param);

    Pager selectListByPage(String sqlKey, String dataSource, Map<String, Object> param, int pageNo, int pageSize, String[] orders);

    int insert(String sqlKey, String dataSource, Object param);

    int update(String sqlKey, String dataSource, Object param);

}
