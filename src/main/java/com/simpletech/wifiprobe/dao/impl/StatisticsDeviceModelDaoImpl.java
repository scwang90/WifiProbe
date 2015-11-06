package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.StatisticsDeviceModelDao;
import com.simpletech.wifiprobe.mac.MacBrand;
import com.simpletech.wifiprobe.mapper.StatisticsDeviceModelMapper;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.model.entity.BrandValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据库表t_visit的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class StatisticsDeviceModelDaoImpl implements StatisticsDeviceModelDao {

    @Autowired
    StatisticsDeviceModelMapper mapper;

    @Override
    public List<BrandValue> brand(String idshop, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        return mapper.brand(idshop, rankingtype.name(), start, end, limit, skip);
    }
    @Override
    public BrandValue countBrand(String idshop, Date start, Date end) throws Exception {
        return mapper.countBrand(idshop, start, end);
    }
}

