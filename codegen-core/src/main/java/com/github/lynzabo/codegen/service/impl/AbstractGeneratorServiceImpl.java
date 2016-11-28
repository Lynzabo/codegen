/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.github.lynzabo.codegen.service.impl;

import com.github.lynzabo.codegen.model.*;
import com.github.lynzabo.codegen.service.Generator;
import com.github.lynzabo.codegen.supports.CodegenConfig;
import com.github.lynzabo.codegen.util.StringUtil;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Generator核心类
 * @author linzhanbo .
 * @since 2016年11月21日, 10:46 .
 * @version 1.0 .
 */
@Service("generatorService")
public abstract class AbstractGeneratorServiceImpl implements Generator {

    /**
     * 获取sqlMapper location
     * @return
     */
    protected String getSqlMapperLocation(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        SqlMapperDTO sqlMapperDTO = genDTO.getSqlMapperDTO();
        return StringUtil.isEmpty(sqlMapperDTO.getLocation())?"src/main/resources/mybatis" : sqlMapperDTO.getLocation();
    }
    /**
     * 获取SqlMapper名称
     * @return
     */
    protected String getSqlMapperName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        SqlMapperDTO sqlMapperDTO = genDTO.getSqlMapperDTO();
        if(null == sqlMapperDTO || StringUtil.isEmpty(sqlMapperDTO.getName())){
            return MessageFormat.format("I{0}Mapper", getKeywordName());
        }
        return sqlMapperDTO.getName();
    }
    /**
     * 获取Model的Package
     * @return
     */
    protected String getModelPackage(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ModelDTO modelDTO = genDTO.getModelDTO();
        if(null == modelDTO || StringUtil.isEmpty(modelDTO.getMpackage()))
            return genDTO.getMpackage()+".model";
        return modelDTO.getMpackage();
    }
    /**
     * 获取Model名称
     * @return
     */
    protected String getEntityName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ModelDTO modelDTO = genDTO.getModelDTO();
        if(null == modelDTO || StringUtil.isEmpty(modelDTO.getName())){
            RenderDataDTO renderDataDTO = genDTO.getRenderDataDTO();
            return renderDataDTO.getEntityName()+"Model";
        }
        return modelDTO.getName();
    }

    /**
     * 获取生成代码关键名称   和Model相比，不带默认Model
     * @return
     */
    protected String getKeywordName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ModelDTO modelDTO = genDTO.getModelDTO();
        if(null == modelDTO || StringUtil.isEmpty(modelDTO.getName())){
            RenderDataDTO renderDataDTO = genDTO.getRenderDataDTO();
            return renderDataDTO.getEntityName();
        }
        return modelDTO.getName();
    }
    /**
     * 获取Model location
     * @return
     */
    protected String getModelLocation(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ModelDTO modelDTO = genDTO.getModelDTO();
        return StringUtil.isEmpty(modelDTO.getLocation())?"src/main/java" : modelDTO.getLocation();
    }
    /**
     * 获取Model的description
     * @return
     */
    protected String getModelDescription(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ModelDTO modelDTO = genDTO.getModelDTO();
        String desc = "";
        if(StringUtil.isEmpty(modelDTO.getDescription())){
            RenderDataDTO renderDataDTO = genDTO.getRenderDataDTO();
            String tableRemark = renderDataDTO.getTableRemark();
            if(StringUtil.isEmpty(tableRemark)){
                String entityName = renderDataDTO.getEntityName();
                desc = MessageFormat.format("{0} 实体",entityName);
            }else{
                desc = MessageFormat.format("{0} 实体",tableRemark);
            }
        } else
            desc = modelDTO.getDescription();
        return desc;
    }

    /**
     * 获取Dao的Package
     * @return
     */
    protected String getDaoPackage(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        DaoDTO daoDTO = genDTO.getDaoDTO();
        if(null == daoDTO || StringUtil.isEmpty(daoDTO.getMpackage()))
            return genDTO.getMpackage()+".dao";
        return daoDTO.getMpackage();
    }
    /**
     * 获取Dao名称
     * @return
     */
    protected String getDaoName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        DaoDTO daoDTO = genDTO.getDaoDTO();
        if(null == daoDTO || StringUtil.isEmpty(daoDTO.getName())){
            return MessageFormat.format("I{0}Dao", getKeywordName());
        }
        return daoDTO.getName();
    }
    /**
     * 获取Dao location
     * @return
     */
    protected String getDaoLocation(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        DaoDTO daoDTO = genDTO.getDaoDTO();
        return StringUtil.isEmpty(daoDTO.getLocation())?"src/main/java" : daoDTO.getLocation();
    }
    /**
     * 获取Dao的description
     * @return
     */
    protected String getDaoDescription(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        DaoDTO daoDTO = genDTO.getDaoDTO();
        String desc = "";
        if(StringUtil.isEmpty(daoDTO.getDescription())){
            RenderDataDTO renderDataDTO = genDTO.getRenderDataDTO();
            String tableRemark = renderDataDTO.getTableRemark();
            if(StringUtil.isEmpty(tableRemark)){
                String entityName = renderDataDTO.getEntityName();
                desc = MessageFormat.format("{0} dao",entityName);
            }else{
                desc = MessageFormat.format("{0} dao",tableRemark);
            }
        } else
            desc = daoDTO.getDescription();
        return desc;
    }
    /**
     * 获取service的Package
     * @return
     */
    protected String getServicePackage(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ServiceDTO serviceDTO = genDTO.getServiceDTO();
        if(null == serviceDTO || StringUtil.isEmpty(serviceDTO.getMpackage()))
            return genDTO.getMpackage()+".service";
        return serviceDTO.getMpackage();
    }
    /**
     * 获取Service名称
     * @return
     */
    protected String getServiceName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ServiceDTO serviceDTO = genDTO.getServiceDTO();
        if(null == serviceDTO || StringUtil.isEmpty(serviceDTO.getName())){
            return MessageFormat.format("I{0}Service", getKeywordName());
        }
        return serviceDTO.getName();
    }
    /**
     * 获取service location
     * @return
     */
    protected String getServiceLocation(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ServiceDTO serviceDTO = genDTO.getServiceDTO();
        return StringUtil.isEmpty(serviceDTO.getLocation())?"src/main/java" : serviceDTO.getLocation();
    }
    /**
     * 获取service的description
     * @return
     */
    protected String getServiceDescription(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ServiceDTO serviceDTO = genDTO.getServiceDTO();
        String desc = "";
        if(StringUtil.isEmpty(serviceDTO.getDescription())){
            RenderDataDTO renderDataDTO = genDTO.getRenderDataDTO();
            String tableRemark = renderDataDTO.getTableRemark();
            if(StringUtil.isEmpty(tableRemark)){
                String entityName = renderDataDTO.getEntityName();
                desc = MessageFormat.format("{0} 服务接口",entityName);
            }else{
                desc = MessageFormat.format("{0} 服务接口",tableRemark);
            }
        } else
            desc = serviceDTO.getDescription();
        return desc;
    }

    /**
     * 获取ServiceImpl的Package
     * @return
     */
    protected String getServiceImplPackage(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ServiceImplDTO serviceImplDTO = genDTO.getServiceImplDTO();
        if(null == serviceImplDTO || StringUtil.isEmpty(serviceImplDTO.getMpackage()))
            return genDTO.getMpackage()+"service.impl";
        return serviceImplDTO.getMpackage();
    }
    /**
     * 获取ServiceImpl的description
     * @return
     */
    protected String getServiceImplDescription(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ServiceImplDTO serviceImplDTO = genDTO.getServiceImplDTO();
        String desc = "";
        if(StringUtil.isEmpty(serviceImplDTO.getDescription())){
            RenderDataDTO renderDataDTO = genDTO.getRenderDataDTO();
            String tableRemark = renderDataDTO.getTableRemark();
            if(StringUtil.isEmpty(tableRemark)){
                String entityName = renderDataDTO.getEntityName();
                desc = MessageFormat.format("{0} 服务",entityName);
            }else{
                desc = MessageFormat.format("{0} 服务",tableRemark);
            }
        } else
            desc = serviceImplDTO.getDescription();
        return desc;
    }
    /**
     * 获取ServiceImpl的serviceIOCName
     * @return
     */
    protected String getServiceIOCName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ServiceImplDTO serviceImplDTO = genDTO.getServiceImplDTO();
        if(null == serviceImplDTO || StringUtil.isEmpty(serviceImplDTO.getServiceIOCName())){
            return MessageFormat.format("{0}Service", StringUtil.firstUpper(getKeywordName()));
        }
        return serviceImplDTO.getServiceIOCName();
    }
    /**
     * 获取ServiceImpl名称
     * @return
     */
    protected String getServiceImplName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ServiceImplDTO serviceImplDTO = genDTO.getServiceImplDTO();
        if(null == serviceImplDTO || StringUtil.isEmpty(serviceImplDTO.getName())){
            return MessageFormat.format("{0}ServiceImpl", getKeywordName());
        }
        return serviceImplDTO.getName();
    }
    /**
     * 获取ServiceImpl location
     * @return
     */
    protected String getServiceImplLocation(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ServiceImplDTO serviceImplDTO = genDTO.getServiceImplDTO();
        return StringUtil.isEmpty(serviceImplDTO.getLocation())?"src/main/java" : serviceImplDTO.getLocation();
    }
    /**
     * 获取proxy的Package
     * @return
     */
    protected String getProxyPackage(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ProxyDTO proxyDTO = genDTO.getProxyDTO();
        if(null == proxyDTO || StringUtil.isEmpty(proxyDTO.getMpackage()))
            return genDTO.getMpackage()+".proxy";
        return proxyDTO.getMpackage();
    }
    /**
     * 获取proxy名称
     * @return
     */
    protected String getProxyName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ProxyDTO proxyDTO = genDTO.getProxyDTO();
        if(null == proxyDTO || StringUtil.isEmpty(proxyDTO.getName())){
            return MessageFormat.format("I{0}Proxy", getKeywordName());
        }
        return proxyDTO.getName();
    }
    /**
     * 获取proxy location
     * @return
     */
    protected String getProxyLocation(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ProxyDTO proxyDTO = genDTO.getProxyDTO();
        return StringUtil.isEmpty(proxyDTO.getLocation())?"src/main/java" : proxyDTO.getLocation();
    }
    /**
     * 获取proxy的description
     * @return
     */
    protected String getProxyDescription(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ProxyDTO proxyDTO = genDTO.getProxyDTO();
        String desc = "";
        if(StringUtil.isEmpty(proxyDTO.getDescription())){
            RenderDataDTO renderDataDTO = genDTO.getRenderDataDTO();
            String tableRemark = renderDataDTO.getTableRemark();
            if(StringUtil.isEmpty(tableRemark)){
                String entityName = renderDataDTO.getEntityName();
                desc = MessageFormat.format("{0} 代理接口",entityName);
            }else{
                desc = MessageFormat.format("{0} 代理接口",tableRemark);
            }
        } else
            desc = proxyDTO.getDescription();
        return desc;
    }
    /**
     * 获取proxyImpl的Package
     * @return
     */
    protected String getProxyImplPackage(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ProxyImplDTO proxyImplDTO = genDTO.getProxyImplDTO();
        if(null == proxyImplDTO || StringUtil.isEmpty(proxyImplDTO.getMpackage()))
            return genDTO.getMpackage()+"proxy.impl";
        return proxyImplDTO.getMpackage();
    }
    /**
     * 获取ProxyImpl的description
     * @return
     */
    protected String getProxyImplDescription(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ProxyImplDTO proxyImplDTO = genDTO.getProxyImplDTO();
        String desc = "";
        if(StringUtil.isEmpty(proxyImplDTO.getDescription())){
            RenderDataDTO renderDataDTO = genDTO.getRenderDataDTO();
            String tableRemark = renderDataDTO.getTableRemark();
            if(StringUtil.isEmpty(tableRemark)){
                String entityName = renderDataDTO.getEntityName();
                desc = MessageFormat.format("{0} 代理",entityName);
            }else{
                desc = MessageFormat.format("{0} 代理",tableRemark);
            }
        } else
            desc = proxyImplDTO.getDescription();
        return desc;
    }
    /**
     * 获取ProxyImpl的proxyIOCName
     * @return
     */
    protected String getProxyIOCName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ProxyImplDTO proxyImplDTO = genDTO.getProxyImplDTO();
        if(null == proxyImplDTO || StringUtil.isEmpty(proxyImplDTO.getComponentIOCName())){
            return MessageFormat.format("{0}Proxy", StringUtil.firstUpper(getKeywordName()));
        }
        return proxyImplDTO.getComponentIOCName();
    }
    /**
     * 获取ProxyImpl名称
     * @return
     */
    protected String getProxyImplName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ProxyImplDTO proxyImplDTO = genDTO.getProxyImplDTO();
        if(null == proxyImplDTO || StringUtil.isEmpty(proxyImplDTO.getName())){
            return MessageFormat.format("{0}ProxyImpl", getKeywordName());
        }
        return proxyImplDTO.getName();
    }
    /**
     * 获取ProxyImpl location
     * @return
     */
    protected String getProxyImplLocation(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ProxyImplDTO proxyImplDTO = genDTO.getProxyImplDTO();
        return StringUtil.isEmpty(proxyImplDTO.getLocation())?"src/main/java" : proxyImplDTO.getLocation();
    }
    /**
     * 获取controller的Package
     * @return
     */
    protected String getControllerPackage(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ControllerDTO controllerDTO = genDTO.getControllerDTO();
        if(null == controllerDTO || StringUtil.isEmpty(controllerDTO.getMpackage()))
            return genDTO.getMpackage()+".controller";
        return controllerDTO.getMpackage();
    }
    /**
     * 获取controller的description
     * @return
     */
    protected String getControllerDescription(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ControllerDTO controllerDTO = genDTO.getControllerDTO();
        String desc = "";
        if(StringUtil.isEmpty(controllerDTO.getDescription())){
            RenderDataDTO renderDataDTO = genDTO.getRenderDataDTO();
            String tableRemark = renderDataDTO.getTableRemark();
            if(StringUtil.isEmpty(tableRemark)){
                String entityName = renderDataDTO.getEntityName();
                desc = MessageFormat.format("{0} Controller",entityName);
            }else{
                desc = MessageFormat.format("{0} Controller",tableRemark);
            }
        } else
            desc = controllerDTO.getDescription();
        return desc;
    }

    /**
     * 获取Controller的controllerIOCName
     * @return
     */
    protected String getControllerIOCName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ControllerDTO controllerDTO = genDTO.getControllerDTO();
        if(null == controllerDTO || StringUtil.isEmpty(controllerDTO.getControllerIOCName())){
            return "";
        }
        return controllerDTO.getControllerIOCName();
    }

    /**
     * 获取Controller的request mapper
     * @return
     */
    protected String getControllerRequestMapping(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ControllerDTO controllerDTO = genDTO.getControllerDTO();
        if(null == controllerDTO || StringUtil.isEmpty(controllerDTO.getRequestMapping())){
            return getKeywordName();
        }
        return controllerDTO.getRequestMapping();
    }

    /**
     * 获取controller名称
     * @return
     */
    protected String getControllerName(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ControllerDTO controllerDTO = genDTO.getControllerDTO();
        if(null == controllerDTO || StringUtil.isEmpty(controllerDTO.getName())){
            return MessageFormat.format("{0}Controller", getKeywordName());
        }
        return controllerDTO.getName();
    }

    /**
     * 获取Controller location
     * @return
     */
    protected String getControllerLocation(){
        GenDTO genDTO = CodegenConfig.getInstance().getGenDTO();
        ControllerDTO controllerDTO = genDTO.getControllerDTO();
        return StringUtil.isEmpty(controllerDTO.getLocation())?"src/main/java" : controllerDTO.getLocation();
    }
    public abstract void render();
}
