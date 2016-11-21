<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${daoPackage}.${daoName}" >
    <#-- 模板常量定义 -->
    <#assign alia=tableAlia!"md"/>
    <resultMap id="BaseResultMap" type="${entityName}" >
        <#list columnsMap?keys as col>
            <#assign  column=columnsMap["${col}"]/>
            <#if column.isPK?string("true","false") == 'true'>
        <id column="${col}" property="${column.javaObject}" jdbcType="${column.dbType}" />
            <#else>
        <result column="${col}" property="${column.javaObject}" jdbcType="${column.dbType}" <#if column.dbType=='TINYINT'>typeHandler="com.letv.portal.util.mybatis.type.IntValueEnumTypeHandler"</#if>/>
            </#if>
        </#list>
    </resultMap>
    <sql id="Base_Column_List" >
    <#assign  cols=columnsMap?keys/>
        <#list cols as col>
            <#-- 如果tableAlia为空，则使用md作为默认表的别名 -->
        ${alia}.${col}<#if col_index < cols?size-1>,</#if>
        </#list>
    </sql>
    <sql id="Base_Where_Clause">
        where 1=1
        <trim suffixOverrides=",">
            <#list columnsMap?keys as col>
                <#assign  column=columnsMap["${col}"]/>
                <#switch column.dbType>
                    <#case 'TINYINT'>
            <if test="${column.javaObject} != null">
                and ${alia+"."+col}=#${"{"+column.javaObject},jdbcType=TINYINT,typeHandler=com.letv.portal.util.mybatis.type.IntValueEnumTypeHandler}
            </if>
                    <#break>
                    <#case 'VARCHAR'>
            <if test="${column.javaObject} != null and ${column.javaObject} != ''  ">
                and ${alia+"."+col}=#${"{"+column.javaObject}}
            </if>
                    <#break>
                    <#default>
            <if test="${column.javaObject} != null  ">
                and ${alia+"."+col}=#${"{"+column.javaObject}}
            </if>
                </#switch>
            </#list>
        </trim>
    </sql>
</mapper>