package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.StatisticsCustomerTypeDao;
import com.simpletech.wifiprobe.dao.ShopDao;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.CustomerTrendValue;
import com.simpletech.wifiprobe.model.entity.CustomerValue;
import com.simpletech.wifiprobe.model.entity.LivenessValue;
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
    public List<CustomerValue> customer(String idshop, Date start, Date end) throws Exception {
       return dao.customer(idshop,start,end);
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
            int lastValue = 0;
            for (String _count : counts) {
//                LivenessValue value=new LivenessValue();


                List<LivenessValue> list=dao.customerLiveness(idshop, lastValue*24*60*60, Integer.parseInt(_count)*24*60*60, start, end);
//                value.setNum(0);
//                if (value.getLive().indexOf("" + Integer.MAX_VALUE) > 0) {
//                    if (value.getNum() > 0) {
//                        value.setDur(value.getDur().replace("" + Integer.MAX_VALUE, ""));
//                        values.add(value);
//                    }
//                } else {
//                    values.add(value);
//                }
                for (LivenessValue value : list) {
//                    value1.setNum();
                    value.setLive(lastValue + "~" + _count);
                    if (value.getLive().indexOf("" + Integer.MAX_VALUE) > 0) {
                        if (value.getNum() >=0) {
                            value.setLive(value.getLive().replace("" + Integer.MAX_VALUE, ""));
//                            values.add(value);
                        }
                    }
//                    else {
//                        values.add(value);
//                    }
                    value.setRate(1f * value.getNum() / (countCustomer.getUv() - countCustomer.getNv()));
                    values.add(value);

                }
                lastValue = Integer.parseInt(_count) + 1;
            }
        }
//        //排重
//        for (int i = 0; i < values.size(); i++) {
//            //逐个比对
//            for (int j = i+1; j < values.size(); j++) {
//                //判断是否相等
//                //相等移除对象
//                if(values.get(i).getLive().equals(values.get(j).getLive())){
//                    values.get(i).setNum(values.get(i).getNum() + values.get(j).getNum());
//                    values.get(i).setDt(values.get(i).getDt() + values.get(j).getDt());
//                    values.get(i).setVp(values.get(i).getVp() + values.get(j).getVp());
//                    values.get(i).setRate(values.get(i).getRate() + values.get(j).getRate());
//                    values.remove(j);
//                    j--;
//                }
//            }
//        }
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
