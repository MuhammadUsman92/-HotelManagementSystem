-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 25, 2020 at 02:09 PM
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
-- Table structure for table `food_table`
--

CREATE TABLE `food_table` (
  `name` varchar(55) NOT NULL,
  `price` varchar(55) DEFAULT NULL,
  `type` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food_table`
--

INSERT INTO `food_table` (`name`, `price`, `type`) VALUES
('Banana Shake', '100', 'Drink'),
('Beef', '350', 'Meal'),
('Beef Biryani', '250', 'Meal'),
('Biryani', '150', 'Meal'),
('Brownie', '150', 'Deserts'),
('Cake', '200', 'Deserts'),
('chai', '170', 'Drink'),
('Channa Chat', '150', 'Meal'),
('Chicken Acchari', '150', 'Meal'),
('Chicken Burger', '200', 'Meal'),
('Chicken Qorma', '250', 'Meal'),
('coffee', '70', 'Drink'),
('Daal Mash', '150', 'Meal'),
('Fruit Chat', '150', 'Deserts'),
('Ice Cream', '150', 'Deserts'),
('Mango Shake', '100', 'Drink'),
('Mutton', '500', 'Meal'),
('Mutton Biryani', '300', 'Meal'),
('Palao', '150', 'Meal'),
('Shwarma', '150', 'Meal'),
('Soda', '50', 'Drink'),
('Strawbery Shake', '150', 'Drink'),
('Tandori Pizza', '1200', 'Meal'),
('Zinger Burger', '150', 'Meal');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `food_table`
--
ALTER TABLE `food_table`
  ADD PRIMARY KEY (`name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
