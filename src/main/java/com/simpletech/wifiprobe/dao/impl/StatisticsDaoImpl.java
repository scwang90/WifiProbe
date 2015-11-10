package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.StatisticsDao;
import com.simpletech.wifiprobe.mapper.StatisticsMapper;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.DeviceBrandValue;
import com.simpletech.wifiprobe.model.entity.DurationSpanValue;
import com.simpletech.wifiprobe.model.entity.DurationTrendValue;
import com.simpletech.wifiprobe.model.entity.EntryTrendValue;
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
    public int visitFrequencyMap(String idshop, int entry, int min, int max, Date start, Date end) throws Exception {
        return mapper.visitFrequencyMap(idshop, entry, min, max, start, end);
    }

//    @Override
//    public List<FrequencyMapValue> visitFrequencyMap(String idshop, Date start, Date end) throws Exception {
//        return mapper.visitFrequencyMap(idshop, start, end);
//    }

    @Override
    public int visitDurationMap(String idshop, int min, int max, Date start, Date end) throws Exception {
        return mapper.visitDurationMap(idshop, min, max, start, end);
    }

    @Override
    public List<Integer> visitPeriodMap(String idshop, int entry, Date start, Date end) throws Exception {
        return mapper.visitPeriodMap(idshop, entry, start, end);
    }

    @Override
    public DurationSpanValue visitDurationSpan(String idshop, int entry, int deep, int jump, Date start, Date end) throws Exception {
        return mapper.visitDurationSpan(idshop, entry, deep, jump, start, end);
    }

    @Override
    public List<DurationTrendValue> visitDurationTrendHour(String idshop, int entry, int deep, int jump, Date start, Date end) throws Exception {
        return mapper.visitDurationTrendHour(idshop, entry, deep, jump, start, end);
    }

    @Override
    public List<DurationTrendValue> visitDurationTrendDay(String idshop, int entry, int deep, int jump, Date start, Date end) throws Exception {
        return mapper.visitDurationTrendDay(idshop, entry, deep, jump, start, end);
    }

    @Override
    public List<DurationTrendValue> visitDurationTrendWeek(String idshop, int entry, int deep, int jump, Date start, Date end) throws Exception {
        return mapper.visitDurationTrendWeek(idshop, entry, deep, jump, start, end);
    }

    @Override
    public List<DurationTrendValue> visitDurationTrendMonth(String idshop, int entry, int deep, int jump, Date start, Date end) throws Exception {
        return mapper.visitDurationTrendMonth(idshop, entry, deep, jump, start, end);
    }

    @Override
    public List<EntryTrendValue> visitEntryTrendHour(String idshop, int entry, Date start, Date end) throws Exception {
        return mapper.visitEntryTrendHour(idshop, entry, start, end);
    }

    @Override
    public List<EntryTrendValue> visitEntryTrendDay(String idshop, int entry, Date start, Date end) throws Exception {
        return mapper.visitEntryTrendDay(idshop, entry, start, end);
    }

    @Override
    public List<EntryTrendValue> visitEntryTrendWeek(String idshop, int entry, Date start, Date end) throws Exception {
        return mapper.visitEntryTrendWeek(idshop, entry, start, end);
    }

    @Override
    public List<EntryTrendValue> visitEntryTrendMonth(String idshop, int entry, Date start, Date end) throws Exception {
        return mapper.visitEntryTrendMonth(idshop, entry, start, end);
    }

    @Override
    public List<DeviceBrandValue> deviceBrand(String idshop, int entry, String type, Date start, Date end, int limit, int skip) throws Exception {
        return mapper.deviceBrand(idshop, entry, type, start, end, limit, skip);
    }
}

