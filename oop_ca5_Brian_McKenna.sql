-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Apr 01, 2021 at 05:01 PM
-- Server version: 5.7.32
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oop_ca5_Brian_McKenna`
--
CREATE DATABASE IF NOT EXISTS `oop_ca5_Brian_McKenna` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `oop_ca5_Brian_McKenna`;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseid` varchar(10) NOT NULL,
  `level` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `institution` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`courseid`, `level`, `title`, `institution`) VALUES
('DK121', 7, 'Child Care', 'Dundalk IT'),
('DK821', 8, 'Computing', 'Dundalk IT'),
('MYN231', 8, 'International Law', 'Maynooth University');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `caoNumber` int(8) NOT NULL,
  `dob` date NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`caoNumber`, `dob`, `password`) VALUES
(12223231, '1991-04-22', 'byebye321'),
(12345678, '1993-03-06', 'hello123'),
(13248752, '1994-03-17', 'well246');

-- --------------------------------------------------------

--
-- Table structure for table `student_courses`
--

DROP TABLE IF EXISTS `student_courses`;
CREATE TABLE `student_courses` (
  `caoNumber` int(8) NOT NULL,
  `courseid` varchar(10) NOT NULL,
  `choiceNumber` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student_courses`
--

INSERT INTO `student_courses` (`caoNumber`, `courseid`, `choiceNumber`) VALUES
(12223231, 'DK121', 1),
(12223231, 'DK821', 2),
(12223231, 'MYN231', 3),
(12345678, 'DK121', 1),
(12345678, 'DK821', 2),
(12345678, 'MYN231', 3),
(13248752, 'DK121', 1),
(13248752, 'DK821', 2),
(13248752, 'MYN231', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`courseid`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`caoNumber`);

--
-- Indexes for table `student_courses`
--
ALTER TABLE `student_courses`
  ADD PRIMARY KEY (`caoNumber`,`courseid`),
  ADD KEY `courseid` (`courseid`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `student_courses`
--
ALTER TABLE `student_courses`
  ADD CONSTRAINT `student_courses_ibfk_1` FOREIGN KEY (`caoNumber`) REFERENCES `student` (`caoNumber`),
  ADD CONSTRAINT `student_courses_ibfk_2` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
