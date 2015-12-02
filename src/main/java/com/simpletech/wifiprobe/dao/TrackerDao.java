package com.simpletech.wifiprobe.dao;

import com.simpletech.wifiprobe.model.MacLog;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.VisitWifi;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
public interface TrackerDao {

    /**
     * 根据WIFIID获取店铺
     * @param idwifi Wifi Id
     */
    Shop findShopByFiwiId(String idwifi) throws Exception;

}
