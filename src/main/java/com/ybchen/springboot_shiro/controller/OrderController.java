package com.ybchen.springboot_shiro.controller;

import com.ybchen.springboot_shiro.utils.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description：
 * @Author：chenyanbin
 * @Date：2021/1/3 6:28 下午
 * @Versiion：1.0
 */
@RestController
@RequestMapping("authc")
public class OrderController {
    /**
     * 购买记录
     * @return
     */
    @GetMapping("/video/play_record")
    public JsonData findMyPlayRecord(){
        Map<String,String> recordMap=new HashMap<>();
        recordMap.put("1","SpringBoot");
        recordMap.put("2","SpringMvc");
        return JsonData.buildSuccess(recordMap);
    }
}
