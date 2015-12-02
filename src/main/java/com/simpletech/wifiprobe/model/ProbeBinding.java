package com.simpletech.wifiprobe.model;

import com.simpletech.wifiprobe.annotations.dbmodel.Id;
import com.simpletech.wifiprobe.annotations.dbmodel.Table;
import com.simpletech.wifiprobe.model.base.ModelBase;

/**
 * 数据库表probe_binding
 * @author 树朾
 * @date 2015-11-26 15:36:56 中国标准时间
 */
@Table("probe_binding")
public class ProbeBinding extends ModelBase{

	/**
	 * ID主键
	 */
	@Id
	private String probeID;
	/**
	 * 店铺ID
	 */
	private String shopID;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;

	public ProbeBinding() {
	}
	
	public String getProbeID(){
		return this.probeID;
	}

	public void setProbeID(String probeID) {
		this.probeID = probeID;
	}
	
	public String getShopID(){
		return this.shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
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