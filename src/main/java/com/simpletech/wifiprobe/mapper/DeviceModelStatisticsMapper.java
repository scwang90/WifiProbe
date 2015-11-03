package com.simpletech.wifiprobe.mapper;

import com.simpletech.wifiprobe.model.entity.BrandValue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 统计Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface DeviceModelStatisticsMapper {

    /**
     * 统计设备信息
     *
     * @param idshop 店铺ID
     * @param start  开始
     * @param end    结束时间
     * @return 设备的访问记录
     */
    @Select("SELECT end_brand name, COUNT(id) vt,COUNT(DISTINCT end_brand) uv  FROM t_visit WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end}) group by name")
    List<BrandValue> brand(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;

    /**
     * 统计排行总量（visit|uv|pv）
     * @param idshop 店铺ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数据总量
     */
    @Select("SELECT COUNT(id) vt,COUNT(DISTINCT end_brand) uv  FROM t_visit WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end}) ")
    BrandValue coutBrand(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end) throws Exception;

}
