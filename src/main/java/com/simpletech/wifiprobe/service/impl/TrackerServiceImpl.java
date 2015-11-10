package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.TrackerDao;
import com.simpletech.wifiprobe.mac.MacBrand;
import com.simpletech.wifiprobe.mac.MacBrandMemory;
import com.simpletech.wifiprobe.model.MacLog;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.VisitWifi;
import com.simpletech.wifiprobe.service.TrackerService;
import com.simpletech.wifiprobe.util.AfStringUtil;
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

    /**
     * 接收探针日志数据
     *
     * @param log 日志数据
     */
    @Override
    public void maclog(MacLog log) throws Exception {
        Date now = new Date();
        Shop shop = dao.findShopByFiwiId(log.getIdwifi());
        if (shop != null) {
            String idvisit = "";
            String idvisitwifi = "";
            if (log.getSignalStrength() >= (100 - 30) * shop.getConfigVisitSignal() / 100 - 100) {//信号过滤
                Visit lastVist = dao.findLastVistByMacAndShop(shop.getId(), log.getMacDevice());
                if (lastVist == null || lastVist.getTimeLeave().getTime() < now.getTime() - shop.getConfigVisitExpired() * 60 * 1000) {
                    Visit visit = new Visit();
                    visit.setMacDevice(log.getMacDevice());
                    visit.setIdwifi(log.getIdwifi());
                    visit.setIdshop(shop.getId());
                    visit.fillNullID();
                    visit.setCreateTime(now);
                    visit.setUpdateTime(now);
                    visit.setTimeEntry(now);
                    visit.setTimeLeave(now);
                    visit.setTimeFromLast((int) ((now.getTime() - ((lastVist != null)?lastVist.getCreateTime().getTime():now.getTime()))/1000));//除以1000换算成秒
                    visit.setTimeDuration(0);
                    visit.setCountLogs(1);
                    visit.setEndBrand(MacBrandMemory.parserBrandMac(log.getMacDevice()));
                    visit.setIsNewUser((lastVist == null || lastVist.getCreateTime().getTime() < now.getTime() - shop.getConfigUserExpired() * 24 * 60 * 60 * 1000));
                    dao.insertVisit(visit);
                    idvisit = visit.getId();
                } else {
                    idvisit = lastVist.getId();
                    MacLog macLog = new MacLog();
                    macLog.setIdvisit(idvisit);
                    dao.updateVisitByMacLog(macLog);
                }
            }
            if (log.getSignalStrength() >= (100 - 30) * shop.getConfigVisitSignalWifi() / 100 - 100) {//信号过滤
                VisitWifi lastVist = dao.findLastVisitWifiByMacAndShop(shop.getId(), log.getMacDevice());
                if (lastVist == null || lastVist.getTimeLeave().getTime() < now.getTime() - shop.getConfigVisitExpiredWifi() * 60 * 1000) {
                    VisitWifi visit = new VisitWifi();
                    visit.setMacDevice(log.getMacDevice());
                    visit.setIdwifi(log.getIdwifi());
                    visit.setIdshop(shop.getId());
                    visit.fillNullID();
                    visit.setCreateTime(now);
                    visit.setUpdateTime(now);
                    visit.setTimeEntry(now);
                    visit.setTimeLeave(now);
                    visit.setTimeDuration(0);
                    visit.setCountLogs(1);
                    dao.insertVisitWifi(visit);
                    idvisitwifi = visit.getId();
                } else {
                    idvisitwifi = lastVist.getId();
                    MacLog macLog = new MacLog();
                    macLog.setIdvisitwifi(idvisitwifi);
                    dao.updateVisitWifiByMacLog(macLog);
                }
            }

            log.setIdvisit(idvisit);
            log.setIdvisitwifi(idvisitwifi);
            log.setIdshop(shop.getId());
            dao.insertMacLog(log);
        } else {
            System.out.println("无效WifiId：" + log.getIdwifi());
        }
    }

}
