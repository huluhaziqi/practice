package com.lin.test.client.shiro.chapter4;

import com.alibaba.druid.pool.DruidDataSource;
import com.lin.test.client.shiro.chapter3.BaseTest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class NoConfigurationCreateTest extends BaseTest {

    @Test
    public void test() {
        DefaultSecurityManager manager = new DefaultSecurityManager();
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        AuthenticationStrategy strategy = new AtLeastOneSuccessfulStrategy();
        authenticator.setAuthenticationStrategy(strategy);
        manager.setAuthenticator(authenticator);
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        manager.setAuthorizer(authorizer);

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/lin");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(druidDataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        manager.setRealm(jdbcRealm);

        SecurityUtils.setSecurityManager(manager);


        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        subject.login(token);

        System.out.println(subject.isAuthenticated());

    }
}
