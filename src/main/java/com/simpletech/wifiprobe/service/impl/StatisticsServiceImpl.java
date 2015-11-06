package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.ShopDao;
import com.simpletech.wifiprobe.dao.StatisticsDao;
import com.simpletech.wifiprobe.mac.MacBrand;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.*;
import com.simpletech.wifiprobe.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 统计API Service 实现
 * Created by 树朾 on 2015/9/25.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsDao dao;

    @Autowired
    ShopDao shopDao;

    @Override
    public List<Visit> visitmac(String idshop, String mac, Date start, Date end) throws Exception {
        List<Visit> visits = dao.visitmac(idshop, mac, start, end);
        for (Visit visit : visits) {
            visit.setEndBrand(MacBrand.parser(visit.getEndBrand()).getCompany());
        }
        return visits;
    }

    @Override
    public List<FrequencyMapValue> visitFrequencyMap(String idshop, Date start, Date end) throws Exception {
        List<FrequencyMapValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            String count = "" + shop.getConfigApiVisitCounts();
            count = count.matches("(\\d+,)+\\d+") ? count : "1,2,5";
            count = count + "," + Integer.MAX_VALUE;
            String[] counts = count.split(",");
            int lastValue = 1, total = 0;
            for (String _count : counts) {
                FrequencyMapValue value = new FrequencyMapValue(lastValue, Integer.valueOf(_count),"次");
                value.setNum(dao.visitFrequencyMap(idshop,(int)(shop.getConfigApiVisitDurationEnter()*60), lastValue, Integer.parseInt(_count), start, end));

                if (_count.equals(Integer.MAX_VALUE)) {
                    if (value.getNum() > 0) {
                        values.add(value);
                    }
                } else {
                    values.add(value);
                }
                total += value.getNum();
                lastValue = Integer.parseInt(_count) + 1;
            }
            for (FrequencyMapValue value : values) {
                value.setRate(1f * value.getNum() / total);
            }
        }
        return values;
    }

