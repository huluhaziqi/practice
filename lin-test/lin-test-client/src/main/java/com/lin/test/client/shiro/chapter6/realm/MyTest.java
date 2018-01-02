package com.lin.test.client.shiro.chapter6.realm;

import net.sf.json.util.JSONUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class MyTest {

    @Test
    public void test01(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-6.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        Object primaryPrincipal1 = subject.getPrincipal();
        System.out.println(JSONUtils.valueToString(primaryPrincipal1));
        PrincipalCollection principalCollection = subject.getPrincipals();

    }
}
