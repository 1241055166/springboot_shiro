package com.ybchen.springboot_shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Description：
 * @Author：chenyanbin
 * @Date：2020/12/27 10:40 下午
 * @Versiion：1.0
 */
public class QuickStartJdbc2Test {
    @Test
    public void testAuthentication() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/shiro?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("root");
        JdbcRealm jdbcRealm = new JdbcRealm();
        //开启查找权限, 默认是false
        jdbcRealm.setPermissionsLookupEnabled(true);
        //配置数据源
        jdbcRealm.setDataSource(ds);
        //jdbc与DefaultSecurityManager关联
        securityManager.setRealm(jdbcRealm);
        //=======================下面内容相同==============================
        //将securityManager设置到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //模拟用户登录，账户、密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("laochen", "123");
        subject.login(usernamePasswordToken);
        //判断是否成功
        boolean authenticated = subject.isAuthenticated();
        System.out.println("认证结果：" + authenticated);
        //是否有对应的角色
        System.out.println("是否有对应的root角色：" + subject.hasRole("root"));
        //获取subject名
        System.out.println("获取subject名：" + subject.getPrincipal());
        //检查是否有对应的角色，无返回值，直接在SecurityManager里面进行判断，没有的话，直接报错
        subject.checkRole("role1");
        //检查是否有对应的角色
        System.out.println("是否存在role1角色：" + subject.hasRole("role1"));
        //================权限，没有的话直接报错================
        //subject.checkPermission("video:delete");
        System.out.println("是否有video:buy权限：" + subject.isPermitted("video:buy"));
        //退出登录
        subject.logout();
        System.out.println("退出登录后，认证结果：" + subject.isAuthenticated());

        //基于角色判断
        if (subject.hasRole("root")){
            //有权限
        }else {
            //无权限
        }
        //基于权限判断
        if (subject.isPermitted("user:add")){
            //有权限
        }else {
            //无权限
        }
    }
}
