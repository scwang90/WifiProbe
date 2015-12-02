package com.simpletech.wifiprobe.mapper_shop;

import com.simpletech.wifiprobe.dao.base.BaseDaoMybatisMYSQLImpl.MybatisMultiDao;
import com.simpletech.wifiprobe.model.Shop;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 数据库表shop的mapper接口
 * @author 树朾
 * @date 2015-11-24 18:16:03 中国标准时间
 */
public interface ShopMapper extends MybatisMultiDao<Shop>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变的行数
	 */
	@Insert("INSERT INTO shop ( shopID , shopName , deleteFlag , createTime , description , config_probe_visit_expired , config_probe_user_expired , config_probe_visit_signal , config_probe_visit_expired_wifi , config_probe_visit_signal_wifi , config_probe_api_visit_counts , config_probe_api_visit_duration , config_probe_api_visit_duration_deep , config_probe_api_visit_duration_jump , config_probe_api_visit_duration_enter , config_probe_api_visit_period , config_probe_api_liveness ) VALUES ( #{shopID} , #{shopName} , #{deleteFlag} , #{createTime} , #{description} , #{configProbeVisitExpired} , #{configProbeUserExpired} , #{configProbeVisitSignal} , #{configProbeVisitExpiredWifi} , #{configProbeVisitSignalWifi} , #{configProbeApiVisitCounts} , #{configProbeApiVisitDuration} , #{configProbeApiVisitDurationDeep} , #{configProbeApiVisitDurationJump} , #{configProbeApiVisitDurationEnter} , #{configProbeApiVisitPeriod} , #{configProbeApiLiveness} )")
	int insert(Shop model);
	/**
	 * 根据ID删除
	 * @param id 数据的主键ID
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM shop WHERE shopID=#{id}")
	int delete(@Param("id") Object id);
	/**
	 * 更新一条数据
	 * @param model 更新的数据
	 * @return 改变的行数
	 */
	@Update("UPDATE shop SET shopID=#{shopID} , shopName=#{shopName} , deleteFlag=#{deleteFlag} , createTime=#{createTime} , description=#{description} , config_probe_visit_expired=#{configProbeVisitExpired} , config_probe_user_expired=#{configProbeUserExpired} , config_probe_visit_signal=#{configProbeVisitSignal} , config_probe_visit_expired_wifi=#{configProbeVisitExpiredWifi} , config_probe_visit_signal_wifi=#{configProbeVisitSignalWifi} , config_probe_api_visit_counts=#{configProbeApiVisitCounts} , config_probe_api_visit_duration=#{configProbeApiVisitDuration} , config_probe_api_visit_duration_deep=#{configProbeApiVisitDurationDeep} , config_probe_api_visit_duration_jump=#{configProbeApiVisitDurationJump} , config_probe_api_visit_duration_enter=#{configProbeApiVisitDurationEnter} , config_probe_api_visit_period=#{configProbeApiVisitPeriod} , config_probe_api_liveness=#{configProbeApiLiveness} WHERE shopID=#{shopID} ")
	int update(Shop model);
	/**
	 * 统计全部出数据
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM shop")
	int countAll();
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return null 或者 主键等于id的数据
	 */
	@Select("SELECT shopID , shopName , deleteFlag , createTime , description , config_probe_visit_expired configProbeVisitExpired , config_probe_user_expired configProbeUserExpired , config_probe_visit_signal configProbeVisitSignal , config_probe_visit_expired_wifi configProbeVisitExpiredWifi , config_probe_visit_signal_wifi configProbeVisitSignalWifi , config_probe_api_visit_counts configProbeApiVisitCounts , config_probe_api_visit_duration configProbeApiVisitDuration , config_probe_api_visit_duration_deep configProbeApiVisitDurationDeep , config_probe_api_visit_duration_jump configProbeApiVisitDurationJump , config_probe_api_visit_duration_enter configProbeApiVisitDurationEnter , config_probe_api_visit_period configProbeApiVisitPeriod , config_probe_api_liveness configProbeApiLiveness FROM shop WHERE shopID=#{id}")
	Shop findById(@Param("id") Object id);
	/**
	 * 获取全部数据
	 * @return 全部数据列表
	 */
	@Select("SELECT shopID , shopName , deleteFlag , createTime , description , config_probe_visit_expired configProbeVisitExpired , config_probe_user_expired configProbeUserExpired , config_probe_visit_signal configProbeVisitSignal , config_probe_visit_expired_wifi configProbeVisitExpiredWifi , config_probe_visit_signal_wifi configProbeVisitSignalWifi , config_probe_api_visit_counts configProbeApiVisitCounts , config_probe_api_visit_duration configProbeApiVisitDuration , config_probe_api_visit_duration_deep configProbeApiVisitDurationDeep , config_probe_api_visit_duration_jump configProbeApiVisitDurationJump , config_probe_api_visit_duration_enter configProbeApiVisitDurationEnter , config_probe_api_visit_period configProbeApiVisitPeriod , config_probe_api_liveness configProbeApiLiveness FROM shop ${order}")
	List<Shop> findAll(@Param("order") String order);
	/**
	 * 分页查询数据
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 分页列表数据
	 */
	@Select("SELECT shopID , shopName , deleteFlag , createTime , description , config_probe_visit_expired configProbeVisitExpired , config_probe_user_expired configProbeUserExpired , config_probe_visit_signal configProbeVisitSignal , config_probe_visit_expired_wifi configProbeVisitExpiredWifi , config_probe_visit_signal_wifi configProbeVisitSignalWifi , config_probe_api_visit_counts configProbeApiVisitCounts , config_probe_api_visit_duration configProbeApiVisitDuration , config_probe_api_visit_duration_deep configProbeApiVisitDurationDeep , config_probe_api_visit_duration_jump configProbeApiVisitDurationJump , config_probe_api_visit_duration_enter configProbeApiVisitDurationEnter , config_probe_api_visit_period configProbeApiVisitPeriod , config_probe_api_liveness configProbeApiLiveness FROM shop ${order} LIMIT ${start},${limit}")
	List<Shop> findByPage(@Param("order") String order, @Param("limit") int limit, @Param("start") int start);
	/**
	 * 选择性删除
	 * @param where SQL条件语句
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM shop ${where}")
	int deleteWhere(@Param("where") String where);
	/**
	 * 根据属性值删除
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM shop WHERE ${propertyName}=#{value}")
	int deleteByPropertyName(@Param("propertyName") String propertyName, @Param("value") Object value);
	/**
	 * 选择性统计
	 * @param where SQL条件语句
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM shop ${where}")
	int countWhere(@Param("where") String where);
	/**
	 * 根据属性统计
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM WHERE ${propertyName}=#{value}")
	int countByPropertyName(@Param("propertyName") String propertyName, @Param("value") Object value);
	/**
	 * 选择性查询
	 * @param where SQL条件语句
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT shopID , shopName , deleteFlag , createTime , description , config_probe_visit_expired configProbeVisitExpired , config_probe_user_expired configProbeUserExpired , config_probe_visit_signal configProbeVisitSignal , config_probe_visit_expired_wifi configProbeVisitExpiredWifi , config_probe_visit_signal_wifi configProbeVisitSignalWifi , config_probe_api_visit_counts configProbeApiVisitCounts , config_probe_api_visit_duration configProbeApiVisitDuration , config_probe_api_visit_duration_deep configProbeApiVisitDurationDeep , config_probe_api_visit_duration_jump configProbeApiVisitDurationJump , config_probe_api_visit_duration_enter configProbeApiVisitDurationEnter , config_probe_api_visit_period configProbeApiVisitPeriod , config_probe_api_liveness configProbeApiLiveness FROM shop ${where} ${order}")
	List<Shop> findWhere(@Param("order") String order, @Param("where") String where);
	/**
	 * 选择性分页查询
	 * @param where SQL条件语句
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT shopID , shopName , deleteFlag , createTime , description , config_probe_visit_expired configProbeVisitExpired , config_probe_user_expired configProbeUserExpired , config_probe_visit_signal configProbeVisitSignal , config_probe_visit_expired_wifi configProbeVisitExpiredWifi , config_probe_visit_signal_wifi configProbeVisitSignalWifi , config_probe_api_visit_counts configProbeApiVisitCounts , config_probe_api_visit_duration configProbeApiVisitDuration , config_probe_api_visit_duration_deep configProbeApiVisitDurationDeep , config_probe_api_visit_duration_jump configProbeApiVisitDurationJump , config_probe_api_visit_duration_enter configProbeApiVisitDurationEnter , config_probe_api_visit_period configProbeApiVisitPeriod , config_probe_api_liveness configProbeApiLiveness FROM shop ${where} ${order} LIMIT ${start},${limit}")
	List<Shop> findWhereByPage(@Param("order") String order, @Param("where") String where, @Param("limit") int limit, @Param("start") int start);
	/**
	 * 根据属性查询
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 返回符合条件的数据列表
	 */
	@Select("SELECT shopID , shopName , deleteFlag , createTime , description , config_probe_visit_expired configProbeVisitExpired , config_probe_user_expired configProbeUserExpired , config_probe_visit_signal configProbeVisitSignal , config_probe_visit_expired_wifi configProbeVisitExpiredWifi , config_probe_visit_signal_wifi configProbeVisitSignalWifi , config_probe_api_visit_counts configProbeApiVisitCounts , config_probe_api_visit_duration configProbeApiVisitDuration , config_probe_api_visit_duration_deep configProbeApiVisitDurationDeep , config_probe_api_visit_duration_jump configProbeApiVisitDurationJump , config_probe_api_visit_duration_enter configProbeApiVisitDurationEnter , config_probe_api_visit_period configProbeApiVisitPeriod , config_probe_api_liveness configProbeApiLiveness FROM shop WHERE ${propertyName}=#{value} ${order}")
	List<Shop> findByPropertyName(@Param("order") String order, @Param("propertyName") String propertyName, @Param("value") Object value);
}