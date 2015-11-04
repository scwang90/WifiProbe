package com.simpletech.wifiprobe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.util.AfReflecter;
import com.simpletech.wifiprobe.util.AfStringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 趋势统计数据（基类）
 * Created by 树朾 on 2015/9/25.
 */
public abstract class TrendValue {

    private static final SimpleDateFormat format = new SimpleDateFormat();

    private Date time;
    private String date;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        this.bindTime(date);
    }

    private void bindTime(String date) {
        try {
            if (AfStringUtil.isNotEmpty(date)){
                for (String period: Period.PERIODS){
                    if (date.length() == period.length()){
                        format.applyPattern(period);
                        time = format.parse(date);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @JsonIgnore
    public void setEmpty() {
        try {
            Field[] fields = AfReflecter.getField(this.getClass(), TrendValue.class);
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    if (field.getType().equals(String.class)){
                        field.set(this,"");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
