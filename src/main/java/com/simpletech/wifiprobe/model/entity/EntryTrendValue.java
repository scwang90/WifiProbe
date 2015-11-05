package com.simpletech.wifiprobe.model.entity;

/**
 * 入店人次-趋势
 * Created by Administrator on 2015/11/3.
 */
public class EntryTrendValue extends TrendValue {

    private int total;           //客流量

    private int past;            //路过用户
    private int entry;           //入店人次
    private int nentry;          //新入店人次
    private int oentry;          //老入店人次

    private float rpast;         //客流量占比
    private float rentry;        //入店人次占比
    private float rnentry;       //新入店人次占比
    private float roentry;       //老入店人次占比

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPast() {
        return past;
    }

    public void setPast(int past) {
        this.past = past;
    }

    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    public int getNentry() {
        return nentry;
    }

    public void setNentry(int nentry) {
        this.nentry = nentry;
    }

    public int getOentry() {
        return oentry;
    }

    public void setOentry(int oentry) {
        this.oentry = oentry;
    }

    public float getRpast() {
        return rpast;
    }

    public void setRpast(float rpast) {
        this.rpast = rpast;
    }

    public float getRentry() {
        return rentry;
    }

    public void setRentry(float rentry) {
        this.rentry = rentry;
    }

    public float getRnentry() {
        return rnentry;
    }

    public void setRnentry(float rnentry) {
        this.rnentry = rnentry;
    }

    public float getRoentry() {
        return roentry;
    }

    public void setRoentry(float roentry) {
        this.roentry = roentry;
    }
}
