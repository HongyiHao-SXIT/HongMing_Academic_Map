package com.hm_map.hm_map.service.impl;

import com.hm_map.hm_map.entity.Paper;
import com.hm_map.hm_map.repository.PaperRepository;
import com.hm_map.hm_map.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Override
    public Paper uploadPaper(Paper paper) {
        paper.setCreateTime(new Date());
        paper.setUpdateTime(new Date());
        return paperRepository.save(paper);
    }

    @Override
    public List<Paper> searchByTitle(String keyword) {
        return paperRepository.findByTitleContaining(keyword);
    }

    @Override
    public List<Paper> searchByKeyword(String keyword) {
        return paperRepository.findByKeywordsContaining(keyword);
    }

    @Override
    public List<Paper> getAllPublicPapers() {
        return paperRepository.findAll().stream()
                .filter(p -> Boolean.TRUE.equals(p.getIsPublic()) && Boolean.FALSE.equals(p.getIsDeleted()))
                .toList();
    }
}