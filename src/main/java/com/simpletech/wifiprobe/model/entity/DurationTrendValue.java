package com.simpletech.wifiprobe.model.entity;

/**
 * 驻店时长-趋势
 * Created by Administrator on 2015/11/3.
 */
public class DurationTrendValue extends TrendValue {

    private int dur_avg;       //平均驻店时长
    private int entry;         //驻店量（人次）
    private int deep;          //深访量（人次）
    private int jump;          //跳出量（人次）
    private float rdeep;       //深访率
    private float rjump;       //跳出率

    public int getDur_avg() {
        return dur_avg;
    }

    public void setDur_avg(int dur_avg) {
        this.dur_avg = dur_avg;
    }

    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
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

    public float getRdeep() {
        return rdeep;
    }

    public void setRdeep(float rdeep) {
        this.rdeep = rdeep;
    }

    public float getRjump() {
        return rjump;
    }

    public void setRjump(float rjump) {
        this.rjump = rjump;
    }
}
