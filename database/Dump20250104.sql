-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: technix_db
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `tblaccount`
--

DROP TABLE IF EXISTS `tblaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblaccount` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `account_nature` varchar(255) DEFAULT NULL,
  `account_sub_id` int DEFAULT NULL,
  `accounts` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int NOT NULL,
  `is_active` bit(1) NOT NULL,
  `is_bank_account` bit(1) NOT NULL,
  `order_by_number` int NOT NULL,
  `system_default` bit(1) NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UK4g24gseg2pw5ijyut33t92bxl` (`account_id`,`company_id`),
  KEY `FKhtqvjfrjkj4yfw24noboo0c1k` (`account_sub_id`),
  KEY `FKc2qh74owr1abbk0icaet668ps` (`company_id`),
  CONSTRAINT `FKc2qh74owr1abbk0icaet668ps` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`),
  CONSTRAINT `FKhtqvjfrjkj4yfw24noboo0c1k` FOREIGN KEY (`account_sub_id`) REFERENCES `tblaccount` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblaccount`
--

LOCK TABLES `tblaccount` WRITE;
/*!40000 ALTER TABLE `tblaccount` DISABLE KEYS */;
INSERT INTO `tblaccount` VALUES (1,'ASSETS',NULL,'ASSETS',0,'2024-11-30 12:31:35.122208',1,_binary '',_binary '',1,_binary ''),(2,'ASSETS',1,'FIXED ASSETS',0,'2024-11-30 12:32:22.717427',1,_binary '',_binary '',2,_binary ''),(3,'ASSETS',1,'CURRENT ASSETS',0,'2024-11-30 15:11:11.498058',1,_binary '',_binary '',3,_binary ''),(4,'CURRENT ASSETS',3,'Sundry Debtors',0,'2024-11-30 15:12:10.100528',1,_binary '',_binary '',4,_binary ''),(5,'ASSETS',1,'INVENTORIES',0,'2024-11-30 15:14:51.452655',1,_binary '',_binary '',4,_binary ''),(6,'ASSETS',1,'INVESTMENT ASSETS',0,'2024-11-30 15:15:29.463221',1,_binary '',_binary '',5,_binary ''),(7,'ASSETS',1,'OTHER ASSETS',0,'2024-11-30 15:16:11.074233',1,_binary '',_binary '',6,_binary ''),(8,'ASSETS',1,'CASH & BANK BALANCES',0,'2024-11-30 15:16:32.888354',1,_binary '',_binary '',7,_binary ''),(9,'CASH & BANK BALANCES',8,'Bank Accounts',0,'2024-11-30 15:17:45.008829',1,_binary '',_binary '',8,_binary ''),(10,'LIABILITIES',NULL,'LIABILITIES',0,'2024-11-30 15:18:58.068639',1,_binary '',_binary '',9,_binary ''),(12,'LIABILITIES',10,'CAPITAL ACCOUNT',0,'2024-11-30 15:19:57.356218',1,_binary '',_binary '',10,_binary ''),(13,'LIABILITIES',10,'CURRENT LIABILITIES',0,'2024-11-30 15:23:33.679984',1,_binary '',_binary '',10,_binary ''),(14,'CURRENT LIABILITIES',13,'Sundry Creditors',0,'2024-11-30 15:24:17.081172',1,_binary '',_binary '',10,_binary ''),(15,'LIABILITIES',10,'DUTIES & TAXES',0,'2024-11-30 15:24:52.723319',1,_binary '',_binary '',10,_binary ''),(16,'LIABILITIES',10,'LIABILITIES & PROVISION',0,'2024-11-30 15:25:15.023261',1,_binary '',_binary '',10,_binary ''),(17,'LIABILITIES',10,'OTHER LIABILITIES',0,'2024-11-30 15:25:32.688005',1,_binary '',_binary '',10,_binary ''),(18,'LIABILITIES',10,'PROFIT & LOSS A/C',0,'2024-11-30 15:25:51.088945',1,_binary '',_binary '',10,_binary ''),(19,'INCOME',NULL,'INCOME',0,'2024-11-30 15:28:29.445502',1,_binary '',_binary '',10,_binary ''),(20,'INCOME',19,'SALES ACCOUNT',0,'2024-11-30 15:29:15.967972',1,_binary '',_binary '',10,_binary ''),(21,'INCOME',19,'DIRECT INCOME',0,'2024-11-30 15:29:47.287237',1,_binary '',_binary '',10,_binary ''),(22,'INCOME',19,'INDIRECT INCOME',0,'2024-11-30 15:34:29.948049',1,_binary '',_binary '',10,_binary ''),(23,'INCOME',19,'OTHER INCOME',0,'2024-11-30 15:34:56.432634',1,_binary '',_binary '',10,_binary ''),(24,'EXPENDITURE',NULL,'EXPENDITURE ',0,'2024-11-30 15:35:45.444534',1,_binary '',_binary '',10,_binary ''),(25,'EXPENDITURE',24,'PURCHASE ACCOUNTS',0,'2024-11-30 15:37:00.377753',1,_binary '',_binary '',10,_binary ''),(26,'EXPENDITURE',24,'DIRECT EXPENSES',0,'2024-11-30 15:37:37.846656',1,_binary '',_binary '',10,_binary ''),(27,'EXPENDITURE',24,'INDIRECT EXPENSES',0,'2024-11-30 15:37:55.391096',1,_binary '',_binary '',10,_binary ''),(28,'EXPENDITURE',24,'FINANCIAL EXPENSES',0,'2024-11-30 15:38:19.700331',1,_binary '',_binary '',10,_binary ''),(29,'EXPENDITURE',24,'OTHER EXPENSES',0,'2024-11-30 15:38:42.030847',1,_binary '',_binary '',10,_binary '');
/*!40000 ALTER TABLE `tblaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblbill`
--

DROP TABLE IF EXISTS `tblbill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblbill` (
  `bill_id` int NOT NULL AUTO_INCREMENT,
  `bill_date` date DEFAULT NULL,
  `branch_id` int NOT NULL,
  `company_id` int DEFAULT NULL,
  `contact_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int NOT NULL,
  `customer_address` varchar(255) DEFAULT NULL,
  `customer_contact_no` varchar(255) DEFAULT NULL,
  `customer_email` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `disc_per` double NOT NULL,
  `discount` double NOT NULL,
  `due_date` date DEFAULT NULL,
  `grand_total` double NOT NULL,
  `invoice_no` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `other_charges` double NOT NULL,
  `place_of_supply` varchar(255) DEFAULT NULL,
  `reference_no` varchar(255) DEFAULT NULL,
  `round_off` double NOT NULL,
  `sales_man` varchar(255) DEFAULT NULL,
  `sales_man_id` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `sub_total` double NOT NULL,
  `total_taxes` double NOT NULL,
  `transaction_id` int DEFAULT NULL,
  `global_disc` double NOT NULL,
  `global_disc_per` double NOT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `FKgcewbhhgeov54abdx2d99kg5g` (`company_id`),
  KEY `FK17v7rrmtqg07s4ireiug1sij5` (`contact_id`),
  KEY `FK2snavnn2ani0wxdwog1kwxrw` (`transaction_id`),
  CONSTRAINT `FK17v7rrmtqg07s4ireiug1sij5` FOREIGN KEY (`contact_id`) REFERENCES `tblcontacts` (`contact_id`),
  CONSTRAINT `FK2poc4svld0qyxgf1i82fls326` FOREIGN KEY (`transaction_id`) REFERENCES `tbltransaction` (`row_id`),
  CONSTRAINT `FK2snavnn2ani0wxdwog1kwxrw` FOREIGN KEY (`transaction_id`) REFERENCES `tblfinancialperiodtransaction` (`transaction_id`),
  CONSTRAINT `FKgcewbhhgeov54abdx2d99kg5g` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbill`
--

LOCK TABLES `tblbill` WRITE;
/*!40000 ALTER TABLE `tblbill` DISABLE KEYS */;
INSERT INTO `tblbill` VALUES (6,'2024-12-12',1,1,1,'2024-12-14 15:06:34.456879',1,'Siwan','9867457656','sk@gmail.com','S.K Enterprises',0,48.94,'2024-12-05',3479,'INV123','Invoice',0,'10','Ref123',-0.45,'',0,'Unpaid',2997.63,530.76,10,0,0),(7,'2024-12-12',2,1,1,'2024-12-14 15:52:08.616941',1,'Siwan','9867457656','sk@gmail.com','S.K Enterprises',0,48.94,'2024-12-05',3479,'INV124','Invoice',0,'10','Ref123',-0.45,'',0,'Unpaid',2997.63,530.76,11,0,0),(8,'2024-12-12',3,1,1,'2024-12-14 16:16:07.885457',1,'Siwan','9867457656','sk@gmail.com','S.K Enterprises',0,48.94,'2024-12-05',3479,'INV125','Invoice',0,'10','Ref123',-0.45,'',0,'Unpaid',2997.63,530.76,12,0,0),(9,'2024-12-12',1,1,1,'2024-12-23 11:52:59.661720',1,'Siwan','9867457656','sk@gmail.com','S.K Enterprises',0,48.94,'2024-12-05',3479,NULL,'Invoice',0,'10','Ref123',-0.45,'',0,'Unpaid',2997.63,530.76,53,0,0),(10,'2024-12-12',1,1,1,'2024-12-23 12:04:05.509638',1,'Siwan','9867457656','sk@gmail.com','S.K Enterprises',0,48.94,'2024-12-05',3479,'TAX/24-25/1','Invoice',0,'10','Ref123',-0.45,'',0,'Unpaid',2997.63,530.76,55,0,0);
/*!40000 ALTER TABLE `tblbill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblbill_charges`
--

DROP TABLE IF EXISTS `tblbill_charges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblbill_charges` (
  `row_id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `bill_id` int DEFAULT NULL,
  `charges_id` int DEFAULT NULL,
  `ledger_id` int DEFAULT NULL,
  `percent` double DEFAULT NULL,
  `tax_id` int DEFAULT NULL,
  `value_of_field` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`row_id`),
  KEY `FKf80q9r63aopu33ecoo2a7p25f` (`bill_id`),
  CONSTRAINT `FKf80q9r63aopu33ecoo2a7p25f` FOREIGN KEY (`bill_id`) REFERENCES `tblbill` (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbill_charges`
--

LOCK TABLES `tblbill_charges` WRITE;
/*!40000 ALTER TABLE `tblbill_charges` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblbill_charges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblbill_particulars`
--

DROP TABLE IF EXISTS `tblbill_particulars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblbill_particulars` (
  `row_id` int NOT NULL AUTO_INCREMENT,
  `alternate_unit` varchar(255) DEFAULT NULL,
  `amount` double NOT NULL,
  `bill_id` int DEFAULT NULL,
  `billed_qty` double NOT NULL,
  `billing_date` date DEFAULT NULL,
  `branch_id` int NOT NULL,
  `company_id` int DEFAULT NULL,
  `conv_factor` double NOT NULL,
  `disc_per` double NOT NULL,
  `discount` double NOT NULL,
  `free_qty` double NOT NULL,
  `hsn_code` varchar(255) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `quantity` double NOT NULL,
  `rate` double NOT NULL,
  `tax_per` varchar(255) DEFAULT NULL,
  `tax_type` varchar(255) DEFAULT NULL,
  `taxable_value` double NOT NULL,
  `taxation_type` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `tex_id` int NOT NULL,
  PRIMARY KEY (`row_id`),
  KEY `FKetb8l4on354q5dns5ua4y5w5h` (`bill_id`),
  KEY `FKc6hwq3fdtjn1bdrjfrch9sl32` (`company_id`),
  KEY `FKbejfm1ntwb8dc1j43y3j7tdy` (`product_id`),
  CONSTRAINT `FKbejfm1ntwb8dc1j43y3j7tdy` FOREIGN KEY (`product_id`) REFERENCES `tblproduct` (`product_id`),
  CONSTRAINT `FKc6hwq3fdtjn1bdrjfrch9sl32` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`),
  CONSTRAINT `FKetb8l4on354q5dns5ua4y5w5h` FOREIGN KEY (`bill_id`) REFERENCES `tblbill` (`bill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbill_particulars`
--

LOCK TABLES `tblbill_particulars` WRITE;
/*!40000 ALTER TABLE `tblbill_particulars` DISABLE KEYS */;
INSERT INTO `tblbill_particulars` VALUES (2,'box',1227,1,16,'2024-12-12',0,1,1,0,1.1,0,'\" \"',1,16,65,'18','',1040,'Exclusive','Pcs',0),(3,'box',1227,2,16,'2024-12-12',0,1,1,0,1.1,0,'\" \"',1,16,65,'18','',1040,'Exclusive','Pcs',0),(4,'box',1227,4,16,'2024-12-12',0,1,1,0,1.1,0,'',1,16,65,'18','',1040,'Exclusive','Pcs',0),(5,'box',1227,5,16,'2024-12-12',1,1,1,0,1.1,0,'',1,16,65,'18','',1040,'Exclusive','Pcs',0),(6,'box',1227,6,16,'2024-12-12',1,1,1,0,1.1,0,'',1,16,65,'18','',1040,'Exclusive','Pcs',0),(7,'box',1227,7,16,'2024-12-12',1,1,1,0,1.1,0,'',1,16,65,'18','',1040,'Exclusive','Pcs',0),(8,'box',1227,8,16,'2024-12-12',1,1,1,0,1.1,0,'',1,16,65,'18','',1040,'Exclusive','Pcs',0),(9,'box',1227,9,16,'2024-12-12',1,1,1,0,1.1,0,'',1,16,65,'18','',1040,'Exclusive','Pcs',0),(10,'box',1227,10,16,'2024-12-12',1,1,1,0,1.1,0,'',1,16,65,'18','',1040,'Exclusive','Pcs',0);
/*!40000 ALTER TABLE `tblbill_particulars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblbill_product_taxes`
--

DROP TABLE IF EXISTS `tblbill_product_taxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblbill_product_taxes` (
  `row_id` int NOT NULL AUTO_INCREMENT,
  `bill_id` int DEFAULT NULL,
  `billing_date` date DEFAULT NULL,
  `tax_amount` double NOT NULL,
  `tax_name` varchar(255) DEFAULT NULL,
  `tax_per` double NOT NULL,
  `tax_type` varchar(255) DEFAULT NULL,
  `taxable_value` double NOT NULL,
  `tax_id` int NOT NULL,
  PRIMARY KEY (`row_id`),
  KEY `FK781jajsnkva6appr9omjopab` (`bill_id`),
  CONSTRAINT `FK781jajsnkva6appr9omjopab` FOREIGN KEY (`bill_id`) REFERENCES `tblbill` (`bill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbill_product_taxes`
--

LOCK TABLES `tblbill_product_taxes` WRITE;
/*!40000 ALTER TABLE `tblbill_product_taxes` DISABLE KEYS */;
INSERT INTO `tblbill_product_taxes` VALUES (2,1,'2024-12-12',235.38,'CGST 9%',9,'CGST',2948.69,0),(3,2,'2024-12-12',235.38,'CGST 9%',9,'CGST',2948.69,0),(4,4,'2024-12-12',235.38,'CGST 9%',9,'CGST',2948.69,0),(5,5,'2024-12-12',235.38,'CGST 9%',9,'CGST',2948.69,0),(6,6,'2024-12-12',235.38,'CGST 9%',9,'CGST',2948.69,0),(7,7,'2024-12-12',235.38,'CGST 9%',9,'CGST',2948.69,0),(8,8,'2024-12-12',235.38,'CGST 9%',9,'CGST',2948.69,0),(9,9,'2024-12-12',235.38,'CGST 9%',9,'CGST',2948.69,0),(10,10,'2024-12-12',235.38,'CGST 9%',9,'CGST',2948.69,0);
/*!40000 ALTER TABLE `tblbill_product_taxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblbrands`
--

DROP TABLE IF EXISTS `tblbrands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblbrands` (
  `brand_id` int NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`brand_id`),
  KEY `FKqn653t1selrhfwcg69dfdo7fl` (`company_id`),
  CONSTRAINT `FKqn653t1selrhfwcg69dfdo7fl` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbrands`
--

LOCK TABLES `tblbrands` WRITE;
/*!40000 ALTER TABLE `tblbrands` DISABLE KEYS */;
INSERT INTO `tblbrands` VALUES (1,'Lee',1,'2024-11-18 13:32:10.857389','1','Denim'),(2,'Levi\'s',1,'2024-11-18 13:32:35.909261','1','Denim'),(3,'Jhon Player',2,'2024-11-18 13:32:58.060150','1','Denim'),(4,'Mufti',3,'2024-11-18 14:05:09.305319','1','Denim');
/*!40000 ALTER TABLE `tblbrands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcategory`
--

DROP TABLE IF EXISTS `tblcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcategory` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `FK2rsyrqa2h9sqbrvyskamces6o` (`company_id`),
  KEY `FK85hpghqnqv5kahx5ebjc0easj` (`parent_id`),
  CONSTRAINT `FK2rsyrqa2h9sqbrvyskamces6o` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`),
  CONSTRAINT `FK85hpghqnqv5kahx5ebjc0easj` FOREIGN KEY (`parent_id`) REFERENCES `tblcategory` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcategory`
--

LOCK TABLES `tblcategory` WRITE;
/*!40000 ALTER TABLE `tblcategory` DISABLE KEYS */;
INSERT INTO `tblcategory` VALUES (1,'Development',1,'2024-11-19 12:13:03.027557',1,'Software Developer','/category/1/1.jpg',0),(2,'backend',1,'2024-11-19 12:15:45.467414',1,'Software Developer','/category/2/2.jpg',1),(3,'Frontend',1,'2024-11-19 12:50:56.452522',1,'Software Developer','/category/3/3.jpg',1),(4,'Middileware',1,'2024-11-19 12:52:46.475348',1,'Software Developer','/category/4/4.jpg',2),(5,'Graphic Designer',1,'2024-11-19 13:06:33.552017',1,'Software Developer','/category/5/5.jpg',0),(6,'Designer1',1,'2024-11-19 14:43:19.770932',1,'graphics','/category/6/6.jpg',5),(7,'Designer2',1,'2024-11-19 14:53:07.833190',1,'graphics','/category/7/7.jpg',5),(8,'Designer3',1,'2024-11-19 14:53:28.214629',1,'graphics','/category/8/8.jpg',5),(9,'abc',1,'2024-11-25 13:17:40.458070',1,'desc','/category/9/9.jpg',5);
/*!40000 ALTER TABLE `tblcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcompanies`
--

DROP TABLE IF EXISTS `tblcompanies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcompanies` (
  `company_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `bussines_type` varchar(255) DEFAULT NULL,
  `cin_number` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `logo_on_invoice` bit(1) DEFAULT NULL,
  `logo_position` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `registration_date` datetime(6) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`company_id`),
  CONSTRAINT `FKrdq735a1ajsnd8gh55d9f2wgn` FOREIGN KEY (`company_id`) REFERENCES `tblcustomer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcompanies`
--

LOCK TABLES `tblcompanies` WRITE;
/*!40000 ALTER TABLE `tblcompanies` DISABLE KEYS */;
INSERT INTO `tblcompanies` VALUES (1,'Phulwari Sharif','IT','TECH234','Patna','Technix India','India','2024-11-18 13:01:14.939568',1,1,'technix@gmail.com','abc','/company/1/1.jpg',_binary '','right','07763876292','07763876292','841506','2024-11-18 13:01:14.939568','Bihar','technix.in'),(2,'Noida sector 63','IT','TCS234','Noida','TCS','India','2024-11-18 13:05:45.403121',1,2,'tcs@gmail.com','abc','/company/2/2.jpg',_binary '\0','right','9876567898','9876567898','201301','2024-11-18 13:05:45.403121','Uttar Pradesh','tcs.in'),(3,'High Tech City','IT','WIPRO234','Hyderabad','Wipro','India','2024-11-18 13:08:58.816068',1,3,'wipro@gmail.com','abc','/company/3/3.jpg',_binary '','right','9876567898','9876567898','201301','2024-11-18 13:08:58.826576','Telangana','wipro.in'),(4,'New York','IT','FB123','New York','Facebook','USA','2024-11-18 13:16:12.543072',1,4,'facebook@gmail.com','xyz','/company/4/4',_binary '','right','7865348798',NULL,'201301','2024-11-18 13:11:55.874880','Valikon City','fb.in'),(5,'Phulwari Sharif','IT','CIN123','Patna','Technix India','India','2024-12-25 13:06:24.565876',1,1,'erviveksingh6292@gmail.com','FAX123','/company/5/5.png',_binary '','right','07763876292','07763876292','841506','2024-12-25 13:06:24.565876','Bihar','in.technix');
/*!40000 ALTER TABLE `tblcompanies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcompany_bank_details`
--

DROP TABLE IF EXISTS `tblcompany_bank_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcompany_bank_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `bank_account_nature` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `branch_address` varchar(255) DEFAULT NULL,
  `branch_name` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int NOT NULL,
  `iban_no` varchar(255) DEFAULT NULL,
  `ifsc_code` varchar(255) DEFAULT NULL,
  `signing_authority` varchar(255) DEFAULT NULL,
  `swift_code` varchar(255) DEFAULT NULL,
  `upi_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqk87j6xxx4b1txbb4fs3hnj8x` (`company_id`),
  CONSTRAINT `FKqk87j6xxx4b1txbb4fs3hnj8x` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcompany_bank_details`
--

LOCK TABLES `tblcompany_bank_details` WRITE;
/*!40000 ALTER TABLE `tblcompany_bank_details` DISABLE KEYS */;
INSERT INTO `tblcompany_bank_details` VALUES (1,'Vivek Singh','1555000100329067','Saving','Corporate','Punjab National Bank','Siwan','Siwan',1,'2024-11-23 14:35:52.347293',1,'11998877','PUNB01555000','Vivek singh','PNB234','7763876292@ybl'),(2,'John','1234567898','Saving','Individual','State Bank Of India','Patna','Patna',1,'2024-11-23 14:53:15.905762',1,'SBI34533','SBI123','Jhon','STBHJ123','sbi@ybl');
/*!40000 ALTER TABLE `tblcompany_bank_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcompany_partners`
--

DROP TABLE IF EXISTS `tblcompany_partners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcompany_partners` (
  `id` int NOT NULL AUTO_INCREMENT,
  `aadhar` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int NOT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `din_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `executive` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `pan` varchar(255) DEFAULT NULL,
  `person_name` varchar(255) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf93764wm7jkmcn1wayql74miv` (`company_id`),
  CONSTRAINT `FKf93764wm7jkmcn1wayql74miv` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcompany_partners`
--

LOCK TABLES `tblcompany_partners` WRITE;
/*!40000 ALTER TABLE `tblcompany_partners` DISABLE KEYS */;
INSERT INTO `tblcompany_partners` VALUES (1,'23454322345','Siwan','Siwan',1,'India','2024-11-23 16:32:15.879389',1,'Developer','DIN123','vivek@gmail.com','Technix','9876567897','V123SH','Vivek Singh','841506','Biarh'),(2,'23454322345','Siwan','Siwan',1,'India','2024-11-23 16:28:22.325956',1,'Developer','DIN123','sonu@gmail.com','Technix','9876567897','V123SH','Sonu Singh','841506','Bihar'),(3,'23454322345','Siwan','Siwan',1,'India','2024-11-23 16:29:40.441999',1,'Developer','DIN123','jhon@gmail.com','Technix','9876567897','V123SH','Jhon','841506','Bihar');
/*!40000 ALTER TABLE `tblcompany_partners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcompany_shareholder`
--

DROP TABLE IF EXISTS `tblcompany_shareholder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcompany_shareholder` (
  `id` int NOT NULL AUTO_INCREMENT,
  `aadhar` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int NOT NULL,
  `date_of_allotment` datetime(6) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `nominee` varchar(255) DEFAULT NULL,
  `number_of_shares` varchar(255) DEFAULT NULL,
  `pan` varchar(255) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `share_value` double NOT NULL,
  `shareholder_name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5nclxl9tmpgf7nmeus22kbyoq` (`company_id`),
  CONSTRAINT `FK5nclxl9tmpgf7nmeus22kbyoq` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcompany_shareholder`
--

LOCK TABLES `tblcompany_shareholder` WRITE;
/*!40000 ALTER TABLE `tblcompany_shareholder` DISABLE KEYS */;
INSERT INTO `tblcompany_shareholder` VALUES (1,'342546758982','Siwan','Siwan',1,'India','2024-11-23 11:39:45.087605',1,'2024-11-23 11:39:45.086760','Member','vivek@gmail.com','8765679875','sukku','10','V123HDR','841506',150,'Vivek Singh','Bihar'),(2,'342546758982','Siwan','Siwan',1,'India','2024-11-23 11:00:01.525940',1,'2024-11-23 11:00:01.525940','Member','sonu@gmail.com','8765679875','sonu singh','10','V123HDR','841506',120,'Sonu Singh','Bihar'),(3,'342546758982','Siwan','Siwan',1,'India','2024-11-23 11:00:31.392453',1,'2024-11-23 11:00:31.392453','Member','ravi@gmail.com','8765679875','ravi singh','10','V123HDR','841506',120,'Ravi Singh','Bihar'),(4,'342546758982','Siwan','Siwan',1,'India','2024-11-23 11:01:09.381039',1,'2024-11-23 11:01:09.381039','Member','raman@gmail.com','8765679875','ravi singh','10','V123HDR','841506',150,'Raman Singh','Bihar'),(5,'342546758982','Siwan','Siwan',1,'India','2024-11-23 11:01:55.374616',1,'2024-11-23 11:01:55.374616','Director','masoom@gmail.com','8765679875','Hanu','10','V123HDR','841506',200,'Masoom Malick','Bihar');
/*!40000 ALTER TABLE `tblcompany_shareholder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcontacts`
--

DROP TABLE IF EXISTS `tblcontacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcontacts` (
  `contact_id` int NOT NULL AUTO_INCREMENT,
  `adhaar_no` varchar(255) DEFAULT NULL,
  `tax_reg_no` varchar(255) DEFAULT NULL,
  `tds_applicable` bit(1) NOT NULL,
  `account_id` int NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `business_name` varchar(255) DEFAULT NULL,
  `business_type` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `contact_code` varchar(255) DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL,
  `contact_person` varchar(255) DEFAULT NULL,
  `contacts_type` enum('Customer','Supplier') DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_by` int NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `credit_limit` double NOT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `customer_type` varchar(255) DEFAULT NULL,
  `default_payment` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `gstin` varchar(255) DEFAULT NULL,
  `gst_in_type` varchar(255) DEFAULT NULL,
  `is_whatsapp` bit(1) NOT NULL,
  `ledger_id` int NOT NULL,
  `login_email` varchar(255) DEFAULT NULL,
  `login_password` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `notification` varchar(255) DEFAULT NULL,
  `oppening_balance` double NOT NULL,
  `oppening_type` varchar(255) DEFAULT NULL,
  `pan_no` varchar(255) DEFAULT NULL,
  `party_disc_per` double NOT NULL,
  `payment_terms` varchar(255) DEFAULT NULL,
  `pin_code` varchar(255) DEFAULT NULL,
  `portal_access` bit(1) NOT NULL,
  `pricing_id` int NOT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  `shipping_city` varchar(255) DEFAULT NULL,
  `shipping_country` varchar(255) DEFAULT NULL,
  `shipping_pin_code` varchar(255) DEFAULT NULL,
  `shipping_state` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `state_code` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `taxation_type` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `use_as` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `work_email` varchar(255) DEFAULT NULL,
  `work_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `FKfh6uyyxt8fldd58exiksp6ngb` (`company_id`),
  CONSTRAINT `FKfh6uyyxt8fldd58exiksp6ngb` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcontacts`
--

LOCK TABLES `tblcontacts` WRITE;
/*!40000 ALTER TABLE `tblcontacts` DISABLE KEYS */;
INSERT INTO `tblcontacts` VALUES (1,'123456789013','TAX123',_binary '',4,'Siwan, Bihar','Software Development','Development','Siwan',1,'CODE123','Vivek Singh','Vivek','Customer','India',1,'2024-12-11 11:51:48.806641',10000,'Rupees','Supplier','No','Developer','vivek@gmail.com','FAX234','GSTIN','GST Type',_binary '',43,'vivek@gmail.com','$2a$10$9y7lX8HtkJEpinNMY7fa/.BZSbAxWD4odgjc4K.0u8h4FxxFvpmDa','123456789','Yes',10000,'Dr','VIV234HD',1,'Pay Terms','841506',_binary '',1,'/contacts/1/1.jpg','This is good','Phulwari','Patna','India','821403','Bihar','Bihar','244656','Active','GST123','Software Developer','Individual','technix.in','vivek@gmail.com','123456789'),(2,'123456789013','TAX123',_binary '',4,'Phulwari, Patna','Software Development','Development','Patna',1,'CODE123','Smith','Smith','Customer','India',1,'2024-12-24 16:14:49.467535',10000,'Smith','Supplier','No','Developer','vivek@gmail.com','FAX234','GSTIN','GST Type',_binary '',47,'smith@gmail.com','$2a$10$jGmPLrYnX/bA7EvOvr2gGON1puy2.3A9ZWrs4VU..CWy0rxxgo4ji','123456789','Yes',10000,'Cr','VIV234HD',1,'Pay Terms','841506',_binary '',1,'/contacts/2/2.png','This is good','Phulwari','Patna','India','821403','Bihar','Bihar','244656','Active','GST123','Software Developer','Individual','technix.in','smith@gmail.com','123456789');
/*!40000 ALTER TABLE `tblcontacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcustomer`
--

DROP TABLE IF EXISTS `tblcustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcustomer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `bussiness_type` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_size` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcustomer`
--

LOCK TABLES `tblcustomer` WRITE;
/*!40000 ALTER TABLE `tblcustomer` DISABLE KEYS */;
INSERT INTO `tblcustomer` VALUES (1,'IT','Technix India','20-50','India','2024-11-18 11:38:29.195847','vivek@gmial.com','Vivek Singh','07763876292'),(2,'IT','Technix India','20-50','India','2024-11-18 11:39:47.373309','shubham@gmial.com','Shubham Singh','9878679878'),(3,'IT','TCS','500-1000','India','2024-11-18 11:40:39.161271','sonu@gmial.com','Sonu Singh','9878679878'),(4,'IT','Technix','20-50','India','2024-11-18 11:41:32.263293','kunal@gmial.com','Kunal Kumar','9878679878'),(5,'IT','Technix','20-50','India','2024-11-18 11:42:09.074432','jhon@gmial.com','Jhon','9878679878');
/*!40000 ALTER TABLE `tblcustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcustomer_subscription`
--

DROP TABLE IF EXISTS `tblcustomer_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcustomer_subscription` (
  `subscription_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int NOT NULL,
  `customer_id` int DEFAULT NULL,
  `is_trail` bit(1) NOT NULL,
  `plan_end` date DEFAULT NULL,
  `plan_id` int DEFAULT NULL,
  `plan_start` date DEFAULT NULL,
  `plan_validity` int NOT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`subscription_id`),
  KEY `FKp1d67eai0gc0ptj37q7hwg2a1` (`customer_id`),
  KEY `FK9ckkm9pqoixps84lrqllirt79` (`plan_id`),
  CONSTRAINT `FK9ckkm9pqoixps84lrqllirt79` FOREIGN KEY (`plan_id`) REFERENCES `tblplan` (`plan_id`),
  CONSTRAINT `FKp1d67eai0gc0ptj37q7hwg2a1` FOREIGN KEY (`customer_id`) REFERENCES `tblcustomer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcustomer_subscription`
--

LOCK TABLES `tblcustomer_subscription` WRITE;
/*!40000 ALTER TABLE `tblcustomer_subscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblcustomer_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbldepartments`
--

DROP TABLE IF EXISTS `tbldepartments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbldepartments` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `company_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`department_id`),
  KEY `FK1tajluejw40d55grkru1t1agr` (`company_id`),
  CONSTRAINT `FK1tajluejw40d55grkru1t1agr` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbldepartments`
--

LOCK TABLES `tbldepartments` WRITE;
/*!40000 ALTER TABLE `tbldepartments` DISABLE KEYS */;
INSERT INTO `tbldepartments` VALUES (1,1,'2024-11-18 14:09:43.705615',1,'Backend','abcd'),(2,1,'2024-11-18 14:09:56.038826',1,'Frontend','abcd'),(3,2,'2024-11-18 14:10:07.335175',1,'Spring boot','abcd'),(4,2,'2024-11-18 14:10:18.006530',1,'Java','abcd');
/*!40000 ALTER TABLE `tbldepartments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblfinancialperiod`
--

DROP TABLE IF EXISTS `tblfinancialperiod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblfinancialperiod` (
  `financial_period_id` int NOT NULL AUTO_INCREMENT,
  `company_id` int DEFAULT NULL,
  `e_date` date DEFAULT NULL,
  `s_date` date DEFAULT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`financial_period_id`),
  KEY `FK270f3933io0faf6c1lu7sd9dn` (`company_id`),
  CONSTRAINT `FK270f3933io0faf6c1lu7sd9dn` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblfinancialperiod`
--

LOCK TABLES `tblfinancialperiod` WRITE;
/*!40000 ALTER TABLE `tblfinancialperiod` DISABLE KEYS */;
INSERT INTO `tblfinancialperiod` VALUES (1,1,'2025-03-31','2024-04-01',_binary ''),(2,5,'2025-03-31','2024-04-01',_binary '');
/*!40000 ALTER TABLE `tblfinancialperiod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblfinancialperiodtransaction`
--

DROP TABLE IF EXISTS `tblfinancialperiodtransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblfinancialperiodtransaction` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `company_id` int DEFAULT NULL,
  `financial_period_id` int NOT NULL,
  `transaction_date` date DEFAULT NULL,
  `transaction_no` int NOT NULL,
  `voucher_no` int NOT NULL,
  `voucher_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FK87j1uah4j0d888x18r1k4383` (`company_id`),
  CONSTRAINT `FK87j1uah4j0d888x18r1k4383` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblfinancialperiodtransaction`
--

LOCK TABLES `tblfinancialperiodtransaction` WRITE;
/*!40000 ALTER TABLE `tblfinancialperiodtransaction` DISABLE KEYS */;
INSERT INTO `tblfinancialperiodtransaction` VALUES (1,1,1,'2024-12-10',1,1,'Receipt'),(2,1,1,'2024-12-10',2,1,'Contra'),(3,1,1,'2024-12-10',3,1,'Payment'),(4,1,1,'2024-12-10',4,2,'Receipt'),(5,1,1,'2024-12-10',5,2,'Payment'),(6,1,1,'2024-12-10',6,3,'Receipt'),(8,1,1,'2024-12-14',7,1,'SALES'),(9,1,1,'2024-12-14',8,2,'Sales'),(10,1,1,'2024-12-14',9,3,'Sales'),(11,1,1,'2024-12-14',10,4,'Sales'),(12,1,1,'2024-12-14',11,5,'Sales'),(18,1,1,'2024-12-20',0,0,'Payment'),(20,1,1,'2024-12-20',0,0,'Receipt'),(21,1,1,'2024-12-20',0,0,'Receipt'),(22,1,1,'2024-12-20',0,0,'Receipt'),(23,1,1,'2024-12-20',0,0,'Receipt'),(24,1,1,'2024-12-20',0,0,'Receipt'),(25,1,1,'2024-12-20',0,0,'Receipt'),(26,1,1,'2024-12-20',0,0,'Receipt'),(27,1,1,'2024-12-20',0,0,'Receipt'),(28,1,1,'2024-12-20',0,0,'Receipt'),(29,1,1,'2024-12-20',0,0,'Receipt'),(30,1,1,'2024-12-20',0,0,'Receipt'),(31,1,1,'2024-12-20',0,0,'Receipt'),(32,1,1,'2024-12-20',0,0,'Receipt'),(33,1,1,'2024-12-21',0,0,'Receipt'),(34,1,1,'2024-12-21',0,0,'Receipt'),(35,1,1,'2024-12-21',0,0,'Receipt'),(36,1,1,'2024-12-21',0,0,'Receipt'),(37,1,1,'2024-12-21',0,0,'Receipt'),(38,1,1,'2024-12-21',0,0,'Receipt'),(39,1,1,'2024-12-21',0,0,'Receipt'),(40,1,1,'2024-12-21',0,0,'Receipt'),(41,1,1,'2024-12-21',0,0,'Receipt'),(42,1,1,'2024-12-21',0,0,'Receipt'),(43,1,1,'2024-12-21',0,0,'Receipt'),(44,1,1,'2024-12-21',12,4,'Receipt'),(45,1,1,'2024-12-23',13,5,'Receipt'),(47,1,1,'2024-12-23',14,6,'Receipt'),(48,1,1,'2024-12-23',15,7,'Receipt'),(49,1,1,'2024-12-23',16,8,'Receipt'),(50,1,1,'2024-12-23',17,9,'Receipt'),(51,1,1,'2024-12-23',18,10,'Receipt'),(52,1,1,'2024-12-23',19,11,'Receipt'),(53,1,1,'2024-12-23',20,6,'Sales'),(54,1,1,'2024-12-23',21,7,'Sales'),(55,1,1,'2024-12-23',22,8,'Sales'),(57,1,1,'2024-12-23',23,12,'Receipt'),(60,1,1,'2024-12-27',24,1,'Purchase'),(63,1,1,'2024-12-27',25,2,'Purchase'),(64,1,1,'2024-12-27',26,3,'Purchase'),(65,1,1,'2024-12-27',27,4,'Purchase'),(66,1,1,'2024-12-27',28,5,'Purchase'),(67,1,1,'2024-12-27',29,6,'Purchase'),(68,1,1,'2024-12-27',30,7,'Purchase'),(69,1,1,'2024-12-27',31,8,'Purchase'),(71,1,1,'2024-12-31',32,9,'Purchase');
/*!40000 ALTER TABLE `tblfinancialperiodtransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblledger`
--

DROP TABLE IF EXISTS `tblledger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblledger` (
  `ledger_id` int NOT NULL AUTO_INCREMENT,
  `tds_applicable` int NOT NULL,
  `account` varchar(255) DEFAULT NULL,
  `account_nature` varchar(255) DEFAULT NULL,
  `account_id` int NOT NULL,
  `company_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int NOT NULL,
  `is_active` bit(1) NOT NULL,
  `ledger_name` varchar(255) DEFAULT NULL,
  `order_by_number` int NOT NULL,
  `system_default` bit(1) NOT NULL,
  PRIMARY KEY (`ledger_id`),
  KEY `FKmn02910mw1hjdf6aqxkirylrw` (`account_id`),
  KEY `FKaph71glv4wsbplnr5mdp222xa` (`company_id`),
  CONSTRAINT `FKaph71glv4wsbplnr5mdp222xa` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`),
  CONSTRAINT `FKmn02910mw1hjdf6aqxkirylrw` FOREIGN KEY (`account_id`) REFERENCES `tblaccount` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblledger`
--

LOCK TABLES `tblledger` WRITE;
/*!40000 ALTER TABLE `tblledger` DISABLE KEYS */;
INSERT INTO `tblledger` VALUES (1,1,'FIXED ASSETS','ASSETS',2,0,'2024-11-30 16:47:09.336557',1,_binary '','Computers',1,_binary ''),(2,1,'FIXED ASSETS','ASSETS',2,2,'2024-11-30 16:48:19.620567',1,_binary '','Furniture & Fixture',1,_binary ''),(3,1,'FIXED ASSETS','ASSETS',2,0,'2024-11-30 16:48:37.533883',1,_binary '','Shop Equipment',1,_binary ''),(4,1,'Sundry Debtors','CURRENT ASSETS',3,0,'2024-11-30 16:49:25.720904',1,_binary '','Deposits',1,_binary ''),(5,1,'Sundry Debtors','CURRENT ASSETS',3,0,'2024-11-30 16:49:41.090937',1,_binary '','Prepaid Expenses',1,_binary ''),(6,1,'Sundry Debtors','CURRENT ASSETS',3,0,'2024-11-30 16:49:58.520531',1,_binary '','Account Receivable',1,_binary ''),(7,1,'INVENTORIES','ASSETS',5,0,'2024-11-30 16:50:42.628041',1,_binary '','Closing Stock',1,_binary ''),(8,1,'INVENTORIES','ASSETS',5,0,'2024-11-30 16:50:59.843211',1,_binary '','Daily Production',1,_binary ''),(9,1,'INVESTMENT ASSETS','ASSETS',6,0,'2024-11-30 16:51:47.434593',1,_binary '','Advance against Salary',1,_binary ''),(10,1,'INVESTMENT ASSETS','ASSETS',6,0,'2024-11-30 16:52:07.604469',1,_binary '','Festival Advances',1,_binary ''),(11,1,'OTHER ASSETS','ASSETS',7,0,'2024-11-30 16:53:00.419077',1,_binary '','Loan to Employees',1,_binary ''),(12,1,'Bank Accounts','CASH & BANK BALANCES',9,0,'2024-11-30 16:53:41.698429',1,_binary '','Cash-In-Hand',1,_binary ''),(13,1,'CAPITAL ACCOUNT','LIABILITIES',12,0,'2024-11-30 16:54:23.705046',1,_binary '','Paid up Capital A/c',1,_binary ''),(14,1,'Sundry Creditors','CURRENT LIABILITIES',13,0,'2024-11-30 16:55:14.612438',1,_binary '','Credtit Notes Payable',1,_binary ''),(15,1,'Sundry Creditors','CURRENT LIABILITIES',13,0,'2024-11-30 16:55:39.175987',1,_binary '','Bad Debt Fund',1,_binary ''),(16,1,'Sundry Creditors','CURRENT LIABILITIES',13,0,'2024-11-30 16:56:01.205791',1,_binary '','Accounts Payable',1,_binary ''),(17,1,'Sundry Creditors','CURRENT LIABILITIES',13,0,'2024-11-30 16:56:17.870888',1,_binary '','Loan from Bank',1,_binary ''),(18,1,'DUTIES & TAXES','LIABILITIES',15,0,'2024-11-30 16:57:11.531134',1,_binary '','CGST Payable',1,_binary ''),(19,1,'DUTIES & TAXES','LIABILITIES',15,0,'2024-11-30 16:57:26.265493',1,_binary '','SGST Payable',1,_binary ''),(20,1,'DUTIES & TAXES','LIABILITIES',15,0,'2024-11-30 16:57:43.546464',1,_binary '','IGST Payable',1,_binary ''),(21,1,'DUTIES & TAXES','LIABILITIES',15,0,'2024-11-30 16:57:58.814990',1,_binary '','Development Tax',1,_binary ''),(22,1,'DUTIES & TAXES','LIABILITIES',15,0,'2024-11-30 16:58:14.406103',1,_binary '','TDS Payable',1,_binary ''),(23,1,'DUTIES & TAXES','LIABILITIES',15,0,'2024-11-30 16:58:28.427269',1,_binary '','GST Payable',1,_binary ''),(24,1,'LIABILITIES & PROVISION','LIABILITIES',16,0,'2024-11-30 16:58:56.999756',1,_binary '','Provision for Computer Purchase',1,_binary ''),(25,1,'LIABILITIES & PROVISION','LIABILITIES',16,0,'2024-11-30 16:59:14.931874',1,_binary '','Bank OD A/c',1,_binary ''),(26,1,'OTHER LIABILITIES','LIABILITIES',17,0,'2024-11-30 16:59:43.351773',1,_binary '','Suspence A/C',1,_binary ''),(27,1,'OTHER LIABILITIES','LIABILITIES',17,0,'2024-11-30 17:00:00.397084',1,_binary '','PF Account',1,_binary ''),(28,1,'PROFIT & LOSS A/C','LIABILITIES',18,0,'2024-11-30 17:00:37.630593',1,_binary '','Profit & Loss A/c',1,_binary ''),(29,1,'PROFIT & LOSS A/C','LIABILITIES',18,0,'2024-11-30 17:00:52.603296',1,_binary '','Difference in opening Balance',1,_binary ''),(43,1,'Sundry Debtors','CURRENT ASSETS',4,1,'2024-12-11 11:51:48.917716',1,_binary '','Vivek Singh',0,_binary '\0'),(44,0,'Bank Accounts','CASH & BANK BALANCES',9,1,'2024-12-20 22:05:54.744003',1,_binary '\0','Punjab National Bank',1,_binary ''),(45,0,'Bank Accounts','CASH & BANK BALANCES',9,1,'2024-12-20 22:07:36.605491',1,_binary '\0','State Bank Of India',1,_binary ''),(46,0,'Bank Accounts','CASH & BANK BALANCES',9,1,'2024-12-20 22:11:45.582025',1,_binary '\0','Cash',1,_binary ''),(47,1,'Sundry Debtors','CURRENT ASSETS',4,1,'2024-12-24 16:14:49.595666',1,_binary '','Smith',0,_binary '\0'),(48,0,'PURCHASE ACCOUNTS','EXPENDITURE',25,1,'2024-12-26 16:37:31.302091',1,_binary '\0','Purchase A/c',1,_binary '');
/*!40000 ALTER TABLE `tblledger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblmanufacturer`
--

DROP TABLE IF EXISTS `tblmanufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblmanufacturer` (
  `manufacturer_id` int NOT NULL AUTO_INCREMENT,
  `company_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`manufacturer_id`),
  KEY `FK1atjw6b835dj1fx8u880awgof` (`company_id`),
  CONSTRAINT `FK1atjw6b835dj1fx8u880awgof` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblmanufacturer`
--

LOCK TABLES `tblmanufacturer` WRITE;
/*!40000 ALTER TABLE `tblmanufacturer` DISABLE KEYS */;
INSERT INTO `tblmanufacturer` VALUES (1,1,'2024-11-18 14:07:47.594535',1,'abcd','Technix'),(2,1,'2024-11-18 14:07:57.308801',1,'abcd','TCS'),(3,2,'2024-11-18 14:08:10.588535',1,'abcd','Wipro'),(4,2,'2024-11-18 14:08:24.367090',1,'abcd','Infotech');
/*!40000 ALTER TABLE `tblmanufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblopeningbalance`
--

DROP TABLE IF EXISTS `tblopeningbalance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblopeningbalance` (
  `row_id` int NOT NULL AUTO_INCREMENT,
  `company_id` int DEFAULT NULL,
  `cr_dr` varchar(255) DEFAULT NULL,
  `financial_period_id` int NOT NULL,
  `ledger_id` int NOT NULL,
  `opening_balance` double NOT NULL,
  `opening_balance_date` date DEFAULT NULL,
  PRIMARY KEY (`row_id`),
  KEY `FKq9xs3y42vho8hivkdt1mgatfb` (`company_id`),
  CONSTRAINT `FKq9xs3y42vho8hivkdt1mgatfb` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblopeningbalance`
--

LOCK TABLES `tblopeningbalance` WRITE;
/*!40000 ALTER TABLE `tblopeningbalance` DISABLE KEYS */;
INSERT INTO `tblopeningbalance` VALUES (2,1,'Cr',1,47,10000,'2024-12-24');
/*!40000 ALTER TABLE `tblopeningbalance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpayment`
--

DROP TABLE IF EXISTS `tblpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblpayment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `amount_due` double NOT NULL,
  `bank_ledger_id` int NOT NULL,
  `bill_amount` double NOT NULL,
  `branch_id` int NOT NULL,
  `cheque_date` varchar(255) DEFAULT NULL,
  `cheque_no` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `contact_id` int NOT NULL,
  `contact_name` varchar(255) DEFAULT NULL,
  `created_by` int NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `due_date` varchar(255) DEFAULT NULL,
  `financial_period_id` int NOT NULL,
  `invoice_date` varchar(255) DEFAULT NULL,
  `invoice_no` varchar(255) DEFAULT NULL,
  `ledger_id` int NOT NULL,
  `payment` double NOT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `receipt_no` int NOT NULL,
  `ref_no` varchar(255) DEFAULT NULL,
  `reference_no` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `total_payment` double NOT NULL,
  `transaction_date` date DEFAULT NULL,
  `transaction_id` int DEFAULT NULL,
  `voucher_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FKk0hd4ud2lfi94gf3wcw8164ha` (`company_id`),
  KEY `FKi06iclyyay19ldx7yc0ll3au4` (`transaction_id`),
  CONSTRAINT `FKi06iclyyay19ldx7yc0ll3au4` FOREIGN KEY (`transaction_id`) REFERENCES `tblfinancialperiodtransaction` (`transaction_id`),
  CONSTRAINT `FKk0hd4ud2lfi94gf3wcw8164ha` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpayment`
--

LOCK TABLES `tblpayment` WRITE;
/*!40000 ALTER TABLE `tblpayment` DISABLE KEYS */;
INSERT INTO `tblpayment` VALUES (1,500,12,1000,1,NULL,NULL,1,1,'Vivek Singh',1,'2024-12-23 10:59:55.601682','2024-12-30',1,'2024-12-15',NULL,43,500,'Cash',1,'REF123','Ref123','Payment for invoice',500,'2024-12-23',51,'Receipt'),(2,200,12,1000,1,NULL,NULL,1,1,'Vivek Singh',1,'2024-12-23 10:59:55.652023','2024-12-30',1,'2024-12-15',NULL,43,300,'UPI',1,'REF123','Ref123','Payment for invoice',300,'2024-12-23',51,'Receipt'),(7,4500,12,5000,1,NULL,NULL,1,1,'Vivek Singh',1,'2024-12-23 15:18:18.069443','2024-12-30',1,'2024-12-15','INV123',43,500,'Cash',0,'REF123','Ref123','Payment for invoice',500,'2024-12-23',52,'Receipt'),(8,4200,12,5000,1,NULL,NULL,1,1,'Vivek Singh',1,'2024-12-23 15:18:18.194487','2024-12-30',1,'2024-12-15','INV123',43,300,'UPI',0,'REF123','Ref123','Payment for invoice',800,'2024-12-23',52,'Receipt'),(9,4500,12,5000,1,NULL,NULL,1,1,'Vivek Singh',1,'2024-12-23 16:37:19.841617','2024-12-30',1,'2024-12-15','TAX/24-25/1',43,500,'Cash',2,'REF123','Ref123','Payment for invoice',800,'2024-12-23',57,'Receipt'),(10,4200,12,5000,1,NULL,NULL,1,1,'Vivek Singh',1,'2024-12-23 16:37:19.880714','2024-12-30',1,'2024-12-15','TAX/24-25/1',43,300,'UPI',2,'REF123','Ref123','Payment for invoice',800,'2024-12-23',57,'Receipt');
/*!40000 ALTER TABLE `tblpayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblplan`
--

DROP TABLE IF EXISTS `tblplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblplan` (
  `plan_id` int NOT NULL AUTO_INCREMENT,
  `currency` varchar(255) DEFAULT NULL,
  `features` longtext,
  `plan_name` varchar(255) DEFAULT NULL,
  `plan_type` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `user_count` int NOT NULL,
  `validity` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '0',
  `trial_available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblplan`
--

LOCK TABLES `tblplan` WRITE;
/*!40000 ALTER TABLE `tblplan` DISABLE KEYS */;
INSERT INTO `tblplan` VALUES (1,'INR','Subscription For Single User|\r Manage Single Business|\r Create 5000 Invoices|\r All in One Dashboard|\r Manage Inventory with Accounts|\r Access on Browser, Android, IOS|\r Customize Invoice Theme|\r Generate & Print Barcode|\r Bulk Download & Print Invoice|\r Email, WhatsApp & SMS Notification|\r Import, Export Feature|\r Multiple Taxes|\r Dues Reminder Notification|\r Reports\r ','Standard','Monthly',599,1,30,'The perfect choice for Startup business',1,1),(2,'INR','Subscription For 3 Users|\r Manage 2 Branch|\r Create 15000 Invoices|\r E-way Bill|\r E-Invoicing|\r POS Billing|\r Manage Warehouse|\r Recurring Payment|\r API based Tax Filling\r ','Professional','Monthly',999,3,30,'Everything in Standard, Plus:',0,0),(3,'INR','Subscription for Multi Users|\r Manage Multiple Business|\r Multiple Branch|\r Create Unlimited Invoices|\r Data Backup|\r Multi Currency','Enterprise','Monthly',NULL,100,30,'Everything in Professinal, Plus:',0,0),(4,'INR','Subscription For Single User|\r Manage Single Business|\r Create 5000 Invoices|\r All in One Dashboard|\r Manage Inventory with Accounts|\r Access on Browser, Android, IOS|\r Customize Invoice Theme|\r Generate & Print Barcode|\r Bulk Download & Print Invoice|\r Email, WhatsApp & SMS Notification|\r Import, Export Feature|\r Multiple Taxes|\r Dues Reminder Notification|\r Reports\r ','Standard','Yearly',7999,1,365,'The perfect choice for Startup business',0,1),(5,'INR','Subscription For 3 Users|\r Manage 2 Branch|\r Create 15000 Invoices|\r E-way Bill|\r E-Invoicing|\r POS Billing|\r Manage Warehouse|\r Recurring Payment|\r API based Tax Filling\r ','Professional','Yearly',9999,3,365,'Everything in Standard, Plus:',0,0),(6,'INR','Subscription for Multi Users|\r Manage Multiple Business|\r Multiple Branch|\r Create Unlimited Invoices|\r Data Backup|\r Multi Currency\r ','Enterprise','Yearly',NULL,100,365,'Everything in Professinal, Plus:',0,0);
/*!40000 ALTER TABLE `tblplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblproduct`
--

DROP TABLE IF EXISTS `tblproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblproduct` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `hsn_code` varchar(255) DEFAULT NULL,
  `uqccode` varchar(255) DEFAULT NULL,
  `alt_unit_required` bit(1) NOT NULL,
  `barcode` varchar(255) DEFAULT NULL,
  `bin` varchar(255) DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `conversion_factor` double NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `dealer_price` double NOT NULL,
  `default_sales_unit` varchar(255) DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount_per` double NOT NULL,
  `distributor_price` double NOT NULL,
  `id_taxable` bit(1) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `manage_item_by` bit(1) NOT NULL,
  `management_method` bit(1) NOT NULL,
  `manufacturer_id` int DEFAULT NULL,
  `material_type` varchar(255) DEFAULT NULL,
  `model_no` varchar(255) DEFAULT NULL,
  `mrp` double NOT NULL,
  `opening_stock` double NOT NULL,
  `opening_value` double NOT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_type` varchar(255) DEFAULT NULL,
  `purchase_rate` double NOT NULL,
  `recorder_point` int NOT NULL,
  `secondary_unituqc` varchar(255) DEFAULT NULL,
  `selling_rate` double NOT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `sku` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `supplier_id` int DEFAULT NULL,
  `tax_id` varchar(255) DEFAULT NULL,
  `tax_per` double NOT NULL,
  `taxation_type` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `unit_rate` double NOT NULL,
  `warranty_period` int NOT NULL,
  `wholesale_price` double NOT NULL,
  `uqc_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FKkd23lpe3tdtoryucf8xnry2yt` (`brand_id`),
  KEY `FKdkv2t4qeyh423fih75o33drpq` (`category_id`),
  KEY `FKlwkysu8vhk14mgwhg6ntyyihr` (`company_id`),
  KEY `FKnvwdu0ywdy0undhf64mpykmmw` (`department_id`),
  KEY `FKi1hppipak2mp0ebs4sebolpnx` (`manufacturer_id`),
  KEY `FKedgfv9vi61498g3fqfikmjcqk` (`supplier_id`),
  CONSTRAINT `FKdkv2t4qeyh423fih75o33drpq` FOREIGN KEY (`category_id`) REFERENCES `tblcategory` (`category_id`),
  CONSTRAINT `FKedgfv9vi61498g3fqfikmjcqk` FOREIGN KEY (`supplier_id`) REFERENCES `tblsupplier` (`supplier_id`),
  CONSTRAINT `FKi1hppipak2mp0ebs4sebolpnx` FOREIGN KEY (`manufacturer_id`) REFERENCES `tblmanufacturer` (`manufacturer_id`),
  CONSTRAINT `FKkd23lpe3tdtoryucf8xnry2yt` FOREIGN KEY (`brand_id`) REFERENCES `tblbrands` (`brand_id`),
  CONSTRAINT `FKlwkysu8vhk14mgwhg6ntyyihr` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`),
  CONSTRAINT `FKnvwdu0ywdy0undhf64mpykmmw` FOREIGN KEY (`department_id`) REFERENCES `tbldepartments` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblproduct`
--

LOCK TABLES `tblproduct` WRITE;
/*!40000 ALTER TABLE `tblproduct` DISABLE KEYS */;
INSERT INTO `tblproduct` VALUES (1,'Tech234','XYZ2314',_binary '','barcode','this is bin',1,1,'Red',1,1,'Technix India','2024-11-21 12:30:35.673485',5000,'abc',1,'This is enterprise application',5.5,7000,_binary '','/product/1/1.jpg',_binary '',_binary '',1,'Development','Model123',10000,5.5,2500,'Business Book','Software',8000,1,'abc',3000,'BB Plus','5','sku data','active',1,'TAX123',5.5,'GST','one',2000,5,4000,NULL),(3,'Tech234','TSP2314',_binary '','barcode','this is bin',1,1,'Red',1,1,'Technix','2024-11-22 10:17:38.097879',5000,'abc',1,'This is enterprise application',5.5,7000,_binary '','/product/3/3.jpg',_binary '',_binary '',1,'Cloths','Model123',10000,5.5,2500,'Flipkart','Cloths',8000,1,'abc',3000,'f kart','5','sku data','active',1,'TAX123',5.5,'GST','one',2000,5,4000,NULL);
/*!40000 ALTER TABLE `tblproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpurchase`
--

DROP TABLE IF EXISTS `tblpurchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblpurchase` (
  `purchase_id` int NOT NULL AUTO_INCREMENT,
  `branch_id` int NOT NULL,
  `company_id` int DEFAULT NULL,
  `contact_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int NOT NULL,
  `disc_per` double NOT NULL,
  `discount` double NOT NULL,
  `due_date` date DEFAULT NULL,
  `global_disc` double NOT NULL,
  `global_disc_per` double NOT NULL,
  `grand_total` double NOT NULL,
  `invoice_date` date DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `other_charges` double NOT NULL,
  `po_date` date DEFAULT NULL,
  `po_no` varchar(255) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `purchase_no` varchar(255) DEFAULT NULL,
  `reference_no` varchar(255) DEFAULT NULL,
  `round_off` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `sub_total` double NOT NULL,
  `taxation_type` varchar(255) DEFAULT NULL,
  `total_taxes` double NOT NULL,
  `transaction_id` int NOT NULL,
  PRIMARY KEY (`purchase_id`),
  KEY `FKk1vh1kwd0csih58if0icy970m` (`company_id`),
  KEY `FK5v7iatmwx57172ymraa0c1jfy` (`contact_id`),
  CONSTRAINT `FK5v7iatmwx57172ymraa0c1jfy` FOREIGN KEY (`contact_id`) REFERENCES `tblcontacts` (`contact_id`),
  CONSTRAINT `FKk1vh1kwd0csih58if0icy970m` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpurchase`
--

LOCK TABLES `tblpurchase` WRITE;
/*!40000 ALTER TABLE `tblpurchase` DISABLE KEYS */;
INSERT INTO `tblpurchase` VALUES (2,0,1,1,'2024-12-31 11:58:25.423586',1,20,1.1,'2024-12-27',10,1281.8,12818,'2024-12-27','Note',0,NULL,'','2024-12-27','24-25/1','TAX/24-25/50',0.3,'Unpaid',13152.25,'',1714.46,71);
/*!40000 ALTER TABLE `tblpurchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpurchase_expenses`
--

DROP TABLE IF EXISTS `tblpurchase_expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblpurchase_expenses` (
  `row_id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `charges_id` int DEFAULT NULL,
  `ledger_id` int DEFAULT NULL,
  `percent` double DEFAULT NULL,
  `purchase_id` int DEFAULT NULL,
  `tax_id` int DEFAULT NULL,
  `value_of_field` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`row_id`),
  KEY `FK3x12b10i9g13g532vsdbgssf1` (`purchase_id`),
  CONSTRAINT `FK3x12b10i9g13g532vsdbgssf1` FOREIGN KEY (`purchase_id`) REFERENCES `tblpurchase` (`purchase_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpurchase_expenses`
--

LOCK TABLES `tblpurchase_expenses` WRITE;
/*!40000 ALTER TABLE `tblpurchase_expenses` DISABLE KEYS */;
INSERT INTO `tblpurchase_expenses` VALUES (1,0,0,0,0,3,0,''),(3,0,0,0,0,2,0,''),(4,0,0,0,0,3,0,''),(5,0,0,0,0,4,0,''),(6,0,0,0,0,5,0,''),(7,0,0,0,0,6,0,''),(8,0,0,0,0,7,0,''),(16,0,0,0,0,2,0,'');
/*!40000 ALTER TABLE `tblpurchase_expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpurchase_particulars`
--

DROP TABLE IF EXISTS `tblpurchase_particulars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblpurchase_particulars` (
  `row_id` int NOT NULL AUTO_INCREMENT,
  `alternate_unit` varchar(255) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `billed_qty` double DEFAULT NULL,
  `branch_id` int DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `conv_factor` double DEFAULT NULL,
  `disc_per` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `free_qty` double DEFAULT NULL,
  `hsn_code` varchar(255) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `purchase_id` int DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `tax_id` int DEFAULT NULL,
  `tax_per` double DEFAULT NULL,
  `tax_type` varchar(255) DEFAULT NULL,
  `taxable_value` double DEFAULT NULL,
  `taxation_type` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`row_id`),
  KEY `FKf8bkd80pnr6sbky9luf93nwbs` (`company_id`),
  KEY `FK9lm0f12kp69uw23apvwyrtwlk` (`product_id`),
  KEY `FKdd6qk6nkvo2rb8ujror395q3t` (`purchase_id`),
  CONSTRAINT `FK9lm0f12kp69uw23apvwyrtwlk` FOREIGN KEY (`product_id`) REFERENCES `tblproduct` (`product_id`),
  CONSTRAINT `FKdd6qk6nkvo2rb8ujror395q3t` FOREIGN KEY (`purchase_id`) REFERENCES `tblpurchase` (`purchase_id`),
  CONSTRAINT `FKf8bkd80pnr6sbky9luf93nwbs` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpurchase_particulars`
--

LOCK TABLES `tblpurchase_particulars` WRITE;
/*!40000 ALTER TABLE `tblpurchase_particulars` DISABLE KEYS */;
INSERT INTO `tblpurchase_particulars` VALUES (1,'box',8960,14,0,1,1,20,1.1,0,'',1,'2024-12-27',3,14,800,1,18,'',7593.22,'Inclusive','PICCS'),(3,'box',8960,14,0,1,1,20,1.1,0,'',1,'2024-12-27',2,14,800,1,18,'',7593.22,'Inclusive','PICCS'),(4,'box',8960,14,0,1,1,20,1.1,0,'',1,'2024-12-27',3,14,800,1,18,'',7593.22,'Inclusive','PICCS'),(5,'box',8960,14,0,1,1,20,1.1,0,'',1,'2024-12-27',4,14,800,1,18,'',7593.22,'Inclusive','PICCS'),(6,'box',8960,14,0,1,1,20,1.1,0,'',1,'2024-12-27',5,14,800,1,18,'',7593.22,'Inclusive','PICCS'),(7,'box',8960,14,0,1,1,20,1.1,0,'',1,'2024-12-27',6,14,800,1,18,'',7593.22,'Inclusive','PICCS'),(8,'box',8960,14,0,1,1,20,1.1,0,'',1,'2024-12-27',7,14,800,1,18,'',7593.22,'Inclusive','PICCS'),(16,'box',8960,14,0,1,1,20,1.1,0,'',1,'2024-12-27',2,14,800,1,18,'',7593.22,'Inclusive','PICCS');
/*!40000 ALTER TABLE `tblpurchase_particulars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpurchase_product_taxes`
--

DROP TABLE IF EXISTS `tblpurchase_product_taxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblpurchase_product_taxes` (
  `row_id` int NOT NULL AUTO_INCREMENT,
  `purchase_date` date DEFAULT NULL,
  `purchase_id` int DEFAULT NULL,
  `tax_amount` double DEFAULT NULL,
  `tax_id` int DEFAULT NULL,
  `tax_name` varchar(255) DEFAULT NULL,
  `tax_per` double DEFAULT NULL,
  `tax_type` varchar(255) DEFAULT NULL,
  `taxable_value` double DEFAULT NULL,
  PRIMARY KEY (`row_id`),
  KEY `FKmfjdwj7xt6p0srt0hl7oyx3k8` (`purchase_id`),
  CONSTRAINT `FKmfjdwj7xt6p0srt0hl7oyx3k8` FOREIGN KEY (`purchase_id`) REFERENCES `tblpurchase` (`purchase_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpurchase_product_taxes`
--

LOCK TABLES `tblpurchase_product_taxes` WRITE;
/*!40000 ALTER TABLE `tblpurchase_product_taxes` DISABLE KEYS */;
INSERT INTO `tblpurchase_product_taxes` VALUES (1,'2024-12-27',3,1366.78,1,'Inclusive',12,'GST',2459.82),(3,'2024-12-27',2,1366.78,1,'Inclusive',12,'GST',2459.82),(4,'2024-12-27',3,1366.78,1,'Inclusive',12,'GST',2459.82),(5,'2024-12-27',4,1366.78,1,'Inclusive',12,'GST',2459.82),(6,'2024-12-27',5,1366.78,1,'Inclusive',12,'GST',2459.82),(7,'2024-12-27',6,1366.78,1,'Inclusive',12,'GST',2459.82),(8,'2024-12-27',7,1366.78,1,'Inclusive',12,'GST',2459.82),(16,'2024-12-27',2,1366.78,1,'Inclusive',12,'GST',2459.82);
/*!40000 ALTER TABLE `tblpurchase_product_taxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblsupplier`
--

DROP TABLE IF EXISTS `tblsupplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblsupplier` (
  `supplier_id` int NOT NULL AUTO_INCREMENT,
  `company_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `supplier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`),
  KEY `FKeqowbd140r52i5xxoi0lluomr` (`company_id`),
  CONSTRAINT `FKeqowbd140r52i5xxoi0lluomr` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblsupplier`
--

LOCK TABLES `tblsupplier` WRITE;
/*!40000 ALTER TABLE `tblsupplier` DISABLE KEYS */;
INSERT INTO `tblsupplier` VALUES (1,1,'2024-11-18 14:06:15.548656',1,'abcd','Technix'),(2,2,'2024-11-18 14:06:30.271997',1,'abcd','TCS'),(3,2,'2024-11-18 14:06:38.362726',1,'abcd','Wipro'),(4,3,'2024-11-18 14:06:51.789083',1,'abcd','Amazon'),(5,2,'2024-11-22 09:15:53.309978',1,'shirt','Amazon');
/*!40000 ALTER TABLE `tblsupplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltransaction`
--

DROP TABLE IF EXISTS `tbltransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbltransaction` (
  `row_id` int NOT NULL AUTO_INCREMENT,
  `branch_id` int NOT NULL,
  `cheque_date` date DEFAULT NULL,
  `cheque_no` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `confirm` varchar(255) DEFAULT NULL,
  `confirmation_date` date DEFAULT NULL,
  `confirmed_by` int NOT NULL,
  `created_by` int NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `credit` double NOT NULL,
  `d_bcr` varchar(255) DEFAULT NULL,
  `debit` double NOT NULL,
  `financial_period_id` int NOT NULL,
  `is_bank_account` int NOT NULL,
  `ledger_id` int NOT NULL,
  `ledger_name` varchar(255) DEFAULT NULL,
  `narration` varchar(255) DEFAULT NULL,
  `particulars` varchar(255) DEFAULT NULL,
  `particulars_id` int NOT NULL,
  `payment_mode` varchar(255) DEFAULT NULL,
  `ref_no` varchar(255) DEFAULT NULL,
  `transaction_date` date DEFAULT NULL,
  `transaction_id` int NOT NULL,
  `transaction_no` int NOT NULL,
  `voucher_no` int NOT NULL,
  `voucher_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`row_id`),
  KEY `FKghlcnmirw9a1brrsq3024018s` (`company_id`),
  CONSTRAINT `FKghlcnmirw9a1brrsq3024018s` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltransaction`
--

LOCK TABLES `tbltransaction` WRITE;
/*!40000 ALTER TABLE `tbltransaction` DISABLE KEYS */;
INSERT INTO `tbltransaction` VALUES (1,1,'2024-12-10','CH123',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',0,'Dr',10000,1,1,37,'Cheque','This is payment narration.',NULL,0,'Cheque','REF123','2024-12-10',1,1,1,'Receipt'),(2,1,'2024-12-10','CH123',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',10000,'Cr',0,1,1,35,'Capital Account','This is payment narration.','',1,'Cheque','REF123','2024-12-10',1,1,1,'Receipt'),(3,2,NULL,'',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',0,'Dr',15000,1,1,12,'Cash-In-Hand','This is contra narration.',NULL,0,'Cash','','2024-12-10',2,2,1,'Contra'),(4,2,NULL,'',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',15000,'Cr',0,1,1,36,'Bank Account','This is contra narration.','',1,'Cash','','2024-12-10',2,2,1,'Contra'),(5,2,NULL,'',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',3000,'Cr',0,1,1,12,'Cash-In-Hand','This is Payment narration.',NULL,0,'Cash','','2024-12-10',3,3,1,'Payment'),(6,2,NULL,'',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',0,'Dr',3000,1,1,34,'Furniture','This is Payment narration.','',1,'Cash','','2024-12-10',3,3,1,'Payment'),(7,2,NULL,'',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',0,'Dr',3000,1,1,12,'Cash-In-Hand','This is receipt narration.',NULL,0,'Cash','','2024-12-10',4,4,2,'Receipt'),(8,2,NULL,'',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',2000,'Cr',0,1,1,32,'Shubham','This is receipt narration.','',1,'Cash','','2024-12-10',4,4,2,'Receipt'),(9,2,NULL,'',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',1000,'Cr',0,1,1,12,'Cash-In-Hand','This is payment narration.',NULL,0,'Cash','','2024-12-10',5,5,2,'Payment'),(10,2,NULL,'',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',0,'Dr',1000,1,1,31,'Kunal','This is payment narration.','',1,'Cash','','2024-12-10',5,5,2,'Payment'),(11,2,NULL,'',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',0,'Dr',50,1,1,12,'Cash-In-Hand','This is Receipt narration.',NULL,0,'Cash','','2024-12-10',6,6,3,'Receipt'),(12,2,NULL,'',1,'1',NULL,0,1,'2024-12-10 00:00:00.000000',50,'Cr',0,1,1,39,'Commision a/c','This is Receipt narration.','',1,'Cash','','2024-12-10',6,6,3,'Receipt'),(21,1,NULL,NULL,1,'1','2024-12-14',1,1,'2024-12-14 00:00:00.000000',3479,'Cr',0,1,0,20,'Sales A/c','By sales invoice no. INV123','Sales A/c',20,'By credit','Ref123','2024-12-14',11,10,4,'Sales'),(22,1,NULL,NULL,1,'1','2024-12-14',1,1,'2024-12-14 00:00:00.000000',0,'Dr',3479,1,0,43,'Vivek Singh','To sales invoice no. INV123','Vivek Singh',1,'By credit','Ref123','2024-12-14',11,10,4,'Sales'),(23,1,NULL,NULL,1,'1','2024-12-14',1,1,'2024-12-14 16:16:07.955885',3479,'Cr',0,1,0,20,'Sales A/c','By sales invoice no. INV123','Sales A/c',20,'By credit','Ref123','2024-12-14',12,11,5,'Sales'),(24,1,NULL,NULL,1,'1','2024-12-14',1,1,'2024-12-14 16:16:07.979746',0,'Dr',3479,1,0,43,'Vivek Singh','To sales invoice no. INV123','Vivek Singh',1,'By credit','Ref123','2024-12-14',12,11,5,'Sales'),(25,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 11:01:20.317001',0,'Dr',5000,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',18,0,0,'Payment'),(26,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 11:01:20.348319',5000,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',18,0,0,'Payment'),(27,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 12:37:39.066741',0,'Dr',500,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',20,0,0,'Receipt'),(28,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 12:37:39.115268',0,'Dr',400,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',20,0,0,'Receipt'),(29,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 12:37:39.132073',400,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',20,0,0,'Receipt'),(30,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 12:40:25.346503',0,'Dr',500,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',21,0,0,'Receipt'),(31,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 12:40:25.349296',0,'Dr',400,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',21,0,0,'Receipt'),(32,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 12:40:25.373119',400,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',21,0,0,'Receipt'),(33,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 14:48:14.884536',0,'Dr',500,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',22,0,0,'Receipt'),(34,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 14:48:14.909512',0,'Dr',400,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',22,0,0,'Receipt'),(35,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 14:48:14.917859',900,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',22,0,0,'Receipt'),(36,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 14:55:46.316527',0,'Dr',500,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',23,0,0,'Receipt'),(37,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 14:55:46.331571',0,'Dr',400,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',23,0,0,'Receipt'),(38,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 14:55:46.339587',900,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',23,0,0,'Receipt'),(39,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 15:37:58.978729',0,'Dr',500,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',24,0,0,'Receipt'),(40,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 15:37:59.018625',0,'Dr',400,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',24,0,0,'Receipt'),(41,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 15:37:59.026672',900,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',24,0,0,'Receipt'),(42,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 15:40:40.389836',0,'Dr',500,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',25,0,0,'Receipt'),(43,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 15:40:40.414013',0,'Dr',400,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',25,0,0,'Receipt'),(44,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 15:40:40.423560',900,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',25,0,0,'Receipt'),(45,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 16:12:28.749645',0,'Dr',500,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',26,0,0,'Receipt'),(46,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 16:12:28.777168',0,'Dr',400,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',26,0,0,'Receipt'),(47,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 16:12:28.783952',900,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',26,0,0,'Receipt'),(48,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 16:22:47.124136',0,'Dr',500,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',27,0,0,'Receipt'),(49,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 16:22:47.135192',0,'Dr',400,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',27,0,0,'Receipt'),(50,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 16:22:47.138676',900,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',27,0,0,'Receipt'),(51,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 16:29:29.320903',0,'Dr',500,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',28,0,0,'Receipt'),(52,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 16:29:29.337923',0,'Dr',400,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',28,0,0,'Receipt'),(53,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 16:29:29.337923',900,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',28,0,0,'Receipt'),(54,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 17:26:50.494888',0,'Dr',500,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',29,0,0,'Receipt'),(55,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 17:26:50.522340',0,'Dr',400,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',29,0,0,'Receipt'),(56,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 17:26:50.527792',900,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',29,0,0,'Receipt'),(57,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 22:37:19.036632',0,'Dr',500,1,0,46,'Cash','To Amount received from INV123','Cash',46,'Cash','Ref123','2024-12-20',30,0,0,'Receipt'),(58,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 22:37:19.064381',0,'Dr',400,1,0,44,'Punjab National Bank','To Amount received from INV123','Punjab National Bank',44,'Cash','Ref123','2024-12-20',30,0,0,'Receipt'),(59,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 22:37:19.070774',900,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Cash','Ref123','2024-12-20',30,0,0,'Receipt'),(60,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 22:45:15.423618',0,'Dr',500,1,0,46,'Cash','To Amount received from INV123','Cash',46,'Cash','Ref123','2024-12-20',31,0,0,'Receipt'),(61,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 22:45:15.449981',0,'Dr',400,1,0,44,'Punjab National Bank','To Amount received from INV123','Punjab National Bank',44,'Punjab National Bank','Ref123','2024-12-20',31,0,0,'Receipt'),(62,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 22:45:15.454423',900,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Punjab National Bank','Ref123','2024-12-20',31,0,0,'Receipt'),(63,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 23:18:57.850392',0,'Dr',500,1,0,12,'Cash-In-Hand','To Amount received from INV123','Cash-In-Hand',12,'Cash','Ref123','2024-12-20',32,0,0,'Receipt'),(64,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 23:18:57.875239',0,'Dr',400,1,0,44,'Punjab National Bank','To Amount received from INV123','Punjab National Bank',44,'Punjab National Bank','Ref123','2024-12-20',32,0,0,'Receipt'),(65,1,NULL,NULL,1,'1','2024-12-20',1,1,'2024-12-20 23:18:57.875239',900,'Cr',0,1,0,43,'Vivek Singh','By being Amount Paid INV123','Vivek Singh',43,'Punjab National Bank','Ref123','2024-12-20',32,0,0,'Receipt'),(66,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 15:27:46.480332',0,'Dr',500,1,0,12,'Cash-In-Hand',NULL,'Cash-In-Hand',12,'Cash','REF123','2024-12-21',33,0,0,'Receipt'),(67,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 15:27:46.538911',0,'Dr',300,1,0,44,'Punjab National Bank',NULL,'Punjab National Bank',44,'UPI','REF124','2024-12-21',33,0,0,'Receipt'),(68,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 15:27:46.545447',800,'Cr',0,1,0,43,'Vivek Singh',NULL,'Vivek Singh',43,'UPI','REF124','2024-12-21',33,0,0,'Receipt'),(69,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 15:43:52.626054',0,'Dr',500,1,0,12,'Cash-In-Hand',NULL,'Cash-In-Hand',12,'Cash','REF123','2024-12-21',34,0,0,'Receipt'),(70,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 15:43:52.655362',0,'Dr',300,1,0,44,'Punjab National Bank',NULL,'Punjab National Bank',44,'UPI','REF124','2024-12-21',34,0,0,'Receipt'),(71,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 15:43:52.660619',800,'Cr',0,1,0,43,'Vivek Singh',NULL,'Vivek Singh',43,'UPI','REF124','2024-12-21',34,0,0,'Receipt'),(72,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 15:48:12.736514',0,'Dr',500,1,0,12,'Cash-In-Hand',NULL,'Cash-In-Hand',12,'Cash','REF123','2024-12-21',35,0,0,'Receipt'),(73,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 15:48:12.758471',0,'Dr',300,1,0,44,'Punjab National Bank',NULL,'Punjab National Bank',44,'UPI','REF124','2024-12-21',35,0,0,'Receipt'),(74,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 15:48:12.768802',800,'Cr',0,1,0,43,'Vivek Singh',NULL,'Vivek Singh',43,'UPI','REF124','2024-12-21',35,0,0,'Receipt'),(75,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 15:58:56.304718',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,NULL,0,'Cash',NULL,'2024-12-21',36,0,0,'Receipt'),(76,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 15:58:56.327253',0,'Dr',300,0,0,44,'Punjab National Bank',NULL,NULL,0,'UPI',NULL,'2024-12-21',36,0,0,'Receipt'),(77,1,NULL,NULL,1,'1',NULL,1,0,'2024-12-21 15:58:56.327253',800,'Cr',0,0,0,43,'Vivek Singh',NULL,NULL,0,'UPI',NULL,'2024-12-21',36,0,0,'Receipt'),(78,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 16:50:15.482569',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,NULL,0,'Cash',NULL,'2024-12-21',37,0,0,'Receipt'),(79,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 16:50:15.712425',0,'Dr',300,0,0,44,'Punjab National Bank',NULL,NULL,0,'UPI',NULL,'2024-12-21',37,0,0,'Receipt'),(80,1,NULL,NULL,1,'1',NULL,1,0,'2024-12-21 16:50:15.722513',800,'Cr',0,0,0,43,'Vivek Singh',NULL,NULL,0,'UPI',NULL,'2024-12-21',37,0,0,'Receipt'),(81,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 16:52:23.053936',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,NULL,0,'Cash',NULL,'2024-12-21',38,0,0,'Receipt'),(82,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 16:52:23.080549',0,'Dr',300,0,0,44,'Punjab National Bank',NULL,NULL,0,'UPI',NULL,'2024-12-21',38,0,0,'Receipt'),(83,1,NULL,NULL,1,'1',NULL,1,0,'2024-12-21 16:52:23.086572',800,'Cr',0,0,0,43,'Vivek Singh',NULL,NULL,0,'UPI',NULL,'2024-12-21',38,0,0,'Receipt'),(84,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 17:09:17.576124',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,NULL,0,'Cash',NULL,'2024-12-21',39,0,0,'Receipt'),(85,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 17:09:17.629918',0,'Dr',300,0,0,44,'Punjab National Bank',NULL,NULL,0,'UPI',NULL,'2024-12-21',39,0,0,'Receipt'),(86,1,NULL,NULL,1,'1',NULL,1,0,'2024-12-21 17:09:17.638431',800,'Cr',0,0,0,43,'Vivek Singh',NULL,NULL,0,'UPI',NULL,'2024-12-21',39,0,0,'Receipt'),(87,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 19:59:29.668456',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,NULL,0,'Cash',NULL,'2024-12-21',40,0,0,'Receipt'),(88,1,NULL,NULL,1,'1',NULL,1,0,'2024-12-21 19:59:29.703522',500,'Cr',0,0,0,43,'Vivek Singh',NULL,NULL,0,'Cash',NULL,'2024-12-21',40,0,0,'Receipt'),(89,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 20:01:31.828378',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,NULL,0,'Cash',NULL,'2024-12-21',41,0,0,'Receipt'),(90,1,NULL,NULL,1,'1',NULL,1,0,'2024-12-21 20:01:31.844403',500,'Cr',0,0,0,43,'Vivek Singh',NULL,NULL,0,'Cash',NULL,'2024-12-21',41,0,0,'Receipt'),(91,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 20:03:39.929181',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,NULL,0,'Cash',NULL,'2024-12-21',42,0,0,'Receipt'),(92,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 20:03:40.050393',0,'Dr',300,0,0,12,'Cash-In-Hand',NULL,NULL,0,'UPI',NULL,'2024-12-21',42,0,0,'Receipt'),(93,1,NULL,NULL,1,'1',NULL,1,0,'2024-12-21 20:03:40.054980',800,'Cr',0,0,0,43,'Vivek Singh',NULL,NULL,0,'UPI',NULL,'2024-12-21',42,0,0,'Receipt'),(94,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 20:15:26.700574',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'Cash',NULL,'2024-12-21',43,0,0,'Receipt'),(95,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 20:15:26.729951',0,'Dr',300,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'UPI',NULL,'2024-12-21',43,0,0,'Receipt'),(96,1,NULL,NULL,1,'1','2024-12-21',1,0,'2024-12-21 20:15:26.739603',800,'Cr',0,0,0,43,'Vivek Singh',NULL,'INV123',6,'UPI',NULL,'2024-12-21',43,0,0,'Receipt'),(97,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 20:30:19.176344',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'Cash',NULL,'2024-12-21',44,12,4,'Receipt'),(98,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 20:30:19.211100',0,'Dr',300,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'UPI',NULL,'2024-12-21',44,12,4,'Receipt'),(99,1,NULL,NULL,1,'1','2024-12-21',1,1,'2024-12-21 20:30:19.220240',800,'Cr',0,0,0,43,'Vivek Singh',NULL,'INV123',6,'UPI',NULL,'2024-12-21',44,12,4,'Receipt'),(113,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:37:52.852446',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'Cash',NULL,'2024-12-23',47,14,6,'Receipt'),(114,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:37:52.885167',0,'Dr',300,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'UPI',NULL,'2024-12-23',47,14,6,'Receipt'),(115,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:37:52.904255',800,'Cr',0,0,0,43,'Vivek Singh',NULL,'INV123',6,'UPI',NULL,'2024-12-23',47,14,6,'Receipt'),(116,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:47:22.431263',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'Cash',NULL,'2024-12-23',48,15,7,'Receipt'),(117,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:47:22.448841',0,'Dr',300,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'UPI',NULL,'2024-12-23',48,15,7,'Receipt'),(118,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:47:22.457558',800,'Cr',0,0,0,43,'Vivek Singh',NULL,'INV123',6,'UPI',NULL,'2024-12-23',48,15,7,'Receipt'),(119,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:49:31.498346',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'Cash',NULL,'2024-12-23',49,16,8,'Receipt'),(120,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:49:31.505861',0,'Dr',300,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'UPI',NULL,'2024-12-23',49,16,8,'Receipt'),(121,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:49:31.514881',800,'Cr',0,0,0,43,'Vivek Singh',NULL,'INV123',6,'UPI',NULL,'2024-12-23',49,16,8,'Receipt'),(125,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:53:59.451428',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'Cash',NULL,'2024-12-23',45,13,5,'Receipt'),(126,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:53:59.468058',0,'Dr',300,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'UPI',NULL,'2024-12-23',45,13,5,'Receipt'),(127,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:53:59.475073',800,'Cr',0,0,0,43,'Vivek Singh',NULL,'INV123',6,'UPI',NULL,'2024-12-23',45,13,5,'Receipt'),(128,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:54:53.145162',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'Cash',NULL,'2024-12-23',50,17,9,'Receipt'),(129,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:54:53.155192',0,'Dr',300,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'UPI',NULL,'2024-12-23',50,17,9,'Receipt'),(130,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:54:53.155192',800,'Cr',0,0,0,43,'Vivek Singh',NULL,'INV123',6,'UPI',NULL,'2024-12-23',50,17,9,'Receipt'),(131,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:59:55.634297',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'Cash',NULL,'2024-12-23',51,18,10,'Receipt'),(132,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:59:55.659408',0,'Dr',300,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'UPI',NULL,'2024-12-23',51,18,10,'Receipt'),(133,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 10:59:55.668416',800,'Cr',0,0,0,43,'Vivek Singh',NULL,'INV123',6,'UPI',NULL,'2024-12-23',51,18,10,'Receipt'),(137,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 11:52:59.786510',3479,'Cr',0,1,0,20,'Sales A/c','By sales invoice no. null','Sales A/c',20,'By credit','Ref123','2024-12-23',53,20,6,'Sales'),(138,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 11:52:59.807288',0,'Dr',3479,1,0,43,'Vivek Singh','To sales invoice no. null','Vivek Singh',1,'By credit','Ref123','2024-12-23',53,20,6,'Sales'),(139,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 12:04:05.596505',3479,'Cr',0,1,0,20,'Sales A/c','By sales invoice no. null','Sales A/c',20,'By credit','Ref123','2024-11-23',55,22,8,'Sales'),(140,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 12:04:05.620350',0,'Dr',3479,1,0,43,'Vivek Singh','To sales invoice no. null','Vivek Singh',1,'By credit','Ref123','2024-04-01',55,22,8,'Sales'),(144,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 15:18:18.186480',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'Cash',NULL,'2024-12-23',52,19,11,'Receipt'),(145,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 15:18:18.203094',0,'Dr',300,0,0,12,'Cash-In-Hand',NULL,'INV123',6,'UPI',NULL,'2024-12-23',52,19,11,'Receipt'),(146,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 15:18:18.210609',800,'Cr',0,0,0,43,'Vivek Singh',NULL,'INV123',6,'UPI',NULL,'2024-12-23',52,19,11,'Receipt'),(147,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 16:37:19.867184',0,'Dr',500,0,0,12,'Cash-In-Hand',NULL,'TAX/24-25/1',10,'Cash',NULL,'2024-12-23',57,23,12,'Receipt'),(148,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 16:37:19.885953',0,'Dr',300,0,0,12,'Cash-In-Hand',NULL,'TAX/24-25/1',10,'UPI',NULL,'2024-12-23',57,23,12,'Receipt'),(149,1,NULL,NULL,1,'1','2024-12-23',1,1,'2024-12-23 16:37:19.893621',800,'Cr',0,0,0,43,'Vivek Singh',NULL,'TAX/24-25/1',10,'UPI',NULL,'2024-12-23',57,23,12,'Receipt'),(180,1,NULL,NULL,1,'1','2024-12-31',1,1,'2024-12-31 11:58:25.473783',12818,'Cr',0,1,0,48,'Purchase A/c','By purchase purchase no. 24-25/1','Purchase A/c',48,'By Purchase','TAX/24-25/50','2024-12-31',71,32,9,'Purchase'),(181,1,NULL,NULL,1,'1','2024-12-31',1,1,'2024-12-31 11:58:25.493512',0,'Dr',12818,1,0,43,'Vivek Singh','To purchase purchase no. 24-25/1','Vivek Singh',1,'By Purchase','TAX/24-25/50','2024-12-31',71,32,9,'Purchase');
/*!40000 ALTER TABLE `tbltransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblunit`
--

DROP TABLE IF EXISTS `tblunit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblunit` (
  `unit_id` int NOT NULL AUTO_INCREMENT,
  `company_id` int DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `uqc` varchar(255) DEFAULT NULL,
  `uqc_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`unit_id`),
  KEY `FK742vbswpvm0tkemuq1pxmkc0e` (`company_id`),
  CONSTRAINT `FK742vbswpvm0tkemuq1pxmkc0e` FOREIGN KEY (`company_id`) REFERENCES `tblcompanies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblunit`
--

LOCK TABLES `tblunit` WRITE;
/*!40000 ALTER TABLE `tblunit` DISABLE KEYS */;
INSERT INTO `tblunit` VALUES (1,1,'Mobile','UQCCode','UN2334');
/*!40000 ALTER TABLE `tblunit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbluser`
--

DROP TABLE IF EXISTS `tbluser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbluser` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `google` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `organisation_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `profiler_picture` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `status` int NOT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK56cafmotgj4c6lwn26g4yr7ni` (`customer_id`),
  CONSTRAINT `FK56cafmotgj4c6lwn26g4yr7ni` FOREIGN KEY (`customer_id`) REFERENCES `tblcustomer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbluser`
--

LOCK TABLES `tbluser` WRITE;
/*!40000 ALTER TABLE `tbluser` DISABLE KEYS */;
INSERT INTO `tbluser` VALUES (1,'2024-11-18 11:38:29.195847',1,1,'vivek@gmail.com',0,'Vivek Singh','Technix India','$2a$10$gnNLveErPhbbLAKUsOAxFOK88SKTWMmwXjlM1zTAH29wIUjjc2Hg6','07763876292','/users/1/1.jpg',1,1,'ADMIN'),(2,'2024-11-18 11:39:47.373309',1,2,'shubham@gmail.com',0,'Shubham Singh','Technix India','$2a$10$zIVNVQwLAKewmUanbhVqgO8locYovBP7FxpEp6xiYxXsayvacSxka','9878679878','/users/2/2.png',1,1,'ADMIN'),(3,'2024-11-18 11:40:39.161271',1,3,'sonu@gmail.com',0,'Sonu Singh','Facebook','$2a$10$NAUNZ1JYingFbO0U5TFCj.L/3MUo.TFAIfZ4/VCHep3TImy/GHJmy','9876678987','/users/3/3.jpg',1,1,'ADMIN'),(4,'2024-11-18 11:41:32.263293',1,4,'kunal@gmail.com',0,'Kunal Kumar','Techinx India','$2a$10$.d5Q3Fjyun88yufUKVWUWunZ7hT9tRniBFnnWocn7ZK.E1Nys41vK','9876678987','/users/4/4.jpg',1,1,'ADMIN'),(5,'2024-11-18 11:42:09.074432',1,5,'jhon@gmail.com',0,'Jhon','Google','$2a$10$jaA9g9nlGc67CFijm3y.0OKip9Iurhx9psglydZotQHme7eb8yDS2','9876678987','/users/5/5.jpg',1,1,'ADMIN'),(6,'2024-11-18 11:45:08.493282',1,1,'ramesh@gmail.com',0,'Ramesh Singh','Wipro','$2a$10$erBatFK9zO9BgSOrBQTkyenkNiqcAxmc5DlCfOkU1bpJylFHznqZy','9876456789','/users/6/6.jpg',1,1,'USER'),(7,'2024-11-18 11:55:19.566842',1,1,'skot@gmail.com',0,'Skot','Wipro','$2a$10$dBApXUKeiXT9CjCUYGCkqeYF7QzqV4n7FTHNkJnyd1mk8MmzIwDsi','9876456789','/users/7/7.jpg',1,1,'USER'),(8,'2024-11-18 12:02:56.096991',1,1,'smith@gmail.com',0,'Smith','American Express','$2a$10$gAfXKeAfA/PdbVB/8.UoSefFigPo08pV4U3HSlKK7iD7rIHljQyym','9876456789','/users/8/8.jpg',1,1,'USER'),(9,'2024-11-18 12:04:49.650536',1,1,'ravi@gmail.com',0,'Ravi Singh','Cisco','$2a$10$t4qHaf8q7S8q2Jfo/6l3Oedymr6YIqKH74B.5mmyIxz3N2aS.emFS','9876456789','/users/9/9.jpg',1,1,'USER'),(10,'2024-11-18 12:05:42.944124',1,1,'anil@gmail.com',0,'Anil Kumar','Cisco','$2a$10$an/FPmxcnwm9LqHRugDuPOZEKTZM1KLdwKHwaiQ5xw465nCzrZUJ.','07763876292','http://localhost:9090/user/profilePic/10',1,1,'USER');
/*!40000 ALTER TABLE `tbluser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblusers_logs`
--

DROP TABLE IF EXISTS `tblusers_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblusers_logs` (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `browser` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `isp` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `login_at` datetime(6) DEFAULT NULL,
  `platform` varchar(255) DEFAULT NULL,
  `postal` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `FKtexuc0fjsayl3nys6tlwuun25` (`user_id`),
  CONSTRAINT `FKtexuc0fjsayl3nys6tlwuun25` FOREIGN KEY (`user_id`) REFERENCES `tbluser` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblusers_logs`
--

LOCK TABLES `tblusers_logs` WRITE;
/*!40000 ALTER TABLE `tblusers_logs` DISABLE KEYS */;
INSERT INTO `tblusers_logs` VALUES (1,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-18 12:11:34.745701',NULL,'800001','Bihar',1),(2,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-18 12:16:34.218371',NULL,'800001','Bihar',2),(3,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-18 12:21:33.851956',NULL,'800001','Bihar',1),(4,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-19 11:08:25.834985',NULL,'800001','Bihar',5),(5,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-19 11:14:45.026302',NULL,'800001','Bihar',5),(6,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-20 09:18:27.618121',NULL,'800001','Bihar',5),(7,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-20 13:11:38.621033',NULL,'800001','Bihar',5),(8,'PostmanRuntime/7.42.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-20 13:37:05.181105',NULL,'800001','Bihar',5),(9,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-21 10:23:18.561615',NULL,'800001','Bihar',5),(10,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-22 09:13:21.060801',NULL,'800001','Bihar',5),(11,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-22 21:21:49.812493',NULL,'800001','Bihar',5),(12,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-22 21:22:43.101416',NULL,'800001','Bihar',5),(13,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-23 09:19:13.229889',NULL,'800001','Bihar',1),(14,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-25 09:53:23.419569',NULL,'800001','Bihar',5),(15,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-25 21:01:10.153792',NULL,'800001','Bihar',5),(16,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-26 09:27:33.185684',NULL,'800001','Bihar',1),(17,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-26 17:44:00.600859',NULL,'800001','Bihar',1),(18,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-27 09:16:42.414196',NULL,'800001','Bihar',5),(19,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-28 09:15:32.621039',NULL,'800001','Bihar',1),(20,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-28 15:08:20.754917',NULL,'800001','Bihar',1),(21,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-29 09:32:02.217667',NULL,'800001','Bihar',5),(22,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-29 14:40:22.906891',NULL,'800001','Bihar',1),(23,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-30 09:17:30.857493',NULL,'800001','Bihar',1),(24,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-11-30 16:26:55.339082',NULL,'800001','Bihar',3),(25,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-01 15:13:14.803734',NULL,'800001','Bihar',5),(26,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-02 09:30:34.917798',NULL,'800001','Bihar',1),(27,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-03 09:19:08.080619',NULL,'800001','Bihar',5),(28,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:132.0) Gecko/20100101 Firefox/132.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-03 12:09:39.410668',NULL,'800001','Bihar',1),(29,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:132.0) Gecko/20100101 Firefox/132.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-03 12:24:37.671165',NULL,'800001','Bihar',1),(30,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:132.0) Gecko/20100101 Firefox/132.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-03 12:43:06.822868',NULL,'800001','Bihar',1),(31,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:132.0) Gecko/20100101 Firefox/132.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-03 17:18:11.386321',NULL,'800001','Bihar',1),(32,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-03 20:40:16.058218',NULL,'800001','Bihar',1),(33,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:132.0) Gecko/20100101 Firefox/132.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-04 11:20:49.932695',NULL,'800001','Bihar',1),(34,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-04 13:31:10.986619',NULL,'800001','Bihar',1),(35,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-05 09:11:28.865661',NULL,'800001','Bihar',1),(36,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-05 09:14:20.214620',NULL,'800001','Bihar',5),(37,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-05 15:58:23.138787',NULL,'800001','Bihar',1),(38,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-05 16:18:32.471468',NULL,'800001','Bihar',5),(39,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-05 16:26:07.999905',NULL,'800001','Bihar',1),(40,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-06 09:25:22.093326',NULL,'800001','Bihar',5),(41,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-06 09:41:25.177434',NULL,'800001','Bihar',1),(42,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-06 16:01:01.736455',NULL,'800001','Bihar',1),(43,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-06 16:29:37.834668',NULL,'800001','Bihar',1),(44,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-06 16:44:58.339331',NULL,'800001','Bihar',1),(45,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-06 21:10:08.219375',NULL,'800001','Bihar',1),(46,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-07 09:19:36.438538',NULL,'800001','Bihar',1),(47,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-08 20:40:49.825020',NULL,'800001','Bihar',1),(48,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-09 09:25:46.596890',NULL,'800001','Bihar',1),(49,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-09 09:40:24.925190',NULL,'800001','Bihar',5),(50,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-09 14:59:49.566412',NULL,'800001','Bihar',1),(51,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-10 09:34:01.229275',NULL,'800001','Bihar',1),(52,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-10 09:40:02.300270',NULL,'800001','Bihar',1),(53,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-10 10:43:33.245935',NULL,'800001','Bihar',5),(54,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-10 15:32:55.792592',NULL,'800001','Bihar',5),(55,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-11 09:24:44.986095',NULL,'800001','Bihar',1),(56,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-11 10:18:56.971657',NULL,'800001','Bihar',1),(57,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-11 10:51:56.853808',NULL,'800001','Bihar',1),(58,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-11 10:54:44.593498',NULL,'800001','Bihar',1),(59,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-11 12:12:47.732241',NULL,'800001','Bihar',5),(60,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-12 09:33:50.750110',NULL,'800001','Bihar',1),(61,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-12 09:33:53.575330',NULL,'800001','Bihar',5),(62,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-12 10:21:31.969281',NULL,'800001','Bihar',5),(63,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-13 09:20:35.830536',NULL,'800001','Bihar',5),(64,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-13 09:56:08.073539',NULL,'800001','Bihar',1),(65,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-14 12:28:46.220559',NULL,'800001','Bihar',1),(66,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-14 15:45:39.214603',NULL,'800001','Bihar',5),(67,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-20 10:16:49.623111',NULL,'800001','Bihar',5),(68,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-20 21:26:01.141141',NULL,'800001','Bihar',1),(69,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-21 15:26:53.136972',NULL,'800001','Bihar',1),(70,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-23 09:26:45.486608',NULL,'800001','Bihar',5),(71,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-23 10:59:11.212696',NULL,'800001','Bihar',1),(72,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-24 10:09:54.211142',NULL,'800001','Bihar',1),(73,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-25 10:58:06.135494',NULL,'800001','Bihar',1),(74,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-26 16:30:44.588995',NULL,'800001','Bihar',5),(75,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-27 09:29:08.612342',NULL,'800001','Bihar',1),(76,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-27 10:57:33.968417',NULL,'800001','Bihar',5),(77,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-27 11:07:50.115423',NULL,'800001','Bihar',3),(78,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-27 11:41:47.999527',NULL,'800001','Bihar',1),(79,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-27 15:57:57.724243',NULL,'800001','Bihar',1),(80,'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-27 16:16:17.767412',NULL,'800001','Bihar',1),(81,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-27 16:31:38.792066',NULL,'800001','Bihar',1),(82,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2024-12-31 10:35:43.923355',NULL,'800001','Bihar',1),(83,'PostmanRuntime/7.43.0','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2025-01-03 09:55:52.366822',NULL,'800001','Bihar',1),(84,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2025-01-03 15:40:02.046217',NULL,'800001','Bihar',1),(85,'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','Patna','IN','49.47.132.152','AS55836 Reliance Jio Infocomm Limited','25.5941,85.1356','2025-01-04 09:43:54.360938',NULL,'800001','Bihar',1);
/*!40000 ALTER TABLE `tblusers_logs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-04 18:05:09
