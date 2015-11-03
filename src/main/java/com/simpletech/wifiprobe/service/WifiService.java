package com.simpletech.wifiprobe.service;

import java.util.List;

import com.simpletech.wifiprobe.model.Wifi;
import com.simpletech.wifiprobe.service.base.BaseService;

/**
 * 数据库表t_wifi的Service接口层
 * @author 树朾
 * @date 2015-11-02 17:16:40 中国标准时间
 */
public interface WifiService extends BaseService<Wifi> {

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变行数
	 */
	int insert(Wifi model) throws Exception;
	/**
	 * 根据ID删除
	 * @param id 主键ID
	 */
	int delete(Object id) throws Exception;
	/**
	 * 更新一条数据
	 * @param model 需要更新数据
	 * @return 改变行数
	 */
	int update(Wifi model) throws Exception;
	/**
	 * 统计全部出数据
	 * @return 全部数据量
	 */
	int countAll() throws Exception;
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return 数据对象 or null
	 */
	Wifi findById(Object id) throws Exception;
	/**
	 * 获取全部数据
	 * @return 全部所有数据
	 */
	List<Wifi> findAll() throws Exception;
	/**
	 * 分页查询数据
	 * @param limit 分页最大值
	 * @param start 开始编号
	 * @return 分页列表数据
	 */
	List<Wifi> findByPage(int limit,int start) throws Exception;
	
}
