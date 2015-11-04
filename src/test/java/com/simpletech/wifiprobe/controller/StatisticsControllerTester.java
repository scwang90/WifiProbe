package com.simpletech.wifiprobe.controller;

import com.simpletech.wifiprobe.aspect.LoggingAspect;
import com.simpletech.wifiprobe.model.constant.Period;
import com.simpletech.wifiprobe.service.StatisticsService;
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
public class StatisticsControllerTester {


	SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

	@Autowired
	StatisticsController controller;

	@Before
	public void setUp() {
		LoggingAspect.log = false;
	}

	@Test
	public void visitFrequencyMap() throws Exception{
		Object result = controller.visitFrequencyMap("1", null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitDurationMap() throws Exception{
		Object result = controller.visitDurationMap("1", null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitPeriodMap() throws Exception{
		Object result = controller.visitPeriodMap("1", null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitDurationSpan() throws Exception{
		Object result = controller.visitDurationSpan("1", null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitDurationTrend() throws Exception{
		Object result = controller.visitDurationTrend("1", Period.day, null, null, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
}
