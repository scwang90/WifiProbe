package com.simpletech.wifiprobe.model.entity;

/**
 * 分布统计数据（基类）
 * Created by 树朾 on 2015/9/25.
 */
public abstract class MapValue {

    private float map_min;            //范围最小值
    private float map_max;            //范围最大值
    private String map_val;         //范围值
    private String map_unit;        //单位

    public MapValue() {

    }

    public MapValue(float min,float max,String unit) {
        this.map_min = min;//+=.05f;
        this.map_max = max;//+=.05f;
        this.map_unit = unit;
        if (min == Integer.MIN_VALUE) {
            this.map_val = "小于" + max;
        } else if (max == Integer.MAX_VALUE) {
            this.map_val = "大于" + min;
        } else if (min == max) {
            this.map_val = min + "";
        } else {
            this.map_val = min + "-" + max;
        }
        this.map_val = this.map_val.replaceAll("\\.0+\\b","") + unit;
    }

    public float getMap_min() {
        return map_min;
    }

    public void setMap_min(int map_min) {
        this.map_min = map_min;
    }

    public float getMap_max() {
        return map_max;
    }

    public void setMap_max(int map_max) {
        this.map_max = map_max;
    }

    public String getMap_val() {
        return map_val;
    }

    public void setMap_val(String map_val) {
        this.map_val = map_val;
    }

    public String getMap_unit() {
        return map_unit;
    }

    public void setMap_unit(String map_unit) {
        this.map_unit = map_unit;
    }
}
