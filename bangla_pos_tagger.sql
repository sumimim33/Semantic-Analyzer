-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 05, 2016 at 12:16 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bangla_pos_tagger`
--

-- --------------------------------------------------------

--
-- Table structure for table `table_of_words`
--

CREATE TABLE IF NOT EXISTS `table_of_words` (
  `sl` int(8) NOT NULL,
  `bangla_word` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pos` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tag` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `table_of_words`
--

INSERT INTO `table_of_words` (`sl`, `bangla_word`, `pos`, `tag`) VALUES
(1, 'গরু', 'Noun', 'N'),
(2, 'বই', 'Noun', 'N'),
(3, 'পড়', 'Verb', 'V'),
(4, 'ে', 'Auxiliary', 'AUX');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `table_of_words`
--
ALTER TABLE `table_of_words`
  ADD PRIMARY KEY (`sl`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `table_of_words`
--
ALTER TABLE `table_of_words`
  MODIFY `sl` int(8) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
