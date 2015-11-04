package com.simpletech.wifiprobe.dao;

import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.model.entity.PeriodValue;

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
//    List<FrequencyValue> visitfrequency(String idshop, Date start, Date end) throws Exception;

    /**
     * 统计店铺到访频次在min，max之间的条数
     *
     * @param idshop 网站ID
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    int visitfrequency(String idshop, int min, int max, Date start, Date end) throws Exception;

    /**
     * 统计店铺停留时间在min，max之间的条数
     *
     * @param idshop 网站ID
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    int visitduration(String idshop, int min, int max, Date start, Date end) throws Exception;


    /**
     * 获取[统计店铺的到访周期]所需的元数据
     *
     * @param idshop      网站ID
     * @param minvisit 最小停留时间（过滤用）
     * @param minduration 最小到访次数（过滤用）
     * @param start       开始时间
     * @param end         结束时间
     * @return 统计数据
     */
    List<PeriodValue> visitperiod(String idshop, int minvisit, int minduration, Date start, Date end) throws Exception;
}
