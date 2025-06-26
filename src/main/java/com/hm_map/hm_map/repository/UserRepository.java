package com.hm_map.hm_map.repository;

import com.hm_map.hm_map.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount(String account);
    Optional<User> findByAccountAndPassword(String account, String password);
}
