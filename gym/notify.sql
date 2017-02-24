/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : gym

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2014-05-27 00:30:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `notify`
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
-- Records of notify
-- ----------------------------
INSERT INTO `notify` VALUES ('1', 'hehe', '欢迎刘轩大叔加入我们的俱乐部', '1401119606', 'all');
INSERT INTO `notify` VALUES ('2', '新开课程', '健美操是一种有氧运动，通常采用徒手或持轻器械进行练习，在氧供应充足的情况下，是人体有氧系统提供能量的一种运动形式。其运动特征是持续一定时间的、中低程度的全身性运动，主要锻炼练习者的心肺功能，是有氧耐力素质的基础。跳健美操有诸多好处，不仅能帮助我们有效的强身健体，而且还有减肥的功效，这种运动减肥方法集健美和健身于一体，特别适合女性，受到了广大女性同胞的喜爱。', '1401119606', 'all');
INSERT INTO `notify` VALUES ('3', '雷霆', '北京时间2014年5月26日，雷霆在主场以106-97战胜马刺，将系列赛大比分扳为1-2，之前被报道为赛季报销的伊巴卡本场火线复出，全场7中6砍下15分，7篮板和4盖帽。', '1401119606', 'customer');
INSERT INTO `notify` VALUES ('4', '波波维奇：马努可能没事也可能赛季报销', '北京时间5月26日，马刺队在今天的西部决赛第三场比赛中97-106输给了雷霆队，此役过后，马刺队在本轮系列赛中2比1领先对手。', '1401121108', 'customer');
INSERT INTO `notify` VALUES ('5', '帕金斯：雷吉一旦投入，无后卫能面对他得分', '北京时间5月26日，雷霆队在今天的西部决赛第三场比赛中106-97击败了马刺队，本场比赛过后，雷霆队将总比分改写为1比2。', '1401121108', 'coach');
INSERT INTO `notify` VALUES ('6', '韦德向萨姆-卡塞尔学投篮假动作', '北京时间5月26日，热火当家后卫德维恩-韦德在接受采访的时候透露自己很喜欢“外星人”萨姆-卡塞尔的打球方式，他的假动作就是模仿卡塞尔的。\r\n\r\n“我一直都在谈论卡塞尔。”韦德今天说到，“我喜欢他在场上像老人一样的打球方式，当他在密尔沃基的时候我就很仔细地研究过他，他会在场上逼疯你，节奏很慢，就像是老人一样。', '1401121108', 'coach');
INSERT INTO `notify` VALUES ('7', '伊巴卡感觉自己能打西决第四场', '北京时间5月26日，雷霆队在今天的西部决赛第三场比赛中以106-97击败了马刺队，本场比赛过后，雷霆队将总比分改写为1比2。\r\n\r\n此役，雷霆队的赛尔吉-伊巴卡迎来复出，最终贡献了15分7个篮板4个盖帽。\r\n\r\n赛后伊巴卡透露，他感觉他应该能打接下来的第四场比赛，但是他也预期自己会感到疼痛。', '1401121108', 'admin');
INSERT INTO `notify` VALUES ('8', '参议院全票通过，布拉奇获菲律宾国籍', '菲律宾当地时间5月26日消息，菲律宾参议院今天就安德雷-布拉奇申请菲律宾国籍一事进行最终投票，投票结果为：20票通过，0票反对，0票弃权。\r\n\r\n这样的结果意味着布拉奇顺利地获得了菲律宾国籍，他将代表菲律宾参加男篮世界杯。', '1401121108', 'admin');
