/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.27 : Database - loanorigination
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`loanorigination` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `loanorigination`;

/*Table structure for table `client` */

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `ClientID` bigint NOT NULL AUTO_INCREMENT,
  `JMBG` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `FirstName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `LastName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `BirthDate` date DEFAULT NULL,
  `ContactPhone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Address` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `MaritalStatus` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`ClientID`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `client` */

insert  into `client`(`ClientID`,`JMBG`,`FirstName`,`LastName`,`BirthDate`,`ContactPhone`,`Address`,`Email`,`MaritalStatus`,`Type`) values 
(98,'1609977623221','Milan','Pavlović','1977-09-16','0638657712','Karađorđeva 16, Kraljevo','mare@gmail.com','None','Employed'),
(99,'1804996655498','Marina','Simić','1996-04-18','0607785434','Branka Ćopića 32, Novi Sad','marina96@gmail.com','None','Employed');

/*Table structure for table `credit_bureau_report` */

DROP TABLE IF EXISTS `credit_bureau_report`;

CREATE TABLE `credit_bureau_report` (
  `CreditBureauReportID` bigint NOT NULL AUTO_INCREMENT,
  `CreationDate` date DEFAULT NULL,
  `TotalDebt` decimal(65,2) DEFAULT NULL,
  `TotalAnnuity` decimal(65,2) DEFAULT NULL,
  `NumberOfDaysInDelay` int DEFAULT NULL,
  `ClientID` bigint DEFAULT NULL,
  PRIMARY KEY (`CreditBureauReportID`),
  KEY `applicantid` (`ClientID`),
  CONSTRAINT `credit_bureau_report_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`) ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `credit_bureau_report` */

insert  into `credit_bureau_report`(`CreditBureauReportID`,`CreationDate`,`TotalDebt`,`TotalAnnuity`,`NumberOfDaysInDelay`,`ClientID`) values 
(7,'2021-09-02',350000.00,26500.00,60,98),
(8,'2021-09-02',50000.00,9300.00,0,99);

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `ClientID` bigint NOT NULL,
  `ProductID` bigint NOT NULL,
  `LoanApplicationID` bigint NOT NULL,
  `DocumentID` bigint NOT NULL AUTO_INCREMENT,
  `Title` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Required` tinyint(1) DEFAULT NULL,
  `ForSigning` tinyint(1) DEFAULT NULL,
  `Content` longblob,
  `FileName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`ClientID`,`ProductID`,`LoanApplicationID`,`DocumentID`),
  KEY `documentid` (`DocumentID`),
  KEY `productid` (`ProductID`),
  KEY `loanapplicationid` (`LoanApplicationID`),
  CONSTRAINT `document_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `document_template` (`ProductID`),
  CONSTRAINT `document_ibfk_2` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`),
  CONSTRAINT `document_ibfk_3` FOREIGN KEY (`LoanApplicationID`) REFERENCES `loan_application` (`LoanApplicationID`) ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `document` */

/*Table structure for table `document_template` */

DROP TABLE IF EXISTS `document_template`;

CREATE TABLE `document_template` (
  `ProductID` bigint NOT NULL,
  `DocumentTemplateID` bigint NOT NULL,
  `Title` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Required` tinyint(1) DEFAULT NULL,
  `ForSigning` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ProductID`,`DocumentTemplateID`),
  KEY `requireddocumentid` (`DocumentTemplateID`),
  CONSTRAINT `document_template_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`) ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `document_template` */

