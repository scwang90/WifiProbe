package com.simpletech.wifiprobe.model.entity;

/**
 * 访问周期
 * Created by 树朾 on 2015/9/25.
 */
public class PeriodMapValue extends MapValue{

    private Integer num;
    private Float rate;

    public PeriodMapValue() {

    }

    public PeriodMapValue(float min,float max,String unit) {
        super(min,max,unit);
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
