package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.StatisticsDao;
import com.simpletech.wifiprobe.mapper.StatisticsMapper;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.DurationValue;
import com.simpletech.wifiprobe.model.entity.FrequencyValue;
import com.simpletech.wifiprobe.model.entity.PeriodValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 数据库表t_url的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class StatisticsDaoImpl implements StatisticsDao {

    @Autowired
    StatisticsMapper mapper;

    @Override
    public List<Visit> visitmac(String idshop, String mac, Date start, Date end) throws Exception {
        return mapper.visitmac(idshop, mac, start, end);
    }

    @Override
    public int visitfrequency(String idshop, int min, int max, Date start, Date end) throws Exception {
        return mapper.visitfrequency(idshop, min, max, start, end);
    }

//    @Override
//    public List<FrequencyValue> visitfrequency(String idshop, Date start, Date end) throws Exception {
//        return mapper.visitfrequency(idshop, start, end);
//    }

    @Override
    public int visitduration(String idshop, int min, int max, Date start, Date end) throws Exception {
        return mapper.visitduration(idshop, min, max, start, end);
    }

    @Override
    public List<PeriodValue> visitperiod(String idshop, int minvisit, int minduration, Date start, Date end) throws Exception {
        return null;
    }

}

