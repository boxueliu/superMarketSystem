/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost
 Source Database       : smbms

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 04/22/2018 20:51:57 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `con_test`
-- ----------------------------
DROP TABLE IF EXISTS `con_test`;
CREATE TABLE `con_test` (
  `a` char(1) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `logs`
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `logId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `project_name` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `level` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `file_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `thread_name` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `line` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `all_category` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `message` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `smbms_bill`
-- ----------------------------
DROP TABLE IF EXISTS `smbms_bill`;
CREATE TABLE `smbms_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `billCode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账单编码',
  `providerId` bigint(20) DEFAULT NULL,
  `productName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `productDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品描述',
  `productUnit` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品单位',
  `productCount` decimal(20,2) DEFAULT NULL COMMENT '商品数量',
  `totalPrice` decimal(20,2) DEFAULT NULL COMMENT '商品总额',
  `isPayment` int(10) DEFAULT NULL COMMENT '是否支付（0：未支付 1：已支付）',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者（userId）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '更新者（userId）',
  `modifyDate` datetime DEFAULT NULL COMMENT '更新时间',
  `RFIDID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `supplierId` (`providerId`),
  CONSTRAINT `smbms_bill_ibfk_1` FOREIGN KEY (`providerId`) REFERENCES `smbms_provider` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `smbms_bill`
-- ----------------------------
BEGIN;
INSERT INTO `smbms_bill` VALUES ('1', 'bg20171014', '1', '花生米', 'bghsm20161214', 'kg', '40.00', '500.00', '1', '1', '2016-12-15 00:00:00', null, null, '1234'), ('5', 'sg20171019', '2', '鸡肉肠', null, 'kg', '800.00', '4800.00', '0', '1', '2017-10-20 23:04:00', null, null, '12324'), ('6', 'sg20171021', '2', '双鸽蛋饼', null, '个', '1000.00', '2000.00', '0', '1', '2017-10-21 10:06:00', null, null, '22515'), ('7', '20180414', '5', '好吃点', null, '浙江工商大学', '10.00', '1000.00', '2', '1', '2018-04-15 11:19:48', null, null, null), ('9', '20180414567', '6', '蔬菜果蔬', null, '浙江工商大学', '10.00', '1000.00', '1', '1', '2018-04-15 11:32:34', '1', '2018-04-15 11:33:04', null), ('10', '2018041456767', '6', '水果', null, '浙江工商大学', '101.00', '1000.00', '1', '1', '2018-04-15 13:47:26', '1', '2018-04-16 14:51:23', null), ('11', '12312', '5', '35325', null, '35435', '3532.00', '3532.00', '2', '1', '2018-04-18 12:30:47', null, null, null), ('13', '20180414', '5', '火腿', null, '浙江工商大学', '100.00', '1000.00', '1', '1', '2018-04-18 12:33:02', '1', '2018-04-19 08:33:29', null);
COMMIT;

-- ----------------------------
--  Table structure for `smbms_provider`
-- ----------------------------
DROP TABLE IF EXISTS `smbms_provider`;
CREATE TABLE `smbms_provider` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `proCode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商编码',
  `proName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商名称',
  `proDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商详细描述',
  `proContact` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商联系人',
  `proPhone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `proAddress` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `proFax` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '传真',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者（userId）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyDate` datetime DEFAULT NULL COMMENT '更新时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '更新者（userId）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `smbms_provider`
-- ----------------------------
BEGIN;
INSERT INTO `smbms_provider` VALUES ('1', 'beiguo', '北国商城', 'sn001', '张三', '13585211452', '河北省石家庄市', '0311-84848521', '1', '2010-10-05 00:00:00', null, null), ('2', 'shuangge', '双鸽冷藏食品', 'sn002', '李四', '13685412145', '河北省石家庄市', '0311-84587412', '1', '2011-10-06 00:00:00', null, null), ('5', '1029324', '浙商大信电学院', '你好', '刘波学', '13826839709', '浙江杭州浙江工商大学', '12334', '123', '2018-04-14 21:07:11', null, null), ('6', '20180415', '毕业设计需要', '毕设', '唐李达', '13826839709', '浙江杭州浙江工商大学', '1233456', '123', '2018-04-15 11:28:39', null, null), ('7', '12321412', '浙商大信电学院', '测试专用', '刘波学', '13826839709', '浙江杭州浙江工商大学', '123341', '123', '2018-04-18 12:35:04', '2018-04-18 13:04:59', '1');
COMMIT;

-- ----------------------------
--  Table structure for `smbms_role`
-- ----------------------------
DROP TABLE IF EXISTS `smbms_role`;
CREATE TABLE `smbms_role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `smbms_role`
-- ----------------------------
BEGIN;
INSERT INTO `smbms_role` VALUES ('1', '总经理'), ('2', '部门经理'), ('3', '员工');
COMMIT;

-- ----------------------------
--  Table structure for `smbms_user`
-- ----------------------------
DROP TABLE IF EXISTS `smbms_user`;
CREATE TABLE `smbms_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userCode` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户编码',
  `userName` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `userPassword` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `gender` int(10) DEFAULT NULL COMMENT '性别（1:女、 2:男）',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机',
  `address` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址',
  `userRole` int(10) DEFAULT NULL COMMENT '用户类型（1：系统管理员、2：经理、3：普通员工）',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者（userId）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '更新者（userId）',
  `modifyDate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `smbms_user`
-- ----------------------------
BEGIN;
INSERT INTO `smbms_user` VALUES ('1', 'admin', '刘波学', '1234567', '1', '1991-10-01', '13545145145', '浙江杭州', '2', '1', '2010-10-10 00:00:00', '1', '2018-04-18 15:55:54'), ('2', 'zhangsan', '张三', 'Adf0B2CWDnvZRDUT8iq5rw==', '1', '1992-05-14', '13851452145', '河北省保定市', '2', '1', '2010-08-08 00:00:00', null, null), ('4', 'wangwu', '王五', 'ZwsUcorZkCrsujLiL6T2vQ==', '1', '1993-10-14', '13652145412', '河北省石家庄市', '2', '1', '2011-09-09 00:00:00', null, null), ('8', 'zm', '赵敏', '111', '2', '1996-10-14', '13852411452', '河北省石家庄无极限', '3', '1', '2012-08-08 00:00:00', null, null), ('9', '2312', '12321', '1234567', '1', '2018-04-11', '13826839709', '浙江杭州', '3', '1', '2018-04-18 14:16:04', null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
