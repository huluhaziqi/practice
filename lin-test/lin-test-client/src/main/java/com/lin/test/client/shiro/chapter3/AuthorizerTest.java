package com.lin.test.client.shiro.chapter3;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class AuthorizerTest extends BaseTest{

    @Test
    public void test(){
        login("classpath:authorizer/shiro-authorizer.ini","zhang","123");
        Subject subject = getSubject();
        System.out.println(subject.isPermitted("user1:update"));
        System.out.println(subject.isPermitted("user1:view"));
        System.out.println(subject.isPermitted("+user1+4"));
        System.out.println(subject.isPermitted("menu:*"));
        System.out.println(subject.isPermitted("system:user:*"));
    }

    @Test
    public void test2(){
        login("classpath:authorizer/shiro-jdbc.ini","zhang","123");
        Subject subject = getSubject();
        System.out.println(subject.isPermitted("user1:update"));
        System.out.println(subject.isPermitted("user1:view"));
        System.out.println(subject.isPermitted("+user1+4"));
        System.out.println(subject.isPermitted("menu:*"));
        System.out.println(subject.isPermitted("system:user:*"));
    }
}
