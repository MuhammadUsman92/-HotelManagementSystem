-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 25, 2020 at 02:10 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `room_table`
--

CREATE TABLE `room_table` (
  `room_number` int(11) NOT NULL,
  `_avability` varchar(55) DEFAULT NULL,
  `room_type` varchar(55) DEFAULT NULL,
  `swimming_pool` varchar(55) DEFAULT NULL,
  `ac` varchar(55) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `rented_by` varchar(55) DEFAULT NULL,
  `number` varchar(55) DEFAULT NULL,
  `id_card` varchar(55) DEFAULT NULL,
  `in_date` varchar(55) DEFAULT NULL,
  `out_date` varchar(55) DEFAULT NULL,
  `food_charges` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room_table`
--

INSERT INTO `room_table` (`room_number`, `_avability`, `room_type`, `swimming_pool`, `ac`, `price`, `rented_by`, `number`, `id_card`, `in_date`, `out_date`, `food_charges`) VALUES
(2, 'YES', 'ONE BAD', 'NO', 'Yes', 6000, '', '', '', '', '', ''),
(3, 'YES', 'ONE BAD', 'NO', 'Yes', 6000, '', '', '', '', '', NULL),
(4, 'YES', 'ONE BAD', 'NO', 'Yes', 6000, '', '', '', '', '', NULL),
(5, 'YES', 'ONE BAD', 'NO', 'Yes', 6000, '', '', '', '', '', NULL),
(6, 'YES', 'ONE BAD', 'YES', 'Yes', 10000, '', '', '', '', '', NULL),
(7, 'YES', 'ONE BAD', 'YES', 'Yes', 10000, '', '', '', '', '', ''),
(8, 'YES', 'ONE BAD', 'YES', 'Yes', 10000, '', '', '', '', '', NULL),
(9, 'YES', 'ONE BAD', 'YES', 'Yes', 10000, '', '', '', '', '', NULL),
(10, 'YES', 'TWO BAD', 'YES', 'Yes', 15000, '', '', '', '', '', NULL),
(11, 'YES', 'TWO BAD', 'YES', 'Yes', 15000, '', '', '', '', '', NULL),
(12, 'YES', 'TWO BAD', 'YES', 'Yes', 15000, '', '', '', '', '', NULL),
(13, 'YES', 'TWO BAD', 'YES', 'Yes', 15000, '', '', '', '', '', NULL),
(14, 'YES', 'TWO BAD', 'No', 'Yes', 12000, '', '', '', '', '', NULL),
(15, 'YES', 'TWO BAD', 'No', 'Yes', 12000, '', '', '', '', '', NULL),
(16, 'YES', 'TWO BAD', 'No', 'Yes', 12000, '', '', '', '', '', NULL),
(17, 'YES', 'TWO BAD', 'No', 'Yes', 12000, '', '', '', '', '', NULL),
(18, 'YES', 'Family Room', 'No', 'Yes', 18000, '', '', '', '', '', NULL),
(19, 'YES', 'Family Room', 'No', 'Yes', 18000, '', '', '', '', '', NULL),
(20, 'YES', 'Family Room', 'No', 'Yes', 18000, '', '', '', '', '', NULL),
(21, 'YES', 'Family Room', 'No', 'Yes', 18000, '', '', '', '', '', NULL),
(22, 'YES', 'Family Room', 'YES', 'Yes', 22000, '', '', '', '', '', ''),
(23, 'NO', 'Family Room', 'YES', 'Yes', 22000, 'shafaq khan', '003300333', '1425346231', '25-06-2020', 'NOT LIVE', ''),
(24, 'YES', 'Family Room', 'YES', 'Yes', 22000, '', '', '', '', '', NULL),
(25, 'YES', 'Family Room', 'YES', 'Yes', 22000, '', '', '', '', '', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `room_table`
--
ALTER TABLE `room_table`
  ADD PRIMARY KEY (`room_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `room_table`
--
ALTER TABLE `room_table`
  MODIFY `room_number` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
