/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.github.lynzabo.codegen.dao;

import com.github.lynzabo.codegen.model.RenderDataDTO;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Map;

/**
 * 数据库连接接口
 * @author linzhanbo .
 * @since 2016年11月17日, 16:06 .
 * @version 1.0 .
 */
public interface Connector {
    /**
     * 获取数据库连接
     * @return  connection
     */
    Connection getConnection();

    /**
     * 关闭数据库连接
     */
    void closeConnection();

    /**
     * 获取数据库元数据信息
     * @return  db meta info
     */
    DatabaseMetaData getDatabaseMetaData();

    /**
     * 获取表/视图备注
     * @param tableName table name
     * @return  表注释
     */
    String getTableRemark(String tableName);

    /**
     * 获取表各个列信息
     * @param tableName table name
     * @return  表个列信
     */
    Map<String,RenderDataDTO.Column> getColumns(String tableName);

    /**
     * 检查表/视图是否存在
     */
    void checkTablesIsExist();
}
