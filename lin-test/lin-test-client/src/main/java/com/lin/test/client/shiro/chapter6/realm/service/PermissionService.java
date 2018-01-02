package com.lin.test.client.shiro.chapter6.realm.service;

import com.lin.test.client.shiro.chapter6.realm.entity.Permission;

public interface PermissionService {

    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
