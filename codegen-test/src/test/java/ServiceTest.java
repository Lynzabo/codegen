/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */

/**
 *
 * @author linzhanbo .
 * @since 2016年11月23日, 9:38 .
 * @version 1.0 .
 */
public class ServiceTest {
    //@Autowired
    //private ISqlDumpService sqlDumpService;
    public void test(){
        //查询
        //分页查询
        /*Page page = new Page();
        page.setCurrentPage(1);
        page.setRecordsPerPage(10);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("mVarchar","111");
        Page page1 = sqlDumpService.queryByPagination(page, params);*/
        //使用ID查询
        //SqlDumpModel sqlDumpModel = sqlDumpService.selectById(1l);
        //使用参数查询
        /*Map<String,Object> params = new HashMap<String,Object>();
        params.put("mVarchar", "111");
        List<SqlDumpModel> sqlDumpModels = sqlDumpService.selectByMap(params);*/
        //使用参数查询条数
        /*Map<String,Object> params2 = new HashMap<String,Object>();
        params2.put("mVarchar", "111");
        Integer count = sqlDumpService.selectCountByMap(params2);*/
        //插入
        /*SqlDumpModel sqlDumpModel = new SqlDumpModel();
        sqlDumpModel.setMBinary("binary".getBytes());
        sqlDumpModel.setMBit(true);
        sqlDumpModel.setMBlob("Blob".getBytes());
        sqlDumpModel.setMBool(true);
        sqlDumpModel.setMBoolean(true);
        sqlDumpModel.setMChar("c");
        sqlDumpModel.setMDate(new Date());
        sqlDumpModel.setMDatetime(new Date());
        sqlDumpModel.setMqDecimal(100l);
        sqlDumpModel.setMDouble(10.12);
        sqlDumpModel.setMEnum("111");
        sqlDumpModel.setMFloat(12.5F);
        sqlDumpModel.setMInt(10);
        sqlDumpModel.setMLongblob("longblob".getBytes());
        sqlDumpModel.setMLongtext("longtext");
        sqlDumpModel.setMMediumblob("mediumblob".getBytes());
        sqlDumpModel.setMMediumint(120);
        sqlDumpModel.setMMediumtext("mediumtext");
        sqlDumpModel.setMDecimal(10l);
        sqlDumpModel.setMDouble(12.546);
        sqlDumpModel.setMSet("SSS");//SSS、AAA
        sqlDumpModel.setMSmallint((short) 12);
        sqlDumpModel.setMText("text");
        sqlDumpModel.setMTime(new Date());
        sqlDumpModel.setMTimestamp(new Date());
        sqlDumpModel.setMTinyblob("tinyblob".getBytes());
        sqlDumpModel.setMTinyint((byte) 1);
        sqlDumpModel.setMTinytext("tinytext");
        sqlDumpModel.setMVarbinary("Varbinary".getBytes());
        sqlDumpModel.setMVarchar("varchar");
        sqlDumpService.insert(sqlDumpModel);*/
        //update
        /*SqlDumpModel sqlDumpModel = new SqlDumpModel();
        sqlDumpModel = sqlDumpService.selectById((long) 14);
        sqlDumpModel.setMVarchar("varchar2");
        sqlDumpService.update(sqlDumpModel);*/
        //delete
        /*SqlDumpModel sqlDumpModel = new SqlDumpModel();
        sqlDumpModel = sqlDumpService.selectById((long) 14);
        sqlDumpService.delete(sqlDumpModel);*/
    }
}