insert  into `document_template`(`ProductID`,`DocumentTemplateID`,`Title`,`Type`,`Required`,`ForSigning`) values 
(1,1,'Lična karta','Identification document',1,0),
(1,2,'Potvrda o zaposlenju','Certificate of employment',1,1),
(1,3,'Potvrda o visini primanja','Average quarterly income',1,1),
(1,4,'Rešenje o porezu na dohodak','OTHER',0,0),
(1,5,'Poreska prijava za preduzetnike koji porez utvrđuju samooporezivanjem','OTHER',0,0),
(2,1,'Lična karta','Identification document',1,0),
(2,2,'Potvrda o zaposlenju','Certificate of employment',1,1),
(2,3,'Potvrda o visini primanja','Average quarterly income',1,1),
(2,4,'Rešenje o porezu na dohodak','OTHER',0,1),
(2,5,'Poreska prijava za preduzetnike koji porez utvrđuju samooporezivanjem','OTHER',0,1),
(3,1,'Lična karta','Identification document',1,0),
(3,2,'Potvrda o zaposlenju','Certificate of employment',1,1),
(3,3,'Potvrda o visini primanja','Average quarterly income',1,1),
(3,4,'Rešenje o porezu na dohodak','OTHER',0,0),
(3,5,'Poreska prijava za preduzetnike koji porez utvrđuju samooporezivanjem','OTHER',0,0),
(3,6,'Administrativna zabrana','OTHER',1,1),
(3,7,'Profaktura prodavca vozila','OTHER',1,0),
(4,1,'Lična karta','Identification document',1,0),
(4,2,'Potvrda o zaposlenju','Certificate of employment',1,1),
(4,3,'Potvrda o visini primanja','Average quarterly income',1,1),
(4,4,'Rešenje o porezu na dohodak','OTHER',0,0),
(4,5,'Poreska prijava za preduzetnike koji porez utvrđuju samooporezivanjem','OTHER',0,0),
(4,6,'Administrativna zabrana','OTHER',1,1),
(4,7,'Profaktura prodavca vozila','OTHER',1,0),
(5,1,'Lična karta','Identification document',1,0),
(5,2,'Potvrda o zaposlenju','Certificate of employment',1,1),
(5,3,'Potvrda o visini primanja','Average quarterly income',1,1),
(5,4,'Rešenje o porezu na dohodak','OTHER',0,0),
(5,5,'Poreska prijava za preduzetnike koji porez utvrđuju samooporezivanjem','OTHER',0,0),
(5,6,'Potvrda o stanju duga','OTHER',1,0),
(166,1,'Lična karta','Identification document',1,1);

/*Table structure for table `employed` */

DROP TABLE IF EXISTS `employed`;

CREATE TABLE `employed` (
  `ClientID` bigint NOT NULL,
  `EmployerName` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `TaxIdentificationNumberOfEmployer` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `SalaryAmount` decimal(65,2) DEFAULT NULL,
  `LengthOfEmployment` int DEFAULT NULL,
  `ContractType` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`ClientID`),
  CONSTRAINT `employed_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`) ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `employed` */

insert  into `employed`(`ClientID`,`EmployerName`,`TaxIdentificationNumberOfEmployer`,`SalaryAmount`,`LengthOfEmployment`,`ContractType`) values 
(98,'Nul Tien','107758934',230000.00,84,'Indefinite period'),
(99,'Symphony','876390876',120000.00,14,'Fixed-term');

/*Table structure for table `fraud_report` */

DROP TABLE IF EXISTS `fraud_report`;

CREATE TABLE `fraud_report` (
  `FraudReportID` bigint NOT NULL AUTO_INCREMENT,
  `CreationDate` date DEFAULT NULL,
  `BlackListRate` double DEFAULT NULL,
  `AMLRate` double DEFAULT NULL,
  `ClientID` bigint DEFAULT NULL,
  PRIMARY KEY (`FraudReportID`),
  KEY `applicantid` (`ClientID`),
  CONSTRAINT `fraud_report_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`) ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `fraud_report` */

insert  into `fraud_report`(`FraudReportID`,`CreationDate`,`BlackListRate`,`AMLRate`,`ClientID`) values 
(7,'2021-09-02',0.78,1.22,98),
(8,'2021-09-02',0.3,0.01,99);

/*Table structure for table `loan_application` */

DROP TABLE IF EXISTS `loan_application`;

