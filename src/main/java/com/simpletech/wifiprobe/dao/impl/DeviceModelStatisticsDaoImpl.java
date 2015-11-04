package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.DeviceModelStatisticsDao;
import com.simpletech.wifiprobe.dao.StatisticsDao;
import com.simpletech.wifiprobe.mac.MacBrand;
import com.simpletech.wifiprobe.mapper.DeviceModelStatisticsMapper;
import com.simpletech.wifiprobe.mapper.StatisticsMapper;
import com.simpletech.wifiprobe.model.MacLog;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.BrandValue;
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
public class DeviceModelStatisticsDaoImpl implements DeviceModelStatisticsDao{

    @Autowired
    DeviceModelStatisticsMapper mapper;

    @Override
    public List<BrandValue> brand(String idshop, Date start, Date end) throws Exception {
        List<BrandValue> list = mapper.brand(idshop,start, end);
        BrandValue count = mapper.coutBrand(idshop, start, end);
        for (BrandValue value : list) {
            String str=MacBrand.parser(value.getName()).getCompany();
            String[] company=str.split("\\s");
            value.setName(company[0]);
            value.setRuv(1f * value.getUv() / count.getUv());
        }
        return list;
    }
}

