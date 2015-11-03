package com.simpletech.wifiprobe.mac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * mac地址 品牌枚举
 * Created by Administrator on 2015/11/2.
 */
public class MacBrand {

    private String city = "";
    private String model = "";
    private String company = "";
    private String country = "";

    static Pattern pattern = Pattern.compile("\\((.*),(.*),(.*),(.*)\\)");

    public MacBrand() {

    }

    public MacBrand(String company,String model,String city,String country) {
        this.city = city;
        this.model = model;
        this.company = company;
        this.country = country;
    }

    public static MacBrand parser(String mac) {
        MacBrand brand = new MacBrand();
//        InputStream stream = ClassLoader.getSystemResourceAsStream("premac.out.txt");
        InputStream stream = MacBrand.class.getClassLoader().getResourceAsStream("premac.out.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith(mac)) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        brand.setCompany(matcher.group(1));
                        brand.setModel(matcher.group(2));
                        brand.setCity(matcher.group(3));
                        brand.setCountry(matcher.group(4));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return brand;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
