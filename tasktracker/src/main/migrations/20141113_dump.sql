CREATE DATABASE  IF NOT EXISTS `task_tracker` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `task_tracker`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: task_tracker
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `workflow_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_c_workflow_id_idx` (`workflow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Project','2014-10-17 08:38:10','2014-10-17 08:38:10',1),(2,'Task','2014-10-17 08:38:10','2014-10-17 08:38:10',2),(3,'Bug','2014-10-17 08:38:10','2014-10-17 08:38:10',3);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment` text,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_task_id_idx` (`task_id`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fk_task_c_id` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_c_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin','2014-10-30 23:39:01','2014-10-30 23:39:01'),(2,'project manager','2014-10-30 23:39:01','2014-10-30 23:39:01'),(3,'analyst','2014-10-30 23:39:01','2014-10-30 23:39:01'),(4,'developer','2014-10-30 23:39:01','2014-10-30 23:39:01'),(5,'tester','2014-10-30 23:39:01','2014-10-30 23:39:01');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statuses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statuses`
--

LOCK TABLES `statuses` WRITE;
/*!40000 ALTER TABLE `statuses` DISABLE KEYS */;
INSERT INTO `statuses` VALUES (1,'New','2014-10-17 19:56:01','2014-10-17 19:56:01'),(2,'In progress','2014-10-17 19:56:01','2014-10-17 19:56:01'),(3,'Opened','2014-10-17 19:56:01','2014-10-17 19:56:01'),(4,'Closed','2014-10-17 19:56:01','2014-10-17 19:56:01'),(5,'Finished','2014-10-17 19:56:01','2014-10-17 19:56:01'),(6,'Feedback','2014-10-17 19:56:01','2014-10-17 19:56:01'),(7,'Check','2014-10-17 19:56:01','2014-10-17 19:56:01'),(8,'Fixed','2014-10-17 19:56:01','2014-10-17 19:56:01');
/*!40000 ALTER TABLE `statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` text,
  `parent_id` int(11) DEFAULT '0',
  `status_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `deadline` datetime DEFAULT NULL,
  `creator_id` int(11) NOT NULL,
  `assignee_id` int(11) NOT NULL,
  `workflow_id` int(11) NOT NULL,
  `progress` int(11) DEFAULT '0',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_status_id_idx` (`status_id`),
  KEY `fk_category_id_idx` (`category_id`),
  KEY `fk_creator_id_idx` (`creator_id`),
  KEY `fk_assignee_id_idx` (`assignee_id`),
  KEY `fk_workflow_id_idx` (`workflow_id`),
  CONSTRAINT `fk_assignee_id` FOREIGN KEY (`assignee_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_creator_id` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_status_id` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_workflow_id` FOREIGN KEY (`workflow_id`) REFERENCES `workflows` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=231 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'root','root','System','Admin','doctorrokter@gmail.com',NULL,'2014-10-17 20:06:27','2014-10-17 20:06:27'),(2,'test','test','Test','User',NULL,NULL,'2014-10-18 10:07:18','2014-10-18 10:07:18'),(3,'doctorrokter','root','Mikhail','Chachkouski','doctorrokter@gmail.com','375259092555','2014-10-31 00:13:27','2014-10-31 00:13:27'),(11,'metalhead','p@ssw0rd','Mike','Oldfield','doctorrokter@gmail.com','375259092555','2014-11-07 18:57:45','2014-11-07 18:57:45');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `fk_users_roles_1_idx` (`user_id`),
  KEY `fk_users_roles_2_idx` (`role_id`),
  CONSTRAINT `fk_users_roles_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_roles_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (2,5),(3,4),(1,1),(11,2),(11,3);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflows`
--

DROP TABLE IF EXISTS `workflows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workflows` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflows`
--

LOCK TABLES `workflows` WRITE;
/*!40000 ALTER TABLE `workflows` DISABLE KEYS */;
INSERT INTO `workflows` VALUES (1,'Project workflow','2014-10-17 08:49:52','2014-10-17 08:49:52'),(2,'Task workflow','2014-10-17 08:49:52','2014-10-17 08:49:52'),(3,'Bug workflow','2014-10-17 08:49:52','2014-10-17 08:49:52');
/*!40000 ALTER TABLE `workflows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflows_statuses`
--

DROP TABLE IF EXISTS `workflows_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workflows_statuses` (
  `workflow_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  KEY `fk_workflow_w_id_idx` (`workflow_id`),
  KEY `fk_status_w_id_idx` (`status_id`),
  CONSTRAINT `fk_status_w_id` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_workflow_w_id` FOREIGN KEY (`workflow_id`) REFERENCES `workflows` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflows_statuses`
--

LOCK TABLES `workflows_statuses` WRITE;
/*!40000 ALTER TABLE `workflows_statuses` DISABLE KEYS */;
INSERT INTO `workflows_statuses` VALUES (1,2),(1,3),(1,4),(2,1),(2,2),(2,5),(2,6),(2,7),(3,1),(3,2),(3,8),(3,6),(3,7);
/*!40000 ALTER TABLE `workflows_statuses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-13  8:33:21
