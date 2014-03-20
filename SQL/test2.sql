/*
Navicat MySQL Data Transfer

Source Server         : 115.29.226.215
Source Server Version : 50535
Source Host           : 115.29.226.215:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-03-20 15:06:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `value` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('1', 'token', 'ewaterman_token_1990');

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `realname` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `building` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `intime` int(11) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for `debug`
-- ----------------------------
DROP TABLE IF EXISTS `debug`;
CREATE TABLE `debug` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `message` text CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of debug
-- ----------------------------
INSERT INTO `debug` VALUES ('8', '2014-03-19 15:36:24', 'dopost', 'dopost');
INSERT INTO `debug` VALUES ('9', '2014-03-19 15:36:24', 'reqBean.content', 'Ff');
INSERT INTO `debug` VALUES ('10', '2014-03-19 15:42:48', 'dopost', 'dopost');
INSERT INTO `debug` VALUES ('11', '2014-03-19 15:42:48', 'reqBean.content', '/::)');
INSERT INTO `debug` VALUES ('12', '2014-03-19 17:52:16', 'dopost', 'dopost');
INSERT INTO `debug` VALUES ('13', '2014-03-19 17:52:16', 'reqBean.content', '赵海荣下班了');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `toUserName` varchar(255) CHARACTER SET utf8 NOT NULL,
  `fromUserName` varchar(255) CHARACTER SET utf8 NOT NULL,
  `createTime` int(11) NOT NULL,
  `msgType` varchar(50) CHARACTER SET utf8 NOT NULL,
  `content` text CHARACTER SET utf8,
  `msgId` bigint(20) NOT NULL,
  `innerType` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `toUserName` (`toUserName`),
  UNIQUE KEY `fromUserName` (`fromUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) NOT NULL,
  `intime` int(11) NOT NULL,
  `workerid` int(11) DEFAULT NULL,
  `customerid` int(11) NOT NULL,
  `remark` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `orderlist`
-- ----------------------------
DROP TABLE IF EXISTS `orderlist`;
CREATE TABLE `orderlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderlist
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` char(32) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '202cb962ac59075b964b07152d234b70', '用户001');
INSERT INTO `user` VALUES ('2', 'rong', '202cb962ac59075b964b07152d234b70', '用户002');
