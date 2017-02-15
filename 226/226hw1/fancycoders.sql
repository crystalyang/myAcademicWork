-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 13, 2017 at 10:25 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fancycoders`
--

-- --------------------------------------------------------

--
-- Table structure for table `album`
--

CREATE TABLE `album` (
  `id` int(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `singer` varchar(100) NOT NULL,
  `style` varchar(50) NOT NULL,
  `gender` char(1) NOT NULL,
  `year` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `album`
--

INSERT INTO `album` (`id`, `name`, `singer`, `style`, `gender`, `year`) VALUES
(0, 'Blackfield V', 'Blackfield', 'rock', 'G', 2017),
(1, 'Only The Lonely', 'Colony House', 'rock', 'G', 2017),
(2, 'Joanne', 'Lady Gaga', 'pop', 'F', 2016),
(3, 'Blackstar', 'David Bowie', 'pop', 'M', 2016),
(4, 'Unbreakable', 'Janet Jackson', 'R&B', 'F', 2015),
(5, 'Code Red', 'Monica', 'R&B', 'F', 2015),
(6, 'Revival', 'Bellowhead', 'folk', 'G', 2014),
(7, 'Sweet Visitor', 'Nancy Kerr', 'folk', 'F', 2014),
(8, 'Revival', 'Selena Gomez', 'pop', 'F', 2015),
(9, 'Made in the A.M.', 'One Direction', 'pop', 'G', 2015),
(10, 'Hard II Love', 'Usher', 'R&B', 'M', 2016),
(11, 'Blue Mountain', 'Bob Weir', 'folk', 'M', 2016),
(12, 'A Girl A Bottle A Boat', 'Train', 'pop', 'G', 2017);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
