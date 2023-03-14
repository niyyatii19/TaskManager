CREATE DATABASE `tododb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tododb`;


CREATE TABLE `user` (
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `tasks` (
  `id` int NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `text` varchar(45) DEFAULT NULL,
  `isComplete` tinyint DEFAULT NULL,
  `assignedTo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `assignedTo_idx` (`assignedTo`),
  CONSTRAINT `assignedTo` FOREIGN KEY (`assignedTo`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
