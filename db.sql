DROP DATABASE IF EXISTS shorten_uri;
CREATE DATABASE shorten_uri;
USE shorten_uri;

CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(50) UNIQUE NOT NULL,
    loginPw CHAR(100) NOT NULL,
    nickname CHAR(30) UNIQUE NOT NULL,
    email CHAR(100) NOT NULL
);

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'master',
loginPw = 'master',
nickname = '관리자',
email = 'jtj3926@gmail.com';

CREATE TABLE shortUri (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) NOT NULL,
    shortCode CHAR(3) NOT NULL,
    originUri CHAR(150) NOT NULL,
    `text` VARCHAR(100) NOT NULL,
    blanklessText CHAR(100) NOT NULL,
    accessCount INT(10) NOT NULL
);

SHOW INDEX FROM shortUri;

ALTER TABLE shortUri ADD UNIQUE INDEX indexname (memberId, blanklessText);

CREATE TABLE keyword (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    relTypeCode CHAR(100) NOT NULL,
    relId INT(10) UNSIGNED NOT NULL,
    keyStr CHAR(20) UNIQUE NOT NULL
);
