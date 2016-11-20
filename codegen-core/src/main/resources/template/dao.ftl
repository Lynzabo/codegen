${copyright}
package com.letv.portal.dao.sql;

import com.letv.common.dao.IBaseDao;
import com.letv.portal.model.sql.DbSqlModel;

import java.util.Map;

/**
* IDbSqlDao ${var1}
* @author ${author} .
* @since 2016年11月08日, 15:31 .
* @version 1.0 .
*/
public interface IDbSqlDao extends IBaseDao<DbSqlModel> {
    Integer selectCountByMap(Map<String, Object> params);

    DbSqlModel selectBySqlId(Long sqlId);
}