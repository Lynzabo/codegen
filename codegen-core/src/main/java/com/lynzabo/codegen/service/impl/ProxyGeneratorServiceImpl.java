/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.service.impl;

import com.lynzabo.codegen.except.CodegenException;
import com.lynzabo.codegen.model.GenDTO;
import com.lynzabo.codegen.model.ProxyDTO;
import com.lynzabo.codegen.service.Generator;
import com.lynzabo.codegen.supports.CodegenConfig;
import com.lynzabo.codegen.supports.FreemarkerUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Proxy类文件生成
 * @author linzhanbo .
 * @since 2016年11月17日, 16:59 .
 * @version 1.0 .
 */
@Service("proxyGeneratorService")
public class ProxyGeneratorServiceImpl extends AbstractGeneratorServiceImpl implements Generator {
    public void render() {
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ProxyDTO proxyDTO = genDTO.getProxyDTO();
        Map<String,Object> dataItems = new HashMap<String,Object>();
        //ftl需要
        dataItems.put("proxyPackage", getProxyPackage());
        dataItems.put("modelPackage",getModelPackage());
        dataItems.put("entityName",getEntityName());
        dataItems.put("proxyName", getProxyName());
        dataItems.put("proxyDescription", getProxyDescription());

        //proxy properties
        Map proxyPropsMap = proxyDTO.getProperties();
        if(!CollectionUtils.isEmpty(proxyPropsMap))
            dataItems.putAll(proxyPropsMap);
        //global properties
        Map globalPropsMaps = CodegenConfig.getInstance().getProperties();
        if(!CollectionUtils.isEmpty(globalPropsMaps))
            dataItems.putAll(globalPropsMaps);
        try {
            FreemarkerUtil.renderToFile(dataItems, proxyDTO.getFtl(), MessageFormat.format("{0}/{1}/{2}.java", getProxyLocation(), getProxyPackage().replace(".", "/"), getProxyName()));
        } catch (Exception e) {
            throw new CodegenException(e);
        }
    }
}
