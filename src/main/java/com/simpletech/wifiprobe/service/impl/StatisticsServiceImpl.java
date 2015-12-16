package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.ShopDao;
import com.simpletech.wifiprobe.dao.StatisticsDao;
import com.simpletech.wifiprobe.mac.MacBrandMemory;
import com.simpletech.wifiprobe.mapper.api.StatisticsMapper;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.model.entity.*;
import com.simpletech.wifiprobe.service.StatisticsService;
import com.simpletech.wifiprobe.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 统计API Service 实现
 * Created by 树朾 on 2015/9/25.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsDao dao;

    @Autowired
    StatisticsMapper mapper;

    @Autowired
    ShopDao shopDao;


    @Override
    public List<VisitTimeMapValue> visitTimeMap(String idsite, TimeType type, int days, Date start, Date end) {
        List<VisitTimeMapValue> list = new ArrayList<>();
        switch (type) {
            case server:
                list = mapper.visitServerTimeMap(idsite, start, end);
                break;
            case local:
                list = mapper.visitLocalTimeMap(idsite, start, end);
                break;
        }
        int tip = 0, tpv = 0, tuv = 0, tvt = 0, count = days > 0 ? days : 1;
        for (VisitTimeMapValue value : list) {
            value.setAvgip((int) Math.rint(1f * value.getIp() / count + 0.4));
            value.setAvgvt((int) Math.rint(1f * value.getVt() / count + 0.4));
            value.setAvguv((int) Math.rint(1f * value.getUv() / count + 0.4));
            value.setAvgpv((int) Math.rint(1f * value.getPv() / count + 0.4));
            tip += value.getAvgip();
            tvt += value.getAvgvt();
            tuv += value.getAvguv();
            tpv += value.getAvgpv();
        }
        for (VisitTimeMapValue value : list) {
            value.setRip(1f * value.getAvgip() / tip);
            value.setRvt(1f * value.getAvgvt() / tvt);
            value.setRuv(1f * value.getAvguv() / tuv);
            value.setRpv(1f * value.getAvgpv() / tpv);
        }
        for (int i = 0, index = 0; i < 24; i++, index++) {
            if (index == list.size() || list.get(index).getTime() > i) {
                VisitTimeMapValue tmp = new VisitTimeMapValue();
                tmp.setTime(i);
                list.add(index, tmp);
            }
        }
        return list;
    }

    @Override
    public List<FrequencyMapValue> visitFrequencyMap(String idshop, Date start, Date end) throws Exception {
        List<FrequencyMapValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            String count = "" + shop.getConfigProbeApiVisitCounts();
            count = count.matches("(\\d+,)+\\d+") ? count : "1,2,5";
            count = count + "," + Integer.MAX_VALUE;
            String[] counts = count.split(",");
            int lastValue = 1, total = 0;
            for (String _count : counts) {
                FrequencyMapValue value = new FrequencyMapValue(lastValue, Integer.valueOf(_count), "次");
                value.setNum(mapper.visitFrequencyMap(idshop, (int) (shop.getConfigProbeApiVisitDurationEnter() * 60), lastValue, Integer.parseInt(_count), start, end));

                if (_count.equals(String.valueOf(Integer.MAX_VALUE))) {
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

    @Override
    public List<FrequencyMapValue> pastFrequencyMap(String idshop, Date start, Date end) throws Exception {
        List<FrequencyMapValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            String count = "" + shop.getConfigProbeApiVisitCounts();
            count = count.matches("(\\d+,)+\\d+") ? count : "1,2,5";
            count = count + "," + Integer.MAX_VALUE;
            String[] counts = count.split(",");
            int lastValue = 1, total = 0;
            for (String _count : counts) {
                FrequencyMapValue value = new FrequencyMapValue(lastValue, Integer.valueOf(_count), "次");
                value.setNum(mapper.pastFrequencyMap(idshop, (int) (shop.getConfigProbeApiVisitDurationEnter() * 60), lastValue, Integer.parseInt(_count), start, end));

                if (_count.equals(String.valueOf(Integer.MAX_VALUE))) {
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

    @Override
    public List<DurationMapValue> visitDurationMap(String idshop, Date start, Date end) throws Exception {
        List<DurationMapValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            String duration = "" + shop.getConfigProbeApiVisitDuration();
            duration = duration.matches("(\\d[\\d\\.]*,)+\\d[\\d\\.]*") ? duration : "5,30,60,120";
            duration = duration + "," + Integer.MAX_VALUE;
            String[] durations = duration.split(",");
            int total = 0;
            float lastValue = shop.getConfigProbeApiVisitDurationEnter().floatValue();
            for (String durate : durations) {
                if (Float.parseFloat(durate) > lastValue) {
                    DurationMapValue value = new DurationMapValue(lastValue, Float.parseFloat(durate), "分钟");
                    value.setNum(mapper.visitDurationMap(idshop, (int) (lastValue * 60), (int) (Float.parseFloat(durate) * 60), start, end));

                    if (durate.equals(String.valueOf(Integer.MAX_VALUE))) {
                        if (value.getNum() > 0) {
                            values.add(value);
                        }
                    } else {
                        values.add(value);
                    }
                    total += value.getNum();
                    lastValue = Float.parseFloat(durate);
                }
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
            shop = new ShopEntity(shop);
            String period = "" + shop.getConfigProbeApiVisitPeriod();
            period = period.matches("(\\d[\\d\\.]*,)+\\d[\\d\\.]*") ? period : "1,2,4,7,14";
//            period = period + "," + (Integer.MAX_VALUE/24/60/60);
            String[] periods = period.split(",");

            List<Integer> list = mapper.visitPeriodMap(idshop, (int) (shop.getConfigProbeApiVisitDurationEnter() * 60), start, end);
            PeriodMapValue value = new PeriodMapValue();
            float lastValue = 1, indexValue = 0;
            list.add((Integer.MAX_VALUE / 24 / 60 / 60) * 24 * 60 * 60);
            while (list.size() > 0) {
                Integer avgperiod = list.get(0);
                if (avgperiod <= (int) (indexValue * 24 * 60 * 60)) {
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
            shop = new ShopEntity(shop);
            int entry = (int) (shop.getConfigProbeApiVisitDurationEnter() * 60);
            int deep = (int) (shop.getConfigProbeApiVisitDurationDeep() * 60);
            int jump = (int) (shop.getConfigProbeApiVisitDurationJump() * 60);
            return mapper.visitDurationSpan(idshop, entry, deep, jump, start, end);
        }
        return value;
    }

    @Override
    public List<DurationTrendValue> visitDurationTrend(String idshop, Period period, Date start, Date end) throws Exception {
        List<DurationTrendValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            int entry = (int) (shop.getConfigProbeApiVisitDurationEnter() * 60);
            int jump = (int) (shop.getConfigProbeApiVisitDurationJump() * 60);
            int deep = (int) (shop.getConfigProbeApiVisitDurationDeep() * 60);
            switch (period) {
                case hour:
                    list = mapper.visitDurationTrendHour(idshop, entry, deep, jump, start, end);
                    break;
                case day:
                    list = mapper.visitDurationTrendDay(idshop, entry, deep, jump, start, end);
                    break;
                case week:
                    list = mapper.visitDurationTrendWeek(idshop, entry, deep, jump, start, end);
                    break;
                case month:
                    list = mapper.visitDurationTrendMonth(idshop, entry, deep, jump, start, end);
                    break;
            }
        }
        return list;
    }

    @Override
    public EntryTrendValue visitSpan(String idshop, Date start, Date end) throws Exception {
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            int entry = (int) (shop.getConfigProbeApiVisitDurationEnter() * 60);
            return mapper.visitSpan(idshop, entry, start, end);
        }
        throw new ServiceException("无效ID");
    }

    @Override
    public List<EntryTrendValue> visitTrend(String idshop, Period period, Date start, Date end) throws Exception {
        List<EntryTrendValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            int entry = (int) (shop.getConfigProbeApiVisitDurationEnter() * 60);
            switch (period) {
                case hour:
                    list = mapper.visitTrendHour(idshop, entry, start, end);
                    break;
                case day:
                    list = mapper.visitTrendDay(idshop, entry, start, end);
                    break;
                case week:
                    list = mapper.visitTrendWeek(idshop, entry, start, end);
                    break;
                case month:
                    list = mapper.visitTrendMonth(idshop, entry, start, end);
                    break;
            }
        }
        return list;
    }

    @Override
    public List<DeviceBrandValue> deviceBrandRanking(String idshop, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception {
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            int entry = (int) (shop.getConfigProbeApiVisitDurationEnter() * 60);
            List<DeviceBrandValue> list = mapper.deviceBrand(idshop, entry, ranktype.name(), start, end, limit, skip);
            for (DeviceBrandValue value : list) {
                value.setName(MacBrandMemory.parser(value.getName()));
            }
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<DeviceBrandValue> deviceBrandRank(String idshop, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception {
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            int entry = (int) (shop.getConfigProbeApiVisitDurationEnter() * 60);
            int olimit = limit;
            limit = limit + limit / 2;

            List<DeviceBrandValue> list = new ArrayList<>(), tmps;
            do {
                tmps = mapper.deviceBrand(idshop, entry, ranktype.name(), start, end, limit, skip);
                for (int i = 0; i < tmps.size() && list.size() < olimit - 1; i++) {
                    DeviceBrandValue value = tmps.get(i);
                    String brand = MacBrandMemory.parserNull(value.getName());
                    if (brand != null) {
                        value.setName(brand);
                        list.add(value);
                    }
                }
                skip += tmps.size();
            } while (list.size() < olimit - 1 && tmps.size() == limit);

            if (list.size() > 0) {
                DeviceBrandValue top = tmps.get(0);
                DeviceBrandValue total = new DeviceBrandValue();
                total.setVt(Float.valueOf(1f * top.getVt() / top.getRvt()).intValue());
                total.setUv(Float.valueOf(1f * top.getUv() / top.getRuv()).intValue());
                total.setPv(Float.valueOf(1f * top.getPv() / top.getRpv()).intValue());
                DeviceBrandValue other = new DeviceBrandValue();
                for (DeviceBrandValue value : list) {
                    other.setVt(other.getVt() + value.getVt());
                    other.setUv(other.getUv() + value.getUv());
                    other.setPv(other.getPv() + value.getPv());
                }
                other.setVt(total.getVt() - other.getVt());
                other.setUv(total.getUv() - other.getUv());
                other.setPv(total.getPv() - other.getPv());
                other.setRvt(1f * other.getVt() / total.getVt());
                other.setRuv(1f * other.getUv() / total.getUv());
                other.setRpv(1f * other.getPv() / total.getPv());

                if (other.getPv() > 0 || other.getVt() > 0 || other.getUv() > 0) {
                    other.setName("其他");
                    list.add(other);
                }
            }
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<UserTypeSpanValue> userTypeSpan(String idshop, Date start, Date end) throws Exception {
        List<UserTypeSpanValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            int entry = (int) (shop.getConfigProbeApiVisitDurationEnter() * 60);
            Map<String, Object> map = mapper.userTypeSpan(idshop, entry, start, end);
            UserTypeSpanValue value1 = new UserTypeSpanValue();
            value1.setIsNewUser(true);
            try {
                value1.setUv(Integer.valueOf(map.get("nuv").toString()));
                value1.setVt(Integer.valueOf(map.get("nvt").toString()));
                value1.setRuv(Double.valueOf(map.get("rnuv").toString()).floatValue());
                value1.setRvt(Double.valueOf(map.get("rnvt").toString()).floatValue());
                value1.setStay(Double.valueOf(map.get("nstay").toString()).intValue());
                try {
                    value1.setPeriod(Double.valueOf(map.get("nperiod").toString()).intValue());
                } catch (NullPointerException e) {
                    value1.setPeriod(0);
                }
            } catch (NullPointerException e) {
                value1.setUv(0);
                value1.setVt(0);
                value1.setRuv(0);
                value1.setRvt(0);
                value1.setStay(0);
                value1.setPeriod(0);
            }
            UserTypeSpanValue value2 = new UserTypeSpanValue();
            value2.setIsNewUser(false);
            try {
                value2.setUv(Integer.valueOf(map.get("ouv").toString()));
                value2.setVt(Integer.valueOf(map.get("ovt").toString()));
                value2.setRuv(Double.valueOf(map.get("rouv").toString()).floatValue());
                value2.setRvt(Double.valueOf(map.get("rovt").toString()).floatValue());
                value2.setStay(Double.valueOf(map.get("ostay").toString()).intValue());
                value2.setPeriod(Double.valueOf(map.get("operiod").toString()).intValue());
            } catch (NullPointerException e) {
                value2.setUv(0);
                value2.setVt(0);
                value2.setRuv(0);
                value2.setRvt(0);
                value2.setStay(0);
                value2.setPeriod(0);
            }
            list.add(value1);
            list.add(value2);
        }
        return list;
    }

    @Override
    public List<UserTypeTrendValue> userTypeTrend(String idshop, Period period, Date start, Date end) throws Exception {
        List<UserTypeTrendValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            int entry = (int) (shop.getConfigProbeApiVisitDurationEnter() * 60);
            switch (period) {
                case hour:
                    list = mapper.userTypeTrendHour(idshop, entry, start, end);
                    break;
                case day:
                    list = mapper.userTypeTrendDay(idshop, entry, start, end);
                    break;
                case week:
                    list = mapper.userTypeTrendWeek(idshop, entry, start, end);
                    break;
                case month:
                    list = mapper.userTypeTrendMonth(idshop, entry, start, end);
                    break;
            }
        }
        return list;
    }

    @Override
    public List<UserLivenessMapValue> userLivenessMap(String idshop, Date start, Date end) throws Exception {
        List<UserLivenessMapValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            int entry = (int) (shop.getConfigProbeApiVisitDurationEnter() * 60);
            String _map = "" + shop.getConfigProbeApiLiveness();
            _map = _map.matches("(\\d[\\d\\.]*,)+\\d[\\d\\.]*") ? _map : "1,7,15,30";
            _map = _map + "," + Integer.MAX_VALUE;
            String[] maps = _map.split(",");
            float lastValue = 0;
            for (String map : maps) {
                UserLivenessMapValue value = new UserLivenessMapValue(lastValue, Float.parseFloat(map), "天");
                int min = (int) (lastValue * 24 * 60 * 60);
                int max = (int) (Float.parseFloat(map) * 24 * 60 * 60);
                UserLivenessTrendValue trendValue = mapper.userLivenessMap(idshop, entry, min, max, start, end);
                value.setUv(trendValue.getUv());
                value.setVt(trendValue.getVt());
                if (map.equals(String.valueOf(Integer.MAX_VALUE))) {
                    if (value.getUv() > 0 || value.getVt() > 0) {
                        list.add(value);
                    }
                } else {
                    list.add(value);
                }
                lastValue = Float.parseFloat(map);
            }
            String[] remarks = {"高活跃度顾客", "中活跃度顾客", "低活跃度顾客", "沉睡顾客", "深度沉睡顾客"};
            for (int i = 0; i < list.size(); i++) {
                if (i < remarks.length) {
                    list.get(i).setRemark(remarks[i]);
                } else {
                    list.get(i).setRemark(remarks[remarks.length - 1]);
                }
            }
        }
        return list;
    }

    @Override
    public List<UserLivenessTrendMapValue> userLivenessTrend(String idshop, Period period, Date start, Date end) throws Exception {
        List<UserLivenessTrendMapValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            int entry = (int) (shop.getConfigProbeApiVisitDurationEnter() * 60);
            String _map = "" + shop.getConfigProbeApiLiveness();
            _map = _map.matches("(\\d[\\d\\.]*,)+\\d[\\d\\.]*") ? _map : "1,7,15,30";
            _map = _map + "," + Integer.MAX_VALUE;
            String[] maps = _map.split(",");
            float lastValue = 0;
            for (String map : maps) {
                UserLivenessTrendMapValue value = new UserLivenessTrendMapValue(lastValue, Float.parseFloat(map), "天");
                int min = (int) (lastValue * 24 * 60 * 60);
                int max = (int) (Float.parseFloat(map) * 24 * 60 * 60);
                switch (period) {
                    case hour:
                        value.setTrend(mapper.userLivenessTrendHour(idshop, entry, min, max, start, end));
                        break;
                    case day:
                        value.setTrend(mapper.userLivenessTrendDay(idshop, entry, min, max, start, end));
                        break;
                    case week:
                        value.setTrend(mapper.userLivenessTrendWeek(idshop, entry, min, max, start, end));
                        break;
                    case month:
                        value.setTrend(mapper.userLivenessTrendMonth(idshop, entry, min, max, start, end));
                        break;
                }
                if (map.equals(String.valueOf(Integer.MAX_VALUE))) {
                    if (value.getTrend() != null && value.getTrend().size() > 0) {
                        list.add(value);
                    }
                } else {
                    list.add(value);
                }
                lastValue = Float.parseFloat(map);
            }
            String[] remarks = {"高活跃度顾客", "中活跃度顾客", "低活跃度顾客", "沉睡顾客", "深度沉睡顾客"};
            for (int i = 0; i < list.size(); i++) {
                if (i < remarks.length) {
                    list.get(i).setRemark(remarks[i]);
                } else {
                    list.get(i).setRemark(remarks[remarks.length - 1]);
                }
            }
        }
        return list;
    }

    @Override
    public int onlineProbe(String idshop) throws Exception {
        Date time = new Date(System.currentTimeMillis() - 5 * 60 * 1000);
        return mapper.onlineProbe(idshop, time);
    }

    @Override
    public int onlineUser(String idshop) throws Exception {
        Date time = new Date(System.currentTimeMillis() - 5 * 60 * 1000);
        return mapper.onlineUser(idshop, time);
    }

    @Override
    public List<OnlineValue> onlineProbeAll() throws Exception {
        Date time = new Date(System.currentTimeMillis() - 5 * 60 * 1000);
        return mapper.onlineProbeAll(time);
    }

    @Override
    public List<OnlineValue> onlineUserAll() throws Exception {
        Date time = new Date(System.currentTimeMillis() - 5 * 60 * 1000);
        return mapper.onlineUserAll(time);
    }
}
