-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2015 at 09:44 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jgsp`
--

-- --------------------------------------------------------

--
-- Table structure for table `dan`
--

CREATE TABLE IF NOT EXISTS `dan` (
  `ID_DANA` int(20) NOT NULL,
  `DAN` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dan`
--

INSERT INTO `dan` (`ID_DANA`, `DAN`) VALUES
(1, 'Radni dan'),
(2, 'Subota'),
(3, 'Nedelja');

-- --------------------------------------------------------

--
-- Table structure for table `gradskiprevoz`
--

CREATE TABLE IF NOT EXISTS `gradskiprevoz` (
  `ID_DANA` int(20) NOT NULL,
  `ID_LINIJE` int(20) NOT NULL,
  `ID_SMER` int(20) NOT NULL,
  `vreme` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `linije`
--

CREATE TABLE IF NOT EXISTS `linije` (
  `ID_LINIJE` int(20) NOT NULL,
  `BROJ_LINIJE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `linije`
--

INSERT INTO `linije` (`ID_LINIJE`, `BROJ_LINIJE`) VALUES
(1, '1234567');

-- --------------------------------------------------------

--
-- Table structure for table `smer`
--

CREATE TABLE IF NOT EXISTS `smer` (
  `ID_SMER` int(20) NOT NULL,
  `SMER` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `smer`
--

INSERT INTO `smer` (`ID_SMER`, `SMER`) VALUES
(1, 'A'),
(2, 'B');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dan`
--
ALTER TABLE `dan`
 ADD PRIMARY KEY (`ID_DANA`), ADD UNIQUE KEY `ID_DANA` (`ID_DANA`);

--
-- Indexes for table `gradskiprevoz`
--
ALTER TABLE `gradskiprevoz`
 ADD PRIMARY KEY (`ID_LINIJE`,`ID_SMER`,`ID_DANA`), ADD KEY `ID_DANA` (`ID_DANA`), ADD KEY `ID_SMER` (`ID_SMER`);

--
-- Indexes for table `linije`
--
ALTER TABLE `linije`
 ADD PRIMARY KEY (`ID_LINIJE`), ADD UNIQUE KEY `ID_LINIJE` (`ID_LINIJE`);

--
-- Indexes for table `smer`
--
ALTER TABLE `smer`
 ADD PRIMARY KEY (`ID_SMER`), ADD UNIQUE KEY `ID_SMER` (`ID_SMER`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `gradskiprevoz`
--
ALTER TABLE `gradskiprevoz`
ADD CONSTRAINT `gradskiprevoz_ibfk_1` FOREIGN KEY (`ID_DANA`) REFERENCES `dan` (`ID_DANA`),
ADD CONSTRAINT `gradskiprevoz_ibfk_2` FOREIGN KEY (`ID_SMER`) REFERENCES `smer` (`ID_SMER`),
ADD CONSTRAINT `gradskiprevoz_ibfk_3` FOREIGN KEY (`ID_LINIJE`) REFERENCES `linije` (`ID_LINIJE`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
