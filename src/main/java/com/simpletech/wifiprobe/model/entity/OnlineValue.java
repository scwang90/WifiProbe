package com.simpletech.wifiprobe.model.entity;

/**
 * 在线数据
 * Created by 树朾 on 2015/9/25.
 */
public class OnlineValue {

    private String id;         //ID
    private int num;           //数值

    public OnlineValue() {

    }

    public OnlineValue(String id, int num) {
        this.id = id;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
