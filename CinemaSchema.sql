CREATE DATABASE  IF NOT EXISTS `cinema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `cinema`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `booking` (
  `bookingID` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `promoID` int(11) DEFAULT NULL,
  `ccNumber` varbinary(16) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `noOfTickets` int(255) DEFAULT NULL,
  PRIMARY KEY (`bookingID`),
  KEY `bUIDFK_idx` (`userID`),
  KEY `bPromoIDFK_idx` (`promoID`),
  KEY `ccNumberFK_idx` (`ccNumber`),
  CONSTRAINT `bPromoIDFK` FOREIGN KEY (`promoID`) REFERENCES `promotion` (`promoID`),
  CONSTRAINT `bUIDFK` FOREIGN KEY (`userID`) REFERENCES `users` (`UserID`),
  CONSTRAINT `ccNumberFK` FOREIGN KEY (`ccNumber`) REFERENCES `creditcard` (`ccNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `categoryID` int(11) NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='1= Action, 2= Sci Fi, 3= Horror, n4= Comedy, 5= Drama';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Action'),(2,'Sci-Fi'),(3,'Horror'),(4,'Comedy'),(5,'Drama');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cctype`
--

DROP TABLE IF EXISTS `cctype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cctype` (
  `ccTypeID` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ccTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='1= Visa, 2= MasterCard, 3= American Express';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cctype`
--

LOCK TABLES `cctype` WRITE;
/*!40000 ALTER TABLE `cctype` DISABLE KEYS */;
INSERT INTO `cctype` VALUES (1,'Visa'),(2,'MasterCard'),(3,'American Express'),(4,'Discover');
/*!40000 ALTER TABLE `cctype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `creditcard` (
  `ccNumber` varbinary(32) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `ccTypeID` int(11) DEFAULT NULL,
  `expDate` date DEFAULT NULL,
  `zipCode` varbinary(32) DEFAULT NULL,
  `ccName` varchar(45) DEFAULT NULL,
  `cvv` varbinary(32) DEFAULT NULL,
  PRIMARY KEY (`ccNumber`),
  KEY `ccTypeIDFK_idx` (`ccTypeID`),
  KEY `ccUIDFK_idx` (`userID`),
  CONSTRAINT `ccTypeIDFK` FOREIGN KEY (`ccTypeID`) REFERENCES `cctype` (`ccTypeID`),
  CONSTRAINT `ccUIDFK` FOREIGN KEY (`userID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (_binary 'õ\·\∆\Ô∞80_sC\⁄\"2òR?mSf3iHÚ\Ï:\√ÚY',9,NULL,'3921-03-01',_binary '\ÀD\ﬂj2&ßsF\◊|\Ê\Ô∂','TEST T EST',_binary 'ïm•äØÉ¸•r¶∏Qf%');
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie` (
  `movieID` int(11) NOT NULL AUTO_INCREMENT,
  `movieTitle` varchar(255) DEFAULT NULL,
  `categoryID` int(11) DEFAULT NULL,
  `cast` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `synopsis` varchar(255) DEFAULT NULL,
  `reviews` varchar(255) DEFAULT NULL,
  `trailer` varchar(255) DEFAULT 'https://www.youtube.com/embed/tgbNymZ7vqY',
  `ratingID` int(11) DEFAULT NULL,
  `length` varchar(45) DEFAULT NULL,
  `isShowing` enum('True','False') NOT NULL DEFAULT 'False',
  PRIMARY KEY (`movieID`),
  KEY `mCategoryIDFK_idx` (`categoryID`),
  KEY `mRatingIDFK_idx` (`ratingID`),
  CONSTRAINT `mCategoryIDFK` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`),
  CONSTRAINT `mRatingIDFK` FOREIGN KEY (`ratingID`) REFERENCES `rating` (`ratingID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'movie1',1,'movie1 cast of listy bois','movie1 director','synopsis','this is a bad movie','https://www.youtube.com/embed/tgbNymZ7vqY',1,'time is relative','True'),(2,'movie2',2,'movie1 cast of listy bois','movie1 director','synopsis','this is a bad movie','https://www.youtube.com/embed/tgbNymZ7vqY',3,'time is relative','True'),(3,'movie3',1,'movie1 cast of listy bois','movie1 director','synopsis','this is a bad movie','https://www.youtube.com/embed/tgbNymZ7vqY',2,'time is relative','False'),(4,'Endgame',1,'Robert Downy Jr.','The Russos','Revenge','https://www.rottentomatoes.com/m/avengers_endgame','https://www.youtube.com/embed/TcMBFSGVi1c',5,'277','False'),(6,'King of the World',5,'Drama','Drama','Drama','Drama','https://www.youtube.com/embed/tgbNymZ7vqY',7,'347','True'),(16,'Really Silly',2,'big cast','smol director','Synopsis','Reviews','https://www.youtube.com/embed/tgbNymZ7vqY',5,'2','True'),(17,'Pet Sematary',3,'Kyle Arnett','Kyle again','Pets kill people','https://www.rottentomatoes.com/m/pet_sematary_2019','https://www.youtube.com/embed/XakmsXltPkA',4,'103','True');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `promotion` (
  `promoID` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `percent` int(11) DEFAULT NULL,
  `expDate` date DEFAULT NULL,
  PRIMARY KEY (`promoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rating` (
  `ratingID` int(11) NOT NULL,
  `rating` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`ratingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='1= G, 2= PG, 3= PG-13, 4= R';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (1,'G'),(2,'PG'),(3,'PG-13'),(4,'R'),(5,'NC-17'),(6,'NR'),(7,'TBR');
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `seat` (
  `seatID` int(11) NOT NULL,
  `showID` int(11) unsigned DEFAULT NULL,
  `seatNumber` int(255) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`seatID`),
  KEY `tShowIDFK_idx` (`showID`),
  CONSTRAINT `showID` FOREIGN KEY (`showID`) REFERENCES `showtimes` (`showID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showstarts`
--

DROP TABLE IF EXISTS `showstarts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `showstarts` (
  `StartID` int(11) NOT NULL,
  `StartTime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`StartID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='1 = 1:45, 2= 4:45, 3= 7:45, 4= 11:15';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showstarts`
--

LOCK TABLES `showstarts` WRITE;
/*!40000 ALTER TABLE `showstarts` DISABLE KEYS */;
INSERT INTO `showstarts` VALUES (1,'1:45'),(2,'4:45'),(3,'7:45'),(4,'11:15');
/*!40000 ALTER TABLE `showstarts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showtimes`
--

DROP TABLE IF EXISTS `showtimes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `showtimes` (
  `showID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `movieID` int(11) DEFAULT NULL,
  `startID` int(11) DEFAULT NULL,
  `endTime` varchar(45) DEFAULT NULL,
  `date` date NOT NULL DEFAULT '2000-01-01',
  PRIMARY KEY (`showID`),
  KEY `stMovieIDFK_idx` (`movieID`),
  KEY `startID_idx` (`startID`),
  CONSTRAINT `stMovieIDFK` FOREIGN KEY (`movieID`) REFERENCES `movie` (`movieID`),
  CONSTRAINT `startID` FOREIGN KEY (`startID`) REFERENCES `showstarts` (`StartID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showtimes`
--

LOCK TABLES `showtimes` WRITE;
/*!40000 ALTER TABLE `showtimes` DISABLE KEYS */;
INSERT INTO `showtimes` VALUES (1,1,1,'3:00','2019-04-11'),(2,2,3,'9:00','2019-04-11'),(3,17,1,'3:28','2019-10-05'),(4,17,1,'3:28','2019-10-06'),(5,17,1,'3:28','2019-10-07');
/*!40000 ALTER TABLE `showtimes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ticket` (
  `ticketID` int(11) NOT NULL AUTO_INCREMENT,
  `bookingID` int(11) DEFAULT NULL,
  `seatID` int(11) DEFAULT NULL,
  `showID` int(11) unsigned DEFAULT NULL,
  `typeID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ticketID`),
  KEY `tBookingIDFK_idx` (`bookingID`),
  KEY `tSeatIDFK_idx` (`seatID`),
  KEY `tTypeIDFK_idx` (`typeID`),
  KEY `showID_idx` (`showID`),
  CONSTRAINT `tBookingIDFK` FOREIGN KEY (`bookingID`) REFERENCES `booking` (`bookingID`),
  CONSTRAINT `tSeatIDFK` FOREIGN KEY (`seatID`) REFERENCES `seat` (`seatID`),
  CONSTRAINT `tShowIDFK` FOREIGN KEY (`showID`) REFERENCES `showtimes` (`showID`),
  CONSTRAINT `tTypeIDFK` FOREIGN KEY (`typeID`) REFERENCES `ticketprice` (`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticketprice`
--

DROP TABLE IF EXISTS `ticketprice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ticketprice` (
  `typeID` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='1 = Child 4.00, 2= Adult 12.00 ticket, 3= Senior 8.00 ticket';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticketprice`
--

LOCK TABLES `ticketprice` WRITE;
/*!40000 ALTER TABLE `ticketprice` DISABLE KEYS */;
INSERT INTO `ticketprice` VALUES (1,'Child',4),(2,'Adult',12),(3,'Senior',8);
/*!40000 ALTER TABLE `ticketprice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varbinary(512) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `enrollForPromotions` enum('True','False') DEFAULT NULL,
  `userType` int(11) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(6) DEFAULT NULL,
  `statusID` int(11) DEFAULT NULL,
  `salt` varbinary(16) NOT NULL,
  `confirmationCode` varchar(45) DEFAULT NULL,
  `addressSuite` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  KEY `userTypeFK_idx` (`userType`),
  KEY `statusIDFK_idx` (`statusID`),
  CONSTRAINT `statusIDFK` FOREIGN KEY (`statusID`) REFERENCES `userstatus` (`statusID`),
  CONSTRAINT `userTypeFK` FOREIGN KEY (`userType`) REFERENCES `usertypes` (`UserTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'Brandon','Nix','brandonnix95@live.com',_binary '(√ß√Ñd√û¬ô¬µW√£H¬µ+¬ª√π#MO¬ú√©√∑¬ø=¬Æ¬∏√ünM¬îo$4¬≥c√†¬ç¬Ñ¬å√©W¬≠√Ωw√ë!@√°9¬ª¬ûFzR\rf√Ö¬Æ¬§¬¶¬¨','678-793-2385','ACTIVE','False',1,NULL,NULL,NULL,NULL,NULL,_binary 'P√ªB√õ?o¬≠$√ót','b1eeaaf7-dbd7-453c-9fc1-2213f91a527c',NULL),(4,'Brandon','Nix','brandonnix95@live.com',_binary 'f:_¬°A√Ç¬∞√Ø\r¬¶√ª.\0g¬î<`-K¬≤√Ç√ü8¬Å√ë¬ª√¶¬ù]√ëA]%√≥√±;¬µ¬ì√Ñ√ûi√î¬Ω¬Ñ¬°¬Ä8¬ú√ù[√à¬å','678-793-2385','ACTIVE','False',2,NULL,NULL,NULL,NULL,NULL,_binary '7√´¬ß6H¬ãZI¬Ç¬πP','ef1c2cdc-36f6-4d77-bd73-b7ed0bbc7d95',NULL),(5,'Alexander','Miller','awm92377@uga.edu',_binary 'ß<$®\÷%Lâ.åD∏ù\ƒ\‘N∞æ%R”ÇÖn’èQ¸=\À-…¶XΩly≤öa\\≥\‚ C˚\“K]Uò§\"FâCx','678-995-2006','ACTIVE','False',1,NULL,NULL,NULL,NULL,NULL,_binary 'âåÉùx:´˛ï®—±ÜwWç','29be29ff-cd5b-4372-b0fb-653cd55a5cf9',NULL),(9,'test','test','test@gmail.com',_binary 'C4ÒEAç\Íd&\\ùp\‘\Ÿ\…\ÿ%Qˆ	§\ÿSÚ$3æ\√!è.\Ï6\ÍéN≠Ù≥y!d#ûbµÖ\ÁfN4Qß\«D(\ƒó)\”o','5555555555','INACTIVE','False',2,'test st','test city','test state','33333',NULL,_binary '\‘ˆ2%-tÉã`&%~3_','cbe22613-9874-4148-9678-a5623f258151','test suite');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userstatus`
--

DROP TABLE IF EXISTS `userstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `userstatus` (
  `statusID` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`statusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='1 = Inactive, 2= Active, 3= Suspended';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userstatus`
--

LOCK TABLES `userstatus` WRITE;
/*!40000 ALTER TABLE `userstatus` DISABLE KEYS */;
INSERT INTO `userstatus` VALUES (1,'Inactive'),(2,'Active'),(3,'Suspended');
/*!40000 ALTER TABLE `userstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertypes`
--

DROP TABLE IF EXISTS `usertypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usertypes` (
  `UserTypeID` int(11) NOT NULL,
  `UserTypeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='1 = Admin, 2= Customer, 3= Employee';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertypes`
--

LOCK TABLES `usertypes` WRITE;
/*!40000 ALTER TABLE `usertypes` DISABLE KEYS */;
INSERT INTO `usertypes` VALUES (1,'Admin'),(2,'Customer'),(3,'Employee');
/*!40000 ALTER TABLE `usertypes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-19 14:45:35
