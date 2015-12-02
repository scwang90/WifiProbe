package com.simpletech.wifiprobe.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simpletech.wifiprobe.aspect.LoggingAspect;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.util.JacksonUtil;

/**
 * 数据库表t_visit的Mapper层测试类
 * @author 树朾
 * @date 2015-11-24 18:16:03 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class VisitMapperTester {

	
	@Autowired
	VisitMapper mapper;

	@Before
	public void setUp() {
		LoggingAspect.log = false;
	}

	@Test
	public void insert() throws Exception{
		Visit model = new Visit();
		Object result = mapper.insert(model);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void update() throws Exception {
		Visit model = new Visit();
		Object result = mapper.update(model);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void delete() throws Exception {
		Object result = mapper.delete("1");
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void countAll() throws Exception {
		Object result = mapper.countAll();
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void findAll() throws Exception {
		Object result = mapper.findAll("");
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

}
