package com.simpletech.wifiprobe.mapper_shop;

import com.simpletech.wifiprobe.model.Shop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
public interface TrackerShopMapper {

    /**
     * 根据WIFIID获取店铺
     * @param idwifi Wifi Id
     * @return Shop or null
     */
    @Select("SELECT shopID , shopName , deleteFlag , createTime , description , config_probe_visit_expired configProbeVisitExpired , config_probe_user_expired configProbeUserExpired , config_probe_visit_signal configProbeVisitSignal , config_probe_visit_expired_wifi configProbeVisitExpiredWifi , config_probe_visit_signal_wifi configProbeVisitSignalWifi , config_probe_api_visit_counts configProbeApiVisitCounts , config_probe_api_visit_duration configProbeApiVisitDuration , config_probe_api_visit_duration_deep configProbeApiVisitDurationDeep , config_probe_api_visit_duration_jump configProbeApiVisitDurationJump , config_probe_api_visit_duration_enter configProbeApiVisitDurationEnter , config_probe_api_visit_period configProbeApiVisitPeriod , config_probe_api_liveness configProbeApiLiveness FROM shop WHERE shopID = (SELECT shopID FROM probe_binding WHERE probeID = #{idwifi})")
    Shop findShopByFiwiId(@Param("idwifi") String idwifi) throws Exception;

}
