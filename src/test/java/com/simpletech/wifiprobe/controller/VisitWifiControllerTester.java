package com.simpletech.wifiprobe.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simpletech.wifiprobe.model.VisitWifi;
import com.simpletech.wifiprobe.util.JacksonUtil;

/**
 * 数据库表t_visit_wifi 的Controller层测试类
 * @author 树朾
 * @date 2015-11-02 17:16:40 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class VisitWifiControllerTester {

	@Autowired
	VisitWifiController controller;
	
	@Test
	public void add() throws Exception{
		VisitWifi model = new VisitWifi();
		Object result = controller.add(model);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void update() throws Exception {
		VisitWifi model = new VisitWifi();
		Object result = controller.update(model);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void delete() throws Exception {
		Object result = controller.delete("1");
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void count() throws Exception {
		Object result = controller.count();
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void get() throws Exception {
		Object result = controller.get("1");
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void listByPage() throws Exception {
		Object result = controller.listByPage(6, 0);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

}