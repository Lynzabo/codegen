/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.dao.impl;

import com.lynzabo.codegen.dao.Connector;
import com.lynzabo.codegen.except.CodegenException;
import com.lynzabo.codegen.model.RenderDataDTO;
import com.lynzabo.codegen.supports.CodegenConfig;
import com.lynzabo.codegen.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * MySQL数据库连接
 *
 * @author linzhanbo .
 * @version 1.0 .
 * @since 2016年11月17日, 16:07 .
 */
@Repository("mysqlConnector")
public class MySQLConnectorImpl implements Connector {
    private final static Logger logger = LoggerFactory.getLogger(MySQLConnectorImpl.class);
    private Connection conn;
    private DatabaseMetaData databaseMetaData;
    public Connection getConnection() {
        if(null != conn)
            return conn;
        logger.debug("connect to MySQL server!");
        Connection connection = null;
        try {
            CodegenConfig.DataSource dataSource = CodegenConfig.getInstance().getDataSource();
            String driverClassName = dataSource.getDriverClassName();
            String url = dataSource.getUrl();
            String userName = dataSource.getUsername();
            String password = dataSource.getPassword();
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, userName, password);
            logger.debug("connected!");
        } catch (Exception ex) {
            throw new CodegenException(ex);
        }
        this.conn = connection;
        return connection;
    }

    public void closeConnection() {
        logger.debug("close to MySQL server!");
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new CodegenException(ex);
        }
    }

    public DatabaseMetaData getDatabaseMetaData() {
        DatabaseMetaData databaseMetaData;
        try {
            databaseMetaData = conn.getMetaData();
        } catch (SQLException e) {
            throw new CodegenException(e);
        }
        return databaseMetaData;
    }

    public Map<String, String> getPrimaryKey(String tableName) {
        Map<String, String> map = new HashMap<String, String>();
        ResultSet pkRSet = null;
        try {
            pkRSet = getDatabaseMetaData().getPrimaryKeys(null, null, tableName);
            while (pkRSet.next()) {
                String primaryKey = pkRSet.getString("COLUMN_NAME");
                String primaryKeyType = getColumnNameType(pkRSet.getString("TABLE_NAME")).get(primaryKey);
                map.put("primaryKey", primaryKey);
                map.put("primaryKeyType", primaryKeyType);
            }
        } catch (SQLException e) {
            throw new CodegenException(e);
        }

        return map;
    }

    public String getTableRemark(String tableName) {
        String[] types = { "TABLE","VIEW" };//从表/视图查看
        ResultSet rs = null;
        try {
            rs = getDatabaseMetaData().getTables(null, null, "%", types);
            while (rs.next()) {
                String tbName = rs.getString("TABLE_NAME"); 	//表/视图名
                if(tbName.toUpperCase().equals(tableName.toUpperCase())){
                    String viewType = rs.getString("TABLE_TYPE"); //视图类型
                    String remarks = rs.getString("REMARKS");		//视图备注
                    return rs.getString("REMARKS"); 	//表/视图备注
                }
                continue;
            }
        } catch (SQLException e) {
            throw new CodegenException(e);
        }
        return "";
    }

    public Map<String, RenderDataDTO.Column> getColumns(String tableName) {
        Map<String, RenderDataDTO.Column> columnMap = new HashMap<String, RenderDataDTO.Column>();
        DatabaseMetaData meta = getDatabaseMetaData();
        ResultSet colRet = null;
        try {
            colRet = meta.getColumns(null, "%", tableName, "%");
            while (colRet.next()) {
                RenderDataDTO.Column column = new RenderDataDTO.Column();
                String columnName = colRet.getString("COLUMN_NAME");
                //字段类型
                int digits = colRet.getInt("DECIMAL_DIGITS");
                int dataType = colRet.getInt("DATA_TYPE");
                String columnRemark = colRet.getString("REMARKS");
                String columnType = getDataType(dataType, digits);
                //column.setDbLength();
                column.setJavaType(columnType);
                column.setJavaObject(StringUtil.underline2Camel(columnName, false));
                column.setRemark(columnRemark);
                columnMap.put(columnName,column);
            }
        } catch (SQLException e) {
            throw new CodegenException(e);
        }
        return columnMap;
    }

    public void checkTablesIsExist() {
        String table = CodegenConfig.getInstance().getTable();
        String[] types = { "TABLE","VIEW" };
        ResultSet rs = null;
        try {
            rs = getDatabaseMetaData().getTables(null, null, "%", types);
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME"); 	//表名
                if(table.toUpperCase().equals(tableName.toUpperCase()))
                    return;
            }
        } catch (SQLException e) {
            throw new CodegenException(e);
        }
        throw new CodegenException(MessageFormat.format("表{0}不存在",table));
    }

    public Map<String, String> getColumnNameType(String tableName) {
        Map<String, String> colMap = new LinkedHashMap<String, String>();
        DatabaseMetaData meta = getDatabaseMetaData();
        ResultSet colRet = null;
        try {
            colRet = meta.getColumns(null, "%", tableName, "%");
            while (colRet.next()) {
                String columnName = colRet.getString("COLUMN_NAME");
                int digits = colRet.getInt("DECIMAL_DIGITS");
                int dataType = colRet.getInt("DATA_TYPE");
                String columnType = getDataType(dataType, digits);
                colMap.put(columnName, columnType);
            }
        } catch (SQLException e) {
            throw new CodegenException(e);
        }
        return colMap;
    }
    public Map<String, String> getColumnRemark(String tableName) {
        Map<String, String> colMap = new LinkedHashMap<String, String>();
        DatabaseMetaData meta = getDatabaseMetaData();
        ResultSet colRet = null;
        try {
            colRet = meta.getColumns(null, "%", tableName, "%");
            while (colRet.next()) {
                String columnName = colRet.getString("COLUMN_NAME");
                String columnRemark = colRet.getString("REMARKS");
                colMap.put(columnName, columnRemark);
            }
        } catch (SQLException e) {
            throw new CodegenException(e);
        }
        return colMap;
    }

    private String getDataType(int type, int digits) {
        String dataType = "";
        switch (type) {
            case Types.VARCHAR:  //12
                dataType = "String";
                break;
            case Types.INTEGER:    //4
                dataType = "Integer";
                break;
            case Types.SMALLINT:    //4
                dataType = "Integer";
                break;
            case Types.TINYINT:    //4
                dataType = "Integer";
                break;
            case Types.BIT:    //-7
                dataType = "Integer";
                break;
            case Types.LONGVARCHAR: //-1
                dataType = "Long";
                break;
            case Types.BIGINT: //-5
                dataType = "Long";
                break;
            case Types.DOUBLE: //8
                dataType = getPrecisionType();
                break;
            case Types.REAL: //7
                dataType = getPrecisionType();
                break;
            case Types.FLOAT: //6
                dataType = getPrecisionType();
                break;
            case Types.DECIMAL:    //3
                dataType = "BigDecimal";
                break;
            case Types.NUMERIC: //2
                switch (digits) {
                    case 0:
                        dataType = "Integer";
                        break;
                    default:
                        dataType = getPrecisionType();
                }
                break;
            case Types.TIME:  //91
                dataType = "Date";
                break;
            case Types.DATE:  //91
                dataType = "Date";
                break;
            case Types.TIMESTAMP: //93
                dataType = "Date";
                break;
            default:
                dataType = "String";
        }
        return dataType;
    }
    private String getPrecisionType() {
        String dataType;
        if ("high".equals("heigh")) {
            dataType = "BigDecimal";
        } else {
            dataType = "Double";
        }
        return dataType;
    }
}
