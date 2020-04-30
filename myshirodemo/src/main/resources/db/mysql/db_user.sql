/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : db0325

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2020-04-30 16:24:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for db_user
-- ----------------------------
DROP TABLE IF EXISTS `db_user`;
CREATE TABLE `db_user` (
  `USERID` varchar(20) NOT NULL,
  `USERPASS` varchar(100) DEFAULT NULL,
  `USERNAME` varchar(20) DEFAULT NULL,
  `DB_SOURCE` varchar(255) DEFAULT NULL,
  `ROLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_user
-- ----------------------------
INSERT INTO `db_user` VALUES ('001', '123', 'tom', 'db0325', null);
INSERT INTO `db_user` VALUES ('002', '123', 'jerry', 'db0325', null);
INSERT INTO `db_user` VALUES ('003', '123', 'Donalg', 'db0325', null);
INSERT INTO `db_user` VALUES ('004', '123', 'Mickey', 'db0325', null);
INSERT INTO `db_user` VALUES ('005', '123', 'tony', 'db0325', null);
INSERT INTO `db_user` VALUES ('006', '0cfbba728c6af668878bc9b0317bf2ec', 'Sophie', 'db0325', 'user');
INSERT INTO `db_user` VALUES ('007', 'e668414dcfdeba408d65362ee99d8f80', 'Truman', 'db0325', 'admin');
INSERT INTO `db_user` VALUES ('008', '6aeb522001eb3831a893ef4972470648', 'paul', 'db0325', 'user');
