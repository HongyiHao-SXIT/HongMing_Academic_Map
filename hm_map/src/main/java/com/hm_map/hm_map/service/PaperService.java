package com.hm_map.hm_map.service;

import com.hm_map.hm_map.entity.Paper;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaperService {

  Paper uploadPaper(Paper paper);

  List<Paper> searchByTitle(String keyword);

  List<Paper> searchByKeyword(String keyword);

  List<Paper> searchByAuthor(String author);

  List<Paper> filterByTypeOrCategory(String type, String categories);

  List<Paper> getAllPublicPapers();

  Page<Paper> getAllPapersPaged(Pageable pageable);

  Page<Paper> listPaged(int page, int size);

  Optional<Paper> getPaperById(Long id);

  Paper updatePaper(Long id, Paper updated);

  Paper adminUpdatePaper(Long id, Paper updated);

  void deletePaper(Long id);

  void incrementViewCount(Long id);

  void incrementDownloadCount(Long id);

  Paper updateCitationCount(Long id, int citationCount);
}
