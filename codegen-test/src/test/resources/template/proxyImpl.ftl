${copyright}
package ${proxyImplPackage};

import ${modelPackage}.${entityName};
import ${proxyPackage}.${proxyName};
import com.letv.portal.service.IBaseService;
import ${servicePackage}.${serviceName};
import org.springframework.beans.factory.annotation.Autowired;

/**
* ${proxyImplDescription}
* @author ${author} .
* @since ${(.now?string(sice))!} .
* @version ${version} .
*/
@Component("${componentIOCName}")
public class ${proxyImplName} extends BaseProxyImpl<${entityName}> implements IDbSqlProxy {

    private final static Logger logger = LoggerFactory.getLogger(${proxyImplName}.class);

    @Autowired
    private ${serviceName} ${serviceEnitityName};

    @Override
    public IBaseService<${entityName}> getService() {
        return ${serviceEnitityName};
    }
}