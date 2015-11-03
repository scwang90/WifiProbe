package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.ShopDao;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.base.ModelBase;
import com.simpletech.wifiprobe.service.ShopService;
import com.simpletech.wifiprobe.service.base.BaseServiceImpl;
import com.simpletech.wifiprobe.util.Page;
import com.simpletech.wifiprobe.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表t_shop的Service接实现
 * @author 树朾
 * @date 2015-11-03 17:09:44 中国标准时间
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements ShopService{

	@Autowired
	ShopDao dao;
	
	@Override
	public int insert(Shop model) throws Exception{
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(Shop model) throws Exception {
		Shop old = findById(getModelID(model));
		if (old == null) {
			throw new ServiceException("请求更新记录不存在或已经被删除！");
		}
		model = checkNullField(old, model);
		return dao.update(model);
	}

	@Override
	public int delete(Object id) throws Exception {
		return dao.delete(id);
	}

	@Override
	public Shop findById(Object id) throws Exception{
		return dao.findById(id);
	}

	@Override
	public List<Shop> findAll() throws Exception{
		return dao.findAll();
	}

	@Override
	public int delete(String id) throws Exception{
		return dao.delete(id);
	}

	@Override
	public List<Shop> findByPage(int limit, int start) throws Exception {
		return dao.findByPage(limit,start);
	}

	@Override
	public Shop findById(String id) throws Exception {
		return dao.findById(id);
	}
	
	@Override
	public Page<Shop> listByPage(int pageSize, int pageNo) throws Exception{
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<Shop> list = dao.findByPage(limit, start);
		
		return new Page<Shop>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() throws Exception {
		return dao.countAll();
	}
}
