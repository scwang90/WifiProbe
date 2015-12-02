package com.simpletech.wifiprobe.service;

import com.simpletech.wifiprobe.dao.TrackerDao;
import com.simpletech.wifiprobe.mapper.api.TrackerMapper;
import com.simpletech.wifiprobe.model.ProbeLog;
import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.model.Visit;
import com.simpletech.wifiprobe.util.AfReflecter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * 探针接收服务测试
 * Created by Administrator on 2015/11/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
//@ContextConfiguration({"classpath:spring-servlet.xml","classpath:spring-database-log.xml"})
public class TrackerServiceTest {

    @Autowired
    TrackerService service;

    private final String name = "scwang";

    @Test
    public void testAfReflecter() throws Exception {
        System.out.println(AfReflecter.getMember(new TrackerServiceTest(),"name"));
        System.out.println(AfReflecter.getMember(service,"h.advised.targetSource.target.dao"));
    }

    @Test
    public void testMaclogMock() throws Exception {
        TrackerDao dao = mock(TrackerDao.class);
        TrackerMapper mapper = mock(TrackerMapper.class);

        AfReflecter.setMember(service, "h.advised.targetSource.target.dao", dao);
        AfReflecter.setMember(service, "h.advised.targetSource.target.mapper", mapper);
        AfReflecter.setMember(service, "dao", dao);
        AfReflecter.setMember(service, "mapper", mapper);

        Shop shop = new Shop();
        shop.setShopID("1");
        when(dao.findShopByFiwiId("021122334455")).thenReturn(shop);
        when(mapper.insertVisit(any(Visit.class))).thenReturn(1);

        ProbeLog log = new ProbeLog();
        log.setIdwifi("021122334455");
        log.setMacDevice("54:ae:27:74:2a:7b");
        log.setSignalStrength(-30);
        log.setLocalTime(new Date());
        log.setUpdateTime(new Date());
        log.setCreateTime(new Date());
        log.fillNullID();
        service.maclog(log);
    }

    @Test
    public void testMaclog() throws Exception {
        ProbeLog log = new ProbeLog();
        log.setIdwifi("021122334455");
        log.setMacDevice("54:ae:27:74:2a:7b");
        log.setSignalStrength(-30);
        log.setLocalTime(new Date());
        log.setUpdateTime(new Date());
        log.setCreateTime(new Date());
        log.fillNullID();
        service.maclog(log);
    }
}