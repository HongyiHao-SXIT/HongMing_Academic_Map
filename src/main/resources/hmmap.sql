CREATE DATABASE IF NOT EXISTS hm_map;

USE hm_map;

CREATE TABLE IF NOT EXISTS user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  account VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  birthday DATE,
  intro TEXT,
  organization VARCHAR(100),
  department VARCHAR(100),
  address VARCHAR(200),
  post_code VARCHAR(20),
  field VARCHAR(100)
);
