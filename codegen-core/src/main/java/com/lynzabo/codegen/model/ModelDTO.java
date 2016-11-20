/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.model;

import java.io.Serializable;
import java.util.Map;

/**
 *  model信息
 * @author linzhanbo .
 * @since 2016年11月18日, 16:19 .
 * @version 1.0 .
 */
public class ModelDTO implements Serializable {
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

    public static class ModelDTOBuilder implements Serializable {
        private ModelDTO modelDTO = new ModelDTO();
        public ModelDTO build() {
            return modelDTO;
        }
        public ModelDTOBuilder setMpackage(String mpackage) {
            modelDTO.mpackage = mpackage;
            return this;
        }
        public ModelDTOBuilder setLocation(String location) {
            modelDTO.location = location;
            return this;
        }

        public ModelDTOBuilder setName(String name) {
            modelDTO.name = name;
            return this;
        }

        public ModelDTOBuilder setFtl(String ftl) {
            modelDTO.ftl = ftl;
            return this;
        }

        public ModelDTOBuilder setDescription(String description) {
            modelDTO.description = description;
            return this;
        }

        public ModelDTOBuilder setProperties(Map properties) {
            modelDTO.properties = properties;
            return this;
        }
    }
}
