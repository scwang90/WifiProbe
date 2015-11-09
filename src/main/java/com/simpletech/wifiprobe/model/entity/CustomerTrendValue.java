package com.simpletech.wifiprobe.model.entity;

/**
 * 设备类型
 * Created by ChenQi on 2015/11/3 13:20.
 */
public class CustomerTrendValue extends TrendValue{
//    private int uv;             //独立顾客数
    private int nv;             //新顾客
    private int ov;             //老顾客
    private float rnv;          //新顾客占比
    private float rov;          //老顾客占比

//    public int getUv() {
//        return uv;
//    }
//
//    public void setUv(int uv) {
//        this.uv = uv;
//    }

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

    public float getRnv() {
        return rnv;
    }

    public void setRnv(float rnv) {
        this.rnv = rnv;
    }

    public float getRov() {
        return rov;
    }

    public void setRov(float rov) {
        this.rov = rov;
    }
}
