/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.model;

import java.io.Serializable;
import java.util.Map;

/**
 *  interface信息
 * @author linzhanbo .
 * @since 2016年11月18日, 16:19 .
 * @version 1.0 .
 */
public class InterfaceDTO implements Serializable {
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

    public static class InterfaceDTOBuilder implements Serializable {
        private InterfaceDTO interfaceDTO = new InterfaceDTO();
        public InterfaceDTO build() {
            return interfaceDTO;
        }
        public InterfaceDTOBuilder setLocation(String location) {
            interfaceDTO.location = location;
            return this;
        }

        public InterfaceDTOBuilder setName(String name) {
            interfaceDTO.name = name;
            return this;
        }

        public InterfaceDTOBuilder setFtl(String ftl) {
            interfaceDTO.ftl = ftl;
            return this;
        }

        public InterfaceDTOBuilder setDescription(String description) {
            interfaceDTO.description = description;
            return this;
        }

        public InterfaceDTOBuilder setProperties(Map properties) {
            interfaceDTO.properties = properties;
            return this;
        }
    }
}
