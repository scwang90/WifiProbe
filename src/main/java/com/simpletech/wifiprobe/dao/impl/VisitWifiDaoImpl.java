package com.simpletech.wifiprobe.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.wifiprobe.dao.base.BaseDaoImpl;
import com.simpletech.wifiprobe.dao.VisitWifiDao;
import com.simpletech.wifiprobe.model.VisitWifi;

/**
 * 数据库表t_visit_wifi的Dao实现
 * @author 树朾
 * @date 2015-11-24 18:16:02 中国标准时间
 */
@Repository
public class VisitWifiDaoImpl extends BaseDaoImpl<VisitWifi> implements VisitWifiDao{

	@Override
	public int insert(VisitWifi t) {
		return super.insert(t);
	}

	@Override
	public int update(VisitWifi t) {
		return super.update(t);
	}

	@Override
	public int delete(Object id) {
		return super.delete(id);
	}

	@Override
	public int countAll() {
		return super.countAll();
	}

	@Override
	public VisitWifi findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<VisitWifi> findAll() {
		return super.findAll();
	}

	@Override
	public List<VisitWifi> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

