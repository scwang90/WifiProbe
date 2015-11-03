package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.StatisticsDao;
import com.simpletech.wifiprobe.mapper.StatisticsMapper;
import com.simpletech.wifiprobe.model.Visit;
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

    @Override
    public List<Visit> macvisit(String idshop, String mac, Date start, Date end) throws Exception {
        return mapper.macvisit(idshop, mac, start, end);
    }
}

