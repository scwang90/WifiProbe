package com.simpletech.wifiprobe.mac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * mac地址 品牌枚举
 * Created by Administrator on 2015/11/2.
 */
public class MacBrandMemory {

    static Map<String, String[]> memory = new HashMap<>();
    static Map<String, String> membrand = new HashMap<>();

    static {
        InputStream stream = MacBrandMemory.class.getClassLoader().getResourceAsStream("premac.mem.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splits = line.trim().split(":");
                String[] brands = splits[1].split(",");
                memory.put(splits[0],brands);
                membrand.put(brands[0],splits[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MacBrandMemory() {

    }

    public static String parserBrandMac(String mac) {
        mac = mac.trim().replace(":", "").substring(0, 6).toUpperCase();
        String[] vals = memory.get(mac);
        if (vals != null) {
            String val = membrand.get(vals[0]);
            if (val != null) {
                return val;
            }
        }
        return mac;
    }

    public static String parser(String mac) {
        mac = mac.trim().replace(":", "").substring(0, 6).toUpperCase();
        String[] vals = memory.get(mac);
        if (vals != null) {
            if (vals.length > 1) {
                return vals[1];
            }
            return vals[0];
        }
        return mac;
    }

}
