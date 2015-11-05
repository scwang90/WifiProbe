package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.StatisticsCustomerTypeDao;
import com.simpletech.wifiprobe.mapper.ShopMapper;
import com.simpletech.wifiprobe.mapper.StatisticsCustomerTypeMapper;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.CustomerTrendValue;
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
public class StatisticsCustomerTypeDaoImpl implements StatisticsCustomerTypeDao {

    @Autowired
    StatisticsCustomerTypeMapper mapper;
    @Autowired
    ShopMapper shopMapper;
    /**
     * 新老用户
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return
     * @throws Exception
     */
    @Override
    public List<CustomerValue> customer(String idshop, Date start, Date end) throws Exception {
        List<CustomerValue> list = mapper.customer(idshop, start, end);
        CustomerValue count=mapper.countCustomer(idshop, start, end);
        for (CustomerValue value : list) {

            value.setOv(value.getUv() - value.getNv());
            value.setNr(1f * value.getNv() / count.getUv());
            value.setOr(1f * value.getOv() / count.getUv());
        }
        return list;
    }

    @Override
    public List<LivenessValue> customerLiveness(String idshop, Date start, Date end) throws Exception {
        List<LivenessValue> values = new ArrayList<>();
        Shop shop = shopMapper.findById(idshop);
        CustomerValue countCustomer=mapper.countCustomer(idshop, start, end);

        if (shop != null) {
            String count = "" + shop.getConfigApiLiveness();
            count = count.matches("(\\d+,)+\\d+") ? count : "1,7,15,30";
            count = count + "," + Integer.MAX_VALUE;
            String[] counts = count.split(",");
            int lastValue = 1;
            for (String _count : counts) {
                List<LivenessValue> list=mapper.customerLiveness(idshop, lastValue, Integer.parseInt(_count), start, end);
                    for (LivenessValue value : list) {
                        value.setLive(lastValue + "~" + _count);
                        value.setRate(1f * value.getNum() / (countCustomer.getUv() - countCustomer.getNv()));
                        values.add(value);
                        lastValue = Integer.parseInt(_count) + 1;
                    }
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
     * @param idshop
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    @Override
    public List<CustomerTrendValue> customerTrend(String idshop,Period period, Date start, Date end) throws Exception {
        List<CustomerTrendValue> list= new ArrayList<>();
        CustomerValue count=mapper.countCustomer(idshop,start,end);
        switch (period) {
            case hour:
                list = mapper.customerTrendHour(idshop, start, end);
                break;
            case day:
                list = mapper.customerTrendDay(idshop,start, end);
                break;
            case week:
                list = mapper.customerTrendWeek(idshop, start, end);
                break;
            case month:
                list = mapper.customerTrendMonth(idshop, start, end);
                break;
        }
        for(CustomerTrendValue value:list){
            value.setOv(value.getUv() - value.getNv());
            value.setRnv(1f * value.getNv() / count.getUv());
            value.setRov(1f * value.getOv() / count.getUv());
        }
        return list;
    }

}

