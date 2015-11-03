package com.simpletech.wifiprobe.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.wifiprobe.dao.base.BaseDaoImpl;
import com.simpletech.wifiprobe.dao.WifiDao;
import com.simpletech.wifiprobe.model.Wifi;

/**
 * 数据库表t_wifi的Dao实现
 * @author 树朾
 * @date 2015-11-02 17:16:40 中国标准时间
 */
@Repository
public class WifiDaoImpl extends BaseDaoImpl<Wifi> implements WifiDao{

	@Override
	public int insert(Wifi t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(Wifi t) throws Exception {
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
	public Wifi findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<Wifi> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<Wifi> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}
}

