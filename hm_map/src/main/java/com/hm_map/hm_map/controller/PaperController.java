package com.hm_map.hm_map.controller;

import com.hm_map.hm_map.entity.Paper;
import com.hm_map.hm_map.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/papers")
public class PaperController {

    @Autowired
    private PaperService paperService;

    private final String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public Paper upload(@RequestParam("file") MultipartFile file,
                        @RequestParam("title") String title,
                        @RequestParam(value = "abstractText", required = false) String abstractText,
                        @RequestParam(value = "authors", required = false) String authors,
                        @RequestParam(value = "keywords", required = false) String keywords) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdirs();
        String originalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "_" + originalFilename;
        File dest = new File(uploadDir, fileName);
        file.transferTo(dest);
        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setAbstractText(abstractText);
        paper.setAuthors(authors);
        paper.setKeywords(keywords);
        paper.setFilePath(dest.getAbsolutePath());
        paper.setCreateTime(new Date());
        paper.setUpdateTime(new Date());
        paper.setIsPublic(true);
        paper.setIsDeleted(false);
        paper.setViewCount(0);
        paper.setDownloadCount(0);
        paper.setCitationCount(0);
        return paperService.uploadPaper(paper);
    }

    @GetMapping("/search")
    public List<Paper> searchByTitle(@RequestParam String keyword) {
        return paperService.searchByTitle(keyword);
    }

    @GetMapping("/keyword")
    public List<Paper> searchByKeyword(@RequestParam String keyword) {
        return paperService.searchByKeyword(keyword);
    }

    @GetMapping("/public")
    public List<Paper> listPublic() {
        return paperService.getAllPublicPapers();
    }

    @GetMapping("/list")
    public List<Paper> listPapers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Paper> paperPage = paperService.getPaperRepository().findAll(pageable);
        return paperPage.stream()
                .filter(p -> Boolean.TRUE.equals(p.getIsPublic()) && Boolean.FALSE.equals(p.getIsDeleted()))
                .toList();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) throws IOException {
        Paper paper = paperService.getPaperRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Paper not found"));
        if (paper.getFilePath() == null) {
            throw new RuntimeException("File not found");
        }
        Path path = Paths.get(paper.getFilePath());
        byte[] data = Files.readAllBytes(path);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName().toString() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }
}
