${copyright}
package ${serviceImplPackage};

import com.letv.common.dao.IBaseDao;
import ${daoPackage}.${daoName};
import ${modelPackage}.${entityName};
import com.letv.portal.service.impl.BaseServiceImpl;
import ${servicePackage}.${serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
* ${serviceImplDescription}
* @author ${author} .
* @since ${(.now?string(sice))!} .
* @version ${version} .
*/
@Service("${serviceIOCName}")
public class ${serviceImplName} extends BaseServiceImpl<${entityName}> implements ${serviceName} {

    private final static Logger logger = LoggerFactory.getLogger(${serviceImplName}.class);

    @Autowired
    private ${daoName} ${daoEnitityName};

    public ${serviceImplName}() {
        super(${entityName}.class);
    }

    @Override
    public IBaseDao<${entityName}> getDao() {
        return ${daoEnitityName};
    }

}
