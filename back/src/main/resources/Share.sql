/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : share

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-05-08 14:29:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_record
-- ----------------------------
DROP TABLE IF EXISTS `account_record`;
CREATE TABLE `account_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键Id',
  `rule_id` int(11) DEFAULT NULL COMMENT '关联规则Id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `experience` int(255) DEFAULT NULL COMMENT '修改经验值数量',
  `gold` int(255) DEFAULT NULL COMMENT '修改金币值数量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_record
-- ----------------------------

-- ----------------------------
-- Table structure for score_grade
-- ----------------------------
DROP TABLE IF EXISTS `score_grade`;
CREATE TABLE `score_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` int(11) DEFAULT NULL COMMENT '等级1-16',
  `experience` int(255) DEFAULT NULL COMMENT '所需经验值',
  `operator` varchar(255) NOT NULL COMMENT '操作人员',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score_grade
-- ----------------------------
INSERT INTO `score_grade` VALUES ('1', '1', '20', 'send', '2017-05-03 11:52:01', '2017-05-03 11:52:01');

-- ----------------------------
-- Table structure for score_rule
-- ----------------------------
DROP TABLE IF EXISTS `score_rule`;
CREATE TABLE `score_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) NOT NULL COMMENT '规则码',
  `experience` int(255) DEFAULT NULL COMMENT '经验值',
  `gold` int(255) DEFAULT NULL COMMENT '金币',
  `experience_formula` varchar(255) DEFAULT NULL COMMENT '经验计算公式',
  `gold_formula` varchar(255) DEFAULT NULL COMMENT '金币计算公式',
  `experience_max` varchar(255) DEFAULT NULL COMMENT '经验上限',
  `gold_max` varchar(255) DEFAULT NULL COMMENT '金币上线',
  `is_formula` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否计算公式,0不是，1是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `delete_flag` int(255) NOT NULL DEFAULT '0' COMMENT '删除表示0：未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score_rule
-- ----------------------------

-- ----------------------------
-- Table structure for share_classification
-- ----------------------------
DROP TABLE IF EXISTS `share_classification`;
CREATE TABLE `share_classification` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增逐渐',
  `name` varchar(255) NOT NULL COMMENT '分享分类名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `delete_flag` int(255) NOT NULL DEFAULT '0' COMMENT '删除表示；0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_classification
-- ----------------------------
INSERT INTO `share_classification` VALUES ('1', '图片', '2017-05-03 11:58:23', '0');
INSERT INTO `share_classification` VALUES ('2', '电影', '2017-05-03 11:58:31', '0');

-- ----------------------------
-- Table structure for share_comment
-- ----------------------------
DROP TABLE IF EXISTS `share_comment`;
CREATE TABLE `share_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键，评论唯一id',
  `owner_user_id` int(11) NOT NULL COMMENT '评论用户id',
  `target_user_id` int(11) NOT NULL COMMENT '评论目标用户Id',
  `content` varchar(1024) DEFAULT NULL COMMENT '评论内容',
  `like` int(255) DEFAULT '0' COMMENT '点赞',
  `parent_id` int(11) NOT NULL COMMENT '评论目标Id',
  `parent_type` varchar(255) DEFAULT NULL COMMENT '评论目标类型，0分享，1评论...',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标识，0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_comment
-- ----------------------------

-- ----------------------------
-- Table structure for share_content
-- ----------------------------
DROP TABLE IF EXISTS `share_content`;
CREATE TABLE `share_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` int(11) NOT NULL COMMENT '创建用户',
  `title` varchar(255) DEFAULT NULL COMMENT '分享内容标题',
  `content` mediumtext COMMENT '分享内容，富文本编辑器内容',
  `classification_id` int(11) DEFAULT NULL COMMENT '分类Id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `delete_flag` int(255) NOT NULL DEFAULT '0' COMMENT '删除表示；0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_content
-- ----------------------------

-- ----------------------------
-- Table structure for share_report
-- ----------------------------
DROP TABLE IF EXISTS `share_report`;
CREATE TABLE `share_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '举报信息唯一Id',
  `owner_user_id` int(255) DEFAULT NULL COMMENT '举报用户Id',
  `target_user_id` int(11) DEFAULT NULL COMMENT '被举报用户Id',
  `share_id` int(11) NOT NULL COMMENT '被举报分享id',
  `report` varchar(255) DEFAULT NULL COMMENT '举报原因',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '举报时间',
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标识，0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_report
-- ----------------------------

-- ----------------------------
-- Table structure for share_statistics
-- ----------------------------
DROP TABLE IF EXISTS `share_statistics`;
CREATE TABLE `share_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `content_id` int(11) NOT NULL COMMENT '内容主键id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `like` int(255) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `comment` int(255) NOT NULL DEFAULT '0' COMMENT '评论数',
  `word` int(11) NOT NULL COMMENT '字数',
  `view` int(255) NOT NULL DEFAULT '0' COMMENT '阅读数',
  `collection` int(255) NOT NULL DEFAULT '0' COMMENT '收藏数',
  `share` int(255) NOT NULL DEFAULT '0' COMMENT '分享数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL COMMENT '自增主键',
  `user_id` int(11) NOT NULL COMMENT '用户Id',
  `experience` int(255) DEFAULT NULL COMMENT '经验值',
  `gold` int(255) DEFAULT NULL COMMENT '金币',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_account
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` int(11) NOT NULL COMMENT '用户Id',
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `sex` int(255) DEFAULT NULL COMMENT '性别0，男；1女，2未设置',
  `phone_number` int(11) DEFAULT NULL,
  `introduction` varchar(1000) DEFAULT NULL COMMENT '个人简介',
  `qr_code_wechat` varchar(255) DEFAULT NULL COMMENT '微信二维码',
  `qr_code_alipay` varchar(255) DEFAULT NULL COMMENT '支付宝二维码',
  `grade` int(255) NOT NULL DEFAULT '1' COMMENT '用户等级',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '1', 'send', null, '0', '1881157107', '生命不息，折腾不止', null, null, '16', '2017-05-03 12:03:52', '2017-05-03 12:03:52');

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键Id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `email` varchar(255) NOT NULL COMMENT '邮箱，用于找回密码',
  `password` varchar(255) NOT NULL COMMENT '登录表',
  `last_login_time` datetime NOT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(255) DEFAULT NULL COMMENT '上次登录IP',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `delete_flag` int(11) DEFAULT '0' COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login
-- ----------------------------

-- ----------------------------
-- Table structure for user_message
-- ----------------------------
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `target_user_id` int(11) DEFAULT NULL COMMENT '目标用户Id',
  `content` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `type` int(255) DEFAULT NULL COMMENT '消息内容，0评论，1喜欢，2关注，3其他消息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_message
-- ----------------------------

-- ----------------------------
-- Table structure for user_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_relation`;
CREATE TABLE `user_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户关系自增主键',
  `owner_user_id` int(11) NOT NULL COMMENT '用户Id',
  `target_user_id` int(11) NOT NULL COMMENT '目标用户Id',
  `rel_type` int(11) NOT NULL DEFAULT '0' COMMENT '关系，0关注，1拉黑.....',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_relation
-- ----------------------------
