package com.simpletech.wifiprobe.mac;

import com.simpletech.wifiprobe.model.MacLog;
import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * mac地址 品牌测试类
 * Created by Administrator on 2015/11/2.
 */
public class MacBrandMemoryTester {

    Map<String,String> nkeys = new HashMap<String,String>(){{
        put("SHENZHEN","Shenzhen");
        put("SICHUAN","SiChuan");
        put("GUANGZHOU","GuangZhou");
        put("SHANGHAI","Shanghai");
        put("ZHEJIANG","Zhejiang");
        put("FUJIAN","Fujian");
        put("SUZHOU","Suzhou");
        put("BEIJING","Beijing");
        put("CHINA","China");
        put("HANGZHOU","Hangzhou");
        put("HEFEI","Hefei");
    }};

    Map<String,String> brands = new HashMap<String,String>(){{
        put("Shenzhen JingHanDa Electronics Co.Ltd","Shenzhen JingHanDa");
        put("Shenzhen Syscan Technology Co.,Ltd.","");
    }};

    Map<String,String> remarks = new HashMap<String,String>(){{
        put("APPLE","苹果");
        put("INTEL","英特尔");
        put("XIAOMI","小米");
        put("SAMSUNG","三星");
        put("HUAWEI","华为");
        put("LENOVO","联想");
        put("NOKIA","诺基亚");
        put("AZUREWAVE","海华");
        put("ESPRESSIF","乐鑫");
        put("YULONG","宇龙");
        put("JINGHANDA","晶汉达");
    }};

    @Test
    public void parserMapper() throws Exception {
        System.out.println(JacksonUtil.toJson(MacBrandMemory.parser("3CD92B")));
        System.out.println(JacksonUtil.toJson(MacBrandMemory.parserBrandMac("0050BA")));
    }

    @Test
    public void buildMapper() throws Exception {
        Pattern pattern = Pattern.compile("(\\S+)\\s{2,}\\(base 16\\)\\s{2,}(.+)", Pattern.CASE_INSENSITIVE);
        InputStream stream = ClassLoader.getSystemResourceAsStream("premac.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("premac.mem.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line.trim());
                    if (matcher.find()) {
                        String patterm = matcher.group(1);
                        String company = matcher.group(2).replaceAll(" {2,}", " ").replaceAll(",.*","");
                        String brand = company.split(" ")[0];
                        String nkey = nkeys.get(brand.toUpperCase());
                        if (nkey != null) {
                            brand = brands.get(company);
                            if (brand == null) {
                                String[] split = company.split(" ");
                                if (split.length > 1 && split[1].length() > 3) {
                                    brand = split[1];
                                } else if (split.length > 1){
                                    brand = split[0] + " " + split[1];
                                } else {
                                    brand = split[0];
                                }
                            }
                        }
                        String remark = remarks.get(brand.toUpperCase());
//                        String model = reader.readLine().trim().replaceAll(" {2,}", " ");
//                        String city = reader.readLine().trim().replaceAll(" {2,}", " ");
//                        String country = reader.readLine().trim().replaceAll(" {2,}", " ");
                        String out = patterm + ":" + brand + ((remark==null)?"":"," + remark);
                        System.out.println(out);
                        if (nkey != null) {
                        }
                        writer.println(out);
                    }
                }
            }
        }
    }

}
