package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.TrackerDao;
import com.simpletech.wifiprobe.mac.MacBrandMemory;
import com.simpletech.wifiprobe.mapper.api.TrackerMapper;
import com.simpletech.wifiprobe.mapper_log.ProbeLogMapper;
import com.simpletech.wifiprobe.model.*;
import com.simpletech.wifiprobe.model.entity.ShopEntity;
import com.simpletech.wifiprobe.service.TrackerService;
import com.simpletech.wifiprobe.util.SynchronizedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
@Service
public class TrackerServiceImpl implements TrackerService {

    @Autowired
    TrackerDao dao;

    @Autowired
    TrackerMapper mapper;

    @Autowired
    ProbeLogMapper logMapper;

    SynchronizedLock<String> visitLock = new SynchronizedLock<>(1000);

    /**
     * 接收探针日志数据
     *
     * @param log 日志数据
     */
    @Override
    public void maclog(ProbeLog log) throws Exception {
        Date now = new Date();
        Shop shop = dao.findShopByFiwiId(log.getIdwifi());
        if (shop != null) {
            shop = new ShopEntity(shop);
            String idvisit = "";
            String idvisitwifi = "";
            synchronized (visitLock.getLock(shop.getShopID() + log.getMacDevice())) {
                if (log.getSignalStrength() >= (100 - 30) * shop.getConfigProbeVisitSignal() / 100 - 100) {//信号过滤
                    Visit lastVist = mapper.findLastVistByMacAndShop(shop.getShopID(), log.getMacDevice());
                    if (lastVist == null || lastVist.getTimeLeave().getTime() < now.getTime() - shop.getConfigProbeVisitExpired() * 60 * 1000l) {
                        Visit visit = new Visit();
                        visit.setMacDevice(log.getMacDevice());
                        visit.setIdwifi(log.getIdwifi());
                        visit.setIdshop(shop.getShopID());
                        visit.fillNullID();
                        visit.setCreateTime(now);
                        visit.setUpdateTime(now);
                        visit.setTimeEntry(now);
                        visit.setTimeLeave(now);
                        visit.setTimeFromLast((int) ((now.getTime() - ((lastVist != null)?lastVist.getCreateTime().getTime():now.getTime()))/1000l));//除以1000换算成秒
                        visit.setTimeDuration(0);
                        visit.setCountLogs(1);
                        visit.setEndBrand(MacBrandMemory.parserBrandMac(log.getMacDevice()));
                        visit.setIsNewUser((lastVist == null || lastVist.getCreateTime().getTime() < now.getTime() - shop.getConfigProbeUserExpired() * 24 * 60 * 60 * 1000l));
                        mapper.insertVisit(visit);
                        idvisit = visit.getId();
                    } else {
                        idvisit = lastVist.getId();
                        MacLog macLog = new MacLog();
                        macLog.setIdvisit(idvisit);
                        mapper.updateVisitByMacLog(macLog);
                    }
                }
                if (log.getSignalStrength() >= (100 - 30) * shop.getConfigProbeVisitSignalWifi() / 100 - 100) {//信号过滤
                    VisitWifi lastVist = mapper.findLastVisitWifiByMacAndShop(shop.getShopID(), log.getMacDevice());
                    if (lastVist == null || lastVist.getTimeLeave().getTime() < now.getTime() - shop.getConfigProbeVisitExpiredWifi() * 60 * 1000l) {
                        VisitWifi visit = new VisitWifi();
                        visit.setMacDevice(log.getMacDevice());
                        visit.setIdwifi(log.getIdwifi());
                        visit.setIdshop(shop.getShopID());
                        visit.fillNullID();
                        visit.setCreateTime(now);
                        visit.setUpdateTime(now);
                        visit.setTimeEntry(now);
                        visit.setTimeLeave(now);
                        visit.setTimeDuration(0);
                        visit.setCountLogs(1);
                        mapper.insertVisitWifi(visit);
                        idvisitwifi = visit.getId();
                    } else {
                        idvisitwifi = lastVist.getId();
                        MacLog macLog = new MacLog();
                        macLog.setIdvisitwifi(idvisitwifi);
                        mapper.updateVisitWifiByMacLog(macLog);
                    }
                }

                log.setIdvisit(idvisit);
                log.setIdvisitwifi(idvisitwifi);
                log.setIdshop(shop.getShopID());
//                mapper.insertMacLog(log);
                logMapper.insert(log);
            }
        } else {
            System.out.println("无效WifiId：" + log.getIdwifi());
        }
    }

}
