/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 21/11/2019 11:55:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `reg_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `status` enum('正常','禁言') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '正常',
  `type` enum('管理员','普通用户') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '普通用户',
  `nickname` varchar(255) DEFAULT NULL,
  `gender` enum('男','女') DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
