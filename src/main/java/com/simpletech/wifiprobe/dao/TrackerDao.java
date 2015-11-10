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

    /**
     * 根据店铺ID和mac获取上一次 log
     * @param idshop 店铺ID
     * @param mac mac地址
     */
    MacLog findLastLogByMacAndShop(String idshop, String mac) throws Exception;

    /**
     * 根据店铺ID和mac获取上一次 Visit
     * @param idshop 店铺ID
     * @param mac mac地址
     */
    Visit findLastVistByMacAndShop(String idshop, String mac) throws Exception;

    /**
     * 根据店铺ID和mac获取上一次 VisitWifi
     * @param idshop 店铺ID
     * @param mac mac地址
     */
    VisitWifi findLastVisitWifiByMacAndShop(String idshop, String mac) throws Exception;

    /**
     * 插入一条新Visit数据
     * @param visit 添加的数据
     * @return 改变行数
     */
    int insertVisit(Visit visit) throws Exception;

//    /**
//     * 更新一条visit数据
//     * @param visit 需要更新数据
//     * @return 改变行数
//     */
//    int updateVisit(Visit visit) throws Exception;
//
//    /**
//     * 根据ID获取
//     * @param idvisit 主键ID
//     * @return 数据对象 or null
//     */
//    Visit findVisitById(String idvisit) throws Exception;

    /**
     * 插入一条新MacLog数据
     * @param log 添加的数据
     * @return 改变行数
     */
    int insertMacLog(MacLog log) throws Exception;

    /**
     * 新的Log触发Visit更新
     * @param log Log数据
     * @return 改变行数
     */
    int updateVisitByMacLog(MacLog log) throws Exception;

    /**
     * 插入一条新VisitWifi数据
     * @param visit 添加的数据
     * @return 改变行数
     */
    int insertVisitWifi(VisitWifi visit) throws Exception;

    /**
     * 新的Log触发VisitWifi更新
     * @param log Log数据
     * @return 改变行数
     */
    int updateVisitWifiByMacLog(MacLog log) throws Exception;
}
