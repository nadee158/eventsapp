-- MySQL dump 10.13  Distrib 5.5.28, for Win64 (x86)
--
-- Host: localhost    Database: sportsman_db
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `category_setup`
--

DROP TABLE IF EXISTS `category_setup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_setup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_user` varchar(255) NOT NULL,
  `creation_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `category_setup_name` varchar(255) NOT NULL,
  `from_age` int(11) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `grade_or_belt` varchar(255) DEFAULT NULL,
  `to_age` int(11) DEFAULT NULL,
  `event_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_setup_id_index` (`uuid`),
  UNIQUE KEY `category_setup_name_status_index` (`category_setup_name`,`record_status`),
  KEY `FK34j9bpmbjpe763addvtn0ibqv` (`event_id`),
  CONSTRAINT `FK34j9bpmbjpe763addvtn0ibqv` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_setup`
--

LOCK TABLES `category_setup` WRITE;
/*!40000 ALTER TABLE `category_setup` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_setup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_setup_aud`
--

DROP TABLE IF EXISTS `category_setup_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_setup_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `category_setup_name` varchar(255) DEFAULT NULL,
  `from_age` int(11) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `grade_or_belt` varchar(255) DEFAULT NULL,
  `to_age` int(11) DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKpog541ik0cvf5p4wvaej102cr` (`rev`),
  CONSTRAINT `FKpog541ik0cvf5p4wvaej102cr` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_setup_aud`
--

LOCK TABLES `category_setup_aud` WRITE;
/*!40000 ALTER TABLE `category_setup_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_setup_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_setup_category_setup_items`
--

DROP TABLE IF EXISTS `category_setup_category_setup_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_setup_category_setup_items` (
  `category_setup_id` bigint(20) NOT NULL,
  `category_setup_items_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_717h85yjjp0yp7o5gej1lai61` (`category_setup_items_id`),
  KEY `FKgkqbedqmcmjw8knyqse6ir7rp` (`category_setup_id`),
  CONSTRAINT `FKgkqbedqmcmjw8knyqse6ir7rp` FOREIGN KEY (`category_setup_id`) REFERENCES `category_setup` (`id`),
  CONSTRAINT `FKchinw66exp8ks3a6cuqtbtlui` FOREIGN KEY (`category_setup_items_id`) REFERENCES `category_setup_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_setup_category_setup_items`
--

LOCK TABLES `category_setup_category_setup_items` WRITE;
/*!40000 ALTER TABLE `category_setup_category_setup_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_setup_category_setup_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_setup_category_setup_items_aud`
--

DROP TABLE IF EXISTS `category_setup_category_setup_items_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_setup_category_setup_items_aud` (
  `rev` int(11) NOT NULL,
  `category_setup_id` bigint(20) NOT NULL,
  `category_setup_items_id` bigint(20) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`rev`,`category_setup_id`,`category_setup_items_id`),
  CONSTRAINT `FKvsmo1jwa11okk8b36f8vkq9l` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_setup_category_setup_items_aud`
--

LOCK TABLES `category_setup_category_setup_items_aud` WRITE;
/*!40000 ALTER TABLE `category_setup_category_setup_items_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_setup_category_setup_items_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_setup_item`
--

DROP TABLE IF EXISTS `category_setup_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_setup_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_user` varchar(255) NOT NULL,
  `creation_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `category_setup_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_setup_item_id_index` (`uuid`),
  KEY `FKl8pkc7tdpax1xi32bsgjembgk` (`category_setup_id`),
  CONSTRAINT `FKl8pkc7tdpax1xi32bsgjembgk` FOREIGN KEY (`category_setup_id`) REFERENCES `category_setup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_setup_item`
--

LOCK TABLES `category_setup_item` WRITE;
/*!40000 ALTER TABLE `category_setup_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_setup_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_setup_item_aud`
--

DROP TABLE IF EXISTS `category_setup_item_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_setup_item_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `category_setup_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKpn3pi67eek4lky3urg0gxkmr6` (`rev`),
  CONSTRAINT `FKpn3pi67eek4lky3urg0gxkmr6` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_setup_item_aud`
--

LOCK TABLES `category_setup_item_aud` WRITE;
/*!40000 ALTER TABLE `category_setup_item_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_setup_item_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_notification`
--

DROP TABLE IF EXISTS `email_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_user` varchar(255) NOT NULL,
  `creation_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `body_text` varchar(5000) DEFAULT NULL,
  `email_subject` varchar(255) DEFAULT NULL,
  `email_type` varchar(255) DEFAULT NULL,
  `exception` varchar(255) DEFAULT NULL,
  `exception_messsage` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `recipient_address` varchar(255) DEFAULT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `retry_count` int(11) DEFAULT NULL,
  `sent_status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_notification`
--

LOCK TABLES `email_notification` WRITE;
/*!40000 ALTER TABLE `email_notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_notification_aud`
--

DROP TABLE IF EXISTS `email_notification_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_notification_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `body_text` varchar(5000) DEFAULT NULL,
  `email_subject` varchar(255) DEFAULT NULL,
  `email_type` varchar(255) DEFAULT NULL,
  `exception` varchar(255) DEFAULT NULL,
  `exception_messsage` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `recipient_address` varchar(255) DEFAULT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `retry_count` int(11) DEFAULT NULL,
  `sent_status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKcu5q8pqc4vsjx5u8tryydt5b6` (`rev`),
  CONSTRAINT `FKcu5q8pqc4vsjx5u8tryydt5b6` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_notification_aud`
--

LOCK TABLES `email_notification_aud` WRITE;
/*!40000 ALTER TABLE `email_notification_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_notification_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_user` varchar(255) NOT NULL,
  `creation_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `days_of_game` int(11) DEFAULT NULL,
  `event_date` datetime DEFAULT NULL,
  `event_name` varchar(255) NOT NULL,
  `event_venue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `event_id_index` (`uuid`),
  UNIQUE KEY `event_name_status_index` (`event_name`,`record_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_aud`
--

DROP TABLE IF EXISTS `event_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `days_of_game` int(11) DEFAULT NULL,
  `event_date` datetime DEFAULT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `event_venue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FK76r0s14ewob41mu1pe3qdbdke` (`rev`),
  CONSTRAINT `FK76r0s14ewob41mu1pe3qdbdke` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_aud`
--

LOCK TABLES `event_aud` WRITE;
/*!40000 ALTER TABLE `event_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_user` varchar(255) NOT NULL,
  `creation_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `nic` varchar(12) NOT NULL,
  `profile_image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `person_nic_index` (`nic`),
  UNIQUE KEY `person_person_id_index` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'UNKNOWN','2016-12-13 14:11:26','2016-12-13 14:11:26','UNKNOWN','INSERT','ACTIVE',1481618486243,'b85850cc-1b06-4701-ad31-922a3d123693',0,'Test Address','0712186182',NULL,'janakawanigatunga82@gmail.com','Super user Testing','812265481v','');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_aud`
--

DROP TABLE IF EXISTS `person_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `nic` varchar(255) DEFAULT NULL,
  `profile_image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FK9lyxk62ui3cyr5k0w8etnfqkm` (`rev`),
  CONSTRAINT `FK9lyxk62ui3cyr5k0w8etnfqkm` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_aud`
--

LOCK TABLES `person_aud` WRITE;
/*!40000 ALTER TABLE `person_aud` DISABLE KEYS */;
INSERT INTO `person_aud` VALUES (1,4,0,'UNKNOWN','2016-12-13 14:11:26','2016-12-13 14:11:26','UNKNOWN','INSERT','ACTIVE',1481618486243,'b85850cc-1b06-4701-ad31-922a3d123693','Test Address','0712186182',NULL,'janakawanigatunga82@gmail.com','Super user Testing','812265481v','');
/*!40000 ALTER TABLE `person_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_user` varchar(255) NOT NULL,
  `creation_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `player_number` varchar(255) NOT NULL,
  `team` varchar(255) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `category_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `player_number_index` (`player_number`),
  UNIQUE KEY `player_id_index` (`uuid`),
  KEY `FKpreff6q0dleix1demn00lflbe` (`category_id`),
  KEY `FK4pnw3ft3e4736nejqm2ubee43` (`person_id`),
  CONSTRAINT `FK4pnw3ft3e4736nejqm2ubee43` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKpreff6q0dleix1demn00lflbe` FOREIGN KEY (`category_id`) REFERENCES `category_setup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_aud`
--

DROP TABLE IF EXISTS `player_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `player_number` varchar(255) DEFAULT NULL,
  `team` varchar(255) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKi612tyyv8wic13yiooef4k8tp` (`rev`),
  CONSTRAINT `FKi612tyyv8wic13yiooef4k8tp` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_aud`
--

LOCK TABLES `player_aud` WRITE;
/*!40000 ALTER TABLE `player_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revinfo`
--

DROP TABLE IF EXISTS `revinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `revinfo` (
  `rev` int(11) NOT NULL AUTO_INCREMENT,
  `revtstmp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rev`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revinfo`
--

LOCK TABLES `revinfo` WRITE;
/*!40000 ALTER TABLE `revinfo` DISABLE KEYS */;
INSERT INTO `revinfo` VALUES (1,1481618103393),(2,1481618142321),(3,1481618309575),(4,1481618486415);
/*!40000 ALTER TABLE `revinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_user`
--

DROP TABLE IF EXISTS `security_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_user` varchar(255) NOT NULL,
  `creation_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `account_enabled` bit(1) NOT NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `credentials_expired` bit(1) NOT NULL,
  `secret` varchar(120) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `security_user_user_name_index` (`user_name`),
  UNIQUE KEY `security_user_security_user_id_index` (`uuid`),
  KEY `FK7rhvd4xg8fivvvlum12sup5u5` (`person_id`),
  CONSTRAINT `FK7rhvd4xg8fivvvlum12sup5u5` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user`
--

LOCK TABLES `security_user` WRITE;
/*!40000 ALTER TABLE `security_user` DISABLE KEYS */;
INSERT INTO `security_user` VALUES (1,'UNKNOWN','2016-12-13 14:11:26','2016-12-13 14:11:26','UNKNOWN','INSERT','ACTIVE',1481618486237,'ea3b2a69-49f8-43fa-af7a-b54339fa8f26',0,'','\0','\0','\0','$2a$10$MYTM3HaOFY.7lo90cccdvuLS3ZyjOszAM8U9Jdty9zwKfVZk4jxvq','sportsman_admin',1);
/*!40000 ALTER TABLE `security_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_user_aud`
--

DROP TABLE IF EXISTS `security_user_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `account_enabled` bit(1) DEFAULT NULL,
  `account_expired` bit(1) DEFAULT NULL,
  `account_locked` bit(1) DEFAULT NULL,
  `credentials_expired` bit(1) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKeaq5e4xj4okp22siahat91cb8` (`rev`),
  CONSTRAINT `FKeaq5e4xj4okp22siahat91cb8` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user_aud`
--

LOCK TABLES `security_user_aud` WRITE;
/*!40000 ALTER TABLE `security_user_aud` DISABLE KEYS */;
INSERT INTO `security_user_aud` VALUES (1,4,0,'UNKNOWN','2016-12-13 14:11:26','2016-12-13 14:11:26','UNKNOWN','INSERT','ACTIVE',1481618486237,'ea3b2a69-49f8-43fa-af7a-b54339fa8f26','','\0','\0','\0','$2a$10$MYTM3HaOFY.7lo90cccdvuLS3ZyjOszAM8U9Jdty9zwKfVZk4jxvq','sportsman_admin',1);
/*!40000 ALTER TABLE `security_user_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_user_user_role`
--

DROP TABLE IF EXISTS `security_user_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user_user_role` (
  `security_user_id` bigint(20) NOT NULL,
  `user_role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`security_user_id`,`user_role_id`),
  KEY `FKngn1rlwpitvphss6s3xh1tfib` (`user_role_id`),
  CONSTRAINT `FKnfw5uf6pocd7ccguotjlnup2` FOREIGN KEY (`security_user_id`) REFERENCES `security_user` (`id`),
  CONSTRAINT `FKngn1rlwpitvphss6s3xh1tfib` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user_user_role`
--

LOCK TABLES `security_user_user_role` WRITE;
/*!40000 ALTER TABLE `security_user_user_role` DISABLE KEYS */;
INSERT INTO `security_user_user_role` VALUES (1,1);
/*!40000 ALTER TABLE `security_user_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_user_user_role_aud`
--

DROP TABLE IF EXISTS `security_user_user_role_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user_user_role_aud` (
  `rev` int(11) NOT NULL,
  `security_user_id` bigint(20) NOT NULL,
  `user_role_id` bigint(20) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`rev`,`security_user_id`,`user_role_id`),
  CONSTRAINT `FKlwoiai0y2pyx7y54sih0w93er` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user_user_role_aud`
--

LOCK TABLES `security_user_user_role_aud` WRITE;
/*!40000 ALTER TABLE `security_user_user_role_aud` DISABLE KEYS */;
INSERT INTO `security_user_user_role_aud` VALUES (4,1,1,0);
/*!40000 ALTER TABLE `security_user_user_role_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence_number`
--

DROP TABLE IF EXISTS `sequence_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence_number` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_user` varchar(255) NOT NULL,
  `creation_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `current_sequence_number` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `number_length` int(11) NOT NULL,
  `prefix_value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sequence_number_id_index` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence_number`
--

LOCK TABLES `sequence_number` WRITE;
/*!40000 ALTER TABLE `sequence_number` DISABLE KEYS */;
INSERT INTO `sequence_number` VALUES (1,'UNKNOWN','2016-12-13 14:05:03','2016-12-13 14:05:03','UNKNOWN','UPDATE','ACTIVE',1481618142275,'e7552429-a731-4f21-8689-8c7eeb135c13',0,0,'Player Number Sequence',5,'PL/YEAR/');
/*!40000 ALTER TABLE `sequence_number` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence_number_aud`
--

DROP TABLE IF EXISTS `sequence_number_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence_number_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `current_sequence_number` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `number_length` int(11) DEFAULT NULL,
  `prefix_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FK1fr9pe6o6tlinb3r4212ixcge` (`rev`),
  CONSTRAINT `FK1fr9pe6o6tlinb3r4212ixcge` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence_number_aud`
--

LOCK TABLES `sequence_number_aud` WRITE;
/*!40000 ALTER TABLE `sequence_number_aud` DISABLE KEYS */;
INSERT INTO `sequence_number_aud` VALUES (1,1,0,'UNKNOWN','2016-12-13 14:05:03','2016-12-13 14:05:03','UNKNOWN','INSERT','ACTIVE',1481618103294,'e7552429-a731-4f21-8689-8c7eeb135c13',0,'Player Number Sequence',5,'PL/YEAR/'),(1,2,1,'UNKNOWN','2016-12-13 14:05:03','2016-12-13 14:05:03','UNKNOWN','UPDATE','ACTIVE',1481618142275,'e7552429-a731-4f21-8689-8c7eeb135c13',1,'Player Number Sequence',5,'PL/YEAR/');
/*!40000 ALTER TABLE `sequence_number_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_notification`
--

DROP TABLE IF EXISTS `sms_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_user` varchar(255) NOT NULL,
  `creation_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `exception` varchar(255) DEFAULT NULL,
  `exception_messsage` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `recipient_address` varchar(255) DEFAULT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `retry_count` int(11) DEFAULT NULL,
  `sent_status` varchar(255) DEFAULT NULL,
  `sms_type` varchar(255) DEFAULT NULL,
  `text` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_notification`
--

LOCK TABLES `sms_notification` WRITE;
/*!40000 ALTER TABLE `sms_notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_notification_aud`
--

DROP TABLE IF EXISTS `sms_notification_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_notification_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `exception` varchar(255) DEFAULT NULL,
  `exception_messsage` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `recipient_address` varchar(255) DEFAULT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `retry_count` int(11) DEFAULT NULL,
  `sent_status` varchar(255) DEFAULT NULL,
  `sms_type` varchar(255) DEFAULT NULL,
  `text` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKlk7otqwg8wyxhvqtqgi4snjip` (`rev`),
  CONSTRAINT `FKlk7otqwg8wyxhvqtqgi4snjip` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_notification_aud`
--

LOCK TABLES `sms_notification_aud` WRITE;
/*!40000 ALTER TABLE `sms_notification_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_notification_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_user` varchar(255) NOT NULL,
  `creation_time` datetime NOT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `user_role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_name_index` (`user_role_name`),
  UNIQUE KEY `user_role_id_index` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'UNKNOWN','2016-12-13 14:08:29','2016-12-13 14:08:29','UNKNOWN','INSERT','ACTIVE',1481618309523,'de7f1aa4-1652-4c2f-9340-609f025c938c',0,'ROLE_SUPER_ADMIN');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_aud`
--

DROP TABLE IF EXISTS `user_role_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `record_status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `user_role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FK2ax4xks5sy1yh2a2gxdndkcmc` (`rev`),
  CONSTRAINT `FK2ax4xks5sy1yh2a2gxdndkcmc` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_aud`
--

LOCK TABLES `user_role_aud` WRITE;
/*!40000 ALTER TABLE `user_role_aud` DISABLE KEYS */;
INSERT INTO `user_role_aud` VALUES (1,3,0,'UNKNOWN','2016-12-13 14:08:29','2016-12-13 14:08:29','UNKNOWN','INSERT','ACTIVE',1481618309523,'de7f1aa4-1652-4c2f-9340-609f025c938c','ROLE_SUPER_ADMIN');
/*!40000 ALTER TABLE `user_role_aud` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-13 15:06:49
