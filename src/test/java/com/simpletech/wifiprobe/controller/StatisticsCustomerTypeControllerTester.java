package com.simpletech.wifiprobe.controller;

import com.simpletech.wifiprobe.model.constant.Level;
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
 * Created by ChenQi on 2015/11/3 14:28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsCustomerTypeControllerTester {
    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");
    @Autowired
    StatisticsCustomerTypeController controller;

    @Test
    public void customer()throws Exception{
        Object result = controller.customer("1",null,null, monthf.parse("2015-11-01"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
    }
    @Test
    public void customerTrend() throws Exception{
        Object result = controller.customerTrend("1", Period.month, null, null, monthf.parse("2015-11-01"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
    }
    @Test
    public void livenessTrend() throws Exception{
        Object result = controller.customerLivenessTrend("1", Level.middle, Period.day, null, null, monthf.parse("2015-11-06"), monthf.parse("2015-11-31"));
        System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
    }

    @Test
    public void customerLiveness() throws Exception{
        Object result = controller.customerLiveness("1", null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
    }
}
