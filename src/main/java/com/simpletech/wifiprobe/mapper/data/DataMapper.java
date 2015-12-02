package com.simpletech.wifiprobe.mapper.data;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据查询处理Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface DataMapper {

    @Select("SELECT *\n" +
            "FROM (SELECT\n" +
            "        mac_device,\n" +
            "        idshop,\n" +
            "        sum(is_new_user) num,\n" +
            "        count(id)        vt,\n" +
            "        sum(count_logs)  pv\n" +
            "      FROM t_visit\n" +
            "      GROUP BY mac_device, idshop\n" +
            "      ORDER BY mac_device) AS t\n" +
            "WHERE num > 1\n" +
            "LIMIT ${skip},${limit}")
    List<Map<String, Object>> abnormalNewUserVisit(@Param("limit") int limit, @Param("skip") int skip);

    @Select("SELECT min(create_time) min\n" +
            "FROM t_visit\n" +
            "WHERE mac_device = #{mac} AND idshop = #{idshop}")
    Date abnormalNewUserVisitMin(@Param("mac") String mac, @Param("idshop") String idshop);

    @Update("UPDATE t_visit\n" +
            "SET is_new_user=0\n" +
            "WHERE mac_device = #{mac} AND idshop = #{idshop}\n" +
            "      AND create_time>#{min}")
    int abnormalNewUserVisitUpdate(@Param("mac") String mac, @Param("idshop") String idshop,@Param("min") Date min);
}

