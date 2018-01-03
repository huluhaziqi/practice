package com.lin.test.client;

import com.lin.test.client.shiro.chapter6.realm.entity.User;
import net.sf.json.util.JSONUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:META-INF/spring/applicationContext.xml",
})
public class Test01 extends BaseTest {

    @Test
    public void test01() {
        User user = new User();
        user.setUsername("fei");
        user.setPassword("123");
        passwordHelper.encryptPassword(user);
        System.out.println(JSONUtils.valueToString(user));
        userService.createUser(user);
    }

    @Test
    public void test02() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-chapter6.ini");
        SecurityManager securityManager = factory.getInstance();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        subject.logout();
    }

    @Test
    public void test03(){
        login("classpath:shiro-chapter6.ini","lin","123");
        Assert.assertTrue(getSubject().hasRole("user"));
        Assert.assertTrue(getSubject().isPermitted("user:create"));
    }

    @Test
    public void test04(){
        login("classpath:shiro-chapter6.ini","zhang","123");
        Assert.assertTrue(getSubject().hasRole("admin"));
        Assert.assertTrue(getSubject().isPermitted("user:update"));
    }
}
