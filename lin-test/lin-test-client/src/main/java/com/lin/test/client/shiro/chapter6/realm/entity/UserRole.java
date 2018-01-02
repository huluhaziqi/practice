package com.lin.test.client.shiro.chapter6.realm.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserRole implements Serializable {

    private Long userId;

    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(userId, userRole.userId) &&
                Objects.equals(roleId, userRole.roleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, roleId);
    }
}
