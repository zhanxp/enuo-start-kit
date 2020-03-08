/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost:3306
 Source Schema         : enuocms_test

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 08/03/2020 12:16:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `ent_types` int(11) NOT NULL DEFAULT '1',
  `ent_status` int(11) NOT NULL DEFAULT '1',
  `creater` int(11) NOT NULL,
  `updater` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `password` varchar(45) NOT NULL,
  `auth_key` varchar(45) DEFAULT NULL,
  `is_delete` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (1, 'admin', 1, 1, 1, 1, '2017-08-28 11:50:43', '2017-08-28 11:50:43', 'admin', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `ent_types` int(11) NOT NULL DEFAULT '1',
  `ent_status` int(11) NOT NULL DEFAULT '1',
  `creater` int(11) NOT NULL,
  `updater` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `intro` varchar(200) DEFAULT NULL,
  `pic_url` varchar(200) DEFAULT NULL,
  `content` text,
  `category_id` int(11) NOT NULL,
  `is_delete` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
BEGIN;
INSERT INTO `article` VALUES (1, 'test1', 0, 0, 1, 1, '2017-08-28 13:08:22', '2017-08-28 13:08:22', 'test1', 'http://www.123.com', '11111', 1, 0);
INSERT INTO `article` VALUES (2, 'article 0', 1, 1, 1, 1, '2017-08-30 11:15:03', '2017-08-30 11:15:03', 'intro 0', 'http://', 'content0', 1, 0);
INSERT INTO `article` VALUES (3, 'article 1', 1, 1, 1, 1, '2017-08-30 11:15:04', '2017-08-30 11:15:04', 'intro 1', 'http://', 'content1', 1, 0);
INSERT INTO `article` VALUES (4, 'article 2', 1, 1, 1, 1, '2017-08-30 11:15:04', '2017-08-30 11:15:04', 'intro 2', 'http://', 'content2', 2, 0);
INSERT INTO `article` VALUES (5, 'article 3', 1, 1, 1, 1, '2017-08-30 11:15:04', '2017-08-30 11:15:04', 'intro 3', 'http://', 'content3', 2, 0);
INSERT INTO `article` VALUES (6, 'article 4', 1, 1, 1, 1, '2017-08-30 11:15:04', '2017-08-30 11:15:04', 'intro 4', 'http://', 'content4', 2, 0);
INSERT INTO `article` VALUES (7, 'article 5', 1, 1, 1, 1, '2017-08-30 11:15:04', '2017-08-30 11:15:04', 'intro 5', 'http://', 'content5', 1, 0);
INSERT INTO `article` VALUES (8, 'article 6', 1, 1, 1, 1, '2017-08-30 11:15:04', '2017-08-30 11:15:04', 'intro 6', 'http://', 'content6', 3, 0);
INSERT INTO `article` VALUES (9, 'article 7', 1, 1, 1, 1, '2017-08-30 11:15:04', '2017-08-30 11:15:04', 'intro 7', 'http://', 'content7', 3, 0);
INSERT INTO `article` VALUES (10, 'article 8', 1, 1, 1, 1, '2017-08-30 11:15:04', '2017-08-30 11:15:04', 'intro 8', 'http://', 'content8', 3, 0);
INSERT INTO `article` VALUES (11, 'article 9', 1, 1, 1, 1, '2017-08-30 11:15:04', '2017-08-30 11:15:04', 'intro 9', 'http://', 'content9', 1, 0);
INSERT INTO `article` VALUES (12, '213122', 1, 1, 1, 1, '2020-03-07 22:53:42', '2020-03-07 22:57:08', 'ewrewr', NULL, 'weqeqwe', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `ent_types` int(11) NOT NULL DEFAULT '1',
  `ent_status` int(11) NOT NULL DEFAULT '1',
  `creater` int(11) NOT NULL,
  `updater` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `is_delete` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1, '专栏', 0, 1, 1, 1, '2017-08-28 14:26:01', '2017-08-28 14:26:01', 0);
INSERT INTO `category` VALUES (2, '资讯', 0, 1, 1, 1, '2020-03-07 19:46:15', '2020-03-07 19:46:21', 0);
INSERT INTO `category` VALUES (3, '新闻', 0, 1, 1, 1, '2020-03-07 19:46:35', '2020-03-07 19:46:38', 0);
INSERT INTO `category` VALUES (4, 'ddd2', 1, 1, 1, 1, '2020-03-07 23:03:49', '2020-03-07 23:03:52', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
