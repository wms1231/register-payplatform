package com.bsoft.support.dao.mybatis;

import com.bsoft.support.Pager;
import com.bsoft.support.dao.DynamicContextHolder;
import com.bsoft.support.dao.DynamicSqlSessionTemplate;
import com.bsoft.support.dao.ICommonDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.orderbyhelper.OrderByHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by wjtc8 on 2016/8/16.
 */
@Repository
public class MybatisCommonDao implements ICommonDao {

    @Autowired
    private DynamicSqlSessionTemplate template;

    /**
     * @param sqlKey     sql id
     * @param dataSource 数据源
     * @param param      参数
     * @return
     */
    public List<Map<String, Object>> selectList(String sqlKey, String dataSource, Object param, String[] orders) {
        DynamicContextHolder.setContextType(dataSource);
        order(orders);
        return template.selectList(sqlKey, param);
    }


    public Map<String, Object> selectOne(String sqlKey, String dataSource, Object param) {
        DynamicContextHolder.setContextType(dataSource);
        return template.selectOne(sqlKey, param);
    }

    @Override
    public Pager selectListByPage(String sqlKey, String dataSource, Object param, int pageNo, int pageSize, String[] orders) {
        DynamicContextHolder.setContextType(dataSource);
        PageHelper.startPage(pageNo, pageSize, true);
        order(orders);
        List list = template.selectList(sqlKey, param);
        Pager pager = new Pager(((Page) list).getTotal(), pageSize, list);
        return pager;
    }

    public int insert(String sqlKey, String dataSource, Object param) {
        DynamicContextHolder.setContextType(dataSource);
        return template.insert(sqlKey, param);
        
    }

    public int update(String sqlKey, String dataSource, Object param) {
        DynamicContextHolder.setContextType(dataSource);
        return template.update(sqlKey, param);
    }

    private void order(String[] orders) {
        if (orders != null) {
            for (String order : orders) {
                String[] o = order.split(":");
                OrderByHelper.orderBy(o[0] + " " + o[1]);
            }
        }
    }

}
