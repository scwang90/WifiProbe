package com.simpletech.wifiprobe.model.entity;


import com.simpletech.wifiprobe.model.Shop;

import java.util.Date;

/**
 * 店铺辅助实体
 * Created by Administrator on 2015/11/19.
 */
public class ShopEntity extends Shop{
    
    Shop shop;

    public ShopEntity(Shop shop) {
        this.shop = shop;
    }

    public void setCreateTime(Date createTime) {
        shop.setCreateTime(createTime);
    }

    public String getShopID() {
        return shop.getShopID();
    }

    @Override
    public Double getConfigProbeApiVisitDurationEnter() {
        Double value = shop.getConfigProbeApiVisitDurationEnter();
        value = (value==null||value.equals(0))?1:value;
        return value;
    }

    @Override
    public void setConfigProbeApiVisitDurationEnter(Double configProbeApiVisitDurationEnter) {
        super.setConfigProbeApiVisitDurationEnter(configProbeApiVisitDurationEnter);
    }

    @Override
    public Integer getConfigProbeVisitExpiredWifi() {
        Integer value = shop.getConfigProbeVisitExpiredWifi();
        value = (value==null||value.equals(0))?3:value;
        return value;
    }

    @Override
    public void setConfigProbeVisitExpiredWifi(Integer configProbeVisitExpiredWifi) {
        super.setConfigProbeVisitExpiredWifi(configProbeVisitExpiredWifi);
    }

    @Override
    public Integer getConfigProbeVisitSignal() {
        Integer value = shop.getConfigProbeVisitSignal();
        value = (value==null||value.equals(0))?-100:value;
        return value;
    }

    @Override
    public void setConfigProbeVisitSignal(Integer configProbeVisitSignal) {
        super.setConfigProbeVisitSignal(configProbeVisitSignal);
    }

    @Override
    public Integer getConfigProbeVisitSignalWifi() {
        Integer value = shop.getConfigProbeVisitSignalWifi();
        value = (value==null||value.equals(0))?-100:value;
        return value;
    }

    @Override
    public void setConfigProbeVisitSignalWifi(Integer configProbeVisitSignalWifi) {
        super.setConfigProbeVisitSignalWifi(configProbeVisitSignalWifi);
    }

    public void setShopID(String shopID) {
        shop.setShopID(shopID);
    }

    public String getShopName() {
        return shop.getShopName();
    }

    public void setShopName(String shopName) {
        shop.setShopName(shopName);
    }

    public Integer getConfigProbeVisitExpired() {
        Integer value = shop.getConfigProbeVisitExpired();
        value = (value==null||value.equals(0))?5:value;
        return value;
    }

    public void setConfigProbeVisitExpired(Integer configVisitExpired) {
        shop.setConfigProbeVisitExpired(configVisitExpired);
    }

    public Integer getConfigProbeUserExpired() {
        Integer value = shop.getConfigProbeUserExpired();
        value = (value==null||value.equals(0))?365:value;
        return value;
    }

    public void setConfigProbeUserExpired(Integer configUserExpired) {
        shop.setConfigProbeUserExpired(configUserExpired);
    }

    public String getConfigProbeApiVisitCounts() {
        return shop.getConfigProbeApiVisitCounts();
    }

    public void setConfigProbeApiVisitCounts(String configApiVisitCounts) {
        shop.setConfigProbeApiVisitCounts(configApiVisitCounts);
    }

    public String getConfigProbeApiVisitDuration() {
        return shop.getConfigProbeApiVisitDuration();
    }

    public void setConfigProbeApiVisitDuration(String configApiVisitDuration) {
        shop.setConfigProbeApiVisitDuration(configApiVisitDuration);
    }

    public Double getConfigProbeApiVisitDurationDeep() {
        Double value = shop.getConfigProbeApiVisitDurationDeep();
        value = (value==null||value.equals(0d))?40:value;
        return value;
    }

    public void setConfigProbeApiVisitDurationDeep(Double configApiVisitDurationDeep) {
        shop.setConfigProbeApiVisitDurationDeep(configApiVisitDurationDeep);
    }

    public Double getConfigProbeApiVisitDurationJump() {
        Double value = shop.getConfigProbeApiVisitDurationJump();
        value = (value==null||value.equals(0d))?5:value;
        return value;
    }

    public void setConfigProbeApiVisitDurationJump(Double configApiVisitDurationJump) {
        shop.setConfigProbeApiVisitDurationJump(configApiVisitDurationJump);
    }

    public String getConfigProbeApiVisitPeriod() {
        return shop.getConfigProbeApiVisitPeriod();
    }

    public void setConfigProbeApiVisitPeriod(String configApiVisitPeriod) {
        shop.setConfigProbeApiVisitPeriod(configApiVisitPeriod);
    }

    public String getConfigProbeApiLiveness() {
        return shop.getConfigProbeApiLiveness();
    }

    public void setConfigProbeApiLiveness(String configApiLiveness) {
        shop.setConfigProbeApiLiveness(configApiLiveness);
    }

    public Date getCreateTime() {
        return shop.getCreateTime();
    }
}
