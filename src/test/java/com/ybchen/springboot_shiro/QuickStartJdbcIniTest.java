package com.ybchen.springboot_shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @Description：
 * @Author：chenyanbin
 * @Date：2020/12/27 10:40 下午
 * @Versiion：1.0
 */
public class QuickStartJdbcIniTest {
    @Test
    public void testAuthentication(){
        //创建SecurityManager工厂，通过配置文件ini创建
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:jdbcrealm.ini");
        SecurityManager securityManager=factory.getInstance();
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
        System.out.println("是否有对应的root角色："+subject.hasRole("root"));
        //获取subject名
        System.out.println("获取subject名："+subject.getPrincipal());
        //检查是否有对应的角色，无返回值，直接在SecurityManager里面进行判断，没有的话，直接报错
        subject.checkRole("role1");
        //检查是否有对应的角色
        System.out.println("是否存在role1角色："+subject.hasRole("role1"));
        //================权限，没有的话直接报错================
        //subject.checkPermission("video:delete");
        System.out.println("是否有video:buy权限："+subject.isPermitted("video:buy"));
        //退出登录
        subject.logout();
        System.out.println("退出登录后，认证结果：" + subject.isAuthenticated());
    }
}
