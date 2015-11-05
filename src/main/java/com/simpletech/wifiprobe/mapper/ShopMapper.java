package com.simpletech.wifiprobe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.simpletech.wifiprobe.model.Shop;
import com.simpletech.wifiprobe.dao.base.BaseDaoMybatisMYSQLImpl.MybatisMultiDao;


/**
 * 数据库表t_shop的mapper接口
 * @author 树朾
 * @date 2015-11-05 15:02:36 中国标准时间
 */
public interface ShopMapper extends MybatisMultiDao<Shop>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变的行数
	 */
	@Insert("INSERT INTO t_shop ( id , name , config_visit_expired , config_user_expired , config_visit_signal , config_visit_expired_wifi , config_visit_signal_wifi , config_api_visit_counts , config_api_visit_duration , config_api_visit_duration_deep , config_api_visit_duration_jump , config_api_visit_duration_enter , config_api_visit_period , config_api_visit_period_min_duration , config_api_liveness , create_time , update_time ) VALUES ( #{id} , #{name} , #{configVisitExpired} , #{configUserExpired} , #{configVisitSignal} , #{configVisitExpiredWifi} , #{configVisitSignalWifi} , #{configApiVisitCounts} , #{configApiVisitDuration} , #{configApiVisitDurationDeep} , #{configApiVisitDurationJump} , #{configApiVisitDurationEnter} , #{configApiVisitPeriod} , #{configApiVisitPeriodMinDuration} , #{configApiLiveness} , #{createTime} , #{updateTime} )")
	int insert(Shop model) throws Exception;
	/**
	 * 根据ID删除
	 * @param id 数据的主键ID
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_shop WHERE id=#{id}")
	int delete(@Param("id") Object id) throws Exception;
	/**
	 * 更新一条数据
	 * @param model 更新的数据
	 * @return 改变的行数
	 */
	@Update("UPDATE t_shop SET id=#{id} , name=#{name} , config_visit_expired=#{configVisitExpired} , config_user_expired=#{configUserExpired} , config_visit_signal=#{configVisitSignal} , config_visit_expired_wifi=#{configVisitExpiredWifi} , config_visit_signal_wifi=#{configVisitSignalWifi} , config_api_visit_counts=#{configApiVisitCounts} , config_api_visit_duration=#{configApiVisitDuration} , config_api_visit_duration_deep=#{configApiVisitDurationDeep} , config_api_visit_duration_jump=#{configApiVisitDurationJump} , config_api_visit_duration_enter=#{configApiVisitDurationEnter} , config_api_visit_period=#{configApiVisitPeriod} , config_api_visit_period_min_duration=#{configApiVisitPeriodMinDuration} , config_api_liveness=#{configApiLiveness} , create_time=#{createTime} , update_time=#{updateTime} WHERE id=#{id} ")
	int update(Shop model) throws Exception;
	/**
	 * 统计全部出数据
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_shop")
	int countAll() throws Exception;
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return null 或者 主键等于id的数据
	 */
	@Select("SELECT id , name , config_visit_expired configVisitExpired , config_user_expired configUserExpired , config_visit_signal configVisitSignal , config_visit_expired_wifi configVisitExpiredWifi , config_visit_signal_wifi configVisitSignalWifi , config_api_visit_counts configApiVisitCounts , config_api_visit_duration configApiVisitDuration , config_api_visit_duration_deep configApiVisitDurationDeep , config_api_visit_duration_jump configApiVisitDurationJump , config_api_visit_duration_enter configApiVisitDurationEnter , config_api_visit_period configApiVisitPeriod , config_api_visit_period_min_duration configApiVisitPeriodMinDuration , config_api_liveness configApiLiveness , create_time createTime , update_time updateTime FROM t_shop WHERE id=#{id}")
	Shop findById(@Param("id") Object id) throws Exception;
	/**
	 * 获取全部数据
	 * @return 全部数据列表
	 */
	@Select("SELECT id , name , config_visit_expired configVisitExpired , config_user_expired configUserExpired , config_visit_signal configVisitSignal , config_visit_expired_wifi configVisitExpiredWifi , config_visit_signal_wifi configVisitSignalWifi , config_api_visit_counts configApiVisitCounts , config_api_visit_duration configApiVisitDuration , config_api_visit_duration_deep configApiVisitDurationDeep , config_api_visit_duration_jump configApiVisitDurationJump , config_api_visit_duration_enter configApiVisitDurationEnter , config_api_visit_period configApiVisitPeriod , config_api_visit_period_min_duration configApiVisitPeriodMinDuration , config_api_liveness configApiLiveness , create_time createTime , update_time updateTime FROM t_shop ${order}")
	List<Shop> findAll(@Param("order") String order) throws Exception;
	/**
	 * 分页查询数据
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 分页列表数据
	 */
	@Select("SELECT id , name , config_visit_expired configVisitExpired , config_user_expired configUserExpired , config_visit_signal configVisitSignal , config_visit_expired_wifi configVisitExpiredWifi , config_visit_signal_wifi configVisitSignalWifi , config_api_visit_counts configApiVisitCounts , config_api_visit_duration configApiVisitDuration , config_api_visit_duration_deep configApiVisitDurationDeep , config_api_visit_duration_jump configApiVisitDurationJump , config_api_visit_duration_enter configApiVisitDurationEnter , config_api_visit_period configApiVisitPeriod , config_api_visit_period_min_duration configApiVisitPeriodMinDuration , config_api_liveness configApiLiveness , create_time createTime , update_time updateTime FROM t_shop ${order} LIMIT ${start},${limit}")
	List<Shop> findByPage(@Param("order") String order,@Param("limit") int limit,@Param("start") int start) throws Exception;
	/**
	 * 选择性删除
	 * @param where SQL条件语句
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_shop ${where}")
	int deleteWhere(@Param("where") String where) throws Exception;
	/**
	 * 根据属性值删除
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_shop WHERE ${propertyName}=#{value}")
	int deleteByPropertyName(@Param("propertyName") String propertyName,@Param("value") Object value) throws Exception;
	/**
	 * 选择性统计
	 * @param where SQL条件语句
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_shop ${where}")
	int countWhere(@Param("where") String where) throws Exception;
	/**
	 * 根据属性统计
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM WHERE ${propertyName}=#{value}")
	int countByPropertyName(@Param("propertyName") String propertyName,@Param("value") Object value) throws Exception;
	/**
	 * 选择性查询
	 * @param where SQL条件语句
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , name , config_visit_expired configVisitExpired , config_user_expired configUserExpired , config_visit_signal configVisitSignal , config_visit_expired_wifi configVisitExpiredWifi , config_visit_signal_wifi configVisitSignalWifi , config_api_visit_counts configApiVisitCounts , config_api_visit_duration configApiVisitDuration , config_api_visit_duration_deep configApiVisitDurationDeep , config_api_visit_duration_jump configApiVisitDurationJump , config_api_visit_duration_enter configApiVisitDurationEnter , config_api_visit_period configApiVisitPeriod , config_api_visit_period_min_duration configApiVisitPeriodMinDuration , config_api_liveness configApiLiveness , create_time createTime , update_time updateTime FROM t_shop ${where} ${order}")
	List<Shop> findWhere(@Param("order") String order,@Param("where") String where) throws Exception;
	/**
	 * 选择性分页查询
	 * @param where SQL条件语句
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , name , config_visit_expired configVisitExpired , config_user_expired configUserExpired , config_visit_signal configVisitSignal , config_visit_expired_wifi configVisitExpiredWifi , config_visit_signal_wifi configVisitSignalWifi , config_api_visit_counts configApiVisitCounts , config_api_visit_duration configApiVisitDuration , config_api_visit_duration_deep configApiVisitDurationDeep , config_api_visit_duration_jump configApiVisitDurationJump , config_api_visit_duration_enter configApiVisitDurationEnter , config_api_visit_period configApiVisitPeriod , config_api_visit_period_min_duration configApiVisitPeriodMinDuration , config_api_liveness configApiLiveness , create_time createTime , update_time updateTime FROM t_shop ${where} ${order} LIMIT ${start},${limit}")
	List<Shop> findWhereByPage(@Param("order") String order,@Param("where") String where,@Param("limit") int limit,@Param("start") int start) throws Exception;
	/**
	 * 根据属性查询
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 返回符合条件的数据列表
	 */
	@Select("SELECT id , name , config_visit_expired configVisitExpired , config_user_expired configUserExpired , config_visit_signal configVisitSignal , config_visit_expired_wifi configVisitExpiredWifi , config_visit_signal_wifi configVisitSignalWifi , config_api_visit_counts configApiVisitCounts , config_api_visit_duration configApiVisitDuration , config_api_visit_duration_deep configApiVisitDurationDeep , config_api_visit_duration_jump configApiVisitDurationJump , config_api_visit_duration_enter configApiVisitDurationEnter , config_api_visit_period configApiVisitPeriod , config_api_visit_period_min_duration configApiVisitPeriodMinDuration , config_api_liveness configApiLiveness , create_time createTime , update_time updateTime FROM t_shop WHERE ${propertyName}=#{value} ${order}")
	List<Shop> findByPropertyName(@Param("order") String order,@Param("propertyName") String propertyName,@Param("value") Object value) throws Exception;
}