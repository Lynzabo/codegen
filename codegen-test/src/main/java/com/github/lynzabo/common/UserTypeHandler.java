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
 * 自动赋、获取用户名类型转换器<br/>
 * 定义MyBatis mapping文件时使用typeHandler为UserTypeHandler，自动填充用户名进去<br/>
 * 当用户定义typeHandler为UserTypeHandler时候，如果代码中手动赋值，则不替换，如果没有手动赋值，
 * 该UserTypeHandler会自动赋默认值<br/>
 * 
 * @author linzhanbo
 * @since 2016年7月18日, 上午16:18:14 .
 * @version 1.0 .
 *
 */
public class UserTypeHandler extends BaseTypeHandler<Long> {
	public UserTypeHandler(){
		
	}
	/**
	 * 在构造方法中预存当前枚举类所有枚举项
	 * 
	 * @param type
	 *            配置文件中设置的转换类
	 */
	public UserTypeHandler(Class<Long> type) {
		if (type == null)
			throw new IllegalArgumentException("Type argument cannot be null");
	}

	/**
	 * 默认为空时进 赋值
	 */
	@Override
	public void setParameter(PreparedStatement ps, int i, Long parameter,
			JdbcType jdbcType) throws SQLException {
		setNonNullParameter(ps, i, parameter, jdbcType);
		;
	}

	/**
	 * 默认当值不为空时进 赋值
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Long parameter, JdbcType jdbcType) throws SQLException {
		// 当没有自定义赋值时，我们赋默认值
		if (parameter == null) {
			// 从缓存中获取登录用户信息
			/*SessionServiceImpl sessionService = (SessionServiceImpl) SpringContextUtil
					.getBean("sessionService");
			if (null == sessionService)
				return;
			Session session = sessionService.getSession();
			if (session != null)
				// 设置该字段默认值为userId
				parameter = session.getUserId();*/
		}

		ps.setObject(i, parameter, jdbcType.TYPE_CODE);
	}

	@Override
	public Long getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		Long userId = rs.getLong(columnName);
		return userId;
	}

	@Override
	public Long getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		Long userId = rs.getLong(columnIndex);
		return userId;
	}

	@Override
	public Long getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		Long userId = cs.getLong(columnIndex);
		return userId;
	}

	/**
	 * 使用用户ID获取用户登录名<br>
	 * 
	 * @param userId
	 * @return
	 */
	public String getUserLoginName(Long userId) {
		/*LoginProxyImpl loginProxy = (LoginProxyImpl) SpringContextUtil
				.getApplicationContext().getBean(LoginProxyImpl.class);
		UserLogin user = loginProxy.selectById(userId);
		return user.getLoginName();*/
		return null;
	}
}
