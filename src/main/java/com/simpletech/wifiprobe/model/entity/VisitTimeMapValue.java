package com.simpletech.wifiprobe.model.entity;

/**
 * 时间分布
 * Created by 树朾 on 2015/9/25.
 */
public class VisitTimeMapValue {

    private int time;        //时间（小时）
    private int vt;             //visit 数
    private int pv;             //pv 数
    private int uv;             //独立用户数
    private int ip;             //pv 数
    private int avgvt;          //平均 visit 数
    private int avgpv;          //平均 pv 数
    private int avguv;          //平均 独立用户数
    private int avgip;          //平均 pv 数
    private float rvt;          //占比 visit 数
    private float rpv;          //占比 pv 数
    private float ruv;          //占比 独立用户数
    private float rip;          //占比 pv 数

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public int getAvgvt() {
        return avgvt;
    }

    public void setAvgvt(int avgvt) {
        this.avgvt = avgvt;
    }

    public int getAvgpv() {
        return avgpv;
    }

    public void setAvgpv(int avgpv) {
        this.avgpv = avgpv;
    }

    public int getAvguv() {
        return avguv;
    }

    public void setAvguv(int avguv) {
        this.avguv = avguv;
    }

    public int getAvgip() {
        return avgip;
    }

    public void setAvgip(int avgip) {
        this.avgip = avgip;
    }

    public float getRvt() {
        return rvt;
    }

    public void setRvt(float rvt) {
        this.rvt = rvt;
    }

    public float getRpv() {
        return rpv;
    }

    public void setRpv(float rpv) {
        this.rpv = rpv;
    }

    public float getRuv() {
        return ruv;
    }

    public void setRuv(float ruv) {
        this.ruv = ruv;
    }

    public float getRip() {
        return rip;
    }

    public void setRip(float rip) {
        this.rip = rip;
    }

}
