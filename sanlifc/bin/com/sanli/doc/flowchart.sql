/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2013-10-24 22:37:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `flowchart`
-- ----------------------------
DROP TABLE IF EXISTS `flowchart`;
CREATE TABLE `flowchart` (
  `uuid` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `city` varchar(20) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `create_project_title` varchar(30) DEFAULT NULL,
  `create_project_number` varchar(30) DEFAULT NULL,
  `project_name` varchar(50) DEFAULT NULL,
  `mis_number` varchar(20) DEFAULT NULL,
  `research_contract_number` varchar(20) DEFAULT NULL,
  `simple_name` varchar(20) DEFAULT NULL,
  `project_main_type` varchar(10) DEFAULT NULL,
  `project_type` varchar(10) DEFAULT NULL,
  `create_project_info` varchar(20) DEFAULT NULL,
  `a_project_manager` varchar(10) DEFAULT NULL,
  `project_leader` varchar(10) DEFAULT NULL,
  `contract_pay_info` varchar(100) DEFAULT NULL,
  `project_total_invest` float DEFAULT NULL,
  `ht_wireless` float DEFAULT NULL,
  `ht_transmission` float DEFAULT NULL,
  `ht_power` float DEFAULT NULL,
  `ht_civil` float DEFAULT NULL,
  `ht_total` float DEFAULT NULL,
  `a_time` bigint(20) DEFAULT NULL,
  `a_note` varchar(50) DEFAULT NULL,
  `b_time` bigint(20) DEFAULT NULL,
  `b_note` varchar(50) DEFAULT NULL,
  `c_time` bigint(20) DEFAULT NULL,
  `c_note` varchar(50) DEFAULT NULL,
  `d_time` bigint(20) DEFAULT NULL,
  `d_note` varchar(50) DEFAULT NULL,
  `e_time` bigint(20) DEFAULT NULL,
  `e_note` varchar(50) DEFAULT NULL,
  `f_time` bigint(20) DEFAULT NULL,
  `f_note` varchar(50) DEFAULT NULL,
  `check_info` varchar(50) DEFAULT NULL,
  `final_cost_time` bigint(20) DEFAULT NULL,
  `final_wireless` float DEFAULT NULL,
  `final_transmission` float DEFAULT NULL,
  `final_power` float DEFAULT NULL,
  `final_civil` float DEFAULT NULL,
  `final_total` float DEFAULT NULL,
  `fapiao_a_time` bigint(20) DEFAULT NULL,
  `fapiao_a_scale` float DEFAULT NULL,
  `fapiao_a_money` float DEFAULT NULL,
  `fapiao_b_time` bigint(20) DEFAULT NULL,
  `fapiao_b_scale` float DEFAULT NULL,
  `fapiao_b_money` float DEFAULT NULL,
  `fapiao_c_time` bigint(20) DEFAULT NULL,
  `fapiao_c_scale` float DEFAULT NULL,
  `fapiao_c_money` float DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of flowchart
-- ----------------------------
INSERT INTO `flowchart` VALUES ('6', '0', '成都222', '0', null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', '0', '0', '0', '0', '0', null, '0', null, '0', null, '0', null, '0', null, '0', null, null, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `flowchart` VALUES ('7', '1', '成都222', '0', null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', '0', '0', '0', '0', '0', null, '0', null, '0', null, '0', null, '0', null, '0', null, null, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `flowchart` VALUES ('17', '4', '蓬安33', '212', '564564', null, null, null, null, null, null, null, null, null, null, null, '0', '0', '0', '0', '0', '0', '1382536559625', null, '0', null, '0', null, '0', null, '0', null, '0', null, null, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null);
