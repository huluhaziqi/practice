package com.lin.test.client.shiro.chapter6.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {
    @Override
    public String getName() {
        return "MyRealm2";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return new SimpleAuthenticationInfo("lin", "123", getName());
    }
}
