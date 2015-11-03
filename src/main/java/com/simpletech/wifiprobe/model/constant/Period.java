package com.simpletech.wifiprobe.model.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 周期
 * Created by 树朾 on 2015/9/25.
 */
public enum Period {

    year("yy", Calendar.YEAR),
    month("yyMM", Calendar.MONTH),
    week("yy-ww", Calendar.WEEK_OF_YEAR),
    day("yyMMdd", Calendar.DAY_OF_MONTH),
    hour("yyMMddHH", Calendar.HOUR_OF_DAY),
    ;
    private final int field;
    private final DateFormat format;
    public static final String[] PERIODS = {"yy","yyMM","yyMMdd","yyMMddhh","yy-ww",};

    Period(String format,int field){
        this.format = new SimpleDateFormat(format);
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public int getParentField() {
        if (this.ordinal() == 0)
            return field;
        return values()[ordinal()-1].field;
    }

    public DateFormat getFormat() {
        return format;
    }

    public DateFormat getParentformat() {
        if (this.ordinal() == 0)
            return format;
        return values()[ordinal()-1].format;
    }
}
