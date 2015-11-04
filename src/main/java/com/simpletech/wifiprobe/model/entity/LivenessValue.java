package com.simpletech.wifiprobe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * WifiProbe
 * Created by ChenQi on 2015/11/4 10:36.
 */
public class LivenessValue extends TrendValue{
    private String live;    //活跃度
    private Integer num;    //人数
    private int uv;         //老客户数量
    private Float rate;     //占比
    private int dt;         //驻店时长 duration time
    private int vp;         //来访周期 visit period

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getVp() {
        return vp;
    }

    public void setVp(int vp) {
        this.vp = vp;
    }
}
