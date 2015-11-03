package com.simpletech.wifiprobe.mac;

import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Test;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * mac地址 品牌测试类
 * Created by Administrator on 2015/11/2.
 */
public class MacBrandTester {

    @Test
    public void parserMapper() throws Exception {
        System.out.println(JacksonUtil.toJson(MacBrand.parser("3CD92B")));
    }

    @Test
    public void buildMapper() throws Exception {

        Pattern pattern = Pattern.compile("(\\S+)\\s{2,}\\(base 16\\)\\s{2,}(.+)", Pattern.CASE_INSENSITIVE);
        InputStream stream = ClassLoader.getSystemResourceAsStream("premac.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            //reader.readLine()
            try (PrintWriter writer = new PrintWriter(new FileWriter("premac.out.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line.trim());
                    if (matcher.find()) {
                        String patterm = matcher.group(1);
                        String company = matcher.group(2).replaceAll(" {2,}", " ");
                        String model = reader.readLine().trim().replaceAll(" {2,}", " ");
                        String city = reader.readLine().trim().replaceAll(" {2,}", " ");
                        String country = reader.readLine().trim().replaceAll(" {2,}", " ");
                        String out = patterm + "(" + company + "," + model + "," + city + "," + country + ")";
                        System.out.println(out);
                        writer.println(out);
                    }
                }
            }
        }
    }

    public String doCamelCase(String value) {
        String[] splits = value.split(" |\\.|,");
        StringBuilder builder = new StringBuilder();
        for (String split : splits) {
            if (splits != null && split.length() > 0) {
                String token = split.substring(0, 1).toUpperCase() + split.substring(1);
                builder.append(token);
            }
        }
        return builder.toString().replace("-", "_");
    }
}
