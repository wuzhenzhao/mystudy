package com.wuzz.demo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/7 13:44
 * @since 1.0
 **/
//省略了import
public class MysqlGenerator {

    /**
     * 包名
     */
    private static final String PACKAGE_NAME = "com.wuzz.demo";
    /**
     * 模块名称
     */
    private static final String MODULE_NAME = "biz";
    /**
     * 代码生成者
     */
    private static final String AUTHOR = "wuzhenzhao";

    /**
     * JDBC相关配置
     */
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/cem";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "zhenzhao123456";

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
//        TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
//        TableFill modifiedField = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
//        tableFillList.add(createField);
//        tableFillList.add(modifiedField);

        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");
        GlobalConfig globalConfig = new GlobalConfig().setOutputDir(projectPath + "/mybatis-plus-study/src/main/java")// 输出目录
                .setFileOverride(true)// 是否覆盖文件
                .setActiveRecord(true)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(false)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setAuthor(AUTHOR)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setXmlName("%sMapper").setMapperName("%sDao");
        // .setServiceName("MP%sService")
                // .setServiceImplName("%sServiceDiy")
                // .setControllerName("%sAction")
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig().setDbType(DbType.POSTGRE_SQL)// 数据库类型
                .setDriverName(DRIVER).setUsername(USER_NAME).setPassword(PASSWORD).setUrl(URL);
        autoGenerator.setDataSource(dataSourceConfig);
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig()
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // .setCapitalMode(true)// 全局大写命名
                // .setTablePrefix(new String[]{"unionpay_"})// 此处可以修改为您的表前缀
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                // .setInclude(new String[] {"user"}) // 需要生成的表
                // .setExclude(new String[]{"test"}) // 排除生成的表
                // 自定义实体，公共字段
                // .setSuperEntityColumns(new String[]{"test_id"})
                // .setTableFillList(tableFillList)
                // 自定义实体父类
                // .setSuperEntityClass("com.baomidou.demo.base.BsBaseEntity")
                // // 自定义 mapper 父类
                // .setSuperMapperClass("com.baomidou.demo.base.BsBaseMapper")
                // // 自定义 service 父类
                // .setSuperServiceClass("com.baomidou.demo.base.BsBaseService")
                // // 自定义 service 实现类父类
                // .setSuperServiceImplClass("com.baomidou.demo.base.BsBaseServiceImpl")
                // 自定义 controller 父类
                // .setSuperControllerClass("com.baomidou.demo.TestController")
                // 【实体】是否生成字段常量（默认 false）
                // public static final String ID = "test_id";
                .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                // public User setName(String name) {this.name = name; return this;}
                .setEntityBuilderModel(true)
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                .setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀处理
        // .setEntityBooleanColumnRemoveIsPrefix(true)
        // .setRestControllerStyle(true)
        // .setControllerMappingHyphenStyle(true)
        // 包配置
        PackageConfig packageConfig = new PackageConfig().setModuleName(MODULE_NAME).setParent(PACKAGE_NAME)// 自定义包路径
                .setController("controller")// 这里是控制器包名，默认 web
                .setXml("mapper").setMapper("dao");
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        // 执行生成
        autoGenerator.execute();
    }

}
