/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.github.lynzabo.codegen.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 往模板渲染数据
 * @author linzhanbo .
 * @since 2016年11月19日, 18:47 .
 * @version 1.0 .
 */
public class RenderDataDTO implements Serializable {
    /**
     * 表名   ES_USER
     */
    private String tableName;
    /**
     * 表注释
     */
    private String tableRemark;
    /**
     * 实体名 EsUser
     */
    private String entityName;

    /**
     * 实体骆驼命名  esUser
     */
    private String entityAliaName;
    /**
     * 字段
     */
    private Map<String,Column> columns;
    public RenderDataDTO(){
        columns = new HashMap<String, Column>();
    }
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableRemark() {
        return tableRemark;
    }

    public void setTableRemark(String tableRemark) {
        this.tableRemark = tableRemark;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityAliaName() {
        return entityAliaName;
    }

    public void setEntityAliaName(String entityAliaName) {
        this.entityAliaName = entityAliaName;
    }

    public Map<String, Column> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, Column> columns) {
        this.columns = columns;
    }

    public static class Column implements Serializable {
        /**
         * 列字段类型
         */
        private String dbType;
        /**
         * jdbc 类型
         */
        private String jdbcType;
        /**
         * 数据长度
         */
        private int dbLength;
        /**
         * 是否是主键
         */
        private boolean isPK;
        /**
         * 可以为空
         */
        private boolean canNull;
        /**
         * 是否自动增长
         */
        private boolean autoIncr;
        /**
         * JAVA类型
         */
        private String javaType;
        /**
         * JAVA名称
         */
        private String javaObject;
        /**
         * 备注
         */
        private String remark;

        public String getDbType() {
            return dbType;
        }

        public void setDbType(String dbType) {
            this.dbType = dbType;
        }

        public int getDbLength() {
            return dbLength;
        }

        public void setDbLength(int dbLength) {
            this.dbLength = dbLength;
        }

        public boolean getIsPK() {
            return isPK;
        }

        public void setIsPK(boolean isPK) {
            this.isPK = isPK;
        }

        public boolean getIsCanNull() {
            return canNull;
        }

        public void setCanNull(boolean canNull) {
            this.canNull = canNull;
        }

        public boolean getIsAutoIncr() {
            return autoIncr;
        }

        public void setAutoIncr(boolean autoIncr) {
            this.autoIncr = autoIncr;
        }

        public String getJavaType() {
            return javaType;
        }

        public void setJavaType(String javaType) {
            this.javaType = javaType;
        }

        public String getJavaObject() {
            return javaObject;
        }

        public void setJavaObject(String javaObject) {
            this.javaObject = javaObject;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setJdbcType(String jdbcType) {
            this.jdbcType = jdbcType;
        }

        public String getJdbcType() {
            return jdbcType;
        }
    }
}
