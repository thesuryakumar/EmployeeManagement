CREATE DATABASE  IF NOT EXISTS `empmanagement` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `empmanagement`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: empmanagement
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'sk@sk.com','sk','ADMIN'),(2,'sk1@sk.com','sk1','ADMIN'),(3,'sk4@sk.com','surya','EMPLOYEE'),(4,'sk3@sk.com','sk3','EMPLOYEE'),(6,'dk@dk.com','darshan','EMPLOYEE'),(7,'dk1@dk.com','dk','ADMIN');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed_back`
--

DROP TABLE IF EXISTS `feed_back`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feed_back` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `feedback` varchar(255) DEFAULT NULL,
  `rating` double NOT NULL,
  `review_id` int DEFAULT NULL,
  `reviwer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtcft7bmhd62ew2dj5n7tst1yx` (`review_id`),
  KEY `FKpjpkkmqaemv0arqmjbx7ad6e1` (`reviwer_id`),
  CONSTRAINT `FKpjpkkmqaemv0arqmjbx7ad6e1` FOREIGN KEY (`reviwer_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKtcft7bmhd62ew2dj5n7tst1yx` FOREIGN KEY (`review_id`) REFERENCES `performance_review` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_back`
--

LOCK TABLES `feed_back` WRITE;
/*!40000 ALTER TABLE `feed_back` DISABLE KEYS */;
INSERT INTO `feed_back` VALUES (1,'2025-12-03',NULL,4,1,2),(2,'2025-12-03','Good performance, can improve communication skills.',4,1,2),(3,'2025-12-03','Good performance, can improve communication skills.',4,1,2),(4,'2025-12-03','doing good\n',3,1,1),(5,'2025-12-03','good devleoper',4,4,4),(6,'2025-12-03','small improvements',1,5,1),(7,'2025-12-03','good performance',5,5,1),(8,'2025-12-04','ok',5,3,4),(9,'2025-12-04',NULL,5,5,2),(10,'2025-12-04','started the task',5,2,2),(11,'2025-12-04','going on with the project.',4,6,3),(12,'2025-12-04','still going on requirement analysis.',5,7,3);
/*!40000 ALTER TABLE `feed_back` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance_review`
--

DROP TABLE IF EXISTS `performance_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performance_review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `due_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9159yuocyhexftv11wmay20cg` (`employee_id`),
  CONSTRAINT `FK9159yuocyhexftv11wmay20cg` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance_review`
--

LOCK TABLES `performance_review` WRITE;
/*!40000 ALTER TABLE `performance_review` DISABLE KEYS */;
INSERT INTO `performance_review` VALUES (1,'2025-12-31','OPEN','Quarterly Review',1),(2,'2025-12-12','OPEN','task',1),(3,'2025-12-12','OPEN','task1',1),(4,'2025-12-10','OPEN','devloper',3),(5,'2025-12-10','OPEN','dev1',3),(6,'2025-12-10','OPEN','test',1),(7,'2025-12-10','OPEN','cyber test',1);
/*!40000 ALTER TABLE `performance_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `review_id` int DEFAULT NULL,
  `reviwer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKibx5c8vcfec0fx01ujb3tes02` (`review_id`),
  KEY `FKm1qfuxa6l82bknh2vv9meydr0` (`reviwer_id`),
  CONSTRAINT `FKibx5c8vcfec0fx01ujb3tes02` FOREIGN KEY (`review_id`) REFERENCES `performance_review` (`id`),
  CONSTRAINT `FKm1qfuxa6l82bknh2vv9meydr0` FOREIGN KEY (`reviwer_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'COMPLETED',1,1),(2,'COMPLETED',4,4),(3,'COMPLETED',5,1),(4,'COMPLETED',5,1),(5,'COMPLETED',6,3),(6,'COMPLETED',3,4),(7,'COMPLETED',5,2),(8,'COMPLETED',2,2),(9,'PENDING',7,2),(10,'COMPLETED',7,3);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-04  1:26:25
