/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : db0325

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2020-04-30 16:23:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for filter_chain_definition
-- ----------------------------
DROP TABLE IF EXISTS `filter_chain_definition`;
CREATE TABLE `filter_chain_definition` (
  `chain_Id` int(20) NOT NULL AUTO_INCREMENT,
  `chain_Name` varchar(20) DEFAULT NULL,
  `chain_Definition` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`chain_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of filter_chain_definition
-- ----------------------------
INSERT INTO `filter_chain_definition` VALUES ('1', '/login.jsp', 'anon');
INSERT INTO `filter_chain_definition` VALUES ('2', '/shiro_login', 'anon');
INSERT INTO `filter_chain_definition` VALUES ('3', '/add_user.jsp', 'anon');
INSERT INTO `filter_chain_definition` VALUES ('4', '/add_user', 'anon');
INSERT INTO `filter_chain_definition` VALUES ('5', '/shiro_logout', 'logout');
INSERT INTO `filter_chain_definition` VALUES ('6', '/user_Unique.jsp', 'authc,roles[user]');
INSERT INTO `filter_chain_definition` VALUES ('7', '/admin_Unique.jsp', 'authc,roles[admin]');
INSERT INTO `filter_chain_definition` VALUES ('8', '/list.jsp', 'user');
INSERT INTO `filter_chain_definition` VALUES ('9', '/**', 'authc');
