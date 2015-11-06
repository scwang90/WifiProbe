package com.simpletech.wifiprobe.service.impl;

import com.simpletech.wifiprobe.dao.StatisticsDeviceModelDao;
import com.simpletech.wifiprobe.mac.MacBrand;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.model.entity.BrandValue;
import com.simpletech.wifiprobe.page.Page;
import com.simpletech.wifiprobe.service.StatisticsDeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 统计API Service 实现
 * Created by 树朾 on 2015/9/25.
 */
@Service
public class StatisticsDeviceModelServiceImpl implements StatisticsDeviceModelService {

    @Autowired
    StatisticsDeviceModelDao dao;

    @Override
    public List<BrandValue> brand(String idshop,RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        List<BrandValue> list = dao.brand(idshop, rankingtype, start, end);
        List<BrandValue> list1=new ArrayList<>();
        BrandValue count = dao.countBrand(idshop, start, end);
        for (BrandValue value : list) {
            String str= MacBrand.parser(value.getName()).getCompany();
            String[] company=str.split("\\s");
            String name=company[0];
            value.setName(name);
            value.setRuv(1f * value.getUv() / count.getUv());
            value.setRvt(1f * value.getVt() / count.getVt());
            value.setRpv(1f * value.getPv() / count.getPv());
            list1.add(value);
        }
        //排重
        for (int i = 0; i < list1.size(); i++) {
            //逐个比对
            for (int j = i+1; j < list1.size(); j++) {
                //判断是否相等
                //相等移除对象
                if(list1.get(i).getName().equals(list1.get(j).getName())){
                    list1.get(i).setUv(list1.get(i).getUv() + list1.get(j).getUv());
                    list1.get(i).setVt(list1.get(i).getVt() + list1.get(j).getVt());
                    list1.get(i).setPv(list1.get(i).getPv() + list1.get(j).getPv());
                    list1.get(i).setRuv(list1.get(i).getRuv() + list1.get(j).getRuv());
                    list1.get(i).setRvt(list1.get(i).getRvt() + list1.get(j).getRvt());
                    list1.get(i).setRpv(list1.get(i).getRpv() + list1.get(j).getRpv());
                    list1.remove(j);
                    j--;
                }
            }
        }
        //对排重后的list进行按排序类型重新进行排序
        switch (rankingtype){
            case uv:
                Collections.sort(list1, new Comparator<BrandValue>() {
                    @Override
                    public int compare(BrandValue o1, BrandValue o2) {
                        return o2.getUv()-o1.getUv();//o2-o1为从大到小排序，o1-o2为从小到大排序

                    }
                });
                break;
            case vt:
                Collections.sort(list1, new Comparator<BrandValue>() {
                    @Override
                    public int compare(BrandValue o1, BrandValue o2) {
                        return o2.getVt()-o1.getVt();//o2-o1为从大到小排序，o1-o2为从小到大排序
                    }
                });
                break;
            case pv:
                Collections.sort(list1, new Comparator<BrandValue>() {
                    @Override
                    public int compare(BrandValue o1, BrandValue o2) {
                        return o2.getPv()-o1.getPv();//o2-o1为从大到小排序，o1-o2为从小到大排序
                    }
                });
                break;
        }

        //分页显示
        Page page=new Page();
        return page.paging(list1, limit, skip);
    }

}
