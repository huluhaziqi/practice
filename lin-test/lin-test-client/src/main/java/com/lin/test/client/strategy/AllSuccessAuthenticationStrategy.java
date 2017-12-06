package com.lin.test.client.strategy;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

public class AllSuccessAuthenticationStrategy extends AbstractAuthenticationStrategy {
    @Override
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException {
        return new SimpleAuthenticationInfo();
    }

    @Override
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        if (!realm.supports(token)) {
            throw new UnsupportedTokenException();
        }
        return aggregate;
    }

    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {

        AuthenticationInfo info;
        if (t != null) {
            if (t instanceof AuthenticationException) {
                throw new AuthenticationException();
            } else {
                String msg = "all must success";
                throw new AuthenticationException(msg, t);
            }
        }
        if (singleRealmInfo == null) {
            String msg = "all must success";
            throw new UnknownAccountException(msg);
        }
        info = merge(singleRealmInfo, aggregateInfo);
        return info;

    }

    @Override
    public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        return super.afterAllAttempts(token, aggregate);
    }
}
