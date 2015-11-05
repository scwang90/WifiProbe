package com.simpletech.wifiprobe.mapper;

import com.simpletech.wifiprobe.model.MacLog;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.VisitWifi;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
public interface TrackerMapper {

    /**
     * 根据WIFIID获取店铺
     * @param idwifi Wifi Id
     * @return Shop or null
     */
//    @Select("SELECT t.id id , config_visit_expired configVisitExpired , config_user_expired configUserExpired , t.create_time createTime , t.update_time updateTime FROM t_wifi LEFT OUTER JOIN t_shop AS t ON t_wifi.idshop=t.id WHERE t_wifi.id=#{idwifi}")
    @Select("SELECT id , config_visit_expired configVisitExpired , config_user_expired configUserExpired , config_visit_signal configVisitSignal , config_visit_expired_wifi configVisitExpiredWifi , config_visit_signal_wifi configVisitSignalWifi , create_time createTime , update_time updateTime  FROM t_shop WHERE id = (SELECT idshop FROM t_wifi WHERE id = #{idwifi})")
    Shop findShopByFiwiId(@Param("idwifi") String idwifi) throws Exception;

    /**
     * 根据店铺ID和mac获取上一次 log
     * @param idshop 店铺ID
     * @param mac mac地址
     */
    @Select("SELECT id , idshop , idwifi , idvisit , idvisitwifi , signal_strength signalStrength , mac_device macDevice , mac_wifi macWifi , ssid , local_time `localTime` , create_time createTime , update_time updateTime  FROM t_mac_log WHERE idshop=#{idshop} AND mac_device=#{mac} ORDER BY create_time DESC LIMIT 0,1 ")
    MacLog findLastLogByMacAndShop(@Param("idshop") String idshop, @Param("mac") String mac) throws Exception;

    /**
     * 根据店铺ID和mac获取上一次 Visit
     * @param idshop 店铺ID
     * @param mac mac地址
     */
    @Select("SELECT id , idshop , idwifi , mac_device macDevice , time_entry timeEntry , time_leave timeLeave , time_duration timeDuration , is_new_user isNewUser , count_logs countLogs , create_time createTime , update_time updateTime FROM t_visit WHERE idshop=#{idshop} AND mac_device=#{mac} ORDER BY create_time DESC LIMIT 0,1 ")
    Visit findLastVistByMacAndShop(@Param("idshop") String idshop, @Param("mac") String mac) throws Exception;

    /**
     * 插入一条新visit数据
     * @param visit 添加的数据
     * @return 改变的行数
     */
    @Insert("INSERT INTO t_visit ( id , idshop , idwifi , mac_device , end_brand , time_entry , time_leave , time_duration , time_from_last , is_new_user , count_logs , create_time , update_time ) VALUES ( #{id} , #{idshop} , #{idwifi} , #{macDevice} , #{endBrand} , NOW() , NOW() , 0 , #{timeFromLast}, #{isNewUser} , 1 , NOW() , NOW() )")
    int insertVisit(Visit visit) throws Exception;

//    /**
//     * 根据ID获取
//     * @param idvisit 主键ID
//     * @return null 或者 主键等于id的数据
//     */
//    @Select("SELECT id , idshop , idwifi , mac_device macDevice , time_entry timeEntry , time_leave timeLeave , time_duration timeDuration , is_new_user isNewUser , count_logs countLogs , create_time createTime , update_time updateTime FROM t_visit WHERE id=#{id}")
//    Visit findVisitById(@Param("id") String idvisit) throws Exception;
//
//    /**
//     * 更新一条visit数据
//     * @param visit 需要更新数据
//     * @return 改变行数
//     */
//    @Update("UPDATE t_visit SET idshop=#{idshop} , idwifi=#{idwifi} , mac_device=#{macDevice} , time_entry=#{timeEntry} , time_leave=#{timeLeave} , time_duration=#{timeDuration} , is_new_user=#{isNewUser} , count_logs=#{countLogs} , update_time=NOW() WHERE id=#{id} ")
//    int updateVisit(Visit visit) throws Exception;

    /**
     * 新的Log触发Visit更新
     * @param log Log数据
     * @return 改变行数
     */
    @Update("UPDATE t_visit SET time_leave=NOW() , time_duration=TIMESTAMPDIFF(SECOND,time_entry,NOW()) , count_logs=count_logs+1 , update_time=#{updateTime} WHERE id=#{idvisit} ")
    int updateVisitByMacLog(MacLog log) throws Exception;

    /**
     * 插入一条新visit数据
     * @param visit 添加的数据
     * @return 改变的行数
     */
    @Insert("INSERT INTO t_visit_wifi ( id , idshop , idwifi , mac_device , time_entry , time_leave , time_duration , count_logs , create_time , update_time ) VALUES ( #{id} , #{idshop} , #{idwifi} , #{macDevice} , NOW() , NOW() , 0 , 1 , NOW() , NOW() )")
    int insertVisitWifi(VisitWifi visit) throws Exception;

    /**
     * 新的Log触发VisitWifi更新
     * @param log Log数据
     * @return 改变行数
     */
    @Update("UPDATE t_visit_wifi SET time_leave=NOW() , time_duration=TIMESTAMPDIFF(SECOND,time_entry,NOW()) , count_logs=count_logs+1 , update_time=#{updateTime} WHERE id=#{idvisitwifi} ")
    int updateVisitWifiByMacLog(MacLog log) throws Exception;

    /**
     * 插入一条新MacLog数据
     * @param log 添加的数据
     * @return 改变行数
     */
    @Insert("INSERT INTO t_mac_log ( id , idshop , idwifi , idvisit ,idvisitwifi , mac_wifi , ssid , mac_device , signal_strength , local_time , create_time , update_time ) VALUES ( #{id} , #{idshop} , #{idwifi} , #{idvisit} ,#{idvisitwifi} , #{macWifi} , #{ssid} , #{macDevice} , #{signalStrength} , #{localTime} , NOW() , NOW() )")
    int insertMacLog(MacLog log) throws Exception;
}
