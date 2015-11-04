package com.simpletech.wifiprobe.model.entity;

/**
 * 访问频次-时段
 * Created by Administrator on 2015/11/3.
 */
public class DurationSpanValue {

    private int dur_avg;            //平均访问时长
    private int dur_deep;           //深度访问时长（也是平均）
    private int amount_total;       //总量（人次）
    private int amount_deep;        //深访量（人次）
    private int amount_jump;        //跳出量（人次）
    private float rate_deep;        //深访率
    private float rate_jump;        //跳出率

    public int getDur_avg() {
        return dur_avg;
    }

    public void setDur_avg(int dur_avg) {
        this.dur_avg = dur_avg;
    }

    public int getDur_deep() {
        return dur_deep;
    }

    public void setDur_deep(int dur_deep) {
        this.dur_deep = dur_deep;
    }

    public int getAmount_total() {
        return amount_total;
    }

    public void setAmount_total(int amount_total) {
        this.amount_total = amount_total;
    }

    public int getAmount_deep() {
        return amount_deep;
    }

    public void setAmount_deep(int amount_deep) {
        this.amount_deep = amount_deep;
    }

    public int getAmount_jump() {
        return amount_jump;
    }

    public void setAmount_jump(int amount_jump) {
        this.amount_jump = amount_jump;
    }

    public float getRate_deep() {
        return rate_deep;
    }

    public void setRate_deep(float rate_deep) {
        this.rate_deep = rate_deep;
    }

    public float getRate_jump() {
        return rate_jump;
    }

    public void setRate_jump(float rate_jump) {
        this.rate_jump = rate_jump;
    }
}
