/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.model;

import java.io.Serializable;
import java.util.Map;

/**
 *  mapper.xml信息
 * @author linzhanbo .
 * @since 2016年11月18日, 16:05 .
 * @version 1.0 .
 */
public class SqlMapperDTO implements Serializable {
    /**
     * 生成位置
     */
    private String location;
    /**
     * 自定义名称
     */
    private String name;
    /**
     * 使用模板
     */
    private String ftl;
    /**
     * 介绍
     */
    private String description;
    /**
     * 自定义生成器环境变量
     */
    private Map properties;

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getFtl() {
        return ftl;
    }

    public String getDescription() {
        return description;
    }

    public Map getProperties() {
        return properties;
    }

    public static class SqlMapperDTOBuilder implements Serializable {
        private SqlMapperDTO sqlMapperDTO = new SqlMapperDTO();
        public SqlMapperDTO build() {
            return sqlMapperDTO;
        }
        public SqlMapperDTOBuilder setLocation(String location) {
            sqlMapperDTO.location = location;
            return this;
        }

        public SqlMapperDTOBuilder setName(String name) {
            sqlMapperDTO.name = name;
            return this;
        }

        public SqlMapperDTOBuilder setFtl(String ftl) {
            sqlMapperDTO.ftl = ftl;
            return this;
        }

        public SqlMapperDTOBuilder setDescription(String description) {
            sqlMapperDTO.description = description;
            return this;
        }

        public SqlMapperDTOBuilder setProperties(Map properties) {
            sqlMapperDTO.properties = properties;
            return this;
        }
    }
}
