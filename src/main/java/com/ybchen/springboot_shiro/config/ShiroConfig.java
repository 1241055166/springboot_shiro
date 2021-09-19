package com.ybchen.springboot_shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description：
 * @Author：chenyanbin
 * @Date：2021/1/3 4:12 下午
 * @Versiion：1.0
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfig ShiroFilterFactoryBean 执行");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //如果访问需要登录的某个接口，却没有登录，则调用此接口(如果不是前后端分离，则跳转页面)
        shiroFilterFactoryBean.setLoginUrl("/pub/need_login");
        //shiroFilterFactoryBean.setLoginUrl("/xxx.jsp");
        //登录成功后，跳转的链接，若前后端分离，没必要设置这个
        //shiroFilterFactoryBean.setSuccessUrl("");
        //登录成功，未授权会调用此方法
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permit");

        //设置自定义Filter
        Map<String, Filter> filterMap=new LinkedHashMap<>();
        filterMap.put("roleOrFilter",new CustomRolesOrAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //拦截路径，必须使用:LinkedHashMap，要不然拦截效果会时有时无，因为使用的是无序的Map
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //key=正则表达式路径，value=org.apache.shiro.web.filter.mgt.DefaultFilter
        //退出过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        //匿名可以访问，游客模式
        filterChainDefinitionMap.put("/pub/**", "anon");
        //登录用户才可以访问
        filterChainDefinitionMap.put("/authc/**", "authc");
        //管理员角色才能访问
//        filterChainDefinitionMap.put("/admin/**", "roles[admin,user]");
        filterChainDefinitionMap.put("/admin/**", "roleOrFilter[admin,user]");
        //有编辑权限才能访问
        filterChainDefinitionMap.put("/video/update", "perms[video_update]");
        //authc：url必须通过认证才可以访问
        //anon：url可以匿名访问
        //过滤链是顺序执行，从上而下，一般把/**，放到最下面
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //如果不是前后端分离，不用设置setSessionManager
        securityManager.setSessionManager(sessionManager());
        //使用自定义cacheManager
        securityManager.setCacheManager(cacheManager());
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    /**
     * 配置redisManager
     * @return
     */
    public RedisManager getRedisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setHost("127.0.0.1:6379");
        //连接那个数据库
        redisManager.setDatabase(0);
        //设置密码
//        redisManager.setPassword("123");
        return redisManager;
    }

    /**
     * 设置具体cache实现类
     * @return
     */
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager=new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        //设置缓存过期时间
        redisCacheManager.setExpire(20);
        return redisCacheManager;
    }

    /**
     * 自定义realm
     *
     * @return
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        //因为数据库密码存的是明文，所以无需使用双重md5校验
//        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    /**
     * 密码验证器，双重md5
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置散列算法，使用md5算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列次数，使用2次md5算法，相当于md5(md5(xxx))
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义SessionManager
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        //超时时间，默认 30分钟，会话超时，单位毫秒
//        customSessionManager.setGlobalSessionTimeout(200000);
        //配置session持久化
        customSessionManager.setSessionDAO(redisSessionDAO());
        return customSessionManager;
    }

    /**
     * 自定义session持久化
     * @return
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());
        return redisSessionDAO;
    }
}
