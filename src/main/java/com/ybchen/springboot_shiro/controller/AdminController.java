package com.ybchen.springboot_shiro.controller;

import com.ybchen.springboot_shiro.utils.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Description：
 * @Author：chenyanbin
 * @Date：2021/1/3 7:22 下午
 * @Versiion：1.0
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @GetMapping("/video/video_list")
    public JsonData videoList() {
        List<String> list = Arrays.asList("docker", "k8s", "jenkins");
        return JsonData.buildSuccess(list);
    }
}
