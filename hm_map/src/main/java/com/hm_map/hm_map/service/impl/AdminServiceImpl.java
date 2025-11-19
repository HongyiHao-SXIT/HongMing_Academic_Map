package com.hm_map.hm_map.service.impl;

import com.hm_map.hm_map.entity.Paper;
import com.hm_map.hm_map.entity.User;
import com.hm_map.hm_map.repository.PaperRepository;
import com.hm_map.hm_map.repository.UserRepository;
import com.hm_map.hm_map.service.AdminService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

  @Autowired private UserRepository userRepository;

  @Autowired private PaperRepository paperRepository;

  @Override
  public Page<User> getAllUsers(int page, int size) {
    return userRepository.findAll(PageRequest.of(page, size, Sort.by("createTime").descending()));
  }

  @Override
  public void setUserRole(Long userId, String role) {
    User user =
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    user.setRole(role);
    userRepository.save(user);
  }

  @Override
  public void deleteUser(Long userId) {
    User user =
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    user.setIsDeleted(true);
    userRepository.save(user);
  }

  @Override
  public Page<Paper> getAllPapers(int page, int size) {
    return paperRepository.findByIsDeletedFalse(PageRequest.of(page, size));
  }

  @Override
  public void approvePaper(Long paperId) {
    Paper paper =
        paperRepository
            .findById(paperId)
            .orElseThrow(() -> new RuntimeException("Paper not found"));
    paper.setIsPublic(true);
    paperRepository.save(paper);
  }

  @Override
  public void deletePaper(Long paperId) {
    Paper paper =
        paperRepository
            .findById(paperId)
            .orElseThrow(() -> new RuntimeException("Paper not found"));
    paper.setIsDeleted(true);
    paperRepository.save(paper);
  }

  @Override
  public Object getDashboardStats() {
    Map<String, Object> stats = new HashMap<>();
    stats.put("userCount", userRepository.count());
    stats.put("paperCount", paperRepository.count());
    stats.put("publicPapers", paperRepository.countByIsPublicTrueAndIsDeletedFalse());
    stats.put("deletedPapers", paperRepository.countByIsDeletedTrue());
    return stats;
  }
}
