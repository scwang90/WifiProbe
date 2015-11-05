package com.simpletech.wifiprobe.controller;

import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.entity.*;
import com.simpletech.wifiprobe.service.StatisticsCustomerTypeService;
import com.simpletech.wifiprobe.util.AfReflecter;
import com.simpletech.wifiprobe.util.AfStringUtil;
import com.simpletech.wifiprobe.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设备类型数据统计接口
 * Created by 树朾 on 2015/9/25.
 */
@RestController
@RequestMapping("api/statistics")
public class StatisticsCustomerTypeController {

    @Autowired
    StatisticsCustomerTypeService service;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyyMMddHHmmss"), true));
    }

    /**
     * 新老用户
     *
     * @param shopId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 新老用户
     */
    @RequestMapping("customer/shop/{shopId:\\d+}")
    public Object customer(@PathVariable String shopId, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        return service.customer(shopId, start, end);
    }

    /**
     * 老顾客的活跃度
     * @param shopId
     * @param offset
     * @param span
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    @RequestMapping("customer/liveness/{shopId:\\d+}")
    public Object customerLiveness(@PathVariable String shopId,Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
//        this.doCheckPeriod(period, start, end);
//        List<LivenessValue> list = service.customerLiveness(shopId, period, start, end);
//        list = fulldata(list, period.getFormat(), period.getField(), start, end, LivenessValue.class);
        return service.customerLiveness(shopId, start, end);
    }

    /**
     * 统计店铺到访顾客的趋势
     *
     * @param shopId 网站ID
     * @param offset 偏移 0=当天 -1=昨天 1=明天 -2 2 -3...
     * @param span   跨度 [day|week|month|year]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    @RequestMapping("customer/trend/{shopId:\\d+}/{period:hour|day|week|month}")
    public Object customerTrend(@PathVariable String shopId, @PathVariable Period period, Integer offset, Period span, Date start, Date end) throws Exception {
        end = timeEnd(end, span, offset);
        start = timeStart(start, span, offset);
        this.doCheckPeriod(period, start, end);
        List<CustomerTrendValue> list = service.customerTrend(shopId, period, start, end);
        list = fulldata(list, period.getFormat(), period.getField(), start, end, CustomerTrendValue.class);
        return list;
    }

    /**
     * 检测时间分段合理性
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     */
    private void doCheckPeriod(Period period, Date start, Date end) throws ServiceException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int count = 0;
        while (calendar.getTime().before(end)) {
            count++;
        }
        if (count > 200) {
            throw new ServiceException("数据量偏大，请调整时间跨度再试！");
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
    private Date timeStart(Date start, Period span, Integer offset) throws ParseException {
        if (span != null && offset != null /*&& !Period.hour.equals(span)*/) {
            DateFormat format = span.getFormat();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(format.format(calendar.getTime())));
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
    private Date timeEnd(Date end, Period span, Integer offset) throws Exception {
        if (span != null && offset != null/* && !Period.hour.equals(span)*/) {
            DateFormat format = span.getFormat();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(format.format(calendar.getTime())));
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

    /**
     * 把 int shopId 转成 string shopId
     *
     * @param shopId  网站ID
     * @param subshop 子项目
     * @return shopId
     */
    private String getIdSite(int shopId, String subshop) {
        if (AfStringUtil.isNotEmpty(subshop)) {
            String format = "%d AND idsubshop='%s'";
            return String.format(format, shopId, subshop);
        }
        return String.valueOf(shopId);
    }
}
