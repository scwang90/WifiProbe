package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.StatisticsCustomerTypeDao;
import com.simpletech.wifiprobe.dao.ShopDao;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.CustomerTrendValue;
import com.simpletech.wifiprobe.model.entity.CustomerValue;
import com.simpletech.wifiprobe.model.entity.LivenessValue;
import com.simpletech.wifiprobe.service.StatisticsCustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service 实现
 * Created by 树朾 on 2015/9/25.
 */
@Service
public class StatisticsCustomerTypeServiceImpl implements StatisticsCustomerTypeService {

    @Autowired
    StatisticsCustomerTypeDao dao;
    @Autowired
    ShopDao shopDao;

    @Override
    public List<CustomerValue> customer(String idshop, Date start, Date end) throws Exception {
       return dao.customer(idshop,start,end);
    }

    /**
     * 客户活跃度统计
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return
     * @throws Exception
     */
    @Override
    public List<LivenessValue> customerLiveness(String idshop, Date start, Date end) throws Exception {
        return dao.customerLiveness(idshop,start,end);
    }
    /**
     * 客户趋势统计
     * @param idshop 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return
     * @throws Exception
     */
    @Override
    public List<CustomerTrendValue> customerTrend(String idshop, Period period, Date start, Date end) throws Exception {
        return dao.customerTrend(idshop,period,start,end);
    }
}
