package com.simpletech.wifiprobe.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.wifiprobe.dao.base.BaseDaoImpl;
import com.simpletech.wifiprobe.dao.ShopDao;
import com.simpletech.wifiprobe.model.Shop;

/**
 * 数据库表shop的Dao实现
 * @author 树朾
 * @date 2015-11-24 18:16:02 中国标准时间
 */
@Repository
public class ShopDaoImpl extends BaseDaoImpl<Shop> implements ShopDao{

	@Override
	public int insert(Shop t) {
		return super.insert(t);
	}

	@Override
	public int update(Shop t) {
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
	public Shop findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<Shop> findAll() {
		return super.findAll();
	}

	@Override
	public List<Shop> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

