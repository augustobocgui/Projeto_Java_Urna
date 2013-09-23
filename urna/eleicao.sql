# Host: localhost  (Version: 5.6.13-log)
# Date: 2013-09-21 23:16:21
# Generator: MySQL-Front 5.3  (Build 4.13)

/*!40101 SET NAMES utf8 */;

#
# Source for table "apuracao"
#

DROP TABLE IF EXISTS `apuracao`;
CREATE TABLE `apuracao` (
  `id` int(11) NOT NULL,
  `contagem` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "apuracao"
#

INSERT INTO `apuracao` VALUES (0,0),(1,0),(12179,0),(23962,0),(25157,0),(25255,0),(43123,0);

#
# Source for table "candidato"
#

DROP TABLE IF EXISTS `candidato`;
CREATE TABLE `candidato` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nome` varchar(255) NOT NULL,
  `partido` varchar(255) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "candidato"
#

INSERT INTO `candidato` VALUES (0,'VOTOS EM BRANCO','-','nulo.jpg'),(1,'VOTOS NULOS','-','nulo.jpg'),(12179,'Andresa Noronha','BRD','andressa.jpg'),(23962,'Murilo Pinheiro','BRA','murilo.jpg'),(25157,'José Weslley','BRC','weslao.jpg'),(25255,'Flavio Telles','BRB','flavio.jpg'),(43123,'Antonio Machado','BRE','antonio.jpg');
