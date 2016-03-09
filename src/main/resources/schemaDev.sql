CREATE DATABASE  IF NOT EXISTS `courses` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `courses`;

DROP TABLE IF EXISTS `courses`;
DROP TABLE IF EXISTS `teachers`;

CREATE TABLE `teachers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lastName1` varchar(45) NOT NULL,
  `lastName2` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `courses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `hours` int(11) NOT NULL,
  `teacherId` int(11) NOT NULL,  
  `level` varchar(45) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teacherId_FK_idx` (`teacherId`),
  CONSTRAINT `teacher` FOREIGN KEY (`teacherId`) REFERENCES `teachers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `teachers` VALUES (1,'Luis','Bermudez','Rodriguez'),
							  (2,'Roberto','Canales','Mora');

INSERT INTO `courses` VALUES (1,'Angular2',25,1,'AVANZADO',1),
							 (2,'JAVA 8',50,2,'AVANZADO',1),
							 (3,'Spring Boot',50,1,'AVANZADO',1),
							 (4,'Jpa',50,2,'INTERMEDIO',1),
							 (5,'SQL',25,1,'BASICO',1),
							 (6,'UML',50,2,'INTERMEDIO',1),
							 (7,'POO',50,1,'AVANZADO',1),
							 (8,'Scrum',50,2,'INTERMEDIO',1),
							 (9,'COBOL',25,1,'BASICO',1),
							 (10,'ETL',50,2,'INTERMEDIO',1),
							 (11,'SPECTRUM BASIC',50,1,'AVANZADO',1),
							 (12,'RUP',50,2,'INTERMEDIO',1);
							 
