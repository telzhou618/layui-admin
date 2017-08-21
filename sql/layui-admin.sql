/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.54 : Database - alex-admin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `userName` varchar(50) NOT NULL COMMENT '用户',
  `logTitle` varchar(300) DEFAULT NULL COMMENT '日志标题',
  `logContent` text COMMENT '日志内容',
  `clientIp` varchar(50) DEFAULT NULL COMMENT '客户端IP',
  `requestUrl` varchar(300) DEFAULT NULL COMMENT '请求URL',
  `requestMethod` varchar(20) DEFAULT NULL COMMENT '请求方式',
  `requestParams` text COMMENT '参数',
  `logTime` datetime DEFAULT NULL COMMENT '日志时间',
  `other` varchar(300) DEFAULT NULL COMMENT '备用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`userName`,`logTitle`,`logContent`,`clientIp`,`requestUrl`,`requestMethod`,`requestParams`,`logTime`,`other`) values ('1941e32d49f3442bb30d88dbac01705d','admin','','编辑用户','0:0:0:0:0:0:0:1','/user/doEdit','POST','{\"password\":[\"\"],\"userImg\":[\"/upload/2017-07-06/bw98sjevkkgi3cvzls5hqpc2dxhzo7qv.jpg\"],\"file\":[\"\"],\"userState\":[\"1\"],\"password2\":[\"\"],\"userDesc\":[\"超级管理员\"],\"id\":[\"8ec475bfc69041a4a3984c5510f7982b\"],\"userName\":[\"admin\"],\"roleIds[]\":[\"737933bffef640329a4f864c4e2746ba\"]}','2017-07-06 15:39:36',NULL),('3ccae7cd77544b929d379d238364a78b','admin','','编辑用户','0:0:0:0:0:0:0:1',NULL,'POST','{\"password\":[\"\"],\"userImg\":[\"/upload/2017-07-06/bw98sjevkkgi3cvzls5hqpc2dxhzo7qv.jpg\"],\"file\":[\"\"],\"userState\":[\"1\"],\"password2\":[\"\"],\"userDesc\":[\"超级管理员\"],\"id\":[\"8ec475bfc69041a4a3984c5510f7982b\"],\"userName\":[\"admin\"],\"roleIds[]\":[\"737933bffef640329a4f864c4e2746ba\"]}','2017-07-06 15:38:10',NULL),('5d6dd88bed3045e385870ad5db52b3a2','admin','','更新角色状态','0:0:0:0:0:0:0:1','/role/roleState','GET','{\"roleState\":[\"true\"],\"id\":[\"737933bffef640329a4f864c4e2746ba\"],\"_\":[\"1499327175984\"]}','2017-07-06 15:46:20',NULL),('8e97fe59385d430d862f73e09087934b','admin','','更新角色状态','0:0:0:0:0:0:0:1','/role/roleState','GET','{\"roleState\":[\"true\"],\"id\":[\"737933bffef640329a4f864c4e2746ba\"],\"_\":[\"1499327175982\"]}','2017-07-06 15:46:19',NULL),('be6e9a25b12c47fe92de521003f50382','admin','','编辑角色','0:0:0:0:0:0:0:1','/role/doEdit','POST','{\"roleName\":[\"超级管理员\"],\"roleDesc\":[\"超级管理员\"],\"id\":[\"737933bffef640329a4f864c4e2746ba\"]}','2017-07-06 15:46:18',NULL),('c0f7a4af5dc240a38ed73c702e129baa','admin','','更新角色状态','0:0:0:0:0:0:0:1','/role/roleState','GET','{\"roleState\":[\"false\"],\"id\":[\"737933bffef640329a4f864c4e2746ba\"],\"_\":[\"1499327175981\"]}','2017-07-06 15:46:19',NULL),('e28ca7d678fa41eb8bc712c6ad59f1af','admin','','更新角色状态','0:0:0:0:0:0:0:1','/role/roleState','GET','{\"roleState\":[\"false\"],\"id\":[\"737933bffef640329a4f864c4e2746ba\"],\"_\":[\"1499327175983\"]}','2017-07-06 15:46:19',NULL);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `menuName` varchar(50) NOT NULL COMMENT '菜单名称',
  `pid` varchar(50) NOT NULL COMMENT '父级菜单ID',
  `url` varchar(255) DEFAULT NULL COMMENT '连接地址',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `deep` int(11) DEFAULT NULL COMMENT '深度',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `resource` varchar(50) DEFAULT NULL COMMENT '资源名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menuName`,`pid`,`url`,`icon`,`sort`,`deep`,`code`,`resource`) values ('0c9b5fc8b44b41d1871a8fc8300247ec','删除菜单','4','','',4,3,'010303','deleteMenu'),('1','系统管理','0','','icon-settings',0,1,'01',''),('1db9105008404a3485b6fac30dba3c0e','查看角色列表','3','','',0,3,'010200','listRole'),('2','用户管理','1','/user/list','icon-yonghu',1,2,'0101','user'),('3','角色管理','1','/role/list','icon-jiaose',2,2,'0102','role'),('3987d383a7a74b45902e14e027d9b56e','更新角色状态','3','','',6,3,'010206','updateStateRole'),('3b18f3d776c74266b63c2542825aa3c3','SPU管理','be659f4c66fb4db989f654eb408d86e1','','icon-SPUguanli',0,2,'0201','spuList'),('3f26102ef0e04c3c9328cb97067cc5fa','创建菜单','4','','',1,3,'010301','addMenu'),('4','菜单管理','1','/menu/list','icon-menu',3,2,'0103','menu'),('4253190001c1480fb0d631d64d150535','编辑用户','2','','',2,3,'010102','editUser'),('42dd5ae31e3a43b3a197440e8ec19a94','监控列表','f5a20c82110b4a3ea9e30ca01a038680','','',1,3,'010701','monitorList'),('488ef1eff57b4827acade7c0744278ce','查看菜单列表','4','','',0,3,'010300','listMenu'),('60dda993d87647f5989c15f14f866df9','新增角色','3','','',1,3,'010201','addRole'),('649b484b58414d91aefa5a26143e6557','删除用户','2','','',3,3,'010103','deleteUser'),('686630c7cb624cc786dcdc9958971a6b','编辑角色','3','','',2,3,'010202','editRole'),('809db56d93e848e8b43396e125803884','日志管理','1','/log/list','icon-rizhi',4,2,'0104',''),('9c51e94cef99435780fb72bdb923a2ab','更新用户状态','2','','',4,3,'010104','updateStateUser'),('a5ebf29168234406939856bc6890c86b','角色授权','3','','',4,3,'010204','authRole'),('a73802e513cc4465883530ec8074abbb','新增用户','2','','',1,3,'010101','addUser'),('b4e7232189b14cf3ba160cf7b0d3bf37','删除角色','3','','',3,3,'010203','deleteRole'),('be659f4c66fb4db989f654eb408d86e1','商品管理','0','/goods/page','icon-shangpin',0,1,'02',''),('c0c304be5c294114b5bc0d0c3acef992','日志列表','809db56d93e848e8b43396e125803884','','',1,3,'010401','listLog'),('d2bc30feb5474a1bb7e02d48d39a3f8a','查看用户列表','2','','',0,3,'010100','listUser'),('dc5f478d98ed4297a8ae638fe90df050','编辑菜单','4','','',3,3,'010302','editMenu'),('f5a20c82110b4a3ea9e30ca01a038680','系统监控','1','/druid/wall.html','icon-jiankong',7,2,'0107',''),('f899f3d3baec4571b1c786717f9906fd','批量删除角色','3','','',5,3,'010205','deleteBatchRole');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `roleName` varchar(50) NOT NULL COMMENT '角色名称',
  `roleDesc` varchar(300) DEFAULT NULL COMMENT '角色描述',
  `roleState` int(2) DEFAULT '1' COMMENT '状态,1-启用,-1禁用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`roleName`,`roleDesc`,`roleState`,`createTime`) values ('2a9b728a431246b08f853c2529e6ba84','测试角色','测试',1,'2017-02-28 15:15:41'),('3bd9f9e5fa8a4e0587a78cf697e4a9ce','只读角色','只读角色',1,'2017-07-06 14:35:37'),('737933bffef640329a4f864c4e2746ba','超级管理员','超级管理员',1,'2016-12-14 10:22:34');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `roleId` varchar(50) NOT NULL COMMENT '角色主键',
  `menuId` varchar(50) NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`roleId`,`menuId`) values ('0007db394e2a4c48a4dfbe2401ac0a39','3bd9f9e5fa8a4e0587a78cf697e4a9ce','3b18f3d776c74266b63c2542825aa3c3'),('0022b20f5a8243d68b729da860fa50d4','2a9b728a431246b08f853c2529e6ba84','3f26102ef0e04c3c9328cb97067cc5fa'),('0685b1490a3a4e62a2b69199f964de2a','ab7e4b34e5d141fa8566fdbb5d3e66f7','d2bc30feb5474a1bb7e02d48d39a3f8a'),('09290e0b4a5949ddbe1da1b087fef750','737933bffef640329a4f864c4e2746ba','be659f4c66fb4db989f654eb408d86e1'),('0ecbbac0284b483488d57a456f6cb339','2a9b728a431246b08f853c2529e6ba84','0c9b5fc8b44b41d1871a8fc8300247ec'),('0f84e4f5f21c4d43836041eb5b415d3c','737933bffef640329a4f864c4e2746ba','3987d383a7a74b45902e14e027d9b56e'),('121b1db2c51d4d3ba7b8c0ebc3e1e32e','a21876314a764438b6af6bfa422ec09a','2'),('15b2a5beabe44f178e4ef76ff7d191ee','164f77a778a341d8925251350be7f916','649b484b58414d91aefa5a26143e6557'),('1a75a9b255464dc3b250425dce81a676','164f77a778a341d8925251350be7f916','d2bc30feb5474a1bb7e02d48d39a3f8a'),('1bf7d5dbf12e49178caa2b05417e6700','737933bffef640329a4f864c4e2746ba','dc5f478d98ed4297a8ae638fe90df050'),('1c5383a25c274021aabb80835184b768','737933bffef640329a4f864c4e2746ba','2'),('1c6642cb915442359d9fc2543b5a8c79','a21876314a764438b6af6bfa422ec09a','488ef1eff57b4827acade7c0744278ce'),('1cc9cecb5d784522a42781a9b6de2369','737933bffef640329a4f864c4e2746ba','809db56d93e848e8b43396e125803884'),('1e0e01a71b8842e7ac203036f7384685','ab7e4b34e5d141fa8566fdbb5d3e66f7','4253190001c1480fb0d631d64d150535'),('1e9750fe4eff43f6917a16942d3bf3a6','737933bffef640329a4f864c4e2746ba','0c9b5fc8b44b41d1871a8fc8300247ec'),('1f51087024e4483b876576101c5a0865','3bd9f9e5fa8a4e0587a78cf697e4a9ce','1'),('21629df3640f4ccdb74c597142840ddd','a21876314a764438b6af6bfa422ec09a','3f26102ef0e04c3c9328cb97067cc5fa'),('2206b8a75e484ccabfce6a086e14bb43','ab7e4b34e5d141fa8566fdbb5d3e66f7','2'),('23e9069b749e4c25b964bbcbb897b64a','2a9b728a431246b08f853c2529e6ba84','3b18f3d776c74266b63c2542825aa3c3'),('2aafe086a2024a2e8e8fc2e3edf15407','2a9b728a431246b08f853c2529e6ba84','f899f3d3baec4571b1c786717f9906fd'),('2cf6f60c33de46a2a77e65ad91d778a1','737933bffef640329a4f864c4e2746ba','b4e7232189b14cf3ba160cf7b0d3bf37'),('2f4917cb5c3842fe89a2f6fa7f0183b2','2a9b728a431246b08f853c2529e6ba84','4'),('35c27b37c89c4e6ca7160ef320329961','1d0d43b9fbbe4c078beb4a732309fe64','1'),('3aceb6111772490e9887904fb54949e3','eb2e1fa3caa448658da909cf141788f8','9'),('3dfc95301c054556a0cf82867626b19c','737933bffef640329a4f864c4e2746ba','4253190001c1480fb0d631d64d150535'),('3feeac67800b4b2d9a7a32b990d2047e','2a9b728a431246b08f853c2529e6ba84','d2bc30feb5474a1bb7e02d48d39a3f8a'),('4443089dc08e44308810848bc7458e5b','737933bffef640329a4f864c4e2746ba','a73802e513cc4465883530ec8074abbb'),('48dd8350fb7a4c3cb4406cbfa78aa524','a21876314a764438b6af6bfa422ec09a','1'),('4e99926456de49018f18cad615842d74','737933bffef640329a4f864c4e2746ba','42dd5ae31e3a43b3a197440e8ec19a94'),('537af500a6b9442eb71e0d77a1ea4841','1d0d43b9fbbe4c078beb4a732309fe64','9'),('5627ff1dd2304e51b16ccd3f4d785fcd','164f77a778a341d8925251350be7f916','4253190001c1480fb0d631d64d150535'),('5f0977d73e2843d39a10d966887522fa','2a9b728a431246b08f853c2529e6ba84','60dda993d87647f5989c15f14f866df9'),('630d0576372c41f2a5597ea6f60266ca','737933bffef640329a4f864c4e2746ba','d2bc30feb5474a1bb7e02d48d39a3f8a'),('6903ea6044034f149256ebf5f8f92cff','737933bffef640329a4f864c4e2746ba','4'),('6f41c85dd5174f78ad2db183db359b55','a21876314a764438b6af6bfa422ec09a','4'),('6fefcf99f69e4351bf37c590fb1ccf40','737933bffef640329a4f864c4e2746ba','1'),('712118e6fe374f92b3beaffc1019952a','f08487637b0d4bfc9accc14cbca6f1cd','3'),('7179e60570a94dfb97d8cd07d87367d7','737933bffef640329a4f864c4e2746ba','3'),('72af0b08728742c6a7ee6bb5ba1d37d8','3bd9f9e5fa8a4e0587a78cf697e4a9ce','1db9105008404a3485b6fac30dba3c0e'),('750868dfc79a4a32841da56d1601a8d1','f08487637b0d4bfc9accc14cbca6f1cd','1'),('77fd54d3ab0d4eaa8605346d93095eb9','eb2e1fa3caa448658da909cf141788f8','8'),('7b464f14ffbe4a32a550a7b16f360517','164f77a778a341d8925251350be7f916','a73802e513cc4465883530ec8074abbb'),('7d32bd0b812240f7b7fca184e6b17625','3bd9f9e5fa8a4e0587a78cf697e4a9ce','d2bc30feb5474a1bb7e02d48d39a3f8a'),('7e263d8880d74c20831402beb4aec25b','737933bffef640329a4f864c4e2746ba','1db9105008404a3485b6fac30dba3c0e'),('7f2d88d5d4f3473b8f61fd1390e9fd1e','3bd9f9e5fa8a4e0587a78cf697e4a9ce','4'),('82728dbd138243c48e18b3af4f98147b','164f77a778a341d8925251350be7f916','1'),('829dc9e65dba4a3a9e3f76a906151205','ab7e4b34e5d141fa8566fdbb5d3e66f7','1'),('82b6803711884de5b40e79b8d278d8f8','3bd9f9e5fa8a4e0587a78cf697e4a9ce','f5a20c82110b4a3ea9e30ca01a038680'),('8696d65710da4fd39b32716bff349745','2a9b728a431246b08f853c2529e6ba84','b4e7232189b14cf3ba160cf7b0d3bf37'),('8893499f02da4efaaff9293fda3afd41','737933bffef640329a4f864c4e2746ba','a5ebf29168234406939856bc6890c86b'),('8a4de646d12c411d8d28cec9642e776e','2a9b728a431246b08f853c2529e6ba84','a73802e513cc4465883530ec8074abbb'),('8a6381f1ddf943a2a3bc42629c339f15','1d0d43b9fbbe4c078beb4a732309fe64','8'),('8be3672bb953491e8ec6289c909c9ae3','2a9b728a431246b08f853c2529e6ba84','a5ebf29168234406939856bc6890c86b'),('93499a0cbd434193947e7eed08ba412f','737933bffef640329a4f864c4e2746ba','649b484b58414d91aefa5a26143e6557'),('96043e15cb0a49a4ab871a5bfa4aaa50','2a9b728a431246b08f853c2529e6ba84','3'),('976499d229b349dba84d804986b5a598','a21876314a764438b6af6bfa422ec09a','4253190001c1480fb0d631d64d150535'),('9b51121484c64285b4726941a80e998c','3bd9f9e5fa8a4e0587a78cf697e4a9ce','2'),('9c988de0c4334b93af5cb4c827cfbba2','2a9b728a431246b08f853c2529e6ba84','dc5f478d98ed4297a8ae638fe90df050'),('9d96d98deafb4a41989475cf6f868814','737933bffef640329a4f864c4e2746ba','3b18f3d776c74266b63c2542825aa3c3'),('9e02696f59654038af6ba409542ec415','2a9b728a431246b08f853c2529e6ba84','2'),('a099cc695e2b4ed790040b02048e3aa2','3bd9f9e5fa8a4e0587a78cf697e4a9ce','be659f4c66fb4db989f654eb408d86e1'),('a7b8fe29da74433fa486d8cd130c97ab','737933bffef640329a4f864c4e2746ba','60dda993d87647f5989c15f14f866df9'),('aad8290942334ab8ae924eec121246e5','ab7e4b34e5d141fa8566fdbb5d3e66f7','649b484b58414d91aefa5a26143e6557'),('ad55487f431741299fa0d55a281fea91','2a9b728a431246b08f853c2529e6ba84','4253190001c1480fb0d631d64d150535'),('adff36f9a7b34e1aa0cef6ba66398e46','a21876314a764438b6af6bfa422ec09a','3'),('ae206c19b7aa48eeacf3665cded4f306','a21876314a764438b6af6bfa422ec09a','686630c7cb624cc786dcdc9958971a6b'),('afee6635bae9403a85097631d76ad7ad','f08487637b0d4bfc9accc14cbca6f1cd','2'),('b0c218d14aed40e0a701d89368d49f66','164f77a778a341d8925251350be7f916','2'),('b22af56a663b4c35a37de4dcd0559f37','2a9b728a431246b08f853c2529e6ba84','649b484b58414d91aefa5a26143e6557'),('b71cea3a72d545ad9d1fb5f302c0d035','a21876314a764438b6af6bfa422ec09a','60dda993d87647f5989c15f14f866df9'),('baa5d4222644488890aa8ff4df64852f','737933bffef640329a4f864c4e2746ba','f5a20c82110b4a3ea9e30ca01a038680'),('bb55745930954773b7f2972d3115442d','2a9b728a431246b08f853c2529e6ba84','686630c7cb624cc786dcdc9958971a6b'),('bb5d89556e364aabb62f3d0c6798787b','737933bffef640329a4f864c4e2746ba','3f26102ef0e04c3c9328cb97067cc5fa'),('bbdd4516f284426483bb3e9075d229e5','2a9b728a431246b08f853c2529e6ba84','1db9105008404a3485b6fac30dba3c0e'),('bc8946474b6b46089650d7c64e69171d','737933bffef640329a4f864c4e2746ba','488ef1eff57b4827acade7c0744278ce'),('bccc077fd2824fc1ad236361c2d1921e','3bd9f9e5fa8a4e0587a78cf697e4a9ce','488ef1eff57b4827acade7c0744278ce'),('c015003b62a84e44aa678bdd9d9ce99b','a21876314a764438b6af6bfa422ec09a','a73802e513cc4465883530ec8074abbb'),('c1737d45e5ca41d6b7a799c977725fac','737933bffef640329a4f864c4e2746ba','f899f3d3baec4571b1c786717f9906fd'),('c280e565620442f988ceb829289f60c1','ab7e4b34e5d141fa8566fdbb5d3e66f7','a73802e513cc4465883530ec8074abbb'),('cc208a9939cc4640a79afbf8bc7ec193','737933bffef640329a4f864c4e2746ba','c0c304be5c294114b5bc0d0c3acef992'),('cd40c275647b4254ae92169c6406952e','3bd9f9e5fa8a4e0587a78cf697e4a9ce','42dd5ae31e3a43b3a197440e8ec19a94'),('d734c00951a34287bb850d58264e21cb','737933bffef640329a4f864c4e2746ba','9c51e94cef99435780fb72bdb923a2ab'),('d9e86f4a7d6145358e96c1a576d57990','2a9b728a431246b08f853c2529e6ba84','1'),('e045bf84cd0345a2811d3906de5f7b40','a21876314a764438b6af6bfa422ec09a','dc5f478d98ed4297a8ae638fe90df050'),('e37a31ed66df4a9a83ecfb5a574a8c28','2a9b728a431246b08f853c2529e6ba84','be659f4c66fb4db989f654eb408d86e1'),('e47f40fb9127424ab4da93b1712cddf0','737933bffef640329a4f864c4e2746ba','686630c7cb624cc786dcdc9958971a6b'),('e8ec8b53a6f543b0a54986a0ecc6799b','3bd9f9e5fa8a4e0587a78cf697e4a9ce','3'),('ee9bc21d592e472b911b0b150085719a','3bd9f9e5fa8a4e0587a78cf697e4a9ce','c0c304be5c294114b5bc0d0c3acef992'),('eec604d8d35d4529a260c1505ec41ce1','2a9b728a431246b08f853c2529e6ba84','488ef1eff57b4827acade7c0744278ce'),('f0ff3269b1994d76b015c59bed009386','a21876314a764438b6af6bfa422ec09a','d2bc30feb5474a1bb7e02d48d39a3f8a'),('f4ad113b195c4efdae8cda777691bec4','3bd9f9e5fa8a4e0587a78cf697e4a9ce','809db56d93e848e8b43396e125803884'),('f85b67e9af3d42afa018b8790d660189','a21876314a764438b6af6bfa422ec09a','1db9105008404a3485b6fac30dba3c0e');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `userState` int(2) NOT NULL DEFAULT '1' COMMENT '用户状态,1-启用,-1禁用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `userDesc` varchar(300) DEFAULT NULL COMMENT '描述',
  `userImg` varchar(300) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`userName`,`password`,`userState`,`createTime`,`userDesc`,`userImg`) values ('230e099407244982895ad972929d7228','test123','CC03E747A6AFBBCBF8BE7668ACFEBEE5',1,'2017-07-06 14:37:07','','/upload/2017-07-06/xktaxi7agg7q3b5ks23rvart84xpfa26.jpg'),('8ec475bfc69041a4a3984c5510f7982b','admin','7FEF6171469E80D32C0559F88B377245',1,'2017-07-05 17:13:45','超级管理员','/upload/2017-07-06/bw98sjevkkgi3cvzls5hqpc2dxhzo7qv.jpg'),('be9cb9ae66b64c54a85abee36c274a55','test2','7FEF6171469E80D32C0559F88B377245',1,'2017-07-05 17:15:07','测试用户','/upload/2017-07-05/gwh9rtgdr5ykm3wk2etilrazzgwf3k0d.jpg');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `Id` varchar(50) NOT NULL COMMENT '主键',
  `userId` varchar(50) NOT NULL COMMENT '用户主键',
  `roleId` varchar(50) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`Id`,`userId`,`roleId`) values ('77f8dd5dd4a141318627da6ffcfb92d5','8ec475bfc69041a4a3984c5510f7982b','737933bffef640329a4f864c4e2746ba'),('7a895ac019ab487c8c0c767580fb20c2','230e099407244982895ad972929d7228','3bd9f9e5fa8a4e0587a78cf697e4a9ce'),('e293016199924f698771625ab376c1f6','be9cb9ae66b64c54a85abee36c274a55','2a9b728a431246b08f853c2529e6ba84');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
