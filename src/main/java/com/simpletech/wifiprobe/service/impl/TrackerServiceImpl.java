package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.TrackerDao;
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
            MacLog last = dao.findLastLogByMacAndShop(shop.getId(), log.getMacDevice());
            String idvisit = "";
            String idvisitwifi = "";
            if (log.getSignalStrength() >= (90 - 30) * shop.getConfigVisitSignal() / 100 - 90) {//信号过滤
                if (last == null || AfStringUtil.isEmpty(last.getIdvisit()) || last.getCreateTime().getTime() < now.getTime() - shop.getConfigVisitExpired() * 60 * 1000) {
                    Visit lastVist = dao.findLastVistByMacAndShop(shop.getId(), log.getMacDevice());
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
                    visit.setEndBrand(log.getMacDevice().replace(":", "").substring(0, 6).toUpperCase());
                    visit.setIsNewUser((lastVist == null || lastVist.getCreateTime().getTime() < now.getTime() - shop.getConfigUserExpired() * 24 * 60 * 60 * 1000));
                    dao.insertVisit(visit);
                    idvisit = visit.getId();
                } else {
                    idvisit = last.getIdvisit();
//                Visit visit = dao.findVisitById(last.getIdvisit());
//                visit.setEndTime(now);
//                visit.setStayTime((int) (now.getTime() - visit.getCreateTime().getTime()));
//                visit.setCountLogs(visit.getCountLogs() + 1);
//                dao.updateVisit(visit);
                    dao.updateVisitByMacLog(last);
                }
            }
            if (log.getSignalStrength() >= (90 - 30) * shop.getConfigVisitSignalWifi() / 100 - 90) {//信号过滤
                if (last == null || AfStringUtil.isEmpty(last.getIdvisitwifi()) || last.getCreateTime().getTime() < now.getTime() - shop.getConfigVisitExpiredWifi() * 60 * 1000) {
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
                    idvisitwifi = last.getIdvisitwifi();
                    dao.updateVisitWifiByMacLog(last);
                }
            }

            log.setIdvisit(idvisit);
            log.setIdvisitwifi(idvisitwifi);
            log.setIdshop(shop.getId());
            dao.insertMacLog(log);
        }
    }

}
