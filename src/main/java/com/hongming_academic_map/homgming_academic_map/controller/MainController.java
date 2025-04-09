package com.hongming_academic_map.homgming_academic_map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    @GetMapping("/")
    public String index() {
        return "login";
    }
    
    @GetMapping("/index")
    public String login() {
        return "index";
    }
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }
}