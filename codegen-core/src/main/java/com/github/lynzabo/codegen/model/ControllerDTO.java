/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.github.lynzabo.codegen.model;

import java.io.Serializable;
import java.util.Map;

/**
 *  controller信息
 * @author linzhanbo .
 * @since 2016年11月18日, 16:19 .
 * @version 1.0 .
 */
public class ControllerDTO implements Serializable {
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
     * Spring Controller层注解名称
     */
    private String controllerIOCName;
    /**
     * SpringMVC Controller层注解名称
     */
    private String requestMapping;
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

    public String getMpackage() {
        return mpackage;
    }
    public String getControllerIOCName() {
        return controllerIOCName;
    }

    public String getRequestMapping() {
        return requestMapping;
    }
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

    public static class ControllerDTOBuilder implements Serializable{
        private ControllerDTO controllerDTO = new ControllerDTO();
        public ControllerDTO build() {
            return controllerDTO;
        }
        public ControllerDTOBuilder setMpackage(String mpackage) {
            controllerDTO.mpackage = mpackage;
            return this;
        }
        public ControllerDTOBuilder setControllerIOCName(String controllerIOCName) {
            controllerDTO.controllerIOCName = controllerIOCName;
            return this;
        }

        public ControllerDTOBuilder setRequestMapping(String requestMapping) {
            controllerDTO.requestMapping = requestMapping;
            return this;
        }
        public ControllerDTOBuilder setLocation(String location) {
            controllerDTO.location = location;
            return this;
        }

        public ControllerDTOBuilder setName(String name) {
            controllerDTO.name = name;
            return this;
        }

        public ControllerDTOBuilder setFtl(String ftl) {
            controllerDTO.ftl = ftl;
            return this;
        }

        public ControllerDTOBuilder setDescription(String description) {
            controllerDTO.description = description;
            return this;
        }

        public ControllerDTOBuilder setProperties(Map properties) {
            controllerDTO.properties = properties;
            return this;
        }
    }

}
