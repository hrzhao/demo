DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) NOT NULL ,
`value`  varchar(255) ,
PRIMARY KEY (`id`),
UNIQUE INDEX `name` (`name`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

BEGIN;
INSERT INTO `config` VALUES ('1', 'token', 'ewaterman_token_1990'), ('2', 'original_id', 'gh_5a402ba88fa0');
COMMIT;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) NULL ,
`realname`  varchar(255) NULL ,
`type`  int(11) NULL ,
`building`  varchar(255) NULL ,
`room`  varchar(255) NULL ,
`address`  varchar(255) NULL ,
`intime`  datetime NULL ,
`phone`  varchar(255) NULL ,
`sex`  varchar(2) NULL ,
`lasttime`  datetime NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

DROP TABLE IF EXISTS `debug`;
CREATE TABLE `debug` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`time`  datetime NOT NULL ,
`name`  varchar(255) NULL ,
`message`  text NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=19

;


DROP TABLE IF EXISTS `EVENTS`;
CREATE TABLE `EVENTS` (
`EVENT_ID`  bigint(20) NOT NULL ,
`EVENT_DATE`  datetime NULL ,
`title`  varchar(255) NULL ,
PRIMARY KEY (`EVENT_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;



DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`toUserName`  varchar(255) NULL ,
`fromUserName`  varchar(255) NULL ,
`createTime`  datetime NULL ,
`msgType`  varchar(50) NULL ,
`content`  text NULL ,
`msgId`  bigint(20) NULL ,
`innerType`  int(11) NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=17

;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`number`  varchar(255) NULL ,
`intime`  int(11) NOT NULL ,
`workerid`  int(11) NULL ,
`customerid`  int(11) NOT NULL ,
`remark`  text NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `number` (`number`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;


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


DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) NULL ,
`create_time`  datetime NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=7

;


BEGIN;
INSERT INTO `test` VALUES ('1', 'hrzhao', null), ('2', 'hrzhao', '0000-00-00 00:00:00'), ('3', 'hrzhao', '2014-03-20 16:50:07'), ('4', 'test', '0000-00-00 00:00:00'), ('5', 'test', '0000-00-00 00:00:00'), ('6', 'hrzhao', '1970-01-16 22:40:31');
COMMIT;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`username`  varchar(255) NULL ,
`password`  varchar(32) NULL ,
`realname`  varchar(255) DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

BEGIN;
INSERT INTO `user` VALUES ('1', 'admin', '202cb962ac59075b964b07152d234b70', '用户001'), ('2', 'rong', '202cb962ac59075b964b07152d234b70', '用户002');
COMMIT;