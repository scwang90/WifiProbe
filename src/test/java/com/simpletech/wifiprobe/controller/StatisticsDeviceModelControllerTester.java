package com.simpletech.wifiprobe.controller;

import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.constant.RankingType;
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
public class StatisticsDeviceModelControllerTester {
    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");
    @Autowired
    StatisticsDeviceModelController controller;

    @Test
    public void brand()throws Exception{
        Object result = controller.brand("1", RankingType.pv,10,0,0,Period.week,monthf.parse("2015-11-01"),monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
    }
}
