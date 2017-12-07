package com.lin.test.client.shiro.chapter3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class BaseTest {

    public void login(String file, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(file);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            System.out.println("success!!!");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

    public Subject getSubject() {
        Subject subject = SecurityUtils.getSubject();
        return subject;
    }

}
