/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.service.impl;

import com.lynzabo.codegen.except.CodegenException;
import com.lynzabo.codegen.model.GenDTO;
import com.lynzabo.codegen.model.ServiceDTO;
import com.lynzabo.codegen.service.Generator;
import com.lynzabo.codegen.supports.CodegenConfig;
import com.lynzabo.codegen.supports.FreemarkerUtil;
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
@Service("serviceGeneratorService")
public class ServiceGeneratorServiceImpl extends AbstractGeneratorServiceImpl implements Generator {
    public void render() {
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ServiceDTO serviceDTO = genDTO.getServiceDTO();
        Map<String,Object> dataItems = new HashMap<String,Object>();
        //ftl需要
        dataItems.put("servicePackage", getServicePackage());
        dataItems.put("modelPackage",getModelPackage());
        dataItems.put("entityName",getEntityName());
        dataItems.put("serviceName", getServiceName());
        dataItems.put("serviceDescription", getServiceDescription());

        //service properties
        Map servicePropsMap = serviceDTO.getProperties();
        if(!CollectionUtils.isEmpty(servicePropsMap))
            dataItems.putAll(servicePropsMap);
        //global properties
        Map globalPropsMaps = CodegenConfig.getInstance().getProperties();
        if(!CollectionUtils.isEmpty(globalPropsMaps))
            dataItems.putAll(globalPropsMaps);
        try {
            FreemarkerUtil.renderToFile(dataItems, serviceDTO.getFtl(), MessageFormat.format("{0}/{1}/{2}.java", getServiceLocation(), getServicePackage().replace(".", "/"), getServiceName()));
        } catch (Exception e) {
            throw new CodegenException(e);
        }
    }
}
