/**
 *
 *  Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 *
 */
package com.github.lynzabo.common;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MyBatis通用按值转换 枚举转换处理器<br>
 * 适合于所有实现于ByteEnum接口的枚举类<br>
 * MyBatis自带枚举处理器EnumOrdinalTypeHandler是按照索引赋值，如处理MclusterStatus枚举，数据库的值为9，应对应枚举项STOPED，
 * 但使用EnumOrdinalTypeHandler却对应的是DESTROYING，因为DESTROYING索引为9
 * 
 * @author linzhanbo .
 * @since 2016年7月18日, 上午15:14:14 .
 * @version 1.0 .
 */
public class IntValueEnumTypeHandler<T extends ByteEnum> extends BaseTypeHandler<T> {

	private Class<T> type;
	private T[] enums;
	public IntValueEnumTypeHandler() {
	}
	/**
	 * 在构造方法中预存当前枚举类所有枚举项
	 * 
	 * @param type
	 *            配置文件中设置的转换类
	 */
	public IntValueEnumTypeHandler(Class<T> type) {
		if (type == null)
			throw new IllegalArgumentException("Type argument cannot be null");
		this.type = type;
		this.enums = type.getEnumConstants();
		if (this.enums == null)
			throw new IllegalArgumentException(type.getSimpleName()
					+ " does not represent an enum type.");
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, T parameter,
			JdbcType jdbcType) throws SQLException {
		ps.setObject(i, parameter.getValue(), jdbcType.TYPE_CODE);
	}

	/**
	 * 使用字段名获取数据
	 */
	@Override
	public T getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		// 根据数据库存储类型决定获取类型，预先约定数据库定义的均为TINYINT类型
		Integer i = rs.getInt(columnName);
		if (rs.wasNull()) {
			return null;
		} else {
			return locateEnumStatus(i);
		}
	}

	/**
	 * 使用字段索引获取数据
	 */
	@Override
	public T getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		// 根据数据库存储类型决定获取类型，预先约定数据库定义的均为TINYINT类型
		Integer i = rs.getInt(columnIndex);
		if (rs.wasNull()) {
			return null;
		} else {
			return locateEnumStatus(i);
		}
	}

	@Override
	public T getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// 根据数据库存储类型决定获取类型，预先约定数据库定义的均为TINYINT类型
		Integer i = cs.getInt(columnIndex);
		if (cs.wasNull()) {
			return null;
		} else {
			return locateEnumStatus(i);
		}
	}

	/**
	 * 根据数据库中的value值，定位PersonType子类
	 * 
	 * @param value
	 *            数据库中存储的自定义value属性
	 * @return value对应的枚举类
	 */
	private T locateEnumStatus(Integer value) {
		for (T e : enums) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException("未知的枚举类型：" + value + ",请核对"
				+ type.getSimpleName());
	}

}
