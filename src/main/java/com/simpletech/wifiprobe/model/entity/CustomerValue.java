package com.simpletech.wifiprobe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * WifiProbe
 * Created by ChenQi on 2015/11/4 10:36.
 */
public class CustomerValue extends TrendValue{
    private int uv;         //独立顾客量 Unique Customer
    private int nv;         //新顾客量  New Customer
    private int ov;         //老顾客量  Old Customer
    private float nr;       //新顾客比率 New Ratio
    private float or;       //老顾客比率 Old Ratio
    private float dt;         //驻店时长 duration time
    private float vp;         //来访周期 visit period

    @JsonIgnore
    int subnv;      //子新用户  Sub New Visitor

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public int getNv() {
        return nv;
    }

    public void setNv(int nv) {
        this.nv = nv;
    }

    public int getOv() {
        return ov;
    }

    public void setOv(int ov) {
        this.ov = ov;
    }

    public float getNr() {
        return nr;
    }

    public void setNr(float nr) {
        this.nr = nr;
    }

    public float getOr() {
        return or;
    }

    public void setOr(float or) {
        this.or = or;
    }

    public float getDt() {
        return dt;
    }

    public void setDt(float dt) {
        this.dt = dt;
    }

    public float getVp() {
        return vp;
    }

    public void setVp(float vp) {
        this.vp = vp;
    }
}
