package com.ybchen.springboot_shiro.controller;

import com.ybchen.springboot_shiro.utils.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author：chenyanbin
 * @Date：2021/1/3 9:20 下午
 * @Versiion：1.0
 */
@RestController
public class OtherController {
    @GetMapping("a")
    public JsonData a(){
        return JsonData.buildSuccess("ok");
    }
}
