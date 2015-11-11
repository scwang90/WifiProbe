package com.simpletech.wifiprobe.mac;

import com.simpletech.wifiprobe.mapper.StatisticsMapper;
import com.simpletech.wifiprobe.model.entity.DeviceBrandValue;
import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.*;

/**
 * mac地址 品牌测试类
 * Created by Administrator on 2015/11/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class MacBrandDatabaseTester {

    Map<String,List<String>> brandmap = new LinkedHashMap<>();

    @Test
    public void parserMapper() throws Exception {
        System.out.println(JacksonUtil.toJson(MacBrandMemory.parser("3CD92B")));
        System.out.println(JacksonUtil.toJson(MacBrandMemory.parserBrandMac("0050BA")));
    }

    @Autowired
    StatisticsMapper mapper;

    @Test
    public void databasequrey() throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\test\\resources\\premac.mem.out.txt"))) {
            List<DeviceBrandValue> values = mapper.doDeviceBrand("vt", 10000, 0);
            for (DeviceBrandValue value : values) {
                value.setName(value.getName() + ":" + MacBrandMemory.parser(value.getName()));
            }
            writer.print(JacksonUtil.toJson(values).replace("{","\n{"));
        }
    }

    @Test
    public void database() throws Exception {
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
                    brandmap.put(brands[0],list);
                }
                list.add(splits[0]);
            }
        }
        List<Map.Entry<String,List<String>>> list = new ArrayList<>(brandmap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, List<String>>>() {
            @Override
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                return Integer.compare(o1.getValue().size(),o2.getValue().size());
            }
        });
        Collections.reverse(list);

        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\test\\resources\\premac.mem.out.txt"))) {

            for (Map.Entry<String,List<String>> entry : list) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue().size());
                writer.println(entry.getKey());
                writer.println(entry.getValue().size());
            }

        }
    }

}
