package com.simpletech.wifiprobe.dao;

import com.simpletech.wifiprobe.util.JacksonUtil;
import javafx.scene.input.DataFormat;
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
public class DeviceModelStatisticsDaoTester {
    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");
    @Autowired
    DeviceModelStatisticsDao dao;

    @Test
    public void brand()throws Exception{
        Object result = dao.brand("1", monthf.parse("2015-11-01"),monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
    }
}
