package com.hm_map.hm_map.service.impl;

import com.hm_map.hm_map.entity.Paper;
import com.hm_map.hm_map.repository.PaperRepository;
import com.hm_map.hm_map.service.PaperService;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class PaperServiceImpl implements PaperService {

    private static final String UPLOAD_DIR = "uploads/papers/";

    @Autowired
    private PaperRepository paperRepository;

    @Override
    public Page<Paper> findPapers(String keyword, String author, String publication, String category,
                                  int page, int size, String sortBy, String order) {

        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Paper> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

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

            predicates.add(cb.isFalse(root.get("isDeleted")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return paperRepository.findAll(specification, pageable);
    }

    @Override
    public Paper getPaperById(Long id) {
        return paperRepository.findById(id).orElse(null);
    }

    @Override
    public Paper savePaper(Paper paper) {
        return paperRepository.save(paper);
    }

    @Override
    public Paper updatePaper(Long id, Paper paper) {
        Optional<Paper> existing = paperRepository.findById(id);
        if (existing.isEmpty()) {
            throw new RuntimeException("Paper not found");
        }
        paper.setId(id);
        return paperRepository.save(paper);
    }

    @Override
    public void deletePaperById(Long id) {
        Optional<Paper> existing = paperRepository.findById(id);
        if (existing.isPresent()) {
            Paper paper = existing.get();
            paper.setIsDeleted(true);
            paperRepository.save(paper);
        } else {
            throw new RuntimeException("Paper not found");
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        try {
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = UPLOAD_DIR + filename;

            file.transferTo(new File(filePath));
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }

    @Override
    public void downloadFile(String filename, HttpServletResponse response) {
        String filePath = UPLOAD_DIR + filename;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found");
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);

        try (FileInputStream fis = new FileInputStream(file);
             ServletOutputStream os = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException("File download failed", e);
        }
    }

    @Override
    public Paper savePaperWithFile(MultipartFile file, String title) {
        String filename = storeFile(file);

        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setFilePath(filename);
        paper.setIsPublic(true);
        paper.setIsDeleted(false);
        return paperRepository.save(paper);
    }

    @Override
    public Map<String, Integer> getKeywordStats() {
        List<Paper> papers = paperRepository.findAllByIsDeletedFalse();

        Map<String, Integer> keywordCount = new HashMap<>();
        for (Paper paper : papers) {
            String keywords = paper.getKeywords();
            if (keywords != null && !keywords.isEmpty()) {
                String[] splitKeywords = keywords.split(",");
                for (String kw : splitKeywords) {
                    kw = kw.trim().toLowerCase();
                    keywordCount.put(kw, keywordCount.getOrDefault(kw, 0) + 1);
                }
            }
        }
        return keywordCount;
    }

    @Override
    public Map<Integer, Integer> getPublicationTrend() {
        List<Paper> papers = paperRepository.findAllByIsDeletedFalse();

        Map<Integer, Integer> yearCount = new TreeMap<>();
        for (Paper paper : papers) {
            if (paper.getPublishDate() != null) {
                int year = paper.getPublishDate().getYear() + 1900;
                yearCount.put(year, yearCount.getOrDefault(year, 0) + 1);
            }
        }
        return yearCount;
    }
}
