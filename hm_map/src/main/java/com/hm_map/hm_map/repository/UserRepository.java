package com.hm_map.hm_map.repository;

import com.hm_map.hm_map.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount(String account);
    boolean existsByAccount(String account);
}