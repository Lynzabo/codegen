/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.github.lynzabo.codegen.service.impl;

import com.github.lynzabo.codegen.except.CodegenException;
import com.github.lynzabo.codegen.model.GenDTO;
import com.github.lynzabo.codegen.model.SqlMapperDTO;
import com.github.lynzabo.codegen.service.Generator;
import com.github.lynzabo.codegen.supports.CodegenConfig;
import com.github.lynzabo.codegen.supports.FreemarkerUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * mapper.xml文件生成
 * @author linzhanbo .
 * @since 2016年11月17日, 16:59 .
 * @version 1.0 .
 */
@Service("sqlMapperGeneratorService")
public class SqlMapperGeneratorServiceImpl extends AbstractGeneratorServiceImpl implements Generator {
    public void render() {
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        String table = CodegenConfig.getInstance().getTable();
        SqlMapperDTO sqlMapperDTO = genDTO.getSqlMapperDTO();
        Map<String,Object> dataItems = new HashMap<String,Object>();
        //ftl需要
        dataItems.put("daoPackage",getDaoPackage());
        dataItems.put("daoName",getDaoName());
        dataItems.put("entityName",getEntityName());
        dataItems.put("columnsMap", genDTO.getRenderDataDTO().getColumns());
        dataItems.put("table", table);
        //sqlMapper properties
        Map sqlMapperPropsMap = sqlMapperDTO.getProperties();
        if(!CollectionUtils.isEmpty(sqlMapperPropsMap))
            dataItems.putAll(sqlMapperPropsMap);
        //global properties
        Map globalPropsMaps = CodegenConfig.getInstance().getProperties();
        if(!CollectionUtils.isEmpty(globalPropsMaps))
            dataItems.putAll(globalPropsMaps);
        try {
            FreemarkerUtil.renderToFile(dataItems, sqlMapperDTO.getFtl(), MessageFormat.format("{0}/{1}.xml", getSqlMapperLocation(), getSqlMapperName()));
        } catch (Exception e) {
            throw new CodegenException(e);
        }
    }
}
