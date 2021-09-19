package com.ybchen.springboot_shiro.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author：chenyanbin
 * @Date：2021/1/3 10:01 下午
 * @Versiion：1.0
 */
@RestController
public class LogoutController {
//    /**
//     * 退出，没必要能这个，退出时，前端直接将token清空即可
//     * 还需要获取前端传来的token，然后从shiro从清空指定的session_id
//     * @return
//     */
//    @GetMapping("logout")
//    public JsonData logout(){
//        Subject subject= SecurityUtils.getSubject();
//        subject.logout();
//        return JsonData.buildSuccess("退出成功");
//    }
}
