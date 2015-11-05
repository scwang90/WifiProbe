package com.simpletech.wifiprobe.service;

import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.model.entity.BrandValue;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service
 * Created by 树朾 on 2015/9/25.
 */
public interface StatisticsDeviceModelService {

    /**
     * 设备类型
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 设备类型记录
     */
    List<BrandValue> brand(String idshop,RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception;
}
