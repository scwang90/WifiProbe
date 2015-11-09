package com.simpletech.wifiprobe.model.entity;

/**
 * WifiProbe
 * Created by ChenQi on 2015/11/4 10:36.
 */
public class IsNewCustomerValue {
    private Boolean isNewUser;
    private String remark;
    private int num;
    private float rate;
    private float dt;
    private float vp;



    public void setIsNewUser(Boolean isNewUser) {
        remark = isNewUser ? "新用户" : "老用户";
        this.isNewUser = isNewUser;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
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

    public Boolean getIsNewUser() {
        return isNewUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
