package com.simpletech.wifiprobe.dao;

import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.DurationSpanValue;
import com.simpletech.wifiprobe.model.entity.DurationTrendValue;
import com.simpletech.wifiprobe.model.entity.EntryTrendValue;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 数据统计 的Dao接口
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface StatisticsDao {

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

//    /**
//     * 统计店铺的到访频次
//     *
//     * @param idshop 网站ID
//     * @param start  开始时间
//     * @param end    结束时间
//     * @return 统计数据
//     */
//    List<FrequencyMapValue> visitFrequencyMap(String idshop, Date start, Date end) throws Exception;

    /**
     * 店铺-到访频次-分布
     * 统计店铺到访频次在min，max之间的条数
     *
     * @param idshop 网站ID
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    int visitFrequencyMap(String idshop, int min, int max, Date start, Date end) throws Exception;

    /**
     * 店铺-驻店时长-分布
     * 统计店铺停留时间在min，max之间的条数
     *
     * @param idshop 网站ID
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    int visitDurationMap(String idshop, int min, int max, Date start, Date end) throws Exception;

    /**
     * 获取[统计店铺的到访周期]所需的元数据
     *
     * @param idshop      网站ID
     * @param minduration 最小停留时间（过滤用）
     * @param start       开始时间
     * @param end         结束时间
     * @return 元数据(先按 mac 排序，再按 create_time 排序)
     */
    List<Integer> visitPeriodMap(String idshop, int minduration, Date start, Date end) throws Exception;

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
    DurationSpanValue visitDurationSpan(String idshop, int entry, int deep, int jump, Date start, Date end) throws Exception;

    /**
     * 店铺-驻店时长-趋势
     *
     * @param idshop 网站ID
     * @param entry  入店标准
     * @param deep   深访标准
     * @param jump   跳出标准
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<DurationTrendValue> visitDurationTrendHour(String idshop, int entry, int deep, int jump, Date start, Date end) throws Exception;
    List<DurationTrendValue> visitDurationTrendDay(String idshop, int entry, int deep, int jump, Date start, Date end) throws Exception;
    List<DurationTrendValue> visitDurationTrendWeek(String idshop, int entry, int deep, int jump, Date start, Date end) throws Exception;
    List<DurationTrendValue> visitDurationTrendMonth(String idshop, int entry, int deep, int jump, Date start, Date end) throws Exception;

    /**
     * 店铺-入店人次-趋势
     *
     * @param idshop 网站ID
     * @param entry  入店标准
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<EntryTrendValue> visitEntryTrendHour(String idshop, int entry, Date start, Date end) throws Exception;
    List<EntryTrendValue> visitEntryTrendDay(String idshop, int entry, Date start, Date end) throws Exception;
    List<EntryTrendValue> visitEntryTrendWeek(String idshop, int entry, Date start, Date end) throws Exception;
    List<EntryTrendValue> visitEntryTrendMonth(String idshop, int entry, Date start, Date end) throws Exception;
}
