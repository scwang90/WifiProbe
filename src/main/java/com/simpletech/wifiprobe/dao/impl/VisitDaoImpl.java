package com.simpletech.wifiprobe.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.wifiprobe.dao.base.BaseDaoImpl;
import com.simpletech.wifiprobe.dao.VisitDao;
import com.simpletech.wifiprobe.model.Visit;

/**
 * 数据库表t_visit的Dao实现
 * @author 树朾
 * @date 2015-11-03 17:09:44 中国标准时间
 */
@Repository
public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao{

	@Override
	public int insert(Visit t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(Visit t) throws Exception {
		return super.update(t);
	}

	@Override
	public int delete(Object id) throws Exception {
		return super.delete(id);
	}

	@Override
	public int countAll() throws Exception {
		return super.countAll();
	}

	@Override
	public Visit findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<Visit> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<Visit> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}
}

