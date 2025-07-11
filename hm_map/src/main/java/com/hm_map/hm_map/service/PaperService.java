package com.hm_map.hm_map.service;

import com.hm_map.hm_map.entity.Paper;

public interface PaperService {

    Object findPapers(String keyword, String author, String publication, String category, int page, int size,
            String sortBy, String order);

    Paper getPaperById(Long id);

    Paper savePaper(Paper paper);

    
}
