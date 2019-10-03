-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: db-godinner.cpmvqfvc7pth.sa-east-1.rds.amazonaws.com    Database: db_godinner
-- ------------------------------------------------------
-- Server version	5.7.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `tbl_avaliacao_usuario`
--

DROP TABLE IF EXISTS `tbl_avaliacao_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_avaliacao_usuario` (
  `id_avaliacao_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `id_restaurante` int(11) NOT NULL,
  `id_consumidor` int(11) NOT NULL,
  `comentario` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id_avaliacao_usuario`),
  KEY `fk_comentario_restaurante_idx` (`id_restaurante`),
  KEY `fk_comentario_consumidor_idx` (`id_consumidor`),
  CONSTRAINT `fk_comentario_consumidor` FOREIGN KEY (`id_consumidor`) REFERENCES `tbl_consumidor` (`id_consumidor`),
  CONSTRAINT `fk_comentario_restaurante` FOREIGN KEY (`id_restaurante`) REFERENCES `tbl_restaurante` (`id_restaurante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_avaliacao_usuario`
--

LOCK TABLES `tbl_avaliacao_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_avaliacao_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_avaliacao_usuario` ENABLE KEYS */;
UNLOCK TABLES;
