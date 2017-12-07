package com.lin.test.client.shiro.chapter3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

public class RoleTest {

    @Test
    public void testHasRole() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:authorizer/shiro-role.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            System.out.println(subject.hasRole("role1"));
            System.out.println(subject.hasAllRoles(Arrays.asList("role1", "role2", "role3")));
            boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
            for (boolean r : result) {
                System.out.println(r);
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("失败鉴权");
        }
    }

    @Test
    public void testPermission(){
        login("classpath:authorizer/shiro-permission.ini","zhang","123");
        Subject subject = getSubject();
        subject.checkPermission("user:create");
        subject.checkPermissions("user:create","user:update");
        subject.checkPermissions("user:create","user:update","user:view");
        System.out.println(subject.isPermitted("user:create"));
    }

    @Test
    public void testCheckRole() {
        login("classpath:authorizer/shiro-role.ini", "zhang", "123");
        Subject subject = getSubject();
        subject.checkRole("role1");
        subject.checkRoles("role1","role2");
        subject.checkRoles("role1","role2","role3");
    }

    public void login(String file, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(file);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

    public Subject getSubject() {
        Subject subject = SecurityUtils.getSubject();
        return subject;
    }
}
