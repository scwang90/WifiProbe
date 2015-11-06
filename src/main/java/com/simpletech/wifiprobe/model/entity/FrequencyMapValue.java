package com.simpletech.wifiprobe.model.entity;

/**
 * 访问频次-分布
 * Created by Administrator on 2015/11/3.
 */
public class FrequencyMapValue extends MapValue{

    private Integer num;
    private Float rate;

    public FrequencyMapValue() {
    }

    public FrequencyMapValue(int min, int max, String unit) {
        super(min, max, unit);
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
