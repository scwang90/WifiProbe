package com.simpletech.wifiprobe.mapper_shop;

import com.simpletech.wifiprobe.aspect.LoggingAspect;
import com.simpletech.wifiprobe.model.ProbeBinding;
import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 数据库表probe_binding的Mapper层测试类
 * @author 树朾
 * @date 2015-11-24 18:16:03 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class ProbeBindingMapperTester {

	
	@Autowired
	ProbeBindingMapper mapper;

	@Before
	public void setUp() {
		LoggingAspect.log = false;
	}

	@Test
	public void insert() throws Exception{
		ProbeBinding model = new ProbeBinding();
		Object result = mapper.insert(model);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void update() throws Exception {
		ProbeBinding model = new ProbeBinding();
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
