/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : webbook

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-13 16:33:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_ISBN` varchar(20) NOT NULL COMMENT '图书ISBN号',
  `book_catagoryId` int(6) NOT NULL,
  `book_name` varchar(100) NOT NULL,
  `book_publish` varchar(20) DEFAULT NULL,
  `book_author` varchar(20) DEFAULT NULL,
  `book_publishDate` date DEFAULT NULL,
  `book_price` float(6,2) DEFAULT NULL,
  `book_storageDate` date DEFAULT NULL,
  `book_stockNumber` int(11) DEFAULT NULL,
  `book_inNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`book_ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('WYU001', '2', '数据库系统概论', '高等教育出版社', '王珊', '1983-04-01', '42.00', '2015-12-03', '4', '4');
INSERT INTO `book` VALUES ('WYU002', '2', 'Oracle数据库（第二版）', '电子工业出版社', '孙风栋', '2014-01-01', '45.00', '2015-11-12', '9', '9');
INSERT INTO `book` VALUES ('WYU003', '1', '马克思主义哲学理论', '中国文学出版社', '马克思', '2005-06-02', '36.00', '2016-12-08', '5', '5');
INSERT INTO `book` VALUES ('WYU004', '3', '大学英语', '外语出版社', '黄珊珊', '2016-12-09', '50.00', '2017-12-15', '4', '4');
INSERT INTO `book` VALUES ('WYU005', '1', '简·爱', '长沙文艺出版社', '刘莎莎', '2006-12-13', '60.00', '2017-01-03', '4', '2');
INSERT INTO `book` VALUES ('WYU006', '3', '德语', '外语出版社', '王文书', '2013-12-05', '36.00', '2016-12-16', '6', '6');
INSERT INTO `book` VALUES ('WYU007', '3', '英语翻译', '外语出版社', '张雪兰', '2014-08-06', '35.00', '2018-12-02', '3', '3');
INSERT INTO `book` VALUES ('WYU008', '2', '数据结构', '高等教育出版社', '刘洪斌', '2011-12-09', '60.00', '2015-12-16', '6', '6');
INSERT INTO `book` VALUES ('WYU009', '2', '算法分析与设计', '电子工业出版社', '刘洋', '2015-12-11', '40.00', '2016-12-07', '20', '20');
INSERT INTO `book` VALUES ('WYU010', '1', '近代史', '中国文学出版社', '张三林', '2016-12-04', '23.00', '2016-12-08', '8', '8');
INSERT INTO `book` VALUES ('WYU011', '1', '三国演义', '中国文学出版社', '罗贯中', '2012-12-04', '65.00', '2013-12-12', '3', '2');
INSERT INTO `book` VALUES ('WYU012', '5', '军事理论', '中国国防出版社', '张国辉', '2015-12-09', '25.00', '2017-12-04', '6', '6');
INSERT INTO `book` VALUES ('WYU013', '4', '经济学理论', '高等教育出版社', '王传君', '2012-12-04', '32.00', '2017-12-14', '5', '5');

-- ----------------------------
-- Table structure for `bookcatagory`
-- ----------------------------
DROP TABLE IF EXISTS `bookcatagory`;
CREATE TABLE `bookcatagory` (
  `bookcatagory_id` int(6) unsigned NOT NULL AUTO_INCREMENT COMMENT '图书分类编号',
  `bookcatagory_name` varchar(20) NOT NULL,
  `bookcatagory_brid` int(6) DEFAULT NULL,
  `bookcatagory_demo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bookcatagory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookcatagory
-- ----------------------------
INSERT INTO `bookcatagory` VALUES ('1', '文学', '1', '此类书籍大致为文学史书籍');
INSERT INTO `bookcatagory` VALUES ('2', '计算机科学', '2', '此类书籍与计算机学科有关的书籍');
INSERT INTO `bookcatagory` VALUES ('3', '外语', '3', '此类书籍与外国语有关');
INSERT INTO `bookcatagory` VALUES ('4', '经济学', '4', '此类书籍与经济学有关');
INSERT INTO `bookcatagory` VALUES ('5', '军事国防', '5', '此类书籍与军事国防有关');

