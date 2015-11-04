package com.simpletech.wifiprobe.service;

import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.DurationValue;
import com.simpletech.wifiprobe.model.entity.FrequencyValue;
import com.simpletech.wifiprobe.model.entity.PeriodValue;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service
 * Created by 树朾 on 2015/9/25.
 */
public interface StatisticsService {

    /**
     * mac的访问记录
     *
     * @param idshop 网站ID
     * @param mac    mac地址
     * @param start  开始时间
     * @param end    结束时间
     * @return mac的访问记录
     */
    List<Visit> visitmac(String idshop, String mac, Date start, Date end) throws Exception;

    /**
     * 统计店铺的到访频次
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<FrequencyValue> visitfrequency(String idshop, Date start, Date end) throws Exception;

    /**
     * 统计店铺的到访时长
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<DurationValue> visitduration(String idshop, Date start, Date end) throws Exception;

    /**
     * 统计店铺的到访周期
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<PeriodValue> visitperiod(String idshop, Date start, Date end) throws Exception;

}
