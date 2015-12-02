package com.simpletech.wifiprobe.mapper.api;

import com.simpletech.wifiprobe.aspect.LoggingAspect;
import com.simpletech.wifiprobe.mapper.api.TrackerMapper;
import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 数据库表t_shop的Mapper层测试类
 * @author 树朾
 * @date 2015-10-30 15:12:46 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class TrackerMapperTester {

	
	@Autowired
	TrackerMapper mapper;

	@Before
	public void setUp() {
		LoggingAspect.log = false;
	}

	@Test
	public void findLastLogByMacAndShop() throws Exception{
		Object result = mapper.findLastLogByMacAndShop("1", "42a5ef8070a4");
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}


}
