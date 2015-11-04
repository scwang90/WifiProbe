package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.CustomerTypeStatisticsDao;
import com.simpletech.wifiprobe.dao.ShopDao;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.CustomerValue;
import com.simpletech.wifiprobe.model.entity.LivenessValue;
import com.simpletech.wifiprobe.service.CustomerTypeStatisticsService;
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
public class CustomerTypeStatisticsServiceImpl implements CustomerTypeStatisticsService {

    @Autowired
    CustomerTypeStatisticsDao dao;
    @Autowired
    ShopDao shopDao;

    @Override
    public List<CustomerValue> customer(String idshop, Period period, Date start, Date end) throws Exception {
        List<CustomerValue> list = new ArrayList<>();
        switch (period) {
            case hour:
                list = dao.customerHour(idshop, start, end);
                break;
            case day:
                list = dao.customerDay(idshop, start, end);
                break;
            case week:
                list = dao.customerWeek(idshop, start, end);
                break;
            case month:
                list = dao.customerMonth(idshop, start, end);
                break;
        }

        for (CustomerValue value : list) {

            value.setOv(value.getUv() - value.getNv());
            value.setNr(1f * value.getNv() / value.getUv());
            value.setOr(1f * value.getOv() / value.getUv());
            value.setDt(1f * value.getDt() / value.getUv());
            value.setVp(1f * value.getVp() / value.getUv());
        }
        return list;
    }

    /**
     * 客户活跃度统计
     * @param idshop 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return
     * @throws Exception
     */
    @Override
    public List<LivenessValue> customerLiveness(String idshop, Period period, Date start, Date end) throws Exception {

        List<LivenessValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        System.out.println(shop.getConfigApiLiveness());
        if (shop != null) {
            String count = "" + shop.getConfigApiVisitCounts();
            count = count.matches("(\\d+,)+\\d+") ? count : "1,2,5";
            count = count + "," + Integer.MAX_VALUE;
            String[] counts = count.split(",");
            int lastValue = 1, total = 0;
            for (String _count : counts) {
//                LivenessValue value = new LivenessValue();
//                if (lastValue == Integer.valueOf(_count)) {
//                    value.setLive(_count);
//                } else {
//                    value.setLive(lastValue + "~" + _count);
//                }
//                value.setNum(dao.visitFrequencyMap(idshop, lastValue, Integer.parseInt(_count), start, end));
                List<LivenessValue> list=dao.customerLiveness(idshop, period, lastValue, Integer.parseInt(_count), start, end);
                for(LivenessValue value:list){
                    if (lastValue == Integer.valueOf(_count)) {
                        value.setLive(_count);
                    } else {
                         value.setLive(lastValue + "~" + _count);
                    }
                    value.setRate(1f * value.getNum() / value.getUv());
                    values.add(value);
                }
//                if (value.getLive().indexOf("" + Integer.MAX_VALUE) > 0) {
//                    if (value.getNum() > 0) {
//                        value.setLive(value.getLive().replace("" + Integer.MAX_VALUE, ""));
//                        values.add(value);
//                    }
//                } else {
//                    values.add(value);
//                }
//                total += value.getNum();
                lastValue = Integer.parseInt(_count) + 1;
            }
//            for (FrequencyMapValue value : values) {
//                value.setRate(1f * value.getNum() / total);
//            }
        }
        return values;
    }
}
