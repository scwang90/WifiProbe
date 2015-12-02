package com.simpletech.wifiprobe.util;

import org.junit.Test;

/**
 * 时间缓存测试
 * Created by Administrator on 2015/11/17.
 */
public class LruTimeCacheTester {

    @Test
    public void lrutimecachetester() throws InterruptedException {
        LruTimeCache<String,String> cache = new LruTimeCache<>(1,10000);
        cache.put("name","scwang");
        while (null != cache.get("name")) {
            Thread.sleep(1000);
            System.out.println(cache.get("name"));
        }
        System.out.println("end");
    }

}
