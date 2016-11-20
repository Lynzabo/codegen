/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.dao.impl;

import com.lynzabo.codegen.dao.Connector;
import com.lynzabo.codegen.model.RenderDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Map;

/**
 * Oracle数据库连接
 *
 * @author linzhanbo .
 * @version 1.0 .
 * @since 2016年11月17日, 16:07 .
 */
@Repository("oracleConnector")
public class OracleConnectorImpl implements Connector {
    private final static Logger logger = LoggerFactory.getLogger(OracleConnectorImpl.class);

    public Connection getConnection() {
        return null;
    }

    public void closeConnection() {

    }

    public DatabaseMetaData getDatabaseMetaData() {
        return null;
    }

    public Map<String, String> getPrimaryKey(String tableName) {
        return null;
    }

    public String getTableRemark(String tableName) {
        return null;
    }

    public Map<String, RenderDataDTO.Column> getColumns(String tableName) {
        return null;
    }

    public Map<String, String> getColumnNameType(String tableName) {
        return null;
    }

    public Map<String, String> getColumnRemark(String tableName) {
        return null;
    }

    public void checkTablesIsExist() {

    }
}
