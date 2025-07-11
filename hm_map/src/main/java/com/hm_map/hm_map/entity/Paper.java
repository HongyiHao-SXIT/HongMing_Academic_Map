package com.hm_map.hm_map.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "paper")
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String abstractText;
    private String authors;
    private String affiliation;
    private String keywords;
    @Temporal(TemporalType.DATE)
    private Date publishDate;
    private String doi;
    private String publication;
    private String type;
    private String categories;
    private String filePath;
    private Long uploaderId;
    private Boolean isPublic;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    private Integer viewCount;
    private Integer downloadCount;
    private Integer citationCount;
}
