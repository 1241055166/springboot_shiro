package com.ybchen.springboot_shiro.controller;

import com.ybchen.springboot_shiro.utils.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author：chenyanbin
 * @Date：2021/1/3 9:41 下午
 * @Versiion：1.0
 */
@RestController
@RequestMapping("video")
public class VideoController {
    @GetMapping("update")
    public JsonData updateVideo() {
        return JsonData.buildSuccess("更新成功");
    }
    @GetMapping("add")
    public JsonData add(){
        return JsonData.buildSuccess("添加成功");
    }
}
