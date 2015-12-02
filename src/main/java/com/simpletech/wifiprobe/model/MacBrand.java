package com.simpletech.wifiprobe.model;

import com.simpletech.wifiprobe.annotations.dbmodel.Id;
import com.simpletech.wifiprobe.annotations.dbmodel.Table;
import com.simpletech.wifiprobe.model.base.ModelBase;

/**
 * 数据库表mac_brand
 * @author 树朾
 * @date 2015-11-26 15:36:56 中国标准时间
 */
@Table("mac_brand")
public class MacBrand extends ModelBase{

	/**
	 * ID主键
	 */
	@Id
	private String mac;
	/**
	 * 店铺ID
	 */
	private String name;
	/**
	 * wifi ID
	 */
	private String remark;

	public MacBrand() {
	}
	
	public String getMac(){
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getName(){
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRemark(){
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "MacBrand{" +
				"mac='" + mac + '\'' +
				", name='" + name + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}