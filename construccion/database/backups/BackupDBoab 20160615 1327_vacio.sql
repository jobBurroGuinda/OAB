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
  `mal_con` varchar(35) DEFAULT NULL,
  `fbk_con` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`idC_con`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`dcontacto`
--

/*!40000 ALTER TABLE `dcontacto` DISABLE KEYS */;
INSERT INTO `dcontacto` (`idC_con`,`tel_con`,`mal_con`,`fbk_con`) VALUES 
 (1,'2222222222','kl@mklk.com','mckw mikw'),
 (2,'2333333333','nciwoiw@ofpd.jo.mx','fio3 fw'),
 (3,'3948205817','jookl@live.com.mx','joldio s');
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
  `idP_usu` tinyint(10) NOT NULL,
  PRIMARY KEY (`idU_usu`),
  UNIQUE KEY `nom_usu_UNIQUE` (`nom_usu`),
  KEY `fk_mUsuario_cPrivilegio_idx` (`idP_usu`),
  CONSTRAINT `fk_mUsuario_cPrivilegio` FOREIGN KEY (`idP_usu`) REFERENCES `cprivilegio` (`idP_prv`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dboab`.`musuario`
--

/*!40000 ALTER TABLE `musuario` DISABLE KEYS */;
INSERT INTO `musuario` (`idU_usu`,`nom_usu`,`psw_usu`,`idP_usu`) VALUES 
 (1,'KOM','SDDFS',1),
 (2,'sd','we34',4);
/*!40000 ALTER TABLE `musuario` ENABLE KEYS */;


--
-- View structure for view `dboab`.`vcitaspcliente`
--

DROP VIEW IF EXISTS `vcitaspcliente`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vcitaspcliente` AS select `i`.`idI_cta` AS `idCita`,`e`.`idE_esp` AS `idEspecialista`,concat(`e`.`nom_esp`,`e`.`app_esp`,`e`.`apm_esp`) AS `especialista`,`i`.`fch_cta` AS `fecha`,`i`.`hra_cta` AS `hora`,`s`.`idS_srv` AS `idServicio`,`s`.`des_srv` AS `servicio`,`s`.`dur_srv` AS `duracion`,concat(`l`.`nom_cli`,`l`.`app_cli`,`l`.`apm_cli`) AS `cliente` from ((((`dboab`.`dcita` `i` join `dboab`.`mespecialista` `e` on((`i`.`idE_cta` = `e`.`idE_esp`))) join `dboab`.`musuario` `u` on((`e`.`idU_esp` = `u`.`idU_usu`))) join `dboab`.`cservicio` `s` on((`i`.`idS_cta` = `s`.`idS_srv`))) join `dboab`.`mcliente` `l` on((`l`.`idL_cli` = `i`.`idL_cta`))) order by `i`.`fch_cta`,`i`.`hra_cta` desc;


--
-- View structure for view `dboab`.`vcitaspdia`
--

DROP VIEW IF EXISTS `vcitaspdia`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vcitaspdia` AS select `i`.`idI_cta` AS `idCita`,`i`.`hra_cta` AS `hora`,`e`.`idE_esp` AS `idEspecialista`,concat(`e`.`nom_esp`,`e`.`app_esp`,`e`.`apm_esp`) AS `especialista`,`s`.`idS_srv` AS `idServicio`,`s`.`des_srv` AS `servicio`,`s`.`dur_srv` AS `duracion`,concat(`l`.`nom_cli`,`l`.`app_cli`,`l`.`apm_cli`) AS `cliente` from ((((`dboab`.`dcita` `i` join `dboab`.`mespecialista` `e` on(((`i`.`idE_cta` = `e`.`idE_esp`) and (`i`.`fch_cta` = curdate())))) join `dboab`.`musuario` `u` on(((`e`.`idU_esp` = `u`.`idU_usu`) and (`i`.`fch_cta` = curdate())))) join `dboab`.`cservicio` `s` on(((`i`.`idS_cta` = `s`.`idS_srv`) and (`i`.`fch_cta` = curdate())))) join `dboab`.`mcliente` `l` on(((`l`.`idL_cli` = `i`.`idL_cta`) and (`i`.`fch_cta` = curdate())))) order by `i`.`hra_cta`;


--
-- View structure for view `dboab`.`vcitaspespecialista`
--

DROP VIEW IF EXISTS `vcitaspespecialista`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vcitaspespecialista` AS select `u`.`nom_usu` AS `usuario`,`e`.`idE_esp` AS `idEspecialista`,concat(`e`.`nom_esp`,`e`.`app_esp`,`e`.`apm_esp`) AS `especialista`,`i`.`idI_cta` AS `idCita`,`i`.`fch_cta` AS `fecha`,`i`.`hra_cta` AS `hora`,`s`.`idS_srv` AS `idServicio`,`s`.`des_srv` AS `servicio`,`s`.`dur_srv` AS `duracion`,concat(`l`.`nom_cli`,`l`.`app_cli`,`l`.`apm_cli`) AS `cliente` from ((((`dboab`.`dcita` `i` join `dboab`.`mespecialista` `e` on((`i`.`idE_cta` = `e`.`idE_esp`))) join `dboab`.`musuario` `u` on((`e`.`idU_esp` = `u`.`idU_usu`))) join `dboab`.`cservicio` `s` on((`i`.`idS_cta` = `s`.`idS_srv`))) join `dboab`.`mcliente` `l` on((`l`.`idL_cli` = `i`.`idL_cta`))) order by `i`.`fch_cta`,`i`.`hra_cta` desc;


--
-- View structure for view `dboab`.`vcitaspfecha`
--

DROP VIEW IF EXISTS `vcitaspfecha`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vcitaspfecha` AS select `i`.`idI_cta` AS `idCita`,`i`.`fch_cta` AS `fecha`,`i`.`hra_cta` AS `hora`,`e`.`idE_esp` AS `idEspecialista`,concat(`e`.`nom_esp`,`e`.`app_esp`,`e`.`apm_esp`) AS `especialista`,`s`.`idS_srv` AS `idServicio`,`s`.`des_srv` AS `servicio`,`s`.`dur_srv` AS `duracion`,concat(`l`.`nom_cli`,`l`.`app_cli`,`l`.`apm_cli`) AS `cliente` from ((((`dboab`.`dcita` `i` join `dboab`.`mespecialista` `e` on((`i`.`idE_cta` = `e`.`idE_esp`))) join `dboab`.`musuario` `u` on((`e`.`idU_esp` = `u`.`idU_usu`))) join `dboab`.`cservicio` `s` on((`i`.`idS_cta` = `s`.`idS_srv`))) join `dboab`.`mcliente` `l` on((`l`.`idL_cli` = `i`.`idL_cta`))) order by `i`.`hra_cta`;


--
-- View structure for view `dboab`.`vdatoscliente`
--

DROP VIEW IF EXISTS `vdatoscliente`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vdatoscliente` AS select `c`.`idL_cli` AS `idCliente`,`c`.`nom_cli` AS `nombreCliente`,`c`.`app_cli` AS `apellidoPaterno`,`c`.`apm_cli` AS `apellidoMaterno`,`o`.`idC_con` AS `idC_con`,`o`.`tel_con` AS `telefono`,`o`.`mal_con` AS `email`,`o`.`fbk_con` AS `facebook` from (`dboab`.`mcliente` `c` join `dboab`.`dcontacto` `o` on((`c`.`idC_cli` = `o`.`idC_con`))) order by `c`.`nom_cli`;


--
-- View structure for view `dboab`.`vnombreclientes`
--

DROP VIEW IF EXISTS `vnombreclientes`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vnombreclientes` AS select concat(`c`.`nom_cli`,`c`.`app_cli`,`c`.`apm_cli`) AS `nombreCompleto` from `dboab`.`mcliente` `c` order by `c`.`nom_cli`;


--
-- View structure for view `dboab`.`vservicios`
--

DROP VIEW IF EXISTS `vservicios`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vservicios` AS select `s`.`idS_srv` AS `idServicio`,`s`.`des_srv` AS `servicio`,`s`.`dur_srv` AS `duracion`,`e`.`idE_esp` AS `idEspecialista`,concat(`e`.`nom_esp`,`e`.`app_esp`,`e`.`apm_esp`) AS `especialista` from ((`dboab`.`cservespecialista` `a` join `dboab`.`cservicio` `s` on((`a`.`idS_sve` = `s`.`idS_srv`))) join `dboab`.`mespecialista` `e` on((`a`.`idE_sve` = `e`.`idE_esp`)));


--
-- View structure for view `dboab`.`vusuariosadmin`
--

DROP VIEW IF EXISTS `vusuariosadmin`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosadmin` AS select `a`.`idA_adm` AS `idAdmin`,`a`.`idU_adm` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`a`.`nom_adm` AS `nomAdmin`,`a`.`app_adm` AS `apellidoPaterno`,`a`.`apm_adm` AS `apellidoMaterno`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from ((`dboab`.`musuario` `u` join `dboab`.`madministrador` `a` on((`u`.`idU_usu` = `a`.`idU_adm`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `a`.`idC_adm`))) order by `a`.`idA_adm`;


--
-- View structure for view `dboab`.`vusuariosespecialista`
--

DROP VIEW IF EXISTS `vusuariosespecialista`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosespecialista` AS select `e`.`idE_esp` AS `idEspecialista`,`u`.`idU_usu` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`e`.`nom_esp` AS `nomGerente`,`e`.`app_esp` AS `apellidoPaterno`,`e`.`apm_esp` AS `apellidoMaterno`,`e`.`hrc_esp` AS `horaComida`,`t`.`idT_hrt` AS `idHorarioTrabajo`,`t`.`hre_hrt` AS `horaEntrada`,`t`.`hrs_hrt` AS `horaSalida`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from (((`dboab`.`musuario` `u` join `dboab`.`mespecialista` `e` on((`u`.`idU_usu` = `e`.`idU_esp`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `e`.`idC_esp`))) join `dboab`.`dhorariotrabajo` `t` on((`e`.`idT_esp` = `t`.`idT_hrt`))) order by `e`.`idE_esp`;


--
-- View structure for view `dboab`.`vusuariosespecialista_a`
--

DROP VIEW IF EXISTS `vusuariosespecialista_a`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosespecialista_a` AS select `e`.`idE_esp` AS `idEspecialista`,`u`.`idU_usu` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`e`.`nom_esp` AS `nomGerente`,`e`.`app_esp` AS `apellidoPaterno`,`e`.`apm_esp` AS `apellidoMaterno`,`e`.`hrc_esp` AS `horaComida`,`t`.`idT_hrt` AS `idHorarioTrabajo`,`t`.`hre_hrt` AS `horaEntrada`,`t`.`hrs_hrt` AS `horaSalida`,`p`.`idP_prv` AS `idPrivilegio`,`p`.`nom_prv` AS `privilegio`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from ((((`dboab`.`musuario` `u` join `dboab`.`mespecialista` `e` on((`u`.`idU_usu` = `e`.`idU_esp`))) join `dboab`.`cprivilegio` `p` on((`p`.`idP_prv` = `u`.`idP_usu`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `e`.`idC_esp`))) join `dboab`.`dhorariotrabajo` `t` on((`e`.`idT_esp` = `t`.`idT_hrt`))) order by `e`.`idE_esp`;


--
-- View structure for view `dboab`.`vusuariosgerente`
--

DROP VIEW IF EXISTS `vusuariosgerente`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosgerente` AS select `g`.`idG_gte` AS `idGerente`,`u`.`idU_usu` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`g`.`nom_gte` AS `nomGerente`,`g`.`app_gte` AS `apellidoPaterno`,`g`.`apm_gte` AS `apellidoMaterno`,`p`.`idP_prv` AS `idPrivilegio`,`p`.`nom_prv` AS `privilegio`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from (((`dboab`.`musuario` `u` join `dboab`.`mgerente` `g` on((`u`.`idU_usu` = `g`.`idU_gte`))) join `dboab`.`cprivilegio` `p` on((`p`.`idP_prv` = `u`.`idP_usu`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `g`.`idC_gte`))) order by `g`.`idG_gte`;


--
-- View structure for view `dboab`.`vusuariosrecepcionista`
--

DROP VIEW IF EXISTS `vusuariosrecepcionista`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosrecepcionista` AS select `r`.`idR_rta` AS `idRecepcionista`,`u`.`idU_usu` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`r`.`nom_rta` AS `nomGerente`,`r`.`app_rta` AS `apellidoPaterno`,`r`.`apm_rta` AS `apellidoMaterno`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from ((`dboab`.`musuario` `u` join `dboab`.`mrecepcionista` `r` on((`u`.`idU_usu` = `r`.`idU_rta`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `r`.`idC_rta`))) order by `r`.`idR_rta`;


--
-- View structure for view `dboab`.`vusuariosrecepcionista_a`
--

DROP VIEW IF EXISTS `vusuariosrecepcionista_a`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dboab`.`vusuariosrecepcionista_a` AS select `r`.`idR_rta` AS `idRecepcionista`,`u`.`idU_usu` AS `idUsuario`,`u`.`nom_usu` AS `nomUsuario`,`r`.`nom_rta` AS `nomGerente`,`r`.`app_rta` AS `apellidoPaterno`,`r`.`apm_rta` AS `apellidoMaterno`,`p`.`idP_prv` AS `idPrivilegio`,`p`.`nom_prv` AS `privilegio`,`c`.`idC_con` AS `idContacto`,`c`.`tel_con` AS `telefono`,`c`.`mal_con` AS `email`,`c`.`fbk_con` AS `facebook` from (((`dboab`.`musuario` `u` join `dboab`.`mrecepcionista` `r` on((`u`.`idU_usu` = `r`.`idU_rta`))) join `dboab`.`cprivilegio` `p` on((`p`.`idP_prv` = `u`.`idP_usu`))) join `dboab`.`dcontacto` `c` on((`c`.`idC_con` = `r`.`idC_rta`))) order by `r`.`idR_rta`;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
