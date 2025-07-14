CREATE DATABASE IF NOT EXISTS hm_map DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hm_map;

CREATE TABLE IF NOT EXISTS users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Primary Key: User ID',
  name VARCHAR(100) DEFAULT NULL COMMENT 'User Name',
  account VARCHAR(100) NOT NULL UNIQUE COMMENT 'Account (unique)',
  password VARCHAR(255) NOT NULL COMMENT 'Password (hashed)',
  birthday DATE DEFAULT NULL COMMENT 'Birthday',
  intro TEXT DEFAULT NULL COMMENT 'User Introduction',
  organization VARCHAR(100) DEFAULT NULL COMMENT 'Organization',
  department VARCHAR(100) DEFAULT NULL COMMENT 'Department',
  address VARCHAR(200) DEFAULT NULL COMMENT 'Address',
  post_code VARCHAR(20) DEFAULT NULL COMMENT 'Postal Code',
  field VARCHAR(100) DEFAULT NULL COMMENT 'Research Field'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User Table';

CREATE TABLE IF NOT EXISTS paper (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Primary Key: Paper ID',

    title VARCHAR(512) NOT NULL COMMENT 'Paper Title',
    abstract_text TEXT DEFAULT NULL COMMENT 'Abstract',
    authors VARCHAR(512) DEFAULT NULL COMMENT 'Authors (comma-separated)',
    affiliation VARCHAR(255) DEFAULT NULL COMMENT 'Affiliated Institution',
    keywords VARCHAR(255) DEFAULT NULL COMMENT 'Keywords (comma-separated)',

    publish_date DATE DEFAULT NULL COMMENT 'Publication Date',
    doi VARCHAR(100) UNIQUE DEFAULT NULL COMMENT 'Digital Object Identifier',
    publication VARCHAR(255) DEFAULT NULL COMMENT 'Journal or Conference Name',
    type VARCHAR(50) DEFAULT NULL COMMENT 'Paper Type (journal/conference/preprint)',
    categories VARCHAR(255) DEFAULT NULL COMMENT 'Subject Categories (comma-separated)',

    file_path VARCHAR(255) DEFAULT NULL COMMENT 'File Path or URL to PDF',
    uploader_id BIGINT DEFAULT NULL COMMENT 'Uploader User ID',

    is_public BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Is Publicly Visible',
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'Logical Delete Marker',

    view_count INT NOT NULL DEFAULT 0 COMMENT 'View Count',
    download_count INT NOT NULL DEFAULT 0 COMMENT 'Download Count',
    citation_count INT NOT NULL DEFAULT 0 COMMENT 'Citation Count',

    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',

    CONSTRAINT fk_paper_uploader FOREIGN KEY (uploader_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Academic Papers Table';


CREATE INDEX idx_paper_title ON paper(title);
CREATE INDEX idx_paper_keywords ON paper(keywords);
CREATE INDEX idx_paper_doi ON paper(doi);
