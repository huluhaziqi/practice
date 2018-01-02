package com.lin.test.client.shiro.chapter6.realm.dao;

import com.lin.test.client.shiro.chapter6.realm.entity.Permission;

public interface PermissionDao {

    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);

}
