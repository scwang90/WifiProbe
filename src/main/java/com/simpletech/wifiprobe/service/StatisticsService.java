package com.simpletech.wifiprobe.service;

import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.model.entity.*;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service
 * Created by 树朾 on 2015/9/25.
 */
public interface StatisticsService {

    /**
     * 店铺-到访频次-分布
     *
     * @param idshop 区域ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<FrequencyMapValue> visitFrequencyMap(String idshop, Date start, Date end) throws Exception;

    /**
     * 店铺-路过频次-分布
     *
     * @param idshop 区域ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<FrequencyMapValue> pastFrequencyMap(String idshop, Date start, Date end) throws Exception;

    /**
     * 店铺-驻店时长-分布
     *
     * @param idshop 区域ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<DurationMapValue> visitDurationMap(String idshop, Date start, Date end) throws Exception;

    /**
     * 统计店铺的到访周期
     *
     * @param idshop 区域ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<PeriodMapValue> visitPeriodMap(String idshop, Date start, Date end) throws Exception;

    /**
     * 店铺-驻店时长-时段
     *
     * @param idshop 区域ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    DurationSpanValue visitDurationSpan(String idshop, Date start, Date end) throws Exception;

    /**
     * 店铺-驻店时长-趋势
     *
     * @param idshop 区域ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<DurationTrendValue> visitDurationTrend(String idshop, Period period, Date start, Date end) throws Exception;

    /**
     * 店铺-客流量-时段
     *
     * @param idshop 区域ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    EntryTrendValue visitSpan(String idshop, Date start, Date end) throws Exception;

    /**
     * 店铺-客流量-趋势
     *
     * @param idshop 区域ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<EntryTrendValue> visitTrend(String idshop, Period period, Date start, Date end) throws Exception;


    /**
     * 店铺-设备品牌-排行
     *
     * @param idshop 区域ID
     * @param ranktype 排序类型 按 vt|uv|pv
     * @param start    开始时间
     * @param end      结束时间
     * @param limit    分页限制
     * @param skip     分页起始
     * @return 排行数据
     */
    List<DeviceBrandValue> deviceBrandRank(String idshop, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception;
    List<DeviceBrandValue> deviceBrandRanking(String idshop, RankingType ranktype, Date start, Date end, int limit, int skip) throws Exception;

    /**
     * 店铺-新老用户-时段
     *
     * @param idshop 区域ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    List<UserTypeSpanValue> userTypeSpan(String idshop, Date start, Date end) throws Exception;

    /**
     * 店铺-新老用户-趋势
     *
     * @param idshop 区域ID
     * @param start    开始时间
     * @param end      结束时间
     * @return 新老用户趋势
     */
    List<UserTypeTrendValue> userTypeTrend(String idshop, Period period, Date start, Date end) throws Exception;

    /**
     * 店铺-活跃度-分布
     *
     * @param idshop 区域ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 活跃度分布
     */
    List<UserLivenessMapValue> userLivenessMap(String idshop, Date start, Date end) throws Exception;

    /**
     * 店铺-活跃度-趋势
     *
     * @param idshop 区域ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return 活跃度趋势
     */
    List<UserLivenessTrendMapValue> userLivenessTrend(String idshop, Period period, Date start, Date end) throws Exception;

    /**
     * 探针-在线台数
     *
     * @param idshop 区域ID
     * @return 在线台数
     */
    int onlineProbe(String idshop) throws Exception;
    List<OnlineValue> onlineProbeAll() throws Exception;

    /**
     * 探针-在线人数
     *
     * @param idshop 区域ID
     * @return 在线人数
     */
    int onlineUser(String idshop) throws Exception;
    List<OnlineValue> onlineUserAll() throws Exception;
}
