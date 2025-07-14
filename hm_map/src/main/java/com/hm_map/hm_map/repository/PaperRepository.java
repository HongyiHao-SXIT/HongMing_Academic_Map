package com.hm_map.hm_map.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hm_map.hm_map.entity.Paper;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    Page<Paper> findAll(Object object, Pageable pageable);

    List<Paper> findAllByIsDeletedFalse();

    
}