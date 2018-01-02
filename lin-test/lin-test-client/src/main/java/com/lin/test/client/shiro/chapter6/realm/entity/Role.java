package com.lin.test.client.shiro.chapter6.realm.entity;

import java.io.Serializable;

public class Role implements Serializable {

    private Long id;

    private String role;
    private String description;

    private Boolean isAvaliable = false;

    public Role() {
    }

    public Role(String role, String description, Boolean isAvaliable) {
        this.role = role;
        this.description = description;
        this.isAvaliable = isAvaliable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvaliable() {
        return isAvaliable;
    }

    public void setAvaliable(Boolean avaliable) {
        isAvaliable = avaliable;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", isAvaliable=" + isAvaliable +
                '}';
    }
}
