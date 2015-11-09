package com.simpletech.wifiprobe.mapper;

import com.simpletech.wifiprobe.model.entity.*;
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
//    @Select("SELECT create_time date,SUM(is_new_user) nv, COUNT(DISTINCT mac_device) uv,AVG(time_duration) dt,AVG(time_from_last) vp FROM t_visit WHERE idshop=${idshop} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY is_new_user ORDER BY date")
    @Select("SELECT\n" +
            "  is_new_user isNewUser,\n" +
            "  num,\n" +
            "  dt,\n" +
            "  total / co vp\n" +
            "FROM (SELECT\n" +
            "        is_new_user,\n" +
            "        COUNT(id)                                  num,\n" +
            "        AVG(time_duration)                         dt,\n" +
            "        SUM(time_from_last > 0)                    co,\n" +
            "        SUM((time_from_last > 0) * time_from_last) total\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "      GROUP BY is_new_user) AS t"
    )
    List<IsNewCustomerValue> customer(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计总量（visit|uv|pv）
     *
     * @param idshop 店铺ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据总量
     */
    @Select("SELECT COUNT(id) vt,COUNT(DISTINCT mac_device) uv,SUM(is_new_user) nv,SUM(count_logs) pv  FROM t_visit WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end}) ")
    CustomerValue countCustomer(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计老客户活跃度
     *
     * @param idshop
     * @param min
     * @param max
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
//    @Select("SELECT COUNT(id) num,COUNT(DISTINCT mac_device) uv ,AVG(time_duration) dt,AVG(time_from_last) vp FROM t_visit WHERE idshop=#{idshop} AND is_new_user <> 1 AND(time_from_last>=#{min} AND time_from_last<=#{max}) AND(create_time BETWEEN #{start} AND #{end})")
    @Select("SELECT\n" +
            "  num,\n" +
            "  dt,\n" +
            "  total / co vp\n" +
            "FROM (SELECT\n" +
            "        COUNT(id)                                  num,\n" +
            "        AVG(time_duration)                         dt,\n" +
            "        SUM(time_from_last > 0)                    co,\n" +
            "        SUM((time_from_last > 0) * time_from_last) total\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND is_new_user<>1\n" +
            "            AND(time_from_last>=#{min} AND time_from_last<=#{max})\n" +
            "            AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "      ) AS t")
    List<LivenessValue> customerLiveness(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT\n" +
            "  date,\n" +
            "  num\n" +
            "FROM (SELECT\n" +
            "    DATE_FORMAT(create_time, '%y%m%d%H')           date,\n" +
            "        COUNT(id)                                  num\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND is_new_user<>1\n" +
            "            AND(time_from_last>=#{min} AND time_from_last<=#{max})\n" +
            "            AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "      ) AS t")
    List<LivenessTrendValue> livenessTrendHour(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT\n" +
            "  date,\n" +
            "  num\n" +
            "FROM (SELECT\n" +
            "    DATE_FORMAT(create_time, '%y%m%d')           date,\n" +
            "        COUNT(id)                                  num\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND is_new_user<>1\n" +
            "            AND(time_from_last>=#{min} AND time_from_last<=#{max})\n" +
            "            AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "      ) AS t")
    List<LivenessTrendValue> livenessTrendDay(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT\n" +
            "  date,\n" +
            "  num\n" +

            "FROM (SELECT\n" +
            "    DATE_FORMAT(create_time, '%y-%u')           date,\n" +
            "        COUNT(id)                                  num\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND is_new_user<>1\n" +
            "            AND(time_from_last>=#{min} AND time_from_last<=#{max})\n" +
            "            AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "      ) AS t")
    List<LivenessTrendValue> livenessTrendWeek(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;

    @Select("SELECT\n" +
            "  date,\n" +
            "  num\n" +
            "FROM (SELECT\n" +
            "    DATE_FORMAT(create_time, '%y%m')           date,\n" +
            "        COUNT(id)                                  num\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND is_new_user<>1\n" +
            "            AND(time_from_last>=#{min} AND time_from_last<=#{max})\n" +
            "            AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "      ) AS t")
    List<LivenessTrendValue> livenessTrendMonth(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计顾客趋势
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
//    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d%H') date,SUM(is_new_user) nv,COUNT(DISTINCT mac_device) uv FROM t_visit WHERE idshop=#{idshop} AND(create_time BETWEEN #{start} AND #{end}) GROUP BY date")
    @Select("SELECT\n" +
            "  date,\n" +
            "  num,\n" +
            "  nv,\n" +
            "  num-nv ov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d%H')      date,\n" +
            "        COUNT(id)        num,\n" +
            "        SUM(is_new_user) nv\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t;")
    List<CustomerTrendValue> customerTrendHour(@Param("idshop") String idshop,@Param("entry") int entry, @Param("start") Date start, @Param("end") Date end) throws Exception;

    //    @Select("SELECT DATE_FORMAT(create_time,'%y%m%d') date,SUM(is_new_user) nv,COUNT(DISTINCT mac_device) uv FROM t_visit WHERE idshop=#{idshop} AND(create_time BETWEEN #{start} AND #{end}) GROUP BY date")
    @Select("SELECT\n" +
            "  date,\n" +
            "  num,\n" +
            "  nv,\n" +
            "  num-nv ov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d')      date,\n" +
            "        COUNT(id)        num,\n" +
            "        SUM(is_new_user) nv\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t;")
    List<CustomerTrendValue> customerTrendDay(@Param("idshop") String idshop,@Param("entry") int entry, @Param("start") Date start, @Param("end") Date end) throws Exception;

    //    @Select("SELECT DATE_FORMAT(create_time,'%y-%u') date,SUM(is_new_user) nv,COUNT(DISTINCT mac_device) uv FROM t_visit WHERE idshop=#{idshop} AND(create_time BETWEEN #{start} AND #{end}) GROUP BY date")
    @Select("SELECT\n" +
            "  date,\n" +
            "  num,\n" +
            "  nv,\n" +
            "  num-nv ov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y-%u')      date,\n" +
            "        COUNT(id)        num,\n" +
            "        SUM(is_new_user) nv\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t;")
    List<CustomerTrendValue> customerTrendWeek(@Param("idshop") String idshop,@Param("entry") int entry, @Param("start") Date start, @Param("end") Date end) throws Exception;

    //    @Select("SELECT DATE_FORMAT(create_time,'%y%m') date,SUM(is_new_user) nv,COUNT(DISTINCT mac_device) uv FROM t_visit WHERE idshop=#{idshop} AND(create_time BETWEEN #{start} AND #{end}) GROUP BY date")
    @Select("SELECT\n" +
            "  date,\n" +
            "  num,\n" +
            "  nv,\n" +
            "  num-nv ov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m')      date,\n" +
            "        COUNT(id)        num,\n" +
            "        SUM(is_new_user) nv\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t;")
    List<CustomerTrendValue> customerTrendMonth(@Param("idshop") String idshop,@Param("entry") int entry, @Param("start") Date start, @Param("end") Date end) throws Exception;

}
