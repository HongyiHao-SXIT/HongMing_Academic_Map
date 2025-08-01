package com.hm_map.hm_map.service;

import com.hm_map.hm_map.entity.Paper;
import com.hm_map.hm_map.entity.User;
import org.springframework.data.domain.Page;

public interface AdminService {

    Page<User> getAllUsers(int page, int size);

    void setUserRole(Long userId, String role);

    void deleteUser(Long userId);

    Page<Paper> getAllPapers(int page, int size);

    void approvePaper(Long paperId);

    void deletePaper(Long paperId);

    Object getDashboardStats();
}
