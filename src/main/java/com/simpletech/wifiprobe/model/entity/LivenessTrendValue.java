package com.simpletech.wifiprobe.model.entity;


/**
 * WifiProbe
 * Created by ChenQi on 2015/11/4 10:36.
 */
public class LivenessTrendValue extends TrendValue{
    private String live;    //活跃度
    private Integer num;    //人数(老用户中当前活跃度人数)
    private Float rate;     //占比


    public LivenessTrendValue(){}

//    public String getLive() {
//        return live;
//    }
//
//    public void setLive(String live) {
//        this.live = live;
//    }

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

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }
}
