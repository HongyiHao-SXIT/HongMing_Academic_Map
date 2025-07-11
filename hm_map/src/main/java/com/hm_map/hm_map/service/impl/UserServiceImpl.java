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
            throw new RuntimeException("This account has already been registered.");
        }
        return userRepository.save(user);
    }


    @Override
    public User login(String account, String password) {
        return userRepository.findByAccountAndPassword(account, password)
                .orElseThrow(() -> new RuntimeException("Invalid account or password."));
    }

    @Override
    public User deleteUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User with ID " + id + " not found.");
        }

        User user = userOptional.get();
        userRepository.deleteById(id);
        return user;
    }
}
