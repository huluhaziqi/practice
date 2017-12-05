package com.lin.test.client.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void testShiroTest01() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm01.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("lin", "123");

        try {
            System.out.println("login");
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("鉴权失败");
        }
        Assert.assertEquals(true, subject.isAuthenticated());

        subject.logout();
        System.out.println("logout");
    }

    @Test
    public void testMultiShiro(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-mult-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("lin","123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("shibai");
        }
        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();

        UsernamePasswordToken token1 = new UsernamePasswordToken("zhang","123");
        subject.login(token1);
        System.out.println( subject.isAuthenticated());
        subject.logout();
    }

    @Test
    public void testMultiShiroRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-mult-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang","123");

        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e){
            e.printStackTrace();
            System.out.println("鉴权失败 ");
        }

        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();

        UsernamePasswordToken usernamePasswordToken1 = new UsernamePasswordToken("lin","123");
        try {
            subject.login(usernamePasswordToken1);
            System.out.println("success");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("鉴权失败");
        }
        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();
    }
}
