package com.simpletech.wifiprobe.model.entity;

/**
 * 顾客活跃度-趋势
 * Created by Administrator on 2015/11/19.
 */
public class UserLivenessTrendValue extends TrendValue{

    private int vt;
    private int uv;

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }
}
