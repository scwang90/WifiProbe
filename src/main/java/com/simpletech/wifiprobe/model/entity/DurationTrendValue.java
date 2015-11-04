package com.simpletech.wifiprobe.model.entity;

/**
 * 访问频次-趋势
 * Created by Administrator on 2015/11/3.
 */
public class DurationTrendValue extends TrendValue{

    private int stay;        //平均访问时长
    private int deep;        //深访量（人次）
    private int jump;        //跳出量（人次）

    public int getStay() {
        return stay;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public int getJump() {
        return jump;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }
}
