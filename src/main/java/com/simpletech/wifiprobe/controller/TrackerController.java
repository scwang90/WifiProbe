package com.simpletech.wifiprobe.controller;

import com.simpletech.wifiprobe.model.MacLog;
import com.simpletech.wifiprobe.service.TrackerService;
import com.simpletech.wifiprobe.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
@RestController
@RequestMapping("api/tracker")
public class TrackerController {

    @Autowired
    TrackerService service;

    static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 接收探针日志数据
     *
     * @param body JSON 数据 {data:[{time:"2015-08-13 14:53:23",rssi:-78,mac:"14:f6:5a:90:a6:63",id:"B068B6FFB1C4"},...]}
     */
    @RequestMapping("mac/log")
    public Object maclog(@RequestBody HashMap body) throws Exception {
        Object id = body.get("id");
        Object data = body.get("data");
        if (data instanceof List) {
            List<HashMap<String, Object>> list = (List) data;
            for (HashMap<String, Object> item : list) {
                MacLog log = new MacLog();
                if (id == null) {
                    log.setIdwifi(item.get("id").toString().trim());
                } else {
                    log.setIdwifi(id.toString());
                }
                log.setMacDevice(item.get("mac").toString().trim());
                log.setSignalStrength(Integer.parseInt(item.get("rssi").toString().trim()));
                log.setLocalTime(format.parse(item.get("time").toString().trim()));
                log.setUpdateTime(new Date());
                log.setCreateTime(new Date());
                log.fillNullID();
                service.maclog(log);
            }
        }
        return true;
    }

    /**
     * 接收探针日志数据
     *
     * @param request    JSON 数据 {data:[{time:"2015-08-13 14:53:23",rssi:-78,mac:"14:f6:5a:90:a6:63",id:"B068B6FFB1C4"},...]}
     */
    @RequestMapping("mac/log/compat")
    public Object maclog_compat(HttpServletRequest request) throws Exception {
        String body = "", tmp;
        BufferedReader reader = request.getReader();
        while (null != (tmp = reader.readLine())) {
            body = body + tmp;
        }
        if (!body.startsWith("{") && body.indexOf("{") > 0) {
            body = body.substring(body.indexOf("{"));
        }
        if (body.indexOf("[]") > 0) {
            return false;
        }
        return maclog(JacksonUtil.toObject(body, LinkedHashMap.class));
    }
}
