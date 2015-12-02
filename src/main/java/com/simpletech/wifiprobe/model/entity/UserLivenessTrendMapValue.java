package com.simpletech.wifiprobe.model.entity;

import java.util.List;

/**
 * 顾客活跃度-趋势-分布
 * Created by Administrator on 2015/11/19.
 */
public class UserLivenessTrendMapValue extends MapValue{

    private String remark;
    private List<UserLivenessTrendValue> trend;

    public UserLivenessTrendMapValue(float min, float max, String unit) {
        super(min, max, unit);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<UserLivenessTrendValue> getTrend() {
        return trend;
    }

    public void setTrend(List<UserLivenessTrendValue> trend) {
        this.trend = trend;
    }
}
