package com.simpletech.wifiprobe.mapper;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.simpletech.wifiprobe.mac.MacBrandMemory;
import com.simpletech.wifiprobe.model.MacBrand;
import com.simpletech.wifiprobe.util.AfReflecter;
import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;

/**
 * 品牌测试类
 * Created by Administrator on 2015/11/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-*.xml"})
public class MacBrandMapperTest {

    @Autowired
    ComboPooledDataSource dataSource;

    @Test
    public void insertAll() throws Exception {
        int index = 0,last = 0;
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        Object memory = AfReflecter.getMember(MacBrandMemory.class, "memory");
        if (memory instanceof Map) {
            Map<String,String[]> map = Map.class.cast(memory);
            for (Map.Entry<String,String[]> entry : map.entrySet()) {
                MacBrand model = new MacBrand();
                model.setName(entry.getValue()[0]);
                if (entry.getValue().length > 1) {
                    model.setRemark(entry.getValue()[1]);
                } else {
                    model.setRemark(entry.getValue()[0]);
                }
                model.setMac(entry.getKey());
                String sql = "INSERT INTO mac_brand ( mac , name , remark ) VALUES ( '%s' , '%s' , '%s' )";
                statement.addBatch(String.format(sql,model.getMac(),model.getName().replace("'", " "),model.getRemark().replace("'"," ")));
                if (++index % 1000 == 0) {
                    System.out.println(last + "-" + index + ":正在提交....");
                    int[] ints = statement.executeBatch();
                    connection.commit();
                    System.out.println(last + "-" + index + ":提交完成");
                    System.out.println(JacksonUtil.toJson(ints) + "------------------------------------");
                    last = index;
                }
            }
            System.out.println(last + "-" + index + ":正在提交....");
            int[] ints = statement.executeBatch();
            connection.commit();
            System.out.println(last + "-" + index + ":提交完成");
            System.out.println(JacksonUtil.toJson(ints) + "====================================");
        }
    }

}