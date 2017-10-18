package com.bsoft.support.dao;

import com.bsoft.support.Pager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wjtc8 on 2016/8/16.
 */
@Repository
public interface ICommonDao {

    /**
     * @param sqlKey
     * @param dataSource
     * @param param
     * @param orders     例如:name:asc 或者 name:desc
     * @return
     */
    List<Map<String, Object>> selectList(String sqlKey, String dataSource, Object param, String[] orders);

    Map<String, Object> selectOne(String sqlKey, String dataSource, Object param);

    /**
     * @param sqlKey
     * @param dataSource
     * @param param
     * @param pageNo
     * @param pageSize
     * @param orders     例如:name:asc 或者 name:desc
     * @return
     */
    Pager selectListByPage(String sqlKey, String dataSource, Object param, int pageNo, int pageSize, String[] orders);

    int insert(String sqlKey, String dataSource, Object param);

    int update(String sqlKey, String dataSource, Object param);
}
