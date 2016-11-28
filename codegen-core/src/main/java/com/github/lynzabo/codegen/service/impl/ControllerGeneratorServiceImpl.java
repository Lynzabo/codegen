/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.github.lynzabo.codegen.service.impl;

import com.github.lynzabo.codegen.except.CodegenException;
import com.github.lynzabo.codegen.model.ControllerDTO;
import com.github.lynzabo.codegen.model.GenDTO;
import com.github.lynzabo.codegen.service.Generator;
import com.github.lynzabo.codegen.supports.CodegenConfig;
import com.github.lynzabo.codegen.supports.FreemarkerUtil;
import com.github.lynzabo.codegen.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller类文件生成
 * @author linzhanbo .
 * @since 2016年11月17日, 16:59 .
 * @version 1.0 .
 */
@Service("controllerGeneratorService")
public class ControllerGeneratorServiceImpl extends AbstractGeneratorServiceImpl implements Generator {
    public void render() {
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ControllerDTO controllerDTO = genDTO.getControllerDTO();
        Map<String,Object> dataItems = new HashMap<String,Object>();
        //ftl需要
        dataItems.put("controllerPackage", getControllerPackage());
        dataItems.put("modelPackage",getModelPackage());
        dataItems.put("entityName",getEntityName());
        dataItems.put("servicePackage",getServicePackage());
        dataItems.put("serviceName", getServiceName());
        dataItems.put("proxyPackage", getProxyPackage());
        dataItems.put("proxyName", getProxyName());
        dataItems.put("controllerDescription", getControllerDescription());
        dataItems.put("controllerIOCName", StringUtil.isEmpty(getControllerIOCName())?"":StringUtil.firstLower(getControllerIOCName()));
        dataItems.put("requestMapping", StringUtil.firstLower(getControllerRequestMapping()));
        dataItems.put("controllerName", getControllerName());
        dataItems.put("serviceEnitityName", StringUtil.sub1Upper(getServiceName()));
        dataItems.put("proxyEntityName", StringUtil.sub1Upper(getProxyName()));

        //controller properties
        Map controllerPropsMap = controllerDTO.getProperties();
        if(!CollectionUtils.isEmpty(controllerPropsMap))
            dataItems.putAll(controllerPropsMap);
        //global properties
        Map globalPropsMaps = CodegenConfig.getInstance().getProperties();
        if(!CollectionUtils.isEmpty(globalPropsMaps))
            dataItems.putAll(globalPropsMaps);
        try {
            FreemarkerUtil.renderToFile(dataItems, controllerDTO.getFtl(), MessageFormat.format("{0}/{1}/{2}.java", getControllerLocation(), getControllerPackage().replace(".", "/"), getControllerName()));
        } catch (Exception e) {
            throw new CodegenException(e);
        }
    }
}
