-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.16-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema dboab
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ dboab;
USE dboab;

--
-- Table structure for table `dboab`.`choraslaborales`
--

DROP TABLE IF EXISTS `choraslaborales`;
CREATE TABLE `choraslaborales` (
  `idX_cth` mediumint(9) NOT NULL,
  `hra_cth` time DEFAULT NULL,
  PRIMARY KEY (`idX_cth`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`choraslaborales`
--

/*!40000 ALTER TABLE `choraslaborales` DISABLE KEYS */;
INSERT INTO `choraslaborales` (`idX_cth`,`hra_cth`) VALUES 
 (1,'11:00:00'),
 (2,'11:05:00'),
 (3,'11:10:00'),
 (4,'11:15:00'),
 (5,'11:20:00'),
 (6,'11:25:00'),
 (7,'11:30:00'),
 (8,'11:35:00'),
 (9,'11:40:00'),
 (10,'11:45:00'),
 (11,'11:50:00'),
 (12,'11:55:00'),
 (13,'12:00:00'),
 (14,'12:05:00'),
 (15,'12:10:00'),
 (16,'12:15:00'),
 (17,'12:20:00'),
 (18,'12:25:00'),
 (19,'12:30:00'),
 (20,'12:35:00'),
 (21,'12:40:00'),
 (22,'12:45:00'),
 (23,'12:50:00'),
 (24,'12:55:00'),
 (25,'13:00:00'),
 (26,'13:05:00'),
 (27,'13:10:00'),
 (28,'13:15:00'),
 (29,'13:20:00'),
 (30,'13:25:00'),
 (35,'13:30:00'),
 (36,'13:35:00'),
 (37,'13:40:00'),
 (38,'13:45:00'),
 (39,'13:55:00'),
 (40,'14:00:00'),
 (45,'14:05:00'),
 (46,'14:10:00'),
 (47,'14:15:00'),
 (48,'14:20:00'),
 (49,'14:25:00'),
 (50,'14:30:00'),
 (51,'14:35:00'),
 (52,'14:40:00'),
 (53,'14:45:00'),
 (54,'14:50:00'),
 (55,'14:55:00'),
 (56,'15:00:00'),
 (57,'15:05:00'),
 (58,'15:10:00'),
 (59,'15:15:00'),
 (60,'15:20:00'),
 (61,'15:25:00'),
 (62,'15:30:00');
INSERT INTO `choraslaborales` (`idX_cth`,`hra_cth`) VALUES 
 (63,'15:35:00'),
 (64,'15:40:00'),
 (65,'15:45:00'),
 (66,'15:50:00'),
 (67,'15:55:00'),
 (68,'16:00:00'),
 (69,'16:05:00'),
 (70,'16:10:00'),
 (71,'16:15:00'),
 (72,'16:20:00'),
 (73,'16:25:00'),
 (74,'16:30:00'),
 (75,'16:35:00'),
 (76,'16:40:00'),
 (77,'16:45:00'),
 (78,'16:50:00'),
 (79,'16:55:00'),
 (80,'17:00:00'),
 (81,'17:05:00'),
 (82,'17:10:00'),
 (83,'17:15:00'),
 (84,'17:20:00'),
 (85,'17:25:00'),
 (86,'17:30:00'),
 (87,'17:35:00'),
 (88,'17:40:00'),
 (89,'17:45:00'),
 (90,'17:50:00'),
 (91,'17:55:00'),
 (92,'18:00:00'),
 (93,'18:05:00'),
 (94,'18:10:00'),
 (95,'18:15:00'),
 (96,'18:20:00'),
 (97,'18:25:00'),
 (98,'18:30:00'),
 (99,'18:35:00'),
 (100,'18:40:00'),
 (101,'18:45:00'),
 (102,'18:50:00'),
 (103,'18:55:00'),
 (104,'19:00:00'),
 (105,'19:05:00'),
 (106,'19:10:00'),
 (107,'19:15:00'),
 (108,'19:20:00'),
 (109,'19:25:00'),
 (110,'19:30:00'),
 (111,'19:35:00'),
 (112,'19:40:00'),
 (113,'19:45:00'),
 (114,'19:50:00'),
 (115,'19:55:00');
INSERT INTO `choraslaborales` (`idX_cth`,`hra_cth`) VALUES 
 (116,'20:00:00'),
 (117,'20:05:00'),
 (118,'20:10:00'),
 (119,'20:15:00'),
 (120,'20:20:00'),
 (121,'20:25:00'),
 (122,'20:30:00'),
 (123,'20:35:00'),
 (124,'20:40:00'),
 (125,'20:45:00'),
 (126,'20:50:00'),
 (127,'20:55:00'),
 (128,'21:00:00');
/*!40000 ALTER TABLE `choraslaborales` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`cprivilegio`
--

DROP TABLE IF EXISTS `cprivilegio`;
CREATE TABLE `cprivilegio` (
  `idP_prv` tinyint(2) NOT NULL,
  `nom_prv` char(1) NOT NULL,
  PRIMARY KEY (`idP_prv`),
  UNIQUE KEY `nom_prv_UNIQUE` (`nom_prv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 MAX_ROWS=4;

--
-- Dumping data for table `dboab`.`cprivilegio`
--

/*!40000 ALTER TABLE `cprivilegio` DISABLE KEYS */;
INSERT INTO `cprivilegio` (`idP_prv`,`nom_prv`) VALUES 
 (3,'A'),
 (2,'E'),
 (4,'G'),
 (1,'R');
/*!40000 ALTER TABLE `cprivilegio` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`cservespecialista`
--

DROP TABLE IF EXISTS `cservespecialista`;
CREATE TABLE `cservespecialista` (
  `idS_sve` mediumint(9) NOT NULL,
  `idE_sve` mediumint(9) NOT NULL,
  PRIMARY KEY (`idS_sve`,`idE_sve`),
  KEY `fk_cServicio_has_mEspecialista_mEspecialista1_idx` (`idE_sve`),
  KEY `fk_cServicio_has_mEspecialista_cServicio1_idx` (`idS_sve`),
  CONSTRAINT `fk_cServicio_has_mEspecialista_cServicio1` FOREIGN KEY (`idS_sve`) REFERENCES `cservicio` (`idS_srv`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cServicio_has_mEspecialista_mEspecialista1` FOREIGN KEY (`idE_sve`) REFERENCES `mespecialista` (`idE_esp`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`cservespecialista`
--

/*!40000 ALTER TABLE `cservespecialista` DISABLE KEYS */;
INSERT INTO `cservespecialista` (`idS_sve`,`idE_sve`) VALUES 
 (2,7),
 (3,7),
 (6,11),
 (7,11),
 (8,11),
 (2,12),
 (3,12),
 (6,12),
 (7,12),
 (8,12);
/*!40000 ALTER TABLE `cservespecialista` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`cservicio`
--

DROP TABLE IF EXISTS `cservicio`;
CREATE TABLE `cservicio` (
  `idS_srv` mediumint(9) NOT NULL AUTO_INCREMENT,
  `des_srv` varchar(60) NOT NULL,
  `dur_srv` time NOT NULL,
  PRIMARY KEY (`idS_srv`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`cservicio`
--

/*!40000 ALTER TABLE `cservicio` DISABLE KEYS */;
INSERT INTO `cservicio` (`idS_srv`,`des_srv`,`dur_srv`) VALUES 
 (2,'Corte caballero','02:20:00'),
 (3,'Depilacion de barba','01:00:00'),
 (6,'Maquillaje','00:05:00'),
 (7,'Balayage','04:00:00'),
 (8,'Balayage cabello corto','01:10:00');
/*!40000 ALTER TABLE `cservicio` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`csexo`
--

DROP TABLE IF EXISTS `csexo`;
CREATE TABLE `csexo` (
  `idO_sex` tinyint(2) NOT NULL,
  `sexo` char(1) NOT NULL,
  PRIMARY KEY (`idO_sex`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`csexo`
--

/*!40000 ALTER TABLE `csexo` DISABLE KEYS */;
/*!40000 ALTER TABLE `csexo` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`dcita`
--

DROP TABLE IF EXISTS `dcita`;
CREATE TABLE `dcita` (
  `idI_cta` int(11) NOT NULL AUTO_INCREMENT,
  `hra_cta` time NOT NULL,
  `fch_cta` date NOT NULL,
  `idL_cta` int(11) NOT NULL,
  `idS_cta` mediumint(9) NOT NULL,
  `idE_cta` mediumint(9) NOT NULL,
  PRIMARY KEY (`idI_cta`),
  KEY `fk_dCita_mCliente1_idx` (`idL_cta`),
  KEY `fk_dCita_cServicio1_idx` (`idS_cta`),
  KEY `fk_dCita_mEspecialista1_idx` (`idE_cta`),
  CONSTRAINT `fk_dCita_cServicio1` FOREIGN KEY (`idS_cta`) REFERENCES `cservicio` (`idS_srv`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dCita_mCliente1` FOREIGN KEY (`idL_cta`) REFERENCES `mcliente` (`idL_cli`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dCita_mEspecialista1` FOREIGN KEY (`idE_cta`) REFERENCES `mespecialista` (`idE_esp`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`dcita`
--

/*!40000 ALTER TABLE `dcita` DISABLE KEYS */;
INSERT INTO `dcita` (`idI_cta`,`hra_cta`,`fch_cta`,`idL_cta`,`idS_cta`,`idE_cta`) VALUES 
 (123,'16:00:00','2016-08-01',48,2,7),
 (126,'16:00:00','2016-10-31',50,2,7),
 (127,'18:00:00','2016-11-19',2,8,11),
 (128,'11:00:00','2016-11-22',2,2,7),
 (129,'17:00:00','2016-11-22',11,3,7),
 (130,'12:00:00','2016-11-22',2,6,11),
 (131,'18:00:00','2016-11-22',22,8,11),
 (132,'18:00:00','2016-11-23',2,6,11),
 (133,'18:00:00','2016-11-24',2,6,11),
 (134,'14:00:00','2017-02-01',27,6,11);
/*!40000 ALTER TABLE `dcita` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`dcontacto`
--

DROP TABLE IF EXISTS `dcontacto`;
CREATE TABLE `dcontacto` (
  `idC_con` int(11) NOT NULL AUTO_INCREMENT,
  `tel_con` decimal(10,0) NOT NULL,
  `mal_con` varchar(35) DEFAULT NULL,
  `fbk_con` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`idC_con`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`dcontacto`
--

/*!40000 ALTER TABLE `dcontacto` DISABLE KEYS */;
INSERT INTO `dcontacto` (`idC_con`,`tel_con`,`mal_con`,`fbk_con`) VALUES 
 (1,'2222222222','kl@mklk.com','mckw mikw'),
 (2,'2333333333','nciwoiw@ofpd.jo.mx','fio3 fw'),
 (3,'3948205817','jookl@live.com.mx','joldio s'),
 (4,'4432540800','america_job@hotmail.com','Job Salinas Pds'),
 (5,'4437885379','raul@gmail.com','Raul Estetica'),
 (6,'4438923492','clau@recepcionistas.mx','Clau M'),
 (8,'4438923492','clau@recepcionistas.mx','Clau M'),
 (9,'4438345292','mate@recepcionistas.mx','Jitomatita'),
 (11,'3947228402','mkw_23@gmail.mx','casmk'),
 (14,'4438769768','gool@rurazik.com','Panchito'),
 (15,'4438769768','gool@rurazik.com','Panchito'),
 (16,'4438769768','gool@rurazik.com','Panchito'),
 (17,'4438769768','gool@rurazik.com','Panchito'),
 (18,'4438769768','gool@rurazik.com','Panchito'),
 (19,'4438769768','gool@rurazik.com','Panchito'),
 (20,'1234567891','hhh@jjlll',''),
 (28,'9824864927','lili@google.mx','Lilita XD'),
 (29,'9824864927','lili@google.mx','Lilita XD'),
 (30,'9824864927','lili@google.mx','Lilita XD'),
 (31,'9824864927','lili@google.mx','Lilita XD');
INSERT INTO `dcontacto` (`idC_con`,`tel_con`,`mal_con`,`fbk_con`) VALUES 
 (32,'9824864927','lili@google.mx','Lilita XD'),
 (33,'9824864927','lili@google.mx','Lilita XD'),
 (34,'9824864927','lili@google.mx','Lilita XD'),
 (39,'6139357512','palo@google.mx','PalomaCanchola'),
 (40,'6689492749','mariana@gmail.mx','MarianaD'),
 (41,'6139357512','palo@google.mx','PalomaCanchola'),
 (42,'6139357512','palo@google.mx','PalomaCanchola'),
 (43,'6139357512','palo@google.mx','PalomaCanchola'),
 (44,'6139357512','palo@google.mx','PalomaCanchola'),
 (45,'6139357512','palo@google.mx','PalomaCanchola'),
 (56,'7777777777','moni@sss','Moniquita MuÃ±eca'),
 (57,'6666666666','ko@h',''),
 (58,'8888888888','j@u',''),
 (59,'8888888888','j@u',''),
 (60,'8888888888','a@h',''),
 (61,'4437492740','k@gmail.com','Kari'),
 (75,'8888888888','a@h',''),
 (76,'8888888888','a@h',''),
 (77,'8888888888','a@h',''),
 (78,'8888888888','a@h',''),
 (79,'8888888888','a@h',''),
 (80,'8888888888','a@h',''),
 (81,'8888888888','a@h',''),
 (82,'8888888888','a@h','');
INSERT INTO `dcontacto` (`idC_con`,`tel_con`,`mal_con`,`fbk_con`) VALUES 
 (83,'4437492740','jimenez_lupita@gmail.com',''),
 (86,'4437492740','alfredo@gmail.com',''),
 (88,'4437492740','magaly@yahoo.mx','Magaly 82'),
 (89,'4437492740','a@h',''),
 (90,'4437492740','a@h',''),
 (91,'4437492740','a@h',''),
 (92,'4437492740','a@h',''),
 (93,'4437492740','magaly@yahoo.mx',''),
 (94,'4439246938','claudia@gmail.com',''),
 (96,'4437492740','lluvia@gmail.com','lluvia'),
 (97,'4431613116','lulu@gmail.com','lulu 123'),
 (99,'4444444444','a@s',''),
 (102,'4456237645','esme@gmail.com','Me 69'),
 (103,'6666666666','a@a',''),
 (104,'6666666666','a@j',''),
 (105,'4444444444','a@e',''),
 (107,'4444444444','a@e',''),
 (108,'1234567891','a@hjjg',''),
 (110,'1111111111','a@g','mialmaenpena');
/*!40000 ALTER TABLE `dcontacto` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`ddomicilio`
--

DROP TABLE IF EXISTS `ddomicilio`;
CREATE TABLE `ddomicilio` (
  `iid_dom` mediumint(9) NOT NULL,
  `colonia` varchar(45) NOT NULL,
  `calle` varchar(45) NOT NULL,
  `numExterior` smallint(6) NOT NULL,
  `numInterior` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`iid_dom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`ddomicilio`
--

/*!40000 ALTER TABLE `ddomicilio` DISABLE KEYS */;
/*!40000 ALTER TABLE `ddomicilio` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`dhorariotrabajo`
--

DROP TABLE IF EXISTS `dhorariotrabajo`;
CREATE TABLE `dhorariotrabajo` (
  `idT_hrt` mediumint(9) NOT NULL AUTO_INCREMENT,
  `hre_hrt` time NOT NULL,
  `hrs_hrt` time NOT NULL,
  PRIMARY KEY (`idT_hrt`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`dhorariotrabajo`
--

/*!40000 ALTER TABLE `dhorariotrabajo` DISABLE KEYS */;
INSERT INTO `dhorariotrabajo` (`idT_hrt`,`hre_hrt`,`hrs_hrt`) VALUES 
 (9,'11:00:00','20:00:00'),
 (10,'12:00:00','20:00:00'),
 (13,'12:00:00','20:00:00'),
 (14,'11:00:00','20:00:00');
/*!40000 ALTER TABLE `dhorariotrabajo` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`madministrador`
--

DROP TABLE IF EXISTS `madministrador`;
CREATE TABLE `madministrador` (
  `idA_adm` smallint(4) NOT NULL AUTO_INCREMENT,
  `nom_adm` varchar(45) NOT NULL,
  `app_adm` varchar(45) NOT NULL,
  `apm_adm` varchar(45) NOT NULL,
  `idU_adm` mediumint(9) NOT NULL,
  `idC_adm` int(11) NOT NULL,
  PRIMARY KEY (`idA_adm`),
  KEY `fk_mAdministrador_mUsuario1_idx` (`idU_adm`),
  KEY `fk_mAdministrador_dContacto1_idx` (`idC_adm`),
  CONSTRAINT `fk_mAdministrador_dContacto1` FOREIGN KEY (`idC_adm`) REFERENCES `dcontacto` (`idC_con`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_mAdministrador_mUsuario1` FOREIGN KEY (`idU_adm`) REFERENCES `musuario` (`idU_usu`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`madministrador`
--

/*!40000 ALTER TABLE `madministrador` DISABLE KEYS */;
INSERT INTO `madministrador` (`idA_adm`,`nom_adm`,`app_adm`,`apm_adm`,`idU_adm`,`idC_adm`) VALUES 
 (1,'Job Adolfo','Salinas','Hernandez',3,4),
 (2,'Alfredo','Estrella','El estrellado',33,86);
/*!40000 ALTER TABLE `madministrador` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`mcliente`
--

DROP TABLE IF EXISTS `mcliente`;
CREATE TABLE `mcliente` (
  `idL_cli` int(11) NOT NULL AUTO_INCREMENT,
  `nom_cli` varchar(45) NOT NULL,
  `app_cli` varchar(45) NOT NULL,
  `apm_cli` varchar(45) NOT NULL,
  `idC_cli` int(11) NOT NULL,
  PRIMARY KEY (`idL_cli`),
  KEY `fk_mCliente_dContacto1_idx` (`idC_cli`),
  CONSTRAINT `fk_mCliente_dContacto1` FOREIGN KEY (`idC_cli`) REFERENCES `dcontacto` (`idC_con`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`mcliente`
--

/*!40000 ALTER TABLE `mcliente` DISABLE KEYS */;
INSERT INTO `mcliente` (`idL_cli`,`nom_cli`,`app_cli`,`apm_cli`,`idC_cli`) VALUES 
 (2,'Itzigueri','Holanda','Kon',11),
 (5,'gool@rurazik.com','Carbrela','Ramones',14),
 (6,'gool@rurazik.com','Carbrela','Ramones',15),
 (7,'gool@rurazik.com','Carbrela','Ramones',16),
 (8,'gool@rurazik.com','Carbrela','Ramones',17),
 (9,'gool@rurazik.com','Carbrela','Ramones',18),
 (10,'gool@rurazik.com','Carbrela','Ramones',19),
 (11,'Jitomatita','Perejila','Meni',20),
 (20,'Lila ','Flora','Meni',29),
 (21,'Lila ','Flora','Meni',30),
 (22,'Lila ','Flora','Meni',31),
 (23,'Lila ','Flora','Meni',32),
 (24,'Lila ','Flora','Meni',33),
 (25,'Lila ','Flora','Meni',34),
 (26,'Paloma','Gonzalez','Canchola',39),
 (27,'Mariana','Gugo','West',40),
 (28,'Paloma','Gonzalez','Canchola',41),
 (29,'Paloma','Gonzalez','Canchola',42),
 (30,'Paloma','Gonzalez','Canchola',43),
 (31,'Paloma','Gonzalez','Canchola',44),
 (32,'Paloma','Gonzalez','Canchola',45),
 (43,'Monica','Kan','Loip',56),
 (44,'koooo','ll','llll',57),
 (45,'j','j','j',58),
 (46,'j','j','j',59);
INSERT INTO `mcliente` (`idL_cli`,`nom_cli`,`app_cli`,`apm_cli`,`idC_cli`) VALUES 
 (47,'k','k','k',60),
 (48,'Lupita','Jimenez','Jimenez',83),
 (49,'Lluvia','Pimentel','Flores',96),
 (50,'lulu','La Gorda','Inflada',97);
/*!40000 ALTER TABLE `mcliente` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`mespecialista`
--

DROP TABLE IF EXISTS `mespecialista`;
CREATE TABLE `mespecialista` (
  `idE_esp` mediumint(9) NOT NULL AUTO_INCREMENT,
  `nom_esp` varchar(45) NOT NULL,
  `app_esp` varchar(45) NOT NULL,
  `apm_esp` varchar(45) NOT NULL,
  `hrc_esp` time NOT NULL,
  `idU_esp` mediumint(9) NOT NULL,
  `idC_esp` int(11) NOT NULL,
  `idT_esp` mediumint(9) NOT NULL,
  PRIMARY KEY (`idE_esp`),
  KEY `fk_mEspecialista_mUsuario1_idx` (`idU_esp`),
  KEY `fk_mEspecialista_dContacto1_idx` (`idC_esp`),
  KEY `fk_mEspecialista_dHorarioTrabajo1_idx` (`idT_esp`),
  CONSTRAINT `fk_mEspecialista_dContacto1` FOREIGN KEY (`idC_esp`) REFERENCES `dcontacto` (`idC_con`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_mEspecialista_dHorarioTrabajo1` FOREIGN KEY (`idT_esp`) REFERENCES `dhorariotrabajo` (`idT_hrt`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_mEspecialista_mUsuario1` FOREIGN KEY (`idU_esp`) REFERENCES `musuario` (`idU_usu`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`mespecialista`
--

/*!40000 ALTER TABLE `mespecialista` DISABLE KEYS */;
INSERT INTO `mespecialista` (`idE_esp`,`nom_esp`,`app_esp`,`apm_esp`,`hrc_esp`,`idU_esp`,`idC_esp`,`idT_esp`) VALUES 
 (7,'Magaly Jimena','Mendez','Gomez','14:00:00',40,93,9),
 (11,'Esperalda','Matinez','Ramirez','16:00:00',47,102,13),
 (12,'mialmaenpena','mialmaenpena','mialmaenpena','15:00:00',55,110,14);
/*!40000 ALTER TABLE `mespecialista` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`mgerente`
--

DROP TABLE IF EXISTS `mgerente`;
CREATE TABLE `mgerente` (
  `idG_gte` mediumint(9) NOT NULL AUTO_INCREMENT,
  `nom_gte` varchar(45) NOT NULL,
  `app_gte` varchar(45) NOT NULL,
  `apm_gte` varchar(45) NOT NULL,
  `idU_gte` mediumint(9) NOT NULL,
  `idC_gte` int(11) NOT NULL,
  PRIMARY KEY (`idG_gte`),
  KEY `fk_mGerente_mUsuario1_idx` (`idU_gte`),
  KEY `fk_mGerente_dContacto1_idx` (`idC_gte`),
  CONSTRAINT `fk_mGerente_dContacto1` FOREIGN KEY (`idC_gte`) REFERENCES `dcontacto` (`idC_con`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_mGerente_mUsuario1` FOREIGN KEY (`idU_gte`) REFERENCES `musuario` (`idU_usu`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`mgerente`
--

/*!40000 ALTER TABLE `mgerente` DISABLE KEYS */;
INSERT INTO `mgerente` (`idG_gte`,`nom_gte`,`app_gte`,`apm_gte`,`idU_gte`,`idC_gte`) VALUES 
 (1,'Raul','Mendez','Lucio',4,5),
 (4,'ruo','salm','navarro',44,99),
 (5,'koriko','koriko','koriko',53,108);
/*!40000 ALTER TABLE `mgerente` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`mrecepcionista`
--

DROP TABLE IF EXISTS `mrecepcionista`;
CREATE TABLE `mrecepcionista` (
  `idR_rta` mediumint(9) NOT NULL AUTO_INCREMENT,
  `nom_rta` varchar(45) NOT NULL,
  `app_rta` varchar(45) NOT NULL,
  `apm_rta` varchar(45) NOT NULL,
  `idU_rta` mediumint(9) NOT NULL,
  `idC_rta` int(11) NOT NULL,
  PRIMARY KEY (`idR_rta`),
  KEY `fk_mRecepcionista_mUsuario1_idx` (`idU_rta`),
  KEY `fk_mRecepcionista_dContacto1_idx` (`idC_rta`),
  CONSTRAINT `fk_mRecepcionista_dContacto1` FOREIGN KEY (`idC_rta`) REFERENCES `dcontacto` (`idC_con`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_mRecepcionista_mUsuario1` FOREIGN KEY (`idU_rta`) REFERENCES `musuario` (`idU_usu`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`mrecepcionista`
--

/*!40000 ALTER TABLE `mrecepcionista` DISABLE KEYS */;
INSERT INTO `mrecepcionista` (`idR_rta`,`nom_rta`,`app_rta`,`apm_rta`,`idU_rta`,`idC_rta`) VALUES 
 (2,'Karina','Jimenez','Contreras',10,61),
 (8,'y','y','y',23,75),
 (9,'y','y','y',24,76),
 (10,'l','l','l',25,77),
 (11,'k','k','k',26,78),
 (12,'j','j','j',27,79),
 (13,'j','j','j',28,80),
 (14,'h','h','h',29,81),
 (15,'h','h','h',30,82),
 (16,'k','k','k',48,103),
 (17,'k','k','k',49,104),
 (18,'nuevanuevecita','nuevanuevecita','nuevanuevecita',50,105),
 (19,'nuevanuevecita','nuevanuevecita','nuevanuevecita',52,107);
/*!40000 ALTER TABLE `mrecepcionista` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`musuario`
--

DROP TABLE IF EXISTS `musuario`;
CREATE TABLE `musuario` (
  `idU_usu` mediumint(9) NOT NULL AUTO_INCREMENT,
  `nom_usu` varchar(45) NOT NULL,
  `psw_usu` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `idP_usu` tinyint(10) NOT NULL,
  PRIMARY KEY (`idU_usu`),
  UNIQUE KEY `nom_usu_UNIQUE` (`nom_usu`),
  KEY `fk_mUsuario_cPrivilegio_idx` (`idP_usu`),
  CONSTRAINT `fk_mUsuario_cPrivilegio` FOREIGN KEY (`idP_usu`) REFERENCES `cprivilegio` (`idP_prv`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`musuario`
--

/*!40000 ALTER TABLE `musuario` DISABLE KEYS */;
INSERT INTO `musuario` (`idU_usu`,`nom_usu`,`psw_usu`,`idP_usu`) VALUES 
 (1,'KOM','SDDFS',1),
 (2,'sd','we34',4),
 (3,'jobAdmin','*169004AA73844B48600040D51982B90CEEC635A9',3),
 (4,'raul','*CEB70BE758805C51DD5AC121A896A4D26F58A393',3),
 (7,'monce','*57A779A0292DE232A4F58E8D65C37CABB1F19E3A',1),
 (8,'Jitomata','*735E620BA19CE6289BC2EA689FEE2684E428110D',2),
 (9,'carina','*9E98D57E396BE86B3DFC9EC313B083EE70F79D7A',1),
 (10,'karina123','*B6729B84795E245219BA7E4022C7EFC2036DF285',1),
 (23,'gy','*9A04E9549880BB91C935B6A3E90DA60E3E5C783F',1),
 (24,'minnnn','*E74B828D663D8F0C417FBD4B4E00CCB994C68112',1),
 (25,'hllllllll','*894549142A2819BC7952B608F00C489C989212E8',1),
 (26,'hhhhh','*69A7BAE3D37A83849ECEF8F3FF9260B35E01555D',1),
 (27,'fffff','*9A04E9549880BB91C935B6A3E90DA60E3E5C783F',1),
 (28,'gjgjh','*9A04E9549880BB91C935B6A3E90DA60E3E5C783F',1),
 (29,'ftf','*B999D775072B36F2F201043EC292C7E15686BD88',1),
 (30,'f','*4EAA8194CFACCA03EE79761D384DA63016B2D88E',1),
 (33,'estrellado','*269951431FB142A08B20D9E6D4EECCD829524F72',3),
 (36,'claudita','*D806E36CFF6EDB65766AC109DEE8EC3B8D03DA9D',2);
INSERT INTO `musuario` (`idU_usu`,`nom_usu`,`psw_usu`,`idP_usu`) VALUES 
 (38,'carlos','*9336E2BDECB21B4BCF4719EC414C84D6BA1982A9',2),
 (39,'bonita12','*D10489F4859300BFB5A20E322E74C50A5B49876A',2),
 (40,'magaly','*6973A932240F2950CCE7B35ACBB5717C3F3ACB86',2),
 (44,'rulo','*84AAC12F54AB666ECFC2A83C676908C8BBC381B1',4),
 (47,'esme','*75EEE75F6B6D60236CDE62B46752150E20FA77A7',2),
 (48,'guchi','*3EC4E7E5BBC2EF208F37D3AB45CFE3625281C519',1),
 (49,'guchi1','*C2A1C58F842B5DFA56FAF4F1E64B563D13575D61',1),
 (50,'nuevanuevecita','*411EFF8E147D4D1C3A7C743152DA2C54C024382F',1),
 (52,'nuevanuevecita123','*411EFF8E147D4D1C3A7C743152DA2C54C024382F',1),
 (53,'koriko','*6741AE37C935EAF5D6C2414E4FAE956551CDFEE9',4),
 (55,'mialmaenpena','*55FDAC7C6F7AC815D10BE9640FE7A907215D732E',2);
/*!40000 ALTER TABLE `musuario` ENABLE KEYS */;


--
-- View structure for view `dboab`.`vcitasdhoy`
--

DROP VIEW IF EXISTS `vcitasdhoy`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vcitasdhoy` AS select `i`.`idI_cta` AS `idCita`,hour(`i`.`hra_cta`) AS `horaCita`,minute(`i`.`hra_cta`) AS `minutosCita`,`e`.`idE_esp` AS `idEspecialista`,concat(`e`.`nom_esp`,' ',`e`.`app_esp`,' ',`e`.`apm_esp`) AS `especialista`,`s`.`idS_srv` AS `idServicio`,`s`.`des_srv` AS `servicio`,hour(`s`.`dur_srv`) AS `horaDuracion`,minute(`s`.`dur_srv`) AS `minutosDuracion`,concat(`l`.`nom_cli`,' ',`l`.`app_cli`,' ',`l`.`apm_cli`) AS `cliente` from ((((`dboab`.`dcita` `i` join `dboab`.`mespecialista` `e` on(((`i`.`idE_cta` = `e`.`idE_esp`) and (`i`.`fch_cta` = curdate())))) join `dboab`.`musuario` `u` on(((`e`.`idU_esp` = `u`.`idU_usu`) and (`i`.`fch_cta` = curdate())))) join `dboab`.`cservicio` `s` on(((`i`.`idS_cta` = `s`.`idS_srv`) and (`i`.`fch_cta` = curdate())))) join `dboab`.`mcliente` `l` on(((`l`.`idL_cli` = `i`.`idL_cta`) and (`i`.`fch_cta` = curdate())))) order by `i`.`hra_cta`;


--
-- View structure for view `dboab`.`vcitaspcliente`
--

DROP VIEW IF EXISTS `vcitaspcliente`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vcitaspcliente` AS select `i`.`idI_cta` AS `idCita`,`e`.`idE_esp` AS `idEspecialista`,concat(`e`.`nom_esp`,' ',`e`.`app_esp`,' ',`e`.`apm_esp`) AS `especialista`,`i`.`fch_cta` AS `fecha`,hour(`i`.`hra_cta`) AS `horaCita`,minute(`i`.`hra_cta`) AS `minutosCita`,`s`.`idS_srv` AS `idServicio`,`s`.`des_srv` AS `servicio`,hour(`s`.`dur_srv`) AS `horaDuracion`,minute(`s`.`dur_srv`) AS `minutosDuracion`,`l`.`idL_cli` AS `idCliente`,concat(`l`.`nom_cli`,' ',`l`.`app_cli`,' ',`l`.`apm_cli`) AS `cliente` from ((((`dboab`.`dcita` `i` join `dboab`.`mespecialista` `e` on((`i`.`idE_cta` = `e`.`idE_esp`))) join `dboab`.`musuario` `u` on((`e`.`idU_esp` = `u`.`idU_usu`))) join `dboab`.`cservicio` `s` on((`i`.`idS_cta` = `s`.`idS_srv`))) join `dboab`.`mcliente` `l` on((`l`.`idL_cli` = `i`.`idL_cta`))) order by `i`.`fch_cta`,`i`.`hra_cta`;


--
-- View structure for view `dboab`.`vcitaspespecialista`
--

DROP VIEW IF EXISTS `vcitaspespecialista`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vcitaspespecialista` AS select `u`.`nom_usu` AS `usuario`,`e`.`idE_esp` AS `idEspecialista`,concat(`e`.`nom_esp`,' ',`e`.`app_esp`,' ',`e`.`apm_esp`) AS `especialista`,`i`.`idI_cta` AS `idCita`,`i`.`fch_cta` AS `fecha`,hour(`i`.`hra_cta`) AS `horaCita`,minute(`i`.`hra_cta`) AS `minutosCita`,`s`.`idS_srv` AS `idServicio`,`s`.`des_srv` AS `servicio`,hour(`s`.`dur_srv`) AS `horaDuracion`,minute(`s`.`dur_srv`) AS `minutosDuracion`,concat(`l`.`nom_cli`,' ',`l`.`app_cli`,' ',`l`.`apm_cli`) AS `cliente` from ((((`dboab`.`dcita` `i` join `dboab`.`mespecialista` `e` on((`i`.`idE_cta` = `e`.`idE_esp`))) join `dboab`.`musuario` `u` on((`e`.`idU_esp` = `u`.`idU_usu`))) join `dboab`.`cservicio` `s` on((`i`.`idS_cta` = `s`.`idS_srv`))) join `dboab`.`mcliente` `l` on((`l`.`idL_cli` = `i`.`idL_cta`))) order by `i`.`fch_cta`,`i`.`hra_cta`;


--
-- View structure for view `dboab`.`vcitaspfecha`
--

DROP VIEW IF EXISTS `vcitaspfecha`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vcitaspfecha` AS select `i`.`idI_cta` AS `idCita`,`i`.`fch_cta` AS `fecha`,hour(`i`.`hra_cta`) AS `horaCita`,minute(`i`.`hra_cta`) AS `minutosCita`,`e`.`idE_esp` AS `idEspecialista`,concat(`e`.`nom_esp`,' ',`e`.`app_esp`,' ',`e`.`apm_esp`) AS `especialista`,`s`.`idS_srv` AS `idServicio`,`s`.`des_srv` AS `servicio`,hour(`s`.`dur_srv`) AS `horaDuracion`,minute(`s`.`dur_srv`) AS `minutosDuracion`,concat(`l`.`nom_cli`,' ',`l`.`app_cli`,' ',`l`.`apm_cli`) AS `cliente` from ((((`dboab`.`dcita` `i` join `dboab`.`mespecialista` `e` on((`i`.`idE_cta` = `e`.`idE_esp`))) join `dboab`.`musuario` `u` on((`e`.`idU_esp` = `u`.`idU_usu`))) join `dboab`.`cservicio` `s` on((`i`.`idS_cta` = `s`.`idS_srv`))) join `dboab`.`mcliente` `l` on((`l`.`idL_cli` = `i`.`idL_cta`))) order by `i`.`hra_cta`;


--
-- View structure for view `dboab`.`vdatoscliente`
--

DROP VIEW IF EXISTS `vdatoscliente`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vdatoscliente` AS select `c`.`idL_cli` AS `idCliente`,`c`.`nom_cli` AS `nombreCliente`,`c`.`app_cli` AS `apellidoPaterno`,`c`.`apm_cli` AS `apellidoMaterno`,`o`.`idC_con` AS `idContacto`,`o`.`tel_con` AS `telefono`,`o`.`mal_con` AS `email`,`o`.`fbk_con` AS `facebook` from (`dboab`.`mcliente` `c` join `dboab`.`dcontacto` `o` on((`c`.`idC_cli` = `o`.`idC_con`))) order by `c`.`nom_cli`;


--
-- View structure for view `dboab`.`vdatosvalidareserva`
--

DROP VIEW IF EXISTS `vdatosvalidareserva`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vdatosvalidareserva` AS select `e`.`idE_esp` AS `idEspecialista`,concat(`e`.`nom_esp`,' ',`e`.`app_esp`,' ',`e`.`apm_esp`) AS `especialista`,`s`.`idS_srv` AS `idServicio`,`s`.`des_srv` AS `servicio`,hour(`s`.`dur_srv`) AS `horaDuracion`,minute(`s`.`dur_srv`) AS `minutosDuracion`,hour(`t`.`hre_hrt`) AS `horaEntrada`,minute(`t`.`hre_hrt`) AS `minutosEntrada`,hour(`t`.`hrs_hrt`) AS `horaSalida`,minute(`t`.`hrs_hrt`) AS `minutosSalida`,hour(`e`.`hrc_esp`) AS `horaComida`,minute(`e`.`hrc_esp`) AS `minutosComida` from (((`dboab`.`cservespecialista` `v` join `dboab`.`mespecialista` `e` on((`e`.`idE_esp` = `v`.`idE_sve`))) join `dboab`.`cservicio` `s` on((`s`.`idS_srv` = `v`.`idS_sve`))) join `dboab`.`dhorariotrabajo` `t` on((`t`.`idT_hrt` = `e`.`idT_esp`)));


--
-- View structure for view `dboab`.`verificardisponibilidad`
--

DROP VIEW IF EXISTS `verificardisponibilidad`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`verificardisponibilidad` AS select `i`.`fch_cta` AS `fechaCita`,`i`.`hra_cta` AS `citaProgramada`,`s`.`dur_srv` AS `duracionCitaProgramada`,`e`.`hrc_esp` AS `horaComida`,`t`.`hre_hrt` AS `horaEntrada`,`t`.`hrs_hrt` AS `horaSalida`,`x`.`hra_cth` AS `horaCita` from ((((`dboab`.`dcita` `i` join `dboab`.`cservicio` `s` on((`i`.`idS_cta` = `s`.`idS_srv`))) join `dboab`.`mespecialista` `e` on((`e`.`idE_esp` = `i`.`idE_cta`))) join `dboab`.`dhorariotrabajo` `t` on((`t`.`idT_hrt` = `e`.`idT_esp`))) join `dboab`.`choraslaborales` `x` on((`x`.`idX_cth` = `x`.`idX_cth`)));


--
-- View structure for view `dboab`.`vnombreclientes`
--

DROP VIEW IF EXISTS `vnombreclientes`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vnombreclientes` AS select concat(`c`.`nom_cli`,' ',`c`.`app_cli`,' ',`c`.`apm_cli`) AS `nombreCompleto` from `dboab`.`mcliente` `c` order by `c`.`nom_cli`;


--
-- View structure for view `dboab`.`vservicios`
--

DROP VIEW IF EXISTS `vservicios`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vservicios` AS select `s`.`idS_srv` AS `idServicio`,`s`.`des_srv` AS `servicio`,`s`.`dur_srv` AS `duracion`,`e`.`idE_esp` AS `idEspecialista`,concat(`e`.`nom_esp`,' ',`e`.`app_esp`,' ',`e`.`apm_esp`) AS `especialista` from ((`dboab`.`cservespecialista` `a` join `dboab`.`cservicio` `s` on((`a`.`idS_sve` = `s`.`idS_srv`))) join `dboab`.`mespecialista` `e` on((`a`.`idE_sve` = `e`.`idE_esp`)));


--
-- View structure for view `dboab`.`vusuariosadmin`
--

DROP VIEW IF EXISTS `vusuariosadmin`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosadmin` AS select `a`.`idA_adm` AS `idAdmin`,`a`.`idU_adm` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`a`.`nom_adm` AS `nomAdmin`,`a`.`app_adm` AS `apellidoPaterno`,`a`.`apm_adm` AS `apellidoMaterno`,`p`.`idP_prv` AS `idPrivilegio`,`p`.`nom_prv` AS `privilegio`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from (((`dboab`.`musuario` `u` join `dboab`.`madministrador` `a` on((`u`.`idU_usu` = `a`.`idU_adm`))) join `dboab`.`cprivilegio` `p` on((`p`.`idP_prv` = `u`.`idP_usu`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `a`.`idC_adm`))) order by `a`.`idA_adm`;


--
-- View structure for view `dboab`.`vusuariosespecialista`
--

DROP VIEW IF EXISTS `vusuariosespecialista`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosespecialista` AS select `e`.`idE_esp` AS `idEspecialista`,`u`.`idU_usu` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`e`.`nom_esp` AS `nomEspecialista`,`e`.`app_esp` AS `apellidoPaterno`,`e`.`apm_esp` AS `apellidoMaterno`,hour(`e`.`hrc_esp`) AS `horaComida`,minute(`e`.`hrc_esp`) AS `minutosComida`,`t`.`idT_hrt` AS `idHorarioTrabajo`,hour(`t`.`hre_hrt`) AS `HoraTrbEntrada`,minute(`t`.`hre_hrt`) AS `MinutosTrbEntrada`,hour(`t`.`hrs_hrt`) AS `HoraTrbSalida`,minute(`t`.`hrs_hrt`) AS `MinutosTrbSalida`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from (((`dboab`.`musuario` `u` join `dboab`.`mespecialista` `e` on((`u`.`idU_usu` = `e`.`idU_esp`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `e`.`idC_esp`))) join `dboab`.`dhorariotrabajo` `t` on((`e`.`idT_esp` = `t`.`idT_hrt`))) order by `e`.`idE_esp`;


--
-- View structure for view `dboab`.`vusuariosespecialista_a`
--

DROP VIEW IF EXISTS `vusuariosespecialista_a`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosespecialista_a` AS select `e`.`idE_esp` AS `idEspecialista`,`u`.`idU_usu` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`e`.`nom_esp` AS `nomEspecialista`,`e`.`app_esp` AS `apellidoPaterno`,`e`.`apm_esp` AS `apellidoMaterno`,hour(`e`.`hrc_esp`) AS `horaComida`,minute(`e`.`hrc_esp`) AS `minutosComida`,`t`.`idT_hrt` AS `idHorarioTrabajo`,hour(`t`.`hre_hrt`) AS `HoraTrbEntrada`,minute(`t`.`hre_hrt`) AS `MinutosTrbEntrada`,hour(`t`.`hrs_hrt`) AS `HoraTrbSalida`,minute(`t`.`hrs_hrt`) AS `MinutosTrbSalida`,`p`.`idP_prv` AS `idPrivilegio`,`p`.`nom_prv` AS `privilegio`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from ((((`dboab`.`musuario` `u` join `dboab`.`mespecialista` `e` on((`u`.`idU_usu` = `e`.`idU_esp`))) join `dboab`.`cprivilegio` `p` on((`p`.`idP_prv` = `u`.`idP_usu`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `e`.`idC_esp`))) join `dboab`.`dhorariotrabajo` `t` on((`e`.`idT_esp` = `t`.`idT_hrt`))) order by `e`.`idE_esp`;


--
-- View structure for view `dboab`.`vusuariosgerente`
--

DROP VIEW IF EXISTS `vusuariosgerente`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosgerente` AS select `g`.`idG_gte` AS `idGerente`,`u`.`idU_usu` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`g`.`nom_gte` AS `nomGerente`,`g`.`app_gte` AS `apellidoPaterno`,`g`.`apm_gte` AS `apellidoMaterno`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from ((`dboab`.`musuario` `u` join `dboab`.`mgerente` `g` on((`u`.`idU_usu` = `g`.`idU_gte`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `g`.`idC_gte`))) order by `g`.`idG_gte`;


--
-- View structure for view `dboab`.`vusuariosgerente_a`
--

DROP VIEW IF EXISTS `vusuariosgerente_a`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosgerente_a` AS select `g`.`idG_gte` AS `idGerente`,`u`.`idU_usu` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`g`.`nom_gte` AS `nomGerente`,`g`.`app_gte` AS `apellidoPaterno`,`g`.`apm_gte` AS `apellidoMaterno`,`p`.`idP_prv` AS `idPrivilegio`,`p`.`nom_prv` AS `privilegio`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from (((`dboab`.`musuario` `u` join `dboab`.`mgerente` `g` on((`u`.`idU_usu` = `g`.`idU_gte`))) join `dboab`.`cprivilegio` `p` on((`p`.`idP_prv` = `u`.`idP_usu`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `g`.`idC_gte`))) order by `g`.`idG_gte`;


--
-- View structure for view `dboab`.`vusuariosrecepcionista`
--

DROP VIEW IF EXISTS `vusuariosrecepcionista`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosrecepcionista` AS select `r`.`idR_rta` AS `idRecepcionista`,`u`.`idU_usu` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`r`.`nom_rta` AS `nomRecepcionista`,`r`.`app_rta` AS `apellidoPaterno`,`r`.`apm_rta` AS `apellidoMaterno`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from ((`dboab`.`musuario` `u` join `dboab`.`mrecepcionista` `r` on((`u`.`idU_usu` = `r`.`idU_rta`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `r`.`idC_rta`))) order by `r`.`idR_rta`;


--
-- View structure for view `dboab`.`vusuariosrecepcionista_a`
--

DROP VIEW IF EXISTS `vusuariosrecepcionista_a`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosrecepcionista_a` AS select `r`.`idR_rta` AS `idRecepcionista`,`u`.`idU_usu` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`r`.`nom_rta` AS `nomRecepcionista`,`r`.`app_rta` AS `apellidoPaterno`,`r`.`apm_rta` AS `apellidoMaterno`,`p`.`idP_prv` AS `idPrivilegio`,`p`.`nom_prv` AS `privilegio`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from (((`dboab`.`musuario` `u` join `dboab`.`mrecepcionista` `r` on((`u`.`idU_usu` = `r`.`idU_rta`))) join `dboab`.`cprivilegio` `p` on((`p`.`idP_prv` = `u`.`idP_usu`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `r`.`idC_rta`))) order by `r`.`idR_rta`;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
