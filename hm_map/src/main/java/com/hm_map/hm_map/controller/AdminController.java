package com.hm_map.hm_map.controller;

import com.hm_map.hm_map.entity.Paper;
import com.hm_map.hm_map.entity.User;
import com.hm_map.hm_map.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  @Autowired private AdminService adminService;

  @GetMapping("/users")
  public Page<User> getUsers(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    return adminService.getAllUsers(page, size);
  }

  @PutMapping("/users/{id}/role")
  public void setUserRole(@PathVariable Long id, @RequestParam String role) {
    adminService.setUserRole(id, role);
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Long id) {
    adminService.deleteUser(id);
  }

  @GetMapping("/papers")
  public Page<Paper> getPapers(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    return adminService.getAllPapers(page, size);
  }

  @PutMapping("/papers/{id}/approve")
  public void approvePaper(@PathVariable Long id) {
    adminService.approvePaper(id);
  }

  @DeleteMapping("/papers/{id}")
  public void deletePaper(@PathVariable Long id) {
    adminService.deletePaper(id);
  }

  @GetMapping("/stats")
  public Object getStats() {
    return adminService.getDashboardStats();
  }
}
