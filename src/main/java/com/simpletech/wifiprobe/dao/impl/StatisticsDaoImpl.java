package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.StatisticsDao;
import com.simpletech.wifiprobe.mapper.api.StatisticsMapper;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.DeviceBrandValue;
import com.simpletech.wifiprobe.model.entity.DurationSpanValue;
import com.simpletech.wifiprobe.model.entity.DurationTrendValue;
import com.simpletech.wifiprobe.model.entity.EntryTrendValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 数据库表t_url的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class StatisticsDaoImpl implements StatisticsDao {

    @Autowired
    StatisticsMapper mapper;

}

