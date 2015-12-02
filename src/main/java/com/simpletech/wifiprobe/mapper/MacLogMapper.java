package com.simpletech.wifiprobe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.simpletech.wifiprobe.model.MacLog;
import com.simpletech.wifiprobe.dao.base.BaseDaoMybatisMYSQLImpl.MybatisMultiDao;


/**
 * 数据库表t_mac_log的mapper接口
 * @author 树朾
 * @date 2015-11-24 18:16:03 中国标准时间
 */
public interface MacLogMapper extends MybatisMultiDao<MacLog>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变的行数
	 */
	@Insert("INSERT INTO t_mac_log ( id , idshop , idwifi , idvisit , idvisitwifi , signal_strength , mac_device , mac_wifi , ssid , local_time , create_time , update_time ) VALUES ( #{id} , #{idshop} , #{idwifi} , #{idvisit} , #{idvisitwifi} , #{signalStrength} , #{macDevice} , #{macWifi} , #{ssid} , #{localTime} , #{createTime} , #{updateTime} )")
	int insert(MacLog model);
	/**
	 * 根据ID删除
	 * @param id 数据的主键ID
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_mac_log WHERE id=#{id}")
	int delete(@Param("id") Object id);
	/**
	 * 更新一条数据
	 * @param model 更新的数据
	 * @return 改变的行数
	 */
	@Update("UPDATE t_mac_log SET id=#{id} , idshop=#{idshop} , idwifi=#{idwifi} , idvisit=#{idvisit} , idvisitwifi=#{idvisitwifi} , signal_strength=#{signalStrength} , mac_device=#{macDevice} , mac_wifi=#{macWifi} , ssid=#{ssid} , local_time=#{localTime} , create_time=#{createTime} , update_time=#{updateTime} WHERE id=#{id} ")
	int update(MacLog model);
	/**
	 * 统计全部出数据
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_mac_log")
	int countAll();
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return null 或者 主键等于id的数据
	 */
	@Select("SELECT id , idshop , idwifi , idvisit , idvisitwifi , signal_strength signalStrength , mac_device macDevice , mac_wifi macWifi , ssid , local_time localTime , create_time createTime , update_time updateTime FROM t_mac_log WHERE id=#{id}")
	MacLog findById(@Param("id") Object id);
	/**
	 * 获取全部数据
	 * @return 全部数据列表
	 */
	@Select("SELECT id , idshop , idwifi , idvisit , idvisitwifi , signal_strength signalStrength , mac_device macDevice , mac_wifi macWifi , ssid , local_time localTime , create_time createTime , update_time updateTime FROM t_mac_log ${order}")
	List<MacLog> findAll(@Param("order") String order);
	/**
	 * 分页查询数据
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 分页列表数据
	 */
	@Select("SELECT id , idshop , idwifi , idvisit , idvisitwifi , signal_strength signalStrength , mac_device macDevice , mac_wifi macWifi , ssid , local_time localTime , create_time createTime , update_time updateTime FROM t_mac_log ${order} LIMIT ${start},${limit}")
	List<MacLog> findByPage(@Param("order") String order,@Param("limit") int limit,@Param("start") int start);
	/**
	 * 选择性删除
	 * @param where SQL条件语句
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_mac_log ${where}")
	int deleteWhere(@Param("where") String where);
	/**
	 * 根据属性值删除
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_mac_log WHERE ${propertyName}=#{value}")
	int deleteByPropertyName(@Param("propertyName") String propertyName,@Param("value") Object value);
	/**
	 * 选择性统计
	 * @param where SQL条件语句
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_mac_log ${where}")
	int countWhere(@Param("where") String where);
	/**
	 * 根据属性统计
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM WHERE ${propertyName}=#{value}")
	int countByPropertyName(@Param("propertyName") String propertyName,@Param("value") Object value);
	/**
	 * 选择性查询
	 * @param where SQL条件语句
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , idshop , idwifi , idvisit , idvisitwifi , signal_strength signalStrength , mac_device macDevice , mac_wifi macWifi , ssid , local_time localTime , create_time createTime , update_time updateTime FROM t_mac_log ${where} ${order}")
	List<MacLog> findWhere(@Param("order") String order,@Param("where") String where);
	/**
	 * 选择性分页查询
	 * @param where SQL条件语句
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , idshop , idwifi , idvisit , idvisitwifi , signal_strength signalStrength , mac_device macDevice , mac_wifi macWifi , ssid , local_time localTime , create_time createTime , update_time updateTime FROM t_mac_log ${where} ${order} LIMIT ${start},${limit}")
	List<MacLog> findWhereByPage(@Param("order") String order,@Param("where") String where,@Param("limit") int limit,@Param("start") int start);
	/**
	 * 根据属性查询
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 返回符合条件的数据列表
	 */
	@Select("SELECT id , idshop , idwifi , idvisit , idvisitwifi , signal_strength signalStrength , mac_device macDevice , mac_wifi macWifi , ssid , local_time localTime , create_time createTime , update_time updateTime FROM t_mac_log WHERE ${propertyName}=#{value} ${order}")
	List<MacLog> findByPropertyName(@Param("order") String order,@Param("propertyName") String propertyName,@Param("value") Object value);
}