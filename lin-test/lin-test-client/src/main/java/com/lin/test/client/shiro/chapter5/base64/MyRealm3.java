package com.lin.test.client.shiro.chapter5.base64;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm3 extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = "liu";
        String salt = "f50842a02fdb259281f83e7412f060a7";
        String password = "c840b5e87fc13cf2fcf77bba752b5843";
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(username + salt));
        return info;
    }
}
