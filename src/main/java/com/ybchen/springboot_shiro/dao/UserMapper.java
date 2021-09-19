package com.ybchen.springboot_shiro.dao;

import com.ybchen.springboot_shiro.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户
 */
public interface UserMapper {
    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return
     */
    @Select("select * from user where username=#{userName}")
    User findByUserName(@Param("userName") String userName);

    /**
     * 根据主键查询用户
     *
     * @param id 主键
     * @return
     */
    @Select("select * from user where id=#{userId}")
    User findById(@Param("userId") int id);

    /**
     * 根据用户名和密码查询用户
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @Select("select * from user where userName=#{userName} and password=#{password}")
    User findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
}
