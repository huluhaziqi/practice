package com.lin.test.client.shiro.chapter6.realm.dao;

import com.lin.test.client.shiro.chapter6.realm.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Role createRole(Role role) {

        String sql = "insert into sys_roles(id,role,description,available) values(?,?,?) ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, role.getRole(), role.getDescription(), role.getAvaliable(), keyHolder);
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
            jdbcTemplate.update(sql, permissionId);
        }
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        if(roleId == null || permissionIds == null || permissionIds.length == 0){
            return;
        }
        String sql = "delete sys_roles_permissions where role_id = ? and permission_id = ?";
        for (Long permissionId : permissionIds) {
            jdbcTemplate.update(sql, permissionId);
        }
    }
}
