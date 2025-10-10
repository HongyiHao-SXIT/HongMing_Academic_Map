package com.hm_map.hm_map.security;

import com.hm_map.hm_map.entity.User;
import com.hm_map.hm_map.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByAccount(username);
        User user = userOpt.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String role = "ROLE_USER";

        return new org.springframework.security.core.userdetails.User(
                user.getAccount(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(role))
        );
    }
}
