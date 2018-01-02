package com.lin.test.client.shiro.chapter6.realm.dao;

import com.lin.test.client.shiro.chapter6.realm.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl implements PermissionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Permission createPermission(Permission permission) {
        String sql = "insert into sys_permissions(permission,description,available) values(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, permission.getPermission(), permission.getDescription(), permission.getAvaliable(), keyHolder);
        permission.setId(keyHolder.getKey().longValue());
        return permission;
    }

    @Override
    public void deletePermission(Long permissionId) {
        String sql = "delete sys_roles_permissions where permission_id = ?";
        jdbcTemplate.update(sql, permissionId);

        sql = "delete sys_permissions where id = ?";
        jdbcTemplate.update(sql, permissionId);
    }
}
