/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50735
Source Host           : localhost:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 50735
File Encoding         : 65001

Date: 2021-10-31 22:04:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `id` bigint(20) NOT NULL,
  `contacts` varchar(50) NOT NULL COMMENT '联系人',
  `address` varchar(255) NOT NULL COMMENT '地址',
  `post` varchar(6) DEFAULT NULL COMMENT '邮编',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `resver1` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `resver2` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_address
-- ----------------------------

-- ----------------------------
-- Table structure for `t_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '公司名字',
  `contacts` varchar(50) NOT NULL COMMENT '联系人',
  `address` varchar(255) NOT NULL,
  `post` varchar(6) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `resver1` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `resver2` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  KEY `id` (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_company
-- ----------------------------

-- ----------------------------
-- Table structure for `t_goods`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` bigint(20) NOT NULL,
  `good_name` varchar(50) NOT NULL COMMENT '商品名称',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `specs` varchar(10) NOT NULL COMMENT '规则',
  `details` varchar(520) NOT NULL COMMENT '详情',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `company_id` bigint(20) NOT NULL COMMENT '公司id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `resver1` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `resver2` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  KEY `id` (`id`,`good_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL,
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `nums` int(11) NOT NULL COMMENT '数量',
  `total_price` decimal(10,2) NOT NULL COMMENT '总价',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `address_id` bigint(20) NOT NULL COMMENT '地址ID',
  `track_num` varchar(32) DEFAULT NULL COMMENT '快递单号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `resver1` varchar(255) DEFAULT NULL COMMENT '扩展',
  `resver2` varchar(255) DEFAULT NULL COMMENT '扩展',
  KEY `id` (`id`,`order_no`,`goods_id`,`track_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `cert_no` varchar(20) NOT NULL COMMENT '证件号码',
  `name` varchar(50) NOT NULL DEFAULT '姓名',
  `sex` tinyint(1) NOT NULL COMMENT '性别0-女;1-男',
  `passwd` varchar(32) NOT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `resver1` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `resver2` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  KEY `id` (`id`,`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
