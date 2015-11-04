package com.simpletech.wifiprobe.mapper;

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
public interface CustomerTypeStatisticsMapper {

    /**
     * 新老用户
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    @Select("SELECT DATE_FORMAT(time_entry,'%y%m%d%H') date,SUM(is_new_user) nv, COUNT(DISTINCT end_brand) uv,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=${idshop} AND (time_entry BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<CustomerValue> customerHour(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(time_entry,'%y%m%d') date,SUM(is_new_user) nv, COUNT(DISTINCT end_brand) uv ,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=${idshop} AND (time_entry BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<CustomerValue> customerDay(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(time_entry,'%y-%u') date,SUM(is_new_user) nv, COUNT(DISTINCT end_brand) uv,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=${idshop} AND (time_entry BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<CustomerValue> customerWeek(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(time_entry,'%y%m') date,SUM(is_new_user) nv, COUNT(DISTINCT end_brand) uv,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=${idshop} AND (time_entry BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
    List<CustomerValue> customerMonth(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;

//    /**
//     * 老客户活跃度
//     * @param idshop
//     * @param start
//     * @param end
//     * @return
//     * @throws Exception
//     */
//    @Select("SELECT DATE_FORMAT(time_entry,'%y%m%d%H') date,SUM(is_new_user) nv, COUNT(DISTINCT end_brand) uv,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=${idshop} AND is_new_user <> 1 AND (time_entry BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
//    List<LivenessValue> customerLivenessHour(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;
//    @Select("SELECT DATE_FORMAT(time_entry,'%y%m%d') date,SUM(is_new_user) nv, COUNT(DISTINCT end_brand) uv ,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=${idshop} AND (time_entry BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
//    List<LivenessValue> customerLivenessDay(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;
//    @Select("SELECT DATE_FORMAT(time_entry,'%y-%u') date,SUM(is_new_user) nv, COUNT(DISTINCT end_brand) uv,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=${idshop} AND (time_entry BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
//    List<LivenessValue> customerLivenessWeek(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;
//    @Select("SELECT DATE_FORMAT(time_entry,'%y%m') date,SUM(is_new_user) nv, COUNT(DISTINCT end_brand) uv,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=${idshop} AND (time_entry BETWEEN #{start} AND #{end}) GROUP BY date ORDER BY date ")
//    List<LivenessValue> customerLivenessMonth(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计老客户在min，max之间的活跃度
     *
     * @param idshop 网站ID
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT DATE_FORMAT(time_entry,'%y%m%d%H') date,COUNT(id) num,COUNT(DISTINCT end_brand) uv,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=#{idshop} AND is_new_user <> 1 AND(time_from_last>=#{min} AND time_from_last<=#{max}) AND(create_time BETWEEN #{start} AND #{end}) GROUP BY end_brand  ORDER BY date")
    List<LivenessValue> customerLivenessHour(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(time_entry,'%y%m%d') date,COUNT(id) num,COUNT(DISTINCT end_brand) uv,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=#{idshop} AND is_new_user <> 1 AND(time_from_last>=#{min} AND time_from_last<=#{max}) AND(create_time BETWEEN #{start} AND #{end}) GROUP BY end_brand  ORDER BY date")
    List<LivenessValue> customerLivenessDay(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(time_entry,'%y-%u') date,COUNT(id) num,COUNT(DISTINCT end_brand) uv,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=#{idshop} AND is_new_user <> 1 AND(time_from_last>=#{min} AND time_from_last<=#{max}) AND(create_time BETWEEN #{start} AND #{end}) GROUP BY end_brand  ORDER BY date")
    List<LivenessValue> customerLivenessWeek(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT DATE_FORMAT(time_entry,'%y%m') date,COUNT(id) num,COUNT(DISTINCT end_brand) uv,SUM(time_duration) dt,SUM(time_from_last) vp FROM t_visit WHERE idshop=#{idshop} AND is_new_user <> 1 AND(time_from_last>=#{min} AND time_from_last<=#{max}) AND(create_time BETWEEN #{start} AND #{end}) GROUP BY end_brand  ORDER BY date")
    List<LivenessValue> customerLivenessMonth(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;

}
