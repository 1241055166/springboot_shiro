package com.ybchen.springboot_shiro.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description：角色
 * @Author：chenyanbin
 * @Date：2021/1/2 11:43 下午
 * @Versiion：1.0
 */
public class Role implements Serializable {
    /**
     * 主键
     */
    private int id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 权限集合
     */
    private List<Permission> permissionList=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

}
