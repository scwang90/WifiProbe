package com.simpletech.wifiprobe.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.wifiprobe.dao.base.BaseDaoImpl;
import com.simpletech.wifiprobe.dao.MacLogDao;
import com.simpletech.wifiprobe.model.MacLog;

/**
 * 数据库表t_mac_log的Dao实现
 * @author 树朾
 * @date 2015-11-24 18:16:02 中国标准时间
 */
@Repository
public class MacLogDaoImpl extends BaseDaoImpl<MacLog> implements MacLogDao{

	@Override
	public int insert(MacLog t) {
		return super.insert(t);
	}

	@Override
	public int update(MacLog t) {
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
	public MacLog findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<MacLog> findAll() {
		return super.findAll();
	}

	@Override
	public List<MacLog> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

