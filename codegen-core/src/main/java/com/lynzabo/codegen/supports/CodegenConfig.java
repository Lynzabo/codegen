/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.supports;

import com.lynzabo.codegen.dao.Connector;
import com.lynzabo.codegen.except.CodegenException;
import com.lynzabo.codegen.model.*;
import com.lynzabo.codegen.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * 读取配置 整个应用任何地方都可以get
 * @author linzhanbo .
 * @since 2016年11月16日, 16:15 .
 * @version 1.0 .
 */
public enum CodegenConfig {
    INSTANCE;
    private final static Logger logger = LoggerFactory.getLogger(CodegenConfig.class);

    public static CodegenConfig getInstance(){
        return INSTANCE;
    }

    /**
     * 解析配置
     */
    public void initConfig(String codegenPath) {
        logger.debug("init config");
        if (StringUtil.isEmpty(codegenPath) || !new File(codegenPath).exists()) {
            throw new CodegenException("init config error,未找到config.yaml文件");
        }
        try {
            Yaml yaml = new Yaml();
            Map map = (Map) yaml.load(new FileInputStream(codegenPath));

            //codegen工作根目录 (codegen.yaml文件和template目录所在目录)
            workDir = codegenPath.substring(0, codegenPath.lastIndexOf("/"));

            Map datasourceMap = (Map) map.get("datasource");
            parseDataSource(datasourceMap);

            Map mapperMap = (Map) map.get("mapper");
            parseMapper(mapperMap);

            String tab = (String) map.get("table");
            parseTable(tab);

            Map genMap = (Map) map.get("gen");
            parseGen(genMap);

            Map propertiesMap = (Map) map.get("properties");
            parseProperties(propertiesMap);
        } catch (Exception ex) {
            throw new CodegenException("init config error",ex);
        }
    }

    private void parseMapper(Map mapperMap) {
        if(CollectionUtils.isEmpty(mapperMap))
            throw new CodegenException("config.yaml配置mapper为空!");
        mapper = mapperMap;
    }

    //自定义生成器环境变量
    private void parseProperties(Map propertiesMap) {
        if(CollectionUtils.isEmpty(propertiesMap))
            throw new CodegenException("config.yaml配置properties为空!");
        this.properties = propertiesMap;

    }

