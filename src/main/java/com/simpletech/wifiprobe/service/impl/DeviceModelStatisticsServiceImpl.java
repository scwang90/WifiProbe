package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.DeviceModelStatisticsDao;
import com.simpletech.wifiprobe.dao.StatisticsDao;
import com.simpletech.wifiprobe.mac.MacBrand;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.BrandValue;
import com.simpletech.wifiprobe.service.DeviceModelStatisticsService;
import com.simpletech.wifiprobe.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service 实现
 * Created by 树朾 on 2015/9/25.
 */
@Service
public class DeviceModelStatisticsServiceImpl implements DeviceModelStatisticsService {

    @Autowired
    DeviceModelStatisticsDao dao;

    @Override
    public List<BrandValue> brand(String idshop,Date start, Date end) throws Exception {
        List<BrandValue> visits = dao.brand(idshop, start, end);
        for (BrandValue visit : visits){
//            visit.setEndBrand(MacBrand.parser(visit.getEndBrand()).getCompany());
        }
        return visits;
    }
}
