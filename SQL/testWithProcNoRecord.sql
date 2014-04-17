/*
Navicat MySQL Data Transfer

Source Server         : 115.29.226.215
Source Server Version : 50535
Source Host           : 115.29.226.215:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-04-17 13:32:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `appconfig`
-- ----------------------------
DROP TABLE IF EXISTS `appconfig`;
CREATE TABLE `appconfig` (
`app_id`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`visible`  bit(1) NULL DEFAULT b'1' ,
`category_id`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`param`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`multi_instance`  bit(1) NULL DEFAULT NULL ,
`description`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`path`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`priority`  int(11) NULL DEFAULT NULL ,
`icon`  varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`app_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
`category_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`description`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`visible`  bit(1) NULL DEFAULT NULL ,
PRIMARY KEY (`category_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`value`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `name` (`name`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`realname`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`type`  int(11) NULL DEFAULT NULL ,
`building`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`room`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`address`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`intime`  datetime NULL DEFAULT NULL ,
`phone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sex`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`lasttime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Table structure for `debug`
-- ----------------------------
DROP TABLE IF EXISTS `debug`;
CREATE TABLE `debug` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`time`  datetime NOT NULL ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`message`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=140

;

-- ----------------------------
-- Table structure for `EVENTS`
-- ----------------------------
DROP TABLE IF EXISTS `EVENTS`;
CREATE TABLE `EVENTS` (
`EVENT_ID`  bigint(20) NOT NULL ,
`EVENT_DATE`  datetime NULL DEFAULT NULL ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`EVENT_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`toUserName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`fromUserName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`createTime`  datetime NULL DEFAULT NULL ,
`msgType`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`msgId`  bigint(20) NULL DEFAULT NULL ,
`event`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`picURL`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`innerType`  int(11) NULL DEFAULT NULL ,
`location_x`  double NULL DEFAULT NULL ,
`location_y`  double NULL DEFAULT NULL ,
`location_scale`  int(11) NULL DEFAULT NULL ,
`location_label`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`intime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=79

;

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`intime`  int(11) NOT NULL ,
`workerid`  int(11) NULL DEFAULT NULL ,
`customerid`  int(11) NOT NULL ,
`remark`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `number` (`number`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `orderlist`
-- ----------------------------
DROP TABLE IF EXISTS `orderlist`;
CREATE TABLE `orderlist` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`orderid`  int(11) NOT NULL ,
`amount`  int(11) NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `test`
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=4

;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`username`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`password`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`realname`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Procedure structure for `proc_userMsgList_get`
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_userMsgList_get`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `proc_userMsgList_get`()
BEGIN
SELECT
	c.`name`,
	COUNT(*) AS amount,
	c.realname,
	c.phone,
	c.address,
	c.room,
	c.building,
	c.type,
	c.intime,
	c.sex,
	max(m.createTime) as lastTime
FROM
	customer AS c
	INNER JOIN message AS m ON c.`name` = m.fromUserName
GROUP BY
	c.`name` ;
End
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `proc1`
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc1`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `proc1`(out s int)
begin
 
select count(*) into s from user;
 
end
;;
DELIMITER ;

-- ----------------------------
-- Auto increment value for `config`
-- ----------------------------
ALTER TABLE `config` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for `customer`
-- ----------------------------
ALTER TABLE `customer` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for `debug`
-- ----------------------------
ALTER TABLE `debug` AUTO_INCREMENT=140;

-- ----------------------------
-- Auto increment value for `message`
-- ----------------------------
ALTER TABLE `message` AUTO_INCREMENT=79;

-- ----------------------------
-- Auto increment value for `order`
-- ----------------------------
ALTER TABLE `order` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `orderlist`
-- ----------------------------
ALTER TABLE `orderlist` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `test`
-- ----------------------------
ALTER TABLE `test` AUTO_INCREMENT=4;

-- ----------------------------
-- Auto increment value for `user`
-- ----------------------------
ALTER TABLE `user` AUTO_INCREMENT=3;
