#codgen介绍


#codegen.yaml配置参数介绍
 
1. 数据源配置 `datasource` 

| 属性 | 名称 | 数据类型 | 是否必须 | 举例 |
|:----------:|:----------:|:----------:|:----------:|
|     driverClassName       |      数据库驱动      |  String |     是     |  com.mysql.jdbc.Driver          |
|      url      |      数据库地址      |  String |    是      |          |
|       username     |       数据库用户名     |    String | 是       |            |
|      password      |      数据库密码      |   String |    是     |            ||

2.要生成代码表 `tables` 

| 属性 | 名称 | 数据类型 | 是否必须 | 举例 |
|:----------:|:----------:|:----------:|:----------:|
|       tables     |      表列表      |       String[]     |      是      |||

3.生成代码配置

```

```
 


>fff



 username: webportal password: "webportal" # 要生成的表 tables: - WEBPORTAL_DB_SQL # 生成文件位置 # 支持两种路径类型 # 1.绝对路径 eg: e:/workspace/test/src # 2.相对路径 eg:./src 假如工程在e:/workspace/test下，则生成器会自动处理成e:/workspace/test/src。注意:"."代表当前工程路径 # 生成代码配置 gen: # 生成包名 package: com.lynzabo.testmybatis # 如下几个模块，填写哪块，生成哪块 # mapper.xml信息 sqlMapper: # 生成位置 可选 默认{projectPath}/src/main/resources/mybatis location: ./src/main/resources/mybatis # 自定义名称 可选 默认I{table驼峰命名}Mapper name: IUserMapper # 使用模板 ftl: sqlMapper.ftl # 介绍 可选 默认为无 如指定，则生成介绍 description: "USER sql映射" # model信息 model: # 生成位置 可选 默认{projectPath}/src/main/java location: ./src/main/java # 生成包名称 可选 默认{package}.model package: com.lynzabo.testmybatis.model # 生成类名 可选 默认{table驼峰命名}Model name: UserModel # 使用模板 ftl: model.ftl # 介绍 可选 默认为{table表名注释} 实体 如果表名注释为“用户管理”，则此为用户管理 实体 description: "用户实体" # dao信息 dao: # 生成位置 可选 默认{projectPath}/src/main/java location: ./src/main/java # 生成包名称 可选 默认{package}.dao package: com.lynzabo.testmybatis.dao # 生成接口名 可选 默认I{table驼峰命名}Dao name: IUserDao # 使用模板 ftl: dao.ftl # 介绍 可选 默认为{table表名注释} dao 如果表名注释为“用户管理”，则此为用户管理 dao description: "用户dao" # service信息 service: # 生成位置 可选 默认{projectPath}/src/main/java location: ./src/main/java # 生成包名称 可选 默认{package}.service package: com.lynzabo.testmybatis.service # 生成接口名 可选 默认I{table驼峰命名}Service name: IUserService # 使用模板 ftl: service.ftl # 介绍 可选 默认为{table表名注释} 服务接口 如果表名注释为“用户管理”，则此为用户管理 服务接口 description: "用户服务接口" # service实现信息 serviceImpl: # 生成位置 可选 默认{projectPath}/src/main/java location: ./src/main/java # 生成包名称 可选 默认{package}.service.impl package: com.lynzabo.testmybatis.service.impl # 生成类名 可选 默认{table驼峰命名}ServiceImpl name: UserServiceImpl # Spring 服务层服务注解名称 可选 默认{table驼峰命名，首字母小写}Service 效果@Service("userService") serviceIOCName: userService # 使用模板 ftl: serviceImpl.ftl # 介绍 可选 默认为{table表名注释} 服务 如果表名注释为“用户管理”，则此为用户管理 服务 description: "用户服务" # controller信息 controller: # 生成位置 可选 默认{projectPath}/src/main/java location: ./src/main/java # 生成包名称 可选 默认{package}.controller package: com.lynzabo.testmybatis.controller # 生成类名 可选 默认{table驼峰命名}Controller name: UserController # Spring Controller层注解名称 可选 默认为@Controller 如下制定为userController 则生成效果@Controller("userController") controllerIOCName: userController # SpringMVC Controller层注解名称 可选 默认为{table驼峰命名} 效果@RequestMapping("/user") requestMapping: user # 使用模板 ftl: controller.ftl # 介绍 可选 默认为{table表名注释} controller 如果表名注释为“用户管理”，则此为用户管理 controller description: "用户controller" # enumeration信息 enumeration: # 生成位置 可选 默认{projectPath}/src/main/java location: ./src/main/java # 生成包名称 可选 默认{package}.enumeration package: com.lynzabo.testmybatis.enumeration # 生成枚举名 可选 默认{table驼峰命名}Status name: UserStatus # 使用模板 ftl: enumeration.ftl # 介绍 可选 默认为{table表名注释} enumeration 如果表名注释为“用户管理”，则此为用户管理 enumeration description: "用户enumeration" # interface信息 TODO interface: location: ./src/main/java name: UserStatus # 使用模板 ftl: enumeration.ftl description: "用户enumeration" # 生成md需要的基本接口文本 # controller 支持用@Valid，根据数据库定义非空，长度判断

#codgen输出变量介绍