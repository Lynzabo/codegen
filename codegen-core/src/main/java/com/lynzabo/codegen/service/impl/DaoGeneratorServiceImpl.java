/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.service.impl;

import com.lynzabo.codegen.except.CodegenException;
import com.lynzabo.codegen.model.DaoDTO;
import com.lynzabo.codegen.model.GenDTO;
import com.lynzabo.codegen.model.RenderDataDTO;
import com.lynzabo.codegen.service.Generator;
import com.lynzabo.codegen.supports.CodegenConfig;
import com.lynzabo.codegen.supports.FreemarkerUtil;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Dao类文件生成
 * @author linzhanbo .
 * @since 2016年11月17日, 16:59 .
 * @version 1.0 .
 */
@Service("daoGeneratorService")
public class DaoGeneratorServiceImpl implements Generator {
    public void render() {
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        DaoDTO daoDTO = genDTO.getDaoDTO();
        RenderDataDTO renderDataDTO = genDTO.getRenderDataDTO();
        Map<String,Object> dataItems = new HashMap<String,Object>();
        dataItems.put("entityName", renderDataDTO.getEntityName());
        dataItems.put("remark", renderDataDTO.getTableRemark());
        //dao properties
        Map daoPropsMap = daoDTO.getProperties();
        dataItems.putAll(daoPropsMap);
        //global properties
        Map globalPropsMaps = CodegenConfig.getInstance().getProperties();
        dataItems.putAll(globalPropsMaps);

        try {
            FreemarkerUtil.renderToFile(dataItems, daoDTO.getFtl(), MessageFormat.format("{0}/{1}/{2}.java", daoDTO.getLocation(),daoDTO.getMpackage().replace(".", "/") ,daoDTO.getName()));
        } catch (Exception e) {
            throw new CodegenException(e);
        }
    }
}
