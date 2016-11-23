${copyright}
package ${modelPackage};

import com.letv.common.model.BaseModel;
import org.codehaus.jackson.map.annotate.JsonSerialize;
<#-- import定义 -->
<#if imports??&&imports?size gt 0>
    <#list imports as import>
${import}
    </#list>
</#if>

/**
 * ${modelDescription}
 * @author ${author} .
 * @since ${(.now?string(sice))!} .
 * @version ${version} .
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ${entityName} extends BaseModel {
    <#-- 属性定义 -->
    <#if columns??&&columns?size gt 0>
        <#list columns as column>
    /**
    * ${column.remark}
    */
    private ${column.javaType} ${column.javaObject};
        </#list>
    </#if>

    <#-- set/get方法定义 -->
    <#if columns??&&columns?size gt 0>
        <#list columns as column><#-- cap_first转换字符串首字母大写 -->
            <#assign types=column.javaType?split(".")>
    public ${types?last} get${column.javaObject?cap_first}() {
        return ${column.javaObject};
    }
    public void set${column.javaObject?cap_first}(${types?last} ${column.javaObject}) {
        this.${column.javaObject} = ${column.javaObject};
    }
        </#list>
    </#if>
}
