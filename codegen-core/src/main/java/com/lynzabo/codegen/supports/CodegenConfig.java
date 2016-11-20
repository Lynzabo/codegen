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

import java.io.FileInputStream;
import java.net.URL;
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
    public void initConfig() {
        logger.debug("init config!");
        try {
            Yaml yaml = new Yaml();
            URL url = CodegenConfig.class.getClassLoader().getResource("config.yaml");
            if (url != null) {
                Map map = (Map) yaml.load(new FileInputStream(url.getFile()));

                Map datasourceMap = (Map) map.get("datasource");
                parseDataSource(datasourceMap);

                String tab = (String) map.get("table");
                parseTable(tab);

                Map genMap = (Map) map.get("gen");
                parseGen(genMap);

                Map propertiesMap = (Map) map.get("properties");
                parseProperties(propertiesMap);
            }
            logger.debug("init success!");
        } catch (Exception ex) {
            logger.error("initialize codegen error",ex);
        }
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
                            "":(String)sqlMapperMap.get("location")
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
            Map modelPropertiesMap = (Map) sqlMapperMap.get("properties");
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
            Map daoPropertiesMap = (Map) sqlMapperMap.get("properties");
            if(!CollectionUtils.isEmpty(daoPropertiesMap))
                daoDTOBuilder.setProperties(daoPropertiesMap);
            genDTOBuilder.setDaoDTO(daoDTOBuilder.build());
        }
        //TODO service
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

    //从配置文件解析数据源信息
    private void parseDataSource(Map datasourceMap){
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
}
