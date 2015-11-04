package com.simpletech.wifiprobe.dao;

import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.BrandValue;
import com.simpletech.wifiprobe.model.entity.CustomerValue;
import com.simpletech.wifiprobe.model.entity.LivenessValue;

import java.util.Date;
import java.util.List;

/**
 * 顾客类型 的Dao接口
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface CustomerTypeStatisticsDao {

	/**
	 * 新老用户
	 *
	 * @param idshop 网站ID
	 * @param start  开始时间
	 * @param end    结束时间
	 * @return 新老用户
	 */
	List<CustomerValue> customerHour(String idshop, Date start, Date end) throws Exception;
	List<CustomerValue> customerDay(String idshop, Date start, Date end) throws Exception;
	List<CustomerValue> customerWeek(String idshop, Date start, Date end) throws Exception;
	List<CustomerValue> customerMonth(String idshop, Date start, Date end) throws Exception;

	/**
	 *
	 * @param idshop
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	List<LivenessValue> customerLiveness(String idshop, Period period, int min, int max, Date start, Date end) throws Exception;
//	List<LivenessValue> customerLivenessDay(String idshop, int min, int max, Date start, Date end)throws Exception;
//	List<LivenessValue> customerLivenessWeek(String idshop, int min, int max, Date start, Date end) throws Exception;
//	List<LivenessValue> customerLivenessMonth(String idshop, int min, int max, Date start, Date end) throws Exception;
}
