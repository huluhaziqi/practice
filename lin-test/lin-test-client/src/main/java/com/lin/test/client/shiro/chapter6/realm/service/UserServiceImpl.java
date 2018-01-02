package com.lin.test.client.shiro.chapter6.realm.service;

import com.lin.test.client.shiro.chapter6.realm.dao.UserDao;
import com.lin.test.client.shiro.chapter6.realm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void delete(Long userId) {
        userDao.delete(userId);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        userDao.correlationRoles(userId,roleIds);
    }

    @Override
    public void uncorrelationsRole(Long userId, Long... roleIds) {
        userDao.uncorrelationsRole(userId,roleIds);
    }

    @Override
    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userDao.findOne(userId);
        if(user != null){
            user.setPassword(newPassword);
        }
    }
}
