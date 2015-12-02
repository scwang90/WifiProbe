package com.simpletech.wifiprobe.mapper.api;

import com.simpletech.wifiprobe.aspect.LoggingAspect;
import com.simpletech.wifiprobe.mapper.api.StatisticsMapper;
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
public class StatisticsMapperTester {


	SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

	@Autowired
	StatisticsMapper mapper;

	@Before
	public void setUp() {
		LoggingAspect.log = false;
	}

	@Test
	public void visitFrequencyMap() throws Exception{
		Object result = mapper.visitFrequencyMap("1", 1, 1, 1, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitPeriodMap() throws Exception{
		Object result = mapper.visitPeriodMap("1", 1, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitTrendHour() throws Exception{
		Object result = mapper.visitTrendHour("1", 1, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void customerTypeSpan() throws Exception{
		Object result = mapper.userTypeSpan("7", 60*10, monthf.parse("2015-11-30"), monthf.parse("2015-12-1"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void visitSpan() throws Exception{
		Object result = mapper.visitSpan("1", 1, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void deviceBrand() throws Exception{
		Object result = mapper.deviceBrand("1", 60, "pv", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"),10,1);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}


}
