/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50744
Source Host           : localhost:3306
Source Database       : restaurant

Target Server Type    : MYSQL
Target Server Version : 50744
File Encoding         : 65001

Date: 2025-08-05 22:46:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `login`
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `empid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `passwd` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='登录表';

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES ('2227612', '123456');

-- ----------------------------
-- Table structure for `rszl`
-- ----------------------------
DROP TABLE IF EXISTS `rszl`;
CREATE TABLE `rszl` (
  `empid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '员工号',
  `empname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '员工姓名',
  `pic` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '照片',
  `sex` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '男' COMMENT '性别',
  `addr` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '住址',
  `birth` date DEFAULT NULL COMMENT '出生日期',
  `Identity` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证',
  `xueli` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学历',
  `zhiwei` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职位',
  `maritalstatus` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '婚姻状况',
  PRIMARY KEY (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='人事管理表';

-- ----------------------------
-- Records of rszl
-- ----------------------------
INSERT INTO `rszl` VALUES ('2227612', '顺平', null, '男', null, null, null, null, '经理', null);
