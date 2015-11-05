package com.simpletech.wifiprobe.service;

import com.simpletech.wifiprobe.aspect.LoggingAspect;
import com.simpletech.wifiprobe.mapper.StatisticsMapper;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * 数据库表t_shop的Mapper层测试类
 * @author 树朾
 * @date 2015-10-30 15:12:46 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsServiceTester {


	SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

	@Autowired
	StatisticsService service;

	@Before
	public void setUp() {
		LoggingAspect.log = false;
	}

	@Test
	public void visitDurationSpan() throws Exception{
		Object result = service.visitDurationSpan("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitDurationMap() throws Exception{
		Object result = service.visitDurationMap("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitDurationTrend() throws Exception{
		Object result = service.visitDurationTrend("1", Period.day, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitFrequencyMap() throws Exception{
		Object result = service.visitFrequencyMap("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitPeriodMap() throws Exception{
		Object result = service.visitPeriodMap("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitEntryTrend() throws Exception{
		Object result = service.visitEntryTrend("1", Period.day, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
}
