package com.lin.test.client.shiro.chapter6.realm.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateUtils {

    private static JdbcTemplate template;

    @Bean
    public static JdbcTemplate jdbcTemplate() {
        if (template == null) {
            template = createJdbcTemplate();
        }
        return template;
    }

    private static JdbcTemplate createJdbcTemplate() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/lin");
        ds.setUsername("root");
        ds.setPassword("root");
        return new JdbcTemplate(ds);
    }
}
