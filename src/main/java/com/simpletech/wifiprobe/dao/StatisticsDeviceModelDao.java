package com.simpletech.wifiprobe.dao;

import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.model.entity.BrandValue;

import java.util.Date;
import java.util.List;

/**
 * 数据统计 的Dao接口
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface StatisticsDeviceModelDao {

	/**
	 * 设备类型
	 *
	 * @param idshop 网站ID
	 * @param start  开始时间
	 * @param end    结束时间
	 * @return 设备类型记录
	 */
	List<BrandValue> brand(String idshop,RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;

	/**
	 * 统计所有设备信息
	 * @param idshop
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	BrandValue countBrand(String idshop, Date start, Date end)throws Exception;
}
