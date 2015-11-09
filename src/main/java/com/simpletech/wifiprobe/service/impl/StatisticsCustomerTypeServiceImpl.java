package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.StatisticsCustomerTypeDao;
import com.simpletech.wifiprobe.dao.ShopDao;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.*;
import com.simpletech.wifiprobe.service.StatisticsCustomerTypeService;
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
public class StatisticsCustomerTypeServiceImpl implements StatisticsCustomerTypeService {

    @Autowired
    StatisticsCustomerTypeDao dao;
    @Autowired
    ShopDao shopDao;

    @Override
    public List<IsNewCustomerValue> customer(String idshop, Date start, Date end) throws Exception {
        Shop shop=shopDao.findById(idshop);
        List<IsNewCustomerValue> values= dao.customer(idshop, shop.getConfigApiVisitDurationEnter().intValue(), start, end);
        int total=0;
        for(IsNewCustomerValue list:values){
            total+=list.getNum();
        }
        for(IsNewCustomerValue list1:values){
            list1.setDt(list1.getDt() / 24 / 60 / 60);//按天算
            list1.setVp(list1.getVp() / 24 / 60 / 60);//按天算
            list1.setRate(1f * list1.getNum() / total);
        }
        return values;
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
        List<LivenessValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        CustomerValue countCustomer=dao.countCustomer(idshop, start, end);

        if (shop != null) {
            String count = "" + shop.getConfigApiLiveness();
            count = count.matches("(\\d+,)+\\d+") ? count : "1,7,15,30";
            count = count + "," + Integer.MAX_VALUE;
            String[] counts = count.split(",");
            int lastValue = 1,total=0;
            for (String _count : counts) {
                LivenessValue value=new LivenessValue(lastValue,Float.parseFloat(_count),"天");

                List<LivenessValue> list=dao.customerLiveness(idshop,shop.getConfigApiVisitDurationEnter().intValue(), lastValue*24*60*60, Integer.parseInt(_count)*24*60*60, start, end);

                for (LivenessValue value1 : list) {
                    total+=value1.getNum();
                    value.setNum(value1.getNum());
                    if(total==0){
                        value.setRate(1f * 0);
                    }else{
                        value.setRate(1f * value1.getNum() / total);
                    }

                    value.setDt(value1.getDt()/24/60/60);
                    value.setVp(value1.getVp()/24/60/60);
                    values.add(value);

                }
                lastValue = Integer.parseInt(_count);
            }
        }

        return values;
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
