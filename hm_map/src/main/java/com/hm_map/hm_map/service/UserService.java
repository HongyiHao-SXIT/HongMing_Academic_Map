package com.hm_map.hm_map.service;

import com.hm_map.hm_map.entity.User;

public interface UserService {

    User register(User user);
    
    User login(String account, String rawPassword);
}
