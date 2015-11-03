package com.simpletech.wifiprobe.mapper;

import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.DurationValue;
import com.simpletech.wifiprobe.model.entity.FrequencyValue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 统计Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface StatisticsMapper {

    /**
     * mac的访问记录
     *
     * @param idshop 网站ID
     * @param mac    mac地址
     * @param start  开始时间
     * @param end    结束时间
     * @return mac的访问记录
     */
    @Select("SELECT id , idshop , idwifi , mac_device macDevice , time_entry timeEntry , time_leave timeLeave , time_duration timeDuration , is_new_user isNewUser , count_logs countLogs , end_brand endBrand , create_time createTime , update_time updateTime FROM t_visit WHERE idshop=#{idshop} AND mac_device=#{mac} AND (create_time BETWEEN #{start} AND #{end})")
    List<Visit> visitmac(@Param("idshop") String idshop, @Param("mac") String mac, @Param("start") Date start, @Param("end") Date end) throws Exception;

//    /**
//     * 统计店铺的到访频次
//     *
//     * @param idshop 网站ID
//     * @param start  开始时间
//     * @param end    结束时间
//     * @return 统计数据
//     */
//    @Select("SELECT mac_device fre,COUNT(id) num FROM t_visit WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY mac_device ORDER BY num")
//    List<FrequencyValue> visitfrequency(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;


    /**
     * 统计店铺到访频次在min，max之间的条数
     *
     * @param idshop 网站ID
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT COUNT(*) FROM (SELECT COUNT(id) num FROM t_visit WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY mac_device ) AS t WHERE t.num>=#{min} AND t.num<=#{max} ")
    int visitfrequency(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计店铺停留时间在min，max之间的数量
     *
     * @param idshop 网站ID
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT COUNT(id) FROM t_visit WHERE idshop=#{idshop} AND time_duration>#{min} AND time_duration<=#{max} AND (create_time BETWEEN #{start} AND #{end}) ")
    int visitduration(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;
}
