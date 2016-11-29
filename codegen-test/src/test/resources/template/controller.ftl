${copyright}
package ${controllerPackage};

import com.letv.common.exception.ValidateException;
import com.letv.common.paging.impl.Page;
import com.letv.common.result.ResultObject;
import com.letv.common.session.SessionServiceImpl;
import com.letv.common.util.HttpUtil;
import ${modelPackage}.${entityName};
import ${proxyPackage}.${proxyName};
import ${servicePackage}.${serviceName};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
* ${controllerDescription}
* @author ${author} .
* @since ${(.now?string(sice))!} .
* @version ${version} .
*/
@Controller<#if controllerIOCName != "">(\"$controllerIOCName\")</#if>
@RequestMapping("/${requestMapping}")
public class ${controllerName} {
    private final static Logger logger = LoggerFactory.getLogger(${controllerName}.class);

    @Autowired(required = false)
    private SessionServiceImpl sessionService;
    @Autowired
    private ${serviceName} ${serviceEnitityName};
    @Autowired
    private ${proxyName} ${proxyEntityName};

    /**
     * ${controllerDescription}列表
     * @param page
     * @param request
     * @param resultObject
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResultObject list(Page page,HttpServletRequest request, ResultObject resultObject) {
        Map<String, Object> params = HttpUtil.requestParam2Map(request);
        params.put("createUser", sessionService.getSession().getUserId());
        /*String title = (String) params.get("title");
        if (!StringUtils.isEmpty(title))
            params.put("title", StringUtil.transSqlCharacter(title));*/
        resultObject.setData(this.${serviceEnitityName}.queryByPagination(page,params));
        return resultObject;
    }

    /**
     * ${controllerDescription}详情查看
     * @param id
     * @param resultObject
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody ResultObject detail(@PathVariable("id") Long id,ResultObject resultObject) {
        if(null == id)
            throw new ValidateException("参数不能为空！");
        resultObject.setData(this.dbSqlService.selectById(id));
        return resultObject;
    }

    /**
     * ${controllerDescription}保存
     * @param ${entityName?uncap_first}
     * @param bindResult
     * @param resultObject
     * @return
     */
    @RequestMapping(value = "/{type}",method = RequestMethod.POST)
    public @ResponseBody ResultObject insert(@Valid ${entityName} ${entityName?uncap_first},BindingResult bindResult,ResultObject resultObject) {
        /*if(StringUtils.isEmpty(cacheId)){
            resultObject.setResult(0);
            resultObject.addMsg("输入SQL为空！");
            return resultObject;
        }*/
        if (bindResult.hasErrors()) {
            return new ResultObject(bindResult.getAllErrors());
        }
        this.${serviceEnitityName}.insert(${entityName?uncap_first});
        return resultObject;
    }

    /**
     * ${controllerDescription}修改
     * @param ${entityName?uncap_first}
     * @param bindResult
     * @param resultObject
     * @return
     */
    @RequestMapping(value = "/{type}",method = RequestMethod.PUT)
    public @ResponseBody ResultObject update(@Valid ${entityName} ${entityName?uncap_first},BindingResult bindResult,ResultObject resultObject) {
        /*if(StringUtils.isEmpty(cacheId)){
            resultObject.setResult(0);
            resultObject.addMsg("输入SQL为空！");
            return resultObject;
        }*/
        if (bindResult.hasErrors()) {
            return new ResultObject(bindResult.getAllErrors());
        }
        this.${serviceEnitityName}.update(${entityName?uncap_first});
        return resultObject;
    }

    /**
     * ${controllerDescription}删除
     * @param id
     * @param resultObject
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public @ResponseBody ResultObject delete(@PathVariable("id") Long id,ResultObject resultObject) {
        if(null == id)
            throw new ValidateException("参数不能为空！");
        ${entityName} ${entityName?uncap_first} = this.${serviceEnitityName}.selectById(id);
        if(null == ${entityName?uncap_first})
            throw new ValidateException("数据不存在！");
        this.${serviceEnitityName}.delete(${entityName?uncap_first});
        return resultObject;
    }

}
