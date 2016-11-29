/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.github.lynzabo.codegen.model;

import java.io.Serializable;
import java.util.Map;

/**
 *  proxy实现信息
 * @author linzhanbo .
 * @since 2016年11月18日, 16:19 .
 * @version 1.0 .
 */
public class ProxyImplDTO implements Serializable {
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
     * Spring component层服务注解名称
     */
    private String componentIOCName;
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

    public String getComponentIOCName() {
        return componentIOCName;
    }

    public Map getProperties() {
        return properties;
    }

    public static class ProxyImplDTOBuilder implements Serializable {
        private ProxyImplDTO proxyImplDTO = new ProxyImplDTO();
        public ProxyImplDTO build() {
            return proxyImplDTO;
        }
        public ProxyImplDTOBuilder setMpackage(String mpackage) {
            proxyImplDTO.mpackage = mpackage;
            return this;
        }
        public ProxyImplDTOBuilder setComponentIOCName(String componentIOCName) {
            proxyImplDTO.componentIOCName = componentIOCName;
            return this;
        }

        public ProxyImplDTOBuilder setLocation(String location) {
            proxyImplDTO.location = location;
            return this;
        }

        public ProxyImplDTOBuilder setName(String name) {
            proxyImplDTO.name = name;
            return this;
        }

        public ProxyImplDTOBuilder setFtl(String ftl) {
            proxyImplDTO.ftl = ftl;
            return this;
        }

        public ProxyImplDTOBuilder setDescription(String description) {
            proxyImplDTO.description = description;
            return this;
        }

        public ProxyImplDTOBuilder setProperties(Map properties) {
            proxyImplDTO.properties = properties;
            return this;
        }
    }
}
