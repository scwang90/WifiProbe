package com.simpletech.wifiprobe.dao.impl;

import com.simpletech.wifiprobe.dao.StatisticsDeviceModelDao;
import com.simpletech.wifiprobe.mac.MacBrand;
import com.simpletech.wifiprobe.mapper.StatisticsDeviceModelMapper;
import com.simpletech.wifiprobe.model.constant.RankingType;
import com.simpletech.wifiprobe.model.entity.BrandValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据库表t_visit的Dao实现
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class StatisticsDeviceModelDaoImpl implements StatisticsDeviceModelDao {

    @Autowired
    StatisticsDeviceModelMapper mapper;

    @Override
    public List<BrandValue> brand(String idshop, RankingType rankingtype, Date start, Date end, int limit, int skip) throws Exception {
        List<BrandValue> list = mapper.brand(idshop, rankingtype.name(), start, end, limit, skip);
        List<BrandValue> list1=new ArrayList<>();
        BrandValue count = mapper.coutBrand(idshop, start, end);
        for (BrandValue value : list) {
            String str=MacBrand.parser(value.getName()).getCompany();
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
    return list1;
    }

}

