/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.model;

import java.io.Serializable;
import java.util.Map;

/**
 *  service实现信息
 * @author linzhanbo .
 * @since 2016年11月18日, 16:19 .
 * @version 1.0 .
 */
public class ServiceImplDTO implements Serializable {
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
     * Spring 服务层服务注解名称
     */
    private String serviceIOCName;
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

    public String getServiceIOCName() {
        return serviceIOCName;
    }

    public Map getProperties() {
        return properties;
    }

    public static class ServiceImplDTOBuilder implements Serializable {
        private ServiceImplDTO serviceImplDTO = new ServiceImplDTO();
        public ServiceImplDTO build() {
            return serviceImplDTO;
        }
        public ServiceImplDTOBuilder setMpackage(String mpackage) {
            serviceImplDTO.mpackage = mpackage;
            return this;
        }
        public ServiceImplDTOBuilder setServiceIOCName(String serviceIOCName) {
            serviceImplDTO.serviceIOCName = serviceIOCName;
            return this;
        }
        public ServiceImplDTOBuilder setLocation(String location) {
            serviceImplDTO.location = location;
            return this;
        }

        public ServiceImplDTOBuilder setName(String name) {
            serviceImplDTO.name = name;
            return this;
        }

        public ServiceImplDTOBuilder setFtl(String ftl) {
            serviceImplDTO.ftl = ftl;
            return this;
        }

        public ServiceImplDTOBuilder setDescription(String description) {
            serviceImplDTO.description = description;
            return this;
        }

        public ServiceImplDTOBuilder setProperties(Map properties) {
            serviceImplDTO.properties = properties;
            return this;
        }
    }
}