    //生成代码配置
    private void parseGen(Map genMap) {
        if(CollectionUtils.isEmpty(genMap))
            throw new CodegenException("config.yaml配置gen为空!");
        //package
        if(StringUtil.isEmpty((String) genMap.get("package"))){
            throw new CodegenException("config.yaml配置gen > package为空!");
        }

        genDTOBuilder.setMpackage((String) genMap.get("package"));
        //sqlMapper
        Map sqlMapperMap = (Map) genMap.get("sqlMapper");
        if (!CollectionUtils.isEmpty(sqlMapperMap)) {
            SqlMapperDTO.SqlMapperDTOBuilder sqlMapperDTOBuilder = new SqlMapperDTO.SqlMapperDTOBuilder();
            sqlMapperDTOBuilder.setLocation(
                    (null == sqlMapperMap.get("location") || StringUtil.isEmpty((String) sqlMapperMap.get("location"))) ?
                            "" : (String) sqlMapperMap.get("location")
            ).setName(
                    (null == sqlMapperMap.get("name") || StringUtil.isEmpty((String) sqlMapperMap.get("name"))) ?
                            "" : (String) sqlMapperMap.get("name")
            );
            if ((null == sqlMapperMap.get("ftl") || StringUtil.isEmpty((String) sqlMapperMap.get("ftl"))))
                throw new CodegenException("config.yaml配置gen > sqlMapper > ftl为空!");
            sqlMapperDTOBuilder.setFtl((String) sqlMapperMap.get("ftl"));
            sqlMapperDTOBuilder.setDescription(
                    (null == sqlMapperMap.get("description") || StringUtil.isEmpty((String) sqlMapperMap.get("description"))) ?
                            "" : (String) sqlMapperMap.get("description")
            );
            Map sqlPropertiesMap = (Map) sqlMapperMap.get("properties");
            if(!CollectionUtils.isEmpty(sqlPropertiesMap))
                sqlMapperDTOBuilder.setProperties(sqlPropertiesMap);
            genDTOBuilder.setSqlMapperDTO(sqlMapperDTOBuilder.build());
        }
        //model
       Map modelMap = (Map) genMap.get("model");
        if (!CollectionUtils.isEmpty(modelMap)) {
            ModelDTO.ModelDTOBuilder modelDTOBuilder = new ModelDTO.ModelDTOBuilder();
            modelDTOBuilder.setLocation(
                    (null == modelMap.get("location") || StringUtil.isEmpty((String) modelMap.get("location"))) ?
                            "":(String)modelMap.get("location")
            ).setMpackage(
                    (null == modelMap.get("package") || StringUtil.isEmpty((String) modelMap.get("package"))) ?
                            "" : (String) modelMap.get("package")
            ).setName(
                    (null == modelMap.get("name") || StringUtil.isEmpty((String) modelMap.get("name"))) ?
                            "" : (String) modelMap.get("name")
            );
            if ((null == modelMap.get("ftl") || StringUtil.isEmpty((String) modelMap.get("ftl"))))
                throw new CodegenException("config.yaml配置gen > model > ftl为空!");
            modelDTOBuilder.setFtl((String) modelMap.get("ftl"));
            modelDTOBuilder.setDescription(
                    (null == modelMap.get("description") || StringUtil.isEmpty((String) modelMap.get("description"))) ?
                            "" : (String) modelMap.get("description")
            );
            Map modelPropertiesMap = (Map) modelMap.get("properties");
            if(!CollectionUtils.isEmpty(modelPropertiesMap))
                modelDTOBuilder.setProperties(modelPropertiesMap);
            genDTOBuilder.setModelDTO(modelDTOBuilder.build());
        }
        //dao
        Map daoMap = (Map) genMap.get("dao");
        if (!CollectionUtils.isEmpty(daoMap)) {
            DaoDTO.DaoDTOBuilder daoDTOBuilder = new DaoDTO.DaoDTOBuilder();
            daoDTOBuilder.setLocation(
                    (null == daoMap.get("location") || StringUtil.isEmpty((String) daoMap.get("location"))) ?
                            "":(String)daoMap.get("location")
            ).setMpackage(
                    (null == daoMap.get("package") || StringUtil.isEmpty((String) daoMap.get("package"))) ?
                            "" : (String) daoMap.get("package")
            ).setName(
                    (null == daoMap.get("name") || StringUtil.isEmpty((String) daoMap.get("name"))) ?
                            "" : (String) daoMap.get("name")
            );
            if ((null == daoMap.get("ftl") || StringUtil.isEmpty((String) daoMap.get("ftl"))))
                throw new CodegenException("config.yaml配置gen > dao > ftl为空!");
            daoDTOBuilder.setFtl((String) daoMap.get("ftl"));
            daoDTOBuilder.setDescription(
                    (null == daoMap.get("description") || StringUtil.isEmpty((String) daoMap.get("description"))) ?
                            "" : (String) daoMap.get("description")
            );
            Map daoPropertiesMap = (Map) daoMap.get("properties");
            if(!CollectionUtils.isEmpty(daoPropertiesMap))
                daoDTOBuilder.setProperties(daoPropertiesMap);
            genDTOBuilder.setDaoDTO(daoDTOBuilder.build());
        }
        //service
        Map serviceMap = (Map) genMap.get("service");
        if (!CollectionUtils.isEmpty(serviceMap)) {
            ServiceDTO.ServiceDTOBuilder serviceDTOBuilder = new ServiceDTO.ServiceDTOBuilder();
            serviceDTOBuilder.setLocation(
                    (null == serviceMap.get("location") || StringUtil.isEmpty((String) serviceMap.get("location"))) ?
                            "" : (String) serviceMap.get("location")
            ).setMpackage(
                    (null == serviceMap.get("package") || StringUtil.isEmpty((String) serviceMap.get("package"))) ?
                            "" : (String) serviceMap.get("package")
            ).setName(
                    (null == serviceMap.get("name") || StringUtil.isEmpty((String) serviceMap.get("name"))) ?
                            "" : (String) serviceMap.get("name")
            );
            if ((null == serviceMap.get("ftl") || StringUtil.isEmpty((String) serviceMap.get("ftl"))))
                throw new CodegenException("config.yaml配置gen > service > ftl为空!");
            serviceDTOBuilder.setFtl((String) serviceMap.get("ftl"));
            serviceDTOBuilder.setDescription(
                    (null == serviceMap.get("description") || StringUtil.isEmpty((String) serviceMap.get("description"))) ?
                            "" : (String) serviceMap.get("description")
            );
            Map servicePropertiesMap = (Map) serviceMap.get("properties");
            if(!CollectionUtils.isEmpty(servicePropertiesMap))
                serviceDTOBuilder.setProperties(servicePropertiesMap);
            genDTOBuilder.setServiceDTO(serviceDTOBuilder.build());
        }
        //serviceImpl
        Map serviceImplMap = (Map) genMap.get("serviceImpl");
        if (!CollectionUtils.isEmpty(serviceImplMap)) {
            ServiceImplDTO.ServiceImplDTOBuilder serviceImplDTOBuilder = new ServiceImplDTO.ServiceImplDTOBuilder();
            serviceImplDTOBuilder.setLocation(
                    (null == serviceImplMap.get("location") || StringUtil.isEmpty((String) serviceImplMap.get("location"))) ?
                            "" : (String) serviceImplMap.get("location")
            ).setMpackage(
                    (null == serviceImplMap.get("package") || StringUtil.isEmpty((String) serviceImplMap.get("package"))) ?
                            "" : (String) serviceImplMap.get("package")
            ).setName(
                    (null == serviceImplMap.get("name") || StringUtil.isEmpty((String) serviceImplMap.get("name"))) ?
                            "" : (String) serviceImplMap.get("name")
            ).setServiceIOCName(
                    (null == serviceImplMap.get("serviceIOCName") || StringUtil.isEmpty((String) serviceImplMap.get("serviceIOCName"))) ?
                            "" : (String) serviceImplMap.get("serviceIOCName")
            );
            if ((null == serviceImplMap.get("ftl") || StringUtil.isEmpty((String) serviceImplMap.get("ftl"))))
                throw new CodegenException("config.yaml配置gen > serviceImpl > ftl为空!");
            serviceImplDTOBuilder.setFtl((String) serviceImplMap.get("ftl"));
            serviceImplDTOBuilder.setDescription(
                    (null == serviceImplMap.get("description") || StringUtil.isEmpty((String) serviceImplMap.get("description"))) ?
                            "" : (String) serviceImplMap.get("description")
            );
            Map serviceImplPropertiesMap = (Map) serviceImplMap.get("properties");
            if(!CollectionUtils.isEmpty(serviceImplPropertiesMap))
                serviceImplDTOBuilder.setProperties(serviceImplPropertiesMap);
            genDTOBuilder.setServiceImplDTO(serviceImplDTOBuilder.build());
        }
        //controller
        Map controllerMap = (Map) genMap.get("controller");
        if (!CollectionUtils.isEmpty(controllerMap)) {
            ControllerDTO.ControllerDTOBuilder controllerDTOBuilder = new ControllerDTO.ControllerDTOBuilder();
            controllerDTOBuilder.setLocation(
                    (null == controllerMap.get("location") || StringUtil.isEmpty((String) controllerMap.get("location"))) ?
                            "":(String)controllerMap.get("location")
            ).setMpackage(
                    (null == controllerMap.get("package") || StringUtil.isEmpty((String) controllerMap.get("package"))) ?
                            "" : (String) controllerMap.get("package")
            ).setName(
                    (null == controllerMap.get("name") || StringUtil.isEmpty((String) controllerMap.get("name"))) ?
                            "" : (String) controllerMap.get("name")
            ).setControllerIOCName(
                    (null == controllerMap.get("controllerIOCName") || StringUtil.isEmpty((String) controllerMap.get("controllerIOCName"))) ?
                            "" : (String) controllerMap.get("controllerIOCName")
            ).setRequestMapping(
                    (null == controllerMap.get("requestMapping") || StringUtil.isEmpty((String) controllerMap.get("requestMapping"))) ?
                            "" : (String) controllerMap.get("requestMapping")
            );
            if ((null == controllerMap.get("ftl") || StringUtil.isEmpty((String) controllerMap.get("ftl"))))
                throw new CodegenException("config.yaml配置gen > controller > ftl为空!");
            controllerDTOBuilder.setFtl((String) controllerMap.get("ftl"));
            controllerDTOBuilder.setDescription(
                    (null == controllerMap.get("description") || StringUtil.isEmpty((String) controllerMap.get("description"))) ?
                            "" : (String) controllerMap.get("description")
            );
            Map controllerPropertiesMap = (Map) controllerMap.get("properties");
            if(!CollectionUtils.isEmpty(controllerPropertiesMap))
                controllerDTOBuilder.setProperties(controllerPropertiesMap);
            genDTOBuilder.setControllerDTO(controllerDTOBuilder.build());
        }
        //enumeration
        Map enumerationMap = (Map) genMap.get("enumeration");
        if (!CollectionUtils.isEmpty(enumerationMap)) {
            EnumerationDTO.EnumerationDTOBuilder enumerationDTOBuilder = new EnumerationDTO.EnumerationDTOBuilder();
            enumerationDTOBuilder.setLocation(
                    (null == enumerationMap.get("location") || StringUtil.isEmpty((String) enumerationMap.get("location"))) ?
                            "" : (String) enumerationMap.get("location")
            ).setMpackage(
                    (null == enumerationMap.get("package") || StringUtil.isEmpty((String) enumerationMap.get("package"))) ?
                            "" : (String) enumerationMap.get("package")
            ).setName(
                    (null == enumerationMap.get("name") || StringUtil.isEmpty((String) enumerationMap.get("name"))) ?
                            "" : (String) enumerationMap.get("name")
            );
            if ((null == enumerationMap.get("ftl") || StringUtil.isEmpty((String) enumerationMap.get("ftl"))))
                throw new CodegenException("config.yaml配置gen > enumeration > ftl为空!");
            enumerationDTOBuilder.setFtl((String) enumerationMap.get("ftl"));
            enumerationDTOBuilder.setDescription(
                    (null == enumerationMap.get("description") || StringUtil.isEmpty((String) enumerationMap.get("description"))) ?
                            "" : (String) enumerationMap.get("description")
            );
            Map enumerationPropertiesMap = (Map) enumerationMap.get("properties");
            if(!CollectionUtils.isEmpty(enumerationPropertiesMap))
                enumerationDTOBuilder.setProperties(enumerationPropertiesMap);
            genDTOBuilder.setEnumerationDTO(enumerationDTOBuilder.build());
        }
        //interface
        Map interfaceMap = (Map) genMap.get("interface");
        if (!CollectionUtils.isEmpty(interfaceMap)) {
            InterfaceDTO.InterfaceDTOBuilder interfaceDTOBuilder = new InterfaceDTO.InterfaceDTOBuilder();
            interfaceDTOBuilder.setLocation(
                    (null == interfaceMap.get("location") || StringUtil.isEmpty((String) interfaceMap.get("location"))) ?
                            "" : (String) interfaceMap.get("location")
            ).setName(
                    (null == interfaceMap.get("name") || StringUtil.isEmpty((String) interfaceMap.get("name"))) ?
                            "" : (String) interfaceMap.get("name")
            );
            if ((null == interfaceMap.get("ftl") || StringUtil.isEmpty((String) interfaceMap.get("ftl"))))
                throw new CodegenException("config.yaml配置gen > interface > ftl为空!");
            interfaceDTOBuilder.setFtl((String) interfaceMap.get("ftl"));
            interfaceDTOBuilder.setDescription(
                    (null == interfaceMap.get("description") || StringUtil.isEmpty((String) interfaceMap.get("description"))) ?
                            "" : (String) interfaceMap.get("description")
            );
            Map interfacePropertiesMap = (Map) interfaceMap.get("properties");
            if(!CollectionUtils.isEmpty(interfacePropertiesMap))
                interfaceDTOBuilder.setProperties(interfacePropertiesMap);
            genDTOBuilder.setInterfaceDTO(interfaceDTOBuilder.build());
        }
    }

