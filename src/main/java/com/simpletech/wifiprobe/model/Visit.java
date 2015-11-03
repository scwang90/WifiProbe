package com.simpletech.wifiprobe.model;

import com.simpletech.wifiprobe.annotations.dbmodel.Column;
import com.simpletech.wifiprobe.annotations.dbmodel.Id;
import com.simpletech.wifiprobe.annotations.dbmodel.Table;
import com.simpletech.wifiprobe.model.base.ModelBase;

/**
 * 数据库表t_visit
 * @author 树朾
 * @date 2015-11-03 17:09:44 中国标准时间
 */
@Table("t_visit")
public class Visit extends ModelBase{

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
	 * 设备Mac
	 */
	@Column("mac_device")
	private String macDevice;
	/**
	 * 进入时间
	 */
	@Column("time_entry")
	private java.util.Date timeEntry;
	/**
	 * 离开时间
	 */
	@Column("time_leave")
	private java.util.Date timeLeave;
	/**
	 * 驻店时间
	 */
	@Column("time_duration")
	private Integer timeDuration;
	/**
	 * 是否新用户
	 */
	@Column("is_new_user")
	private Boolean isNewUser;
	/**
	 * 日志条数
	 */
	@Column("count_logs")
	private Integer countLogs;
	/**
	 * 设备品牌
	 */
	@Column("end_brand")
	private String endBrand;
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

	public Visit() {
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
	
	public String getMacDevice(){
		return this.macDevice;
	}

	public void setMacDevice(String macDevice) {
		this.macDevice = macDevice;
	}
	
	public java.util.Date getTimeEntry(){
		return this.timeEntry;
	}

	public void setTimeEntry(java.util.Date timeEntry) {
		this.timeEntry = timeEntry;
	}
	
	public java.util.Date getTimeLeave(){
		return this.timeLeave;
	}

	public void setTimeLeave(java.util.Date timeLeave) {
		this.timeLeave = timeLeave;
	}
	
	public Integer getTimeDuration(){
		return this.timeDuration;
	}

	public void setTimeDuration(Integer timeDuration) {
		this.timeDuration = timeDuration;
	}
	
	public Boolean getIsNewUser(){
		return this.isNewUser;
	}

	public void setIsNewUser(Boolean isNewUser) {
		this.isNewUser = isNewUser;
	}
	
	public Integer getCountLogs(){
		return this.countLogs;
	}

	public void setCountLogs(Integer countLogs) {
		this.countLogs = countLogs;
	}
	
	public String getEndBrand(){
		return this.endBrand;
	}

	public void setEndBrand(String endBrand) {
		this.endBrand = endBrand;
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