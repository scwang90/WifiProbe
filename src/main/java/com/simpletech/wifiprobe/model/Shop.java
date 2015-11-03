package com.simpletech.wifiprobe.model;

import com.simpletech.wifiprobe.annotations.dbmodel.Column;
import com.simpletech.wifiprobe.annotations.dbmodel.Id;
import com.simpletech.wifiprobe.annotations.dbmodel.Table;
import com.simpletech.wifiprobe.model.base.ModelBase;

/**
 * 数据库表t_shop
 * @author 树朾
 * @date 2015-11-02 17:16:40 中国标准时间
 */
@Table("t_shop")
public class Shop extends ModelBase{

	/**
	 * ID主键
	 */
	@Id
	private String id;
	/**
	 * 访问过期时间段（分钟）
	 */
	@Column("config_visit_expired")
	private Integer configVisitExpired;
	/**
	 * 用户过期时间段（天）
	 */
	@Column("config_user_expired")
	private Integer configUserExpired;
	/**
	 * 信号强度过滤（0-100）
	 */
	@Column("config_visit_signal")
	private Integer configVisitSignal;
	/**
	 * WIFI 访客过期时间
	 */
	@Column("config_visit_expired_wifi")
	private Integer configVisitExpiredWifi;
	/**
	 * WIFI 信号过滤
	 */
	@Column("config_visit_signal_wifi")
	private Integer configVisitSignalWifi;
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

	public Shop() {
	}
	
	public String getId(){
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getConfigVisitExpired(){
		return this.configVisitExpired;
	}

	public void setConfigVisitExpired(Integer configVisitExpired) {
		this.configVisitExpired = configVisitExpired;
	}
	
	public Integer getConfigUserExpired(){
		return this.configUserExpired;
	}

	public void setConfigUserExpired(Integer configUserExpired) {
		this.configUserExpired = configUserExpired;
	}
	
	public Integer getConfigVisitSignal(){
		return this.configVisitSignal;
	}

	public void setConfigVisitSignal(Integer configVisitSignal) {
		this.configVisitSignal = configVisitSignal;
	}
	
	public Integer getConfigVisitExpiredWifi(){
		return this.configVisitExpiredWifi;
	}

	public void setConfigVisitExpiredWifi(Integer configVisitExpiredWifi) {
		this.configVisitExpiredWifi = configVisitExpiredWifi;
	}
	
	public Integer getConfigVisitSignalWifi(){
		return this.configVisitSignalWifi;
	}

	public void setConfigVisitSignalWifi(Integer configVisitSignalWifi) {
		this.configVisitSignalWifi = configVisitSignalWifi;
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