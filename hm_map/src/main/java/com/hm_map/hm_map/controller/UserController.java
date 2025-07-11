package com.hm_map.hm_map.controller;

import com.hm_map.hm_map.entity.Result;
import com.hm_map.hm_map.entity.User;
import com.hm_map.hm_map.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        try {
            userService.register(user);
            return Result.success("Registration successful");
        } catch (Exception e) {
            return Result.error("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        try {
            userService.login(loginUser.getAccount(), loginUser.getPassword());
            return Result.success("Login successful");
        } catch (Exception e) {
            return Result.error("Login failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteAccount(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return Result.success("User deleted successfully");
        } catch (Exception e) {
            return Result.error("Deletion failed: " + e.getMessage());
        }
    }
}
