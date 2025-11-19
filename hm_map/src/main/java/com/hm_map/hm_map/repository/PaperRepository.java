package com.hm_map.hm_map.repository;

import com.hm_map.hm_map.entity.Paper;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PaperRepository
    extends PagingAndSortingRepository<Paper, Long>, CrudRepository<Paper, Long> {

  List<Paper> findByTitleContainingIgnoreCaseAndIsDeletedFalse(String keyword);

  List<Paper> findByKeywordsContainingIgnoreCaseAndIsDeletedFalse(String keyword);

  List<Paper> findByAuthorsContainingIgnoreCaseAndIsDeletedFalse(String author);

  List<Paper> findByTypeOrCategories(String type, String categories);

  List<Paper> findByIsPublicTrueAndIsDeletedFalse();

  Page<Paper> findByIsDeletedFalse(Pageable pageable);

  Optional<Paper> findByIdAndIsDeletedFalse(Long id);

  @Modifying
  @Transactional
  @Query("update Paper p set p.viewCount = p.viewCount + 1 where p.id = ?1")
  void incrementViewCount(Long id);

  @Modifying
  @Transactional
  @Query("update Paper p set p.downloadCount = p.downloadCount + 1 where p.id = ?1")
  void incrementDownloadCount(Long id);

  Page<Paper> findByIsDeletedFalse(Pageable pageable);

  long countByIsPublicTrueAndIsDeletedFalse();

  long countByIsDeletedTrue();
}
