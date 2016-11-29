/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.github.lynzabo.codegen.service.impl;

import com.github.lynzabo.codegen.except.CodegenException;
import com.github.lynzabo.codegen.model.GenDTO;
import com.github.lynzabo.codegen.model.ProxyImplDTO;
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
 * ProxyImpl类文件生成
 * @author linzhanbo .
 * @since 2016年11月17日, 16:59 .
 * @version 1.0 .
 */
@Service("proxyImplGeneratorService")
public class ProxyImplGeneratorServiceImpl extends AbstractGeneratorServiceImpl implements Generator {
    public void render() {
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ProxyImplDTO proxyImplDTO = genDTO.getProxyImplDTO();
        Map<String,Object> dataItems = new HashMap<String,Object>();
        //ftl需要
        dataItems.put("proxyImplPackage", getProxyImplPackage());
        dataItems.put("modelPackage",getModelPackage());
        dataItems.put("entityName",getEntityName());
        dataItems.put("servicePackage",getServicePackage());
        dataItems.put("serviceName", getServiceName());
        dataItems.put("proxyImplDescription", getProxyImplDescription());
        dataItems.put("componentIOCName", StringUtil.firstLower(getProxyIOCName()));
        dataItems.put("proxyImplName", getProxyImplName());
        dataItems.put("proxyPackage",getProxyPackage());
        dataItems.put("proxyName",getProxyName());
        dataItems.put("serviceEnitityName", StringUtil.sub1Upper(getServiceName()));

        //proxyImpl properties
        Map proxyImplPropsMap = proxyImplDTO.getProperties();
        if(!CollectionUtils.isEmpty(proxyImplPropsMap))
            dataItems.putAll(proxyImplPropsMap);
        //global properties
        Map globalPropsMaps = CodegenConfig.getInstance().getProperties();
        if(!CollectionUtils.isEmpty(globalPropsMaps))
            dataItems.putAll(globalPropsMaps);
        try {
            FreemarkerUtil.renderToFile(dataItems, proxyImplDTO.getFtl(), MessageFormat.format("{0}/{1}/{2}.java", getProxyImplLocation(), getProxyImplPackage().replace(".", "/"), getProxyImplName()));
        } catch (Exception e) {
            throw new CodegenException(e);
        }
    }
}
