package com.lin.test.client.shiro.chapter6.realm.dao;

import com.lin.test.client.shiro.chapter6.realm.entity.User;

import java.util.Set;

public interface UserDao {

    User createUser(User user);

    void updateUser(User user);

    void delete(Long userId);

    void correlationRoles(Long userId, Long... roleIds);

    void uncorrelationsRole(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
