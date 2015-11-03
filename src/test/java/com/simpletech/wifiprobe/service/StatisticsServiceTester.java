package com.simpletech.wifiprobe.service;

import com.simpletech.wifiprobe.aspect.LoggingAspect;
import com.simpletech.wifiprobe.mapper.StatisticsMapper;
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
	public void visitfrequency() throws Exception{
		Object result = service.visitfrequency("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitduration() throws Exception{
		Object result = service.visitduration("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
}