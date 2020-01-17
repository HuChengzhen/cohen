# create database if not exists cohen;
# use cohen;
#
# create table if not exists user
# (
#     id              int auto_increment  not null,
#     username        varchar(50) unique  not null,
#     email           varchar(255) unique not null,
#     password        CHAR(60)            not null,
#     create_date     datetime            not null,
#     last_login_date datetime            null,
#     enabled         tinyint(4)          NOT NULL DEFAULT '1',
#     roles           text,
#     primary key (id),
#     index username_index (username)
# ) engine = InnoDB;
#
# INSERT INTO `user`
# VALUES (null, 'hcz', 'hcz1995@qq.com', '$2a$10$qTU7t4u3Si9AwusJTGFAZ.LdfYwASPxI65spRsoMkXDgHSfJrQekO',
#         '2020-01-13 20:24:35', '2020-01-15 15:50:54', 1, 'ROLE_USER,ROLE_ADMIN'),
#        (null, 'cohen', 'cohen@gmail.com', '$2a$10$fBzyNHG2FkmYmTpki4Fim.CJrWMYKIhBfMnzXiFrkEFGVVNsHwwni',
#         '2020-01-14 11:05:51', '2020-01-14 20:28:00', 1, 'ROLE_USER');
#
#
# create table if not exists article
# (
#     id            int          not null auto_increment,
#     title         varchar(255) not null,
#     article       LONGTEXT     null,
#     author_id     int          not null,
#     uploaded_date datetime     not null,
#     edit_date     datetime     not null,
#     primary key (id),
#     foreign key fk_user (author_id)
#         references user (id)
#         on update cascade
#         on delete restrict
# ) engine = InnoDB;
#
#
# create table if not exists comment
# (
#     id           int      not null auto_increment,
#     user_id      int      not null,
#     article_id   int      not null,
#     comment      text     not null,
#     comment_date datetime not null,
#     primary key (id),
#     foreign key fk_article (article_id)
#         references article (id)
#         on update cascade
#         on delete restrict,
#     foreign key fk_user (user_id)
#         references user (id)
#         on update cascade
#         on delete restrict
# ) engine = InnoDB;


-- MySQL dump 10.13  Distrib 8.0.19, for Linux (x86_64)
--
-- Host: localhost    Database: cohen
-- ------------------------------------------------------
-- Server version	8.0.19


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

create database if not exists cohen;
use cohen;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article`
(
    `id`            int          NOT NULL AUTO_INCREMENT,
    `title`         varchar(255) NOT NULL,
    `article`       longtext,
    `author_id`     int          NOT NULL,
    `uploaded_date` datetime     NOT NULL,
    `edit_date`     datetime     NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_user` (`author_id`),
    CONSTRAINT `article_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 232
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article`
    DISABLE KEYS */;
INSERT INTO `article`
VALUES (1, 'title', 'article', 9, '2020-01-13 20:55:43', '2020-01-13 20:55:43'),
       (2, 'title', 'article', 9, '2020-01-13 23:44:32', '2020-01-13 23:44:32'),
       (3, 'title', 'article', 9, '2020-01-13 23:46:22', '2020-01-13 23:46:22'),
       (4, 'title', 'article', 9, '2020-01-13 23:46:22', '2020-01-13 23:46:22'),
       (5, 'title', 'article', 9, '2020-01-13 23:46:23', '2020-01-13 23:46:23'),
       (6, 'title', 'article', 9, '2020-01-13 23:46:24', '2020-01-13 23:46:24'),
       (8, 'title', 'articleaaaa', 9, '2020-01-14 00:28:48', '2020-01-14 00:28:48'),
       (17, 'title', 'article', 13, '2020-01-14 11:23:49', '2020-01-14 11:23:49'),
       (18, 'title', 'article', 13, '2020-01-14 11:23:50', '2020-01-14 11:23:50'),
       (20, 'qwercv', 'asgrcvoiuzv', 9, '2020-01-14 15:44:03', '2020-01-14 15:44:03'),
       (21, 'qwercv', 'asgrcvoiuzv', 9, '2020-01-14 15:44:12', '2020-01-14 15:44:12'),
       (22, 'qwercv', 'asgrcvoiuzv', 9, '2020-01-14 22:44:51', '2020-01-14 22:44:51');
/*!40000 ALTER TABLE `article`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment`
(
    `id`           int      NOT NULL AUTO_INCREMENT,
    `user_id`      int      NOT NULL,
    `article_id`   int      NOT NULL,
    `comment`      text     NOT NULL,
    `comment_date` datetime NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_article` (`article_id`),
    KEY `fk_user` (`user_id`),
    CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 129
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment`
    DISABLE KEYS */;
INSERT INTO `comment`
VALUES (4, 9, 1, 'wetasdfasf', '2020-01-14 15:52:49'),
       (5, 9, 1, 'wetasdfasf', '2020-01-14 15:52:51'),
       (6, 9, 1, 'wetasdfasf', '2020-01-14 15:52:52'),
       (7, 9, 1, 'wetasdfasf', '2020-01-14 15:52:53'),
       (8, 9, 1, 'wetasdfasf', '2020-01-14 22:44:58');
/*!40000 ALTER TABLE `comment`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`              int          NOT NULL AUTO_INCREMENT,
    `username`        varchar(50)  NOT NULL,
    `email`           varchar(255) NOT NULL,
    `password`        char(60)     NOT NULL,
    `create_date`     datetime     NOT NULL,
    `last_login_date` datetime              DEFAULT NULL,
    `enabled`         tinyint      NOT NULL DEFAULT '1',
    `roles`           text,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username_UNIQUE` (`username`),
    UNIQUE KEY `email_UNIQUE` (`email`),
    KEY `username_index` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 357
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user`
VALUES (9, 'hcz', 'hcz1995@qq.com', '$2a$10$qTU7t4u3Si9AwusJTGFAZ.LdfYwASPxI65spRsoMkXDgHSfJrQekO',
        '2020-01-13 20:24:35', '2020-01-15 15:50:54', 1, 'ROLE_USER,ROLE_ADMIN'),
       (13, 'cohen', 'cohen@gmail.com', '$2a$10$fBzyNHG2FkmYmTpki4Fim.CJrWMYKIhBfMnzXiFrkEFGVVNsHwwni',
        '2020-01-14 11:05:51', '2020-01-14 20:28:00', 1, 'ROLE_USER'),
       (19, 'cohen123', 'cohen123@gmail.com', '$2a$10$mJeqrHt1lUGkvOOGrW/Sj.zZoO0CaP/guTCjc8N9zpqIQHobgxCRa',
        '2020-01-14 15:05:30', NULL, 1, 'ROLE_USER'),
       (21, 'cohen12345', 'cohen1235@gmail.com', '$2a$10$GML9kgj6xtzV2ZeUS6vXrOeQFEvs1W3Snkej2cyjON3oWaXuNVzYq',
        '2020-01-14 19:03:03', NULL, 1, 'ROLE_USER'),
       (22, 'cohen12364', 'cohen123666@gmail.com', '$2a$10$wh6eTMWnAD6kQ1Zyy5dh6uXFbHfHfJyt456dwxj7AQO7N6/kJIBmC',
        '2020-01-14 19:51:32', NULL, 1, 'ROLE_USER'),
       (23, 'cohen3', 'cohen3@gmail.com', '$2a$10$U0JHCVzBqYxYFBY/mG1ff.bKau1NnV5irbj6AOZjoMQSA0tVhQ13K',
        '2020-01-14 23:14:44', NULL, 1, 'ROLE_USER');
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2020-01-16 18:30:36