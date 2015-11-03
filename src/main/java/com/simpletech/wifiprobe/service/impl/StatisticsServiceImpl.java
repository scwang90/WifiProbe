package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.ShopDao;
import com.simpletech.wifiprobe.dao.StatisticsDao;
import com.simpletech.wifiprobe.mac.MacBrand;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.DurationValue;
import com.simpletech.wifiprobe.model.entity.FrequencyValue;
import com.simpletech.wifiprobe.model.entity.PeriodValue;
import com.simpletech.wifiprobe.service.StatisticsService;
import com.simpletech.wifiprobe.util.AfStringUtil;
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
    public List<FrequencyValue> visitfrequency(String idshop, Date start, Date end) throws Exception {
        List<FrequencyValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            String count = "" + shop.getConfigApiVisitCounts();
            count = count.matches("(\\d+,)+\\d+") ? count : "1,2,5";
            count = count + "," + Integer.MAX_VALUE;
            String[] counts = count.split(",");
            int lastValue = 1,total = 0;
            for (String _count : counts) {
                FrequencyValue value = new FrequencyValue();
                if (lastValue == Integer.valueOf(_count)) {
                    value.setFre(_count);
                } else {
                    value.setFre(lastValue + "~" + _count);
                }
                value.setNum(dao.visitfrequency(idshop, lastValue, Integer.parseInt(_count), start, end));

                if (value.getFre().indexOf(""+Integer.MAX_VALUE) > 0) {
                    if (value.getNum() > 0) {
                        value.setFre(value.getFre().replace("" + Integer.MAX_VALUE, ""));
                        values.add(value);
                    }
                } else {
                    values.add(value);
                }
                total += value.getNum();
                lastValue = Integer.parseInt(_count) + 1;
            }
            for (FrequencyValue value : values) {
                value.setRate(1f*value.getNum()/total);
            }
        }
        return values;
    }
//    @Override
//    public List<FrequencyValue> visitfrequency(String idshop, Date start, Date end) throws Exception {
//        List<FrequencyValue> values = new ArrayList<>();
//        Shop shop = shopDao.findById(idshop);
//        if (shop != null) {
//            String count = "" + shop.getConfigApiVisitCounts();
//            count = count.matches("(\\d+,)+\\d+") ? count : "1,2,5";
//            String[] counts = count.split(",");
//            List<FrequencyValue> frequencys = new ArrayList<>(dao.visitfrequency(idshop, start, end));
//            String lastValue = "1", indexValue = "0";
//            FrequencyValue value = new FrequencyValue();
//            value.setNum(Integer.MAX_VALUE);
//            frequencys.add(value);
//            while (frequencys.size() > 0) {
//                FrequencyValue frequency = frequencys.get(0);
//                if (frequency.getNum() <= Integer.parseInt(indexValue)) {
//                    if (frequency.getNum() < Integer.MAX_VALUE) {
//                        value.setNum(value.getNum() + frequency.getNum());
//                    }
//                } else if (values.size() < counts.length) {
//                    indexValue = counts[values.size()];
//                    value = new FrequencyValue();
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
//                    value = new FrequencyValue();
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
    public List<DurationValue> visitduration(String idshop, Date start, Date end) throws Exception {
        List<DurationValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            String duration = "" + shop.getConfigApiVisitDuration();
            duration = duration.matches("(\\d+,)+\\d+") ? duration : "5,30,60,120";
            duration = duration + "," + Integer.MAX_VALUE;
            String[] durations = duration.split(",");
            int lastValue = 0,total = 0;
            for (String durate : durations) {
                DurationValue value = new DurationValue();
                value.setDur(lastValue + "-" + durate);
                value.setNum(dao.visitduration(idshop, lastValue * 100, Integer.parseInt(durate) * 100, start, end));

                if (value.getDur().indexOf(""+Integer.MAX_VALUE) > 0) {
                    if (value.getNum() > 0) {
                        value.setDur(value.getDur().replace(""+Integer.MAX_VALUE,""));
                        values.add(value);
                    }
                } else {
                    values.add(value);
                }
                total += value.getNum();
                lastValue = Integer.parseInt(durate);
            }
            for (DurationValue value : values) {
                value.setRate(1f*value.getNum()/total);
            }
        }
        return values;
    }

    @Override
    public List<PeriodValue> visitperiod(String idshop, Date start, Date end) throws Exception {
        List<PeriodValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
//            String period = "" + shop.getConfigApiVisitPeriod();
//            period = period.matches("(\\d+,)+\\d+") ? period : "5,30,60,120";
//            period = period + "," + Integer.MAX_VALUE;
//            String[] periods = period.split(",");

        }
        return values;
    }
}
