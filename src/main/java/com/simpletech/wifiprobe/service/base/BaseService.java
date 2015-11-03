package com.simpletech.wifiprobe.service.base;

import com.simpletech.wifiprobe.dao.base.BaseDao;
import com.simpletech.wifiprobe.util.Page;

/**
 * 通用Service层接口
 * @param <T>
 * @author 树朾
 * @date 2015-11-03 17:09:44 中国标准时间
 */
public interface BaseService<T> extends BaseDao<T>{
	int delete(String id) throws Exception;
	T findById(String id) throws Exception;
	Page<T> listByPage(int pageSize, int pageNo) throws Exception;
}
