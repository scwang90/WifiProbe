package com.simpletech.wifiprobe.mapper;

import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.DeviceBrandValue;
import com.simpletech.wifiprobe.model.entity.DurationSpanValue;
import com.simpletech.wifiprobe.model.entity.DurationTrendValue;
import com.simpletech.wifiprobe.model.entity.EntryTrendValue;
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
//    List<FrequencyMapValue> visitFrequencyMap(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;


    /**
     * 修改添加大于进店时间
     *
     *
     * 店铺-到访频次-分布
     * 统计店铺到访频次在min，max之间的条数
     *
     * @param idshop 网站ID
     * @param entry  入店标准时间（过滤用）
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT COUNT(*) FROM (SELECT COUNT(id) num FROM t_visit WHERE idshop=#{idshop} AND time_duration>#{entry} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY mac_device ) AS t WHERE t.num>=#{min} AND t.num<=#{max} ")
    int visitFrequencyMap(@Param("idshop") String idshop, @Param("entry") int entry, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     *
     * 修改分布区间支持浮点型
     *
     * 店铺-驻店时长-分布
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
    int visitDurationMap(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end) throws Exception;


    /**
     * 获取[统计店铺的到访周期]所需的元数据
     *
     * @param idshop      网站ID
     * @param entry       入店标准时间（过滤用）
     * @param start       开始时间
     * @param end         结束时间
     * @return 元数据-每个符合条件用户的平均周期列表-按小到大排序
     */
    @Select("SELECT AVG(time_from_last) period\n" +
            "FROM t_visit\n" +
            "WHERE idshop=#{idshop} \n" +
            "AND time_duration > #{entry} AND time_from_last > 0\n" +
            "AND (create_time BETWEEN #{start} AND #{end}) \n" +
            "GROUP BY mac_device\n" +
            "ORDER BY period \n")
    List<Integer> visitPeriodMap(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 店铺-驻店时长-时段
     *
     * @param idshop 网站ID
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
    DurationSpanValue visitDurationSpan(@Param("idshop") String idshop, @Param("entry") int entry, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 店铺-驻店时长-趋势
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump,\n" +
            "  deep / entry_count       rdeep,\n" +
            "  jump / entry_count       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d%H')  date,\n" +
            "        COUNT(id)                             entry_count,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND time_duration >= #{entry} " +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendHour(@Param("idshop") String idshop, @Param("entry") int entry, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump,\n" +
            "  deep / entry_count       rdeep,\n" +
            "  jump / entry_count       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d')    date,\n" +
            "        COUNT(id)                             entry_count,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND time_duration >= #{entry} " +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendDay(@Param("idshop") String idshop, @Param("entry") int entry, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump,\n" +
            "  deep / entry_count       rdeep,\n" +
            "  jump / entry_count       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y-%u')     date,\n" +
            "        COUNT(id)                             entry_count,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND time_duration >= #{entry} " +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendWeek(@Param("idshop") String idshop, @Param("entry") int entry, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump,\n" +
            "  deep / entry_count       rdeep,\n" +
            "  jump / entry_count       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m')      date,\n" +
            "        COUNT(id)                             entry_count,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM t_visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND time_duration >= #{entry} " +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendMonth(@Param("idshop") String idshop, @Param("entry") int entry, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 店铺-入店人次-趋势
     *
     * @param idshop 网站ID
     * @param entry   入店标准
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    @Select("SELECT\n" +
            "  date,entry,past,nentry,oentry,\n" +
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
    List<EntryTrendValue> visitEntryTrendHour(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT\n" +
            "  date,entry,past,nentry,oentry,\n" +
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
    List<EntryTrendValue> visitEntryTrendDay(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT\n" +
            "  date,entry,past,nentry,oentry,\n" +
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
    List<EntryTrendValue> visitEntryTrendWeek(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end) throws Exception;
    @Select("SELECT\n" +
            "  date,entry,past,nentry,oentry,\n" +
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
    List<EntryTrendValue> visitEntryTrendMonth(@Param("idshop") String idshop, @Param("entry") int entry, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 设备品牌排行
     *
     * @param idshop 网站ID
     * @param entry   入店标准
     * @param type   排序规则
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 设备品牌排行
     */
    @Select("SELECT end_brand name, COUNT(id) vt,COUNT(DISTINCT mac_device) uv,SUM(count_logs) pv FROM t_visit WHERE idshop=#{idshop} AND (time_entry BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<DeviceBrandValue> deviceBrand(@Param("idshop") String idshop, @Param("entry") int entry, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip) throws Exception;
}
