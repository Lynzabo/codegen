/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.model;

import java.io.Serializable;
import java.util.Map;

/**
 *  proxy信息
 * @author linzhanbo .
 * @since 2016年11月18日, 16:19 .
 * @version 1.0 .
 */
public class ProxyDTO implements Serializable {
    /**
     * 生成位置
     */
    private String location;
    /**
     * 生成包名称
     */
    private String mpackage;
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
    public String getMpackage() {
        return mpackage;
    }

    public Map getProperties() {
        return properties;
    }

    public static class ProxyDTOBuilder implements Serializable{
        private ProxyDTO proxyDTO = new ProxyDTO();
        public ProxyDTO build() {
            return proxyDTO;
        }
        public ProxyDTOBuilder setMpackage(String mpackage) {
            proxyDTO.mpackage = mpackage;
            return this;
        }
        public ProxyDTOBuilder setLocation(String location) {
            proxyDTO.location = location;
            return this;
        }

        public ProxyDTOBuilder setName(String name) {
            proxyDTO.name = name;
            return this;
        }

        public ProxyDTOBuilder setFtl(String ftl) {
            proxyDTO.ftl = ftl;
            return this;
        }

        public ProxyDTOBuilder setDescription(String description) {
            proxyDTO.description = description;
            return this;
        }

        public ProxyDTOBuilder setProperties(Map properties) {
            proxyDTO.properties = properties;
            return this;
        }
    }
}
