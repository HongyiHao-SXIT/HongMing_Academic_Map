package com.hm_map.hm_map.repository;

import com.hm_map.hm_map.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {
    List<Paper> findByTitleContaining(String keyword);
}
