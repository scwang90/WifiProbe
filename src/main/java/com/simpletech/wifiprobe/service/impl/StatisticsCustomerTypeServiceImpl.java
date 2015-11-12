package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.StatisticsCustomerTypeDao;
import com.simpletech.wifiprobe.dao.ShopDao;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.constant.Level;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.*;
import com.simpletech.wifiprobe.service.StatisticsCustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 顾客类型 Service 实现
 * Created by 树朾 on 2015/9/25.
 */
@Service
public class StatisticsCustomerTypeServiceImpl implements StatisticsCustomerTypeService {

    @Autowired
    StatisticsCustomerTypeDao dao;
    @Autowired
    ShopDao shopDao;

    /**
     * 新老用户统计
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return
     * @throws Exception
     */
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
     * 客户活跃度-分布
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return
     * @throws Exception
     */
    @Override
    public List<LivenessValue> customerLiveness(String idshop, Date start, Date end) throws Exception {
        List<LivenessValue> values = new ArrayList<>();
        List<LivenessValue> ss = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        int entry=shop.getConfigApiVisitDurationEnter().intValue();
        LivenessTrendValue countCustomer=dao.countCustomer(idshop,entry ,start, end);

        if (shop != null) {
            String count = "" + shop.getConfigApiLiveness();
            count = count.matches("(\\d+,)+\\d+") ? count : "1,7,15,30";
//            count = count + "," + Integer.MAX_VALUE;
            String[] counts = count.split(",");
            int lastValue = 0,total=0;
            for (String _count : counts) {
                LivenessValue value=new LivenessValue(lastValue,Float.parseFloat(_count),"天");

                List<LivenessValue> list=dao.customerLiveness(idshop,shop.getConfigApiVisitDurationEnter().intValue(), lastValue*24*60*60, Integer.parseInt(_count)*24*60*60, start, end);

                for (LivenessValue value1 : list) {
                    if(lastValue==0){
                        value.setLive("高活跃度顾客");
                    }
                     if((lastValue!=Integer.parseInt(_count))&&lastValue!=0) {
                        if(total>0&&total<=1){
                            value.setLive("中活跃度顾客");
                        }
                        if(total>1&&total<=2){
                            value.setLive("低活跃度顾客");
                        }
                        if(total>2&&total<=3){
                            value.setLive("沉睡顾客");
                        }
                        if(total>3){
                            value.setLive("深度沉睡顾客");
                        }
                    }
                    value.setNum(value1.getNum());
                    if(countCustomer.getNum()==0){
                        value.setRate(1f * 0);
                    }else{
                        value.setRate(1f * value1.getNum() / countCustomer.getNum());
                    }

                    value.setDt(value1.getDt()/24/60/60);
                    value.setVp(value1.getVp()/24/60/60);
                    values.add(value);

                }
                lastValue = Integer.parseInt(_count);
                total++;
            }

        }

        return values;
    }

    /**
     * 活跃度-趋势
     * @param idshop
     * @param level 活跃度范围 high|middle|low|sleep|deepSleep
     * @param period
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public List<LivenessTrendValue> livenessTrend(String idshop,Level level, Period period,Date start, Date end) throws Exception {
        List<LivenessTrendValue> values = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        int entry=shop.getConfigApiVisitDurationEnter().intValue();
        LivenessTrendValue countCustomer=dao.countCustomer(idshop,entry, start, end);

        if (shop != null) {
            String count = "" + shop.getConfigApiLiveness();
            count = count.matches("(\\d+,)+\\d+") ? count : "1,7,15,30";
            count = count + "," + Integer.MAX_VALUE;
            String[] counts = count.split(",");
            int lastValue = 0,total=0;
            switch (level){
                case high:
                    List<LivenessTrendValue> list=dao.livenessTrend(idshop, period, shop.getConfigApiVisitDurationEnter().intValue(), lastValue * 24 * 60 * 60, Integer.parseInt(counts[0]) * 24 * 60 * 60, start, end);
                    for(LivenessTrendValue value : list){
                        value.setRemark("high");
                        if(countCustomer.getNum()!=0){
                            System.out.println(countCustomer.getNum());
                            value.setRate(1f*value.getNum()/countCustomer.getNum());
                        }else{
                            value.setRate(1f * 0);
                        }

                        values.add(value);
                    }
                    break;
                case middle:
                    List<LivenessTrendValue> list1=dao.livenessTrend(idshop, period, shop.getConfigApiVisitDurationEnter().intValue(), Integer.parseInt(counts[0]) * 24 * 60 * 60, Integer.parseInt(counts[1]) * 24 * 60 * 60, start, end);
                    for(LivenessTrendValue value : list1){
//                        total+=value.getNum();
                        value.setRemark("middle");
                        if(countCustomer.getNum()!=0){
                            value.setRate(1f * value.getNum() / countCustomer.getNum());
                        }else{
                            value.setRate(1f * 0);
                        }
                        values.add(value);
                    }
                    break;
                case low:
                    List<LivenessTrendValue> list2=dao.livenessTrend(idshop, period, shop.getConfigApiVisitDurationEnter().intValue(), Integer.parseInt(counts[1]) * 24 * 60 * 60, Integer.parseInt(counts[2]) * 24 * 60 * 60, start, end);
                    for(LivenessTrendValue value : list2){
//                        total+=value.getNum();
                        value.setRemark("low");
                        if(countCustomer.getNum()!=0){
                            value.setRate(1f * value.getNum() / countCustomer.getNum());
                        }else{
                            value.setRate(1f * 0);
                        }
                        values.add(value);
                    }
                    break;
                case sleep:
                    List<LivenessTrendValue> list3=dao.livenessTrend(idshop, period, shop.getConfigApiVisitDurationEnter().intValue(), Integer.parseInt(counts[2]) * 24 * 60 * 60, Integer.parseInt(counts[3]) * 24 * 60 * 60, start, end);
                    for(LivenessTrendValue value : list3){
//                        total+=value.getNum();
                        value.setRemark("sleep");
                        if(countCustomer.getNum()!=0){
                            value.setRate(1f * value.getNum() / countCustomer.getNum());
                        }else{
                            value.setRate(1f * 0);
                        }
                        values.add(value);
                    }
                    break;
                case deepsleep:
                    List<LivenessTrendValue> list4=dao.livenessTrend(idshop, period, shop.getConfigApiVisitDurationEnter().intValue(), Integer.parseInt(counts[3]) * 24 * 60 * 60, Integer.MAX_VALUE * 24 * 60 * 60, start, end);
                    for(LivenessTrendValue value : list4){
//                        total+=value.getNum();
                        value.setRemark("deepsleep");
                        if(countCustomer.getNum()!=0){
                            value.setRate(1f * value.getNum() / countCustomer.getNum());
                        }else{
                            value.setRate(1f * 0);
                        }

                        values.add(value);
                    }
                    break;
            }

//                lastValue = Integer.parseInt(_count);
//            }
        }
        return values;
    }
    /**
     * 到访顾客-趋势
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
