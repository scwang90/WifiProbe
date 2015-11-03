package com.simpletech.wifiprobe.dao;

import com.simpletech.wifiprobe.model.Visit;

import java.util.Date;
import java.util.List;

/**
 * 数据统计 的Dao接口
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface StatisticsDao {

	/**
	 * mac的访问记录
	 *
	 * @param idshop 网站ID
	 * @param mac    mac地址
	 * @param start  开始时间
	 * @param end    结束时间
	 * @return mac的访问记录
	 */
	List<Visit> macvisit(String idshop, String mac, Date start, Date end) throws Exception;

}
