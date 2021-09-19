package com.ybchen.springboot_shiro.domain;

import java.io.Serializable;

/**
 * @Description：用户角色
 * @Author：chenyanbin
 * @Date：2021/1/2 11:46 下午
 * @Versiion：1.0
 */
public class UserRole implements Serializable {
    /**
     * 主键
     */
    private int id;
    /**
     * 角色id
     */
    private int roleId;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 备注
     */
    private String remark;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", userId=" + userId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
