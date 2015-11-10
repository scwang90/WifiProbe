package com.simpletech.wifiprobe.service;

import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.model.entity.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service
 * Created by 树朾 on 2015/9/25.
 */
public interface StatisticsService {

    /**
     * mac的访问记录
     *
     * @param idshop 网站ID
     * @param mac    mac地址
     * @param start  开始时间
     * @param end    结束时间
     * @return mac的访问记录
     */
    List<Visit> visitmac(String idshop, String mac, Date start, Date end) throws Exception;

    /**
     * 店铺-到访频次-分布
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<FrequencyMapValue> visitFrequencyMap(String idshop, Date start, Date end) throws Exception;

    /**
     * 店铺-驻店时长-分布
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<DurationMapValue> visitDurationMap(String idshop, Date start, Date end) throws Exception;

    /**
     * 统计店铺的到访周期
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<PeriodMapValue> visitPeriodMap(String idshop, Date start, Date end) throws Exception;

    /**
     * 店铺-驻店时长-时段
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    DurationSpanValue visitDurationSpan(String idshop, Date start, Date end) throws Exception;

    /**
     * 店铺-驻店时长-趋势
     *
     * @param idshop 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<DurationTrendValue> visitDurationTrend(String idshop, Period period, Date start, Date end) throws Exception;

    /**
     * 店铺-入店人次-趋势
     *
     * @param idshop 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    List<EntryTrendValue> visitEntryTrend(String idshop, Period period, Date start, Date end) throws Exception;


    /**
     * 店铺-设备品牌-排行
     *
     * @param idshop 网站ID
     * @param ranktype 排序类型 按 vt|uv|pv
     * @param start    开始时间
     * @param end      结束时间
     * @param limit    分页限制
     * @param skip     分页起始
     * @return 排行数据
     */
    List<DeviceBrandValue> deviceBrandRanking(String idshop, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception;
}
