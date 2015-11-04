package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.CustomerTypeStatisticsDao;
import com.simpletech.wifiprobe.dao.DeviceModelStatisticsDao;
import com.simpletech.wifiprobe.mac.MacBrand;
import com.simpletech.wifiprobe.mapper.CustomerTypeStatisticsMapper;
import com.simpletech.wifiprobe.mapper.DeviceModelStatisticsMapper;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.BrandValue;
import com.simpletech.wifiprobe.model.entity.CustomerValue;
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
public class CustomerTypeStatisticsDaoImpl implements CustomerTypeStatisticsDao {

    @Autowired
    CustomerTypeStatisticsMapper mapper;

    @Override
    public List<CustomerValue> customerHour(String idshop, Date start, Date end) throws Exception {
        return mapper.customerHour(idshop, start, end);
    }

    @Override
    public List<CustomerValue> customerDay(String idshop, Date start, Date end) throws Exception {
        return mapper.customerDay(idshop, start, end);
    }

    @Override
    public List<CustomerValue> customerWeek(String idshop, Date start, Date end) throws Exception {
        return mapper.customerWeek(idshop, start, end);
    }

    @Override
    public List<CustomerValue> customerMonth(String idshop, Date start, Date end) throws Exception {
        return mapper.customerMonth(idshop, start, end);
    }

    /**
     * 活跃度统计
     * @param idshop
     * @param start
     * @param end
     * @return
     * @throws Exception
     */


    @Override
    public List<LivenessValue> customerLiveness(String idshop,Period period, int min, int max, Date start, Date end) throws Exception {
        List<LivenessValue> list= new ArrayList<>();
        switch (period) {
            case hour:
                list = mapper.customerLivenessHour(idshop,min,max, start, end);
                break;
            case day:
                list = mapper.customerLivenessDay(idshop,min,max, start, end);
                break;
            case week:
                list = mapper.customerLivenessWeek(idshop,min,max, start, end);
                break;
            case month:
                list = mapper.customerLivenessMonth(idshop,min,max, start, end);
                break;
        }
        return list;
    }

//    @Override
//    public List<LivenessValue> customerLivenessDay(String idshop, int min, int max, Date start, Date end) throws Exception {
//        return mapper.customerLivenessDay(idshop,min,max, start, end);
//    }
//
//    @Override
//    public List<LivenessValue> customerLivenessWeek(String idshop, int min, int max, Date start, Date end) throws Exception {
//        return mapper.customerLivenessWeek(idshop,min,max, start, end);
//    }
//
//    @Override
//    public List<LivenessValue> customerLivenessMonth(String idshop, int min, int max, Date start, Date end) throws Exception {
//        return mapper.customerLivenessMonth(idshop,min,max, start, end);
//    }
}

