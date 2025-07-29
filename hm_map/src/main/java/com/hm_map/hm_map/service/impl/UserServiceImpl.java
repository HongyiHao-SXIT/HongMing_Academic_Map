package com.hm_map.hm_map.service.impl;

import com.hm_map.hm_map.entity.User;
import com.hm_map.hm_map.repository.UserRepository;
import com.hm_map.hm_map.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User register(User user) {
        Optional<User> existing = userRepository.findByAccount(user.getAccount());
        if (existing.isPresent()) {
            throw new RuntimeException("This account has already been registered.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User login(String account, String password) {
        Optional<User> userOpt = userRepository.findByAccount(account);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid account or password.");
        }
        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid account or password.");
        }
        return user;
    }

    @Override
    public User deleteUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with ID " + id + " not found.");
        }
        User user = userOptional.get();
        userRepository.deleteById(id);
        return user;
    }
}
