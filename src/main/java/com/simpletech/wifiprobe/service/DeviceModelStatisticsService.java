package com.simpletech.wifiprobe.service;

import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.BrandValue;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service
 * Created by 树朾 on 2015/9/25.
 */
public interface DeviceModelStatisticsService {

    /**
     * mac的访问记录
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return mac的访问记录
     */
    List<BrandValue> brand(String idshop, Date start, Date end) throws Exception;
}
