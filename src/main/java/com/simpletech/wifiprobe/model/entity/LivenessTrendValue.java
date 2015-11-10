package com.simpletech.wifiprobe.model.entity;

/**
 * 顾客活跃度趋势类
 * Created by ChenQi on 2015/11/4 10:36.
 */
public class LivenessTrendValue extends TrendValue{

    private Integer num;  //数量
    private String remark;//标记
    private float rate;   //占比

    public LivenessTrendValue(){}

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {

        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public void setEmpty() {
        this.setRemark("");
        this.setNum(0);
    }
}
