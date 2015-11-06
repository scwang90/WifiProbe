package com.simpletech.wifiprobe.mapper;

import com.simpletech.wifiprobe.model.entity.CustomerTrendValue;
import com.simpletech.wifiprobe.model.entity.CustomerValue;
import com.simpletech.wifiprobe.model.entity.LivenessValue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 统计Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface StatisticsCustomerTypeMapper {

    /**
     * 新老用户
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    @Select("SELECT create_time date,SUM(is_new_user) nv, COUNT(DISTINCT mac_device) uv,AVG(time_duration) dt,AVG(time_from_last) vp FROM t_visit WHERE idshop=${idshop} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY is_new_user ORDER BY date")
    List<CustomerValue> customer(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计总量（visit|uv|pv）
     * @param idshop 店铺ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据总量
     */
    @Select("SELECT COUNT(id) vt,COUNT(DISTINCT mac_device) uv,SUM(is_new_user) nv,SUM(count_logs) pv  FROM t_visit WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end}) ")
    CustomerValue countCustomer(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计老客户活跃度
     * @param idshop
     * @param min
     * @param max
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    @Select("SELECT create_time date,COUNT(id) num,COUNT(DISTINCT mac_device) uv,AVG(time_duration) dt,AVG(time_from_last) vp FROM t_visit WHERE idshop=#{idshop} AND is_new_user <> 1 AND(time_from_last>=#{min} AND time_from_last<=#{max}) AND(create_time BETWEEN #{start} AND #{end})")
    List<LivenessValue> customerLiveness(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计顾客趋势
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d%H') date,SUM(is_new_user) nv,COUNT(DISTINCT mac_device) uv FROM t_visit WHERE idshop=#{idshop} AND(create_time BETWEEN #{start} AND #{end}) GROUP BY date")
    List<CustomerTrendValue> customerTrendHour(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d') date,SUM(is_new_user) nv,COUNT(DISTINCT mac_device) uv FROM t_visit WHERE idshop=#{idshop} AND(create_time BETWEEN #{start} AND #{end}) GROUP BY date")
    List<CustomerTrendValue> customerTrendDay(@Param("idshop") String idshop,@Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(create_time,'%y-%u') date,SUM(is_new_user) nv,COUNT(DISTINCT mac_device) uv FROM t_visit WHERE idshop=#{idshop} AND(create_time BETWEEN #{start} AND #{end}) GROUP BY date")
    List<CustomerTrendValue> customerTrendWeek(@Param("idshop") String idshop,@Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(create_time,'%y%m') date,SUM(is_new_user) nv,COUNT(DISTINCT mac_device) uv FROM t_visit WHERE idshop=#{idshop} AND(create_time BETWEEN #{start} AND #{end}) GROUP BY date")
    List<CustomerTrendValue> customerTrendMonth(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;

}
