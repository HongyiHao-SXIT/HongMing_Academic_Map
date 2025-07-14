package com.hm_map.hm_map.controller;

import com.hm_map.hm_map.entity.Paper;
import com.hm_map.hm_map.entity.Result;
import com.hm_map.hm_map.service.PaperService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        return Result.success(paperService.findPapers(
                keyword, author, publication, category, page, size, sortBy, order
        ));
    }

    @GetMapping("/{id}")
    public Result getPaper(@PathVariable Long id) {
        Paper paper = paperService.getPaperById(id);
        return paper != null ? Result.success(paper) : Result.error("Paper not found");
    }

    @PostMapping
    public Result uploadPaper(@RequestBody Paper paper) {
        return Result.success(paperService.savePaper(paper));
    }

    @PutMapping("/{id}")
    public Result updatePaper(@PathVariable Long id, @RequestBody Paper paper) {
        return Result.success(paperService.updatePaper(id, paper));
    }

    @DeleteMapping("/{id}")
    public Result deletePaper(@PathVariable Long id) {
        paperService.deletePaperById(id);
        return Result.success("Paper deleted successfully");
    }

    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = paperService.storeFile(file);
            return Result.success(filePath);
        } catch (Exception e) {
            return Result.error("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/downloadFile/{filename}")
    public void downloadFile(@PathVariable String filename, HttpServletResponse response) {
        try {
            paperService.downloadFile(filename, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/uploadWithPaper")
    public Result uploadWithPaper(@RequestParam("file") MultipartFile file,
                                  @RequestParam("title") String title) {
        try {
            Paper paper = paperService.savePaperWithFile(file, title);
            return Result.success(paper);
        } catch (Exception e) {
            return Result.error("Upload with paper failed: " + e.getMessage());
        }
    }

    @GetMapping("/stats/keywords")
    public Result getKeywordStats() {
        return Result.success(paperService.getKeywordStats());
    }

    @GetMapping("/stats/publicationTrend")
    public Result getPublicationTrend() {
        return Result.success(paperService.getPublicationTrend());
    }
}
