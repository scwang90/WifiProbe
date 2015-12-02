package com.simpletech.wifiprobe.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simpletech.wifiprobe.model.VisitWifi;
import com.simpletech.wifiprobe.util.JacksonUtil;

/**
 * 数据库表t_visit_wifi的Service层测试类
 * @author 树朾
 * @date 2015-11-24 18:16:03 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class VisitWifiServiceTester {

	@Autowired
	VisitWifiService service;
	
	@Test
	public void insert() throws Exception{
		VisitWifi model = new VisitWifi();
		Object result = service.insert(model);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void update() throws Exception {
		VisitWifi model = new VisitWifi();
		Object result = service.update(model);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void delete() throws Exception {
		Object result = service.delete("1");
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void countAll() throws Exception {
		Object result = service.countAll();
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void findByPage() throws Exception {
		Object result = service.findByPage(5,0);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

}
