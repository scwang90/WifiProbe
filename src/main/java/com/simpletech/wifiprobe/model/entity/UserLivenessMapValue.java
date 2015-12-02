package com.simpletech.wifiprobe.model.entity;

/**
 * 顾客活跃度-分布
 * Created by Administrator on 2015/11/19.
 */
public class UserLivenessMapValue extends MapValue{

    private String remark;
    private int vt;
    private int uv;

    public UserLivenessMapValue(float min, float max, String unit) {
        super(min, max, unit);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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
