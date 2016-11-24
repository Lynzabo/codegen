/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen;

import com.lynzabo.codegen.dao.Connector;
import com.lynzabo.codegen.except.CodegenException;
import com.lynzabo.codegen.model.GenDTO;
import com.lynzabo.codegen.service.Generator;
import com.lynzabo.codegen.supports.CodegenConfig;
import com.lynzabo.codegen.supports.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * GenerateStarter
 * @author linzhanbo .
 * @since 2016年11月16日, 19:19 .
 * @version 1.0 .
 */
@Component("generateStarter")
public class GenerateStarter {
    private final static Logger logger = LoggerFactory.getLogger(GenerateStarter.class);

    @Resource(name = "mysqlConnector")
    private Connector connector;
    //初始化配置信息
    private void initialize(String codegenPath){
        logger.debug("initialize codegen");
        //1、校验并解析配置信息
        CodegenConfig.getInstance().initConfig(codegenPath);
        //2、初始化数据库连接信息
        connector.getConnection();
        //3、检查环境（包括表都存在与否），检查表/视图是否存在
        connector.checkTablesIsExist();
        //4.准备codegen大本营常量信息
        CodegenConfig.getInstance().checkGenData(connector);
        logger.debug("initialize ok!");
    }

    public void start(String codegenPath){
        setBanner();
        initialize(codegenPath);
        render();
        connector.closeConnection();
    }

    private void setBanner() {
        logger.info("\n   ____     ____     ______      _____      _____     _____      __      _  \n" +
                "  / ___)   / __ \\   (_  __ \\    / ___/     / ___ \\   / ___/     /  \\    / ) \n" +
                " / /      / /  \\ \\    ) ) \\ \\  ( (__      / /   \\_) ( (__      / /\\ \\  / /  \n" +
                "( (      ( ()  () )  ( (   ) )  ) __)    ( (  ____   ) __)     ) ) ) ) ) )  \n" +
                "( (      ( ()  () )   ) )  ) ) ( (       ( ( (__  ) ( (       ( ( ( ( ( (   \n" +
                " \\ \\___   \\ \\__/ /   / /__/ /   \\ \\___    \\ \\__/ /   \\ \\___   / /  \\ \\/ /   \n" +
                "  \\____)   \\____/   (______/     \\____\\    \\____/     \\____\\ (_/    \\__/    \n" +
                "                                                                            ");
    }

    private void render() {
        logger.debug("render...");
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        try {
            //render sqlMapper
            if(null != genDTO.getSqlMapperDTO()){
                Generator generator = (Generator) SpringContextUtil.getBean("sqlMapperGeneratorService");
                generator.render();
            }
            //render model
            if(null != genDTO.getModelDTO()){
                Generator generator = (Generator) SpringContextUtil.getBean("modelGeneratorService");
                generator.render();
            }
            //render dao
            if(null != genDTO.getDaoDTO()){
                Generator generator = (Generator) SpringContextUtil.getBean("daoGeneratorService");
                generator.render();
            }
            //render service
            if(null != genDTO.getServiceDTO()){
                Generator generator = (Generator) SpringContextUtil.getBean("serviceGeneratorService");
                generator.render();
            }
            //render serviceImpl
            if(null != genDTO.getServiceDTO()){
                Generator generator = (Generator) SpringContextUtil.getBean("serviceImplGeneratorService");
                generator.render();
            }
            //render controller
            if(null != genDTO.getServiceDTO()){
                Generator generator = (Generator) SpringContextUtil.getBean("controllerGeneratorService");
                generator.render();
            }
            //render interface
            if(null != genDTO.getServiceDTO()){
                Generator generator = (Generator) SpringContextUtil.getBean("interfaceGeneratorService");
                generator.render();
            }
            logger.debug("render ok!");
        } catch (CodegenException e) {
            throw new CodegenException("render error",e);
        }
    }
}
