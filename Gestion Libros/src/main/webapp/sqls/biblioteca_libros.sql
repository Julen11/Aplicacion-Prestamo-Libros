-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `libros` (
  `idlibros` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `isbn` varchar(15) DEFAULT NULL,
  `fecha_prestamo` date DEFAULT NULL,
  `alumnos_idalumnos` int(11) DEFAULT NULL,
  `editoriales_ideditorial` int(11) DEFAULT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  PRIMARY KEY (`idlibros`),
  KEY `fk_libros_alumnos_idx` (`alumnos_idalumnos`),
  KEY `fk_libros_editoriales1_idx` (`editoriales_ideditorial`),
  CONSTRAINT `fk_libros_alumnos` FOREIGN KEY (`alumnos_idalumnos`) REFERENCES `alumnos` (`idalumno`),
  CONSTRAINT `fk_libros_editoriales1` FOREIGN KEY (`editoriales_ideditorial`) REFERENCES `editoriales` (`ideditorial`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (6,'HTML','HT-85845-45','2018-12-11',6,1,'2018-12-18'),(8,'JAVA','JA-84965712-35','2018-10-31',5,1,'2018-11-14'),(9,'HTML','HT-85845-45','2018-10-24',4,1,'2018-10-31'),(10,'JAVA','JA-84965712-35','2018-10-24',5,1,'2018-10-31'),(14,'el nombre del viento',NULL,'2018-10-30',5,8,'2018-10-30'),(22,'prueba final',NULL,'2018-10-31',5,NULL,'2018-11-07'),(23,'prueba para piero',NULL,'2018-10-31',4,NULL,'2018-11-21'),(24,'prueba definitiva',NULL,'2018-10-31',5,NULL,'2018-11-21'),(25,'eldiamante',NULL,'2018-10-31',6,NULL,'2018-11-21'),(26,'el iibro de txABER',NULL,NULL,NULL,11,NULL),(27,'PHP',NULL,NULL,NULL,13,NULL);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-31 12:56:15
