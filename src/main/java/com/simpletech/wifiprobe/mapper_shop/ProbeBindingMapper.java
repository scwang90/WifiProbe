package com.simpletech.wifiprobe.mapper_shop;

import com.simpletech.wifiprobe.dao.base.BaseDaoMybatisMYSQLImpl.MybatisMultiDao;
import com.simpletech.wifiprobe.model.ProbeBinding;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 数据库表probe_binding的mapper接口
 * @author 树朾
 * @date 2015-11-24 18:16:03 中国标准时间
 */
public interface ProbeBindingMapper extends MybatisMultiDao<ProbeBinding>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变的行数
	 */
	@Insert("INSERT INTO probe_binding ( probeID , shopID , createTime , updateTime ) VALUES ( #{probeID} , #{shopID} , #{createTime} , #{updateTime} )")
	int insert(ProbeBinding model);
	/**
	 * 根据ID删除
	 * @param id 数据的主键ID
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM probe_binding WHERE probeID=#{id}")
	int delete(@Param("id") Object id);
	/**
	 * 更新一条数据
	 * @param model 更新的数据
	 * @return 改变的行数
	 */
	@Update("UPDATE probe_binding SET probeID=#{probeID} , shopID=#{shopID} , createTime=#{createTime} , updateTime=#{updateTime} WHERE probeID=#{probeID} ")
	int update(ProbeBinding model);
	/**
	 * 统计全部出数据
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM probe_binding")
	int countAll();
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return null 或者 主键等于id的数据
	 */
	@Select("SELECT probeID , shopID , createTime , updateTime FROM probe_binding WHERE probeID=#{id}")
	ProbeBinding findById(@Param("id") Object id);
	/**
	 * 获取全部数据
	 * @return 全部数据列表
	 */
	@Select("SELECT probeID , shopID , createTime , updateTime FROM probe_binding ${order}")
	List<ProbeBinding> findAll(@Param("order") String order);
	/**
	 * 分页查询数据
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 分页列表数据
	 */
	@Select("SELECT probeID , shopID , createTime , updateTime FROM probe_binding ${order} LIMIT ${start},${limit}")
	List<ProbeBinding> findByPage(@Param("order") String order, @Param("limit") int limit, @Param("start") int start);
	/**
	 * 选择性删除
	 * @param where SQL条件语句
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM probe_binding ${where}")
	int deleteWhere(@Param("where") String where);
	/**
	 * 根据属性值删除
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM probe_binding WHERE ${propertyName}=#{value}")
	int deleteByPropertyName(@Param("propertyName") String propertyName, @Param("value") Object value);
	/**
	 * 选择性统计
	 * @param where SQL条件语句
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM probe_binding ${where}")
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
	@Select("SELECT probeID , shopID , createTime , updateTime FROM probe_binding ${where} ${order}")
	List<ProbeBinding> findWhere(@Param("order") String order, @Param("where") String where);
	/**
	 * 选择性分页查询
	 * @param where SQL条件语句
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT probeID , shopID , createTime , updateTime FROM probe_binding ${where} ${order} LIMIT ${start},${limit}")
	List<ProbeBinding> findWhereByPage(@Param("order") String order, @Param("where") String where, @Param("limit") int limit, @Param("start") int start);
	/**
	 * 根据属性查询
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 返回符合条件的数据列表
	 */
	@Select("SELECT probeID , shopID , createTime , updateTime FROM probe_binding WHERE ${propertyName}=#{value} ${order}")
	List<ProbeBinding> findByPropertyName(@Param("order") String order, @Param("propertyName") String propertyName, @Param("value") Object value);
}