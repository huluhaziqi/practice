package com.lin.test.client.shiro.chapter6.realm.realm;

import com.lin.test.client.shiro.chapter6.realm.entity.User;
import com.lin.test.client.shiro.chapter6.realm.service.UserService;
import com.lin.test.client.shiro.chapter6.realm.service.UserServiceImpl;
import net.sf.json.util.JSONUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {

    private UserService userService = new UserServiceImpl();

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = principals.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(userService.findRoles(username));
        simpleAuthorizationInfo.setStringPermissions(userService.findPermissions(username));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        User user = userService.findByUsername(username);
        System.out.println(JSONUtils.valueToString(user));
        if (user == null) {
            System.out.println("empty");
            throw new UnknownAccountException();
        }
        System.out.println("user :" + JSONUtils.valueToString(user));

        if (user.getLocked() == true) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
                user.getPassword(), org.apache.shiro.util.ByteSource.Util.bytes(user.credentialsSalt()), getName());
        return simpleAuthenticationInfo;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
