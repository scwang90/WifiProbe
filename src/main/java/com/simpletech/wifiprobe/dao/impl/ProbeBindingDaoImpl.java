package com.simpletech.wifiprobe.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.wifiprobe.dao.base.BaseDaoImpl;
import com.simpletech.wifiprobe.dao.ProbeBindingDao;
import com.simpletech.wifiprobe.model.ProbeBinding;

/**
 * 数据库表probe_binding的Dao实现
 * @author 树朾
 * @date 2015-11-24 18:16:02 中国标准时间
 */
@Repository
public class ProbeBindingDaoImpl extends BaseDaoImpl<ProbeBinding> implements ProbeBindingDao{

	@Override
	public int insert(ProbeBinding t) {
		return super.insert(t);
	}

	@Override
	public int update(ProbeBinding t) {
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
	public ProbeBinding findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<ProbeBinding> findAll() {
		return super.findAll();
	}

	@Override
	public List<ProbeBinding> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

