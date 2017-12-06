package com.lin.test.client.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class Realm02 implements Realm {
    @Override
    public String getName() {
        return "realm02";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = null;
        try {
            password = new String((char[])token.getCredentials());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!"zhang".equals(username)){
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
