-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: cardmanager
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.13-MariaDB

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
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `CardID` int(11) NOT NULL AUTO_INCREMENT,
  `CardColor` varchar(126) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardName` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardMana` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardTyp` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardAttack` int(11) DEFAULT NULL,
  `CardDefense` int(11) DEFAULT NULL,
  `CardText` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardFlavorText` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardArtist` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EdiID` int(11) NOT NULL,
  PRIMARY KEY (`CardID`),
  KEY `EdiID` (`EdiID`),
  CONSTRAINT `card_ibfk_1` FOREIGN KEY (`EdiID`) REFERENCES `edition` (`EdiID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_to_deck`
--

DROP TABLE IF EXISTS `card_to_deck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card_to_deck` (
  `DeckCardID` int(11) NOT NULL AUTO_INCREMENT,
  `DeckID` int(11) NOT NULL,
  `CardID` int(11) NOT NULL,
  PRIMARY KEY (`DeckCardID`),
  KEY `DeckID` (`DeckID`),
  KEY `CardID` (`CardID`),
  CONSTRAINT `card_to_deck_ibfk_1` FOREIGN KEY (`DeckID`) REFERENCES `deck` (`DeckID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `card_to_deck_ibfk_2` FOREIGN KEY (`CardID`) REFERENCES `card` (`CardID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_to_deck`
--

LOCK TABLES `card_to_deck` WRITE;
/*!40000 ALTER TABLE `card_to_deck` DISABLE KEYS */;
/*!40000 ALTER TABLE `card_to_deck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deck`
--

DROP TABLE IF EXISTS `deck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deck` (
  `DeckID` int(11) NOT NULL AUTO_INCREMENT,
  `DeckName` varchar(126) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DeckFormat` varchar(126) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`DeckID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deck`
--

LOCK TABLES `deck` WRITE;
/*!40000 ALTER TABLE `deck` DISABLE KEYS */;
/*!40000 ALTER TABLE `deck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edition`
--

DROP TABLE IF EXISTS `edition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edition` (
  `EdiID` int(11) NOT NULL AUTO_INCREMENT,
  `EdiName` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`EdiID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edition`
--

LOCK TABLES `edition` WRITE;
/*!40000 ALTER TABLE `edition` DISABLE KEYS */;
INSERT INTO `edition` VALUES (1,'Unknown'),(2,'ISD'),(3,'DKA'),(4,'AVR'),(5,'RTR'),(6,'GTC'),(7,'DGM'),(8,'THS'),(9,'BNG'),(10,'JOU'),(11,'KTK'),(12,'FRF'),(13,'DTK'),(14,'BFZ'),(15,'OGW'),(16,'SOI'),(17,'EMN'),(18,'KLD'),(19,'AER'),(20,'AKH'),(21,'HOU');
/*!40000 ALTER TABLE `edition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `help`
--

DROP TABLE IF EXISTS `help`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `help` (
  `HelpID` int(11) NOT NULL AUTO_INCREMENT,
  `HelpText` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`HelpID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `help`
--

LOCK TABLES `help` WRITE;
/*!40000 ALTER TABLE `help` DISABLE KEYS */;
INSERT INTO `help` VALUES (1,'Dieses Programm ist selbsterklärent ;)'),(2,'Dieses Programm ist selbsterklärent ;)');
/*!40000 ALTER TABLE `help` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-04 20:04:08
