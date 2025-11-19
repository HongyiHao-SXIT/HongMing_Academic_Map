package com.hm_map.hm_map.service.impl;

import com.hm_map.hm_map.entity.User;
import com.hm_map.hm_map.repository.UserRepository;
import com.hm_map.hm_map.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public User register(User user) {
    if (userRepository.existsByAccount(user.getAccount())) {
      throw new RuntimeException("Account already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public User login(String account, String rawPassword) {
    Optional<User> userOpt = userRepository.findByAccount(account);
    User user =
        userOpt
            .filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()))
            .orElseThrow(() -> new RuntimeException("Invalid account or password"));

    return user;
  }
}
