package com.lin.test.client.shiro.chapter6.realm.dao;

import com.lin.test.client.shiro.chapter6.realm.entity.Permission;
import com.lin.test.client.shiro.chapter6.realm.util.JdbcTemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PermissionDaoImpl implements PermissionDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    public Permission createPermission(Permission permission) {
        String sql = "insert into sys_permissions(permission,description,available) values(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql,new String[]{"id"});
                preparedStatement.setString(1, permission.getPermission());
                preparedStatement.setString(2, permission.getDescription());
                preparedStatement.setBoolean(3, permission.getAvaliable());
                return preparedStatement;
            }
        }, keyHolder);
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
