package com.hm_map.hm_map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.hm_map.hm_map.service.PaperService;
import com.hm_map.hm_map.entity.Result;
import com.hm_map.hm_map.entity.Paper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/papers")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @GetMapping
    public Result listPapers(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publication,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "publishDate") String sortBy,
            @RequestParam(defaultValue = "desc") String order) {

        return Result.success(paperService.findPapers(keyword, author, publication, category, page, size, sortBy, order));
    }

    @GetMapping("/{id}")
    public Result getPaper(@PathVariable Long id) {
        Paper paper = paperService.getPaperById(id);
        return paper != null ? Result.success(paper) : Result.error("Paper not found");
    }

    @PostMapping
    public Result uploadPaper(@RequestBody Paper paper) {
        Paper savePaper = paperService.savePaper(paper);
        return Result.success();
    }
    
    
    
}
