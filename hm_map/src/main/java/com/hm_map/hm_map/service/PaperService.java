package com.hm_map.hm_map.service;

import com.hm_map.hm_map.entity.Paper;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface PaperService {

    Page<Paper> findPapers(String keyword, String author, String publication,
                           String category, int page, int size, String sortBy, String order);

    Paper getPaperById(Long id);

    Paper savePaper(Paper paper);

    Paper updatePaper(Long id, Paper paper);

    void deletePaperById(Long id);

    String storeFile(MultipartFile file);

    void downloadFile(String filename, HttpServletResponse response);

    Paper savePaperWithFile(MultipartFile file, String title);

    Map<String, Integer> getKeywordStats();

    Map<Integer, Integer> getPublicationTrend();
}
