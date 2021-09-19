package com.ybchen.springboot_shiro.domain;

import java.io.Serializable;

/**
 * @Description：角色权限
 * @Author：chenyanbin
 * @Date：2021/1/2 11:44 下午
 * @Versiion：1.0
 */
public class RolePermission implements Serializable {
    /**
     * 主键
     */
    private int id;
    /**
     * 角色id
     */
    private int roleId;
    /**
     * 权限id
     */
    private int permissiionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissiionId() {
        return permissiionId;
    }

    public void setPermissiionId(int permissiionId) {
        this.permissiionId = permissiionId;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissiionId=" + permissiionId +
                '}';
    }
}
