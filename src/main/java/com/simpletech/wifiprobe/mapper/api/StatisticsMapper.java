package com.simpletech.wifiprobe.mapper.api;

import com.simpletech.wifiprobe.model.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 统计Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface StatisticsMapper {

    /**
     * 访问时间 - 服务器时间 - 分布
     *
     * @param idsite 网站IDE
     * @param start  开始时间
     * @param end    结束时间
     * @return 访问时间
     */
    @Select("SELECT DATE_FORMAT(visit_servertime,'%H') time, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY time ORDER BY time ")
    List<VisitTimeMapValue> visitServerTimeMap(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 浏览器时间 - 服务器时间 - 分布
     *
     * @param idsite 网站IDE
     * @param start  开始时间
     * @param end    结束时间
     * @return 访问时间
     */
    @Select("SELECT DATE_FORMAT(visit_localtime,'%H') time, COUNT(id) vt, SUM(count_visits) pv, COUNT(DISTINCT idvisitor) uv, COUNT(DISTINCT location_ip) ip FROM t_visit AS t WHERE idsite=${idsite} AND (visit_servertime BETWEEN #{start} AND #{end}) GROUP BY time ORDER BY time ")
    List<VisitTimeMapValue> visitLocalTimeMap(@Param("idsite") String idsite, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-到访频次-分布
     * 统计店铺到访频次在min，max之间的条数
     *
     * @param idshop 区域ID
     * @param entry  入店标准时间（过滤用）
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT COUNT(*) FROM (SELECT COUNT(id) num FROM t_visit WHERE idshop=#{idshop} AND time_duration>#{entry} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY mac_device ) AS t WHERE t.num>=#{min} AND t.num<=#{max} ")
    int visitFrequencyMap(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-路过频次-分布
     * 统计店铺到访频次在min，max之间的条数
     *
     * @param idshop 区域ID
     * @param entry  入店标准时间（过滤用）
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT COUNT(*) FROM (SELECT COUNT(id) num FROM t_visit WHERE idshop=#{idshop} AND time_duration<#{entry} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY mac_device ) AS t WHERE t.num>=#{min} AND t.num<=#{max} ")
    int pastFrequencyMap(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-驻店时长-分布
     * 统计店铺停留时间在min，max之间的数量
     *
     * @param idshop 区域ID
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT COUNT(id) FROM t_visit WHERE idshop=#{idshop} AND time_duration>#{min} AND time_duration<=#{max} AND (create_time BETWEEN #{start} AND #{end}) ")
    int visitDurationMap(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);


    /**
     * 获取[统计店铺的到访周期]所需的元数据
     *
     * @param idshop 区域ID
     * @param entry  入店标准时间（过滤用）
     * @param start  开始时间
     * @param end    结束时间
     * @return 元数据-每个符合条件用户的平均周期列表-按小到大排序
     */
    @Select("SELECT AVG(time_from_last) period\n" +
            "FROM t_visit\n" +
            "WHERE idshop=#{idshop} \n" +
            "AND time_duration > #{entry} AND time_from_last > 0\n" +
            "AND (create_time BETWEEN #{start} AND #{end}) \n" +
            "GROUP BY mac_device\n" +
            "ORDER BY period \n")
    List<Integer> visitPeriodMap(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-驻店时长-时段
     *
     * @param idshop 区域ID
     * @param entry  驻店标准
     * @param deep   深访标准
     * @param jump   跳出标准
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    @Select("SELECT\n" +
            "  *,\n" +
            "  amount_deep / amount_total rate_deep,\n" +
            "  amount_jump / amount_total rate_jump\n" +
            "FROM (SELECT\n" +
            "        AVG(time_duration)                  dur_entry,\n" +
            "        (SELECT AVG(time_duration)\n" +
            "         FROM t_visit\n" +
            "         WHERE time_duration >= #{deep})    dur_deep,\n" +
            "        COUNT(id)                           amount_total,\n" +
            "        SUM(time_duration >= #{deep})       amount_deep,\n" +
            "        SUM(time_duration <= #{jump})       amount_jump\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND time_duration>=#{entry}\n" +
            "        AND (create_time BETWEEN #{start} AND #{end}) ) AS t")
    DurationSpanValue visitDurationSpan(@Param("idshop") String idshop, @Param("entry") int entry, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-驻店时长-趋势
     *
     * @param idshop 区域ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump, entry ,\n" +
            "  deep / entry       rdeep,\n" +
            "  jump / entry       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d%H')  date,\n" +
            "        COUNT(id)                             entry,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND time_duration >= #{entry} " +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendHour(@Param("idshop") String idshop, @Param("entry") int entry, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump, entry,\n" +
            "  deep / entry       rdeep,\n" +
            "  jump / entry       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d')    date,\n" +
            "        COUNT(id)                             entry,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND time_duration >= #{entry} " +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendDay(@Param("idshop") String idshop, @Param("entry") int entry, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump, entry,\n" +
            "  deep / entry       rdeep,\n" +
            "  jump / entry       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y-%u')     date,\n" +
            "        COUNT(id)                             entry,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND time_duration >= #{entry} " +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendWeek(@Param("idshop") String idshop, @Param("entry") int entry, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump, entry,\n" +
            "  deep / entry       rdeep,\n" +
            "  jump / entry       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m')      date,\n" +
            "        COUNT(id)                             entry,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND time_duration >= #{entry} " +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendMonth(@Param("idshop") String idshop, @Param("entry") int entry, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-客流量-时段
     *
     * @param idshop 区域ID
     * @param entry  入店标准
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    @Select("SELECT\n" +
            "  total,entry,past,nentry,oentry,\n" +
            "  past / total   rpast,\n" +
            "  entry / total  rentry,\n" +
            "  nentry / total rnentry,\n" +
            "  oentry / total roentry\n" +
            "FROM (\n" +
            "  SELECT\n" +
            "    COUNT(id)                                    total,\n" +
            "    SUM(time_duration >= #{entry})                    entry,\n" +
            "    SUM(time_duration < #{entry})                     past,\n" +
            "    SUM(is_new_user = 1 && time_duration >= #{entry}) nentry,\n" +
            "    SUM(is_new_user = 0 && time_duration >= #{entry}) oentry\n" +
            "  FROM t_visit\n" +
            "  WHERE idshop=#{idshop}\n" +
            "    AND (create_time BETWEEN #{start} AND #{end}) ) AS t")
    EntryTrendValue visitSpan(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-客流量-趋势
     *
     * @param idshop 区域ID
     * @param entry  入店标准
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    @Select("SELECT\n" +
            "  date,total,entry,past,nentry,oentry,\n" +
            "  past / total   rpast,\n" +
            "  entry / total  rentry,\n" +
            "  nentry / total rnentry,\n" +
            "  oentry / total roentry\n" +
            "FROM (\n" +
            "  SELECT\n" +
            "    DATE_FORMAT(create_time, '%y%m%d%H')           date,\n" +
            "    COUNT(id)                                    total,\n" +
            "    SUM(time_duration >= #{entry})                    entry,\n" +
            "    SUM(time_duration < #{entry})                     past,\n" +
            "    SUM(is_new_user = 1 && time_duration >= #{entry}) nentry,\n" +
            "    SUM(is_new_user = 0 && time_duration >= #{entry}) oentry\n" +
            "  FROM t_visit\n" +
            "  WHERE idshop=#{idshop}\n" +
            "    AND (create_time BETWEEN #{start} AND #{end}) " +
            "    GROUP BY date) AS t")
    List<EntryTrendValue> visitTrendHour(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  date,total,entry,past,nentry,oentry,\n" +
            "  past / total   rpast,\n" +
            "  entry / total  rentry,\n" +
            "  nentry / total rnentry,\n" +
            "  oentry / total roentry\n" +
            "FROM (\n" +
            "  SELECT\n" +
            "    DATE_FORMAT(create_time, '%y%m%d')           date,\n" +
            "    COUNT(id)                                    total,\n" +
            "    SUM(time_duration >= #{entry})                    entry,\n" +
            "    SUM(time_duration < #{entry})                     past,\n" +
            "    SUM(is_new_user = 1 && time_duration >= #{entry}) nentry,\n" +
            "    SUM(is_new_user = 0 && time_duration >= #{entry}) oentry\n" +
            "  FROM t_visit\n" +
            "  WHERE idshop=#{idshop}\n" +
            "    AND (create_time BETWEEN #{start} AND #{end}) " +
            "    GROUP BY date) AS t")
    List<EntryTrendValue> visitTrendDay(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  date,total,entry,past,nentry,oentry,\n" +
            "  past / total   rpast,\n" +
            "  entry / total  rentry,\n" +
            "  nentry / total rnentry,\n" +
            "  oentry / total roentry\n" +
            "FROM (\n" +
            "  SELECT\n" +
            "    DATE_FORMAT(create_time, '%y-%u')           date,\n" +
            "    COUNT(id)                                    total,\n" +
            "    SUM(time_duration >= #{entry})                    entry,\n" +
            "    SUM(time_duration < #{entry})                     past,\n" +
            "    SUM(is_new_user = 1 && time_duration >= #{entry}) nentry,\n" +
            "    SUM(is_new_user = 0 && time_duration >= #{entry}) oentry\n" +
            "  FROM t_visit\n" +
            "  WHERE idshop=#{idshop}\n" +
            "    AND (create_time BETWEEN #{start} AND #{end}) " +
            "    GROUP BY date) AS t")
    List<EntryTrendValue> visitTrendWeek(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  date,total,entry,past,nentry,oentry,\n" +
            "  past / total   rpast,\n" +
            "  entry / total  rentry,\n" +
            "  nentry / total rnentry,\n" +
            "  oentry / total roentry\n" +
            "FROM (\n" +
            "  SELECT\n" +
            "    DATE_FORMAT(create_time, '%y%m')           date,\n" +
            "    COUNT(id)                                    total,\n" +
            "    SUM(time_duration >= #{entry})                    entry,\n" +
            "    SUM(time_duration < #{entry})                     past,\n" +
            "    SUM(is_new_user = 1 && time_duration >= #{entry}) nentry,\n" +
            "    SUM(is_new_user = 0 && time_duration >= #{entry}) oentry\n" +
            "  FROM t_visit\n" +
            "  WHERE idshop=#{idshop}\n" +
            "    AND (create_time BETWEEN #{start} AND #{end}) " +
            "    GROUP BY date) AS t")
    List<EntryTrendValue> visitTrendMonth(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-设备品牌-排行
     *
     * @param idshop 区域ID
     * @param entry  入店标准
     * @param type   排序规则
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 设备品牌排行
     */
    @Select("SELECT\n" +
            "  name, vt, uv, pv,\n" +
            "  vt / tvt rvt,\n" +
            "  uv / tuv ruv,\n" +
            "  pv / tpv rpv\n" +
            "FROM\n" +
            "  (SELECT\n" +
            "     end_brand                  name,\n" +
            "     COUNT(id)                  vt,\n" +
            "     COUNT(DISTINCT mac_device) uv,\n" +
            "     SUM(count_logs)            pv\n" +
            "   FROM t_visit\n" +
            "   WHERE idshop = #{idshop}\n" +
            "     AND time_duration >= #{entry}\n" +
            "     AND (time_entry BETWEEN #{start} AND #{end})\n" +
            "   GROUP BY name\n" +
            "   ORDER BY ${type} DESC\n" +
            "   LIMIT ${skip}, ${limit}\n" +
            "  ) AS t1,\n" +
            "  (SELECT\n" +
            "     COUNT(id)                  tvt,\n" +
            "     COUNT(DISTINCT mac_device) tuv,\n" +
            "     SUM(count_logs)            tpv\n" +
            "   FROM t_visit\n" +
            "   WHERE idshop = #{idshop}\n" +
            "     AND time_duration >= #{entry}\n" +
            "     AND (time_entry BETWEEN #{start} AND #{end})\n" +
            "  ) AS t2")
    List<DeviceBrandValue> deviceBrand(@Param("idshop") String idshop, @Param("entry") int entry, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT end_brand name, COUNT(id) vt,COUNT(DISTINCT mac_device) uv,SUM(count_logs) pv FROM t_visit GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<DeviceBrandValue> doDeviceBrand(@Param("type") String type, @Param("limit") int limit, @Param("skip") int skip);

    /**
     * 店铺-新老用户-时段
     *
     * @param idshop 区域ID
     * @param entry  入店标准
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    @Select("SELECT\n" +
            "  uv,nuv,ovt,\n" +
            "  uv - nuv        ouv,\n" +
            "  vt - ovt        nvt,\n" +
            "  nuv / uv        rnuv,\n" +
            "  ovt / vt        rovt,\n" +
            "  (uv - nuv) / uv rouv,\n" +
            "  (vt - ovt) / vt rnvt,\n" +
            "  tnd / cnd       nstay,\n" +
            "  tod / cod       ostay,\n" +
            "  tnp / cnp       nperiod,\n" +
            "  top / cop       operiod\n" +
            "FROM (SELECT\n" +
            "        COUNT(id)                  vt,\n" +
            "        COUNT(DISTINCT mac_device) uv,\n" +
            "        SUM(is_new_user)           nuv,\n" +
            "        SUM(is_new_user=0)         ovt\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "        AND (time_duration >= #{entry} OR is_new_user=1)\n" +
            "        AND (create_time BETWEEN #{start} AND #{end})\n" +
            "     ) AS t1,\n" +
            "  (SELECT\n" +
            "     SUM(time_duration * (is_new_user = 1))        tnd,\n" +
            "     SUM((time_duration > 0) * (is_new_user = 1))  cnd,\n" +
            "     SUM(time_duration * (is_new_user = 0))        tod,\n" +
            "     SUM((time_duration > 0) * (is_new_user = 0))  cod,\n" +
            "     SUM(time_from_last * (is_new_user = 1))       tnp,\n" +
            "     SUM((time_from_last > 0) * (is_new_user = 1)) cnp,\n" +
            "     SUM(time_from_last * (is_new_user = 0))       top,\n" +
            "     SUM((time_from_last > 0) * (is_new_user = 0)) cop\n" +
            "   FROM t_visit\n" +
            "   WHERE idshop = #{idshop}\n" +
            "     AND time_duration >= #{entry}\n" +
            "     AND (create_time BETWEEN #{start} AND #{end})\n" +
            "  ) AS t2")
    Map<String, Object> userTypeSpan(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-新老用户-趋势
     *
     * @param idshop 区域ID
     * @param entry  入店标准
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT\n" +
            "  *,\n" +
            "  uv-nv ov,\n" +
            "  nv/uv rnv,\n" +
            "  (uv-nv)/uv rov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d%H')   date,\n" +
            "        COUNT(DISTINCT mac_device)             uv,\n" +
            "        SUM(is_new_user)                       nv\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<UserTypeTrendValue> userTypeTrendHour(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  *,\n" +
            "  uv-nv ov,\n" +
            "  nv/uv rnv,\n" +
            "  (uv-nv)/uv rov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d')     date,\n" +
            "        COUNT(DISTINCT mac_device)             uv,\n" +
            "        SUM(is_new_user)                       nv\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<UserTypeTrendValue> userTypeTrendDay(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  *,\n" +
            "  uv-nv ov,\n" +
            "  nv/uv rnv,\n" +
            "  (uv-nv)/uv rov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y-%u')      date,\n" +
            "        COUNT(DISTINCT mac_device)             uv,\n" +
            "        SUM(is_new_user)                       nv\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<UserTypeTrendValue> userTypeTrendWeek(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  *,\n" +
            "  uv-nv ov,\n" +
            "  nv/uv rnv,\n" +
            "  (uv-nv)/uv rov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m')       date,\n" +
            "        COUNT(DISTINCT mac_device)             uv,\n" +
            "        SUM(is_new_user)                       nv\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND time_duration >= #{entry}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<UserTypeTrendValue> userTypeTrendMonth(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-顾客活跃度-分布
     *
     * @param idshop 区域ID
     * @param min    最小周期时间
     * @param max    最大周期时间
     * @param start  开始时间
     * @param end    结束时间
     */
    @Select("SELECT\n" +
            "  COUNT(id)                                vt,\n" +
            "  COUNT(DISTINCT mac_device)               uv\n" +
            "FROM t_visit\n" +
            "WHERE idshop = #{idshop}\n" +
            "  AND time_duration >= #{entry}\n" +
            "  AND(time_from_last>=#{min} AND time_from_last<#{max})\n" +
            "  AND (create_time BETWEEN #{start} AND  #{end})")
    UserLivenessTrendValue userLivenessMap(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-顾客活跃度-趋势
     *
     * @param idshop 区域ID
     * @param min    最小周期时间
     * @param max    最大周期时间
     * @param start  开始时间
     * @param end    结束时间
     */
    @Select("SELECT\n" +
            "  DATE_FORMAT(create_time, '%y%m%d%H')     date,\n" +
            "  COUNT(id)                                vt,\n" +
            "  COUNT(DISTINCT mac_device)               uv\n" +
            "FROM t_visit\n" +
            "WHERE idshop = #{idshop}\n" +
            "  AND time_duration >= #{entry}\n" +
            "  AND(time_from_last>=#{min} AND time_from_last<#{max})\n" +
            "  AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "GROUP BY date")
    List<UserLivenessTrendValue> userLivenessTrendHour(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  DATE_FORMAT(create_time, '%y%m%d')       date,\n" +
            "  COUNT(id)                                vt,\n" +
            "  COUNT(DISTINCT mac_device)               uv\n" +
            "FROM t_visit\n" +
            "WHERE idshop = #{idshop}\n" +
            "  AND time_duration >= #{entry}\n" +
            "  AND(time_from_last>=#{min} AND time_from_last<#{max})\n" +
            "  AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "GROUP BY date")
    List<UserLivenessTrendValue> userLivenessTrendDay(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  DATE_FORMAT(create_time, '%y-%u')     date,\n" +
            "  COUNT(id)                                vt,\n" +
            "  COUNT(DISTINCT mac_device)               uv\n" +
            "FROM t_visit\n" +
            "WHERE idshop = #{idshop}\n" +
            "  AND time_duration >= #{entry}\n" +
            "  AND(time_from_last>=#{min} AND time_from_last<#{max})\n" +
            "  AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "GROUP BY date")
    List<UserLivenessTrendValue> userLivenessTrendWeek(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

    @Select("SELECT\n" +
            "  DATE_FORMAT(create_time, '%y%m')     date,\n" +
            "  COUNT(id)                                vt,\n" +
            "  COUNT(DISTINCT mac_device)               uv\n" +
            "FROM t_visit\n" +
            "WHERE idshop = #{idshop}\n" +
            "  AND time_duration >= #{entry}\n" +
            "  AND(time_from_last>=#{min} AND time_from_last<#{max})\n" +
            "  AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "GROUP BY date")
    List<UserLivenessTrendValue> userLivenessTrendMonth(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

    /**
     * 探针-在线台数
     *
     * @param idshop 区域ID
     * @return 在线台数
     */
    @Select("SELECT COUNT(DISTINCT idwifi)\n" +
            "FROM t_visit\n" +
            "WHERE idshop = #{idshop} AND update_time >= #{time}")
    int onlineProbe(@Param("idshop") String idshop, @Param("time") Date time);

    @Select("SELECT idshop id,COUNT(DISTINCT idwifi) num\n" +
            "FROM t_visit\n" +
            "WHERE update_time >= #{time} GROUP BY idshop ")
    List<OnlineValue> onlineProbeAll(@Param("time") Date time);

    @Select("SELECT idshop id,COUNT(DISTINCT idwifi) num\n" +
            "FROM t_visit\n" +
            "WHERE update_time >= #{time} AND idshop in (${ids}) GROUP BY idshop ")
    List<OnlineValue> onlineProbeShopIds(@Param("time") Date time, @Param("ids") String ids);

    /**
     * 探针-在线台数
     *
     * @param idshop 区域ID
     * @return 在线台数
     */
    @Select("SELECT COUNT(DISTINCT mac_device)\n" +
            "FROM t_visit\n" +
            "WHERE idshop = #{idshop} AND update_time >= #{time}")
    int onlineUser(@Param("idshop") String idshop, @Param("time") Date time);

    @Select("SELECT idshop id,COUNT(DISTINCT mac_device) num\n" +
            "FROM t_visit\n" +
            "WHERE update_time >= #{time} GROUP BY idshop ")
    List<OnlineValue> onlineUserAll(@Param("time") Date time);

    @Select("SELECT idshop id,COUNT(DISTINCT mac_device) num\n" +
            "FROM t_visit\n" +
            "WHERE update_time >= #{time} AND idshop in (${ids}) GROUP BY idshop ")
    List<OnlineValue> onlineUserShopIds(@Param("time") Date time, @Param("ids") String ids);
}
