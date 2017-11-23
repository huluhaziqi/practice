package com.lin.common.util;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成类
 *
 * @author linxiaobin
 */
public class MyBatisGeneratorUtil {
    private static String generatorConfig_vm = "/template/generatorConfig.vm";

    public static void generator(
            String driver,
            String jdbc_url,
            String database,
            String password,
            String username,
            String table_prefix,
            String package_name,
            Map<String, String> last_insert_id_tables) {
        generatorConfig_vm = MyBatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath();
        System.out.println("generatorConfig_vm : " + generatorConfig_vm);
        String targetProject = "lin-test";
        String basePath = MyBatisGeneratorUtil.class.getResource("/").getPath().
                replace("/target/classes/", "").replace(targetProject, "");
        targetProject = basePath + targetProject;
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
        context.put("generator_javaModelGenerator_targetPackage", package_name + ".model");
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


//    public static void main(String[] args) {
//        generator();
//    }
    }
}
