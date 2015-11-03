package com.simpletech.wifiprobe.mapper;

import com.simpletech.wifiprobe.model.Visit;
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
    List<Visit> macvisit(@Param("idshop") String idshop, @Param("mac") String mac, @Param("start") Date start, @Param("end") Date end) throws Exception;
}
