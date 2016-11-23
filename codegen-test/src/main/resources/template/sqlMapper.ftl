<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${daoPackage}.${daoName}" >
    <#-- 模板常量定义 -->
    <#assign alia=tableAlia!"md"/>
    <#assign  cols=columnsMap?keys/>
    <resultMap id="BaseResultMap" type="${entityName}" >
        <#list cols as col>
            <#assign  column=columnsMap["${col}"]/>
            <#if column.isPK?string("true","false") == 'true'>
        <id column="${col}" property="${column.javaObject}" jdbcType="${column.jdbcType}" />
            <#else>
        <result column="${col}" property="${column.javaObject}" jdbcType="${column.jdbcType}" <#if column.jdbcType=='TINYINT'>typeHandler="com.letv.portal.util.mybatis.type.IntValueEnumTypeHandler"</#if>/>
            </#if>
        </#list>
    </resultMap>
    <sql id="Base_Column_List" >
        <#list cols as col>
            <#-- 如果tableAlia为空，则使用md作为默认表的别名 -->
        ${alia}.${col}<#if col_index < cols?size-1>,</#if>
        </#list>
    </sql>
    <sql id="Base_Where_Clause">
        where 1=1
        <trim suffixOverrides=",">
            <#list cols as col>
                <#assign  column=columnsMap["${col}"]/>
                <#switch column.jdbcType>
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
    <sql id="Page_Where_Clause">
        where 1=1
        <trim suffixOverrides=",">
            <#list cols as col>
                <#assign  column=columnsMap["${col}"]/>
                <#switch column.jdbcType>
                    <#case 'TINYINT'>
            <if test="params != null and ${"params."+column.javaObject} != null">
                and ${alia+"."+col}=#${"{params."+column.javaObject},jdbcType=TINYINT,typeHandler=com.letv.portal.util.mybatis.type.IntValueEnumTypeHandler}
            </if>
                        <#break>
                    <#case 'VARCHAR'>
            <if test="params != null and ${"params."+column.javaObject} != null and ${"params."+column.javaObject} != ''  ">
                and ${alia+"."+col}=#${"{params."+column.javaObject}}
            </if>
                        <#break>
                    <#default>
            <if test="params != null and ${"params."+column.javaObject} != null  ">
                and ${alia+"."+col}=#${"{params."+column.javaObject}}
            </if>
                </#switch>
            </#list>
        </trim>
    </sql>
    <!-- 列表总数 -->
    <select id="selectByMapCount" resultType="java.lang.Integer" parameterType="com.letv.common.dao.QueryParam">
        select
        count(1)
        from
        ${table} ${alia}
        <include refid="Page_Where_Clause" />
    </select>
    <!-- 分页：根据条件查询 -->
    <select id="selectPageByMap" resultMap="BaseResultMap" parameterType="com.letv.common.dao.QueryParam">
        select
        <include refid="Base_Column_List" />
        from
        ${table} ${alia}
        <include refid="Page_Where_Clause" />
        ORDER BY ${alia}.CREATE_TIME DESC
        <if test="page != null">
            limit ${r'#{page.startRowPosition}'},${r'#{page.recordsPerPage}'}
        </if>
    </select>
    <select id="selectById" resultMap="BaseResultMap" parameterType="java.lang.Long" >
        select
        <include refid="Base_Column_List" />
        from
        ${table} ${alia}
        where ${alia}.ID = ${r'#{id,jdbcType=BIGINT}'}
    </select>
    <select id="selectByMap" resultMap="BaseResultMap" parameterType="java.util.Map" >
        select
        <include refid="Base_Column_List" />
        from
        ${table} ${alia}
        <include refid="Base_Where_Clause" />
    </select>
    <select id="selectCountByMap" resultType="java.lang.Integer" parameterType="java.util.Map">
        select
        count(1)
        from
        ${table} ${alia}
        <include refid="Base_Where_Clause" />
    </select>
    <delete id="delete" parameterType="${entityName}" >
        delete from ${table}
        where ID = ${r'#{id,jdbcType=BIGINT}'}
    </delete>
    <insert id="insert" parameterType="${entityName}" useGeneratedKeys="true" keyProperty="id">
        insert into ${table}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list cols as col>
                <#assign  column=columnsMap["${col}"]/>
                <#switch column.jdbcType>
                    <#case 'TINYINT'>
            <if test="${column.javaObject} != null">
                `${col}`,
            </if>
                        <#break>
                    <#case 'VARCHAR'>
            <if test="${column.javaObject} != null and ${column.javaObject} != ''">
                `${col}`,
            </if>
                        <#break>
                    <#case 'BIGINT'>
                        <#if column.javaObject == 'updateUser'>
                        <#elseif column.javaObject == 'createUser'>
            `${col}`,
                        <#else>
            <if test="${column.javaObject} != null">
                `${col}`,
            </if>
                        </#if>
                        <#break>
                    <#case 'DATETIME'>
                        <#if col=="CREATE_TIME">
            `${col}`,
                        <#elseif col="UPDATE_TIME">
                        <#else>
            <if test="${column.javaObject} != null">
                `${col}`,
            </if>
                        </#if>
                        <#break>
                    <#default>
            <if test="${column.javaObject} != null  ">
                `${col}`,
            </if>
                </#switch>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list cols as col>
                <#assign  column=columnsMap["${col}"]/>
                <#switch column.jdbcType>
                    <#case 'TINYINT'>
            <if test="${column.javaObject} != null">
                ${"#{"+column.javaObject+"},"}
            </if>
                        <#break>
                    <#case 'VARCHAR'>
            <if test="${column.javaObject} != null and ${column.javaObject} != ''">
                ${"#{"+column.javaObject+"},"}
            </if>
                        <#break>
                    <#case 'BIGINT'>
                        <#if column.javaObject == 'updateUser'>
                        <#elseif column.javaObject == 'createUser'>
            ${"#{"+column.javaObject+",jdbcType=BIGINT,typeHandler=com.letv.portal.util.mybatis.type.UserTypeHandler},"}
                        <#else>
            <if test="${column.javaObject} != null">
                ${"#{"+column.javaObject+"},"}
            </if>
                        </#if>
                        <#break>
                    <#case 'DATETIME'>
                        <#if col=="CREATE_TIME">
            now(),
                        <#elseif col=="UPDATE_TIME">
                        <#else>
            <if test="${column.javaObject} != null  ">
                ${"#{"+column.javaObject+"},"}
            </if>
                        </#if>
                        <#break>
                    <#default>
            <if test="${column.javaObject} != null  ">
                ${"#{"+column.javaObject+"},"}
            </if>
                </#switch>
            </#list>
        </trim>
    </insert>
    <#--<insert id="insertBatch" parameterType="java.util.List" useGeneratedKeys="true" keyProperty="id">
        insert into ${table}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list cols as col>
                <#assign  column=columnsMap["${col}"]/>
                <#switch column.jdbcType>
                    <#case 'TINYINT'>
            <if test="${column.javaObject} != null">
                `${col}`,
            </if>
                        <#break>
                    <#case 'VARCHAR'>
            <if test="${column.javaObject} != null and ${column.javaObject} != ''">
                `${col}`,
            </if>
                        <#break>
                    <#case 'BIGINT'>
                        <#if column.javaObject == 'updateUser'>
                        <#elseif column.javaObject == 'createUser'>
            `${col}`,
                        <#else>
            <if test="${column.javaObject} != null">
                `${col}`,
            </if>
                        </#if>
                        <#break>
                    <#case 'DATETIME'>
                        <#if col=="CREATE_TIME">
            `${col}`,
                        <#elseif col="UPDATE_TIME">
                        <#else>
            <if test="${column.javaObject} != null">
                `${col}`,
            </if>
                        </#if>
                        <#break>
                    <#default>
            <if test="${column.javaObject} != null  ">
                `${col}`,
            </if>
                </#switch>
            </#list>
        </trim>
        values
        <foreach collection="list" item="item" index="index"
                 separator=",">
            <trim prefix="values (" suffix=")" suffixOverrides=",">
                <#list cols as col>
                    <#assign  column=columnsMap["${col}"]/>
                    <#switch column.jdbcType>
                        <#case 'TINYINT'>
                <if test="${column.javaObject} != null">
                    ${"#{"+column.javaObject+"},"}
                </if>
                            <#break>
                        <#case 'VARCHAR'>
                <if test="${column.javaObject} != null and ${column.javaObject} != ''">
                    ${"#{"+column.javaObject+"},"}
                </if>
                            <#break>
                        <#case 'BIGINT'>
                            <#if column.javaObject == 'updateUser'>
                            <#elseif column.javaObject == 'createUser'>
                ${"#{"+column.javaObject+",jdbcType=BIGINT,typeHandler=com.letv.portal.util.mybatis.type.UserTypeHandler},"}
                            <#else>
                <if test="${column.javaObject} != null">
                    ${"#{"+column.javaObject+"},"}
                </if>
                            </#if>
                            <#break>
                        <#case 'DATETIME'>
                            <#if col=="CREATE_TIME">
                now(),
                            <#elseif col=="UPDATE_TIME">
                            <#else>
                <if test="${column.javaObject} != null  ">
                    ${"#{"+column.javaObject+"},"}
                </if>
                            </#if>
                            <#break>
                        <#default>
                <if test="${column.javaObject} != null  ">
                    ${"#{"+column.javaObject+"},"}
                </if>
                    </#switch>
                </#list>
            </trim>
        </foreach>
        <!-- 获取上个刚插入的自增ID值 &ndash;&gt;
        <selectKey resultType="java.lang.Long" order="AFTER" keyProperty="id">
            SELECT LAST_INSERT_ID() AS id
        </selectKey>
    </insert>-->

    <update id="update" parameterType="${entityName}">
        update ${table} ${alia}
        <trim prefix="set" suffixOverrides="," suffix="where ${alia}.${r'ID = #{id,jdbcType=BIGINT}'}" >
            <#list cols as col>
                <#assign  column=columnsMap["${col}"]/>
                <#switch column.jdbcType>
                    <#case 'TINYINT'>
            <if test="${column.javaObject} != null">
                ${alia+"."+col}=#${"{"+column.javaObject},jdbcType=TINYINT,typeHandler=com.letv.portal.util.mybatis.type.IntValueEnumTypeHandler},
            </if>
                        <#break>
                    <#case 'VARCHAR'>
            <if test="${column.javaObject} != null and ${column.javaObject} != ''">
                ${alia+"."+col}=#${"{"+column.javaObject}},
            </if>
                        <#break>
                    <#case 'BIGINT'>
                        <#if column.javaObject == 'createUser'>
                        <#elseif column.javaObject == 'updateUser'>
            ${alia+"."+col}=${"#{"+column.javaObject+",jdbcType=BIGINT,typeHandler=com.letv.portal.util.mybatis.type.UserTypeHandler},"}
                        <#else>
            <if test="${column.javaObject} != null">
                ${alia+"."+col}=${"#{"+column.javaObject+"},"}
            </if>
                        </#if>
                        <#break>
                    <#case 'DATETIME'>
                        <#if col=="UPDATE_TIME">
            ${alia+"."+col}=now(),
                        <#elseif col=="CREATE_TIME">
                        <#else>
            <if test="${column.javaObject} != null  ">
                ${alia+"."+col}=${"#{"+column.javaObject+"},"}
            </if>
                        </#if>
                        <#break>
                    <#default>
            <if test="${column.javaObject} != null  ">
                ${alia+"."+col}=${"#{"+column.javaObject+"},"}
            </if>
                </#switch>
            </#list>
        </trim>
    </update>
</mapper>