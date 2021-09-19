package com.ybchen.springboot_shiro.domain;

import java.io.Serializable;

/**
 * @Description：接收用户名和密码
 * @Author：chenyanbin
 * @Date：2021/1/3 6:19 下午
 * @Versiion：1.0
 */
public class UserQuery implements Serializable {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
