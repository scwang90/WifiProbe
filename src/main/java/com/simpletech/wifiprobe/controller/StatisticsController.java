package com.simpletech.wifiprobe.controller;

import com.simpletech.wifiprobe.controller.base.BaseController;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.model.constant.TimeType;
import com.simpletech.wifiprobe.model.entity.*;
import com.simpletech.wifiprobe.service.StatisticsService;
import com.simpletech.wifiprobe.util.AfReflecter;
import com.simpletech.wifiprobe.util.AfStringUtil;
import com.simpletech.wifiprobe.util.JacksonUtil;
import com.simpletech.wifiprobe.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 数据统计接口
 * Created by 树朾 on 2015/9/25.
 */
@RestController
@RequestMapping("v1/area/{areaId}")
public class StatisticsController extends BaseController {

    @Autowired
    StatisticsService service;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyyMMddHHmmss"), true));
    }

    /**
     * 探针-在线台数
     *
     * @param areaId 区域ID
     * @return 在线台数
     */
    @RequestMapping("probe/online")
    public Object probeOnline(@PathVariable String areaId) throws Exception {
        return service.onlineProbe(areaId);
    }

    /**
     * 探针-在线台数
     *
     * @param areaId 区域ID
     * @param body   店铺ID数组json格式参数
     * @return 在线台数
     */
    @RequestMapping("online/probe")
    public Object onlineProbe(@PathVariable String areaId, @RequestBody(required = false) String body) throws Exception {
        if ("all".equals(areaId)) {
            if (body != null && body.matches("^\\[\".+\"\\]$")) {
                return service.onlineProbeShopIds(body);
            } else if (body != null && body.trim().length() > 0) {
                throw new ServiceException("请使用json:[\"id1\",\"id2\"]格式传参！");
            }
            return service.onlineProbeAll();
        } else
            return service.onlineProbe(areaId);
    }

    /**
     * 探针-在线人数
     *
     * @param areaId 区域ID
     * @param body   店铺ID数组json格式参数
     * @return 在线人数
     */
    @RequestMapping("online/user")
    public Object onlineUser(@PathVariable String areaId, @RequestBody(required = false) String body) throws Exception {
        if ("all".equals(areaId)) {
            if (body != null && body.matches("^\\[\".+\"\\]$")) {
                return service.onlineUserShopIds(body);
            } else if (body != null && body.trim().length() > 0) {
                throw new ServiceException("请使用json:[\"id1\",\"id2\"]格式传参！");
            }
            return service.onlineUserAll();
        } else
            return service.onlineUser(areaId);
    }

    /**
     * 访问时间-分布 （服务器时间，浏览器时间）
     *
     * @param areaId 网站ID
     * @param type   时间类型  server-服务器时间|local-浏览器时间
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/{type:server|local}/time/map")
    public Object visitTimeMap(@PathVariable String areaId, @PathVariable TimeType type, Integer offset, Period span, Date start, Date end) {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        int days = countDays(Period.day, start, end);
        return service.visitTimeMap(areaId, type, days, start, end);
    }

    /**
     * 店铺-到访频次-分布
     *
     * @param areaId 区域ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/frequency/map")
    public Object visitFrequencyMap(@PathVariable String areaId, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        return service.visitFrequencyMap(areaId, start, end);
    }

    /**
     * 店铺-路过频次-分布
     *
     * @param areaId 区域ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("past/frequency/map")
    public Object pastFrequencyMap(@PathVariable String areaId, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        return service.pastFrequencyMap(areaId, start, end);
    }

    /**
     * 店铺-驻店时长-时段
     *
     * @param areaId 区域ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/duration/span")
    public Object visitDurationSpan(@PathVariable String areaId, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        return service.visitDurationSpan(areaId, start, end);
    }

    /**
     * 店铺-驻店时长-趋势
     *
     * @param areaId 区域ID
     * @param period 时段周期 [时|日|周|月]
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/duration/trend/{period:hour|day|week|month}")
    public Object visitDurationTrend(@PathVariable String areaId, @PathVariable Period period, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        this.doCheckPeriod(period, start, end);
        List<DurationTrendValue> list = service.visitDurationTrend(areaId, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, DurationTrendValue.class);
        return list;
    }

    /**
     * 店铺-驻店时长-分布
     *
     * @param areaId 区域ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/duration/map")
    public Object visitDurationMap(@PathVariable String areaId, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        return service.visitDurationMap(areaId, start, end);
    }

    /**
     * 店铺-到访周期-分布
     *
     * @param areaId 区域ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/period/map")
    public Object visitPeriodMap(@PathVariable String areaId, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        return service.visitPeriodMap(areaId, start, end);
    }

    /**
     * 店铺-客流量-时段
     *
     * @param areaId 区域ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/span")
    public Object visitSpan(@PathVariable String areaId, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        return mapExclude(service.visitSpan(areaId, start, end), new String[]{"time", "date"});
    }

    /**
     * 店铺-客流量-趋势
     *
     * @param areaId 区域ID
     * @param period 时段周期 [时|日|周|月]
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("visit/trend/{period:hour|day|week|month}")
    public Object visitTrend(@PathVariable String areaId, @PathVariable Period period, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        this.doCheckPeriod(period, start, end);
        List<EntryTrendValue> list = service.visitTrend(areaId, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, EntryTrendValue.class);
        return list;
    }

    /**
     * 店铺-设备品牌-排行
     *
     * @param areaId   区域ID
     * @param ranktype 排序类型 按 vt|uv|pv
     * @param offset   偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span     跨度 [day|week|month|year]
     * @param start    开始时间 ("yyyyMMddHHmmss")
     * @param end      结束时间 ("yyyyMMddHHmmss")
     * @param limit    分页限制
     * @param skip     分页起始
     * @return 排行数据
     */
    @RequestMapping("device/brand/ranking/{ranktype:vt|uv|pv}/{limit:\\d+}/{skip:\\d+}")
    public Object deviceBrandRanking(@PathVariable String areaId, @PathVariable RankingType ranktype, @PathVariable int limit, @PathVariable int skip, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        return service.deviceBrandRanking(areaId, ranktype, start, end, limit, skip);
    }

    @RequestMapping("device/brand/rank/{ranktype:vt|uv|pv}/{limit:\\d+}/{skip:\\d+}")
    public Object deviceBrandRank(@PathVariable String areaId, @PathVariable RankingType ranktype, @PathVariable int limit, @PathVariable int skip, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        return service.deviceBrandRank(areaId, ranktype, start, end, limit, skip);
    }

    /**
     * 店铺-新老用户-时段
     *
     * @param areaId 区域ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 新老用户
     */
    @RequestMapping("user/type/span")
    public Object userTypeSpan(@PathVariable String areaId, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        return service.userTypeSpan(areaId, start, end);
    }

    /**
     * 店铺-新老用户-趋势
     *
     * @param areaId 区域ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 新老用户趋势
     */
    @RequestMapping("user/type/trend/{period:hour|day|week|month}")
    public Object userTypeTrend(@PathVariable String areaId, @PathVariable Period period, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        this.doCheckPeriod(period, start, end);
        List<UserTypeTrendValue> list = service.userTypeTrend(areaId, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, UserTypeTrendValue.class);
        return list;
    }

    /**
     * 店铺-活跃度-分布
     *
     * @param areaId 区域ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 活跃度分布
     */
    @RequestMapping("user/liveness/map")
    public Object userLivenessMap(@PathVariable String areaId, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        return service.userLivenessMap(areaId, start, end);
    }

    /**
     * 店铺-活跃度-趋势
     *
     * @param areaId 区域ID
     * @param period 时段周期 [时|日|周|月]
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 活跃度趋势
     */
    @RequestMapping("user/liveness/trend/{period:hour|day|week|month}")
    public Object userLivenessTrend(@PathVariable String areaId, @PathVariable Period period, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        this.doCheckPeriod(period, start, end);
        List<UserLivenessTrendMapValue> list = service.userLivenessTrend(areaId, period, start, end);
        for (UserLivenessTrendMapValue value : list) {
            value.setTrend(fulldata(value.getTrend(), period.getFormat(), period.getField(), start, end, UserLivenessTrendValue.class));
        }
        return list;
    }

    /**
     * 计算分割段数
     *
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return 段数
     */
    private int countDays(Period period, Date start, Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int count = 0;
        while (calendar.getTime().before(end)) {
            count++;
            calendar.add(period.getField(), 1);
        }
        return count;
    }

    /**
     * 检测时间分段合理性
     *
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     */
    private void doCheckPeriod(Period period, Date start, Date end) throws ServiceException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int count = 0, max = 200;
        while (calendar.getTime().before(end)) {
            if (count++ > max) {
                throw new ServiceException("数据量偏大，请调整时间跨度再试！");
            }
            calendar.add(period.getField(), 1);
        }
    }

    /**
     * 根据周期和便宜计算开始时间
     *
     * @param start  开始时间
     * @param span   时间跨度
     * @param offset 偏移
     * @return 开始时间
     */
    private Date timeStart(Date start, Period span, Integer offset) {
        if (span != null && offset != null /*&& !Period.hour.equals(span)*/) {
            DateFormat format = span.getFormat();
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(format.parse(format.format(calendar.getTime())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(span.getField(), offset);
            return calendar.getTime();
        }
        if (start == null) {
            return timeStart(new Date(), Period.year, -1000);
        }
        return start;
    }

    /**
     * 根据周期和便宜计算结束时间
     *
     * @param end    结束时间
     * @param span   时间跨度
     * @param offset 偏移
     * @return 结束时间
     */
    private Date timeEnd(Date end, Period span, Integer offset) {
        if (span != null && offset != null/* && !Period.hour.equals(span)*/) {
            DateFormat format = span.getFormat();
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(format.parse(format.format(calendar.getTime())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(span.getField(), offset + 1);
            return calendar.getTime();
        }
        if (end == null) {
            return timeEnd(new Date(), Period.year, 1000);
        }
        return end;
    }

    /**
     * 填充数据
     *
     * @param list 数据库有效数据列表
     * @return 填充的数据
     */
    private <T extends TrendValue> List<T> fulldata(List<T> list, DateFormat format, int field, Date start, Date end, Class<T> clazz) {
        Map<String, T> map = tomap(list);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        List<T> nlist = new ArrayList<>();
        while (calendar.getTime().before(end)) {
            String keytime = format.format(calendar.getTime());
            T value = map.get(keytime);
            if (value == null) {
                value = AfReflecter.newInstance(clazz);
                value.setEmpty();
                value.setDate(keytime);
                value.setTime(calendar.getTime());
                nlist.add(value);
            } else {
                nlist.add(value);
                map.remove(keytime);
            }
            calendar.add(field, 1);
        }
        for (Map.Entry<String, T> entry : map.entrySet()) {
            nlist.add(entry.getValue());
        }
        return nlist;
    }

    /**
     * 把list转为map 方便查找
     *
     * @param list 数据库有效数据列表
     * @return map
     */
    private <T extends TrendValue> Map<String, T> tomap(List<T> list) {
        Map<String, T> map = new LinkedHashMap<>();
        for (T value : list) {
            map.put(value.getDate(), value);
        }
        return map;
    }

}
