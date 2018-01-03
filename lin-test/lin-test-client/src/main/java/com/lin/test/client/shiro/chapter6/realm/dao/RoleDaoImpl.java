package com.lin.test.client.shiro.chapter6.realm.dao;

import com.lin.test.client.shiro.chapter6.realm.entity.Role;
import com.lin.test.client.shiro.chapter6.realm.util.JdbcTemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;

public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    public Role createRole(Role role) {

        String sql = "insert into sys_roles(role,description,available) values(?,?,?) ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((c) -> {
            PreparedStatement preparedStatement = c.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, role.getRole());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setBoolean(3, role.getAvaliable());
            return preparedStatement;
        }, keyHolder);
        role.setId(keyHolder.getKey().longValue());
        return role;
    }

    @Override
    public void updateRole(Role role) {

    }

    @Override
    public void deleteRole(Long roleId) {
        String sql = "delete sys_users_roles where role_id =?";
        jdbcTemplate.update(sql, roleId);

        sql = "delete sys_roles where id =?";
        jdbcTemplate.update(sql, roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        String sql = "insert into sys_roles_permissions(role_id, permission_id) values(?,?) ";
        for (Long permissionId : permissionIds) {
            jdbcTemplate.update(sql, roleId, permissionId);
        }
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        if (roleId == null || permissionIds == null || permissionIds.length == 0) {
            return;
        }
        String sql = "delete sys_roles_permissions where role_id = ? and permission_id = ?";
        for (Long permissionId : permissionIds) {
            jdbcTemplate.update(sql, permissionId);
        }
    }
}
