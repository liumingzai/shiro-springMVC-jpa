#数据库连接池采用druid，对密码有加密的作用，需要在cmd环境进行加密
#druid加密
java -cp druid-1.0.5.jar com.alibaba.druid.filter.config.ConfigTools you_password 即输出加密后密码

#部署说明
#数据库生产环境在config_dev.properties中，测试环境在config.properties，部署时要根据环境选择数据库配置文件，在application中进行修改
#修改密码中逻辑也要相应修改，在E:\splunk-web\src\main\java\com\gome\splunk\controller\LoginController.java中，把config.properties中config_dev.properties