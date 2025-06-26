package com.hm_map.hm_map.controller;

import com.hm_map.hm_map.entity.User;
import com.hm_map.hm_map.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 注册
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.register(user);
        return "注册成功";
    }

    // 登录
    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {
        userService.login(loginUser.getAccount(), loginUser.getPassword());
        return "登录成功";
    }
}
