package com.lin.test.client.shiro.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

public class MyRolePermissionResolver implements RolePermissionResolver {
    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        if ("role1".equals(roleString)) {
            return Arrays.asList(new WildcardPermission("menu:*"));
        }
        if("role3".equals(roleString)){
            return Arrays.asList(new WildcardPermission("system:user:*"));
        }
        return null;
    }
}
