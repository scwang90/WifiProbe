package com.simpletech.wifiprobe.model.entity;

/**
 * 访问频次值
 * Created by Administrator on 2015/11/3.
 */
public class FrequencyValue {

    private String fre;
    private Integer num;
    private Float rate;

    public String getFre() {
        return fre;
    }

    public void setFre(String fre) {
        this.fre = fre;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}
