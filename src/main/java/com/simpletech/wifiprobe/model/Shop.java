package com.simpletech.wifiprobe.model;

import com.simpletech.wifiprobe.annotations.dbmodel.Column;
import com.simpletech.wifiprobe.annotations.dbmodel.Id;
import com.simpletech.wifiprobe.annotations.dbmodel.Table;
import com.simpletech.wifiprobe.model.base.ModelBase;

/**
 * 数据库表t_shop
 * @author 树朾
 * @date 2015-11-04 17:29:11 中国标准时间
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
	 * 到访次数统计规则（1,2,5）次数
	 */
	@Column("config_api_visit_counts")
	private String configApiVisitCounts;
	/**
	 * 到访时长统计规则（5,30,60,120）分钟
	 */
	@Column("config_api_visit_duration")
	private String configApiVisitDuration;
	/**
	 * 到访时长达到深访的标准（分钟）
	 */
	@Column("config_api_visit_duration_deep")
	private Integer configApiVisitDurationDeep;
	/**
	 * 到访时长达到跳出的标准（分钟）
	 */
	@Column("config_api_visit_duration_jump")
	private Integer configApiVisitDurationJump;
	/**
	 * 到访周期统计规则（1,2,4,7,14）天
	 */
	@Column("config_api_visit_period")
	private String configApiVisitPeriod;
	/**
	 * 到访周期统计 最小停留时间（过滤用，分钟）
	 */
	@Column("config_api_visit_period_min_duration")
	private Integer configApiVisitPeriodMinDuration;
	/**
	 * 活跃度统计(1,7,15,30,60天)
	 */
	@Column("config_api_liveness")
	private String configApiLiveness;
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
	
	public String getConfigApiVisitCounts(){
		return this.configApiVisitCounts;
	}

	public void setConfigApiVisitCounts(String configApiVisitCounts) {
		this.configApiVisitCounts = configApiVisitCounts;
	}
	
	public String getConfigApiVisitDuration(){
		return this.configApiVisitDuration;
	}

	public void setConfigApiVisitDuration(String configApiVisitDuration) {
		this.configApiVisitDuration = configApiVisitDuration;
	}
	
	public Integer getConfigApiVisitDurationDeep(){
		return this.configApiVisitDurationDeep;
	}

	public void setConfigApiVisitDurationDeep(Integer configApiVisitDurationDeep) {
		this.configApiVisitDurationDeep = configApiVisitDurationDeep;
	}
	
	public Integer getConfigApiVisitDurationJump(){
		return this.configApiVisitDurationJump;
	}

	public void setConfigApiVisitDurationJump(Integer configApiVisitDurationJump) {
		this.configApiVisitDurationJump = configApiVisitDurationJump;
	}
	
	public String getConfigApiVisitPeriod(){
		return this.configApiVisitPeriod;
	}

	public void setConfigApiVisitPeriod(String configApiVisitPeriod) {
		this.configApiVisitPeriod = configApiVisitPeriod;
	}
	
	public Integer getConfigApiVisitPeriodMinDuration(){
		return this.configApiVisitPeriodMinDuration;
	}

	public void setConfigApiVisitPeriodMinDuration(Integer configApiVisitPeriodMinDuration) {
		this.configApiVisitPeriodMinDuration = configApiVisitPeriodMinDuration;
	}
	
	public String getConfigApiLiveness(){
		return this.configApiLiveness;
	}

	public void setConfigApiLiveness(String configApiLiveness) {
		this.configApiLiveness = configApiLiveness;
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