package com.lin.test.client;

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
    public void testHelloworld() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiroTest1.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("lin", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
        }

        junit.framework.Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
        System.out.println("logout");
    }

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
    public void testMultiShiro() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-mult-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("lin", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("shibai");
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();

        UsernamePasswordToken token1 = new UsernamePasswordToken("zhang", "123");
        subject.login(token1);
        System.out.println(subject.isAuthenticated());
        subject.logout();
    }

    @Test
    public void testMultiShiroRealm() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-mult-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "123");

        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("鉴权失败 ");
        }

        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();

        UsernamePasswordToken usernamePasswordToken1 = new UsernamePasswordToken("lin", "123");
        try {
            subject.login(usernamePasswordToken1);
            System.out.println("success");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("鉴权失败");
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }


    @Test
    public void testJdbcShiroRealm() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "123");

        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("鉴权失败 ");
        }

        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();

    }

    @Test
    public void testFirstSuccess() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-first-success.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        UsernamePasswordToken token = new UsernamePasswordToken("mao", "123");

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("失败");
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }

    @Test
    public void testAllSuccess() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-all-success.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        UsernamePasswordToken token = new UsernamePasswordToken("mao", "123");

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("失败");
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }


    @Test
    public void testAtLeastTwo() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-atLeastTwo-success.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        UsernamePasswordToken token = new UsernamePasswordToken("lin", "123");
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("鉴权失败");
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }

    @Test
    public void testOnlyOneAuthenticationStrategy(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-onlyOne-success.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        UsernamePasswordToken token = new UsernamePasswordToken("lin", "123");
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("鉴权失败");
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }

    @Test
    public void testAllSuccessAuthenticationStrategy(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-myAllSuccess.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        UsernamePasswordToken token = new UsernamePasswordToken("lin", "123");
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("鉴权失败");
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }
}
