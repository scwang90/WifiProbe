package com.simpletech.wifiprobe.model.entity;

/**
 * 驻店时长-趋势
 * Created by Administrator on 2015/11/3.
 */
public class DurationTrendValue extends TrendValue {

    private int dur_avg;       //平均访问时长
    private int dur_entry;     //平均驻店时长
    private int deep;          //深访量（人次）
    private int jump;          //跳出量（人次）

    public int getDur_avg() {
        return dur_avg;
    }

    public void setDur_avg(int dur_avg) {
        this.dur_avg = dur_avg;
    }

    public int getDur_entry() {
        return dur_entry;
    }

    public void setDur_entry(int dur_entry) {
        this.dur_entry = dur_entry;
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
