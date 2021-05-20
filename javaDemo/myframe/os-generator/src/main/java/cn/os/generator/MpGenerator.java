package cn.os.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.sun.istack.internal.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description mybatis-plus代码生成器
 * @Auther os
 * @date: 2021/5/20 17:13
 */
public class MpGenerator {
    static String driverName = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/myframe?characterEncoding=UTF-8&serverTimezone=UTC";
    static String username = "root";
    static String password = "root";
    static DbType dbType = DbType.MYSQL;

    static String projectPath = "D:\\Demo_Git\\os\\Demo\\javaDemo\\myframe\\os-admin";
    static String packageName = "cn.os.admin";

    static AutoGenerator mpg = null;
    static StrategyConfig strategy = null;
    static String generatorPro = "generator";
    static String moduleName = "generator";
    static PackageConfig pc = null;

    static {
        // 创建代码生成器对象
        mpg = new AutoGenerator();
        // 选择 freemarker 引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 项目文件路径
//        String projectPath = System.getProperty("user.dir") + File.separator;
        String outputDir = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "java";
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        // 关闭ActiveRecord特性，保证Entity的纯粹性
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        //.setKotlin(true) 是否生成 kotlin 代码
        gc.setAuthor("os");
        // 开启swagger注解
        gc.setSwagger2(true);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setEntityName("%sEntity");
        gc.setMapperName("%sDao");
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(dbType);
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert(){});
        dataSourceConfig.setDriverName(driverName);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        dataSourceConfig.setUrl(url);
        mpg.setDataSource(dataSourceConfig);

        // 策略配置
        strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // 此处可以修改为您的表前缀
        strategy.setTablePrefix(new String[] { ""});
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        // REST 风格
        strategy.setRestControllerStyle(true);
        // 自定义实体父类
        // strategy.setSuperEntityClass(Serializable.class);

        // 自定义实体，公共字段
        strategy.setSuperEntityColumns(new String[] { "id", "is_delete", "create_date", "create_by", "update_date", "update_by" });
        // 自定义 dao 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        pc = new PackageConfig();
        // 包配置
//        pc.setModuleName(moduleName);
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);
        pc.setEntity("model.entity");
        pc.setMapper("db.dao");
        pc.setXml("db.mapper");
        pc.setController("controller");


        // 模板配置
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/template/controller.java");
        tc.setEntity("/template/entity.java");
        tc.setService("/template/service.java");
        tc.setServiceImpl("/template/serviceImpl.java");
        mpg.setTemplate(tc);

        // 注入自定义配置
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】  ${cfg.abc}
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                ConfigBuilder config = this.getConfig();
                Map<String, String> packageInfo = config.getPackageInfo();
                String entity = packageInfo.get("Entity");
                String model = entity.substring(0, entity.indexOf("entity"));
                packageInfo.put("VO",model+"vo");
                packageInfo.put("DTO",model+"dto");

                // 处理文件输出路径
                Map<String, String> pathInfo = config.getPathInfo();
                String entityPath = pathInfo.get("entity_path");
                String modelPath = entityPath.substring(0, entityPath.indexOf("entity"));
                pathInfo.put("vo_path",modelPath+"vo"+File.separator);
                pathInfo.put("dto_path",modelPath+"dto"+File.separator);
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-np");
                this.setMap(map);
            }
        };


        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

        // 自定义 ***VO.java 生成
        focList.add(new FileOutConfig("/template/entityVO.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                ConfigBuilder config = cfg.getConfig();
                String voPath = config.getPathInfo().get("vo_path");
                return voPath + tableInfo.getEntityName() + "VO.java";
            }
        });


        // 自定义 ***DTO.java 生成
        focList.add(new FileOutConfig("/template/entityDTO.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                ConfigBuilder config = cfg.getConfig();
                String voPath = config.getPathInfo().get("dto_path");
                return voPath + tableInfo.getEntityName() + "DTO.java";
            }
        });

        // 自定义 ***PageDTO.java 生成
        focList.add(new FileOutConfig("/template/entityPageDTO.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                ConfigBuilder config = cfg.getConfig();
                String voPath = config.getPathInfo().get("dto_path");
                return voPath + tableInfo.getEntityName() + "PageDTO.java";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }

    public static void generator(@NotNull String... tables){
        // 需要生成的表
        strategy.setInclude(tables);
        // 执行生成
        mpg.execute();
        System.out.println("执行完毕");
    }
}
