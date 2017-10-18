package com.bsoft.support.service;

import com.bsoft.support.Pager;
import com.bsoft.support.dao.ICommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wjtc8 on 2016/8/16.
 */
@Service
public class CommonService implements ICommonService {

	@Autowired
	private ICommonDao commonDao;

	/**
	 * @param sqlKey
	 *            sql id
	 * @param param
	 *            参数
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Map<String, Object>> selectList(String sqlKey, String dataSource, Object param, String[] orders) {
		return commonDao.selectList(sqlKey, dataSource, param, orders);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Map<String, Object> selectOne(String sqlKey, String dataSource, Object param) {
		return commonDao.selectOne(sqlKey, dataSource, param);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Pager selectListByPage(String sqlKey, String dataSource, Map<String, Object> param, int pageNo, int pageSize,
			String[] orders) {
		return commonDao.selectListByPage(sqlKey, dataSource, param, pageNo, pageSize, orders);
	}

	@Override
	@Transactional
	public int insert(String sqlKey, String dataSource, Object param) {
		return commonDao.insert(sqlKey, dataSource, param);
	}

	@Override
	@Transactional
	public int update(String sqlKey, String dataSource, Object param) {
		return commonDao.update(sqlKey, dataSource, param);
	}

}
