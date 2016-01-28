package com.simpletech.wifiprobe.mapper.data;

import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 异常数据的Mapper层测试类
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class DataMapperTest {

    private static DateFormat full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    DataMapper mapper;

    @Transactional
    public void abnormalNewUserVisit() {
        List<Map<String,Object>> result = mapper.abnormalNewUserVisit(100, 0);
        System.out.println(JacksonUtil.toJsonNoException(result).replace("{", "\n{"));
        for (Map<String,Object> map : result) {
            Date min = mapper.abnormalNewUserVisitMin(map.get("mac_device").toString(),map.get("idshop").toString());
            System.out.println(full.format(min));
            mapper.abnormalNewUserVisitUpdate(map.get("mac_device").toString(),map.get("idshop").toString(),min);
        }
    }

    @Test
    public void testAbnormalNewUserVisit() {
        abnormalNewUserVisit();
    }
}