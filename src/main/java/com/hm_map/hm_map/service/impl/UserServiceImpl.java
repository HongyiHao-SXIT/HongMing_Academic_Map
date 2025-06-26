package com.hm_map.hm_map.service.impl;

import com.hm_map.hm_map.entity.User;
import com.hm_map.hm_map.repository.UserRepository;
import com.hm_map.hm_map.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        Optional<User> existing = userRepository.findByAccount(user.getAccount());
        if (existing.isPresent()) {
            throw new RuntimeException("该账号已被注册");
        }
        return userRepository.save(user);
    }

    @Override
    public User login(String account, String password) {
        return userRepository.findByAccountAndPassword(account, password)
                .orElseThrow(() -> new RuntimeException("账号或密码错误"));
    }
}
