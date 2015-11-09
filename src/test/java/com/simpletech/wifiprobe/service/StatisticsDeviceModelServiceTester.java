package com.simpletech.wifiprobe.service;

import com.simpletech.wifiprobe.aspect.LoggingAspect;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * 数据库表t_visit的Mapper层测试类
 * @author 树朾
 * @date 2015-10-30 15:12:46 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsDeviceModelServiceTester {


	SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

	@Autowired
	StatisticsDeviceModelService service;

	@Before
	public void setUp() {
		LoggingAspect.log = false;
	}

	@Test
	public void brand() throws Exception{
		Object result = service.brand("1", RankingType.uv,monthf.parse("2015-11-01"), monthf.parse("2015-11-30"),10,1);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

}
