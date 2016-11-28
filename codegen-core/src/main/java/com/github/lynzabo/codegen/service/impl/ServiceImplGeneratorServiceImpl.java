/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.github.lynzabo.codegen.service.impl;

import com.github.lynzabo.codegen.except.CodegenException;
import com.github.lynzabo.codegen.model.GenDTO;
import com.github.lynzabo.codegen.model.ServiceImplDTO;
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
 * Service类文件生成
 * @author linzhanbo .
 * @since 2016年11月17日, 16:59 .
 * @version 1.0 .
 */
@Service("serviceImplGeneratorService")
public class ServiceImplGeneratorServiceImpl extends AbstractGeneratorServiceImpl implements Generator {
    public void render() {
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ServiceImplDTO serviceImplDTO = genDTO.getServiceImplDTO();
        Map<String,Object> dataItems = new HashMap<String,Object>();
        //ftl需要
        dataItems.put("serviceImplPackage", getServiceImplPackage());
        dataItems.put("daoPackage",getDaoPackage());
        dataItems.put("daoName",getDaoName());
        dataItems.put("daoEnitityName", StringUtil.sub1Upper(getDaoName()));
        dataItems.put("modelPackage",getModelPackage());
        dataItems.put("entityName",getEntityName());
        dataItems.put("servicePackage", getServicePackage());
        dataItems.put("serviceName", getServiceName());
        dataItems.put("serviceImplDescription", getServiceImplDescription());
        dataItems.put("serviceIOCName", StringUtil.firstLower(getServiceIOCName()));
        dataItems.put("serviceImplName", getServiceImplName());

        //serviceImpl properties
        Map serviceImplPropsMap = serviceImplDTO.getProperties();
        if(!CollectionUtils.isEmpty(serviceImplPropsMap))
            dataItems.putAll(serviceImplPropsMap);
        //global properties
        Map globalPropsMaps = CodegenConfig.getInstance().getProperties();
        if(!CollectionUtils.isEmpty(globalPropsMaps))
            dataItems.putAll(globalPropsMaps);
        try {
            FreemarkerUtil.renderToFile(dataItems, serviceImplDTO.getFtl(), MessageFormat.format("{0}/{1}/{2}.java", getServiceImplLocation(), getServiceImplPackage().replace(".", "/"), getServiceImplName()));
        } catch (Exception e) {
            throw new CodegenException(e);
        }
    }
}