    //解析表信息
    private void parseTable(String table) {
        if(StringUtil.isEmpty(table))
            throw new CodegenException("config.yaml配置table为空!");
        this.table = table;
    }

    /**
     * 获取数据源信息
     * @return
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取sql -> Java 类型映射
     * @return
     */
    public Map<String, Map<String, String>> getMapper() {
        return mapper;
    }

    /**
     * 获取要gen的所有表
     * @return
     */
    public String getTable() {
        return table;
    }
    /**
     * 生成代码配置
     * @return
     */
    public GenDTO getGenDTO() {
        return genDTOBuilder.build();
    }

    public RenderDataDTO getRenderDataDTO() {
        return getGenDTO().getRenderDataDTO();
    }

    /**
     * 获取自定义生成器环境变量
     * @return
     */
    public Map getProperties() {
        return properties;
    }

    /**
     * 获取codegen工作根目录 (codegen.yaml文件和template目录所在目录
     * @return
     */
    public String getWorkDir() {
        return workDir;
    }

    //从配置文件解析数据源信息
    private void parseDataSource(Map datasourceMap){
        if(CollectionUtils.isEmpty(datasourceMap))
            throw new CodegenException("config.yaml配置datasource为空!");
        dataSource = new DataSource();
        Object dcn = datasourceMap.get("driverClassName");
        if(null == dcn || StringUtil.isEmpty((String) dcn))
            throw new CodegenException("config.yaml配置dataSource > driverClassName为空!");
        dataSource.setDriverClassName((String) dcn);

        Object durl = datasourceMap.get("url");
        if(null == durl || StringUtil.isEmpty((String) durl))
            throw new CodegenException("config.yaml配置dataSource > url为空!");
        dataSource.setUrl((String) durl);

        Object dusername = datasourceMap.get("username");
        if(null == dusername || StringUtil.isEmpty((String) dusername))
            throw new CodegenException("config.yaml配置dataSource > username为空!");
        dataSource.setUsername((String) dusername);

        Object dpassword = datasourceMap.get("password");
        if(null == dpassword || StringUtil.isEmpty((String) dpassword))
            throw new CodegenException("config.yaml配置dataSource > password为空!");
        dataSource.setPassword((String) datasourceMap.get("password"));
    }

