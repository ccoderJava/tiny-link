/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : tiny-link

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 29/06/2021 13:39:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_tiny_link
-- ----------------------------
DROP TABLE IF EXISTS `t_tiny_link`;
CREATE TABLE `t_tiny_link` (
  `link_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `origin_link` varchar(512) NOT NULL COMMENT '源长链接',
  `origin_link_summary` varchar(32) NOT NULL COMMENT '源长链接摘要',
  `tiny_link` varchar(32) NOT NULL COMMENT '短链接',
  `link_type` varchar(1) NOT NULL COMMENT '生成方式,系统system自动生成, 用户自定义custom',
  `expired_time` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`link_id`),
  UNIQUE KEY `origin_link_summary_unindex` (`origin_link_summary`) COMMENT '源链接摘要唯一索引',
  UNIQUE KEY `tiny_link_unindex` (`tiny_link`) COMMENT '短链接唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
