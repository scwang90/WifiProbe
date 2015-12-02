package com.simpletech.wifiprobe.mac;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.simpletech.wifiprobe.mapper.api.StatisticsMapper;
import com.simpletech.wifiprobe.model.entity.DeviceBrandValue;
import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

/**
 * mac地址 品牌测试类
 * Created by Administrator on 2015/11/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class MacBrandDatabaseTester {

    @Test
    public void parserMapper() throws Exception {
        System.out.println(JacksonUtil.toJson(MacBrandMemory.parser("3CD92B")));
        System.out.println(JacksonUtil.toJson(MacBrandMemory.parserBrandMac("0050BA")));
    }

    @Autowired
    StatisticsMapper mapper;

    /**
     * 先缓存数据库数据，用于平凡测试 premac.mem.out.txt
     * @throws Exception
     */
    @Test
    public void databasequrey() throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\test\\resources\\premac.mem.out.txt"))) {
            int max = 10000;
            List<DeviceBrandValue> values = mapper.doDeviceBrand("vt", max, 0);
            if (max <= values.size()) {
                throw new NullPointerException("max(" + max + ") <= values.size(" + values.size() + ")");
            }
            for (DeviceBrandValue value : values) {
                value.setName(value.getName() + ":" + MacBrandMemory.parserBrandMac(value.getName()));
            }
            writer.print(JacksonUtil.toJson(values).replace("{", "\n{"));
        }
    }

    @Autowired
    ComboPooledDataSource dataSource;

    @Test
    public void databasefilequrey() throws Exception {
        InputStream stream = ClassLoader.getSystemResourceAsStream("premac.mem.out.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line, content = "";
            while ((line = reader.readLine()) != null) {
                content += line;
            }
            int max = 10000;
            List<DeviceBrandValue> outs = mapper.doDeviceBrand("vt", max, 0);
            if (max <= outs.size()) {
                throw new NullPointerException("max("+max+") <= outs.size("+outs.size()+")");
            }
            for (DeviceBrandValue value : outs) {
                value.setName(value.getName() + ":" + MacBrandMemory.parserBrandMac(value.getName()));
            }
//            List<DeviceBrandValue> outs = JacksonUtil.toObjects(content, DeviceBrandValue.class);
//            List<DeviceBrandValue> values = JacksonUtil.toObjects(content, DeviceBrandValue.class);
//
//            for (DeviceBrandValue value : values) {
//                String[] splits = value.getName().split(":");
//                if (splits.length == 2 && !splits[0].equals(splits[1])) {
//                    outs.add(value);
//                }
//            }
//
//            System.out.print(JacksonUtil.toJson(outs).replace("{", "\n{"));

            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();

            int index = 0, last = 0;
            for (DeviceBrandValue out : outs) {
                String[] splits = out.getName().split(":");
                if (splits.length == 2 && !splits[0].equals(splits[1])) {
                    String sql = "UPDATE t_visit SET end_brand='" + splits[1] + "' WHERE end_brand='" + splits[0] + "'";
                    statement.addBatch(sql);
                    if (++index % 200 == 0) {
                        System.out.println(last + "-" + index + ":正在提交....");
                        int[] ints = statement.executeBatch();
                        connection.commit();
                        System.out.println(last + "-" + index + ":提交完成");
                        System.out.println(JacksonUtil.toJson(ints) + "------------------------------------");
                        last = index;
                    }
                } else {
//                    System.err.println("过滤数据:" + out.getName());
                }
            }

            System.out.println(last + "-" + index + ":正在提交....");
            int[] ints = statement.executeBatch();
            connection.commit();
            System.out.println(last + "-" + index + ":提交完成");
            System.out.println(JacksonUtil.toJson(ints) + "====================================");
        }
    }

    @Test
    public void doublet() throws Exception {
        Map<String, List<String>> brandmap = new LinkedHashMap<>();
        InputStream stream = ClassLoader.getSystemResourceAsStream("premac.mem.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] splits = line.trim().split(":");
                String[] brands = splits[1].split(",");
                List<String> list = brandmap.get(brands[0]);
                if (list == null) {
                    list = new ArrayList<>();
                    brandmap.put(brands[0], list);
                }
                list.add(splits[0]);
            }
        }
        List<Map.Entry<String, List<String>>> list = new ArrayList<>(brandmap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, List<String>>>() {
            @Override
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                return Integer.compare(o1.getValue().size(), o2.getValue().size());
            }
        });
        Collections.reverse(list);

        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\test\\resources\\premac.mem.out.txt"))) {
            for (Map.Entry<String, List<String>> entry : list) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue().size());
                writer.println(entry.getKey());
                writer.println(entry.getValue().size());
            }
        }
    }

}
