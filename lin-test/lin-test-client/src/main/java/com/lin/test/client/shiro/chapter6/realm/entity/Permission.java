package com.lin.test.client.shiro.chapter6.realm.entity;

import java.io.Serializable;

public class Permission implements Serializable{

    private Long id;

    private String permission;

    private String description;

    private Boolean isAvaliable = false;

    public Permission() {
    }

    public Permission(String permission, String description, Boolean isAvaliable) {
        this.permission = permission;
        this.description = description;
        this.isAvaliable = isAvaliable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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
        return "Permission{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", description='" + description + '\'' +
                ", isAvaliable=" + isAvaliable +
                '}';
    }
}
