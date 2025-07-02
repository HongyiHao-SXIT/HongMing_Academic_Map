package com.hm_map.hm_map.controller;

import com.hm_map.hm_map.entity.Result;
import com.hm_map.hm_map.entity.User;
import com.hm_map.hm_map.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.register(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        userService.login(loginUser.getAccount(), loginUser.getPassword());
        return Result.success();
    }
}
