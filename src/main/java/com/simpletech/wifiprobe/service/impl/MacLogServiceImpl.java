package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.MacLogDao;
import com.simpletech.wifiprobe.model.MacLog;
import com.simpletech.wifiprobe.model.base.ModelBase;
import com.simpletech.wifiprobe.service.MacLogService;
import com.simpletech.wifiprobe.service.base.BaseServiceImpl;
import com.simpletech.wifiprobe.util.Page;
import com.simpletech.wifiprobe.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表t_mac_log的Service接实现
 * @author 树朾
 * @date 2015-11-24 18:16:03 中国标准时间
 */
@Service
public class MacLogServiceImpl extends BaseServiceImpl<MacLog> implements MacLogService{

	@Autowired
	MacLogDao dao;
	
	@Override
	public int insert(MacLog model){
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(MacLog model) {
		MacLog old = findById(getModelID(model));
		if (old == null) {
			throw new ServiceException("请求更新记录不存在或已经被删除！");
		}
		model = checkNullField(old, model);
		return dao.update(model);
	}

	@Override
	public int delete(Object id) {
		return dao.delete(id);
	}

	@Override
	public MacLog findById(Object id){
		return dao.findById(id);
	}

	@Override
	public List<MacLog> findAll(){
		return dao.findAll();
	}

	@Override
	public int delete(String id){
		return dao.delete(id);
	}

	@Override
	public List<MacLog> findByPage(int limit, int start) {
		return dao.findByPage(limit,start);
	}

	@Override
	public MacLog findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Page<MacLog> listByPage(int pageSize, int pageNo){
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<MacLog> list = dao.findByPage(limit, start);
		
		return new Page<MacLog>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() {
		return dao.countAll();
	}
}
