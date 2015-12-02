package com.simpletech.wifiprobe.service;

import com.simpletech.wifiprobe.model.MacLog;
import com.simpletech.wifiprobe.model.ProbeLog;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
public interface TrackerService {

    /**
     * 接收探针日志数据
     * @param log 日志数据
     */
    void maclog(ProbeLog log) throws Exception;
}
