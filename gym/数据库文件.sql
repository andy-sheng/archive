/*
MySQL Backup
Source Server Version: 5.6.12
Source Database: gym
Date: 2014/6/2 14:05:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `coachbook`
-- ----------------------------
DROP TABLE IF EXISTS `coachbook`;
CREATE TABLE `coachbook` (
  `email` varchar(50) DEFAULT NULL,
  `coachemail` varchar(50) DEFAULT NULL,
  `addition` text,
  `date` int(11) DEFAULT NULL,
  `time` tinyint(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `coachtime`
-- ----------------------------
DROP TABLE IF EXISTS `coachtime`;
CREATE TABLE `coachtime` (
  `email` varchar(50) NOT NULL DEFAULT '',
  `starttime` tinyint(4) DEFAULT NULL,
  `endtime` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `email` varchar(50) DEFAULT NULL,
  `contant` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `starttime` int(11) DEFAULT NULL,
  `amount` tinyint(4) DEFAULT NULL,
  `lasttime` int(11) DEFAULT NULL,
  `site` varchar(50) DEFAULT NULL,
  `capacity` tinyint(4) DEFAULT NULL,
  `description` text,
  `price` int(4) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `coachemail` varchar(50) DEFAULT NULL,
  `time` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `coursetmp`
-- ----------------------------
DROP TABLE IF EXISTS `coursetmp`;
CREATE TABLE `coursetmp` (
  `id` int(11) NOT NULL,
  `site` varchar(50) DEFAULT NULL,
  `capacity` tinyint(4) DEFAULT NULL,
  `description` text,
  `price` int(4) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dayoff`
-- ----------------------------
DROP TABLE IF EXISTS `dayoff`;
CREATE TABLE `dayoff` (
  `email` varchar(50) NOT NULL,
  `time` int(11) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `instrument`
-- ----------------------------
DROP TABLE IF EXISTS `instrument`;
CREATE TABLE `instrument` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL DEFAULT '0',
  `message` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `notify`
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify` (
  `id` int(11) NOT NULL DEFAULT '0',
  `head` tinytext,
  `content` text,
  `date` int(11) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ratecoach`
-- ----------------------------
DROP TABLE IF EXISTS `ratecoach`;
CREATE TABLE `ratecoach` (
  `email` varchar(50) DEFAULT NULL,
  `responsibility` varchar(50) DEFAULT NULL,
  `planning` varchar(50) DEFAULT NULL,
  `listener` varchar(50) DEFAULT NULL,
  `fame` varchar(50) DEFAULT NULL,
  `experience` varchar(50) DEFAULT NULL,
  `progress` varchar(50) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ratecourse`
-- ----------------------------
DROP TABLE IF EXISTS `ratecourse`;
CREATE TABLE `ratecourse` (
  `id` int(11) DEFAULT NULL,
  `content` int(11) DEFAULT NULL,
  `style` int(11) DEFAULT NULL,
  `interaction` int(11) DEFAULT NULL,
  `progress` int(11) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `record`
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `email` varchar(50) DEFAULT NULL,
  `operation` varchar(20) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `email` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `paypwd` varchar(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `rank` tinyint(4) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  `nickname` varchar(50) NOT NULL,
  `cardid` int(11) DEFAULT NULL,
  `cardstatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`email`),
  KEY `index` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `usercourse`
-- ----------------------------
DROP TABLE IF EXISTS `usercourse`;
CREATE TABLE `usercourse` (
  `email` varchar(50) DEFAULT NULL,
  `courseid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `coachbook` VALUES ('1@qq.com','4@qq.com','啊师傅大师傅','1401382601','12'), ('1@qq.com','4@qq.com','123123123','1401384783','20'), ('1@qq.com','4@qq.com','山东分公司的官方说的','1401464978','19'), ('1@qq.com','4@qq.com','yghjg','1401551387','9'), ('1@qq.com','4@qq.com','','1401551415','11'), ('1@qq.com','4@qq.com','','1402200276','0'), ('1@qq.com','4@qq.com','3234243234','1401858904','10');
INSERT INTO `coachtime` VALUES ('4@qq.com','9','15');
INSERT INTO `course` VALUES ('1','健美操','1401382601','10','60','健身房','5','健美操是一种有氧运动，通常采用徒手或持轻器械进行练习，在氧供应充足的情况下，是人体有氧系统提供能量的一种运动形式。其运动特征是持续一定时间的、中低程度的全身性运动，主要锻炼练习者的心肺功能，是有氧耐力素质的基础。跳健美操有诸多好处，不仅能帮助我们有效的强身健体，而且还有减肥的功效，这种运动减肥方法集健美和健身于一体，特别适合女性，受到了广大女性同胞的喜爱。','1000','发布','4@qq.com','9'), ('2','瑜伽','1401382601','10','60','健身房','0','瑜伽起源于印度，距今有五千多年的历史文化被人们称为“世界的瑰宝”。瑜伽发源印度北部的喜马拉雅山麓地带，古印度瑜伽修行者在大自然中修炼身心时，无意中发现各种动物与植物天生具有治疗、放松、睡眠、或保持清醒的方法，患病时能不经任何治疗而自然痊愈。于是古印度瑜伽修行者根据动物的姿势观察、模仿并亲自体验，创立出一系列有益身心的锻炼系统，也就是体位法。这些姿势历经了五千多年的锤炼，瑜伽教给人们的治愈法，让世世代代的人从中获益。','1000','发布','4@qq.com','12'), ('3','动感单车','1401382601','10','60','健身房','8','动感单车（自行车）英文名字（SPINNING），是由美国私人教练兼极限运动员JOHNNYG于二十世纪八十年代首创，是一种结合了音乐、视觉效果等独特的充满活力的室内自行车训练课程。','1000','发布','4@qq.com','11'), ('4','普拉提','1401382601','10','60','健身房','3','彼拉提斯（英语：Pilates，英语发音：/pɨˈlɑːtiːz/，德语： [piˈlaːtəs]），中国大陆多翻译为普拉提或普拉提斯，是由德国人约瑟夫·皮拉提斯（Joseph Pilates）在二十世纪发展的体适能（Physical fitness）运动。\r\n约瑟夫·皮拉提斯为他开发的这项运动，由英语：Control（控制）创造了一个新字 英语：Contrology，因为他相信，这项运动的核心，就在于对肌肉的控制。','1000','发布','4@qq.com','9'), ('5','极限搏击操','1401382601','10','60','健身房','5','搏击操，是一种有氧操，是Aerobics的又一创新，它结合了拳击、泰拳、跆拳道、散手、太极的基本动作，遵循健美操最新编排方法，在强有力的音乐节拍下完成的一种身体锻炼方式。有氧搏击操英文名为kickboxing，最早是由欧洲的搏击选手与职业健身操运动员推出的，其具体形式是将拳击、空手道、跆拳道功夫，甚至一些舞蹈动作混合在一起，并配合强劲的音乐，成为一类风格独特的健身操。','1000','发布','4@qq.com','13'), ('6','有氧拉丁操','1401382601','10','60','健身房','10','有氧拉丁采用传统的拉丁步伐为主，其音乐则较为舒缓，配合音乐节奏进行呼吸吐纳，能起到较好的健身效果。有氧拉丁最大的特点是在运动中洋溢着拉丁舞蹈特有的欢乐与激情。有氧拉丁可以增加健身趣味性，对于都市人释放压力、放松自己特别有好处。','100000','发布','4@qq.com','14');
INSERT INTO `coursetmp` VALUES ('5','5','5','5','5','添加');
INSERT INTO `dayoff` VALUES ('4@qq.com','1401727692','拒绝'), ('4@qq.com','1402202949','待审核');
INSERT INTO `instrument` VALUES ('1','哑铃','借出'), ('2','杠铃','在馆'), ('3','跑步机','报修'), ('4','跑步机','在馆'), ('5','跑步机','借出'), ('6','瑜伽垫','借入'), ('7','11','在馆');
INSERT INTO `notify` VALUES ('0','123','123','1401177380','all'), ('1','hehe','欢迎刘轩大叔加入我们的俱乐部','1401119606','all'), ('2','新开课程','健美操是一种有氧运动，通常采用徒手或持轻器械进行练习，在氧供应充足的情况下，是人体有氧系统提供能量的一种运动形式。其运动特征是持续一定时间的、中低程度的全身性运动，主要锻炼练习者的心肺功能，是有氧耐力素质的基础。跳健美操有诸多好处，不仅能帮助我们有效的强身健体，而且还有减肥的功效，这种运动减肥方法集健美和健身于一体，特别适合女性，受到了广大女性同胞的喜爱。','1401119606','all'), ('3','雷霆','北京时间2014年5月26日，雷霆在主场以106-97战胜马刺，将系列赛大比分扳为1-2，之前被报道为赛季报销的伊巴卡本场火线复出，全场7中6砍下15分，7篮板和4盖帽。','1401119606','customer'), ('4','波波维奇：马努可能没事也可能赛季报销','北京时间5月26日，马刺队在今天的西部决赛第三场比赛中97-106输给了雷霆队，此役过后，马刺队在本轮系列赛中2比1领先对手。','1401121108','customer'), ('5','帕金斯：雷吉一旦投入，无后卫能面对他得分','北京时间5月26日，雷霆队在今天的西部决赛第三场比赛中106-97击败了马刺队，本场比赛过后，雷霆队将总比分改写为1比2。','1401121108','coach'), ('6','韦德向萨姆-卡塞尔学投篮假动作','北京时间5月26日，热火当家后卫德维恩-韦德在接受采访的时候透露自己很喜欢“外星人”萨姆-卡塞尔的打球方式，他的假动作就是模仿卡塞尔的。\r\n\r\n“我一直都在谈论卡塞尔。”韦德今天说到，“我喜欢他在场上像老人一样的打球方式，当他在密尔沃基的时候我就很仔细地研究过他，他会在场上逼疯你，节奏很慢，就像是老人一样。','1401121108','coach'), ('7','伊巴卡感觉自己能打西决第四场','北京时间5月26日，雷霆队在今天的西部决赛第三场比赛中以106-97击败了马刺队，本场比赛过后，雷霆队将总比分改写为1比2。\r\n\r\n此役，雷霆队的赛尔吉-伊巴卡迎来复出，最终贡献了15分7个篮板4个盖帽。\r\n\r\n赛后伊巴卡透露，他感觉他应该能打接下来的第四场比赛，但是他也预期自己会感到疼痛。','1401121108','admin'), ('8','参议院全票通过，布拉奇获菲律宾国籍','菲律宾当地时间5月26日消息，菲律宾参议院今天就安德雷-布拉奇申请菲律宾国籍一事进行最终投票，投票结果为：20票通过，0票反对，0票弃权。\r\n\r\n这样的结果意味着布拉奇顺利地获得了菲律宾国籍，他将代表菲律宾参加男篮世界杯。','1401121108','admin'), ('9','撒地方撒旦法司法','撒旦发生的发生的发生的发顺丰','1401177731','admin'), ('10','撒旦发生的发生的发生发的',' 啊时代发生的发生的发生的发生发生的发生天气热','1401177933','all'), ('11','撒发生的发生的fret儿童','情人特我让他问题','1401178001','all'), ('12','发的公司的公司的股份十多个','撒地方撒旦法司法火热问题过生日','1401178257','customer'), ('13','asdfasdf','阿斯顿发生的法','1401180075','customer'), ('14','啊沙发上','阿斯顿发生的发生地方','1401180118','customer'), ('15','呵呵额呵呵','爱仕达撒发顺丰大师傅大是大非撒噶','1401180408','customer'), ('16','阿斯顿发顺丰打算发生的发生','而特瑞特温柔提问','1401180446','admin'), ('17','阿斯顿发顺丰打算发生的发生','而特瑞特温柔提问','1401180477','admin'), ('18','啊撒速度发顺丰','阿斯顿发生的法沙发上','1401180517','customer'), ('19','撒旦法双方的','阿桑的发生发生','1401180555','customer'), ('20','asdfasdf','asdfasdf','1401180660','customer'), ('21','委屈而委屈而','请问人情味热情','1401180815','customer'), ('22','asdfasdf','阿斯顿发顺丰','1401180861','customer'), ('23','教练请假审核结果','管理员批准了2014年06月03日的请假请求','1401644729','all'), ('24','教练请假审核结果','管理员拒绝了的2014年06月03日的请假请求','1401644824','all'), ('25','教练请假审核结果','管理员拒绝了的2014年06月03日的请假请求','1401644935','all'), ('26','教练请假审核结果','管理员拒绝了李华的2014年06月03日的请假请求','1401645028','all'), ('27','教练请假审核结果','管理员拒绝了的1970年01月01日的请假请求','1401647285','all'), ('28','教练请假审核结果','管理员批准了的1970年01月01日的请假请求','1401647286','all'), ('29','教练请假审核结果','管理员拒绝了的1970年01月01日的请假请求','1401647331','all'), ('30','教练请假审核结果','管理员批准了的1970年01月01日的请假请求','1401647332','all'), ('31','教练请假审核结果','管理员拒绝了的1970年01月01日的请假请求','1401648228','all'), ('32','教练请假审核结果','管理员拒绝了的1970年01月01日的请假请求','1401648263','all');
INSERT INTO `ratecoach` VALUES ('4@qq.com','认真负责','基本满意','善于并且主动倾听意见和建议','德高望重','经验丰富','飞跃进步','虽然特热特色认同');
INSERT INTO `ratecourse` VALUES ('1','5','5','5','5','呵呵呵呵好');
INSERT INTO `record` VALUES ('1@qq.com','充值','1000','1400939126'), ('1@qq.com','充值','1000','1400939126'), ('2@qq.com','消费','500','1400939126'), ('1@qq.com','消费','100','1401686112'), ('1@qq.com','充值','100','1401686385');
INSERT INTO `user` VALUES ('1@qq.com','张三','000000','111111','customer','女','12345678912','北京市西土城路十号','3','92116','抠脚大汉123','1','正常	'), ('2@qq.com','李四','000000','111111','customer','男','12345678912','北京市西土城路十号','4','1011','伸手党','2','正常'), ('3@qq.com','李华','000000',' ','admin','男','123123123','北京市中南海',NULL,'6004','大叔',NULL,NULL), ('4@qq.com','李华','000000',' ','coach','男','123123123','北京市中南海','4','12','大叔',NULL,NULL);
INSERT INTO `usercourse` VALUES ('1@qq.com','1'), ('1@qq.com','2');
