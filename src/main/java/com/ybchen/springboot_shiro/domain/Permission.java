package com.ybchen.springboot_shiro.domain;

import java.io.Serializable;

/**
 * @Description：权限
 * @Author：chenyanbin
 * @Date：2021/1/2 11:47 下午
 * @Versiion：1.0
 */
public class Permission implements Serializable {
    /**
     * 主键
     */
    private int id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 路径
     */
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
