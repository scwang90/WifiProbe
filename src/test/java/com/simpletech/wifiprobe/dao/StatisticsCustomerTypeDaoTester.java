package com.simpletech.wifiprobe.dao;

import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * WifiProbe
 * Created by ChenQi on 2015/11/3 14:18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsCustomerTypeDaoTester {
    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");
    @Autowired
    StatisticsCustomerTypeDao dao;

    @Test
    public void customer()throws Exception{
        Object result = dao.customer("1", 0,monthf.parse("2015-11-01"),monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
    }

    @Test
    public void customerTrend()throws Exception{
        Object result = dao.customerTrend("1",Period.week, monthf.parse("2015-11-01"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
    }

    @Test
    public void customerLiveness()throws Exception{
        Object result = dao.customerLiveness("1",1,1,12, monthf.parse("2015-11-01"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
    }
}
