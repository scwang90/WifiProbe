package com.simpletech.wifiprobe.model.entity;


/**
 * 顾客活跃度类
 * Created by ChenQi on 2015/11/4 10:36.
 */
public class LivenessValue extends MapValue{
    private String live;    //活跃度
    private Integer num;    //人数(老用户中当前活跃度人数)
    private Float rate;     //占比
    private float dt;         //驻店时长 duration time
    private float vp;         //来访周期 visit period

    public LivenessValue(){}
    public LivenessValue(float min, float max, String unit) {
        super(min, max, unit);
    }

    public String getLive() {
        return live;
    }
//
    public void setLive(String live) {
        this.live = live;
    }

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