//    @Override
//    public List<FrequencyMapValue> visitFrequencyMap(String idshop, Date start, Date end) throws Exception {
//        List<FrequencyMapValue> values = new ArrayList<>();
//        Shop shop = shopDao.findById(idshop);
//        if (shop != null) {
//            String count = "" + shop.getConfigApiVisitCounts();
//            count = count.matches("(\\d+,)+\\d+") ? count : "1,2,5";
//            String[] counts = count.split(",");
//            List<FrequencyMapValue> frequencys = new ArrayList<>(dao.visitFrequencyMap(idshop, start, end));
//            String lastValue = "1", indexValue = "0";
//            FrequencyMapValue value = new FrequencyMapValue();
//            value.setNum(Integer.MAX_VALUE);
//            frequencys.add(value);
//            while (frequencys.size() > 0) {
//                FrequencyMapValue frequency = frequencys.get(0);
//                if (frequency.getNum() <= Integer.parseInt(indexValue)) {
//                    if (frequency.getNum() < Integer.MAX_VALUE) {
//                        value.setNum(value.getNum() + frequency.getNum());
//                    }
//                } else if (values.size() < counts.length) {
//                    indexValue = counts[values.size()];
//                    value = new FrequencyMapValue();
//                    if (lastValue.compareTo(indexValue) == 0) {
//                        value.setFre(indexValue);
//                    } else {
//                        value.setFre(lastValue + "~" + indexValue);
//                    }
//                    value.setNum(frequency.getNum() < Integer.MAX_VALUE ? frequency.getNum() : 0);
//                    lastValue = String.valueOf(Integer.parseInt(indexValue) + 1);
//                    values.add(value);
//                } else {
//                    indexValue = String.valueOf(Integer.MAX_VALUE);
//                    value = new FrequencyMapValue();
//                    value.setFre(lastValue + "~");
//                    value.setNum(frequency.getNum() < Integer.MAX_VALUE ? frequency.getNum() : 0);
//                    values.add(value);
//                }
//                if (frequency.getNum() != Integer.MAX_VALUE || values.size() >= counts.length) {
//                    frequencys.remove(0);
//                }
//            }
//        }
//        return values;
//    }

    @Override
    public List<DurationMapValue> visitDurationMap(String idshop, Date start, Date end) throws Exception {
        List<DurationMapValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            String duration = "" + shop.getConfigApiVisitDuration();
            duration = duration.matches("(\\d[\\d\\.]*,)+\\d[\\d\\.]*") ? duration : "5,30,60,120";
            duration = duration + "," + Integer.MAX_VALUE;
            String[] durations = duration.split(",");
            int total = 0;
            float lastValue = 0;
            for (String durate : durations) {
                DurationMapValue value = new DurationMapValue(lastValue,Float.parseFloat(durate),"分钟");
                value.setNum(dao.visitDurationMap(idshop, (int)(lastValue * 60), (int)(Float.parseFloat(durate) * 60), start, end));

                if (durate.equals(Integer.MAX_VALUE)) {
                    if (value.getNum() > 0) {
                        values.add(value);
                    }
                } else {
                    values.add(value);
                }
                total += value.getNum();
                lastValue = Float.parseFloat(durate);
            }
            for (DurationMapValue value : values) {
                value.setRate(1f * value.getNum() / total);
            }
        }
        return values;
    }

    @Override
    public List<PeriodMapValue> visitPeriodMap(String idshop, Date start, Date end) throws Exception {
        List<PeriodMapValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            String period = "" + shop.getConfigApiVisitPeriod();
            period = period.matches("(\\d[\\d\\.]*,)+\\d[\\d\\.]*") ? period : "1,2,4,7,14";
//            period = period + "," + (Integer.MAX_VALUE/24/60/60);
            String[] periods = period.split(",");

            List<Integer> list = dao.visitPeriodMap(idshop, (int) (shop.getConfigApiVisitDurationEnter() * 60), start, end);
            PeriodMapValue value = new PeriodMapValue();
            float lastValue = 1, indexValue = 0;
            list.add((Integer.MAX_VALUE / 24 / 60 / 60) * 24 * 60 * 60);
            while (list.size() > 0) {
                Integer avgperiod = list.get(0);
                if (avgperiod <= (int)(indexValue * 24 * 60 * 60)) {
                    if (avgperiod < Integer.MAX_VALUE) {
                        value.setNum(value.getNum() + 1);
                    }
                } else if (values.size() < periods.length) {
                    indexValue = Float.parseFloat(periods[values.size()]);
                    value = new PeriodMapValue(lastValue, indexValue, "天");
                    value.setNum(avgperiod < Integer.MAX_VALUE ? 1 : 0);
                    lastValue = indexValue + 1;
                    values.add(value);
                } else {
                    indexValue = Integer.MAX_VALUE / 24 / 60 / 60;
                    value = new PeriodMapValue(lastValue, Integer.MAX_VALUE, "天");
                    value.setNum(avgperiod < Integer.MAX_VALUE ? 1 : 0);
                    values.add(value);
                }
                if (avgperiod != Integer.MAX_VALUE || values.size() >= periods.length - 1) {
                    list.remove(0);
                }
            }
        }
        int total = 0;
        for (PeriodMapValue value : values) {
            total += value.getNum();
        }
        for (PeriodMapValue value : values) {
            value.setRate(1f * value.getNum() / total);
        }
        return values;
    }

    @Override
    public DurationSpanValue visitDurationSpan(String idshop, Date start, Date end) throws Exception {
        DurationSpanValue value = new DurationSpanValue();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            int entry = (int) (shop.getConfigApiVisitDurationEnter() * 60);
            int deep = (int) (shop.getConfigApiVisitDurationDeep() * 60);
            int jump = (int) (shop.getConfigApiVisitDurationJump() * 60);
            return dao.visitDurationSpan(idshop, entry, deep, jump, start, end);
        }
        return value;
    }

    @Override
    public List<DurationTrendValue> visitDurationTrend(String idshop, Period period, Date start, Date end) throws Exception {
        List<DurationTrendValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            int entry = (int) (shop.getConfigApiVisitDurationEnter() * 60);
            int jump = (int) (shop.getConfigApiVisitDurationJump() * 60);
            int deep = (int) (shop.getConfigApiVisitDurationDeep() * 60);
            switch (period) {
                case hour:
                    list = dao.visitDurationTrendHour(idshop, entry, deep, jump, start, end);
                    break;
                case day:
                    list = dao.visitDurationTrendDay(idshop, entry, deep, jump, start, end);
                    break;
                case week:
                    list = dao.visitDurationTrendWeek(idshop, entry, deep, jump, start, end);
                    break;
                case month:
                    list = dao.visitDurationTrendMonth(idshop, entry, deep, jump, start, end);
                    break;
            }
        }
        return list;
    }

    @Override
    public List<EntryTrendValue> visitEntryTrend(String idshop, Period period, Date start, Date end) throws Exception {
        List<EntryTrendValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            int entry = (int) (shop.getConfigApiVisitDurationEnter() * 60);
            switch (period) {
                case hour:
                    list = dao.visitEntryTrendHour(idshop, entry, start, end);
                    break;
                case day:
                    list = dao.visitEntryTrendDay(idshop, entry, start, end);
                    break;
                case week:
                    list = dao.visitEntryTrendWeek(idshop, entry, start, end);
                    break;
                case month:
                    list = dao.visitEntryTrendMonth(idshop, entry, start, end);
                    break;
            }
        }
        return list;
    }
}
