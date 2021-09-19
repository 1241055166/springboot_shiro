package com.ybchen.springboot_shiro.service.impl;

import com.ybchen.springboot_shiro.dao.RoleMapper;
import com.ybchen.springboot_shiro.dao.UserMapper;
import com.ybchen.springboot_shiro.domain.Role;
import com.ybchen.springboot_shiro.domain.User;
import com.ybchen.springboot_shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：
 * @Author：chenyanbin
 * @Date：2021/1/3 1:15 上午
 * @Versiion：1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User findAllUserInfoByUserName(String userName) {
        User user = userMapper.findByUserName(userName);
        //用户角色的集合
        List<Role> roleList = roleMapper.findRoleListByUserId(user.getId());
        user.setRoleList(roleList);
        return user;
    }

    @Override
    public User findSimpleUserInfoById(int userId) {
        return userMapper.findById(userId);
    }

    @Override
    public User findSimpleUserInfoByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }
}
