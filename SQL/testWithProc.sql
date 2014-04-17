/*
Navicat MySQL Data Transfer

Source Server         : 115.29.226.215
Source Server Version : 50535
Source Host           : 115.29.226.215:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-04-17 13:31:36
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
-- Records of appconfig
-- ----------------------------
BEGIN;
INSERT INTO `appconfig` VALUES ('1e8e7f6f-091c-49e9-969d-c1c6256ce32a', '综合地图展示', '', '82d6aeaa-9e11-455f-ba1d-312dc87b4445', '{\"extent\":{\"xmin\":112.9777336120808,\"ymin\":22.546251101910844,\"xmax\":113.1986618042192,\"ymax\":22.641501525817873}}', '', '地图展示', 'plugin/GisModule.swf', '0', 'assets/menuIcons/icon_map.png'), ('24c71b11-01da-4aa6-8022-4ab99c4a99b9', '系统管理', '', null, null, '', null, null, '0', null), ('2A871243-8F19-0CBD-77AB-6521BEA11799', 'Test模块', '', '24c71b11-01da-4aa6-8022-4ab99c4a99b9', null, '', null, 'plugin/TestModule.swf', '2', 'assets/menuIcons/graph_64.png'), ('4d7665a6-175c-49e1-b89d-d8628157355e', '模块配置', '', '24c71b11-01da-4aa6-8022-4ab99c4a99b9', '{\"author\":\"HRZhao\"}', '', '基础模块，管理整个系统', 'plugin/AppStore.swf', '15', 'assets/menuIcons/settings_64.png'), ('82d6aeaa-9e11-455f-ba1d-312dc87b4445', '数据分析', '', null, null, '', null, null, '0', null), ('C382C871-7EE3-37B9-2EFC-69A6F74BAB69', '微信消息', '', '24c71b11-01da-4aa6-8022-4ab99c4a99b9', null, '', '查看来自微用户发来的消息', 'plugin/MessageMng.swf', '15', 'assets/menuIcons/paramrecord_64.png');
COMMIT;

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
-- Records of category
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of config
-- ----------------------------
BEGIN;
INSERT INTO `config` VALUES ('1', 'token', 'ewaterman_token_1990'), ('2', 'original_id', 'gh_5a402ba88fa0');
COMMIT;

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
-- Records of customer
-- ----------------------------
BEGIN;
INSERT INTO `customer` VALUES ('1', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '海荣', '0', null, null, '邑大', '2014-03-23 15:11:12', null, null, '2014-03-23 15:11:12'), ('2', 'owoPQjm5toXCKe9gD_-dKSTYfMoA', null, '2', null, null, null, '2014-04-03 18:20:42', null, null, '2014-04-03 18:20:42'), ('3', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '蔡春兰', '0', null, null, '五邑大学40栋1013', '2014-04-08 16:53:52', null, null, '2014-04-08 16:53:52'), ('4', 'owoPQjm2AHeU_SgAeUHqIZVUha2E', '/::P', '0', null, null, '海璟印象城', '2014-04-09 14:10:08', null, null, '2014-04-09 14:10:08'), ('5', 'owoPQjs9sCXvqZEazVm-fUUCj0fI', null, '2', null, null, null, '2014-04-17 13:30:33', null, null, '2014-04-17 13:30:33');
COMMIT;

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
AUTO_INCREMENT=138

;

-- ----------------------------
-- Records of debug
-- ----------------------------
BEGIN;
INSERT INTO `debug` VALUES ('78', '2014-03-21 17:55:08', 'SrcXML', '<xml><URL><![CDATA[http://115.29.226.215/Demo/Enterance]]></URL><ToUserName><![CDATA[内容要我]]></ToUserName><FromUserName><![CDATA[adsfasd]]></FromUserName><CreateTime>1234123412</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[我去]]></Content><MsgId>2341234123</MsgId></xml>'), ('79', '2014-03-21 17:55:27', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395395728</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你的]]></Content><MsgId>5993179016938205991</MsgId></xml>'), ('80', '2014-03-21 21:02:49', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395406969</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[unsubscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>'), ('81', '2014-03-21 21:03:45', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395407025</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>'), ('82', '2014-03-21 21:05:07', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395407106</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content><MsgId>5993227885076100380</MsgId></xml>'), ('83', '2014-03-21 21:06:19', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395407178</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/8DgJya2a4eiciaYptNJ1XvuNtLsI4sTkehibPuO7pJREWNyK4YV5rUHLgC63vicuRxB0MaiaMDibQtyBwJF3zezm40rQ/0]]></PicUrl><MsgId>5993228194313745695</MsgId><MediaId><![CDATA[ZpPWAACbEEICawkBwjwa2q3DuTCqoJndEY5z8PunuKPJTC4hUxdSwSau9BcHaqkK]]></MediaId></xml>'), ('84', '2014-03-21 21:06:58', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395407218</CreateTime><MsgType><![CDATA[location]]></MsgType><Location_X>22.557110</Location_X><Location_Y>113.954358</Location_Y><Scale>17</Scale><Label><![CDATA[中国深圳市南山区西丽松坪山路口 邮政编码: 518055]]></Label><MsgId>5993228366112437538</MsgId></xml>'), ('85', '2014-03-21 21:28:04', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395408483</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[unsubscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>'), ('86', '2014-03-21 21:28:38', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395408515</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>'), ('87', '2014-03-21 21:42:24', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395409345</CreateTime><MsgType><![CDATA[voice]]></MsgType><MediaId><![CDATA[v5YM7XoYwkERkzWRoWMIW4SzB3NObssM_Xs4SCe4KUG-XbuYbiLo8vjaeUVCH9LY]]></MediaId><Format><![CDATA[amr]]></Format><MsgId>5993237501507876227</MsgId><Recognition><![CDATA[]]></Recognition></xml>'), ('88', '2014-03-21 21:55:47', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395410148</CreateTime><MsgType><![CDATA[location]]></MsgType><Location_X>22.556987</Location_X><Location_Y>113.954374</Location_Y><Scale>17</Scale><Label><![CDATA[中国深圳市南山区西丽松坪山路口 邮政编码: 518055]]></Label><MsgId>5993240950366614975</MsgId></xml>'), ('89', '2014-03-21 22:17:27', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395411446</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[Ht作品集]]></Content><MsgId>5993246525234165229</MsgId></xml>'), ('90', '2014-03-21 23:20:03', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395415203</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content><MsgId>5993262661426296525</MsgId></xml>'), ('91', '2014-03-21 23:20:21', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395415221</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[赵海荣]]></Content><MsgId>5993262738735707855</MsgId></xml>'), ('92', '2014-03-21 23:25:41', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395415541</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content><MsgId>5993264113125242585</MsgId></xml>'), ('93', '2014-03-21 23:25:55', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395415555</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[赵海荣]]></Content><MsgId>5993264173254784731</MsgId></xml>'), ('94', '2014-03-22 11:45:34', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395459934</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[我是]]></Content><MsgId>5993454779608414643</MsgId></xml>'), ('95', '2014-03-22 11:45:52', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395459951</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[赵海荣]]></Content><MsgId>5993454852622858677</MsgId></xml>'), ('96', '2014-03-22 11:46:31', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395459992</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[赵海荣]]></Content><MsgId>5993455028716517816</MsgId></xml>'), ('97', '2014-03-22 11:55:13', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395460513</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content><MsgId>5993457266394479041</MsgId></xml>'), ('98', '2014-03-22 11:55:20', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395460521</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[赵海荣]]></Content><MsgId>5993457300754217411</MsgId></xml>'), ('99', '2014-03-22 13:37:22', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395466641</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你女]]></Content><MsgId>5993483585954069059</MsgId></xml>'), ('100', '2014-03-22 13:37:28', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395466649</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[赵海荣]]></Content><MsgId>5993483620313807429</MsgId></xml>'), ('101', '2014-03-22 13:37:51', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395466671</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[松坪村]]></Content><MsgId>5993483714803087943</MsgId></xml>'), ('102', '2014-03-22 13:38:00', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395466680</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[民]]></Content><MsgId>5993483753457793609</MsgId></xml>'), ('103', '2014-03-22 13:43:07', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395466987</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[unsubscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>'), ('104', '2014-03-22 13:43:46', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395467025</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>'), ('105', '2014-03-22 13:44:08', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395467048</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[赵海荣]]></Content><MsgId>5993485334005758557</MsgId></xml>'), ('106', '2014-03-22 13:44:24', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395467064</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[长布]]></Content><MsgId>5993485402725235298</MsgId></xml>'), ('107', '2014-03-22 13:44:41', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395467081</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[。]]></Content><MsgId>5993485475739679335</MsgId></xml>'), ('108', '2014-03-22 13:44:52', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395467092</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/8DgJya2a4e8enASnibkj5UbB8gVtv0d9jnLSF3vlIGHBa37F99icFOzhLCjh7ia2AOMYphXvNBq1eXfQ4jIgpiaXAQ/0]]></PicUrl><MsgId>5993485522984319596</MsgId><MediaId><![CDATA[G25YTDBmnTiug7gkVE952AAGbpzlPW74ieSlUAdUK7LNIa3jpcH5grUyecTsitIH]]></MediaId></xml>'), ('109', '2014-03-22 15:16:00', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395472560</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你是？]]></Content><MsgId>5993509007865494339</MsgId></xml>'), ('110', '2014-03-22 15:16:14', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395472573</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[赵海荣]]></Content><MsgId>5993509063700069189</MsgId></xml>'), ('111', '2014-03-22 15:16:27', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395472587</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[松坏村]]></Content><MsgId>5993509123829611335</MsgId></xml>'), ('112', '2014-03-22 15:18:07', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395472687</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content><MsgId>5993509553326340938</MsgId></xml>'), ('113', '2014-03-23 15:11:12', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395558672</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[unsubscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>'), ('114', '2014-03-23 15:37:41', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395560261</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>'), ('115', '2014-03-23 15:38:10', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395560288</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[海荣]]></Content><MsgId>5993885796756439594</MsgId></xml>'), ('116', '2014-03-23 15:38:21', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395560301</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[海荣]]></Content><MsgId>5993885852591014445</MsgId></xml>'), ('117', '2014-03-23 15:38:47', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395560327</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[邑大]]></Content><MsgId>5993885964260164143</MsgId></xml>'), ('118', '2014-03-23 15:38:55', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395560333</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[？]]></Content><MsgId>5993885990029967921</MsgId></xml>'), ('119', '2014-03-23 15:49:36', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1395560976</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[黄嘉霖]]></Content><MsgId>5993888751693939256</MsgId></xml>'), ('120', '2014-04-03 18:20:42', 'SrcXML', '<xml><ToUserName><![CDATA[gh_db247177973e]]></ToUserName><FromUserName><![CDATA[owoPQjm5toXCKe9gD_-dKSTYfMoA]]></FromUserName><CreateTime>1396520441</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[unsubscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>'), ('121', '2014-04-08 16:53:52', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1396947231</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[我想订水]]></Content><MsgId>5999842671582896276</MsgId></xml>'), ('122', '2014-04-08 16:53:59', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1396947239</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[蔡春兰]]></Content><MsgId>5999842705942634648</MsgId></xml>'), ('123', '2014-04-08 16:54:20', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1396947260</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[五邑大学40栋1013]]></Content><MsgId>5999842796136947874</MsgId></xml>'), ('124', '2014-04-08 16:54:25', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1396947265</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[嗯]]></Content><MsgId>5999842817611784356</MsgId></xml>'), ('125', '2014-04-08 17:27:01', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1396949220</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[我想订水]]></Content><MsgId>5999851214272848185</MsgId></xml>'), ('126', '2014-04-09 11:13:23', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1397013203</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[en]]></Content><MsgId>6000126019165350243</MsgId></xml>'), ('127', '2014-04-09 11:13:25', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1397013205</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[好呀]]></Content><MsgId>6000126027755284837</MsgId></xml>'), ('128', '2014-04-09 14:10:08', 'SrcXML', '<xml><ToUserName><![CDATA[gh_db247177973e]]></ToUserName><FromUserName><![CDATA[owoPQjm2AHeU_SgAeUHqIZVUha2E]]></FromUserName><CreateTime>1397023808</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>'), ('129', '2014-04-09 14:16:04', 'SrcXML', '<xml><ToUserName><![CDATA[gh_db247177973e]]></ToUserName><FromUserName><![CDATA[owoPQjm2AHeU_SgAeUHqIZVUha2E]]></FromUserName><CreateTime>1397024164</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[/::P]]></Content><MsgId>6000173096301816427</MsgId></xml>'), ('130', '2014-04-09 14:16:55', 'SrcXML', '<xml><ToUserName><![CDATA[gh_db247177973e]]></ToUserName><FromUserName><![CDATA[owoPQjm2AHeU_SgAeUHqIZVUha2E]]></FromUserName><CreateTime>1397024215</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[海璟印象城]]></Content><MsgId>6000173315345148527</MsgId></xml>'), ('131', '2014-04-09 15:53:45', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1397030025</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[好]]></Content><MsgId>6000198269105204720</MsgId></xml>'), ('132', '2014-04-09 15:54:38', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1397030078</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[我要定水]]></Content><MsgId>6000198496738471414</MsgId></xml>'), ('133', '2014-04-09 15:55:08', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1397030108</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[订水]]></Content><MsgId>6000198625587490297</MsgId></xml>'), ('134', '2014-04-09 15:55:23', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1397030122</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[我要订水]]></Content><MsgId>6000198685717032443</MsgId></xml>'), ('135', '2014-04-09 15:55:35', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt6LdbNeqa7Zo89OvPuygMsM]]></FromUserName><CreateTime>1397030135</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[我要买水]]></Content><MsgId>6000198741551607295</MsgId></xml>'), ('136', '2014-04-11 17:55:15', 'SrcXML', '<xml><ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName><FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName><CreateTime>1397210115</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[？]]></Content><MsgId>6000971749765550692</MsgId></xml>'), ('137', '2014-04-17 13:30:30', 'SrcXML', '<xml><ToUserName><![CDATA[gh_db247177973e]]></ToUserName><FromUserName><![CDATA[owoPQjs9sCXvqZEazVm-fUUCj0fI]]></FromUserName><CreateTime>1397712629</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>');
COMMIT;

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
-- Records of EVENTS
-- ----------------------------
BEGIN;
COMMIT;

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
AUTO_INCREMENT=77

;

-- ----------------------------
-- Records of message
-- ----------------------------
BEGIN;
INSERT INTO `message` VALUES ('1', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-21 14:31:58', 'text', '??????', '5993126575387520840', null, null, '0', null, null, null, null, null), ('2', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-21 14:47:36', 'text', '??????', '5993130604066844523', null, null, '0', null, null, null, null, null), ('3', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-21 14:53:19', 'text', '??????', '5993132077240627070', null, null, '0', null, null, null, null, null), ('4', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-21 14:53:44', 'text', '??????', '5993132184614809472', null, null, '0', null, null, null, null, null), ('5', 'toUser', 'fromUser', '2012-09-28 19:31:00', 'text', 'this is a test支持中文吗？', '1234567890123456', null, null, '0', null, null, null, null, null), ('6', 'toUser', 'fromUser', '2012-09-28 19:31:00', 'text', '中文也行test???涓?????', '1234567890123456', null, null, '0', null, null, null, null, null), ('7', 'toUser', 'fromUser', '2012-09-28 19:31:00', 'text', 'this is a test支持中文吗？', '1234567890123456', null, null, '0', null, null, null, null, null), ('8', 'toUserName', 'FromUser������', '2361-01-29 18:11:32', 'text', '������������', '123412341234231', null, null, '0', null, null, null, null, null), ('9', 'gh_db247177973e', 'owoPQjpMMAylAoBFKO9RAjeOcOXA', '2014-03-21 15:19:01', 'text', '������', '5993138700080161744', null, null, '0', null, null, null, null, null), ('10', '���������', '���������������', null, 'text', '������', '1234', null, null, '0', null, null, null, null, null), ('11', '1234wqasf', 'asdf', null, 'text', '���', '1234', null, null, '0', null, null, null, null, null), ('12', '1234wqasf', 'asdf', null, 'text', '???', '1234', null, null, '0', null, null, null, null, null), ('13', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '?????????', '1234', null, null, '0', null, null, null, null, null), ('14', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '??????', '1234', null, null, '0', null, null, null, null, null), ('15', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '???', '1234', null, null, '0', null, null, null, null, null), ('16', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '???', '1234', null, null, '0', null, null, null, null, null), ('17', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '??????', '1234', null, null, '0', null, null, null, null, null), ('18', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '??????', '1234', null, null, '0', null, null, null, null, null), ('19', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '??????', '1234', null, null, '0', null, null, null, null, null), ('20', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '??????', '1234', null, null, '0', null, null, null, null, null), ('21', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '锟斤拷锟斤拷锟斤拷', '1234', null, null, '0', null, null, null, null, null), ('22', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '??????', '1234', null, null, '0', null, null, null, null, null), ('23', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '??????', '1234', null, null, '0', null, null, null, null, null), ('24', '1234wqasf', 'asdf', '2011-12-17 20:02:21', 'text', '??????', '1234', null, null, '0', null, null, null, null, null), ('25', 'sdfasd', 'adsfasd', '2009-02-09 04:03:32', 'text', '??????', '2341234123', null, null, '0', null, null, null, null, null), ('26', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-21 23:20:03', 'text', '你好', '5993262661426296525', null, null, '0', null, null, null, null, null), ('27', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-21 23:20:21', 'text', '赵海荣', '5993262738735707855', null, null, '0', null, null, null, null, null), ('28', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-21 23:25:41', 'text', '你好', '5993264113125242585', null, null, '0', null, null, null, null, null), ('29', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-21 23:25:55', 'text', '赵海荣', '5993264173254784731', null, null, '0', null, null, null, null, null), ('30', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 11:45:34', 'text', '我是', '5993454779608414643', null, null, '0', null, null, null, null, null), ('31', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 11:45:51', 'text', '赵海荣', '5993454852622858677', null, null, '0', null, null, null, null, null), ('32', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 11:46:32', 'text', '赵海荣', '5993455028716517816', null, null, '0', null, null, null, null, null), ('33', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 11:55:13', 'text', '你好', '5993457266394479041', null, null, '0', null, null, null, null, null), ('34', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 11:55:21', 'text', '赵海荣', '5993457300754217411', null, null, '0', null, null, null, null, null), ('35', null, 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:28:55', 'text', '你好', null, null, null, '0', null, null, null, null, null), ('36', null, 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:31:09', 'text', '赵海荣', null, null, null, '0', null, null, null, null, null), ('37', null, 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:32:18', 'text', '松坪村', null, null, null, '0', null, null, null, null, null), ('38', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:37:21', 'text', '你女', '5993483585954069059', null, null, '0', null, null, null, null, null), ('39', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:37:29', 'text', '赵海荣', '5993483620313807429', null, null, '0', null, null, null, null, null), ('40', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:37:51', 'text', '松坪村', '5993483714803087943', null, null, '0', null, null, null, null, null), ('41', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:38:00', 'text', '民', '5993483753457793609', null, null, '0', null, null, null, null, null), ('42', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:43:07', 'event', null, null, 'unsubscribe', null, '0', null, null, null, null, null), ('43', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:43:45', 'event', null, null, 'subscribe', null, '0', null, null, null, null, null), ('44', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:44:08', 'text', '赵海荣', '5993485334005758557', null, null, '0', null, null, null, null, null), ('45', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:44:24', 'text', '长布', '5993485402725235298', null, null, '0', null, null, null, null, null), ('46', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:44:41', 'text', '。', '5993485475739679335', null, null, '0', null, null, null, null, null), ('47', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 13:44:52', 'image', null, '5993485522984319596', null, 'http://mmbiz.qpic.cn/mmbiz/8DgJya2a4e8enASnibkj5UbB8gVtv0d9jnLSF3vlIGHBa37F99icFOzhLCjh7ia2AOMYphXvNBq1eXfQ4jIgpiaXAQ/0', '0', null, null, null, null, null), ('48', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 15:16:00', 'text', '你是？', '5993509007865494339', null, null, '0', null, null, null, null, null), ('49', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 15:16:13', 'text', '赵海荣', '5993509063700069189', null, null, '0', null, null, null, null, null), ('50', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 15:16:27', 'text', '松坏村', '5993509123829611335', null, null, '0', null, null, null, null, null), ('51', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-22 15:18:07', 'text', '你好', '5993509553326340938', null, null, '0', null, null, null, null, null), ('52', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-23 15:11:12', 'event', null, null, 'unsubscribe', null, '0', null, null, null, null, null), ('53', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-23 15:37:41', 'event', null, null, 'subscribe', null, '0', null, null, null, null, null), ('54', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-23 15:38:08', 'text', '海荣', '5993885796756439594', null, null, '0', null, null, null, null, null), ('55', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-23 15:38:21', 'text', '海荣', '5993885852591014445', null, null, '0', null, null, null, null, null), ('56', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-23 15:38:47', 'text', '邑大', '5993885964260164143', null, null, '0', null, null, null, null, null), ('57', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-23 15:38:53', 'text', '？', '5993885990029967921', null, null, '0', null, null, null, null, null), ('58', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-03-23 15:49:36', 'text', '黄嘉霖', '5993888751693939256', null, null, '0', null, null, null, null, null), ('59', 'gh_db247177973e', 'owoPQjm5toXCKe9gD_-dKSTYfMoA', '2014-04-03 18:20:41', 'event', null, null, 'unsubscribe', null, '0', null, null, null, null, null), ('60', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-08 16:53:51', 'text', '我想订水', '5999842671582896276', null, null, '0', null, null, null, null, null), ('61', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-08 16:53:59', 'text', '蔡春兰', '5999842705942634648', null, null, '0', null, null, null, null, null), ('62', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-08 16:54:20', 'text', '五邑大学40栋1013', '5999842796136947874', null, null, '0', null, null, null, null, null), ('63', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-08 16:54:25', 'text', '嗯', '5999842817611784356', null, null, '0', null, null, null, null, null), ('64', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-08 17:27:00', 'text', '我想订水', '5999851214272848185', null, null, '0', null, null, null, null, null), ('65', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-09 11:13:23', 'text', 'en', '6000126019165350243', null, null, '0', null, null, null, null, null), ('66', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-09 11:13:25', 'text', '好呀', '6000126027755284837', null, null, '0', null, null, null, null, null), ('67', 'gh_db247177973e', 'owoPQjm2AHeU_SgAeUHqIZVUha2E', '2014-04-09 14:10:08', 'event', null, null, 'subscribe', null, '0', null, null, null, null, null), ('68', 'gh_db247177973e', 'owoPQjm2AHeU_SgAeUHqIZVUha2E', '2014-04-09 14:16:04', 'text', '/::P', '6000173096301816427', null, null, '0', null, null, null, null, null), ('69', 'gh_db247177973e', 'owoPQjm2AHeU_SgAeUHqIZVUha2E', '2014-04-09 14:16:55', 'text', '海璟印象城', '6000173315345148527', null, null, '0', null, null, null, null, null), ('70', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-09 15:53:45', 'text', '好', '6000198269105204720', null, null, '0', null, null, null, null, null), ('71', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-09 15:54:38', 'text', '我要定水', '6000198496738471414', null, null, '0', null, null, null, null, null), ('72', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-09 15:55:08', 'text', '订水', '6000198625587490297', null, null, '0', null, null, null, null, null), ('73', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-09 15:55:22', 'text', '我要订水', '6000198685717032443', null, null, '0', null, null, null, null, null), ('74', 'gh_5a402ba88fa0', 'oBx4Dt6LdbNeqa7Zo89OvPuygMsM', '2014-04-09 15:55:35', 'text', '我要买水', '6000198741551607295', null, null, '0', null, null, null, null, null), ('75', 'gh_5a402ba88fa0', 'oBx4Dt37J4GSXlt32V4zGf-EDQQM', '2014-04-11 17:55:15', 'text', '？', '6000971749765550692', null, null, '0', null, null, null, null, null), ('76', 'gh_db247177973e', 'owoPQjs9sCXvqZEazVm-fUUCj0fI', '2014-04-17 13:30:29', 'event', null, null, 'subscribe', null, '0', null, null, null, null, null);
COMMIT;

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
-- Records of order
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of orderlist
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of test
-- ----------------------------
BEGIN;
INSERT INTO `test` VALUES ('1', 'li', 'liContent66'), ('2', 'li', 'liContent22'), ('3', 'li', 'liContent44');
COMMIT;

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
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'admin', '202cb962ac59075b964b07152d234b70', '鐢ㄦ埛001'), ('2', 'rong', '202cb962ac59075b964b07152d234b70', '鐢ㄦ埛002');
COMMIT;

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
ALTER TABLE `debug` AUTO_INCREMENT=138;

-- ----------------------------
-- Auto increment value for `message`
-- ----------------------------
ALTER TABLE `message` AUTO_INCREMENT=77;

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
