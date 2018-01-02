package com.lin.test.client.shiro.chapter6.realm;

import com.lin.test.dao.model.UpmsUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm3 implements Realm {
    @Override
    public String getName() {
        return "MyRealm3";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UpmsUser upmsUser = new UpmsUser();
        upmsUser.setUsername("lin");
        upmsUser.setPassword("123");
        return new SimpleAuthenticationInfo(upmsUser, "123", getName());
    }
}
