package com.simpletech.wifiprobe.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void array() throws JsonProcessingException {
        List<String> list = Arrays.asList("1", "2", "3");
        System.out.println(JacksonUtil.toJson(list).replace('"','\'').replaceAll("\\[|\\]",""));
        System.out.println(JacksonUtil.toJson(list));
        System.out.println(JacksonUtil.toJson(list).matches("^\\[\".+\"\\]$"));
    }

}
