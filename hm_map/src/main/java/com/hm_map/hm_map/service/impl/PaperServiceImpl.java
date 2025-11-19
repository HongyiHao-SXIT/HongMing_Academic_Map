package com.hm_map.hm_map.service.impl;

import com.hm_map.hm_map.entity.Paper;
import com.hm_map.hm_map.repository.PaperRepository;
import com.hm_map.hm_map.service.PaperService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {

  @Autowired private PaperRepository paperRepository;

  @Override
  public Paper uploadPaper(Paper paper) {
    return paperRepository.save(paper);
  }

  @Override
  public List<Paper> searchByTitle(String keyword) {
    return paperRepository.findByTitleContainingIgnoreCaseAndIsDeletedFalse(keyword);
  }

  @Override
  public List<Paper> searchByKeyword(String keyword) {
    return paperRepository.findByKeywordsContainingIgnoreCaseAndIsDeletedFalse(keyword);
  }

  @Override
  public List<Paper> searchByAuthor(String author) {
    return paperRepository.findByAuthorsContainingIgnoreCaseAndIsDeletedFalse(author);
  }

  @Override
  public List<Paper> filterByTypeOrCategory(String type, String categories) {
    return paperRepository.findByTypeOrCategories(type, categories);
  }

  @Override
  public List<Paper> getAllPublicPapers() {
    return paperRepository.findByIsPublicTrueAndIsDeletedFalse();
  }

  @Override
  public Page<Paper> getAllPapersPaged(Pageable pageable) {
    return paperRepository.findAll(pageable);
  }

  @Override
  public Page<Paper> listPaged(int page, int size) {
    return paperRepository.findByIsDeletedFalse(PageRequest.of(page, size));
  }

  @Override
  public Optional<Paper> getPaperById(Long id) {
    return paperRepository.findByIdAndIsDeletedFalse(id);
  }

  @Override
  public Paper updatePaper(Long id, Paper updated) {
    Paper existing =
        paperRepository.findById(id).orElseThrow(() -> new RuntimeException("Paper not found"));

    existing.setTitle(updated.getTitle());
    existing.setAbstractText(updated.getAbstractText());
    existing.setAuthors(updated.getAuthors());
    existing.setKeywords(updated.getKeywords());
    existing.setType(updated.getType());
    existing.setCategories(updated.getCategories());
    existing.setTags(updated.getTags());
    existing.setUpdateTime(new Date());

    return paperRepository.save(existing);
  }

  @Override
  public Paper adminUpdatePaper(Long id, Paper updated) {
    Paper existing =
        paperRepository.findById(id).orElseThrow(() -> new RuntimeException("Paper not found"));

    existing.setTitle(updated.getTitle());
    existing.setAbstractText(updated.getAbstractText());
    existing.setAuthors(updated.getAuthors());
    existing.setKeywords(updated.getKeywords());
    existing.setType(updated.getType());
    existing.setCategories(updated.getCategories());
    existing.setTags(updated.getTags());
    existing.setIsPublic(updated.getIsPublic());
    existing.setIsDeleted(updated.getIsDeleted());
    existing.setUpdateTime(new Date());

    return paperRepository.save(existing);
  }

  @Override
  public void deletePaper(Long id) {
    Paper paper =
        paperRepository.findById(id).orElseThrow(() -> new RuntimeException("Paper not found"));
    paper.setIsDeleted(true);
    paper.setUpdateTime(new Date());
    paperRepository.save(paper);
  }

  @Override
  public void incrementViewCount(Long id) {
    paperRepository.incrementViewCount(id);
  }

  @Override
  public void incrementDownloadCount(Long id) {
    paperRepository.incrementDownloadCount(id);
  }

  @Override
  public Paper updateCitationCount(Long id, int citationCount) {
    Paper paper =
        paperRepository.findById(id).orElseThrow(() -> new RuntimeException("Paper not found"));
    paper.setCitationCount(citationCount);
    paper.setUpdateTime(new Date());
    return paperRepository.save(paper);
  }
}
