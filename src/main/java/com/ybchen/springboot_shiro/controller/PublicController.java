package com.ybchen.springboot_shiro.controller;

import com.ybchen.springboot_shiro.domain.UserQuery;
import com.ybchen.springboot_shiro.utils.JsonData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：chenyanbin
 * @Date：2021/1/3 1:12 上午
 * @Versiion：1.0
 */
@RestController
@RequestMapping("pub")
public class PublicController {
    /**
     * 需要登录
     *
     * @return
     */
    @GetMapping("need_login")
    public JsonData needLogin() {
        return JsonData.buildSuccess(-1, "温馨提示：请使用对应的账号登录");
    }

    /**
     * 没权限
     *
     * @return
     */
    @GetMapping("not_permit")
    public JsonData notPermit() {
        return JsonData.buildSuccess(-1, "温馨提示：拒绝访问，没权限");
    }

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("index")
    public JsonData index() {
        List<String> list = Arrays.asList("SpringBoot", "SpringMvc", "Mysql", "Redis");
        return JsonData.buildSuccess(list);
    }

    /**
     * 登录接口
     *
     * @param userQuery
     * @param request
     * @param response
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody UserQuery userQuery, HttpServletRequest request, HttpServletResponse response) {
        //拿到主体
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userQuery.getUserName(), userQuery.getPassword());
            subject.login(usernamePasswordToken);
            Map<String,Object> info=new HashMap<>();
            info.put("msg","登录成功");
            info.put("session_id",subject.getSession().getId());
            return JsonData.buildSuccess(info);
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("账号或密码错误");
        }
    }
}
