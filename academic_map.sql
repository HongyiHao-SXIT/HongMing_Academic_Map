-- Active: 1736786939860@@127.0.0.1@3306@academic
CREATE DATABASE academic;

CREATE TABLE user
(
    uid int(10) primary key NOT NULL AUTO_INCREMENT,
    uname varchar(30) NOT NULL,
    password varchar(255) NOT NULL,
    UNIQUE (uname)
);
