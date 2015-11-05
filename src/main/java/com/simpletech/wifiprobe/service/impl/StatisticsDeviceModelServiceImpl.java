package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.StatisticsDeviceModelDao;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.model.entity.BrandValue;
import com.simpletech.wifiprobe.service.StatisticsDeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service 实现
 * Created by 树朾 on 2015/9/25.
 */
@Service
public class StatisticsDeviceModelServiceImpl implements StatisticsDeviceModelService {

    @Autowired
    StatisticsDeviceModelDao dao;

    @Override
    public List<BrandValue> brand(String idshop,RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        List<BrandValue> visits = dao.brand(idshop,rankingtype, start, end, limit, skip);
        return visits;
    }
}
