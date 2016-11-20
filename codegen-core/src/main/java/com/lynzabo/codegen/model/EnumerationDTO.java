/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.model;

import java.io.Serializable;

/**
 *  enumeration信息
 * @author linzhanbo .
 * @since 2016年11月18日, 16:19 .
 * @version 1.0 .
 */
public class EnumerationDTO implements Serializable {
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
    public String getMpackage() {
        return mpackage;
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
    public static class EnumerationDTOBuilder  implements Serializable {
        private EnumerationDTO enumerationDTO = new EnumerationDTO();
        public EnumerationDTO build() {
            return enumerationDTO;
        }
        public EnumerationDTOBuilder setMpackage(String mpackage) {
            enumerationDTO.mpackage = mpackage;
            return this;
        }
        public EnumerationDTOBuilder setLocation(String location) {
            enumerationDTO.location = location;
            return this;
        }

        public EnumerationDTOBuilder setName(String name) {
            enumerationDTO.name = name;
            return this;
        }

        public EnumerationDTOBuilder setFtl(String ftl) {
            enumerationDTO.ftl = ftl;
            return this;
        }

        public EnumerationDTOBuilder setDescription(String description) {
            enumerationDTO.description = description;
            return this;
        }
    }
}
