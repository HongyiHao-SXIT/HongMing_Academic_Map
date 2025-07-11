package com.hm_map.hm_map.service.impl;

import com.hm_map.hm_map.entity.Paper;
import com.hm_map.hm_map.repository.PaperRepository;
import com.hm_map.hm_map.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Override
    public Page<Paper> findPapers(String keyword, String author, String publication, String category, int page, int size,
                                  String sortBy, String order) {

        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return paperRepository.findAll((root, query, cb) -> {
            List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();

            if (keyword != null && !keyword.isEmpty()) {
                predicates.add(cb.or(
                    cb.like(root.get("title"), "%" + keyword + "%"),
                    cb.like(root.get("abstractText"), "%" + keyword + "%"),
                    cb.like(root.get("keywords"), "%" + keyword + "%")
                ));
            }
            if (author != null && !author.isEmpty()) {
                predicates.add(cb.like(root.get("authors"), "%" + author + "%"));
            }
            if (publication != null && !publication.isEmpty()) {
                predicates.add(cb.equal(root.get("publication"), publication));
            }
            if (category != null && !category.isEmpty()) {
                predicates.add(cb.like(root.get("categories"), "%" + category + "%"));
            }

            return cb.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        }, pageable);
    }

    @Override
    public Page<Paper> getPaperById(Long id){

    }
}
