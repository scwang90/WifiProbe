package com.simpletech.wifiprobe.model;

import com.simpletech.wifiprobe.annotations.dbmodel.Column;
import com.simpletech.wifiprobe.annotations.dbmodel.Id;
import com.simpletech.wifiprobe.annotations.dbmodel.Table;
import com.simpletech.wifiprobe.model.base.ModelBase;

/**
 * 数据库表t_mac_log
 * @author 树朾
 * @date 2015-11-03 17:09:44 中国标准时间
 */
@Table("t_mac_log")
public class MacLog extends ModelBase{

	/**
	 * ID主键
	 */
	@Id
	private String id;
	/**
	 * 店铺ID
	 */
	private String idshop;
	/**
	 * wifi ID
	 */
	private String idwifi;
	/**
	 * 访问ID
	 */
	private String idvisit;
	/**
	 * WIFI访问ID
	 */
	private String idvisitwifi;
	/**
	 * WiFi信号强弱
	 */
	@Column("signal_strength")
	private Integer signalStrength;
	/**
	 * 手机Mac
	 */
	@Column("mac_device")
	private String macDevice;
	/**
	 * wifi mac地址
	 */
	@Column("mac_wifi")
	private String macWifi;
	/**
	 * wifi的SSID号
	 */
	private String ssid;
	/**
	 * 本地时间
	 */
	@Column("local_time")
	private java.util.Date localTime;
	/**
	 * 服务器时间
	 */
	@Column("create_time")
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	@Column("update_time")
	private java.util.Date updateTime;

	public MacLog() {
	}
	
	public String getId(){
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdshop(){
		return this.idshop;
	}

	public void setIdshop(String idshop) {
		this.idshop = idshop;
	}
	
	public String getIdwifi(){
		return this.idwifi;
	}

	public void setIdwifi(String idwifi) {
		this.idwifi = idwifi;
	}
	
	public String getIdvisit(){
		return this.idvisit;
	}

	public void setIdvisit(String idvisit) {
		this.idvisit = idvisit;
	}
	
	public String getIdvisitwifi(){
		return this.idvisitwifi;
	}

	public void setIdvisitwifi(String idvisitwifi) {
		this.idvisitwifi = idvisitwifi;
	}
	
	public Integer getSignalStrength(){
		return this.signalStrength;
	}

	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}
	
	public String getMacDevice(){
		return this.macDevice;
	}

	public void setMacDevice(String macDevice) {
		this.macDevice = macDevice;
	}
	
	public String getMacWifi(){
		return this.macWifi;
	}

	public void setMacWifi(String macWifi) {
		this.macWifi = macWifi;
	}
	
	public String getSsid(){
		return this.ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	
	public java.util.Date getLocalTime(){
		return this.localTime;
	}

	public void setLocalTime(java.util.Date localTime) {
		this.localTime = localTime;
	}
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
}