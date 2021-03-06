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