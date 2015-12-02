package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.TrackerDao;
import com.simpletech.wifiprobe.mapper.api.TrackerMapper;
import com.simpletech.wifiprobe.mapper_shop.TrackerShopMapper;
import com.simpletech.wifiprobe.model.MacLog;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.VisitWifi;
import com.simpletech.wifiprobe.util.LruTimeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
@Service
public class TrackerDaoImpl implements TrackerDao {

    @Autowired
    TrackerShopMapper mapper;

    LruTimeCache<String, Shop> timeCacheShop = new LruTimeCache<>(100, 60 * 1000);

    @Override
    public Shop findShopByFiwiId(String idwifi) throws Exception {
        Shop shop = timeCacheShop.get(idwifi);
        if (shop != null) return shop;
        shop = mapper.findShopByFiwiId(idwifi);
        if (shop != null)
            timeCacheShop.put(idwifi, shop);
        return shop;
    }

}
