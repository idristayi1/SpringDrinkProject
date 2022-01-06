drop table if exists drink cascade;

CREATE TABLE `drink` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
   `make` varchar(255) DEFAULT NULL,
  `volume` int DEFAULT NULL,
  `taste` varchar(255) DEFAULT NULL,
  `colour` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
 );

