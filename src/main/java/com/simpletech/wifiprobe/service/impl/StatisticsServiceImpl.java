package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.StatisticsDao;
import com.simpletech.wifiprobe.mac.MacBrand;
import com.simpletech.wifiprobe.model.Visit;
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
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsDao dao;

    @Override
    public List<Visit> macvisit(String idshop, String mac, Date start, Date end) throws Exception {
        List<Visit> visits = dao.macvisit(idshop, mac, start, end);
        for (Visit visit : visits){
            visit.setEndBrand(MacBrand.parser(visit.getEndBrand()).getCompany());
        }
        return visits;
    }
}
