package com.simpletech.wifiprobe.model.entity;

/**
 * 客户类型（新老客户）时段
 * Created by ChenQi on 2015/11/4 10:36.
 */
public class UserTypeSpanValue {

    private Boolean isNewUser;
    private String name;
    private int vt;
    private int uv;
    private float rvt;
    private float ruv;
    private int stay;
    private int period;

    public Boolean getIsNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(Boolean isNewUser) {
        name = isNewUser ? "新用户" : "老用户";
        this.isNewUser = isNewUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public float getRvt() {
        return rvt;
    }

    public void setRvt(float rvt) {
        this.rvt = rvt;
    }

    public float getRuv() {
        return ruv;
    }

    public void setRuv(float ruv) {
        this.ruv = ruv;
    }

    public int getStay() {
        return stay;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
