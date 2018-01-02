package com.lin.test.client.shiro.chapter6.realm.util;

import com.lin.test.client.shiro.chapter6.realm.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

    private RandomNumberGenerator generator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";

    private final int hashIterations = 2;

    public void encryptPassword(User user) {
        user.setSalt(generator.nextBytes().toHex());
        String password = new SimpleHash(algorithmName, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), hashIterations).toHex();
        user.setPassword(password);
    }
}
