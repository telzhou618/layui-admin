/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.44 : Database - restful-api
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`restful-api` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `restful-api`;

/*Table structure for table `tb_blog` */

DROP TABLE IF EXISTS `tb_blog`;

CREATE TABLE `tb_blog` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `userId` varchar(50) DEFAULT NULL COMMENT '作者',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_blog` */

insert  into `tb_blog`(`id`,`title`,`content`,`userId`,`createTime`) values ('1c6732d554044385837ba956112a252a','呵呵','哈哈','uidxxx','2017-06-09 09:53:10'),('436bfa2df0fe4b65b126dec65b3efb34','测试','测试内容','09c63f873a9e472ca464accb61cd5e51','2016-12-30 11:16:31'),('45e3d28b04774f48b8be0206a90cffe8','机构和计划','发的','uidxxx','2017-06-20 11:45:51'),('52d47feaf7404c818255e5ae021eacab','测试','测试内容','09c63f873a9e472ca464accb61cd5e51','2016-12-30 11:16:18'),('5a8d876cfe164695b5543091f7a91629','呵呵','哈哈','uidxxx','2017-06-09 10:27:40'),('7177a02736ed4985b4fc74a356a7b96e','测试','测试内容','09c63f873a9e472ca464accb61cd5e51','2016-12-30 11:16:30'),('72142bfdfbb84bd793e9dd8e961f3518','测试','测试内容','09c63f873a9e472ca464accb61cd5e51','2016-12-30 11:16:21'),('8f2b518ffb9645dc8e36030fa6cd2181','更多发挥','广告费','uidxxx','2017-06-20 11:25:20'),('e975e62814d04264bf97793ca71609fb','呵呵','哈哈',NULL,'2017-06-09 09:52:36');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `userDesc` varchar(300) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`userName`,`password`,`createTime`,`userDesc`) values ('09c63f873a9e472ca464accb61cd5e51','test','DC483E80A7A0BD9EF71D8CF973673924','2016-12-12 15:50:39','aaaaa'),('4754f010ef344c59b728ea60809ab926','e100000','1973EBD114AAB8BD85457E037BBBFA62','2016-12-12 13:43:59','aa44515112121'),('549d321508db446e9bcaa477835fe5f1','admin','E10ADC3949BA59ABBE56E057F20F883E','2016-12-14 14:35:08','所有权限'),('629ba7eb1d8944d2873ecfc6896288e7','zhangsan','25F9E794323B453885F5181F1B624D0B','2016-12-12 11:49:21','张三负责系统的委会和开发工作。');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
