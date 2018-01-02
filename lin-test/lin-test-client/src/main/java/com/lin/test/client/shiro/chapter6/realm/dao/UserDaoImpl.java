package com.lin.test.client.shiro.chapter6.realm.dao;

import com.lin.test.client.shiro.chapter6.realm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User createUser(User user) {
        final String sql = "insert into sys_users(username,password,salt,locked) values (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getSalt());
                preparedStatement.setBoolean(4, user.getLocked());
                return preparedStatement;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public void updateUser(User user) {
        String sql = "update sys_users set username=?, password=?, salt=?, locked=? where id=?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getLocked());
    }

    @Override
    public void delete(Long userId) {
        String sql = "delete sys_users where id=?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        if (userId == null || roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "insert into sys_users_roles(user_id,role_id) values(?, ?)";
        for (Long roleId : roleIds) {
            jdbcTemplate.update(sql, userId, roleId);
        }
    }

    @Override
    public void uncorrelationsRole(Long userId, Long... roleIds) {
        if (userId == null || roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "delete sys_users_roles where user_id =? and role_id =?";
        for (Long roleId : roleIds) {
            jdbcTemplate.update(sql, userId, roleId);
        }
    }

    @Override
    public User findOne(Long userId) {
        String sql = "select id, username, password, salt, locked from sys_users where id =?";
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), userId);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select id, username, password, salt, locked from sys_users where username = ?";
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Set<String> findRoles(String username) {
        String sql = "select role from sys_users u,sys_roles r, sys_users_roles ur where u.username = ? and u.id = ur.user_id and r.id = ur.role_id";
        List<String> list = jdbcTemplate.queryForList(sql, String.class, username);
        return new HashSet<>(list);
    }

    @Override
    public Set<String> findPermissions(String username) {
        //TODO
        String sql = "select permission from sys_users u, sys_roles r, sys_permissions p, sys_users_roles ur, sys_roles_permissions rp " +
                "where u.username = ? and u.id = ur.user_id and r.id = ur.role_id and rp.role_id = r.id and p.id = rp.permission_id";
        List<String> list = jdbcTemplate.queryForList(sql, String.class, username);
        return new HashSet<>(list);
    }
}