-- ----------------------------
-- Table structure for `bookroom`
-- ----------------------------
DROP TABLE IF EXISTS `bookroom`;
CREATE TABLE `bookroom` (
  `bookroom_id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `bookroom_name` varchar(20) NOT NULL,
  `bookroom_address` varchar(20) DEFAULT NULL,
  `bookroom_phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`bookroom_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookroom
-- ----------------------------
INSERT INTO `bookroom` VALUES ('1', '文学馆', '五邑大学图书馆3楼文学馆', '3296252');
INSERT INTO `bookroom` VALUES ('2', '科技馆', '五邑大学图书馆4楼科技馆', '3296257');
INSERT INTO `bookroom` VALUES ('3', '外文馆', '五邑大学图书馆5楼外语馆', '3296255');
INSERT INTO `bookroom` VALUES ('4', '经济学馆', '五邑大学图书馆6楼经济学馆', '3296259');
INSERT INTO `bookroom` VALUES ('5', '军事国防馆', '五邑大学图书馆7楼军事国防管', '3296345');

-- ----------------------------
-- Table structure for `borrow`
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `borrow_id` int(11) NOT NULL AUTO_INCREMENT,
  `borrow_bcid` varchar(20) DEFAULT NULL,
  `borrow_ISBN` varchar(20) DEFAULT NULL,
  `borrow_borrowDate` date NOT NULL,
  `borrow_expireDate` date DEFAULT NULL,
  `borrow_dueDate` date DEFAULT NULL,
  PRIMARY KEY (`borrow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('1', 'WYU3116004112', 'WYU005', '2018-09-05', '2018-10-15', null);
INSERT INTO `borrow` VALUES ('2', 'WYU3216000611', 'WYU005', '2018-10-08', '2018-11-17', '2018-12-13');
INSERT INTO `borrow` VALUES ('3', 'WYU3116002659', 'WYU005', '2018-11-07', '2019-01-26', null);
INSERT INTO `borrow` VALUES ('4', 'WYU3116002615', 'WYU011', '2018-08-15', '2018-10-14', null);

-- ----------------------------
-- Table structure for `borrowcard`
-- ----------------------------
DROP TABLE IF EXISTS `borrowcard`;
CREATE TABLE `borrowcard` (
  `borrowcard_id` varchar(20) NOT NULL,
  `borrowcard_rid` int(20) DEFAULT NULL,
  `borrowcard_quantity` int(6) DEFAULT NULL,
  `borrowcard_blid` int(2) DEFAULT NULL,
  PRIMARY KEY (`borrowcard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrowcard
-- ----------------------------
INSERT INTO `borrowcard` VALUES ('WYU3116002615', '4', '1', '2');
INSERT INTO `borrowcard` VALUES ('WYU3116002629', '5', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116002659', '6', '1', '3');
INSERT INTO `borrowcard` VALUES ('WYU3116002665', '7', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116002716', '8', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116003145', '9', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116003162', '10', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116003304', '11', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116003319', '12', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116003479', '13', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116003538', '14', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116003571', '15', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116003708', '3', '0', '3');
INSERT INTO `borrowcard` VALUES ('WYU3116003712', '16', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116003859', '17', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116003948', '18', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116004112', '19', '1', '1');
INSERT INTO `borrowcard` VALUES ('WYU3116004259', '2', '0', '3');
INSERT INTO `borrowcard` VALUES ('WYU3216000611', '20', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3216002708', '21', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3216003226', '22', '0', '1');
INSERT INTO `borrowcard` VALUES ('WYU3216004461', '23', '0', '1');

-- ----------------------------
-- Table structure for `borrowlevel`
-- ----------------------------
DROP TABLE IF EXISTS `borrowlevel`;
CREATE TABLE `borrowlevel` (
  `borrowlevel_id` int(2) unsigned NOT NULL AUTO_INCREMENT,
  `borrowlevel_name` varchar(10) NOT NULL,
  `borrowlevel_quantity` int(6) NOT NULL,
  `borrowlevel_days` int(11) NOT NULL,
  `borrowlevel_fine` float(6,2) NOT NULL,
  `borrowlevel_demo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`borrowlevel_id`,`borrowlevel_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrowlevel
-- ----------------------------
INSERT INTO `borrowlevel` VALUES ('1', '低级', '5', '40', '0.50', '低级借阅证，可借5本书，可持续40天，超时每天罚款0.5元');
INSERT INTO `borrowlevel` VALUES ('2', '中级', '7', '60', '0.30', '中级借阅证，可借7本书，可持续60天，超时每天罚款0.3元');
INSERT INTO `borrowlevel` VALUES ('3', '高级', '10', '80', '0.10', '高级借阅证，可借10本书，可持续80天，超时每天罚款0.1元');

-- ----------------------------
-- Table structure for `ticket`
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `ticket_id` int(6) NOT NULL AUTO_INCREMENT,
  `ticket_bcid` varchar(20) DEFAULT NULL,
  `ticket_ISBN` varchar(20) DEFAULT NULL,
  `ticket_fineMoney` float(6,2) DEFAULT NULL,
  `ticket_fineDate` date DEFAULT NULL,
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES ('1', 'WYU3216000611', 'WYU005', '13.00', '2018-12-13');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_account` varchar(20) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_name` varchar(20) DEFAULT '',
  `user_sex` varchar(2) DEFAULT '',
  `user_phone` varchar(20) DEFAULT '',
  `user_photo` varchar(20) DEFAULT NULL,
  `user_level` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'ADMIN', '男', '13612250853', null, '超级管理员');
INSERT INTO `user` VALUES ('2', '3116004259', 'e10adc3949ba59abbe56e057f20f883e', '钟文武', '男', '13612250853', null, '管理员');
INSERT INTO `user` VALUES ('3', '3116003708', 'e10adc3949ba59abbe56e057f20f883e', '张文宇', '男', '15766946398', null, '管理员');
INSERT INTO `user` VALUES ('4', '3116002615', 'e10adc3949ba59abbe56e057f20f883e', '宋悦濠', '男', '13612250585', null, '读者');
INSERT INTO `user` VALUES ('5', '3116002629', 'e10adc3949ba59abbe56e057f20f883e', '黄浩杰', '男', '13612212345', null, '读者');
INSERT INTO `user` VALUES ('6', '3116002659', 'e10adc3949ba59abbe56e057f20f883e', '陈桂旭', '男', '13612212563', null, '读者');
INSERT INTO `user` VALUES ('7', '3116002665', 'e10adc3949ba59abbe56e057f20f883e', '植志明', '男', '13612278965', null, '读者');
INSERT INTO `user` VALUES ('8', '3116002716', 'e10adc3949ba59abbe56e057f20f883e', '余海霖', '男', '13612212596', null, '读者');
INSERT INTO `user` VALUES ('9', '3116003145', 'e10adc3949ba59abbe56e057f20f883e', '黄国棋', '男', '13612243651', null, '读者');
INSERT INTO `user` VALUES ('10', '3116003162', 'e10adc3949ba59abbe56e057f20f883e', '郑杰锐', '男', '13612232548', null, '读者');
INSERT INTO `user` VALUES ('11', '3116003304', 'e10adc3949ba59abbe56e057f20f883e', '林家立', '男', '13612258796', null, '读者');
INSERT INTO `user` VALUES ('12', '3116003319', 'e10adc3949ba59abbe56e057f20f883e', '区梓麟', '男', '13612232589', null, '读者');
INSERT INTO `user` VALUES ('13', '3116003479', 'e10adc3949ba59abbe56e057f20f883e', '黄俊杰', '男', '13612245638', null, '读者');
INSERT INTO `user` VALUES ('14', '3116003538', 'e10adc3949ba59abbe56e057f20f883e', '许锦鹏', '男', '13612263587', null, '读者');
INSERT INTO `user` VALUES ('15', '3116003571', 'e10adc3949ba59abbe56e057f20f883e', '丘洪斌', '男', '13612296487', null, '读者');
INSERT INTO `user` VALUES ('16', '3116003712', 'e10adc3949ba59abbe56e057f20f883e', '甘洪焕', '男', '13612235896', null, '读者');
INSERT INTO `user` VALUES ('17', '3116003859', 'e10adc3949ba59abbe56e057f20f883e', '冯文华', '男', '13612236971', null, '读者');
INSERT INTO `user` VALUES ('18', '3116003948', 'e10adc3949ba59abbe56e057f20f883e', '吕家发', '男', '13612296482', null, '读者');
INSERT INTO `user` VALUES ('19', '3116004112', 'e10adc3949ba59abbe56e057f20f883e', '陆树钊', '男', '13612265387', null, '读者');
INSERT INTO `user` VALUES ('20', '3216000611', 'e10adc3949ba59abbe56e057f20f883e', '叶尚华', '女', '1361264598', null, '读者');
INSERT INTO `user` VALUES ('21', '3216002708', 'e10adc3949ba59abbe56e057f20f883e', '蔡鸿', '女', '13612269831', null, '读者');
INSERT INTO `user` VALUES ('22', '3216003226', 'e10adc3949ba59abbe56e057f20f883e', '苏健珊', '女', '13612269317', null, '读者');
INSERT INTO `user` VALUES ('23', '3216004461', 'e10adc3949ba59abbe56e057f20f883e', '吴媚敏', '女', '13612223981', null, '读者');
