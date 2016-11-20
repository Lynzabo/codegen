/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.model;

import java.io.Serializable;
import java.util.Map;

/**
 *  dao信息
 * @author linzhanbo .
 * @since 2016年11月18日, 16:05 .
 * @version 1.0 .
 */
public class DaoDTO  implements Serializable {
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

    public static class DaoDTOBuilder  implements Serializable{
        private DaoDTO daoDTO = new DaoDTO();

        public DaoDTO build() {
            return daoDTO;
        }
        public DaoDTOBuilder setMpackage(String mpackage) {
            daoDTO.mpackage = mpackage;
            return this;
        }
        public DaoDTOBuilder setLocation(String location) {
            daoDTO.location = location;
            return this;
        }

        public DaoDTOBuilder setName(String name) {
            daoDTO.name = name;
            return this;
        }

        public DaoDTOBuilder setFtl(String ftl) {
            daoDTO.ftl = ftl;
            return this;
        }

        public DaoDTOBuilder setDescription(String description) {
            daoDTO.description = description;
            return this;
        }

        public DaoDTOBuilder setProperties(Map properties) {
            daoDTO.properties = properties;
            return this;
        }
    }
}
