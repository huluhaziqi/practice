package com.lin.test.client.shiro.chapter6.realm.dao;

import com.lin.test.client.shiro.chapter6.realm.entity.Role;

public interface RoleDao {

    Role createRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long roleId);

    void correlationPermissions(Long roleId, Long... permissionIds);

    void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
