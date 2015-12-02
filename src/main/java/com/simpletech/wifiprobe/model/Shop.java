package com.simpletech.wifiprobe.model;

import com.simpletech.wifiprobe.annotations.dbmodel.Column;
import com.simpletech.wifiprobe.annotations.dbmodel.Id;
import com.simpletech.wifiprobe.annotations.dbmodel.Table;
import com.simpletech.wifiprobe.model.base.ModelBase;

/**
 * 数据库表shop
 * @author 树朾
 * @date 2015-11-26 15:36:56 中国标准时间
 */
@Table("shop")
public class Shop extends ModelBase{

	/**
	 * ID主键
	 */
	@Id
	private String shopID;
	/**
	 * 名称
	 */
	private String shopName;
	/**
	 * 删除标记
	 */
	private Byte deleteFlag;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 访问过期时间段（分钟）
	 */
	@Column("config_probe_visit_expired")
	private Integer configProbeVisitExpired;
	/**
	 * 用户过期时间段（天）
	 */
	@Column("config_probe_user_expired")
	private Integer configProbeUserExpired;
	/**
	 * 信号强度过滤（0 - 100）
	 */
	@Column("config_probe_visit_signal")
	private Integer configProbeVisitSignal;
	/**
	 * WIFI 访客过期时间
	 */
	@Column("config_probe_visit_expired_wifi")
	private Integer configProbeVisitExpiredWifi;
	/**
	 * WIFI 信号过滤
	 */
	@Column("config_probe_visit_signal_wifi")
	private Integer configProbeVisitSignalWifi;
	/**
	 * 到访次数统计规则（1, 2, 5）次数
	 */
	@Column("config_probe_api_visit_counts")
	private String configProbeApiVisitCounts;
	/**
	 * 到访时长统计规则（5, 30, 60, 120）分钟
	 */
	@Column("config_probe_api_visit_duration")
	private String configProbeApiVisitDuration;
	/**
	 * 到访时长达到深访的标准（分钟）
	 */
	@Column("config_probe_api_visit_duration_deep")
	private Double configProbeApiVisitDurationDeep;
	/**
	 * 到访时长达到跳出的标准（分钟）
	 */
	@Column("config_probe_api_visit_duration_jump")
	private Double configProbeApiVisitDurationJump;
	/**
	 * 到访时长达到入店的标准（分钟）
	 */
	@Column("config_probe_api_visit_duration_enter")
	private Double configProbeApiVisitDurationEnter;
	/**
	 * 到访周期统计规则（1, 2, 4, 7, 14）天
	 */
	@Column("config_probe_api_visit_period")
	private String configProbeApiVisitPeriod;
	/**
	 * 活跃度统计(1, 7, 15, 30天)
	 */
	@Column("config_probe_api_liveness")
	private String configProbeApiLiveness;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;

	public Shop() {
	}
	
	public String getShopID(){
		return this.shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
	}
	
	public String getShopName(){
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public Byte getDeleteFlag(){
		return this.deleteFlag;
	}

	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public String getDescription(){
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getConfigProbeVisitExpired(){
		return this.configProbeVisitExpired;
	}

	public void setConfigProbeVisitExpired(Integer configProbeVisitExpired) {
		this.configProbeVisitExpired = configProbeVisitExpired;
	}
	
	public Integer getConfigProbeUserExpired(){
		return this.configProbeUserExpired;
	}

	public void setConfigProbeUserExpired(Integer configProbeUserExpired) {
		this.configProbeUserExpired = configProbeUserExpired;
	}
	
	public Integer getConfigProbeVisitSignal(){
		return this.configProbeVisitSignal;
	}

	public void setConfigProbeVisitSignal(Integer configProbeVisitSignal) {
		this.configProbeVisitSignal = configProbeVisitSignal;
	}
	
	public Integer getConfigProbeVisitExpiredWifi(){
		return this.configProbeVisitExpiredWifi;
	}

	public void setConfigProbeVisitExpiredWifi(Integer configProbeVisitExpiredWifi) {
		this.configProbeVisitExpiredWifi = configProbeVisitExpiredWifi;
	}
	
	public Integer getConfigProbeVisitSignalWifi(){
		return this.configProbeVisitSignalWifi;
	}

	public void setConfigProbeVisitSignalWifi(Integer configProbeVisitSignalWifi) {
		this.configProbeVisitSignalWifi = configProbeVisitSignalWifi;
	}
	
	public String getConfigProbeApiVisitCounts(){
		return this.configProbeApiVisitCounts;
	}

	public void setConfigProbeApiVisitCounts(String configProbeApiVisitCounts) {
		this.configProbeApiVisitCounts = configProbeApiVisitCounts;
	}
	
	public String getConfigProbeApiVisitDuration(){
		return this.configProbeApiVisitDuration;
	}

	public void setConfigProbeApiVisitDuration(String configProbeApiVisitDuration) {
		this.configProbeApiVisitDuration = configProbeApiVisitDuration;
	}
	
	public Double getConfigProbeApiVisitDurationDeep(){
		return this.configProbeApiVisitDurationDeep;
	}

	public void setConfigProbeApiVisitDurationDeep(Double configProbeApiVisitDurationDeep) {
		this.configProbeApiVisitDurationDeep = configProbeApiVisitDurationDeep;
	}
	
	public Double getConfigProbeApiVisitDurationJump(){
		return this.configProbeApiVisitDurationJump;
	}

	public void setConfigProbeApiVisitDurationJump(Double configProbeApiVisitDurationJump) {
		this.configProbeApiVisitDurationJump = configProbeApiVisitDurationJump;
	}
	
	public Double getConfigProbeApiVisitDurationEnter(){
		return this.configProbeApiVisitDurationEnter;
	}

	public void setConfigProbeApiVisitDurationEnter(Double configProbeApiVisitDurationEnter) {
		this.configProbeApiVisitDurationEnter = configProbeApiVisitDurationEnter;
	}
	
	public String getConfigProbeApiVisitPeriod(){
		return this.configProbeApiVisitPeriod;
	}

	public void setConfigProbeApiVisitPeriod(String configProbeApiVisitPeriod) {
		this.configProbeApiVisitPeriod = configProbeApiVisitPeriod;
	}
	
	public String getConfigProbeApiLiveness(){
		return this.configProbeApiLiveness;
	}

	public void setConfigProbeApiLiveness(String configProbeApiLiveness) {
		this.configProbeApiLiveness = configProbeApiLiveness;
	}
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
}