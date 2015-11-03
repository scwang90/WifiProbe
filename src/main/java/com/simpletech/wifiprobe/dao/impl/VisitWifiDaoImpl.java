package com.simpletech.wifiprobe.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.wifiprobe.dao.base.BaseDaoImpl;
import com.simpletech.wifiprobe.dao.VisitWifiDao;
import com.simpletech.wifiprobe.model.VisitWifi;

/**
 * 数据库表t_visit_wifi的Dao实现
 * @author 树朾
 * @date 2015-11-02 17:16:40 中国标准时间
 */
@Repository
public class VisitWifiDaoImpl extends BaseDaoImpl<VisitWifi> implements VisitWifiDao{

	@Override
	public int insert(VisitWifi t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(VisitWifi t) throws Exception {
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
	public VisitWifi findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<VisitWifi> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<VisitWifi> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}
}

