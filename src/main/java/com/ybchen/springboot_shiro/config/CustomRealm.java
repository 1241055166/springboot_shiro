package com.ybchen.springboot_shiro.config;

import com.ybchen.springboot_shiro.domain.Role;
import com.ybchen.springboot_shiro.domain.User;
import com.ybchen.springboot_shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description：自定义realm
 * @Author：chenyanbin
 * @Date：2021/1/2 11:16 下午
 * @Versiion：1.0
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 进行权限校验的时候会调用
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("CustomRealm doGetAuthorizationInfo 授权");
        //获取用户名
        User newUser = (User) principals.getPrimaryPrincipal();
        User user = userService.findAllUserInfoByUserName(newUser.getUsername());
        if (user == null) {
            return null;
        }
        //角色集合
        List<String> stringRoleList = new ArrayList<>();
        //权限集合
        List<String> stringPermissionList = new ArrayList<>();
        List<Role> roleList = user.getRoleList();
        stringRoleList = roleList.stream().map(
                obj -> {
                    stringPermissionList.addAll(obj.getPermissionList()
                            .stream()
                            .map(per ->
                                    per.getName()).collect(Collectors.toList()));
                    return obj.getName();
                }).collect(Collectors.toList());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户登录的时候会调用
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("CustomRealm doGetAuthenticationInfo 认证");
        //从token中获取用户信息
        String uesrName = (String) token.getPrincipal();
        User user = userService.findAllUserInfoByUserName(uesrName);
        if (user == null) {
            return null;
        }
        //密码
        String pwd = user.getPassword();
        if (pwd == null || "".equals(pwd)) {
            return null;
        }
        return new SimpleAuthenticationInfo(user, pwd, this.getClass().getName());
    }
}
