-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.13-log


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
-- Table structure for table `dboab`.`cprivilegio`
--

DROP TABLE IF EXISTS `cprivilegio`;
CREATE TABLE `cprivilegio` (
  `idP_prv` tinyint(4) NOT NULL,
  `nom_prv` char(1) NOT NULL,
  PRIMARY KEY (`idP_prv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 MAX_ROWS=4;

--
-- Dumping data for table `dboab`.`cprivilegio`
--

/*!40000 ALTER TABLE `cprivilegio` DISABLE KEYS */;
/*!40000 ALTER TABLE `cprivilegio` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`cservicio`
--

DROP TABLE IF EXISTS `cservicio`;
CREATE TABLE `cservicio` (
  `idS_srv` mediumint(9) NOT NULL AUTO_INCREMENT,
  `des_srv` varchar(60) NOT NULL,
  `dur_srv` time NOT NULL,
  `idE_srv` mediumint(9) NOT NULL,
  PRIMARY KEY (`idS_srv`),
  KEY `fk_cServicio_mEspecialista1_idx` (`idE_srv`),
  CONSTRAINT `fk_cServicio_mEspecialista1` FOREIGN KEY (`idE_srv`) REFERENCES `mespecialista` (`idE_esp`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`cservicio`
--

/*!40000 ALTER TABLE `cservicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `cservicio` ENABLE KEYS */;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`dcita`
--

/*!40000 ALTER TABLE `dcita` DISABLE KEYS */;
/*!40000 ALTER TABLE `dcita` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`dcontacto`
--

DROP TABLE IF EXISTS `dcontacto`;
CREATE TABLE `dcontacto` (
  `idC_con` int(11) NOT NULL AUTO_INCREMENT,
  `tel_con` decimal(10,0) NOT NULL,
  `mal_con` varchar(35) NOT NULL,
  `fbk_con` varchar(35) NOT NULL,
  PRIMARY KEY (`idC_con`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`dcontacto`
--

/*!40000 ALTER TABLE `dcontacto` DISABLE KEYS */;
/*!40000 ALTER TABLE `dcontacto` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`dhorariotrabajo`
--

DROP TABLE IF EXISTS `dhorariotrabajo`;
CREATE TABLE `dhorariotrabajo` (
  `idT_hrt` mediumint(9) NOT NULL AUTO_INCREMENT,
  `hre_hrt` time NOT NULL,
  `hrs_hrt` time NOT NULL,
  PRIMARY KEY (`idT_hrt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`dhorariotrabajo`
--

/*!40000 ALTER TABLE `dhorariotrabajo` DISABLE KEYS */;
/*!40000 ALTER TABLE `dhorariotrabajo` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`madministrador`
--

DROP TABLE IF EXISTS `madministrador`;
CREATE TABLE `madministrador` (
  `idA_adm` smallint(2) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`madministrador`
--

/*!40000 ALTER TABLE `madministrador` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`mcliente`
--

/*!40000 ALTER TABLE `mcliente` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`mespecialista`
--

/*!40000 ALTER TABLE `mespecialista` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`mgerente`
--

/*!40000 ALTER TABLE `mgerente` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`mrecepcionista`
--

/*!40000 ALTER TABLE `mrecepcionista` DISABLE KEYS */;
/*!40000 ALTER TABLE `mrecepcionista` ENABLE KEYS */;


--
-- Table structure for table `dboab`.`musuario`
--

DROP TABLE IF EXISTS `musuario`;
CREATE TABLE `musuario` (
  `idU_usu` mediumint(9) NOT NULL AUTO_INCREMENT,
  `nom_usu` varchar(45) NOT NULL,
  `psw_usu` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `idP_usu` tinyint(4) NOT NULL,
  PRIMARY KEY (`idU_usu`),
  UNIQUE KEY `nom_usu_UNIQUE` (`nom_usu`),
  KEY `fk_mUsuario_cPrivilegio_idx` (`idP_usu`),
  CONSTRAINT `fk_mUsuario_cPrivilegio` FOREIGN KEY (`idP_usu`) REFERENCES `cprivilegio` (`idP_prv`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`musuario`
--

/*!40000 ALTER TABLE `musuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `musuario` ENABLE KEYS */;


--
-- View structure for view `dboab`.`dcitaspespecialista`
--

DROP VIEW IF EXISTS `dcitaspespecialista`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`dcitaspespecialista` AS select `u`.`nom_usu` AS `usuario`,`e`.`idE_esp` AS `idEspecialista`,`i`.`idI_cta` AS `idCita`,`i`.`fch_cta` AS `fecha`,`i`.`hra_cta` AS `hora`,`s`.`idS_srv` AS `servicio`,`s`.`dur_srv` AS `duracion`,concat(`l`.`nom_cli`,`l`.`app_cli`,`l`.`apm_cli`) AS `cliente` from ((((`dboab`.`dcita` `i` join `dboab`.`mespecialista` `e` on((`i`.`idE_cta` = `e`.`idE_esp`))) join `dboab`.`musuario` `u` on((`e`.`idU_esp` = `u`.`idU_usu`))) join `dboab`.`cservicio` `s` on((`i`.`idS_cta` = `s`.`idS_srv`))) join `dboab`.`mcliente` `l` on((`l`.`idL_cli` = `i`.`idL_cta`))) order by `i`.`fch_cta`,`i`.`hra_cta` desc;


--
-- View structure for view `dboab`.`ddatoscliente`
--

DROP VIEW IF EXISTS `ddatoscliente`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`ddatoscliente` AS select `c`.`idL_cli` AS `idCliente`,`c`.`nom_cli` AS `nombreCliente`,`c`.`app_cli` AS `apellidoPaterno`,`c`.`apm_cli` AS `apellidoMaterno`,`o`.`tel_con` AS `telefono`,`o`.`mal_con` AS `email`,`o`.`fbk_con` AS `facebook` from (`dboab`.`mcliente` `c` join `dboab`.`dcontacto` `o` on((`c`.`idC_cli` = `o`.`idC_con`))) order by `c`.`nom_cli`;


--
-- View structure for view `dboab`.`dnombreclientes`
--

DROP VIEW IF EXISTS `dnombreclientes`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`dnombreclientes` AS select concat(`c`.`nom_cli`,`c`.`app_cli`,`c`.`apm_cli`) AS `nombreCompleto` from `dboab`.`mcliente` `c` order by `c`.`nom_cli`;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
