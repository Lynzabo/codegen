/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.model;

import java.io.Serializable;
import java.util.Map;

/**
 *  service信息
 * @author linzhanbo .
 * @since 2016年11月18日, 16:19 .
 * @version 1.0 .
 */
public class ServiceDTO implements Serializable {
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

    public static class ServiceDTOBuilder implements Serializable{
        private ServiceDTO serviceDTO = new ServiceDTO();
        public ServiceDTO build() {
            return serviceDTO;
        }
        public ServiceDTOBuilder setMpackage(String mpackage) {
            serviceDTO.mpackage = mpackage;
            return this;
        }
        public ServiceDTOBuilder setLocation(String location) {
            serviceDTO.location = location;
            return this;
        }

        public ServiceDTOBuilder setName(String name) {
            serviceDTO.name = name;
            return this;
        }

        public ServiceDTOBuilder setFtl(String ftl) {
            serviceDTO.ftl = ftl;
            return this;
        }

        public ServiceDTOBuilder setDescription(String description) {
            serviceDTO.description = description;
            return this;
        }

        public ServiceDTOBuilder setProperties(Map properties) {
            serviceDTO.properties = properties;
            return this;
        }
    }
}
