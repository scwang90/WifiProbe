package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.TrackerDao;
import com.simpletech.wifiprobe.mapper.TrackerMapper;
import com.simpletech.wifiprobe.model.MacLog;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.VisitWifi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
@Service
public class TrackerDaoImpl implements TrackerDao {


    @Autowired
    TrackerMapper mapper;

    @Override
    public Shop findShopByFiwiId(String idwifi) throws Exception {
        return mapper.findShopByFiwiId(idwifi);
    }

    @Override
    public MacLog findLastLogByMacAndShop(String idshop, String mac) throws Exception {
        return mapper.findLastLogByMacAndShop(idshop, mac);
    }

    @Override
    public Visit findLastVistByMacAndShop(String idshop, String mac) throws Exception {
        return mapper.findLastVistByMacAndShop(idshop, mac);
    }

    @Override
    public VisitWifi findLastVisitWifiByMacAndShop(String idshop, String mac) throws Exception {
        return mapper.findLastVisitWifiByMacAndShop(idshop, mac);
    }

    @Override
    public int insertVisit(Visit visit) throws Exception {
        return mapper.insertVisit(visit);
    }

//    @Override
//    public int updateVisit(Visit visit) throws Exception {
//        return mapper.updateVisit(visit);
//    }
//
//    @Override
//    public Visit findVisitById(String idvisit) throws Exception {
//        return mapper.findVisitById(idvisit);
//    }

    @Override
    public int insertMacLog(MacLog log) throws Exception {
        return mapper.insertMacLog(log);
    }

    @Override
    public int updateVisitByMacLog(MacLog log) throws Exception {
        return mapper.updateVisitByMacLog(log);
    }

    @Override
    public int insertVisitWifi(VisitWifi visit) throws Exception {
        return mapper.insertVisitWifi(visit);
    }

    @Override
    public int updateVisitWifiByMacLog(MacLog log) throws Exception {
        return mapper.updateVisitWifiByMacLog(log);
    }
}
