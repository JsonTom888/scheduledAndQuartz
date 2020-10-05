/*
Navicat MySQL Data Transfer

Source Server         : 本地Mysql
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : quartz

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-10-05 21:38:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for schedule_info
-- ----------------------------
DROP TABLE IF EXISTS `schedule_info`;
CREATE TABLE `schedule_info` (
  `id` bigint(20) NOT NULL,
  `execute_count` int(11) DEFAULT NULL,
  `job_detail_group` varchar(255) DEFAULT NULL,
  `job_detail_name` varchar(255) DEFAULT NULL,
  `trigger_group` varchar(255) DEFAULT NULL,
  `trigger_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