CREATE TABLE `loan_application` (
  `ClientID` bigint NOT NULL,
  `ProductID` bigint NOT NULL,
  `LoanApplicationID` bigint NOT NULL AUTO_INCREMENT,
  `CreationDate` date DEFAULT NULL,
  `Status` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Amount` decimal(65,2) DEFAULT NULL,
  `Annuity` decimal(65,2) DEFAULT NULL,
  `RepaymentPeriod` int DEFAULT NULL,
  `CreditBureauReportID` bigint DEFAULT NULL,
  `FraudReportID` bigint DEFAULT NULL,
  PRIMARY KEY (`ClientID`,`ProductID`,`LoanApplicationID`),
  KEY `loanapplicationid` (`LoanApplicationID`),
  KEY `creditbureaureportid` (`CreditBureauReportID`),
  KEY `fraudreportid` (`FraudReportID`),
  KEY `productid` (`ProductID`),
  CONSTRAINT `loan_application_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`) ON UPDATE RESTRICT,
  CONSTRAINT `loan_application_ibfk_2` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`) ON UPDATE RESTRICT,
  CONSTRAINT `loan_application_ibfk_3` FOREIGN KEY (`CreditBureauReportID`) REFERENCES `credit_bureau_report` (`CreditBureauReportID`) ON UPDATE RESTRICT,
  CONSTRAINT `loan_application_ibfk_4` FOREIGN KEY (`FraudReportID`) REFERENCES `fraud_report` (`FraudReportID`) ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `loan_application` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `ProductID` bigint NOT NULL AUTO_INCREMENT,
  `Name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Description` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Currency` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `MinAmount` decimal(65,2) DEFAULT NULL,
  `MaxAmount` decimal(65,2) DEFAULT NULL,
  `InterestRate` double DEFAULT NULL,
  `Status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product` */

insert  into `product`(`ProductID`,`Name`,`Description`,`Currency`,`MinAmount`,`MaxAmount`,`InterestRate`,`Status`) values 
(1,'Gotovinski kredit do 12 meseci','Gotovinski dinarsk kredit sa periodom otplate do 12 meseci po kamatnoj stopi 7.95%','RSD',20000.00,120000.00,7.85,'Active'),
(2,'Gotovninski kredit do 71 meseci','Gotovinski dinarsk kredit sa periodom otplate do 71 meseci po kamatnoj stopi 12%','RSD',50000.00,2250000.00,11,'Inactive'),
(3,'Auto kredit [EUR]','Kredit za kupovinu vozila od pravnih i fizičkih lica u evrima','EUR',2000.00,15000.00,6,'Active'),
(4,'Auto kredit [RSD]','Kredit za kupovinu vozila od pravnih i fizičkih lica u dinarima','RSD',200000.00,3600000.00,5.45,'Active'),
(5,'Kredit za refinansiranje u dinarima','Kredit za refinansiranje u dinarima sa periodom otplate do 71 mesec i kamatnom stopom 11%','RSD',25000.00,2250000.00,11,'Active'),
(166,'Gotovinski kredit do 6 meseci','Gotovinski dinarsk kredit sa periodom otplate do 12 meseci po kamatnoj stopi 7.95%','EUR',500.00,2000.00,7.95,'Active');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `ClientID` bigint NOT NULL,
  `AverageGrade` double DEFAULT NULL,
  `LengthOfStudy` int DEFAULT NULL,
  `StateBudgetFinancing` tinyint(1) DEFAULT NULL,
  `NumberOfRemainingExams` int DEFAULT NULL,
  `University` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Faculty` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Course` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`ClientID`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`) ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `student` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `UserID` bigint NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `LastName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Username` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Password` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`UserID`,`FirstName`,`LastName`,`Username`,`Password`) values 
(1,'Jovana','Jovanović','jovana','jovana'),
(2,'Marko','Marković','marko','marko'),
(3,'Miloš','Milošević','milos','milos');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
