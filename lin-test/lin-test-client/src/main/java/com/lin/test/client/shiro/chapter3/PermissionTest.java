package com.lin.test.client.shiro.chapter3;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class PermissionTest extends BaseTest {

    @Test
    public void testPermission() {
        login("classpath:authorizer/shiro-permission.ini", "lin", "123");
        Subject subject = getSubject();
//        subject.checkPermissions("system:user:update,delete,create,view");
        subject.checkPermission("system:user:update");
        subject.checkPermission("system:user:create");
        subject.checkPermission("system:user:view");
    }

    @Test
    public void testPermission2() {
        login("classpath:authorizer/shiro-permission.ini", "li", "123");
        Subject subject = getSubject();
//        subject.checkPermissions("system:user:update,delete,create,view");
        subject.checkPermission("system:user:*");
        subject.checkPermission("system:user:create");
        subject.checkPermission("system:user:view");
    }

    @Test
    public void testPermission3() {
        login("classpath:authorizer/shiro-permission.ini", "l", "123");
        Subject subject = getSubject();
//        subject.checkPermissions("system:user:update,delete,create,view");
        subject.checkPermission("system:user:view");
        subject.checkPermission("*:user:view");
        subject.checkPermission("*:*:view");
    }

    @Test
    public void testPermission4() {
        login("classpath:authorizer/shiro-permission.ini", "ll", "123");
        Subject subject = getSubject();
//        subject.checkPermissions("system:user:update,delete,create,view");
        subject.checkPermission("user:view:1");
        subject.checkPermissions("user:*:1");
        subject.checkPermissions("user:auth:1","user:auth:2");
    }

    @Test
    public void testPermission5() {
        login("classpath:authorizer/shiro-permission.ini", "lll", "123");
        Subject subject = getSubject();
//        subject.checkPermissions("system:user:update,delete,create,view");
        subject.checkPermission("menu:1");
        subject.checkPermissions("organization");
    }

}
