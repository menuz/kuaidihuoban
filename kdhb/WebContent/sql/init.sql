/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : tjbus

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2013-12-13 13:03:39
*/

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL comment '用户名即学生学号',
  `password` varchar(30) DEFAULT NULL comment '密码',
  
  `name` varchar(20) DEFAULT NULL comment '姓名',
  `phone` varchar(20) DEFAULT NULL comment '手机',
  
  `admit_time` varchar(20) DEFAULT NULL comment '入学时间',
  `college` varchar(20) DEFAULT NULL comment '学院',
  `major_name` varchar(20) DEFAULT NULL comment '专业',
  `graduate` int(11) NOT NULL DEFAULT '0' COMMENT '本科生为0, 研究生为1',
  
  `floor` varchar(20) DEFAULT NULL comment '住的楼层' ,
  `lab` varchar(20) DEFAULT NULL comment '实验室',
  
  `tag` int(11) DEFAULT '0' COMMENT '有没有激活，1激活，0未激活',
  
  `release_task_no` int(11) DEFAULT '0' COMMENT '发布的任务数目',
  `accept_task_no` int(11) DEFAULT '0' COMMENT '接受的任务数目',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4099 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `fa_kuaidi_order`  派发从快递公司来的快件
-- ----------------------------
DROP TABLE IF EXISTS `fa_kuaidi_order`;
CREATE TABLE `fa_kuaidi_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `release_user_id` int(11) DEFAULT NULL comment '发布这项任务的用户编号',
  `order_id` varchar(30) DEFAULT NULL comment '订单号',
  `ct` timestamp DEFAULT CURRENT_TIMESTAMP comment '订单生成时间',
  
  `pack_sign_timeinterval` varchar(20) DEFAULT '' comment '签收时间段', 
  `pack_company` varchar(20) DEFAULT '' comment '快递公司',
  `pack_size` varchar(20) DEFAULT '' comment '快递大小',
  
  `pack_dest` varchar(20) DEFAULT '' comment '快递送到的地方，实验室还是寝室',
  `pack_money` int(11) DEFAULT '0' comment '包裹的酬金',
  `pack_to_dest_time` timestamp not null comment '期望包裹到达实验室或者寝室的时间',
  `order_valid_time` timestamp not null comment '订单有效截止时间',
  
  `accept_user_id` varchar(10) DEFAULT '' comment '接受这项任务的编号',
  `status` int(11) DEFAULT '0' comment '0，刚刚发布，1，接受，2，成功，3，失败',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4099 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `shou_kuaidi_order`  收集快递，从学生那边收集的快递
-- ----------------------------
DROP TABLE IF EXISTS `shou_kuaidi_order`;
CREATE TABLE `shou_kuaidi_order` (

  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(30) NOT NULL comment '收集订单号',
  `receiver_name` varchar(20) DEFAULT NULL comment '收件人姓名',
  `receiver_address` varchar(50) DEFAULT NULL comment '收件人地址',
  `receiver_zipcode` varchar(6) not null comment '收件人邮编',
  
  `book_time` timestamp not null comment '预约时间',  
  `book_address` varchar(30) not null comment '预约收集快递地点',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4099 DEFAULT CHARSET=utf8;


INSERT INTO user(id, username, password, tag, name, college, admit_time, major_name, phone, graduate, floor, lab, release_task_no, accept_task_no) VALUES ('1', '1152531', '', '0', '高建辉', '100', '2013', '电子科学与技术', '13012345678', '0', '16楼', '电信大楼415', '0', '0');
INSERT INTO user(id, username, password, tag, name, college, admit_time, major_name, phone, graduate, floor, lab, release_task_no, accept_task_no) VALUES ('2', '1333793', '', '0', '徐小奇', '100', '2013', '电子科学与技术', '13012345678', '1', '16楼', '电信大楼415', '0', '0');
INSERT INTO user(id, username, password, tag, name, college, admit_time, major_name, phone, graduate, floor, lab, release_task_no, accept_task_no) VALUES ('3', '1333902', '', '0', '黄炳川龙', '100', '2013', '电子科学与技术', '13012345678', '1', '16楼', '电信大楼415', '0', '0');
INSERT INTO user(id, username, password, tag, name, college, admit_time, major_name, phone, graduate, floor, lab, release_task_no, accept_task_no) VALUES ('4', '1233690', '', '0', '徐严康', '100', '2012', '电子科学与技术', '13012345678', '1', '16楼', '电信大楼415', '0', '0');
