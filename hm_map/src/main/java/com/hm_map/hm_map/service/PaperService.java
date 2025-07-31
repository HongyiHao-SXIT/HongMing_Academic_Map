package com.hm_map.hm_map.service;

import com.hm_map.hm_map.entity.Paper;

import java.util.List;

public interface PaperService {
    Paper uploadPaper(Paper paper);
    List<Paper> searchByTitle(String keyword);
    List<Paper> searchByKeyword(String keyword);
    List<Paper> getAllPublicPapers();
}