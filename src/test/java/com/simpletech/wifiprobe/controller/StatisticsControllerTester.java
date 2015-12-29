package com.simpletech.wifiprobe.controller;

import com.simpletech.wifiprobe.aspect.LoggingAspect;
import com.simpletech.wifiprobe.dao.StatisticsDao;
import com.simpletech.wifiprobe.mapper.api.StatisticsMapper;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.model.entity.OnlineValue;
import com.simpletech.wifiprobe.service.StatisticsService;
import com.simpletech.wifiprobe.util.AfReflecter;
import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Matchers.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * 数据库表t_shop的Mapper层测试类
 *
 * @author 树朾
 * @date 2015-10-30 15:12:46 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsControllerTester {


    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    StatisticsController controller;

    @Before
    public void setUp() {
        LoggingAspect.log = false;
    }

    @Test
    public void onlineProbe() throws Exception {
        StatisticsService service = AfReflecter.getMemberNoException(controller, "CGLIB$CALLBACK_0.advised.targetSource.target.service", StatisticsService.class);
        if (service == null) service = AfReflecter.getMemberNoException(controller, "service", StatisticsService.class);

        StatisticsDao dao = mock(StatisticsDao.class);
        StatisticsMapper mapper = mock(StatisticsMapper.class);

        AfReflecter.setMember(service, "h.advised.targetSource.target.dao", dao);
        AfReflecter.setMember(service, "h.advised.targetSource.target.mapper", mapper);
        AfReflecter.setMember(service, "dao", dao);
        AfReflecter.setMember(service, "mapper", mapper);

        when(mapper.onlineProbeAll(any(Date.class))).thenReturn(new ArrayList<OnlineValue>() {{
            add(new OnlineValue("onlineProbeAll", 2));
            add(new OnlineValue("001", 333));
        }});
        when(mapper.onlineProbeShopIds(any(Date.class), anyString())).thenReturn(new ArrayList<OnlineValue>() {{
            add(new OnlineValue("onlineProbeShopIds", 2));
            add(new OnlineValue("001", 222));
        }});
        when(mapper.onlineProbe(eq("1"), any(Date.class))).thenReturn(8);


        Object result = controller.onlineProbe("1", "");
        System.out.println(JacksonUtil.toJson(result));
        result = controller.onlineProbe("all", "[\"001\",\"002\"]");
        System.out.println(JacksonUtil.toJson(result));
        result = controller.onlineProbe("all", "");
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void visitFrequencyMap() throws Exception {
        Object result = controller.visitFrequencyMap("1", null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void visitDurationMap() throws Exception {
        Object result = controller.visitDurationMap("1", null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void visitPeriodMap() throws Exception {
        Object result = controller.visitPeriodMap("1", null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void visitDurationSpan() throws Exception {
        Object result = controller.visitDurationSpan("1", null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void visitDurationTrend() throws Exception {
        Object result = controller.visitDurationTrend("1", Period.day, null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }


    @Test
    public void visitSpan() throws Exception {
        Object result = controller.visitSpan("1", null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void deviceBrandRank() throws Exception {
        Object result = controller.deviceBrandRank("1", RankingType.vt, 10, 1, null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

}
