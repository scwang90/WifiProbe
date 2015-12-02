package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.VisitWifiDao;
import com.simpletech.wifiprobe.model.VisitWifi;
import com.simpletech.wifiprobe.model.base.ModelBase;
import com.simpletech.wifiprobe.service.VisitWifiService;
import com.simpletech.wifiprobe.service.base.BaseServiceImpl;
import com.simpletech.wifiprobe.util.Page;
import com.simpletech.wifiprobe.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表t_visit_wifi的Service接实现
 * @author 树朾
 * @date 2015-11-24 18:16:03 中国标准时间
 */
@Service
public class VisitWifiServiceImpl extends BaseServiceImpl<VisitWifi> implements VisitWifiService{

	@Autowired
	VisitWifiDao dao;
	
	@Override
	public int insert(VisitWifi model){
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(VisitWifi model) {
		VisitWifi old = findById(getModelID(model));
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
	public VisitWifi findById(Object id){
		return dao.findById(id);
	}

	@Override
	public List<VisitWifi> findAll(){
		return dao.findAll();
	}

	@Override
	public int delete(String id){
		return dao.delete(id);
	}

	@Override
	public List<VisitWifi> findByPage(int limit, int start) {
		return dao.findByPage(limit,start);
	}

	@Override
	public VisitWifi findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Page<VisitWifi> listByPage(int pageSize, int pageNo){
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<VisitWifi> list = dao.findByPage(limit, start);
		
		return new Page<VisitWifi>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() {
		return dao.countAll();
	}
}
