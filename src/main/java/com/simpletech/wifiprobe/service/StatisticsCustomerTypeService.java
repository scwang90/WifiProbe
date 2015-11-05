package com.simpletech.wifiprobe.service;

import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.CustomerTrendValue;
import com.simpletech.wifiprobe.model.entity.CustomerValue;
import com.simpletech.wifiprobe.model.entity.LivenessValue;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service
 * Created by 树朾 on 2015/9/25.
 */
public interface StatisticsCustomerTypeService {

    /**
     * 新老用户
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    List<CustomerValue> customer(String idshop, Date start, Date end) throws Exception;

    /**
     * 老用户活跃度
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return
     * @throws Exception
     */
    List<LivenessValue> customerLiveness(String idshop, Date start, Date end) throws Exception;

    /**
     * 用户活跃度
     * @param idshop 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return
     * @throws Exception
     */
    List<CustomerTrendValue> customerTrend(String idshop, Period period, Date start, Date end) throws Exception;
}
