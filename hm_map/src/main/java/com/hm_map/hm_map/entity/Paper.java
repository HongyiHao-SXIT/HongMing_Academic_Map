package com.hm_map.hm_map.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "paper")
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "abstract_text", columnDefinition = "TEXT")
    private String abstractText;

    private String authors;
    private String affiliation;
    private String keywords;
    private Date publishDate;
    private String doi;
    private String publication;
    private String type;
    private String categories;

    private String tags;

    @Column(name = "file_path")
    private String filePath;

    private Long uploaderId;
    private Boolean isPublic = true;
    private Boolean isDeleted = false;
    private Integer viewCount = 0;
    private Integer downloadCount = 0;
    private Integer citationCount = 0;

    private Date createTime;
    private Date updateTime;

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
}