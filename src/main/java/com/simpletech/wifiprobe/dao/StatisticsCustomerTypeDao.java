package com.simpletech.wifiprobe.dao;

import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.*;

import java.util.Date;
import java.util.List;

/**
 * 顾客类型 的Dao接口
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface StatisticsCustomerTypeDao {

	/**
	 * 新老用户
	 *
	 * @param idshop 网站ID
	 * @param start  开始时间
	 * @param end    结束时间
	 * @return 新老用户
	 */
	List<IsNewCustomerValue> customer(String idshop,int entry,Date start, Date end) throws Exception;


	/**
	 * 老顾客活跃度
	 * @param idshop
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	List<LivenessValue> customerLiveness(String idshop,int entry, int min, int max, Date start, Date end) throws Exception;

	/**
	 * 顾客趋势
	 * @param idshop
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	List<CustomerTrendValue> customerTrend(String idshop, Period period, Date start, Date end) throws Exception;
//	List<LivenessValue> customerLivenessDay(String idshop, int min, int max, Date start, Date end)throws Exception;
//	List<LivenessValue> customerLivenessWeek(String idshop, int min, int max, Date start, Date end) throws Exception;
//	List<LivenessValue> customerLivenessMonth(String idshop, int min, int max, Date start, Date end) throws Exception;

	/**
	 * 汇总顾客信息
	 * @param idshop
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	CustomerValue countCustomer(String idshop, Date start, Date end) throws Exception;
}
