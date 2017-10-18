package com.bsoft.support.dao.hibernate;

import com.bsoft.support.Pager;
import com.bsoft.support.dao.ICommonDao;
import com.bsoft.support.utils.SpringContextUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by wjtc8 on 2017/5/10.
 */
//@Repository
public class HibernateCommonDao implements ICommonDao {
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(HibernateCommonDao.class);


    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Map<String, Object>> selectList(String sqlKey, String dataSource, Object param, String[] orders) {
        Criteria criteria = getSession().createCriteria(sqlKey);
        getParam(sqlKey, (Map<String, Object>) param, criteria);
        orderBy(orders, criteria);//添加排序
        return criteria.list();
    }

    @Override
    public Map<String, Object> selectOne(String sqlKey, String dataSource, Object param) {
        Criteria criteria = getSession().createCriteria(sqlKey);
        getParam(sqlKey, (Map<String, Object>) param, criteria);
//        orderBy(orders, criteria);//添加排序
        return (Map<String, Object>) criteria.list().get(0);
    }

    @Override
    public Pager selectListByPage(String sqlKey, String dataSource, Object param, int pageNo, int pageSize, String[] orders) {
        Criteria criteria = getSession().createCriteria(sqlKey);
        getParam(sqlKey, (Map<String, Object>) param, criteria);
        //获得总数
        int rowCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
        criteria.setProjection(null);
        //获得分页数据
        orderBy(orders, criteria);//添加排序
        criteria.setFirstResult((pageNo - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        Pager pager = new Pager(rowCount, pageSize, criteria.list());
        return pager;
    }

    private static Configuration hibernateConf;

    @Override
    public int insert(String sqlKey, String dataSource, Object param) {
        getSession().saveOrUpdate(sqlKey, getOriginal(sqlKey,param));
        return 1;
    }

    @Override
    public int update(String sqlKey, String dataSource, Object param) {
        getSession().update(getOriginal(sqlKey,param));
        return 1;
    }

    private Object getOriginal(String sqlKey,Object param) {
        Map<String, Object> p = (Map<String, Object>) param;
        PersistentClass pc = ((LocalSessionFactoryBean) SpringContextUtil.getBean("&sessionFactory")).getConfiguration().getClassMapping(sqlKey);
        Map<String, Object> original = null;
        for (Iterator it = pc.getIdentifier().getColumnIterator(); it.hasNext(); ) {
            Column col = (Column) it.next();
            //todo 组合索引暂时还没有考虑怎么处理，后续遇到再处理
//            Map<String, Object> serialize = new HashMap<>();
//            serialize.put(col.getName(), );
            original = (Map<String, Object>) getSession().get(sqlKey, (Serializable) p.get(col.getName()));
        }
        Set<String> keySet = original.keySet();
        for (String key : keySet) {
            if (p.get(key) != null) {
                original.put(key, p.get(key));
            } else if (p.get(key) == null && p.containsKey(key)) {
                original.put(key, null);
            }
        }
        return original;
    }


    private void orderBy(String[] orders, Criteria criteria) {
        if (orders != null) {
            for (String o : orders) {
                String[] sa = o.split(":");
                if (sa[1].equals("asc")) {
                    criteria.addOrder(Order.asc(sa[0]));
                } else {
                    criteria.addOrder(Order.desc(sa[0]));
                }
            }
        }
    }


    private Map<String, Object> getParam(String entityName, Map<String, Object> param, Criteria criteria) {
        Map<String, Object> resultParam = new HashMap<>();
        Set<String> keySet = param.keySet();
        for (String key : keySet) {
            Object value = param.get(key);
            if (key.startsWith("lt_") && hasCol(entityName, key.substring(3, key.length()))) {
                criteria.add(Property.forName(key.substring(3, key.length())).lt(value));
            } else if (key.startsWith("gt_") && hasCol(entityName, key.substring(3, key.length()))) {
                criteria.add(Property.forName(key.substring(3, key.length())).gt(value));
            } else if (key.startsWith("eq_") && hasCol(entityName, key.substring(3, key.length()))) {
                criteria.add(Property.forName(key.substring(3, key.length())).eq(value));
            } else if (key.startsWith("like_") && hasCol(entityName, key.substring(5, key.length()))) {
                criteria.add(Property.forName(key.substring(5, key.length())).like(value));
            } else if (key.startsWith("in_") && hasCol(entityName, key.substring(3, key.length()))) {
                criteria.add(Property.forName(key.substring(3, key.length())).in((Object[]) value));
            } else if (hasCol(entityName, key)) {
                criteria.add(Property.forName(key).eq(value));
            }
        }
        return resultParam;
    }

    private boolean hasCol(String entityName, String name) {
        PersistentClass pc = ((LocalSessionFactoryBean) SpringContextUtil.getBean("&sessionFactory")).getConfiguration().getClassMapping(entityName);
        for (Iterator it = pc.getTable().getColumnIterator(); it.hasNext(); ) {
            Column col = (Column) it.next();
            if (col.getName().equals(name)) {
                logger.debug("{0}字段被找到", name);
                return true;
            }
        }
        return false;
    }


}
