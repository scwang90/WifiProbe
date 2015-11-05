package com.simpletech.wifiprobe.model;

import com.simpletech.wifiprobe.annotations.dbmodel.Column;
import com.simpletech.wifiprobe.annotations.dbmodel.Id;
import com.simpletech.wifiprobe.annotations.dbmodel.Table;
import com.simpletech.wifiprobe.model.base.ModelBase;

/**
 * 数据库表t_wifi
 * @author 树朾
 * @date 2015-11-05 15:02:36 中国标准时间
 */
@Table("t_wifi")
public class Wifi extends ModelBase{

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
	 * 服务器时间
	 */
	@Column("create_time")
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	@Column("update_time")
	private java.util.Date updateTime;

	public Wifi() {
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