package com.lin.test.client.shiro.chapter5.base64;

import com.lin.test.client.shiro.chapter3.BaseTest;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class PasswordTest extends BaseTest {

    @Test
    public void test() {
        DefaultPasswordService defaultPasswordService = new DefaultPasswordService();
        System.out.println(defaultPasswordService.encryptPassword("123"));
    }

    @Test
    public void test01() {

        login("classpath:passwordService/shiro-passwordservice.ini", "wu", "123");
    }

    @Test
    public void test02() {
        login("classpath:passwordService/shiro-hashed-credentialsMatcher.ini", "liu", "123");
    }

    @Test
    public void test03() {
        String algorithmName = "md5";
        String username = "liu";
        String password = "123";
        String salt1 = username;
//        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        String salt2 = "f50842a02fdb259281f83e7412f060a7";
        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, 2);
        String encodedPassword = hash.toHex();
        System.out.println(salt2);
        System.out.println(encodedPassword);

    }

    @Test
    public void test04() {
        login("classpath:passwordService/shiro-jdbc-hashed-credentialsMatcher.ini", "liu", "123");
    }

    @Test
    public void test05(){
        for(int i = 0; i < 5; i++) {
            try {
                login("classpath:passwordService/shiro-retryLimitHashedCredentialMatcher.ini", "liu", "223");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        login("classpath:passwordService/shiro-retryLimitHashedCredentialMatcher.ini", "liu", "223");
    }
}
