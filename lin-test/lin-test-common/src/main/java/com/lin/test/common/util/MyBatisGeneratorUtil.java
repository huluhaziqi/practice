package com.lin.test.common.util;

import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 代码生成类
 *
 * @author linxiaobin
 */
public class MyBatisGeneratorUtil {
    private static String generatorConfig_vm = "/template/generatorConfig.vm";

    private static String service_vm = "/template/Service.vm";

    private static String serviceMock_vm = "/template/ServiceMock.vm";

    private static String serviceImpl_vm = "/template/ServiceImpl.vm";
    public static void generator(
            String module,
            String driver,
            String jdbc_url,
            String database,
            String password,
            String username,
            String table_prefix,
            String package_name,
            Map<String, String> last_insert_id_tables) throws IOException {
        generatorConfig_vm = MyBatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath();
        service_vm = MyBatisGeneratorUtil.class.getResource(service_vm).getPath();
        serviceMock_vm = MyBatisGeneratorUtil.class.getResource(serviceMock_vm).getPath();
        serviceImpl_vm = MyBatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath();
        System.out.println("generatorConfig_vm : " + generatorConfig_vm);
        String targetProject = module + "/" + module + "-dao";
        System.out.println("targetProject : " + targetProject);
        String basePath = MyBatisGeneratorUtil.class.getResource("/").getPath().
                replace("/target/classes/", "").replace(targetProject, "");
        System.out.println("basePath :" + basePath);
        targetProject = basePath + targetProject;
        System.out.println("targetProject : " + targetProject);
        String generatorConfigXml = targetProject + "/src/main/resources/generatorConfig.xml";

        System.out.println("=========开始生成generatorConfig.xml文件===========");
        //查询语句，查询所有的table_prefix开头的table
        String sql = "select table_name from INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' and " +
                "table_name LIKE  '" + table_prefix + "_%'";
        List<Map<String, Object>> tables = new ArrayList<>();
        JdbcUtil jdbcUtil = new JdbcUtil(driver, jdbc_url, AESUtil.AESDecode(password), username);
        List<Map> result = jdbcUtil.selectByParams(sql, null);
        VelocityContext context = new VelocityContext();
        for (Map map : result) {
            map.get("TABLE_NAME");
            Map<String, Object> table = new HashMap<>();
            table.put("table_name", map.get("TABLE_NAME"));
            table.put("model_name", StringUtil.lineToHump((String) map.get("TABLE_NAME")));
            tables.add(table);
        }
        //释放连接
        jdbcUtil.release();

        context.put("tables", tables);
        context.put("generator_javaModelGenerator_targetPackage", package_name + ".dao.model");
        context.put("generator_sqlMapGenerator_targetPackage", package_name + ".dao.mapper");
        context.put("generator_javaClientGenerator_targetPackage", package_name + ".dao.mapper");
        context.put("targetProject", targetProject);
        context.put("targetProject_sqlMap", package_name + ".dao.mapper");
        context.put("generator_jdbc_password", AESUtil.AESDecode(password));
        context.put("last_insert_id_tables", last_insert_id_tables);

        try {
            VelocityUtil.generator(generatorConfig_vm, generatorConfigXml, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=========结束生成generatorConfig.xml文件===========");
        System.out.println("=========开始运行MybatisGenerator文件===========");
        List<String> warnings = new ArrayList<>();
        System.out.println("generatorConfigXml :" + generatorConfigXml);
        File configFile = new File(generatorConfigXml);
        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        Configuration c = null;
        try {
            c = configurationParser.parseConfiguration(configFile);

            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator mybatisgenerator = new MyBatisGenerator(c, callback, warnings);
            mybatisgenerator.generate(null);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("=========结束运行MybatisGenerator文件===========");
        System.out.println("=========开始生成service===========");

        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println("basePath :" + basePath);
        System.out.println("module :" + module);
        String servicePath = basePath + module + "/" + module + "-rpc-api" +
                "/src/main/java/" + package_name.replaceAll("\\.","/") + "/rpc/api";
        System.out.println("servicePath : " + servicePath);
        String serviceimplPath = basePath + module + "/" + module + "-rpc-service" +
                "/src/main/java/" + package_name.replaceAll("\\.","/") + "/rpc/service/impl";
        for(int i = 0; i < tables.size(); i++){
            String model = StringUtil.lineToHump((String)tables.get(i).get("table_name"));
            String mapper = StringUtil.toLowerCaseFirstOne(model);
            String service = servicePath + "/" + model + "Service.java";
            System.out.println("service :" + service);
            String serviceMock = servicePath + "/" + model + "ServiceMock.java";
            String serviceImpl = serviceimplPath + "/" + model + "ServiceImpl.java";
            File serviceFile = new File(service);
                VelocityContext context1 = new VelocityContext();
                context1.put("package_name",package_name);
                context1.put("model",model);
                context1.put("ctime",time);
                VelocityUtil.generator(service_vm,service,context1);
            File serviceMockFile = new File(serviceMock);
//            if(!serviceMockFile.exists()){
                VelocityContext context2 = new VelocityContext();
                context2.put("package_name",package_name);
                context2.put("model",model);
                context2.put("ctime",time);
                VelocityUtil.generator(serviceMock_vm,serviceMock,context2);
//            }
            File serviceImplFile = new File(serviceImpl);
//            if(!serviceImplFile.exists()){
                VelocityContext context3 = new VelocityContext();
                context3.put("package_name",package_name);
                context3.put("model",model);
                context3.put("ctime",time);
                context3.put("mapper",mapper);
                VelocityUtil.generator(serviceImpl_vm,serviceImpl,context3);
//            }
            System.out.println("===============结束生成service====================");
        }
    }
}
