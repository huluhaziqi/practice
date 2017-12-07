package com.lin.test.client.shiro.chapter4;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ConfigurationCreateTest {

    @Test
    public void test(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "classpath:authorizer/shiro-config.ini");
        SecurityManager securityManager = factory.getInstance();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        System.out.println(subject.isAuthenticated());
    }
}
