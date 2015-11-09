package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.StatisticsCustomerTypeDao;
import com.simpletech.wifiprobe.mapper.ShopMapper;
import com.simpletech.wifiprobe.mapper.StatisticsCustomerTypeMapper;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.CustomerTrendValue;
import com.simpletech.wifiprobe.model.entity.CustomerValue;
import com.simpletech.wifiprobe.model.entity.IsNewCustomerValue;
import com.simpletech.wifiprobe.model.entity.LivenessValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据库表t_visit的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class StatisticsCustomerTypeDaoImpl implements StatisticsCustomerTypeDao {

    @Autowired
    StatisticsCustomerTypeMapper mapper;
    /**
     * 新老用户
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return
     * @throws Exception
     */
    @Override
    public List<IsNewCustomerValue> customer(String idshop,int entry, Date start, Date end) throws Exception {
        return mapper.customer(idshop, entry,start, end);
    }

    @Override
    public List<LivenessValue> customerLiveness(String idshop,int entry,int min, int max, Date start, Date end) throws Exception {

        return mapper.customerLiveness(idshop,entry,min,max,start,end);
    }
    /**
     * 客户趋势统计
     * @param idshop
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    @Override
    public List<CustomerTrendValue> customerTrend(String idshop,Period period, Date start, Date end) throws Exception {
        List<CustomerTrendValue> list= new ArrayList<>();
        CustomerValue count=mapper.countCustomer(idshop,start,end);
        switch (period) {
            case hour:
                list = mapper.customerTrendHour(idshop, start, end);
                break;
            case day:
                list = mapper.customerTrendDay(idshop,start, end);
                break;
            case week:
                list = mapper.customerTrendWeek(idshop, start, end);
                break;
            case month:
                list = mapper.customerTrendMonth(idshop, start, end);
                break;
        }
        for(CustomerTrendValue value:list){
            value.setOv(value.getUv() - value.getNv());
            value.setRnv(1f * value.getNv() / value.getUv());
            value.setRov(1f * value.getOv() / value.getUv());
        }
        return list;
    }

    public CustomerValue countCustomer(String idshop, Date start, Date end)throws Exception{
        return mapper.countCustomer(idshop,start,end);
    }
}