    public void checkGenData( Connector connector) {
        RenderDataDTO renderDataDTO = new RenderDataDTO();
        renderDataDTO.setTableName(this.getTable());
        renderDataDTO.setTableRemark(connector.getTableRemark(this.getTable()));
        renderDataDTO.setEntityName(StringUtil.underline2Camel(this.getTable(), true));
        renderDataDTO.setEntityAliaName(StringUtil.underline2Camel(this.getTable(), false));
        renderDataDTO.setColumns(connector.getColumns(this.getTable()));
        genDTOBuilder.setRenderDataDTO(renderDataDTO);
    }

    public class DataSource{
        private String driverClassName;
        private String url;
        private String username;
        private String password;

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "DataSource{" +
                    "driverClassName='" + driverClassName + '\'' +
                    ", url='" + url + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    /**
     * 数据源
     */
    private DataSource dataSource;
    /**
     * 类型映射
     */
    private Map<String,Map<String,String>> mapper;
    /**
     * 要生成表
     */
    private String table;
    /**
     * 获取生成代码配置
     */
    private GenDTO.GenDTOBuilder genDTOBuilder = new GenDTO.GenDTOBuilder();
    /**
     * 自定义生成器环境变量
     */
    private Map properties;
    /**
     * codegen工作根目录 (codegen.yaml文件和template目录所在目录)
     */
    private String workDir;
}
