package com.ybchen.springboot_shiro.service;

import com.ybchen.springboot_shiro.domain.User;

public interface UserService {
    /**
     * 获取全部用户信息，包括角色、权限
     * @param userName
     * @return
     */
    User findAllUserInfoByUserName(String userName);

    /**
     * 获取用户基本信息
     * @param userId
     * @return
     */
    User findSimpleUserInfoById(int userId);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    User findSimpleUserInfoByUserName(String userName);
}
