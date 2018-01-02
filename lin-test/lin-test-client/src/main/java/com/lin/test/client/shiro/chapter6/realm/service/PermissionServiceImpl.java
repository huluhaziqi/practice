package com.lin.test.client.shiro.chapter6.realm.service;

import com.lin.test.client.shiro.chapter6.realm.dao.PermissionDao;
import com.lin.test.client.shiro.chapter6.realm.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
