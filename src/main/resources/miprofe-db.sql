-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.21 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for miprofe
CREATE DATABASE IF NOT EXISTS `miprofe` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `miprofe`;


-- Dumping structure for table miprofe.admin_profile_details
CREATE TABLE IF NOT EXISTS `admin_profile_details` (
  `admin_Profile_Id` int(11) NOT NULL AUTO_INCREMENT,
  `first_Name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_Name` varchar(255) DEFAULT NULL,
  `mobile_Number` varchar(255) DEFAULT NULL,
  `User_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`admin_Profile_Id`),
  KEY `FKBA3844BC64D72DFC` (`User_Id`),
  CONSTRAINT `FKBA3844BC64D72DFC` FOREIGN KEY (`User_Id`) REFERENCES `user` (`User_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.admin_profile_details: ~0 rows (approximately)
/*!40000 ALTER TABLE `admin_profile_details` DISABLE KEYS */;
INSERT INTO `admin_profile_details` (`admin_Profile_Id`, `first_Name`, `gender`, `last_Name`, `mobile_Number`, `User_Id`) VALUES
	(2, 'adm', NULL, 'asdasdas', '1111111111', 50);
/*!40000 ALTER TABLE `admin_profile_details` ENABLE KEYS */;


-- Dumping structure for table miprofe.booking_relation
CREATE TABLE IF NOT EXISTS `booking_relation` (
  `booking_relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `tutor_id` int(11) DEFAULT NULL,
  `booking_Id` int(11) DEFAULT NULL,
  `roomId` varchar(50) DEFAULT NULL,
  `roomName` varchar(50) DEFAULT NULL,
  `roomPassword` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`booking_relation_id`),
  KEY `FK__student_profile_details_booking` (`student_id`),
  KEY `FK__parent_profile_details_booking` (`parent_id`),
  KEY `FK__tutor_profile_detail_booking` (`tutor_id`),
  KEY `FK_booking_relation_booking_tutor` (`booking_Id`),
  CONSTRAINT `FK__parent_profile_details_booking` FOREIGN KEY (`parent_id`) REFERENCES `parent_profile_details` (`Parent_Id`),
  CONSTRAINT `FK__student_profile_details_booking` FOREIGN KEY (`student_id`) REFERENCES `student_profile_details` (`Student_Profile_Id`),
  CONSTRAINT `FK__tutor_profile_detail_booking` FOREIGN KEY (`tutor_id`) REFERENCES `tutor_profile_detail` (`Tutor_Profile_Id`),
  CONSTRAINT `FK_booking_relation_booking_tutor` FOREIGN KEY (`booking_Id`) REFERENCES `booking_tutor` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.booking_relation: ~220 rows (approximately)
/*!40000 ALTER TABLE `booking_relation` DISABLE KEYS */;
INSERT INTO `booking_relation` (`booking_relation_id`, `student_id`, `parent_id`, `tutor_id`, `booking_Id`, `roomId`, `roomName`, `roomPassword`) VALUES
	(3, 3, NULL, 2, 9, 't5hkwhc9', 'Miprofe: Algebra-Classroom', ''),
	(4, 3, NULL, 2, 10, '', 'Miprofe: Biomolecular-Classroom', ''),
	(5, 2, NULL, 2, 11, 'vkr38dmpt', 'Miprofe: Biomolecular-Classroom', ''),
	(6, 2, NULL, 2, 12, 'jrzp5f1s1', 'Miprofe: Acoustics-Classroom', ''),
	(7, 3, NULL, 2, 13, '4mtkx3wc', 'Miprofe: Acoustics-Classroom', ''),
	(8, 3, NULL, 2, 14, 'kzgs1d8', 'Miprofe: Trignometry-Classroom', ''),
	(9, 3, NULL, 2, 15, NULL, NULL, NULL),
	(10, 3, NULL, 2, 16, NULL, NULL, NULL),
	(11, 3, NULL, 2, 17, NULL, NULL, NULL),
	(12, 3, NULL, 2, 18, NULL, NULL, NULL),
	(13, 3, NULL, 2, 19, NULL, NULL, NULL),
	(14, 3, NULL, 2, 20, 'jjghc4zx', 'Miprofe: Trignometry-Classroom', ''),
	(15, 3, NULL, 2, 21, 'mq1ng3ss', 'Miprofe: Trignometry-Classroom', ''),
	(16, 3, NULL, 2, 22, 'jf55trmt', 'Miprofe: Biomolecular-Classroom', ''),
	(17, 3, NULL, 2, 23, 'vsnqxn0', 'Miprofe: Acoustics-Classroom', ''),
	(18, 3, NULL, 2, 24, 'rch6yybf', 'Miprofe: Algebra-Classroom', ''),
	(19, 3, NULL, 2, 25, 'wf1rt1tzr', 'Miprofe: Acoustics-Classroom', ''),
	(20, 3, NULL, 2, 26, 'fvmsc62qx', 'Miprofe: Acoustics-Classroom', ''),
	(21, 3, NULL, 2, 27, 't6kb0ff9', 'Miprofe: Trignometry-Classroom', ''),
	(22, 3, NULL, 2, 28, '8th7zzd', 'Miprofe: Trignometry-Classroom', ''),
	(23, 3, NULL, 2, 29, '98npgc5zc', 'Miprofe: Trignometry-Classroom', ''),
	(24, 14, NULL, 2, 30, NULL, NULL, NULL),
	(25, 16, NULL, 2, 31, NULL, NULL, NULL),
	(26, 16, NULL, 2, 32, NULL, NULL, NULL),
	(27, 16, NULL, 2, 33, NULL, NULL, NULL),
	(28, 16, NULL, 2, 34, NULL, NULL, NULL),
	(29, 16, NULL, 2, 35, NULL, NULL, NULL),
	(30, 16, NULL, 2, 36, NULL, NULL, NULL),
	(31, 16, NULL, 2, 37, NULL, NULL, NULL),
	(32, 16, NULL, 2, 38, NULL, NULL, NULL),
	(33, 16, NULL, 2, 39, NULL, NULL, NULL),
	(34, 16, NULL, 2, 40, NULL, NULL, NULL),
	(35, 17, NULL, 2, 41, NULL, NULL, NULL),
	(36, 18, NULL, 4, 42, 'c73fxfj', 'Miprofe: Acoustics-Classroom', ''),
	(37, 18, NULL, 2, 43, NULL, NULL, NULL),
	(38, 18, NULL, 4, 44, NULL, NULL, NULL),
	(39, 19, NULL, 5, 45, '5mqwhjpb', 'Miprofe: Calculus-Classroom', ''),
	(40, 19, NULL, 5, 46, 'mzqxqg286', 'Miprofe: Calculus-Classroom', ''),
	(41, 19, NULL, 5, 47, 'ngq2x0md', 'Miprofe: Calculus-Classroom', ''),
	(42, 19, NULL, 5, 48, NULL, NULL, NULL),
	(43, 20, NULL, 6, 49, 'uyxbt90', 'Miprofe: Trignometry-Classroom', ''),
	(44, 20, NULL, 6, 50, 'xps0q7b', 'Miprofe: Trignometry-Classroom', ''),
	(45, 20, NULL, 6, 51, '6tsdnbj', 'Miprofe: Trignometry-Classroom', ''),
	(46, 19, NULL, 2, 52, NULL, NULL, NULL),
	(47, 21, NULL, 8, 53, 'kjnux9t', 'Miprofe: Trignometry-Classroom', ''),
	(48, 22, NULL, 8, 54, 'jyuy2mzp8', 'Miprofe: Trignometry-Classroom', ''),
	(49, 22, NULL, 8, 55, '3wkktff', 'Miprofe: Trignometry-Classroom', ''),
	(50, 22, NULL, 8, 56, 'vc4z077q1', 'Miprofe: Trignometry-Classroom', ''),
	(51, 23, NULL, 9, 57, 'whbjx9fd', 'Miprofe: Acoustics-Classroom', ''),
	(52, 22, NULL, 9, 58, 'cfzkp42', 'Miprofe: Acoustics-Classroom', ''),
	(53, 23, NULL, 9, 59, 'us1yufp', 'Miprofe: Acoustics-Classroom', ''),
	(54, 23, NULL, 9, 60, 'bwgg7fuv', 'Miprofe: Acoustics-Classroom', ''),
	(55, 23, NULL, 9, 61, 'gc3gqp7ug', 'Miprofe: Acoustics-Classroom', ''),
	(56, 24, NULL, 10, 62, 'upq4ruu', 'Miprofe: Acoustics-Classroom', ''),
	(57, 24, NULL, 10, 63, 'bpb9bp97', 'Miprofe: Acoustics-Classroom', ''),
	(58, 24, NULL, 10, 64, 'nj6qqq4r', 'Miprofe: Acoustics-Classroom', ''),
	(59, 24, NULL, 10, 65, 'zggq1cs', 'Miprofe: Acoustics-Classroom', ''),
	(60, 24, NULL, 10, 66, 'z8wq8hh', 'Miprofe: Acoustics-Classroom', ''),
	(61, 24, NULL, 10, 67, NULL, NULL, NULL),
	(62, 24, NULL, 10, 68, 'b9kwrytr5', 'Miprofe: Acoustics-Classroom', ''),
	(63, 24, NULL, 10, 69, 'v7mburp', 'Miprofe: Acoustics-Classroom', ''),
	(64, 24, NULL, 10, 70, 'j6s2fhr8r', 'Miprofe: Acoustics-Classroom', ''),
	(65, 24, NULL, 6, 71, NULL, NULL, NULL),
	(66, 24, NULL, 10, 72, 'n73pczn', 'Miprofe: Acoustics-Classroom', ''),
	(67, 24, NULL, 10, 73, 'h3p9xcn2x', 'Miprofe: Acoustics-Classroom', ''),
	(68, 24, NULL, 10, 74, 'jfv466c', 'Miprofe: Acoustics-Classroom', ''),
	(69, 24, NULL, 10, 75, 'n2qpwtfg', 'Miprofe: Acoustics-Classroom', ''),
	(70, 24, NULL, 10, 76, '4yjrdmn', 'Miprofe: Acoustics-Classroom', ''),
	(71, 24, NULL, 10, 77, '1tnnvqdxq', 'Miprofe: Acoustics-Classroom', ''),
	(72, 24, NULL, 10, 78, '6xuvrx5m7', 'Miprofe: Acoustics-Classroom', ''),
	(73, 24, NULL, 10, 79, 'gmm94km', 'Miprofe: Acoustics-Classroom', ''),
	(74, 24, NULL, 10, 80, '2ncvu4uub', 'Miprofe: Acoustics-Classroom', ''),
	(75, 24, NULL, 10, 81, '4r2wkwp', 'Miprofe: Acoustics-Classroom', ''),
	(76, 24, NULL, 10, 82, 'tb4kvqqsu', 'Miprofe: Acoustics-Classroom', ''),
	(77, 24, NULL, 10, 83, 'drkncj8w', 'Miprofe: Acoustics-Classroom', ''),
	(78, 24, NULL, 10, 84, 'nq1gvdy7q', 'Miprofe: Acoustics-Classroom', ''),
	(79, 24, NULL, 10, 85, 'zrfbk2431', 'Miprofe: Acoustics-Classroom', ''),
	(80, 24, NULL, 10, 86, '8vxtysz61', 'Miprofe: Acoustics-Classroom', ''),
	(81, 24, NULL, 10, 87, 'nuj5skx', 'Miprofe: Acoustics-Classroom', ''),
	(82, 24, NULL, 9, 88, NULL, NULL, NULL),
	(83, 24, NULL, 3, 89, NULL, NULL, NULL),
	(84, 24, NULL, 10, 90, '0udwxtjnv', 'Miprofe: Acoustics-Classroom', ''),
	(85, 24, NULL, 5, 91, NULL, NULL, NULL),
	(86, 24, NULL, 7, 92, NULL, NULL, NULL),
	(87, 24, NULL, 10, 93, NULL, NULL, NULL),
	(88, 24, NULL, 2, 94, NULL, NULL, NULL),
	(89, 25, NULL, 9, 95, NULL, NULL, NULL),
	(90, 25, NULL, 6, 96, NULL, NULL, NULL),
	(91, 25, NULL, 11, 97, NULL, NULL, NULL),
	(92, 25, NULL, 2, 98, NULL, NULL, NULL),
	(93, 25, NULL, 2, 99, NULL, NULL, NULL),
	(94, 25, NULL, 3, 100, NULL, NULL, NULL),
	(95, 25, NULL, 2, 101, NULL, NULL, NULL),
	(96, 25, NULL, 2, 102, NULL, NULL, NULL),
	(97, 21, NULL, 2, 103, NULL, NULL, NULL),
	(98, 25, NULL, 2, 104, NULL, NULL, NULL),
	(99, 25, NULL, 2, 105, NULL, NULL, NULL),
	(100, 25, NULL, 2, 106, NULL, NULL, NULL),
	(101, 25, NULL, 2, 107, NULL, NULL, NULL),
	(102, 25, NULL, 2, 108, NULL, NULL, NULL),
	(103, 25, NULL, 8, 109, 'zrhwd0c', 'Miprofe: Trignometry-Classroom', ''),
	(104, 25, NULL, 8, 110, 'nx7zyqn5b', 'Miprofe: Trignometry-Classroom', ''),
	(105, 27, NULL, 12, 111, 'rfmk7jtb', 'Miprofe: Acoustics-Classroom', ''),
	(106, 27, NULL, 12, 112, 'b4pbvxcfq', 'Miprofe: Acoustics-Classroom', ''),
	(107, 27, NULL, 12, 113, NULL, NULL, NULL),
	(108, 27, NULL, 12, 114, NULL, NULL, NULL),
	(109, 27, NULL, 12, 115, 'pkqvrq8kv', 'Miprofe: Acoustics-Classroom', ''),
	(110, 27, NULL, 12, 116, 'rhdx0crf', 'Miprofe: Acoustics-Classroom', ''),
	(111, 27, NULL, 12, 117, '2cdd765x8', 'Miprofe: Acoustics-Classroom', ''),
	(112, 27, NULL, 12, 118, 'xcuudz148', 'Miprofe: Acoustics-Classroom', ''),
	(113, 27, NULL, 12, 119, '7wfqpsmsz', 'Miprofe: Acoustics-Classroom', ''),
	(114, 27, NULL, 12, 120, NULL, NULL, NULL),
	(115, 27, NULL, 12, 121, 'sj3xmsqq', 'Miprofe: Acoustics-Classroom', ''),
	(116, 27, NULL, 12, 122, '5nhfz1ygy', 'Miprofe: Acoustics-Classroom', ''),
	(117, 27, NULL, 12, 123, NULL, NULL, NULL),
	(118, 27, NULL, 12, 124, 'g99vtxwf', 'Miprofe: Acoustics-Classroom', ''),
	(119, 27, NULL, 12, 125, NULL, NULL, NULL),
	(120, 27, NULL, 13, 126, NULL, NULL, NULL),
	(121, 27, NULL, 12, 127, 'p2hfuqjzr', 'Miprofe: Acoustics-Classroom', ''),
	(122, 27, NULL, 12, 128, 'mmu12n6mm', 'Miprofe: Acoustics-Classroom', ''),
	(123, 27, NULL, 12, 129, 'mrdfqwk12', 'Miprofe: Acoustics-Classroom', ''),
	(124, 27, NULL, 12, 130, '8t4qsu0zj', 'Miprofe: Acoustics-Classroom', ''),
	(125, 26, NULL, 12, 131, 'zcb85t9', 'Miprofe: Acoustics-Classroom', ''),
	(126, 27, NULL, 12, 132, 'qmxpxtx2y', 'Miprofe: Acoustics-Classroom', ''),
	(127, 27, NULL, 12, 133, 'q2upz2w', 'Miprofe: Acoustics-Classroom', ''),
	(128, 27, NULL, 12, 134, 'xrd4gvg', 'Miprofe: Acoustics-Classroom', ''),
	(129, 27, NULL, 12, 135, 'f3fruw0z0', 'Miprofe: Acoustics-Classroom', ''),
	(130, 27, NULL, 12, 136, 'h9utkzmr', 'Miprofe: Acoustics-Classroom', ''),
	(131, 27, NULL, 2, 137, NULL, NULL, NULL),
	(132, 27, NULL, 5, 138, NULL, NULL, NULL),
	(133, 27, NULL, 8, 139, NULL, NULL, NULL),
	(134, 27, NULL, 12, 140, NULL, NULL, NULL),
	(135, 27, NULL, 4, 141, NULL, NULL, NULL),
	(136, 27, NULL, 4, 142, NULL, NULL, NULL),
	(137, 27, NULL, 2, 143, NULL, NULL, NULL),
	(138, 27, NULL, 12, 144, 'zx6pnb6', 'Miprofe: Acoustics-Classroom', ''),
	(139, 27, NULL, 2, 145, NULL, NULL, NULL),
	(140, 27, NULL, 2, 146, NULL, NULL, NULL),
	(141, 26, NULL, 2, 147, NULL, NULL, NULL),
	(142, 26, NULL, 3, 148, NULL, NULL, NULL),
	(143, 26, NULL, 6, 149, NULL, NULL, NULL),
	(144, 26, NULL, 12, 150, NULL, NULL, NULL),
	(145, 26, NULL, 5, 151, NULL, NULL, NULL),
	(146, 27, NULL, 12, 152, 'tgys4vdpu', 'Miprofe: Acoustics-Classroom', ''),
	(147, 27, NULL, 12, 153, 'k92whjwqc', 'Miprofe: Acoustics-Classroom', ''),
	(148, 27, NULL, 12, 154, 'wzkj3r6y', 'Miprofe: Acoustics-Classroom', ''),
	(149, 26, NULL, 12, 155, '41g9bcscy', 'Miprofe: Acoustics-Classroom', ''),
	(150, 26, NULL, 12, 156, 'vb9yp84h', 'Miprofe: Acoustics-Classroom', ''),
	(151, 26, NULL, 12, 157, '9mpkdgss', 'Miprofe: Acoustics-Classroom', ''),
	(152, 26, NULL, 12, 158, '7t2c1kn', 'Miprofe: Acoustics-Classroom', ''),
	(153, 26, NULL, 12, 159, '0pz9m6mtx', 'Miprofe: Acoustics-Classroom', ''),
	(154, 26, NULL, 12, 160, '8vr68nqx', 'Miprofe: Acoustics-Classroom', ''),
	(155, 26, NULL, 12, 161, 'jnnt6mb4', 'Miprofe: Acoustics-Classroom', ''),
	(156, 26, NULL, 12, 162, 'v4xbhpxm', 'Miprofe: Acoustics-Classroom', ''),
	(157, 26, NULL, 12, 163, 'kzfur7cg', 'Miprofe: Acoustics-Classroom', ''),
	(158, 26, NULL, 12, 164, 'rt8pz1b3u', 'Miprofe: Acoustics-Classroom', ''),
	(159, 26, NULL, 12, 165, '77fdfvch', 'Miprofe: Acoustics-Classroom', ''),
	(160, 26, NULL, 12, 166, 'j9nphrpw', 'Miprofe: Acoustics-Classroom', ''),
	(161, 26, NULL, 12, 167, '2f9q5qcf', 'Miprofe: Acoustics-Classroom', ''),
	(162, 26, NULL, 12, 168, 'vcwjy1cs', 'Miprofe: Acoustics-Classroom', ''),
	(163, 26, NULL, 12, 169, 'z5s4rgj', 'Miprofe: Acoustics-Classroom', ''),
	(164, 26, NULL, 12, 170, '0npdwux', 'Miprofe: Acoustics-Classroom', ''),
	(165, 26, NULL, 12, 171, 'kukz90z', 'Miprofe: Acoustics-Classroom', ''),
	(166, 26, NULL, 12, 172, 'd5ghgjzm', 'Miprofe: Acoustics-Classroom', ''),
	(167, 26, NULL, 12, 173, 'ngc8mssb0', 'Miprofe: Acoustics-Classroom', ''),
	(168, 26, NULL, 12, 174, 'jdrmz6z8', 'Miprofe: Acoustics-Classroom', ''),
	(169, 26, NULL, 12, 175, '34nwtwkm', 'Miprofe: Acoustics-Classroom', ''),
	(170, 29, NULL, 13, 176, '7kuwzy3n', 'Miprofe: Trignometry-Classroom', ''),
	(171, 29, NULL, 13, 177, NULL, NULL, NULL),
	(172, 29, NULL, 13, 178, 'guntmb1g', 'Miprofe: Trignometry-Classroom', ''),
	(173, 29, NULL, 12, 179, NULL, NULL, NULL),
	(174, 29, NULL, 13, 180, 'hcwx0hmkx', 'Miprofe: Trignometry-Classroom', ''),
	(175, 29, NULL, 13, 181, 'hquzjc6', 'Miprofe: Trignometry-Classroom', ''),
	(176, 27, NULL, 12, 182, 's06qc3djj', 'Miprofe: Acoustics-Classroom', ''),
	(177, 27, NULL, 12, 183, '0ft2fqvk', 'Miprofe: Acoustics-Classroom', ''),
	(178, 27, NULL, 12, 184, 'fdm5tfz', 'Miprofe: Acoustics-Classroom', ''),
	(179, 27, NULL, 13, 185, 'rjdfcv47g', 'Miprofe: Trignometry-Classroom', ''),
	(180, 27, NULL, 12, 186, 'b5ushrnm', 'Miprofe: Astronomy-Classroom', ''),
	(181, 27, NULL, 12, 187, '4cqsjws', 'Miprofe: Acoustics-Classroom', ''),
	(182, 27, NULL, 12, 188, 'nu1u6t30', 'Miprofe: Acoustics-Classroom', ''),
	(183, 27, NULL, 12, 189, 'jcvgxh3', 'Miprofe: Acoustics-Classroom', ''),
	(184, 27, NULL, 12, 190, 't0vpz74b0', 'Miprofe: Acoustics-Classroom', ''),
	(185, 27, NULL, 12, 191, 'bj6gdxxjd', 'Miprofe: Acoustics-Classroom', ''),
	(186, 27, NULL, 12, 192, 'unvuf4f', 'Miprofe: Acoustics-Classroom', ''),
	(187, 27, NULL, 12, 193, 'jn7yy5xy', 'Miprofe: Astronomy-Classroom', ''),
	(188, 27, NULL, 12, 194, NULL, NULL, NULL),
	(189, 27, NULL, 12, 195, '64vtnjjdk', 'Miprofe: Acoustics-Classroom', ''),
	(190, 27, NULL, 12, 196, 'brkpq9x1', 'Miprofe: Acoustics-Classroom', ''),
	(191, 27, NULL, 12, 197, 'hxhh8wrbr', 'Miprofe: Acoustics-Classroom', ''),
	(192, 27, NULL, 12, 198, NULL, NULL, NULL),
	(193, 27, NULL, 12, 199, NULL, NULL, NULL),
	(194, 27, NULL, 12, 200, 'mkmxjfk3f', 'Miprofe: Acoustics-Classroom', ''),
	(195, 27, NULL, 12, 201, '2v7b1szh', 'Miprofe: Calculus-Classroom', ''),
	(196, 27, NULL, 12, 202, 'kvmh7gr', 'Miprofe: Algebra-Classroom', ''),
	(197, 27, NULL, 7, 203, NULL, NULL, NULL),
	(198, 27, NULL, 13, 204, NULL, NULL, NULL),
	(199, 27, NULL, 7, 205, NULL, NULL, NULL),
	(200, 27, NULL, 12, 206, 'qtubpr30k', 'Miprofe: Acoustics-Classroom', ''),
	(201, 27, NULL, 12, 207, 'z2z1ffpwh', 'Miprofe: Acoustics-Classroom', ''),
	(202, 27, NULL, 12, 208, 'm78dztqxc', 'Miprofe: Biomolecular-Classroom', ''),
	(203, 27, NULL, 12, 209, '37mv8pt', 'Miprofe: Calculus-Classroom', ''),
	(204, 27, NULL, 12, 210, NULL, NULL, NULL),
	(205, 27, NULL, 12, 211, NULL, NULL, NULL),
	(206, 27, NULL, 12, 212, 'hz7r75qpu', 'Miprofe: Biomolecular-Classroom', ''),
	(207, 27, NULL, 12, 213, '6n03pdf', 'Miprofe: Biomolecular-Classroom', ''),
	(208, 27, NULL, 12, 214, 'h9vsfhwt', 'Miprofe: Biomolecular-Classroom', ''),
	(209, 27, NULL, 12, 215, 'ugxmps2', 'Miprofe: Biomolecular-Classroom', ''),
	(210, 27, NULL, 12, 216, 'wgutg09r', 'Miprofe: Acoustics-Classroom', ''),
	(211, 27, NULL, 12, 217, 'r7zzrjvf', 'Miprofe: Acoustics-Classroom', ''),
	(212, 27, NULL, 12, 218, 'ptk3twfwm', 'Miprofe: Acoustics-Classroom', ''),
	(213, 27, NULL, 12, 219, '9svq8qnqu', 'Miprofe: Calculus-Classroom', ''),
	(214, 57, NULL, 7, 220, NULL, NULL, NULL),
	(216, 57, NULL, 18, 222, 'tuwthyh1', 'Miprofe: Spanish-Classroom', ''),
	(219, 57, NULL, 18, 225, 'ppstj5px', 'Miprofe: Spanish-Classroom', ''),
	(220, 57, NULL, 18, 226, 'zb26kufm', 'Miprofe: Spanish-Classroom', ''),
	(231, 57, NULL, 18, 237, 'kpxhv8z4w', 'Miprofe: Spanish-Classroom', ''),
	(247, 57, NULL, 16, 253, NULL, NULL, NULL),
	(248, 57, NULL, 18, 254, 'qv21dkk6', 'Miprofe: Spanish-Classroom', ''),
	(249, 57, NULL, 18, 255, '6c35pmjws', 'Miprofe: Spanish-Classroom', ''),
	(250, 57, NULL, 18, 256, 'pgfzz2sb', 'Miprofe: Spanish-Classroom', '');
/*!40000 ALTER TABLE `booking_relation` ENABLE KEYS */;


-- Dumping structure for table miprofe.booking_tutor
CREATE TABLE IF NOT EXISTS `booking_tutor` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) DEFAULT NULL,
  `booking_date` timestamp NULL DEFAULT NULL,
  `accepted` varchar(2) DEFAULT 'N',
  `is_completed` varchar(2) DEFAULT 'N',
  `is_deleted` varchar(2) DEFAULT 'N',
  `booking_duration` varchar(20) DEFAULT NULL,
  `subject_type_master_id` int(11) DEFAULT NULL,
  `tutor_joined_time` timestamp NULL DEFAULT NULL,
  `student_joined_time` timestamp NULL DEFAULT NULL,
  `meeting_endtime` timestamp NULL DEFAULT NULL,
  `student_leave_time` timestamp NULL DEFAULT NULL,
  `tutor_leave_time` timestamp NULL DEFAULT NULL,
  `chat_history` text,
  PRIMARY KEY (`booking_id`),
  KEY `FK__subjects` (`subject_id`),
  KEY `FK_booking_tutor_subject_type_master` (`subject_type_master_id`),
  CONSTRAINT `FK__subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`Subjects_Id`),
  CONSTRAINT `FK_booking_tutor_subject_type_master` FOREIGN KEY (`subject_type_master_id`) REFERENCES `subject_type_master` (`Subject_Type_Master_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.booking_tutor: ~220 rows (approximately)
/*!40000 ALTER TABLE `booking_tutor` DISABLE KEYS */;
INSERT INTO `booking_tutor` (`booking_id`, `subject_id`, `booking_date`, `accepted`, `is_completed`, `is_deleted`, `booking_duration`, `subject_type_master_id`, `tutor_joined_time`, `student_joined_time`, `meeting_endtime`, `student_leave_time`, `tutor_leave_time`, `chat_history`) VALUES
	(9, 9, '2015-04-22 03:08:00', 'Y', 'Y', 'N', '60', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(10, 14, '2015-04-10 12:15:00', 'Y', 'Y', 'N', '120', 5, NULL, NULL, NULL, NULL, NULL, NULL),
	(11, 14, '2015-04-06 12:26:00', 'Y', 'Y', 'N', '30', 5, NULL, NULL, NULL, NULL, NULL, NULL),
	(12, 10, '2015-04-08 06:19:00', 'Y', 'Y', 'N', '60', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(13, 10, '2015-04-22 04:04:00', 'Y', 'Y', 'N', '60', 4, NULL, NULL, '2015-04-22 04:09:54', NULL, NULL, NULL),
	(14, 7, '2015-04-22 06:12:00', 'Y', 'Y', 'N', '45', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(15, 7, '2015-04-22 01:00:00', 'Y', 'Y', 'N', '45', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(16, 7, '2015-04-30 01:09:00', 'Y', 'Y', 'N', '30', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(17, 7, '2015-04-26 16:15:00', 'Y', 'Y', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(18, 7, '2015-04-27 21:00:00', 'Y', 'Y', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(19, 10, '2015-04-22 01:35:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(20, 7, '2015-04-22 02:00:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(21, 7, '2015-04-22 03:45:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(22, 14, '2015-04-22 04:30:00', 'Y', 'N', 'N', '30', 5, NULL, '2015-04-22 04:33:05', NULL, NULL, NULL, NULL),
	(23, 10, '2015-04-22 05:29:00', 'Y', 'Y', 'Y', '30', 4, '2015-04-22 11:48:36', '2015-04-22 11:51:05', '2015-04-22 05:30:23', NULL, NULL, NULL),
	(24, 9, '2015-04-22 05:38:00', 'Y', 'N', 'N', '60', 3, '2015-04-22 05:37:32', NULL, NULL, NULL, NULL, NULL),
	(25, 10, '2015-04-22 05:49:00', 'Y', 'N', 'N', '15', 4, '2015-04-22 11:50:15', '2015-04-22 11:51:06', NULL, NULL, NULL, NULL),
	(26, 10, '2015-04-22 11:38:00', 'Y', 'Y', 'Y', '15', 4, NULL, '2015-04-22 11:40:38', '2015-04-22 11:40:35', NULL, NULL, NULL),
	(27, 7, '2015-04-22 13:30:00', 'Y', 'Y', 'Y', '15', 3, '2015-04-22 11:55:23', '2015-04-22 13:31:41', '2015-04-22 12:05:35', NULL, NULL, NULL),
	(28, 7, '2015-04-23 05:26:00', 'Y', 'Y', 'Y', '30', 3, '2015-04-23 05:26:59', '2015-04-23 05:11:29', '2015-04-23 03:55:12', NULL, NULL, NULL),
	(29, 7, '2015-04-23 14:44:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(30, 7, '2015-04-29 19:15:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(31, 7, '2015-04-24 04:31:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(32, NULL, '2015-04-24 04:32:00', 'N', 'N', 'N', '15', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(33, NULL, '2015-04-24 04:36:00', 'N', 'N', 'N', '15', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(34, NULL, '2015-04-24 04:40:00', 'N', 'N', 'N', '15', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(35, NULL, '2015-04-24 04:41:00', 'N', 'N', 'N', '15', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(36, NULL, '2015-04-24 04:42:00', 'N', 'N', 'N', '15', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(37, 7, '2015-04-24 04:42:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(38, NULL, '2015-04-24 04:49:00', 'N', 'N', 'N', '15', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(39, NULL, '2015-04-24 04:51:00', 'N', 'N', 'N', '15', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(40, 7, '2015-04-24 05:30:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(41, 7, '2015-04-28 19:00:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(42, 10, '2015-04-23 23:37:00', 'Y', 'Y', 'Y', '15', 4, '2015-04-24 05:07:52', '2015-04-24 05:07:44', '2015-04-24 05:08:43', NULL, NULL, NULL),
	(43, 7, '2015-04-24 05:10:00', 'N', 'N', 'N', '30', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(44, 10, '2015-04-24 00:07:00', 'N', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(45, 8, '2015-04-24 05:51:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(46, 8, '2015-04-24 00:36:00', 'Y', 'Y', 'Y', '15', 3, '2015-04-24 00:36:46', '2015-04-24 00:36:53', '2015-04-24 00:43:05', NULL, NULL, NULL),
	(47, 8, '2015-04-24 00:44:00', 'Y', 'N', 'N', '15', 3, '2015-04-24 00:44:33', NULL, NULL, NULL, NULL, NULL),
	(48, 8, '2015-04-24 03:33:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(49, 7, '2015-04-27 00:41:20', 'Y', 'Y', 'Y', '60', 3, '2015-04-27 06:07:27', '2015-04-27 06:13:16', '2015-04-27 06:16:14', NULL, NULL, NULL),
	(50, 7, '2015-04-27 01:30:00', 'Y', 'Y', 'Y', '15', 3, '2015-04-27 07:08:14', NULL, '2015-04-27 07:08:42', NULL, NULL, NULL),
	(51, 7, '2015-04-27 01:44:00', 'Y', 'Y', 'Y', '15', 3, NULL, '2015-04-27 07:18:07', '2015-04-27 07:22:34', NULL, NULL, NULL),
	(52, 7, '2015-04-29 13:30:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(53, 7, '2015-04-28 09:12:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(54, 7, '2015-04-28 04:42:00', 'Y', 'Y', 'Y', '15', 3, '2015-04-28 10:04:34', '2015-04-28 04:51:04', '2015-04-28 04:55:17', NULL, NULL, NULL),
	(55, 7, '2015-04-28 04:55:00', 'Y', 'N', 'N', '30', 3, '2015-04-28 04:56:39', '2015-04-28 04:56:21', NULL, NULL, NULL, NULL),
	(56, 7, '2015-04-28 06:32:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(57, 10, '2015-04-28 07:58:00', 'Y', 'Y', 'Y', '15', 4, '2015-04-28 13:32:13', '2015-04-28 13:56:33', '2015-04-28 13:57:11', NULL, NULL, NULL),
	(58, 10, '2015-04-28 07:57:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(59, 10, '2015-04-28 08:28:00', 'Y', 'Y', 'Y', '15', 4, '2015-04-28 13:59:29', '2015-04-28 13:59:47', '2015-04-28 14:00:00', NULL, NULL, NULL),
	(60, 10, '2015-04-28 08:47:00', 'Y', 'Y', 'Y', '15', 4, NULL, '2015-04-28 14:17:43', '2015-04-28 14:17:53', NULL, NULL, NULL),
	(61, 10, '2015-04-28 09:10:00', 'Y', 'N', 'N', '15', 4, '2015-04-28 14:40:32', NULL, NULL, NULL, NULL, NULL),
	(62, 10, '2015-04-29 09:34:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(63, 10, '2015-04-29 00:13:00', 'Y', 'Y', 'Y', '15', 4, '2015-04-29 00:13:27', '2015-04-29 00:13:28', '2015-04-29 00:13:46', NULL, NULL, NULL),
	(64, 10, '2015-04-29 00:14:00', 'Y', 'Y', 'Y', '15', 4, '2015-04-29 00:14:29', '2015-04-29 00:14:31', '2015-04-29 00:16:21', NULL, NULL, NULL),
	(65, 10, '2015-04-29 00:16:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(66, 10, '2015-04-29 00:31:00', 'Y', 'Y', 'Y', '15', 4, '2015-04-29 00:32:22', '2015-04-29 00:32:22', '2015-04-29 00:33:08', NULL, NULL, NULL),
	(67, 10, '2015-04-29 10:03:00', 'N', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(68, 10, '2015-05-01 00:56:00', 'Y', 'Y', 'Y', '60', 4, NULL, '2015-05-01 06:27:18', '2015-05-01 06:28:31', NULL, NULL, NULL),
	(69, 10, '2015-05-01 03:56:00', 'Y', 'Y', 'Y', '30', 4, '2015-05-01 09:26:50', NULL, '2015-05-01 09:27:12', NULL, '2015-05-01 09:27:12', NULL),
	(70, 10, '2015-05-01 04:00:00', 'Y', 'Y', 'Y', '60', 4, '2015-05-01 09:31:08', NULL, '2015-05-01 09:32:36', NULL, '2015-05-01 09:32:36', NULL),
	(71, 7, '2015-05-01 15:04:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(72, 10, '2015-05-04 04:34:00', 'Y', 'N', 'N', '15', 4, NULL, '2015-05-04 10:11:42', NULL, NULL, NULL, NULL),
	(73, 10, '2015-05-05 00:55:00', 'Y', 'Y', 'Y', '1', 4, '2015-05-05 06:34:53', '2015-05-05 06:34:58', '2015-05-05 06:36:07', '2015-05-05 06:36:15', '2015-05-05 06:36:07', NULL),
	(74, 10, '2015-05-04 06:22:00', 'Y', 'Y', 'Y', '60', 4, '2015-05-04 12:00:34', '2015-05-04 11:58:23', '2015-05-04 12:02:46', NULL, '2015-05-04 12:02:46', NULL),
	(75, 10, '2015-05-05 00:02:00', 'Y', 'Y', 'Y', '01', 4, '2015-05-05 05:17:48', '2015-05-05 06:23:36', '2015-05-05 06:25:02', '2015-05-05 06:25:02', NULL, NULL),
	(76, 10, '2015-05-05 04:15:00', 'Y', 'N', 'N', '3', 4, '2015-05-05 07:09:10', '2015-05-05 07:09:04', '2015-05-05 08:18:58', '2015-05-05 08:18:58', '2015-05-05 08:57:32', NULL),
	(77, 10, '2015-05-05 04:50:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-05 04:48:25', '2015-05-05 04:47:41', '2015-05-05 05:04:00', '2015-05-05 05:04:07', '2015-05-05 05:04:00', NULL),
	(78, 10, '2015-05-05 05:15:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-05 05:09:36', '2015-05-05 05:09:32', '2015-05-05 05:27:11', '2015-05-05 05:27:16', '2015-05-05 05:27:11', NULL),
	(79, 10, '2015-05-05 12:06:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(80, 10, '2015-05-05 12:52:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(81, 10, '2015-05-05 13:07:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(82, 10, '2015-05-05 05:09:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(83, 10, '2015-05-05 03:07:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(84, 10, '2015-05-05 03:18:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(85, 10, '2015-05-06 03:24:00', 'Y', 'N', 'N', '15', 4, '2015-05-06 03:23:48', '2015-05-06 03:23:43', NULL, NULL, NULL, NULL),
	(86, 10, '2015-05-06 03:46:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-06 09:15:20', '2015-05-06 09:15:15', '2015-05-06 09:18:26', '2015-05-06 09:18:26', NULL, NULL),
	(87, 10, '2015-05-06 03:55:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-06 09:21:28', '2015-05-06 09:22:38', '2015-05-06 09:39:23', NULL, '2015-05-06 09:39:23', NULL),
	(88, 10, '2015-05-14 04:42:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(89, 16, '2015-05-30 04:42:00', 'N', 'N', 'N', '15', 6, NULL, NULL, NULL, NULL, NULL, NULL),
	(90, 10, '2015-05-29 05:56:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(91, 8, '2015-05-28 06:41:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(92, 13, '2015-05-27 07:07:00', 'Y', 'N', 'N', '15', 5, NULL, NULL, NULL, NULL, NULL, NULL),
	(93, 10, '2015-05-07 00:21:00', 'N', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(94, 10, '2015-05-07 00:22:00', 'N', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(95, 10, '2015-05-07 11:56:00', 'N', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(96, 7, '2015-05-07 11:58:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(97, 10, '2015-05-08 07:27:00', 'N', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(98, 7, '2015-05-08 01:50:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(99, 7, '2015-05-07 19:59:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(100, 16, '2015-05-07 20:03:00', 'N', 'N', 'N', '15', 6, NULL, NULL, NULL, NULL, NULL, NULL),
	(101, 7, '2015-05-07 20:07:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(102, 7, '2015-05-08 02:11:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(103, 7, '2015-05-08 01:14:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(104, 7, '2015-05-08 02:20:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(105, 7, '2015-05-08 07:37:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(106, 7, '2015-05-08 05:43:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(107, 7, '2015-05-08 05:32:00', 'N', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(108, 7, '2015-05-08 05:33:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(109, 7, '2015-05-07 02:29:00', 'Y', 'Y', 'Y', '15', 3, '2015-05-07 02:45:55', NULL, '2015-05-07 02:29:23', NULL, '2015-05-07 02:29:23', NULL),
	(110, 7, '2015-05-07 01:58:00', 'Y', 'Y', 'Y', '15', 3, '2015-05-07 01:57:20', '2015-05-07 01:52:59', '2015-05-07 02:26:01', NULL, '2015-05-07 02:26:01', NULL),
	(111, 10, '2015-05-07 03:01:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-07 02:53:56', '2015-05-07 02:55:50', '2015-05-07 03:01:21', NULL, '2015-05-07 03:01:21', NULL),
	(112, 10, '2015-05-07 03:15:00', 'Y', 'Y', 'Y', '1', 4, '2015-05-07 03:13:29', '2015-05-07 03:11:03', '2015-05-07 03:15:40', NULL, '2015-05-07 03:15:40', NULL),
	(113, 10, '2015-05-07 03:19:00', 'N', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(114, 10, '2015-05-06 03:34:00', 'N', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(115, 10, '2015-05-06 03:35:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(116, 10, '2015-05-07 03:36:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(117, 10, '2015-05-08 04:35:00', 'Y', 'Y', 'N', '60', 4, '2015-05-07 04:35:32', '2015-05-07 04:08:25', NULL, NULL, NULL, NULL),
	(118, 10, '2015-05-22 05:28:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(119, 10, '2015-05-06 05:44:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(120, 10, '2015-05-25 05:45:00', 'N', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(121, 10, '2015-07-18 05:55:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(122, 10, '2015-05-07 05:29:00', 'Y', 'Y', 'Y', '1', 4, '2015-05-07 05:29:00', '2015-05-07 05:30:19', '2015-05-07 05:31:03', NULL, '2015-05-07 05:31:03', NULL),
	(123, 10, '2015-05-07 05:31:00', 'N', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(124, 10, '2015-05-07 05:40:00', 'Y', 'Y', 'Y', '1', 4, '2015-05-07 05:32:38', '2015-05-07 05:32:42', '2015-05-07 05:34:13', '2015-05-07 05:34:13', '2015-05-07 05:34:25', NULL),
	(125, 10, '2015-05-28 07:10:00', 'N', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(126, 10, '2015-05-07 23:18:00', 'N', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(127, 10, '2015-05-07 23:45:00', 'Y', 'Y', 'N', '15', 4, '2015-05-07 23:41:50', '2015-05-07 23:42:41', NULL, NULL, NULL, NULL),
	(128, 10, '2015-05-07 23:25:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-07 23:28:54', '2015-05-07 23:26:58', '2015-05-07 23:39:07', '2015-05-07 23:40:02', '2015-05-07 23:39:07', NULL),
	(129, 10, '2015-05-08 18:13:00', 'Y', 'Y', 'N', '60', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(130, 10, '2015-05-08 01:15:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-08 01:10:59', '2015-05-08 01:10:42', '2015-05-08 01:11:54', NULL, '2015-05-08 01:11:54', NULL),
	(131, 10, '2015-05-08 01:19:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-08 01:15:09', '2015-05-08 01:23:33', '2015-05-08 01:17:36', NULL, '2015-05-08 01:17:36', NULL),
	(132, 10, '2015-05-08 01:29:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-08 01:25:24', '2015-05-08 01:25:21', '2015-05-08 01:26:41', NULL, '2015-05-08 01:26:41', NULL),
	(133, 10, '2015-05-08 01:31:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-08 01:29:57', '2015-05-08 01:38:12', '2015-05-08 01:30:28', NULL, '2015-05-08 01:30:28', NULL),
	(134, 10, '2015-05-08 01:42:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-08 01:43:04', '2015-05-08 01:45:28', '2015-05-08 01:43:49', '2015-05-08 01:45:42', '2015-05-08 01:43:49', NULL),
	(135, 10, '2015-05-08 01:48:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-08 01:47:02', '2015-05-08 01:50:00', '2015-05-08 01:48:16', '2015-05-08 01:51:05', '2015-05-08 01:48:16', NULL),
	(136, 10, '2015-05-08 01:59:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-08 01:57:35', '2015-05-08 01:56:38', '2015-05-08 01:58:24', '2015-05-08 01:58:24', '2015-05-08 02:00:17', NULL),
	(137, 7, '2015-05-11 03:45:00', 'N', 'Y', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(138, 8, '2015-05-11 05:02:00', 'N', 'Y', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(139, 7, '2015-05-13 05:08:00', 'N', 'Y', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(140, 10, '2015-05-30 06:56:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(141, 10, '2015-05-22 06:58:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(142, 10, '2015-05-15 06:43:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(143, 10, '2015-05-16 05:14:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(144, 10, '2015-05-23 00:53:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(145, 14, '2015-12-01 04:49:00', 'N', 'Y', 'N', '15', 5, NULL, NULL, NULL, NULL, NULL, NULL),
	(146, 10, '2015-05-15 05:52:00', 'N', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(147, 10, '2015-05-29 06:41:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(148, 16, '2015-05-15 06:39:00', 'Y', 'Y', 'N', '15', 6, NULL, NULL, NULL, NULL, NULL, NULL),
	(149, 7, '2015-05-30 07:26:00', 'Y', 'Y', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(150, 10, '2015-05-29 07:28:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(151, 8, '2015-05-22 07:14:00', 'Y', 'Y', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(152, 10, '2015-05-16 05:25:00', 'Y', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(153, 10, '2015-05-14 22:13:00', 'Y', 'Y', 'N', '60', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(154, 10, '2015-05-15 04:48:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-15 04:46:37', '2015-05-15 04:46:33', '2015-05-15 04:48:00', '2015-05-15 04:48:00', '2015-05-15 04:48:15', NULL),
	(155, 10, '2015-05-15 04:56:00', 'Y', 'Y', 'Y', '60', 4, '2015-05-15 04:54:18', NULL, '2015-05-15 04:54:45', NULL, '2015-05-15 04:54:45', NULL),
	(156, 10, '2015-05-15 05:06:00', 'Y', 'Y', 'N', '15', 4, '2015-05-15 05:04:02', NULL, '2015-05-15 05:04:37', NULL, '2015-05-15 05:04:37', NULL),
	(157, 10, '2015-05-15 05:08:00', 'Y', 'Y', 'N', '60', 4, '2015-05-15 05:06:25', NULL, '2015-05-15 05:06:43', NULL, '2015-05-15 05:06:43', NULL),
	(158, 10, '2015-05-15 05:18:00', 'Y', 'Y', 'N', '15', 4, '2015-05-15 05:15:48', NULL, '2015-05-15 05:16:18', NULL, '2015-05-15 05:16:18', NULL),
	(159, 10, '2015-05-15 05:25:00', 'Y', 'Y', 'N', '60', 4, '2015-05-15 05:20:59', NULL, '2015-05-15 05:21:08', NULL, '2015-05-15 05:21:08', NULL),
	(160, 10, '2015-05-15 05:29:00', 'Y', 'Y', 'N', '60', 4, '2015-05-15 05:25:09', NULL, '2015-05-15 05:25:16', NULL, '2015-05-15 05:25:16', NULL),
	(161, 10, '2015-05-15 05:45:00', 'Y', 'Y', 'N', '15', 4, '2015-05-15 05:40:29', '2015-05-15 05:40:25', '2015-05-15 05:40:33', NULL, '2015-05-15 05:40:33', NULL),
	(162, 10, '2015-05-17 22:19:00', 'Y', 'Y', 'N', '15', 4, '2015-05-17 22:15:35', NULL, '2015-05-17 22:15:43', NULL, '2015-05-17 22:15:43', NULL),
	(163, 10, '2015-05-17 22:20:00', 'Y', 'Y', 'Y', '60', 4, '2015-05-17 22:18:02', NULL, '2015-05-17 22:18:18', NULL, '2015-05-17 22:18:18', NULL),
	(164, 10, '2015-05-17 22:29:00', 'Y', 'Y', 'Y', '30', 4, '2015-05-17 22:26:52', NULL, '2015-05-17 22:27:04', NULL, '2015-05-17 22:27:04', NULL),
	(165, 10, '2015-05-17 22:32:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-17 22:29:44', NULL, '2015-05-17 22:29:53', NULL, '2015-05-17 22:29:53', NULL),
	(166, 10, '2015-05-17 22:34:00', 'Y', 'Y', 'Y', '30', 4, '2015-05-17 22:30:40', NULL, '2015-05-17 22:30:46', NULL, '2015-05-17 22:30:46', NULL),
	(167, 10, '2015-05-17 22:36:00', 'Y', 'Y', 'Y', '30', 4, '2015-05-17 22:33:01', NULL, '2015-05-17 22:33:07', NULL, '2015-05-17 22:33:07', NULL),
	(168, 10, '2015-05-17 22:38:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-17 22:34:12', NULL, '2015-05-17 22:34:16', NULL, '2015-05-17 22:34:16', NULL),
	(169, 10, '2015-05-17 22:50:00', 'Y', 'Y', 'N', '30', 4, '2015-05-17 22:46:07', NULL, '2015-05-17 22:46:29', NULL, '2015-05-17 22:46:29', NULL),
	(170, 10, '2015-05-17 22:56:00', 'Y', 'Y', 'Y', '30', 4, '2015-05-17 22:52:50', NULL, '2015-05-17 22:53:03', NULL, '2015-05-17 22:53:03', NULL),
	(171, 10, '2015-05-18 06:00:00', 'Y', 'Y', 'N', '60', 4, '2015-05-18 05:57:48', NULL, '2015-05-18 05:58:06', NULL, '2015-05-18 05:58:06', NULL),
	(172, 10, '2015-05-18 06:05:00', 'Y', 'Y', 'N', '60', 4, '2015-05-18 06:02:37', NULL, '2015-05-18 06:02:41', NULL, '2015-05-18 06:02:41', NULL),
	(173, 10, '2015-05-18 06:10:00', 'Y', 'Y', 'Y', '60', 4, '2015-05-18 06:05:36', NULL, '2015-05-18 06:05:49', NULL, '2015-05-18 06:05:49', NULL),
	(174, 10, '2015-05-18 06:18:00', 'Y', 'Y', 'Y', '60', 4, '2015-05-18 06:12:31', NULL, '2015-05-18 06:12:54', NULL, '2015-05-18 06:12:54', NULL),
	(175, 10, '2015-05-19 05:34:00', 'Y', 'Y', 'Y', '60', 4, '2015-05-19 05:32:02', NULL, '2015-05-18 06:13:59', NULL, '2015-05-19 05:32:05', NULL),
	(176, 7, '2015-05-21 00:49:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(177, 1, '2015-05-20 03:20:00', 'Y', 'Y', 'N', '15', 1, '2015-05-20 03:17:39', '2015-05-20 03:17:29', '2015-05-20 03:18:20', '2015-05-20 03:18:20', NULL, NULL),
	(178, 7, '2015-05-21 21:47:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(179, 10, '2015-05-30 04:09:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(180, 7, '2015-05-20 04:11:00', 'Y', 'Y', 'Y', '15', 3, NULL, '2015-05-20 04:06:09', '2015-05-20 04:04:01', '2015-05-20 04:06:15', NULL, NULL),
	(181, 7, '2015-05-20 05:02:00', 'Y', 'N', 'N', '60', 3, '2015-05-20 05:12:56', NULL, NULL, NULL, NULL, NULL),
	(182, 10, '2015-05-22 10:28:00', 'Y', 'N', 'N', '60', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(183, 10, '2015-05-22 11:04:00', 'Y', 'N', 'N', '60', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(184, 10, '2015-05-21 11:10:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(185, 7, '2015-05-23 00:17:00', 'Y', 'N', 'N', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(186, 11, '2015-05-26 01:29:00', 'Y', 'N', 'N', '60', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(187, 10, '2015-05-26 01:20:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-26 01:19:54', '2015-05-26 01:19:04', '2015-05-26 01:59:13', '2015-05-26 01:59:13', '2015-05-26 02:20:06', NULL),
	(188, 10, '2015-05-26 08:59:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-26 09:13:50', '2015-05-26 09:08:56', '2015-05-26 09:13:33', '2015-05-26 09:13:33', '2015-05-26 09:13:53', NULL),
	(189, 10, '2015-05-26 23:32:00', 'Y', 'Y', 'Y', '60', 4, '2015-05-26 23:29:29', '2015-05-26 23:29:26', '2015-05-26 23:54:42', '2015-05-26 23:54:42', NULL, NULL),
	(190, 10, '2015-05-27 00:02:00', 'Y', 'Y', 'Y', '60', 4, '2015-05-26 23:56:39', '2015-05-27 00:11:08', '2015-05-27 01:03:44', '2015-05-27 01:03:44', NULL, NULL),
	(191, 10, '2015-05-27 01:07:00', 'Y', 'Y', 'Y', '15', 4, NULL, NULL, '2015-05-27 01:05:59', '2015-05-27 01:05:59', NULL, NULL),
	(192, 10, '2015-05-27 19:36:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(193, 11, '2015-05-27 01:16:00', 'Y', 'Y', 'Y', '30', 4, '2015-05-27 01:14:56', '2015-05-27 01:15:12', '2015-05-27 01:19:27', '2015-05-27 01:19:27', '2015-05-27 01:19:55', NULL),
	(194, 10, '2015-05-26 19:49:00', 'N', 'N', 'N', '30', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(195, 10, '2015-05-27 01:35:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-27 01:41:06', '2015-05-27 01:38:34', '2015-05-27 01:43:23', '2015-05-27 01:43:23', '2015-05-27 01:43:33', NULL),
	(196, 10, '2015-05-27 01:46:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-27 01:45:24', '2015-05-27 01:44:32', '2015-05-27 01:49:20', '2015-05-27 01:49:20', NULL, NULL),
	(197, 10, '2015-05-27 01:53:00', 'Y', 'Y', 'Y', '15', 4, '2015-05-27 01:51:19', '2015-05-27 01:51:12', '2015-05-27 02:56:37', '2015-05-27 02:56:37', '2015-05-27 02:56:47', NULL),
	(198, 10, '2015-05-28 23:12:00', 'Y', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(199, 10, '2015-05-11 00:17:00', 'Y', 'N', 'N', '30', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(200, 10, '2015-05-28 04:51:00', 'Y', 'N', 'N', '60', 4, '2015-05-28 04:48:39', '2015-05-28 04:49:20', NULL, NULL, NULL, NULL),
	(201, 8, '2015-05-28 04:55:00', 'Y', 'N', 'N', '60', 3, '2015-05-28 04:53:13', '2015-05-28 04:53:18', NULL, NULL, NULL, NULL),
	(202, 9, '2015-05-19 00:15:00', 'Y', 'N', 'N', '60', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(203, 13, '2015-05-19 06:48:00', 'N', 'N', 'N', '15', 5, NULL, NULL, NULL, NULL, NULL, NULL),
	(204, 7, '2015-05-18 23:35:00', 'N', 'N', 'Y', '15', 3, NULL, NULL, NULL, NULL, NULL, NULL),
	(205, 13, '2015-05-18 00:42:00', 'N', 'N', 'Y', '15', 5, NULL, NULL, NULL, NULL, NULL, NULL),
	(206, 10, '2015-05-19 17:57:00', 'Y', 'N', 'Y', '60', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(207, 10, '2015-05-28 23:39:00', 'Y', 'N', 'N', '45', 4, '2015-05-28 23:40:58', '2015-05-28 23:55:43', NULL, NULL, NULL, NULL),
	(208, 14, '2015-05-29 00:08:00', 'Y', 'N', 'N', '60', 5, '2015-05-29 00:02:42', '2015-05-29 00:03:13', NULL, NULL, NULL, NULL),
	(209, 8, '2015-05-29 00:50:00', 'Y', 'Y', 'Y', '15', 3, '2015-05-29 00:45:02', '2015-05-29 00:45:06', '2015-05-29 01:02:33', NULL, '2015-05-29 01:02:33', NULL),
	(210, 10, '2015-05-30 03:47:00', 'N', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(211, 10, '2015-06-13 03:09:00', 'N', 'Y', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(212, 14, '2015-06-20 03:14:00', 'Y', 'Y', 'N', '15', 5, NULL, NULL, NULL, NULL, NULL, NULL),
	(213, 14, '2015-05-29 03:55:00', 'Y', 'Y', 'Y', '15', 5, '2015-05-29 03:57:19', '2015-05-29 03:57:30', '2015-05-29 04:10:07', '2015-05-29 04:10:07', '2015-05-29 04:10:27', NULL),
	(214, 14, '2015-05-29 04:28:00', 'Y', 'N', 'N', '60', 5, '2015-05-29 04:22:45', '2015-05-29 04:23:01', NULL, NULL, NULL, NULL),
	(215, 14, '2015-05-29 05:39:00', 'Y', 'Y', 'Y', '45', 5, '2015-05-29 05:37:29', '2015-05-29 05:37:32', '2015-05-29 05:56:15', '2015-05-29 05:56:15', '2015-05-29 05:56:32', NULL),
	(216, 10, '2015-05-29 06:02:00', 'Y', 'Y', 'Y', '30', 4, '2015-05-29 05:57:51', '2015-05-29 05:57:58', '2015-05-29 06:16:59', '2015-05-29 06:16:59', '2015-05-29 06:17:14', NULL),
	(217, 10, '2015-05-30 03:33:00', 'Y', 'Y', 'Y', '60', 4, '2015-05-30 03:29:55', '2015-05-30 03:29:57', '2015-05-30 03:31:33', '2015-05-30 03:31:33', '2015-05-30 03:32:34', NULL),
	(218, 10, '2015-05-30 03:37:00', 'Y', 'Y', 'Y', '45', 4, '2015-05-30 03:35:44', '2015-05-30 03:33:13', '2015-05-30 03:40:19', '2015-05-30 03:40:36', '2015-05-30 03:40:19', NULL),
	(219, 8, '2015-06-02 04:55:00', 'Y', 'Y', 'N', '60', 3, '2015-06-02 04:52:30', '2015-06-02 04:52:05', NULL, NULL, NULL, NULL),
	(220, 13, '2015-06-26 12:54:00', 'Y', 'Y', 'N', '30', 5, NULL, NULL, NULL, NULL, NULL, NULL),
	(222, 4, '2015-06-11 13:38:00', 'Y', 'Y', 'Y', '15', 2, NULL, NULL, NULL, NULL, NULL, NULL),
	(225, 4, '2015-06-10 07:24:00', 'Y', 'Y', 'Y', '15', 2, NULL, NULL, NULL, NULL, NULL, NULL),
	(226, 4, '2015-06-10 07:02:00', 'Y', 'Y', 'N', '15', 2, NULL, NULL, NULL, NULL, NULL, NULL),
	(237, 4, '2015-06-10 02:10:00', 'Y', 'Y', 'Y', '15', 2, '2015-06-10 02:00:22', '2015-06-10 02:00:24', '2015-06-10 02:00:55', '2015-06-10 02:01:05', '2015-06-10 02:00:55', NULL),
	(253, 10, '2015-06-18 17:23:00', 'N', 'N', 'N', '15', 4, NULL, NULL, NULL, NULL, NULL, NULL),
	(254, 4, '2015-06-15 05:30:00', 'Y', 'Y', 'Y', '15', 2, '2015-06-15 05:22:33', '2015-06-15 05:22:40', '2015-06-15 05:26:38', NULL, '2015-06-15 05:26:38', NULL),
	(255, 4, '2015-06-15 05:42:00', 'Y', 'Y', 'Y', '15', 2, '2015-06-15 05:35:03', '2015-06-15 05:34:58', '2015-06-15 05:37:17', '2015-06-15 05:44:01', '2015-06-15 05:37:17', NULL),
	(256, 4, '2015-06-15 05:52:00', 'Y', 'Y', 'Y', '15', 2, '2015-06-15 05:45:23', '2015-06-15 05:45:20', '2015-06-15 05:47:21', '2015-06-15 05:58:15', '2015-06-15 05:47:21', '<?xml version="1.0" encoding="UTF-8"?><response function="chats.details" status="ok">                <result_set totalResults="8">                             <result>                                <roomid>pgfzz2sb</roomid>                                <msg>smith: OK THANKS</msg>                                <added>2015-06-15 05:47:09.0</added>                            </result>					                            <result>                                <roomid>pgfzz2sb</roomid>                                <msg>: OK NP</msg>                                <added>2015-06-15 05:47:02.0</added>                            </result>					                            <result>                                <roomid>pgfzz2sb</roomid>                                <msg>smith: I AM JUST TESSTING IT FOR FLOW</msg>                                <added>2015-06-15 05:46:57.0</added>                            </result>					                            <result>                                <roomid>pgfzz2sb</roomid>                                <msg>smith: HOW R U </msg>                                <added>2015-06-15 05:46:50.0</added>                            </result>					                            <result>                                <roomid>pgfzz2sb</roomid>                                <msg>: HELLO TUTUOR</msg>                                <added>2015-06-15 05:46:45.0</added>                            </result>					                            <result>                                <roomid>pgfzz2sb</roomid>                                <msg>smith: FDGDFGDFG</msg>                                <added>2015-06-15 05:46:29.0</added>                            </result>					                            <result>                                <roomid>pgfzz2sb</roomid>                                <msg> connected</msg>                                <added>2015-06-15 05:45:21.0</added>                            </result>					                            <result>                                <roomid>pgfzz2sb</roomid>                                <msg>smith connected</msg>                                <added>2015-06-15 05:45:21.0</added>                            </result>					</result_set></response>');
/*!40000 ALTER TABLE `booking_tutor` ENABLE KEYS */;


-- Dumping structure for table miprofe.career_type_master
CREATE TABLE IF NOT EXISTS `career_type_master` (
  `Career_Type_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Career_Type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Career_Type_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.career_type_master: ~4 rows (approximately)
/*!40000 ALTER TABLE `career_type_master` DISABLE KEYS */;
INSERT INTO `career_type_master` (`Career_Type_Id`, `Career_Type`) VALUES
	(1, 'Student'),
	(2, 'Self Employee'),
	(3, 'Govt Employee'),
	(4, 'Home Maker');
/*!40000 ALTER TABLE `career_type_master` ENABLE KEYS */;


-- Dumping structure for table miprofe.choose_plan
CREATE TABLE IF NOT EXISTS `choose_plan` (
  `choosePlan_Id` int(11) NOT NULL AUTO_INCREMENT,
  `basic_Plan` int(11) NOT NULL,
  `buyMinutes_Plan` int(11) NOT NULL,
  `plus_Plan` int(11) NOT NULL,
  `popular_Plan` int(11) NOT NULL,
  `Country_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`choosePlan_Id`),
  KEY `FK9BCB0451B448021A` (`Country_Id`),
  CONSTRAINT `FK9BCB0451B448021A` FOREIGN KEY (`Country_Id`) REFERENCES `country_master` (`Country_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.choose_plan: ~0 rows (approximately)
/*!40000 ALTER TABLE `choose_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `choose_plan` ENABLE KEYS */;


-- Dumping structure for table miprofe.cms_pdf
CREATE TABLE IF NOT EXISTS `cms_pdf` (
  `Cms_Pdf_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Page_Id` int(11) NOT NULL,
  `Pdf_Text` varchar(50) DEFAULT NULL,
  `Pdf_URL` varchar(500) DEFAULT NULL,
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Cms_Pdf_Id`),
  KEY `FK__manage_cms_pdf` (`Page_Id`),
  CONSTRAINT `FK__manage_cms_pdf` FOREIGN KEY (`Page_Id`) REFERENCES `manage_cms` (`Cms_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.cms_pdf: ~0 rows (approximately)
/*!40000 ALTER TABLE `cms_pdf` DISABLE KEYS */;
INSERT INTO `cms_pdf` (`Cms_Pdf_Id`, `Page_Id`, `Pdf_Text`, `Pdf_URL`, `Created_Date`) VALUES
	(4, 5, 'pdf', '/videos/pdf/pdf1.pdf', '2015-06-12 17:54:48'),
	(5, 5, 'pdf', '/videos/pdf/pdf2.pdf', '2015-06-12 17:54:48'),
	(6, 5, 'pdf', '/videos/pdf/pdf3.pdf', '2015-06-12 17:54:48');
/*!40000 ALTER TABLE `cms_pdf` ENABLE KEYS */;


-- Dumping structure for table miprofe.cms_videos
CREATE TABLE IF NOT EXISTS `cms_videos` (
  `Cms_Video_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Page_Id` int(11) NOT NULL,
  `Video_Text` varchar(50) DEFAULT NULL,
  `Video_URL` varchar(500) DEFAULT NULL,
  `Image_URL` varchar(500) DEFAULT NULL,
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Cms_Video_Id`),
  KEY `FK__manage_cms` (`Page_Id`),
  CONSTRAINT `FK__manage_cms` FOREIGN KEY (`Page_Id`) REFERENCES `manage_cms` (`Cms_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.cms_videos: ~0 rows (approximately)
/*!40000 ALTER TABLE `cms_videos` DISABLE KEYS */;
INSERT INTO `cms_videos` (`Cms_Video_Id`, `Page_Id`, `Video_Text`, `Video_URL`, `Image_URL`, `Created_Date`) VALUES
	(1, 1, 'about1', '/videos/about/about1.mp4', '/videos/about/about1.png', '2015-06-09 11:42:33'),
	(2, 1, 'about2', '/videos/about/about2.mp4', '/videos/about/about2.png', '2015-06-09 11:42:33'),
	(3, 4, 'tutor', '/videos/tutor/tutor.mp4', '/videos/tutor/tutor.png', '2015-06-09 16:25:14'),
	(4, 5, 'tip1', '/videos/tips/tip1.mp4', '/videos/tips/tip1.png', '2015-06-09 16:26:01'),
	(5, 5, 'tip2', '/videos/tips/tip2.mp4', '/videos/tips/tip2.png', '2015-06-09 16:26:01'),
	(6, 5, 'tip3', '/videos/tips/tip3.mp4', '/videos/tips/tip3.png', '2015-06-09 16:26:01'),
	(7, 5, 'tip4', '/videos/tips/tip4.mp4', '/videos/tips/tip4.png', '2015-06-09 16:26:01'),
	(8, 5, 'tip5', '/videos/tips/tip5.mp4', '/videos/tips/tip5.png', '2015-06-09 16:26:01'),
	(9, 5, 'tip6', '/videos/tips/tip6.mp4', '/videos/tips/tip6.png', '2015-06-09 16:26:01'),
	(10, 1, 'student1', '/videos/student/student1.mp4', '/videos/student/student1.png', '2015-06-09 11:42:33'),
	(11, 1, 'student2', '/videos/student/student2.mp4', '/videos/student/student2.png', '2015-06-09 11:42:33'),
	(12, 1, 'parent1', '/videos/parent/parent1.mp4', '/videos/parent/parent1.png', '2015-06-09 11:42:33'),
	(13, 1, 'parent2', '/videos/parent/parent2.mp4', '/videos/parent/parent2.png', '2015-06-09 11:42:33'),
	(14, 1, 'org1', '/videos/org/org1.mp4', '/videos/org/org1.png', '2015-06-09 11:42:33'),
	(15, 1, 'org2', '/videos/org/org2.mp4', '/videos/org/org2.png', '2015-06-09 11:42:33'),
	(16, 9, 'parent1', '/videos/parent/parent1.mp4', '/videos/parent/parent1.png', '2015-06-11 17:33:58'),
	(17, 9, 'parent2', '/videos/parent/parent2.mp4', '/videos/parent/parent2.png', '2015-06-11 17:33:59'),
	(18, 8, 'student1', '/videos/student/student1.mp4', '/videos/student/student1.png', '2015-06-11 17:34:15'),
	(19, 8, 'student2', '/videos/student/student2.mp4', '/videos/student/student2.png', '2015-06-11 17:34:15'),
	(20, 10, 'org1', '/videos/org/org1.mp4', '/videos/org/org1.png', '2015-06-11 17:35:01'),
	(21, 10, 'org2', '/videos/org/org2.mp4', '/videos/org/org2.png', '2015-06-11 17:35:02');
/*!40000 ALTER TABLE `cms_videos` ENABLE KEYS */;


-- Dumping structure for table miprofe.country_master
CREATE TABLE IF NOT EXISTS `country_master` (
  `Country_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Country_Code` varchar(50) DEFAULT NULL,
  `Country_Name` varchar(50) DEFAULT NULL,
  `Is_Spanish` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Country_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.country_master: ~243 rows (approximately)
/*!40000 ALTER TABLE `country_master` DISABLE KEYS */;
INSERT INTO `country_master` (`Country_Id`, `Country_Code`, `Country_Name`, `Is_Spanish`) VALUES
	(1, 'US', 'United States', 'N'),
	(2, 'CA', 'Canada', 'N'),
	(3, 'AF', 'Afghanistan', 'N'),
	(4, 'AL', 'Albania', 'N'),
	(5, 'DZ', 'Algeria', 'N'),
	(6, 'DS', 'American Samoa', 'N'),
	(7, 'AD', 'Andorra', 'N'),
	(8, 'AO', 'Angola', 'N'),
	(9, 'AI', 'Anguilla', 'N'),
	(10, 'AQ', 'Antarctica', 'N'),
	(11, 'AG', 'Antigua and/or Barbuda', 'N'),
	(12, 'AR', 'Argentina', 'Y'),
	(13, 'AM', 'Armenia', 'N'),
	(14, 'AW', 'Aruba', 'N'),
	(15, 'AU', 'Australia', 'N'),
	(16, 'AT', 'Austria', 'N'),
	(17, 'AZ', 'Azerbaijan', 'N'),
	(18, 'BS', 'Bahamas', 'N'),
	(19, 'BH', 'Bahrain', 'N'),
	(20, 'BD', 'Bangladesh', 'N'),
	(21, 'BB', 'Barbados', 'N'),
	(22, 'BY', 'Belarus', 'N'),
	(23, 'BE', 'Belgium', 'N'),
	(24, 'BZ', 'Belize', 'N'),
	(25, 'BJ', 'Benin', 'N'),
	(26, 'BM', 'Bermuda', 'N'),
	(27, 'BT', 'Bhutan', 'N'),
	(28, 'BO', 'Bolivia', 'Y'),
	(29, 'BA', 'Bosnia and Herzegovina', 'N'),
	(30, 'BW', 'Botswana', 'N'),
	(31, 'BV', 'Bouvet Island', 'N'),
	(32, 'BR', 'Brazil', 'N'),
	(33, 'IO', 'British lndian Ocean Territory', 'N'),
	(34, 'BN', 'Brunei Darussalam', 'N'),
	(35, 'BG', 'Bulgaria', 'N'),
	(36, 'BF', 'Burkina Faso', 'N'),
	(37, 'BI', 'Burundi', 'N'),
	(38, 'KH', 'Cambodia', 'N'),
	(39, 'CM', 'Cameroon', 'N'),
	(40, 'CV', 'Cape Verde', 'N'),
	(41, 'KY', 'Cayman Islands', 'N'),
	(42, 'CF', 'Central African Republic', 'N'),
	(43, 'TD', 'Chad', 'N'),
	(44, 'CL', 'Chile', 'Y'),
	(45, 'CN', 'China', 'N'),
	(46, 'CX', 'Christmas Island', 'N'),
	(47, 'CC', 'Cocos (Keeling) Islands', 'N'),
	(48, 'CO', 'Colombia', 'Y'),
	(49, 'KM', 'Comoros', 'N'),
	(50, 'CG', 'Congo', 'N'),
	(51, 'CK', 'Cook Islands', 'N'),
	(52, 'CR', 'Costa Rica', 'Y'),
	(53, 'HR', 'Croatia (Hrvatska)', 'N'),
	(54, 'CU', 'Cuba', 'Y'),
	(55, 'CY', 'Cyprus', 'N'),
	(56, 'CZ', 'Czech Republic', 'N'),
	(57, 'DK', 'Denmark', 'N'),
	(58, 'DJ', 'Djibouti', 'N'),
	(59, 'DM', 'Dominica', 'N'),
	(60, 'DO', 'Dominican Republic', 'Y'),
	(61, 'TP', 'East Timor', 'N'),
	(62, 'EC', 'Ecuador', 'Y'),
	(63, 'EG', 'Egypt', 'N'),
	(64, 'SV', 'El Salvador', 'Y'),
	(65, 'GQ', 'Equatorial Guinea', 'Y'),
	(66, 'ER', 'Eritrea', 'N'),
	(67, 'EE', 'Estonia', 'N'),
	(68, 'ET', 'Ethiopia', 'N'),
	(69, 'FK', 'Falkland Islands (Malvinas)', 'N'),
	(70, 'FO', 'Faroe Islands', 'N'),
	(71, 'FJ', 'Fiji', 'N'),
	(72, 'FI', 'Finland', 'N'),
	(73, 'FR', 'France', 'N'),
	(74, 'FX', 'France, Metropolitan', 'N'),
	(75, 'GF', 'French Guiana', 'N'),
	(76, 'PF', 'French Polynesia', 'N'),
	(77, 'TF', 'French Southern Territories', 'N'),
	(78, 'GA', 'Gabon', 'N'),
	(79, 'GM', 'Gambia', 'N'),
	(80, 'GE', 'Georgia', 'N'),
	(81, 'DE', 'Germany', 'N'),
	(82, 'GH', 'Ghana', 'N'),
	(83, 'GI', 'Gibraltar', 'N'),
	(84, 'GR', 'Greece', 'N'),
	(85, 'GL', 'Greenland', 'N'),
	(86, 'GD', 'Grenada', 'N'),
	(87, 'GP', 'Guadeloupe', 'N'),
	(88, 'GU', 'Guam', 'N'),
	(89, 'GT', 'Guatemala', 'Y'),
	(90, 'GN', 'Guinea', 'N'),
	(91, 'GW', 'Guinea-Bissau', 'N'),
	(92, 'GY', 'Guyana', 'N'),
	(93, 'HT', 'Haiti', 'N'),
	(94, 'HM', 'Heard and Mc Donald Islands', 'N'),
	(95, 'HN', 'Honduras', 'Y'),
	(96, 'HK', 'Hong Kong', 'N'),
	(97, 'HU', 'Hungary', 'N'),
	(98, 'IS', 'Iceland', 'N'),
	(99, 'IN', 'India', 'N'),
	(100, 'ID', 'Indonesia', 'N'),
	(101, 'IR', 'Iran (Islamic Republic of)', 'N'),
	(102, 'IQ', 'Iraq', 'N'),
	(103, 'IE', 'Ireland', 'N'),
	(104, 'IL', 'Israel', 'N'),
	(105, 'IT', 'Italy', 'N'),
	(106, 'CI', 'Ivory Coast', 'N'),
	(107, 'JM', 'Jamaica', 'N'),
	(108, 'JP', 'Japan', 'N'),
	(109, 'JO', 'Jordan', 'N'),
	(110, 'KZ', 'Kazakhstan', 'N'),
	(111, 'KE', 'Kenya', 'N'),
	(112, 'KI', 'Kiribati', 'N'),
	(113, 'KP', 'Korea, Democratic People\'s Republic of', 'N'),
	(114, 'KR', 'Korea, Republic of', 'N'),
	(115, 'XK', 'Kosovo', 'N'),
	(116, 'KW', 'Kuwait', 'N'),
	(117, 'KG', 'Kyrgyzstan', 'N'),
	(118, 'LA', 'Lao People\'s Democratic Republic', 'N'),
	(119, 'LV', 'Latvia', 'N'),
	(120, 'LB', 'Lebanon', 'N'),
	(121, 'LS', 'Lesotho', 'N'),
	(122, 'LR', 'Liberia', 'N'),
	(123, 'LY', 'Libyan Arab Jamahiriya', 'N'),
	(124, 'LI', 'Liechtenstein', 'N'),
	(125, 'LT', 'Lithuania', 'N'),
	(126, 'LU', 'Luxembourg', 'N'),
	(127, 'MO', 'Macau', 'N'),
	(128, 'MK', 'Macedonia', 'N'),
	(129, 'MG', 'Madagascar', 'N'),
	(130, 'MW', 'Malawi', 'N'),
	(131, 'MY', 'Malaysia', 'N'),
	(132, 'MV', 'Maldives', 'N'),
	(133, 'ML', 'Mali', 'N'),
	(134, 'MT', 'Malta', 'N'),
	(135, 'MH', 'Marshall Islands', 'N'),
	(136, 'MQ', 'Martinique', 'N'),
	(137, 'MR', 'Mauritania', 'N'),
	(138, 'MU', 'Mauritius', 'N'),
	(139, 'TY', 'Mayotte', 'N'),
	(140, 'MX', 'Mexico', 'Y'),
	(141, 'FM', 'Micronesia, Federated States of', 'N'),
	(142, 'MD', 'Moldova, Republic of', 'N'),
	(143, 'MC', 'Monaco', 'N'),
	(144, 'MN', 'Mongolia', 'N'),
	(145, 'ME', 'Montenegro', 'N'),
	(146, 'MS', 'Montserrat', 'N'),
	(147, 'MA', 'Morocco', 'N'),
	(148, 'MZ', 'Mozambique', 'N'),
	(149, 'MM', 'Myanmar', 'N'),
	(150, 'NA', 'Namibia', 'N'),
	(151, 'NR', 'Nauru', 'N'),
	(152, 'NP', 'Nepal', 'N'),
	(153, 'NL', 'Netherlands', 'N'),
	(154, 'AN', 'Netherlands Antilles', 'N'),
	(155, 'NC', 'New Caledonia', 'N'),
	(156, 'NZ', 'New Zealand', 'N'),
	(157, 'NI', 'Nicaragua', 'Y'),
	(158, 'NE', 'Niger', 'N'),
	(159, 'NG', 'Nigeria', 'N'),
	(160, 'NU', 'Niue', 'N'),
	(161, 'NF', 'Norfork Island', 'N'),
	(162, 'MP', 'Northern Mariana Islands', 'N'),
	(163, 'NO', 'Norway', 'N'),
	(164, 'OM', 'Oman', 'N'),
	(165, 'PK', 'Pakistan', 'N'),
	(166, 'PW', 'Palau', 'N'),
	(167, 'PA', 'Panama', 'Y'),
	(168, 'PG', 'Papua New Guinea', 'N'),
	(169, 'PY', 'Paraguay', 'Y'),
	(170, 'PE', 'Peru', 'Y'),
	(171, 'PH', 'Philippines', 'N'),
	(172, 'PN', 'Pitcairn', 'N'),
	(173, 'PL', 'Poland', 'N'),
	(174, 'PT', 'Portugal', 'N'),
	(175, 'PR', 'Puerto Rico', 'N'),
	(176, 'QA', 'Qatar', 'N'),
	(177, 'RE', 'Reunion', 'N'),
	(178, 'RO', 'Romania', 'N'),
	(179, 'RU', 'Russian Federation', 'N'),
	(180, 'RW', 'Rwanda', 'N'),
	(181, 'KN', 'Saint Kitts and Nevis', 'N'),
	(182, 'LC', 'Saint Lucia', 'N'),
	(183, 'VC', 'Saint Vincent and the Grenadines', 'N'),
	(184, 'WS', 'Samoa', 'N'),
	(185, 'SM', 'San Marino', 'N'),
	(186, 'ST', 'Sao Tome and Principe', 'N'),
	(187, 'SA', 'Saudi Arabia', 'N'),
	(188, 'SN', 'Senegal', 'N'),
	(189, 'RS', 'Serbia', 'N'),
	(190, 'SC', 'Seychelles', 'N'),
	(191, 'SL', 'Sierra Leone', 'N'),
	(192, 'SG', 'Singapore', 'N'),
	(193, 'SK', 'Slovakia', 'N'),
	(194, 'SI', 'Slovenia', 'N'),
	(195, 'SB', 'Solomon Islands', 'N'),
	(196, 'SO', 'Somalia', 'N'),
	(197, 'ZA', 'South Africa', 'N'),
	(198, 'GS', 'South Georgia South Sandwich Islands', 'N'),
	(199, 'ES', 'Spain', 'Y'),
	(200, 'LK', 'Sri Lanka', 'N'),
	(201, 'SH', 'St. Helena', 'N'),
	(202, 'PM', 'St. Pierre and Miquelon', 'N'),
	(203, 'SD', 'Sudan', 'N'),
	(204, 'SR', 'Suriname', 'N'),
	(205, 'SJ', 'Svalbarn and Jan Mayen Islands', 'N'),
	(206, 'SZ', 'Swaziland', 'N'),
	(207, 'SE', 'Sweden', 'N'),
	(208, 'CH', 'Switzerland', 'N'),
	(209, 'SY', 'Syrian Arab Republic', 'N'),
	(210, 'TW', 'Taiwan', 'N'),
	(211, 'TJ', 'Tajikistan', 'N'),
	(212, 'TZ', 'Tanzania, United Republic of', 'N'),
	(213, 'TH', 'Thailand', 'N'),
	(214, 'TG', 'Togo', 'N'),
	(215, 'TK', 'Tokelau', 'N'),
	(216, 'TO', 'Tonga', 'N'),
	(217, 'TT', 'Trinidad and Tobago', 'N'),
	(218, 'TN', 'Tunisia', 'N'),
	(219, 'TR', 'Turkey', 'N'),
	(220, 'TM', 'Turkmenistan', 'N'),
	(221, 'TC', 'Turks and Caicos Islands', 'N'),
	(222, 'TV', 'Tuvalu', 'N'),
	(223, 'UG', 'Uganda', 'N'),
	(224, 'UA', 'Ukraine', 'N'),
	(225, 'AE', 'United Arab Emirates', 'N'),
	(226, 'GB', 'United Kingdom', 'N'),
	(227, 'UM', 'United States minor outlying islands', 'N'),
	(228, 'UY', 'Uruguay', 'Y'),
	(229, 'UZ', 'Uzbekistan', 'N'),
	(230, 'VU', 'Vanuatu', 'N'),
	(231, 'VA', 'Vatican City State', 'N'),
	(232, 'VE', 'Venezuela', 'Y'),
	(233, 'VN', 'Vietnam', 'N'),
	(234, 'VG', 'Virgin Islands (British)', 'N'),
	(235, 'VI', 'Virgin Islands (U.S.)', 'N'),
	(236, 'WF', 'Wallis and Futuna Islands', 'N'),
	(237, 'EH', 'Western Sahara', 'N'),
	(238, 'YE', 'Yemen', 'N'),
	(239, 'YU', 'Yugoslavia', 'N'),
	(240, 'ZR', 'Zaire', 'N'),
	(241, 'ZM', 'Zambia', 'N'),
	(242, 'ZW', 'Zimbabwe', 'N'),
	(243, 'OTH', 'Other Countries', 'N');
/*!40000 ALTER TABLE `country_master` ENABLE KEYS */;


-- Dumping structure for table miprofe.education_type_master
CREATE TABLE IF NOT EXISTS `education_type_master` (
  `Education_Type_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Education_Type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Education_Type_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.education_type_master: ~5 rows (approximately)
/*!40000 ALTER TABLE `education_type_master` DISABLE KEYS */;
INSERT INTO `education_type_master` (`Education_Type_Id`, `Education_Type`) VALUES
	(1, 'Primary School'),
	(2, 'Mid School'),
	(3, 'High School'),
	(4, 'Bachelor'),
	(5, 'Master');
/*!40000 ALTER TABLE `education_type_master` ENABLE KEYS */;


-- Dumping structure for table miprofe.favourite_tutor
CREATE TABLE IF NOT EXISTS `favourite_tutor` (
  `favourite_tutor_Id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_Id` int(11) DEFAULT NULL,
  `student_Id` int(11) DEFAULT NULL,
  `tutor_Id` int(11) DEFAULT NULL,
  `comments` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`favourite_tutor_Id`),
  KEY `FK_parentId_favourite_tutor` (`parent_Id`),
  KEY `FK_studentId_favourite_tutor` (`student_Id`),
  KEY `FK_tutorId_favourite_tutor` (`tutor_Id`),
  CONSTRAINT `FK_parentId_favourite_tutor` FOREIGN KEY (`parent_Id`) REFERENCES `parent_profile_details` (`Parent_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_studentId_favourite_tutor` FOREIGN KEY (`student_Id`) REFERENCES `student_profile_details` (`Student_Profile_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tutorId_favourite_tutor` FOREIGN KEY (`tutor_Id`) REFERENCES `tutor_profile_detail` (`Tutor_Profile_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.favourite_tutor: ~12 rows (approximately)
/*!40000 ALTER TABLE `favourite_tutor` DISABLE KEYS */;
INSERT INTO `favourite_tutor` (`favourite_tutor_Id`, `parent_Id`, `student_Id`, `tutor_Id`, `comments`) VALUES
	(21, NULL, 26, 2, NULL),
	(22, NULL, 26, 3, NULL),
	(23, NULL, 26, 6, NULL),
	(24, NULL, 29, 7, NULL),
	(27, NULL, 27, 12, NULL),
	(28, NULL, 29, 12, NULL),
	(29, NULL, 27, 7, NULL),
	(30, NULL, 27, 13, NULL),
	(31, NULL, 27, 14, NULL),
	(32, NULL, 27, 10, NULL),
	(33, NULL, 57, 7, NULL),
	(34, NULL, 57, 10, NULL);
/*!40000 ALTER TABLE `favourite_tutor` ENABLE KEYS */;


-- Dumping structure for table miprofe.level_master
CREATE TABLE IF NOT EXISTS `level_master` (
  `Level_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Level` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Level_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.level_master: ~4 rows (approximately)
/*!40000 ALTER TABLE `level_master` DISABLE KEYS */;
INSERT INTO `level_master` (`Level_Id`, `Level`) VALUES
	(1, 'Primary'),
	(2, 'Mid School'),
	(3, 'High School'),
	(4, 'College');
/*!40000 ALTER TABLE `level_master` ENABLE KEYS */;


-- Dumping structure for table miprofe.manage_cms
CREATE TABLE IF NOT EXISTS `manage_cms` (
  `Cms_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Page_Name` varchar(50) DEFAULT NULL,
  `Page_Content` longtext,
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Cms_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.manage_cms: ~10 rows (approximately)
/*!40000 ALTER TABLE `manage_cms` DISABLE KEYS */;
INSERT INTO `manage_cms` (`Cms_Id`, `Page_Name`, `Page_Content`, `Created_Date`) VALUES
	(1, 'about', '            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.</p>\r\n            <p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n            <p> Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.\r\n              "Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.“</p>\r\n            <p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.</p>\r\n            <p>"Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.“</p>', '2015-06-04 15:51:40'),
	(2, 'faq', '<div class="ques-con">\r\n<p><a href="#q1">Q1. Excepteursintoccaecatcupidatatnon proident ? </a></p>\r\n\r\n<p><a href="#q2">Q2. Excepteursintoccaecatcupidatatnon proident ?</a></p>\r\n\r\n<p><a href="#q3">Q3. Excepteursintoccaecatcupidatatnon proident ?</a></p>\r\n</div>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<div class="ans">\r\n<h5>1. Excepteursintoccaecatcupidatatnon proident</h5>\r\n\r\n<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n</div>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<div class="ans">\r\n<h5>2. Excepteursintoccaecatcupidatatnon proident</h5>\r\n\r\n<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n</div>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<div class="ans">\r\n<h5>3. Excepteursintoccaecatcupidatatnon proident</h5>\r\n\r\n<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n</div>\r\n\r\n<p>&nbsp;</p>\r\n', '2015-06-04 15:51:51'),
	(3, 'privacy', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.</p> <p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p> <p> Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum."Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.“</p><p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.</p><p>"Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.“</p><p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.</p><p>"Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.“</p>\r\n  ', '2015-06-04 15:52:05'),
	(4, 'tutor', ' <p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.</p>\r\n            <p>"Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum."</p>\r\n            <p> Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.\r\n              "Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.“</p>\r\n          ', '2015-06-05 10:52:27'),
	(5, 'tips', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.</p>\r\n', '2015-06-08 15:40:55'),
	(6, 'honor', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.</p> <p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p> <p> Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum."Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.“</p><p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.</p><p>"Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.“</p><p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.</p><p>"Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.“</p>\r\n  ', '2015-06-04 15:52:05'),
	(7, 'tnc', '<div class="ques-con">\r\n<p><strong><a href="#q1">Q1. Excepteursintoccaecatcupidatatnon proident ? </a></strong></p>\r\n\r\n<p><strong><a href="#q2">Q2. Excepteursintoccaecatcupidatatnon proident ?</a></strong></p>\r\n\r\n<p><strong><a href="#q4">Q3. Excepteursintoccaecatcupidatatnon proident ?</a></strong></p>\r\n\r\n<p><strong><a href="#q4">Q4. Excepteursintoccaecatcupidatatnon proident ?</a></strong></p>\r\n</div>\r\n\r\n<div class="ans" id="q1">\r\n<h5>&nbsp;</h5>\r\n\r\n<h5>&nbsp;</h5>\r\n\r\n<h5>1. Excepteursintoccaecatcupidatatnon proident</h5>\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodte porincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n</div>\r\n\r\n<div class="ans" id="q2">\r\n<h5>&nbsp;</h5>\r\n\r\n<h5>&nbsp;</h5>\r\n\r\n<h5>2. Excepteursintoccaecatcupidatatnon proident</h5>\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n</div>\r\n\r\n<div class="ans" id="q3">\r\n<h5>&nbsp;</h5>\r\n\r\n<h5>&nbsp;</h5>\r\n\r\n<h5>3. Excepteursintoccaecatcupidatatnon proident</h5>\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo nullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n</div>\r\n\r\n<div class="ans">\r\n<h5>&nbsp;</h5>\r\n\r\n<h5>&nbsp;</h5>\r\n\r\n<h5><a id="q4" name="q4"><span style="color:#065591">4. Excepteursintoccaecatcupidatatnon proident</span></a></h5>\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo nullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n</div>\r\n', '2015-06-04 15:51:51'),
	(8, 'student', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n\r\n<p>Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum. &quot;Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.&ldquo;</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.</p>\r\n\r\n<p>&quot;Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.&ldquo;</p>\r\n', '2015-06-04 15:51:40'),
	(9, 'parent', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n\r\n<p>Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum. &quot;Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.&ldquo;</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.</p>\r\n\r\n<p>&quot;Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.&ldquo;</p>\r\n', '2015-06-04 15:51:40'),
	(10, 'org', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque.</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur.</p>\r\n\r\n<p>Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum. &quot;Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.&ldquo;</p>\r\n\r\n<p>Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.</p>\r\n\r\n<p>&quot;Loremipsumdolor sitamet, consecteturadipiscingelit, sed do eiusmodtemporincididuntut labore et doloremagna aliqua. Ut enimad minimveniam, quisnostrudexercitationullamcolaborisnisiut aliquipex eacommodo consequat. Duisauteiruredolor in reprehenderitin voluptatevelitessecillumdoloreeufugiatnullapariatur. Excepteursintoccaecatcupidatatnon proident, suntin culpa quiofficiadeseruntmollitanimid estlaborum.&ldquo;</p>\r\n', '2015-06-04 15:51:40');
/*!40000 ALTER TABLE `manage_cms` ENABLE KEYS */;


-- Dumping structure for table miprofe.manage_job
CREATE TABLE IF NOT EXISTS `manage_job` (
  `Job_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Job_Text` varchar(1000) DEFAULT NULL,
  `Job_Content` varchar(1000) DEFAULT NULL,
  `Job_Video` varchar(250) DEFAULT NULL,
  `Job_Image` varchar(250) DEFAULT NULL,
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Job_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.manage_job: ~4 rows (approximately)
/*!40000 ALTER TABLE `manage_job` DISABLE KEYS */;
INSERT INTO `manage_job` (`Job_Id`, `Job_Text`, `Job_Content`, `Job_Video`, `Job_Image`, `Created_Date`) VALUES
	(1, 'job1', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex.</p>\r\n', '/videos/job/job1.mp4', '/videos/job/job1.png', '2015-06-11 15:36:45'),
	(2, 'job2', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex.</p>\r\n', '/videos/job/job2.mp4', '/videos/job/job2.png', '2015-06-11 15:36:45'),
	(3, 'job3', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex.</p>\r\n', '/videos/job/job3.mp4', '/videos/job/job3.png', '2015-06-11 15:36:45'),
	(4, 'job4', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex.</p>\r\n', '/videos/job/job4.mp4', '/videos/job/job4.png', '2015-06-11 15:36:45');
/*!40000 ALTER TABLE `manage_job` ENABLE KEYS */;


-- Dumping structure for table miprofe.manage_news
CREATE TABLE IF NOT EXISTS `manage_news` (
  `News_Id` int(11) NOT NULL AUTO_INCREMENT,
  `News_Text` varchar(1000) DEFAULT NULL,
  `News_Content` varchar(1000) DEFAULT NULL,
  `News_Video` varchar(250) DEFAULT NULL,
  `News_Image` varchar(250) DEFAULT NULL,
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`News_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.manage_news: ~0 rows (approximately)
/*!40000 ALTER TABLE `manage_news` DISABLE KEYS */;
INSERT INTO `manage_news` (`News_Id`, `News_Text`, `News_Content`, `News_Video`, `News_Image`, `Created_Date`) VALUES
	(1, 'news1', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex.</p>', '/videos/news/news1.mp4', '/videos/news/news1.png', '2015-06-08 14:48:16'),
	(2, 'news2', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex.</p>', '/videos/news/news2.mp4', '/videos/news/news2.png', '2015-06-08 14:48:16'),
	(3, 'news3', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex.</p>', '/videos/news/news3.mp4', '/videos/news/news3.png', '2015-06-08 14:48:16'),
	(4, 'news4', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex. Praesent vehicula, tortor eget egestas posuere, ex nulla vestibulum tortor, a dictum mi purus ut quam. Aliquam accumsan eros eget est tincidunt, a tincidunt augue tristique. Nulla nec cursus magna. Mauris vel nunc neque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non suscipit purus. Vestibulum eget congue nunc. Praesent molestie finibus nibh, sit amet commodo elit suscipit a. Nulla velit risus, mattis et viverra id, semper nec metus. Sed elit turpis, elementum pellentesque lacinia consequat, viverra sit amet ex.</p>', '/videos/news/news4.mp4', '/videos/news/news4.png', '2015-06-08 14:48:16');
/*!40000 ALTER TABLE `manage_news` ENABLE KEYS */;


-- Dumping structure for table miprofe.messages
CREATE TABLE IF NOT EXISTS `messages` (
  `message_Id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `User_From` int(11) DEFAULT NULL,
  `User_To` int(11) DEFAULT NULL,
  `Read_Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`message_Id`),
  KEY `FKE475014C64D72F5C` (`User_To`),
  KEY `FKE475014C81D2CB8B` (`User_From`),
  CONSTRAINT `FKE475014C64D72F5C` FOREIGN KEY (`User_To`) REFERENCES `user` (`User_Id`),
  CONSTRAINT `FKE475014C81D2CB8B` FOREIGN KEY (`User_From`) REFERENCES `user` (`User_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.messages: ~3 rows (approximately)
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` (`message_Id`, `message`, `Created_Date`, `User_From`, `User_To`, `Read_Status`) VALUES
	(1, 'test case', '2015-05-20 06:51:22', 53, 51, 'Y'),
	(2, 'asddsadas', '2015-05-20 07:18:41', 51, 53, 'Y'),
	(3, 'sdfsfdsfdsfs', '2015-05-22 04:43:38', 51, 53, 'N');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;


-- Dumping structure for table miprofe.parent_profile_details
CREATE TABLE IF NOT EXISTS `parent_profile_details` (
  `Parent_Id` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Country_Id` int(11) DEFAULT NULL,
  `Timezone_Id` int(11) DEFAULT NULL,
  `User_Id` int(11) DEFAULT NULL,
  `zoneId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Parent_Id`),
  KEY `FK__country_master` (`Country_Id`),
  KEY `FK__timezone_master` (`Timezone_Id`),
  KEY `FK8D15341764D72DFC` (`User_Id`),
  KEY `FK8D153417441D6D95` (`zoneId`),
  CONSTRAINT `FK__country_master` FOREIGN KEY (`Country_Id`) REFERENCES `country_master` (`Country_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_parent_profile_details_user` FOREIGN KEY (`User_Id`) REFERENCES `user` (`User_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_parent_profile_details_zone` FOREIGN KEY (`Timezone_Id`) REFERENCES `zone` (`zone_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_parent_profile_details_zone_2` FOREIGN KEY (`zoneId`) REFERENCES `zone` (`zone_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.parent_profile_details: ~13 rows (approximately)
/*!40000 ALTER TABLE `parent_profile_details` DISABLE KEYS */;
INSERT INTO `parent_profile_details` (`Parent_Id`, `FirstName`, `LastName`, `Country_Id`, `Timezone_Id`, `User_Id`, `zoneId`) VALUES
	(1, 'Tarun', 'Gupta', 12, 1, 5, NULL),
	(2, 'parentCheck', 'Blank student', 95, NULL, 26, 180),
	(3, 'fdgdfg', 'dfgdfgd', 140, NULL, 28, 252),
	(4, 'asdsa', 'dasdasd', 157, NULL, 29, 272),
	(5, 'cxvx', 'cxcvxcvx', 48, NULL, 30, 124),
	(6, 'dfsf', 'sdfs', 157, NULL, 33, 272),
	(7, 'dsfsdf', 'sdfsf', 28, NULL, 34, 61),
	(8, 'dfgdfg', 'dgf', 44, NULL, 37, 119),
	(9, 'party', 'erswers', 99, NULL, 49, 191),
	(10, NULL, NULL, NULL, NULL, NULL, NULL),
	(11, NULL, NULL, NULL, NULL, NULL, NULL),
	(12, NULL, NULL, NULL, NULL, NULL, NULL),
	(13, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `parent_profile_details` ENABLE KEYS */;


-- Dumping structure for table miprofe.parent_student_relationship
CREATE TABLE IF NOT EXISTS `parent_student_relationship` (
  `Parent_Student_Relationship_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Parent_Profile_Id` int(11) DEFAULT NULL,
  `Student_Profile_Id` int(11) DEFAULT NULL,
  `is_Verified` varchar(255) DEFAULT NULL,
  `Parent_Email` varchar(255) DEFAULT NULL,
  `Student_Email` varchar(255) DEFAULT NULL,
  `addedBy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Parent_Student_Relationship_Id`),
  KEY `FK__parent_profile_details` (`Parent_Profile_Id`),
  KEY `FK__student_profile_details` (`Student_Profile_Id`),
  CONSTRAINT `FK__parent_profile_details` FOREIGN KEY (`Parent_Profile_Id`) REFERENCES `parent_profile_details` (`Parent_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__student_profile_details` FOREIGN KEY (`Student_Profile_Id`) REFERENCES `student_profile_details` (`Student_Profile_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.parent_student_relationship: ~26 rows (approximately)
/*!40000 ALTER TABLE `parent_student_relationship` DISABLE KEYS */;
INSERT INTO `parent_student_relationship` (`Parent_Student_Relationship_Id`, `Parent_Profile_Id`, `Student_Profile_Id`, `is_Verified`, `Parent_Email`, `Student_Email`, `addedBy`) VALUES
	(1, 1, NULL, 'N', 'newtut@test.com', 'test@test.com', 'student'),
	(2, 2, NULL, 'N', 'parent@chktst.com', 'child1@par.com', 'parent'),
	(3, 2, NULL, 'N', 'parent@chktst.com', NULL, 'parent'),
	(4, 3, NULL, 'N', 'parent@chk1.com', 'chld1@chd.com', 'parent'),
	(5, 3, NULL, 'N', 'parent@chk1.com', 'sdfsd@fds.com', 'parent'),
	(6, 4, NULL, 'N', 'par@par.com', 'asdadsas@sad.com', 'parent'),
	(7, 4, NULL, 'N', 'par@par.com', 'sdfsdf@sfds.com', 'parent'),
	(8, 4, NULL, 'N', 'par@par.com', NULL, 'parent'),
	(9, 5, NULL, 'N', 'testing@par.com', 'xcvxvx@sdsd.com', 'parent'),
	(10, NULL, 21, 'N', 'parent1@chk.com', 'student1@test.com', 'student'),
	(11, 6, NULL, 'N', 'parent1@hhh.com', 'parent1@hhh.com', 'parent'),
	(12, 7, NULL, 'N', 'parent2@hhh.com', 'sdfsdfds@sds.com', 'parent'),
	(13, 7, NULL, 'N', 'par@tt.com', 'SDFsdfs@sd.com', 'parent'),
	(14, NULL, 22, 'N', 'sjdfhskf@par.com', 'stu@ind1.com', 'student'),
	(15, 8, 27, 'Y', 'par@tt.com', 'stu@mi.com', 'parent'),
	(16, 8, 26, 'Y', 'par@tt.com', 'student1@mi.com', 'parent'),
	(17, 8, 23, 'Y', 'par@tt.com', '29@stu.com', 'parent'),
	(18, 8, 24, 'Y', 'par@tt.com', 'stu@chille.com', 'parent'),
	(19, 9, 28, 'N', 'par@stu2.com', 'new@stu2.com', 'parent'),
	(20, 9, 27, 'Y', 'par@stu2.com', 'stu@mi.com', 'parent'),
	(33, 10, 43, 'N', 'ghgfhhh@lll.lll', 'gfhgfhf@fdgfdg.com', 'student'),
	(34, 11, 44, 'N', 'vbnvnvnv@cascase.com', 'sbhfjksf@sdhsd.com', 'student'),
	(35, 8, 50, 'N', 'par@tt.com', 'fgfdg@qwerewq.com', 'student'),
	(36, 2, 51, 'N', 'parent@chktst.com', 'retet@sdfsf.com', 'student'),
	(37, 12, 52, 'N', 'papa@a.cl', 'dsfdfssd@sdsd.com', 'student'),
	(38, 13, 53, 'N', 'zxczxczx#@xzczx.com', 'fffff@asd.dsa', 'student');
/*!40000 ALTER TABLE `parent_student_relationship` ENABLE KEYS */;


-- Dumping structure for table miprofe.plan_master
CREATE TABLE IF NOT EXISTS `plan_master` (
  `Plan_Master_Id` int(10) NOT NULL AUTO_INCREMENT,
  `Plan_Name` varchar(100) DEFAULT '0',
  `Plan_Min` varchar(50) DEFAULT '0',
  PRIMARY KEY (`Plan_Master_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.plan_master: ~4 rows (approximately)
/*!40000 ALTER TABLE `plan_master` DISABLE KEYS */;
INSERT INTO `plan_master` (`Plan_Master_Id`, `Plan_Name`, `Plan_Min`) VALUES
	(1, 'Basic', '60'),
	(2, 'Popular', '120'),
	(3, 'Plus', '240'),
	(4, 'Buy MInutes', '0');
/*!40000 ALTER TABLE `plan_master` ENABLE KEYS */;


-- Dumping structure for table miprofe.plan_rate
CREATE TABLE IF NOT EXISTS `plan_rate` (
  `Plan_Rate_Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Country_Id` int(11) DEFAULT NULL,
  `Plan_Master_Id` int(11) DEFAULT NULL,
  `Rate` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `hours` int(11) NOT NULL,
  PRIMARY KEY (`Plan_Rate_Id`),
  KEY `FK_plan_rate_country_master` (`Country_Id`),
  KEY `FK_plan_rate_plan_master` (`Plan_Master_Id`),
  CONSTRAINT `FK_plan_rate_country_master` FOREIGN KEY (`Country_Id`) REFERENCES `country_master` (`Country_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_plan_rate_plan_master` FOREIGN KEY (`Plan_Master_Id`) REFERENCES `plan_master` (`Plan_Master_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.plan_rate: ~13 rows (approximately)
/*!40000 ALTER TABLE `plan_rate` DISABLE KEYS */;
INSERT INTO `plan_rate` (`Plan_Rate_Id`, `Country_Id`, `Plan_Master_Id`, `Rate`, `description`, `hours`) VALUES
	(1, 12, 1, 24, NULL, 0),
	(2, 12, 2, 44, NULL, 0),
	(3, 12, 3, 80, NULL, 0),
	(4, 12, 4, 32, NULL, 0),
	(5, 44, 1, 20, '', 1),
	(6, 44, 2, 40, '', 2),
	(7, 44, 3, 80, '', 4),
	(8, 44, 4, 24, '', 1),
	(9, 170, 1, 11, 'dasdasds', 11),
	(10, NULL, 1, 20, NULL, 1),
	(11, NULL, 2, 40, NULL, 2),
	(12, NULL, 3, 60, NULL, 4),
	(13, NULL, 4, 80, NULL, 1);
/*!40000 ALTER TABLE `plan_rate` ENABLE KEYS */;


-- Dumping structure for table miprofe.reset_password
CREATE TABLE IF NOT EXISTS `reset_password` (
  `reset_password_Id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `vCode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`reset_password_Id`),
  KEY `FK__user_reset` (`userId`),
  CONSTRAINT `FK__user_reset` FOREIGN KEY (`userId`) REFERENCES `user` (`User_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.reset_password: ~6 rows (approximately)
/*!40000 ALTER TABLE `reset_password` DISABLE KEYS */;
INSERT INTO `reset_password` (`reset_password_Id`, `userId`, `vCode`) VALUES
	(7, 50, '8tipvlg5qsia'),
	(11, 7, '7avrl9mg917e'),
	(12, 7, '3gcq1icdbo31'),
	(13, 7, 'oc5e1aickpct'),
	(14, 7, 'gdqi7b6kiuup'),
	(15, 7, '1gaaet9kebol');
/*!40000 ALTER TABLE `reset_password` ENABLE KEYS */;


-- Dumping structure for table miprofe.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `Role_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Role_Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Role_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.roles: ~6 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`Role_Id`, `Role_Name`) VALUES
	(1, 'Parent'),
	(2, 'Student'),
	(3, 'Tutor'),
	(4, 'Admin'),
	(5, 'SysAdmin'),
	(6, 'Support');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


-- Dumping structure for table miprofe.student_account_activity
CREATE TABLE IF NOT EXISTS `student_account_activity` (
  `Student_Account_Activity_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Activity_Name` varchar(100) DEFAULT NULL,
  `Activity_Date` date DEFAULT NULL,
  `Activity_Minute` varchar(50) DEFAULT NULL,
  `Min_Balance` varchar(50) DEFAULT NULL,
  `Amount` varchar(50) DEFAULT NULL,
  `Student_Profile_Id` int(11) DEFAULT NULL,
  `Tutor_Profile_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Student_Account_Activity_Id`),
  KEY `FK__student_profile_details_acoount_activity` (`Student_Profile_Id`),
  KEY `FK_student_account_activity_tutor_profile_detail` (`Tutor_Profile_Id`),
  CONSTRAINT `FK__student_profile_details_acoount_activity` FOREIGN KEY (`Student_Profile_Id`) REFERENCES `student_profile_details` (`Student_Profile_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_account_activity_tutor_profile_detail` FOREIGN KEY (`Tutor_Profile_Id`) REFERENCES `tutor_profile_detail` (`Tutor_Profile_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.student_account_activity: ~32 rows (approximately)
/*!40000 ALTER TABLE `student_account_activity` DISABLE KEYS */;
INSERT INTO `student_account_activity` (`Student_Account_Activity_Id`, `Activity_Name`, `Activity_Date`, `Activity_Minute`, `Min_Balance`, `Amount`, `Student_Profile_Id`, `Tutor_Profile_Id`) VALUES
	(1, 'Basic', '2015-05-01', '60', '60', '20', 24, NULL),
	(2, 'Popular', '2015-05-01', '120', '180', '40', 24, NULL),
	(3, 'Plus', '2015-05-01', '240', '420', '80', 24, NULL),
	(4, 'Basic', '2015-05-01', '60', '480', '20', 24, NULL),
	(5, 'Miprofe: Acoustics-Classroom', '2015-05-01', '60', '420', 'NA', 24, NULL),
	(6, 'Basic', '2015-05-01', '60', '480', '20', 24, NULL),
	(7, 'Plus', '2015-05-01', '240', '720', '80', 24, NULL),
	(8, 'Miprofe: Acoustics-Classroom', '2015-05-01', '30', '690', 'NA', 24, NULL),
	(9, 'Miprofe: Acoustics-Classroom', '2015-05-04', '60', '630', 'NA', 24, NULL),
	(10, 'Miprofe: Acoustics-Classroom', '2015-05-05', '1', '629', 'NA', 24, NULL),
	(11, 'Miprofe: Acoustics-Classroom', '2015-05-05', '15', '614', 'NA', 24, NULL),
	(12, 'Miprofe: Acoustics-Classroom', '2015-05-05', '15', '599', 'NA', 24, NULL),
	(13, 'Miprofe: Acoustics-Classroom', '2015-05-06', '15', '584', 'NA', 24, NULL),
	(14, 'Basic', '2015-05-11', '60', '60', '20', 27, NULL),
	(15, 'Plus', '2015-05-11', '240', '300', '60', 27, NULL),
	(16, 'Basic', '2015-05-12', '60', '360', '20', 27, NULL),
	(17, 'Basic', '2015-05-12', '60', '60', '20', 26, NULL),
	(18, 'Basic', '2015-05-12', '60', '60', '20', 23, NULL),
	(19, 'Plus', '2015-05-12', '240', '300', '60', 26, NULL),
	(20, 'Miprofe: Acoustics-Classroom', '2015-05-15', '15', '285', 'NA', 26, NULL),
	(21, 'Miprofe: Calculus-Classroom', '2015-05-29', '15', '345', 'NA', 27, NULL),
	(22, 'Miprofe: Acoustics-Classroom', '2015-05-30', '45', '300', 'NA', 27, NULL),
	(23, 'Plus', '2015-06-10', '240', '240', '60', 57, NULL),
	(24, 'Miprofe: Spanish-Classroom [Cancelation Penalty]', '2015-06-10', '15', '225', 'NA', 57, NULL),
	(25, 'Miprofe: Spanish-Classroom [Cancelation Penalty]', '2015-06-12', '15', '210', 'NA', 57, NULL),
	(26, 'Miprofe: Spanish-Classroom [Cancelation Penalty]', '2015-06-12', '15', '195', 'NA', 57, NULL),
	(27, 'Miprofe: Spanish-Classroom [Cancelation Compensation]', '2015-06-12', '15', '210', 'NA', 57, NULL),
	(28, 'Miprofe: Spanish-Classroom [Cancelation Compensation]', '2015-06-12', '15', '225', 'NA', 57, NULL),
	(29, 'Miprofe: Spanish-Classroom [Cancelation Compensation]', '2015-06-12', '15', '240', 'NA', 57, NULL),
	(30, 'Miprofe: Spanish-Classroom', '2015-06-15', '15', '225', 'NA', 57, NULL),
	(31, 'Miprofe: Spanish-Classroom', '2015-06-15', '15', '210', 'NA', 57, NULL),
	(32, 'Miprofe: Spanish-Classroom', '2015-06-15', '15', '195', 'NA', 57, NULL);
/*!40000 ALTER TABLE `student_account_activity` ENABLE KEYS */;


-- Dumping structure for table miprofe.student_profile_details
CREATE TABLE IF NOT EXISTS `student_profile_details` (
  `Student_Profile_Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_Id` int(11) DEFAULT NULL,
  `First_Name` varchar(50) DEFAULT NULL,
  `Last_Name` varchar(50) DEFAULT NULL,
  `BirthDate` datetime DEFAULT NULL,
  `Parent_Email` varchar(100) DEFAULT NULL,
  `Country_Id` int(11) DEFAULT NULL,
  `Time_Zone_Id` int(11) DEFAULT NULL,
  `Education_Type_Id` int(11) DEFAULT NULL,
  `Level_Id` int(11) DEFAULT NULL,
  `Career_Type_Id` int(11) DEFAULT NULL,
  `Plan_Master_Id` int(11) DEFAULT NULL,
  `Min_Balance` varchar(50) DEFAULT NULL,
  `zoneId` int(11) DEFAULT NULL,
  `career` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Student_Profile_Id`),
  KEY `FK__user` (`User_Id`),
  KEY `FK_student_profile_details_country_master` (`Country_Id`),
  KEY `FK_student_profile_details_education_type_master` (`Education_Type_Id`),
  KEY `FK_student_profile_details_timezone_master` (`Time_Zone_Id`),
  KEY `FK_student_profile_details_level_master` (`Level_Id`),
  KEY `FK_student_profile_details_career_type_master` (`Career_Type_Id`),
  KEY `FK4C8D6BA8AFC4C79` (`Time_Zone_Id`),
  KEY `FK_student_profile_details_plan_master` (`Plan_Master_Id`),
  KEY `FK4C8D6BA8441D6D95` (`zoneId`),
  CONSTRAINT `FK4C8D6BA8441D6D95` FOREIGN KEY (`zoneId`) REFERENCES `zone` (`zone_id`),
  CONSTRAINT `FK4C8D6BA8AFC4C79` FOREIGN KEY (`Time_Zone_Id`) REFERENCES `timezone_master` (`Timezne_Id`),
  CONSTRAINT `FK__user` FOREIGN KEY (`User_Id`) REFERENCES `user` (`User_Id`),
  CONSTRAINT `FK_student_profile_details_career_type_master` FOREIGN KEY (`Career_Type_Id`) REFERENCES `career_type_master` (`Career_Type_Id`),
  CONSTRAINT `FK_student_profile_details_country_master` FOREIGN KEY (`Country_Id`) REFERENCES `country_master` (`Country_Id`),
  CONSTRAINT `FK_student_profile_details_education_type_master` FOREIGN KEY (`Education_Type_Id`) REFERENCES `education_type_master` (`Education_Type_Id`),
  CONSTRAINT `FK_student_profile_details_level_master` FOREIGN KEY (`Level_Id`) REFERENCES `level_master` (`Level_Id`),
  CONSTRAINT `FK_student_profile_details_plan_master` FOREIGN KEY (`Plan_Master_Id`) REFERENCES `plan_master` (`Plan_Master_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_profile_details_zone` FOREIGN KEY (`Time_Zone_Id`) REFERENCES `zone` (`zone_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.student_profile_details: ~47 rows (approximately)
/*!40000 ALTER TABLE `student_profile_details` DISABLE KEYS */;
INSERT INTO `student_profile_details` (`Student_Profile_Id`, `User_Id`, `First_Name`, `Last_Name`, `BirthDate`, `Parent_Email`, `Country_Id`, `Time_Zone_Id`, `Education_Type_Id`, `Level_Id`, `Career_Type_Id`, `Plan_Master_Id`, `Min_Balance`, `zoneId`, `career`, `grade`) VALUES
	(1, 4, 'Raman', 'deep', '1984-01-01 00:03:00', '', 1, 1, 2, 2, 2, NULL, NULL, NULL, NULL, NULL),
	(2, 6, '', 'stuart', '1946-01-12 00:03:00', '', 18, 4, 1, 1, 1, NULL, NULL, NULL, NULL, NULL),
	(3, 7, 'sdfsd', 'fsdfsdfs', '1945-01-24 00:04:00', '', 17, 2, 1, 1, 1, NULL, NULL, NULL, NULL, NULL),
	(14, 13, 'bvvcbvb', 'cvbcvb', '1961-01-04 00:04:00', '', 167, NULL, 2, 1, 1, NULL, NULL, NULL, NULL, NULL),
	(15, 14, 'fgh', 'fghf', '1959-01-08 00:04:00', '', 167, NULL, 1, 1, 1, NULL, NULL, 281, NULL, NULL),
	(16, 15, 'addy', 'aryan', '1979-01-24 00:04:00', '', 44, NULL, 1, 1, 1, NULL, NULL, 119, NULL, NULL),
	(17, 16, 'addy', 'arya', '1988-01-12 00:04:00', '', 44, NULL, 1, 1, 1, NULL, NULL, 119, NULL, NULL),
	(18, 17, 'tarun', 'gupta', '1964-01-28 00:04:00', '', 12, NULL, 1, 1, 1, NULL, NULL, 19, NULL, NULL),
	(19, 20, 'student', 'test', '1978-01-04 00:04:00', '', 99, NULL, 1, 1, 1, NULL, NULL, 191, NULL, NULL),
	(20, 21, 'tarun', 'gupta', '1980-01-09 00:04:00', '', 44, NULL, 1, 1, 1, NULL, NULL, 119, NULL, NULL),
	(21, 31, 'student null', 'null', '1948-01-06 00:04:00', '', 60, NULL, 1, 1, 1, NULL, NULL, 191, NULL, NULL),
	(22, 35, 'sfds', 'dfsdf', '1959-01-15 00:04:00', '', 99, NULL, 1, 1, 1, NULL, NULL, 191, NULL, NULL),
	(23, 39, 'TU', 'thh', '1970-01-01 00:04:00', '', 99, NULL, 1, 1, 1, 1, '60', 191, NULL, NULL),
	(24, 40, 'chille', 'stu', '1976-01-27 00:04:00', '', 44, NULL, 1, 1, 1, 3, '584', 191, NULL, NULL),
	(25, 42, 'dsddddddddddd', 'gffffffff', '1959-01-19 00:05:00', '', 44, NULL, 4, NULL, NULL, NULL, NULL, 119, 'sdfs', 'dfsdf'),
	(26, 44, 'stu', 'stu', '1970-01-05 00:05:00', '', 99, NULL, 1, NULL, NULL, 3, '285', 191, 'fb', 'gfh'),
	(27, 45, 'we', 'we', '1966-01-09 00:05:00', '', 99, NULL, 1, NULL, NULL, 1, '300', 191, 'werwerwe', 'ewew'),
	(28, 48, 'stuyyyy', 'tark', '1985-01-07 00:05:00', 'par@stu2.com', 99, NULL, 1, NULL, NULL, NULL, NULL, 191, '', ''),
	(29, 51, 'stuM', 'stuindiain', '1959-01-05 00:05:00', '', 99, NULL, 2, NULL, NULL, NULL, NULL, 191, 'sdfds', 'fsdf'),
	(30, 54, 'sdf', 'sfdsdfsd', '1958-01-14 00:05:00', '', 99, NULL, 1, NULL, NULL, NULL, NULL, 191, 'sdfsd', 'fsfd'),
	(31, 55, 'dsf', 'dsfsdf', '1973-01-08 00:05:00', 'dfsfddsf@sdsds.com', 157, NULL, 2, NULL, NULL, NULL, NULL, 272, 'sdfsd', 'fsdfsfd'),
	(32, 56, 'sdfs', 'dfdsfsdf', '2015-01-04 00:05:00', 'sdfsdfsfsf@dfdf.ccc', 170, NULL, 1, NULL, NULL, NULL, NULL, 282, 'sdfsd', 'fsdfsdf'),
	(33, 56, 'sdfs', 'dfdsfsdf', '2015-01-04 00:05:00', 'sdfsdfsfsf@dfdf.ccc', 170, NULL, 1, NULL, NULL, NULL, NULL, 282, 'sdfsd', 'fsdfsdf'),
	(34, 56, 'sdfs', 'dfdsfsdf', '2015-01-04 00:05:00', 'sdfsdfsfsf@dfdf.ccc', 170, NULL, 1, NULL, NULL, NULL, NULL, 282, 'sdfsd', 'fsdfsdf'),
	(35, 56, 'sdfs', 'dfdsfsdf', '2015-01-04 00:05:00', 'sdfsdfsfsf@dfdf.ccc', 170, NULL, 1, NULL, NULL, NULL, NULL, 282, 'sdfsd', 'fsdfsdf'),
	(36, 56, 'sdfs', 'dfdsfsdf', '2015-01-04 00:05:00', 'sdfsdfsfsf@dfdf.ccc', 170, NULL, 1, NULL, NULL, NULL, NULL, 282, 'sdfsd', 'fsdfsdf'),
	(37, 56, 'sdfs', 'dfdsfsdf', '2015-01-04 00:05:00', 'sdfsdfsfsf@dfdf.ccc', 170, NULL, 1, NULL, NULL, NULL, NULL, 282, 'sdfsd', 'fsdfsdf'),
	(38, 56, 'sdfs', 'dfdsfsdf', '2015-01-04 00:05:00', 'sdfsdfsfsf@dfdf.ccc', 170, NULL, 1, NULL, NULL, NULL, NULL, 282, 'sdfsd', 'fsdfsdf'),
	(39, 57, 'fghgf', 'hfhfghfghfg', '2015-01-12 00:05:00', 'ghgfhhh@lll.lll', 169, NULL, 1, NULL, NULL, NULL, NULL, 300, 'fghfgh', 'gfhfgh'),
	(40, 57, 'fghgf', 'hfhfghfghfg', '2015-01-12 00:05:00', 'ghgfhhh@lll.lll', 169, NULL, 1, NULL, NULL, NULL, NULL, 300, 'fghfgh', 'gfhfgh'),
	(41, 57, 'fghgf', 'hfhfghfghfg', '2015-01-12 00:05:00', 'ghgfhhh@lll.lll', 169, NULL, 1, NULL, NULL, NULL, NULL, 300, 'fghfgh', 'gfhfgh'),
	(42, 57, 'fghgf', 'hfhfghfghfg', '2015-01-12 00:05:00', 'ghgfhhh@lll.lll', 169, NULL, 1, NULL, NULL, NULL, NULL, 300, 'fghfgh', 'gfhfgh'),
	(43, 57, 'fghgf', 'hfhfghfghfg', '2015-01-12 00:05:00', 'ghgfhhh@lll.lll', 169, NULL, 1, NULL, NULL, NULL, NULL, 300, 'fghfgh', 'gfhfgh'),
	(44, 58, 'vbnvn', 'bnvnb', '2015-01-13 00:05:00', 'vbnvnvnv@cascase.com', 169, NULL, 3, NULL, NULL, NULL, NULL, 300, 'vbnvn', ''),
	(45, 59, 'retreter', 'tertet', '2015-01-05 00:05:00', 'par@tt.com', 157, NULL, 2, NULL, NULL, NULL, NULL, 272, 'erter', 'tert'),
	(46, 59, 'retreter', 'tertet', '2015-01-05 00:05:00', 'par@tt.com', 157, NULL, 2, NULL, NULL, NULL, NULL, 272, 'erter', 'tert'),
	(47, 59, 'retreter', 'tertet', '2015-01-05 00:05:00', 'par@tt.com', 157, NULL, 2, NULL, NULL, NULL, NULL, 272, 'erter', 'tert'),
	(48, 59, 'retreter', 'tertet', '2015-01-05 00:05:00', 'par@tt.com', 157, NULL, 2, NULL, NULL, NULL, NULL, 272, 'erter', 'tert'),
	(49, 59, 'retreter', 'tertet', '2015-01-05 00:05:00', 'par@tt.com', 157, NULL, 2, NULL, NULL, NULL, NULL, 272, 'erter', 'tert'),
	(50, 59, 'retreter', 'tertet', '2015-01-05 00:05:00', 'par@tt.com', 157, NULL, 2, NULL, NULL, NULL, NULL, 272, 'erter', 'tert'),
	(51, 60, 'sdfsdf', 'sfsfdsfd', '2015-01-12 00:05:00', 'parent@chktst.com', 169, NULL, 1, NULL, NULL, NULL, NULL, 300, 'sdfsdf', 'sdfsf'),
	(52, 61, 'dfgd', 'gdfgdfg', '2015-01-05 00:05:00', 'papa@a.cl', 167, NULL, 1, NULL, NULL, NULL, NULL, 281, 'dfgfdg', 'dgd'),
	(53, 63, 'zxc', 'zxc', '2015-01-02 00:06:00', 'zxczxczx#@xzczx.com', 157, NULL, 1, NULL, NULL, NULL, NULL, 272, 'zxc', 'zxc'),
	(54, 64, 'FIREBASE', 'USER', '1947-01-03 00:06:00', '', 12, NULL, 2, NULL, NULL, NULL, NULL, 19, 'sdf', 'sdf'),
	(55, 65, 'asasd', 'asdasd', '1946-01-11 00:06:00', '', 157, NULL, 2, NULL, NULL, NULL, NULL, 272, 'asd', 'asda'),
	(56, 66, 'fireUSer', 'asdsa', '1945-01-19 00:06:00', '', 157, NULL, 2, NULL, NULL, NULL, NULL, 272, '', 'asd'),
	(57, 67, 'student', 'fdsdfsdfsdf', '1949-01-14 00:06:00', '', 99, NULL, 1, NULL, NULL, 3, '195', 272, 'sdfsfd', 'sdf');
/*!40000 ALTER TABLE `student_profile_details` ENABLE KEYS */;


-- Dumping structure for table miprofe.subjects
CREATE TABLE IF NOT EXISTS `subjects` (
  `Subjects_Id` int(10) NOT NULL AUTO_INCREMENT,
  `Subject_Type_Master_Id` int(10) DEFAULT '0',
  `Subject_Name` varchar(50) DEFAULT '0',
  PRIMARY KEY (`Subjects_Id`),
  KEY `FK__subject_type_master` (`Subject_Type_Master_Id`),
  CONSTRAINT `FK__subject_type_master` FOREIGN KEY (`Subject_Type_Master_Id`) REFERENCES `subject_type_master` (`Subject_Type_Master_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.subjects: ~18 rows (approximately)
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` (`Subjects_Id`, `Subject_Type_Master_Id`, `Subject_Name`) VALUES
	(1, 1, 'Java'),
	(2, 1, 'C++'),
	(3, 1, 'php'),
	(4, 2, 'Spanish'),
	(5, 2, 'English'),
	(6, 2, 'Hindi'),
	(7, 3, 'Trignometry'),
	(8, 3, 'Calculus'),
	(9, 3, 'Algebra'),
	(10, 4, 'Acoustics'),
	(11, 4, 'Astronomy'),
	(12, 4, 'Atomic Physics'),
	(13, 5, 'Analytical'),
	(14, 5, 'Biomolecular'),
	(15, 5, 'Electrochemistry'),
	(16, 6, 'Entomology'),
	(17, 6, 'Biogeography '),
	(18, 6, 'Chronobiology');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;


-- Dumping structure for table miprofe.subject_type_master
CREATE TABLE IF NOT EXISTS `subject_type_master` (
  `Subject_Type_Master_Id` int(10) NOT NULL AUTO_INCREMENT,
  `Subject_Type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Subject_Type_Master_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.subject_type_master: ~6 rows (approximately)
/*!40000 ALTER TABLE `subject_type_master` DISABLE KEYS */;
INSERT INTO `subject_type_master` (`Subject_Type_Master_Id`, `Subject_Type`) VALUES
	(1, 'Programming'),
	(2, 'Foreign Languages'),
	(3, 'Math'),
	(4, 'Physics'),
	(5, 'Chemistry'),
	(6, 'Biology');
/*!40000 ALTER TABLE `subject_type_master` ENABLE KEYS */;


-- Dumping structure for table miprofe.suggested_tutor
CREATE TABLE IF NOT EXISTS `suggested_tutor` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_Id` int(11) DEFAULT NULL,
  `student_Id` int(11) DEFAULT NULL,
  `tutor_Id` int(11) DEFAULT NULL,
  `comments` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_parentId` (`parent_Id`),
  KEY `FK_studentId` (`student_Id`),
  KEY `FK_tutorId` (`tutor_Id`),
  CONSTRAINT `FK_parentId` FOREIGN KEY (`parent_Id`) REFERENCES `parent_profile_details` (`Parent_Id`),
  CONSTRAINT `FK_studentId` FOREIGN KEY (`student_Id`) REFERENCES `student_profile_details` (`Student_Profile_Id`),
  CONSTRAINT `FK_tutorId` FOREIGN KEY (`tutor_Id`) REFERENCES `tutor_profile_detail` (`Tutor_Profile_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.suggested_tutor: ~9 rows (approximately)
/*!40000 ALTER TABLE `suggested_tutor` DISABLE KEYS */;
INSERT INTO `suggested_tutor` (`Id`, `parent_Id`, `student_Id`, `tutor_Id`, `comments`) VALUES
	(9, 8, 26, 2, NULL),
	(10, 8, 26, 2, NULL),
	(17, 8, 27, 7, NULL),
	(18, 8, 27, 12, NULL),
	(19, 8, 23, 12, NULL),
	(20, 8, 23, 13, NULL),
	(22, 8, 23, 7, NULL),
	(23, 9, 27, 7, NULL),
	(24, 9, 27, 12, NULL);
/*!40000 ALTER TABLE `suggested_tutor` ENABLE KEYS */;


-- Dumping structure for table miprofe.superadmin_profile_details
CREATE TABLE IF NOT EXISTS `superadmin_profile_details` (
  `super_Admin_Profile_Id` int(11) NOT NULL AUTO_INCREMENT,
  `first_Name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `image_Name` varchar(255) DEFAULT NULL,
  `image_Url` varchar(255) DEFAULT NULL,
  `last_Name` varchar(255) DEFAULT NULL,
  `mobile_Number` varchar(255) DEFAULT NULL,
  `User_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`super_Admin_Profile_Id`),
  KEY `FK66B8E02164D72DFC` (`User_Id`),
  CONSTRAINT `FK66B8E02164D72DFC` FOREIGN KEY (`User_Id`) REFERENCES `user` (`User_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.superadmin_profile_details: ~0 rows (approximately)
/*!40000 ALTER TABLE `superadmin_profile_details` DISABLE KEYS */;
INSERT INTO `superadmin_profile_details` (`super_Admin_Profile_Id`, `first_Name`, `gender`, `image_Name`, `image_Url`, `last_Name`, `mobile_Number`, `User_Id`) VALUES
	(1, 'admin', 'male', NULL, NULL, 'adm', '1111111111', 2);
/*!40000 ALTER TABLE `superadmin_profile_details` ENABLE KEYS */;


-- Dumping structure for table miprofe.support_profile_details
CREATE TABLE IF NOT EXISTS `support_profile_details` (
  `support_Profile_Id` int(11) NOT NULL AUTO_INCREMENT,
  `first_Name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_Name` varchar(255) DEFAULT NULL,
  `mobile_Number` varchar(255) DEFAULT NULL,
  `User_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`support_Profile_Id`),
  KEY `FK8A9BC13C64D72DFC` (`User_Id`),
  CONSTRAINT `FK8A9BC13C64D72DFC` FOREIGN KEY (`User_Id`) REFERENCES `user` (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.support_profile_details: ~0 rows (approximately)
/*!40000 ALTER TABLE `support_profile_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `support_profile_details` ENABLE KEYS */;


-- Dumping structure for table miprofe.timezone_master
CREATE TABLE IF NOT EXISTS `timezone_master` (
  `Timezne_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Timezone_Name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Timezne_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.timezone_master: ~3 rows (approximately)
/*!40000 ALTER TABLE `timezone_master` DISABLE KEYS */;
INSERT INTO `timezone_master` (`Timezne_Id`, `Timezone_Name`) VALUES
	(1, 'Alpha Time Zone'),
	(2, 'Australian Central Daylight Time'),
	(4, 'Australian Central Standard Time ');
/*!40000 ALTER TABLE `timezone_master` ENABLE KEYS */;


-- Dumping structure for table miprofe.tutor_account_activity
CREATE TABLE IF NOT EXISTS `tutor_account_activity` (
  `Tutor_Account_Activity_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Activity_Name` varchar(100) DEFAULT NULL,
  `Activity_Date` date DEFAULT NULL,
  `Activity_Minute` varchar(50) DEFAULT NULL,
  `Amount` varchar(50) DEFAULT NULL,
  `Tutor_Profile_Id` int(11) DEFAULT NULL,
  `Admin_Payment` varchar(12) DEFAULT NULL,
  `Is_Deleted` varchar(5) NOT NULL DEFAULT 'N',
  `Balance` varchar(255) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Tutor_Account_Activity_Id`),
  KEY `FK_tutor_account_activity_tutor_profile_detail` (`Tutor_Profile_Id`),
  CONSTRAINT `FK_tutor_account_activity_tutor_profile_detail` FOREIGN KEY (`Tutor_Profile_Id`) REFERENCES `tutor_profile_detail` (`Tutor_Profile_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.tutor_account_activity: ~35 rows (approximately)
/*!40000 ALTER TABLE `tutor_account_activity` DISABLE KEYS */;
INSERT INTO `tutor_account_activity` (`Tutor_Account_Activity_Id`, `Activity_Name`, `Activity_Date`, `Activity_Minute`, `Amount`, `Tutor_Profile_Id`, `Admin_Payment`, `Is_Deleted`, `Balance`, `Status`) VALUES
	(9, 'Miprofe: Acoustics-Classroom', '2015-05-18', '60', '10.0', 12, NULL, 'N', '10.0', 'Pending'),
	(10, 'Miprofe: Acoustics-Classroom', '2015-05-18', '60', '10.0', 12, NULL, 'Y', '10.0', 'Paid'),
	(11, 'Miprofe: Acoustics-Classroom', '2015-05-19', '60', '10.0', 12, NULL, 'Y', '20.0', 'Cancel'),
	(12, 'fgdfgdgd', '2015-05-18', '80', '12.0', 3, NULL, 'N', '12', 'Pending'),
	(13, 'Miprofe: Acoustics-Classroom', '2015-05-26', '15', '2.5', 12, NULL, 'N', '12.5', 'Pending'),
	(14, 'Miprofe: Acoustics-Classroom', '2015-05-26', '15', '2.5', 12, NULL, 'N', '15.0', 'Pending'),
	(15, 'Miprofe: Astronomy-Classroom', '2015-05-27', '30', '5.0', 12, NULL, 'N', '20.0', 'Pending'),
	(16, 'Miprofe: Acoustics-Classroom', '2015-05-27', '15', '2.5', 12, NULL, 'N', '22.5', 'Pending'),
	(17, 'Miprofe: Acoustics-Classroom', '2015-05-27', '15', '2.5', 12, NULL, 'N', '25.0', 'Pending'),
	(18, 'Miprofe: Calculus-Classroom', '2015-05-29', '15', '2.5', 12, NULL, 'N', '27.5', 'Pending'),
	(19, 'Miprofe: Biomolecular-Classroom', '2015-05-29', '15', '2.5', 12, NULL, 'N', '30.0', 'Pending'),
	(20, 'Miprofe: Biomolecular-Classroom', '2015-05-29', '45', '7.5', 12, NULL, 'N', '37.5', 'Pending'),
	(21, 'Miprofe: Acoustics-Classroom', '2015-05-29', '30', '5.0', 12, NULL, 'N', '42.5', 'Pending'),
	(22, 'Miprofe: Acoustics-Classroom', '2015-05-30', '60', '10.0', 12, NULL, 'N', '52.5', 'Pending'),
	(23, 'Miprofe: Acoustics-Classroom', '2015-05-30', '45', '7.5', 12, NULL, 'N', '60.0', 'Pending'),
	(24, 'Miprofe: Spanish-Classroom [Cancelation]', '2015-06-10', '15', '-2.5', 18, NULL, 'N', '-2.5', 'Pending'),
	(25, 'Miprofe: Spanish-Classroom [Cancelation]', '2015-06-11', '15', '-2.5', 18, NULL, 'N', '-5.0', 'Pending'),
	(26, 'Miprofe: Spanish-Classroom [Cancelation]', '2015-06-10', '15', '-2.5', 18, NULL, 'N', '-7.5', 'Pending'),
	(27, 'Miprofe: Spanish-Classroom [Cancelation]', '2015-06-11', '15', '-2.5', 18, NULL, 'N', '-10.0', 'Pending'),
	(28, 'Miprofe: Spanish-Classroom [Cancelation]', '2015-06-10', '15', '-2.5', 18, NULL, 'N', '-12.5', 'Pending'),
	(29, 'Miprofe: Spanish-Classroom [Cancelation]', '2015-06-25', '15', '-2.5', 18, NULL, 'N', '-15.0', 'Pending'),
	(30, 'Miprofe: Spanish-Classroom [Cancelation]', '2015-06-10', '15', '-2.5', 18, NULL, 'N', '-17.5', 'Pending'),
	(31, 'Miprofe: Spanish-Classroom [Cancelation]', '2015-06-10', '15', '-2.5', 18, NULL, 'N', '-20.0', 'Pending'),
	(32, 'Miprofe: Spanish-Classroom [Cancelation]', '2015-06-11', '15', '-2.5', 18, NULL, 'N', '-22.5', 'Pending'),
	(33, 'Miprofe: Spanish-Classroom [Cancelation]', '2015-06-10', '15', '-2.5', 18, NULL, 'N', '-25.0', 'Pending'),
	(34, 'Miprofe: Spanish-Classroom', '2015-06-10', '15', '2.5', 18, NULL, 'N', '-22.5', 'Pending'),
	(35, 'Miprofe: Spanish-Classroom [Cancelation Compensation]', '2015-06-10', '15', '2.5', 18, NULL, 'N', '-20.0', 'Pending'),
	(36, 'Miprofe: Spanish-Classroom [Cancelation Compensation]', '2015-06-12', '15', '2.5', 18, NULL, 'N', '-17.5', 'Pending'),
	(37, 'Miprofe: Spanish-Classroom [Cancelation Compensation]', '2015-06-12', '15', '2.5', 18, NULL, 'N', '-15.0', 'Pending'),
	(38, 'Miprofe: Spanish-Classroom [Cancelation Penalty]', '2015-06-12', '15', '-2.5', 18, NULL, 'N', '-17.5', 'Pending'),
	(39, 'Miprofe: Spanish-Classroom [Cancelation Penalty]', '2015-06-12', '15', '-2.5', 18, NULL, 'N', '-20.0', 'Pending'),
	(40, 'Miprofe: Spanish-Classroom [Cancelation Penalty]', '2015-06-12', '15', '-2.5', 18, NULL, 'N', '-22.5', 'Pending'),
	(41, 'Miprofe: Spanish-Classroom', '2015-06-15', '15', '2.5', 18, NULL, 'N', '-20.0', 'Pending'),
	(42, 'Miprofe: Spanish-Classroom', '2015-06-15', '15', '2.5', 18, NULL, 'N', '-17.5', 'Pending'),
	(43, 'Miprofe: Spanish-Classroom', '2015-06-15', '15', '2.5', 18, NULL, 'N', '-15.0', 'Pending');
/*!40000 ALTER TABLE `tutor_account_activity` ENABLE KEYS */;


-- Dumping structure for table miprofe.tutor_chat_sessions
CREATE TABLE IF NOT EXISTS `tutor_chat_sessions` (
  `tutor_chat_Id` int(11) NOT NULL AUTO_INCREMENT,
  `student_profileId` int(11) DEFAULT '0',
  `parent_profileId` int(11) DEFAULT '0',
  `isSession_started` varchar(50) DEFAULT '0',
  `tutor_profileId` int(11) DEFAULT '0',
  PRIMARY KEY (`tutor_chat_Id`),
  KEY `tutorChat_student` (`student_profileId`),
  KEY `tutorChat_parent` (`parent_profileId`),
  KEY `tutorChat_tutor` (`tutor_profileId`),
  CONSTRAINT `tutorChat_parent` FOREIGN KEY (`parent_profileId`) REFERENCES `parent_profile_details` (`Parent_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tutorChat_student` FOREIGN KEY (`student_profileId`) REFERENCES `student_profile_details` (`Student_Profile_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tutorChat_tutor` FOREIGN KEY (`tutor_profileId`) REFERENCES `tutor_profile_detail` (`Tutor_Profile_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.tutor_chat_sessions: ~14 rows (approximately)
/*!40000 ALTER TABLE `tutor_chat_sessions` DISABLE KEYS */;
INSERT INTO `tutor_chat_sessions` (`tutor_chat_Id`, `student_profileId`, `parent_profileId`, `isSession_started`, `tutor_profileId`) VALUES
	(3, 27, 12, 'N', 18),
	(6, 27, 12, 'N', 8),
	(7, 26, 12, 'Y', 12),
	(8, 26, 4, 'Y', 10),
	(9, 26, 8, 'Y', 7),
	(10, 27, 5, 'Y', 10),
	(11, 27, NULL, 'Y', 7),
	(12, 27, NULL, 'Y', 13),
	(13, 57, NULL, 'Y', 7),
	(14, 57, NULL, 'Y', 12),
	(15, 57, NULL, 'N', 18),
	(16, 57, NULL, 'Y', 14),
	(17, 57, NULL, 'Y', 10),
	(18, 57, NULL, 'Y', 13);
/*!40000 ALTER TABLE `tutor_chat_sessions` ENABLE KEYS */;


-- Dumping structure for table miprofe.tutor_fee_per_country
CREATE TABLE IF NOT EXISTS `tutor_fee_per_country` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Country_Id` int(10) DEFAULT NULL,
  `Fee` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_tutor_fee_per_country_country_master` (`Country_Id`),
  CONSTRAINT `FK_tutor_fee_per_country_country_master` FOREIGN KEY (`Country_Id`) REFERENCES `country_master` (`Country_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.tutor_fee_per_country: ~0 rows (approximately)
/*!40000 ALTER TABLE `tutor_fee_per_country` DISABLE KEYS */;
INSERT INTO `tutor_fee_per_country` (`Id`, `Country_Id`, `Fee`) VALUES
	(1, 99, '10');
/*!40000 ALTER TABLE `tutor_fee_per_country` ENABLE KEYS */;


-- Dumping structure for table miprofe.tutor_profile_detail
CREATE TABLE IF NOT EXISTS `tutor_profile_detail` (
  `Tutor_Profile_Id` int(10) NOT NULL AUTO_INCREMENT,
  `User_Id` int(10) DEFAULT NULL,
  `First_Name` varchar(50) DEFAULT NULL,
  `Last_Name` varchar(50) DEFAULT NULL,
  `Mobile_Number` varchar(50) DEFAULT NULL,
  `Country_Id` int(10) DEFAULT NULL,
  `Tax_Id` varchar(50) DEFAULT NULL,
  `Image` longtext,
  `College` varchar(50) DEFAULT NULL,
  `Career` varchar(50) DEFAULT NULL,
  `Graduation_Date` datetime DEFAULT NULL,
  `Time_Zone_Id` int(10) DEFAULT NULL,
  `About_You` longtext,
  `About_You_More` longtext,
  `Image_Name` varchar(50) DEFAULT NULL,
  `zoneId` int(11) DEFAULT NULL,
  `Rating` int(2) NOT NULL DEFAULT '0',
  `Min_Balance` varchar(12) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL,
  `GPA` varchar(255) DEFAULT NULL,
  `GPAScale` varchar(255) DEFAULT NULL,
  `Street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Tutor_Profile_Id`),
  KEY `FK_tutor_profile_detail_user` (`User_Id`),
  KEY `FK_tutor_profile_detail_country_master` (`Country_Id`),
  KEY `FK888CCFD0AFC4C79` (`Time_Zone_Id`),
  KEY `FK888CCFD0441D6D95` (`zoneId`),
  CONSTRAINT `FK888CCFD0441D6D95` FOREIGN KEY (`zoneId`) REFERENCES `zone` (`zone_id`),
  CONSTRAINT `FK_tutor_profile_detail_country_master` FOREIGN KEY (`Country_Id`) REFERENCES `country_master` (`Country_Id`),
  CONSTRAINT `FK_tutor_profile_detail_user` FOREIGN KEY (`User_Id`) REFERENCES `user` (`User_Id`),
  CONSTRAINT `FK_tutor_profile_detail_zone` FOREIGN KEY (`Time_Zone_Id`) REFERENCES `zone` (`zone_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.tutor_profile_detail: ~17 rows (approximately)
/*!40000 ALTER TABLE `tutor_profile_detail` DISABLE KEYS */;
INSERT INTO `tutor_profile_detail` (`Tutor_Profile_Id`, `User_Id`, `First_Name`, `Last_Name`, `Mobile_Number`, `Country_Id`, `Tax_Id`, `Image`, `College`, `Career`, `Graduation_Date`, `Time_Zone_Id`, `About_You`, `About_You_More`, `Image_Name`, `zoneId`, `Rating`, `Min_Balance`, `City`, `GPA`, `GPAScale`, `Street`) VALUES
	(2, 8, 'tutor', 'smith', '1234567890', 44, '123456', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\8\\fileupload\\8Koala.jpg', 'gmccc', 'masters', '2015-04-01 00:00:00', 191, 'sdadad', 'ddfgd fdg dfgdf dfgdf g', 'Koala.jpg', NULL, 0, NULL, NULL, NULL, NULL, NULL),
	(3, 10, 'Raman', 'Singh', '8796546321', 12, '123', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\10\\fileupload\\10', 'PU', 'Software Developer', '2005-04-01 00:00:00', 20, '', '', '', NULL, 0, NULL, NULL, NULL, NULL, NULL),
	(4, 18, 'Raman', 'Tutor', '78464564456484', 12, '123', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\18\\fileupload\\18default_user_icon_128_8.png', 'PU', 'Software Developer', '2005-04-01 00:00:00', NULL, '', '', 'default_user_icon_128_8.png', 19, 0, NULL, NULL, NULL, NULL, NULL),
	(5, 19, 'Raman', 'Singh', '7879411231312', 33, '789', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\19\\fileupload\\19photo3.png', 'PU', 'Software Developer', '2005-04-01 00:00:00', NULL, '', '', 'photo3.png', 191, 0, NULL, NULL, NULL, NULL, NULL),
	(6, 22, 'Tarun', 'Gupta', '1111111111', 169, '11111', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\22\\fileupload\\22', 'sdf', 'sdf', '2015-04-01 00:00:00', NULL, '', '', '', 300, 0, NULL, NULL, NULL, NULL, NULL),
	(7, 23, 'dfg', 'dfg', '1111111111', 167, '1111', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\23\\fileupload\\23Hydrangeas.jpg', 'dfg', 'dfg', '2015-04-01 00:00:00', NULL, 'dfg', 'dfg', 'Hydrangeas.jpg', 281, 0, NULL, NULL, NULL, NULL, NULL),
	(8, 32, 'raman tutor k', 'si', '1111111111', 157, '1111', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\32\\fileupload\\32', 'asd', 'asda', '2015-04-01 00:00:00', NULL, '', '', '', 272, 0, NULL, NULL, NULL, NULL, NULL),
	(9, 38, 'Ram', 'singh', '656465456465', 12, '2234234', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\38\\fileupload\\38default_user_icon_128_8.png', 'khjk', 'jkhjk', '2015-04-01 00:00:00', NULL, '', '', 'default_user_icon_128_8.png', 19, 0, NULL, NULL, NULL, NULL, NULL),
	(10, 41, 'Raman', 'chili', '5456456464', 44, '789', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\41\\fileupload\\41images.jpg', 'pu', 'asdasdas', '2005-04-01 00:00:00', NULL, '', '', 'images.jpg', 191, 0, NULL, NULL, NULL, NULL, NULL),
	(11, 43, 'dsfsdf', 'sdfsdf', '123131313123', 44, '23423423', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\43\\fileupload\\43', 'sdfs', 'dfsdfsdf', '2015-05-01 00:00:00', NULL, '', '', '', 119, 0, NULL, NULL, NULL, NULL, NULL),
	(12, 46, 'Tut', 'tut', '+5666131321321', 99, '345345', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\46\\fileupload\\46bcm-tutor-img2.jpg', 'MMU Mullana', 'software developer', '2015-05-01 00:00:00', NULL, 'there riods  skdfs kfs fsdf skf sfsfjslfj ssfsjf ss f skjf sf s f   sjfsfsf ', 'erter', 'bcm-tutor-img2.jpg', 191, 2, '60.0', NULL, NULL, NULL, NULL),
	(13, 53, 'partutu', 'param', '1111111111', 33, '11111', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\53\\fileupload\\53Penguins.jpg', 'sdfdsf', 'sdfsdf', '2015-05-01 00:00:00', NULL, 'sdfsdf', 'sdfsfdsfds', 'Penguins.jpg', 191, 2, NULL, NULL, NULL, NULL, NULL),
	(14, 62, 'zx', 'czxczxc', '1111111111', 167, '1111', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\62\\fileupload\\62', 'zxczxc', 'zxczx', '2015-05-01 00:00:00', NULL, 'zxczx', 'czxczxc', '', 281, 0, NULL, NULL, NULL, NULL, NULL),
	(15, 68, 'tutRandom', 'sdfsdf', '1111111111', 89, 'sdfs', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\68\\fileupload\\68AloProfe beta.png', 'sdfs', 'sdf', '2015-06-01 00:00:00', NULL, 'dfsdf', 'sdfsdf', 'AloProfe beta.png', 175, 0, NULL, NULL, NULL, NULL, NULL),
	(16, 69, 'Andrew', ' Smith', '1111111111', 157, 'sdfsdf', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\69\\fileupload\\69', 'fghf', 'fghfgh', '2015-06-01 00:00:00', NULL, 'dfdsf', 'sdfsdf', '', 272, 0, NULL, NULL, NULL, NULL, NULL),
	(17, 70, 'werw', 'erwerw', '1111111111', 170, '32423', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\70\\fileupload\\70', 'erwer', 'wewe', '2015-06-01 00:00:00', NULL, 'werw', 'rwerwr', '', 282, 0, NULL, NULL, NULL, NULL, NULL),
	(18, 71, 'smith', 'dfgdg', '1111111111', 28, '11111', 'D:\\New-Divorce-\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\miprofe\\71\\fileupload\\71', 'fdgdf', 'dfg', '2015-06-01 00:00:00', NULL, 'df', 'g', '', 61, 4, '-15.0', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `tutor_profile_detail` ENABLE KEYS */;


-- Dumping structure for table miprofe.tutor_rating
CREATE TABLE IF NOT EXISTS `tutor_rating` (
  `tutor_Rating_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Comments` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `Explain_Rating` int(11) DEFAULT NULL,
  `General_Rating` int(11) DEFAULT NULL,
  `Knowledge_Rating` int(11) DEFAULT NULL,
  `Quality_Rating` int(11) DEFAULT NULL,
  `Recomended` varchar(255) DEFAULT NULL,
  `Tutor_Profile_Id` int(11) DEFAULT NULL,
  `Student_User_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`tutor_Rating_Id`),
  KEY `FK2FC3AEC68F88EBF8` (`Student_User_Id`),
  KEY `FK2FC3AEC684823880` (`Tutor_Profile_Id`),
  CONSTRAINT `FK2FC3AEC684823880` FOREIGN KEY (`Tutor_Profile_Id`) REFERENCES `tutor_profile_detail` (`Tutor_Profile_Id`),
  CONSTRAINT `FK2FC3AEC68F88EBF8` FOREIGN KEY (`Student_User_Id`) REFERENCES `user` (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.tutor_rating: ~0 rows (approximately)
/*!40000 ALTER TABLE `tutor_rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutor_rating` ENABLE KEYS */;


-- Dumping structure for table miprofe.tutor_subject_relationship
CREATE TABLE IF NOT EXISTS `tutor_subject_relationship` (
  `Tutor_Subject_Relationship_Id` int(10) NOT NULL AUTO_INCREMENT,
  `User_Id` int(10) DEFAULT '0',
  `Subjects_Id` int(10) DEFAULT '0',
  PRIMARY KEY (`Tutor_Subject_Relationship_Id`),
  KEY `FK_tutor_subject_relationship_user` (`User_Id`),
  KEY `FK_tutor_subject_relationship_subjects` (`Subjects_Id`),
  CONSTRAINT `FK_tutor_subject_relationship_subjects` FOREIGN KEY (`Subjects_Id`) REFERENCES `subjects` (`Subjects_Id`),
  CONSTRAINT `FK_tutor_subject_relationship_user` FOREIGN KEY (`User_Id`) REFERENCES `user` (`User_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.tutor_subject_relationship: ~31 rows (approximately)
/*!40000 ALTER TABLE `tutor_subject_relationship` DISABLE KEYS */;
INSERT INTO `tutor_subject_relationship` (`Tutor_Subject_Relationship_Id`, `User_Id`, `Subjects_Id`) VALUES
	(1, 8, 10),
	(2, 8, 14),
	(3, 8, 7),
	(4, 8, 9),
	(5, 10, 16),
	(6, 18, 10),
	(7, 19, 8),
	(8, 22, 7),
	(9, 23, 13),
	(10, 23, 14),
	(11, 32, 7),
	(12, 38, 10),
	(13, 41, 10),
	(14, 43, 10),
	(17, 53, 10),
	(18, 53, 13),
	(19, 53, 1),
	(20, 53, 7),
	(21, 46, 10),
	(22, 46, 11),
	(23, 46, 14),
	(24, 46, 15),
	(25, 46, 8),
	(26, 46, 9),
	(27, 62, 10),
	(28, 68, 13),
	(29, 68, 14),
	(30, 68, 15),
	(31, 69, 10),
	(32, 70, 13),
	(33, 71, 4);
/*!40000 ALTER TABLE `tutor_subject_relationship` ENABLE KEYS */;


-- Dumping structure for table miprofe.tutor_working_countries
CREATE TABLE IF NOT EXISTS `tutor_working_countries` (
  `Tutor_Working_Country_Id` int(10) NOT NULL AUTO_INCREMENT,
  `Tutor_Fee_Per_Country_Id` int(10) NOT NULL,
  `Tutor_Profile_Id` int(10) NOT NULL,
  `Availability_Status` varchar(5) NOT NULL,
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Tutor_Working_Country_Id`),
  KEY `FK__tutor_fee_per_country` (`Tutor_Fee_Per_Country_Id`),
  KEY `FK_tutor_working_countries_tutor_profile_detail` (`Tutor_Profile_Id`),
  CONSTRAINT `FK__tutor_fee_per_country` FOREIGN KEY (`Tutor_Fee_Per_Country_Id`) REFERENCES `tutor_fee_per_country` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tutor_working_countries_tutor_profile_detail` FOREIGN KEY (`Tutor_Profile_Id`) REFERENCES `tutor_profile_detail` (`Tutor_Profile_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.tutor_working_countries: ~0 rows (approximately)
/*!40000 ALTER TABLE `tutor_working_countries` DISABLE KEYS */;
INSERT INTO `tutor_working_countries` (`Tutor_Working_Country_Id`, `Tutor_Fee_Per_Country_Id`, `Tutor_Profile_Id`, `Availability_Status`, `Created_Date`) VALUES
	(1, 1, 18, 'N', '2015-06-10 08:44:11');
/*!40000 ALTER TABLE `tutor_working_countries` ENABLE KEYS */;


-- Dumping structure for table miprofe.user
CREATE TABLE IF NOT EXISTS `user` (
  `User_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Role_Id` int(11) DEFAULT NULL,
  `Username` varchar(100) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Is_Deleted` varchar(2) NOT NULL DEFAULT 'N',
  `Is_Verified` varchar(2) NOT NULL DEFAULT 'Y',
  `Scribblar_Username` varchar(100) DEFAULT NULL,
  `scribblarId` varchar(255) DEFAULT NULL,
  `Firebase_Username` varchar(255) DEFAULT NULL,
  `Firebase_Password` varchar(100) DEFAULT NULL,
  `sessionFlag` varchar(10) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`User_Id`),
  KEY `FK__roles` (`Role_Id`),
  CONSTRAINT `FK__roles` FOREIGN KEY (`Role_Id`) REFERENCES `roles` (`Role_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- Dumping data for table miprofe.user: ~70 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`User_Id`, `Role_Id`, `Username`, `Password`, `Created_Date`, `Is_Deleted`, `Is_Verified`, `Scribblar_Username`, `scribblarId`, `Firebase_Username`, `Firebase_Password`, `sessionFlag`) VALUES
	(2, 5, 'admin@admin.com', '11111111', '2015-03-03 04:43:20', 'N', 'Y', 'scribblar_TU', '934F016D-D900-BCB7-25E75E4A637FD40B', NULL, NULL, 'N'),
	(3, 1, 'raman@mp.com', '11111111', '2015-03-31 04:45:02', 'N', 'N', NULL, NULL, NULL, NULL, 'N'),
	(4, 2, 'raman@mp.com', '11111111', '2015-03-31 05:10:07', 'Y', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(5, 1, 'newtut@test.com', '11111111', '2015-03-31 05:17:04', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(6, 2, 'student@chk.com', '11111111', '2015-03-31 05:38:48', 'N', 'Y', 'scribblar_student', '934F016D-D900-BCB7-25E75E4A637FD40B', NULL, NULL, 'N'),
	(7, 2, 'aarya@seasiainfotech.com', '11111111', '2015-04-01 06:15:52', 'N', 'Y', 'scribblar_sdfsd', '8DC5C701-C128-89B3-A433BDA7D3301339', NULL, NULL, 'N'),
	(8, 3, 'tutor@chk.com', '11111111', '2015-04-03 04:45:46', 'Y', 'Y', 'scribblar_tutor', '8DC59F6C-AC7B-6A2A-9B6996E91B949F24', NULL, NULL, 'N'),
	(9, 2, 'adarsh@addy.com', '11111111', '2015-04-23 09:01:46', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(10, 3, 'raman@test.com', '123456789', '2015-04-23 09:03:01', 'Y', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(11, 2, 'sss@s1.com', '11111111', '2015-04-23 11:02:11', 'N', 'N', NULL, NULL, NULL, NULL, 'N'),
	(12, 2, 'asdasd@sdsd.com', '11111111', '2015-04-23 11:07:47', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(13, 2, 'yeha@stu.com', '11111111', '2015-04-23 11:23:39', 'Y', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(14, 2, 'stu@check.com', '11111111', '2015-04-23 11:46:45', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(15, 2, 'addy@aryan.com', '11111111', '2015-04-24 04:31:00', 'Y', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(16, 2, 'student@tst.com', '11111111', '2015-04-24 05:01:41', 'Y', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(17, 2, 'tarun@tst.com', '11111111', '2015-04-24 05:05:03', 'N', 'Y', 'scribblar_tarun', '223F249E-FB3D-58EE-E0121A4CA08BD27B', NULL, NULL, 'N'),
	(18, 3, 'rsingh4@seasiainfotech.com', '123456789', '2015-04-24 05:05:35', 'Y', 'Y', 'scribblar_Raman', '223EFA2D-AD21-721F-4573A0571E5D5BCF', NULL, NULL, 'N'),
	(19, 3, 'raman@yah.com', '123456789', '2015-04-24 05:50:47', 'Y', 'Y', '', '', NULL, NULL, 'N'),
	(20, 2, 'new@student.com', '11111111', '2015-04-24 05:51:01', 'N', 'Y', '', '', NULL, NULL, 'N'),
	(21, 2, 'tarun@stu.com', '11111111', '2015-04-25 07:39:24', 'N', 'Y', '', '', NULL, NULL, 'N'),
	(22, 3, 'new@tutor.com', '11111111', '2015-04-27 04:37:30', 'Y', 'Y', 'scribblar_dfsf', 'BEB4EAB2-D832-B4FB-2D353A2DBB632F1B', NULL, NULL, 'N'),
	(23, 3, 'tut@img.com', '11111111', '2015-04-27 09:48:16', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(24, 1, 'parent@tst.com', '11111111', '2015-04-28 04:05:24', 'N', 'N', NULL, NULL, NULL, NULL, 'N'),
	(25, 1, 'parent@ptst.com', '11111111', '2015-04-28 04:07:34', 'N', 'N', NULL, NULL, NULL, NULL, 'N'),
	(26, 1, 'parent@chktst.com', '11111111', '2015-04-28 04:09:56', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(27, 1, 'tst@par.com', '11111111', '2015-04-28 04:12:08', 'N', 'N', NULL, NULL, NULL, NULL, 'N'),
	(28, 1, 'parent@chk1.com', '11111111', '2015-04-28 04:16:04', 'Y', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(29, 1, 'par@par.com', '11111111', '2015-04-28 04:22:04', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(30, 1, 'testing@par.com', '11111111', '2015-04-28 04:24:17', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(31, 2, 'student1@test.com', '11111111', '2015-04-28 05:10:22', 'N', 'Y', 'scribblar_student null', 'F06C187A-D621-86EA-3DC9DACA5BB96390', NULL, NULL, 'N'),
	(32, 3, '28@tut.com', '11111111', '2015-04-28 05:11:28', 'Y', 'N', 'scribblar_raman tutor india', 'F06BEE0F-D908-79B4-5C18BFCE6C858D83', NULL, NULL, 'N'),
	(33, 1, 'parent1@hhh.com', '11111111', '2015-04-28 09:11:25', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(34, 1, 'parent2@hhh.com', '11111111', '2015-04-28 09:12:39', 'Y', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(35, 2, 'stu@ind1.com', '11111111', '2015-04-28 09:58:55', 'N', 'Y', 'scribblar_sfds', 'FAB49F80-DE40-023A-2ACBA63C4CAEFF7C', NULL, NULL, 'N'),
	(36, 1, 'par@ttt.com', '11111111', '2015-04-28 10:27:45', 'N', 'N', NULL, NULL, NULL, NULL, 'N'),
	(37, 1, 'par@tt.com', '11111111', '2015-04-28 10:33:32', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(38, 3, '29@tut.com', '12345678', '2015-04-28 12:37:14', 'Y', 'N', 'scribblar_Ram', '006F39E5-B1AC-AD92-0CCA596B070C673A', NULL, NULL, 'N'),
	(39, 2, '29@stu.com', '12345678', '2015-04-28 12:37:51', 'N', 'Y', 'scribblar_TU', '006F63F2-E2B8-5DB1-8FADB9BE4D7A9A7D', NULL, NULL, 'N'),
	(40, 2, 'stu@chille.com', '12345678', '2015-04-29 05:33:02', 'N', 'Y', 'chille', '24B80BB0-90AD-941F-C8D66C3383197F47', NULL, NULL, 'N'),
	(41, 3, 'stu@chile.com', '123456789', '2015-04-29 05:34:19', 'N', 'Y', '', '', NULL, NULL, 'N'),
	(42, 2, 'student@chile1.com', '11111111', '2015-05-07 05:53:23', 'N', 'Y', 'dsddddddddddd', 'C465BE2C-BAD1-978A-4686474977C2C338', NULL, NULL, 'N'),
	(43, 3, 'tut@null.com', '11111111', '2015-05-07 06:11:33', 'Y', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(44, 2, 'student1@mi.com', '11111111', '2015-05-07 08:17:24', 'N', 'Y', 'stu', '67000175-01BB-50AD-1D950D86919FFE1A', NULL, NULL, 'N'),
	(45, 2, 'stu@mi.com', '11111111', '2015-05-07 08:20:04', 'N', 'Y', 'we', 'C6B10CD2-C206-C1B5-6DE6B35BACB83747', NULL, NULL, 'N'),
	(46, 3, 'tut@mi.com', '11111111', '2015-05-07 08:21:20', 'N', 'Y', 'Tut', 'C6B0E3E9-D66C-A0B2-4EB3C9E2C06EE10B', NULL, NULL, 'N'),
	(47, 2, 'sdf@df.sdf', '11111111', '2015-05-08 05:14:56', 'N', 'N', NULL, NULL, NULL, NULL, 'N'),
	(48, 2, 'new@stu2.com', '11111111', '2015-05-15 06:47:06', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(49, 1, 'par@stu2.com', '11111111', '2015-05-15 06:49:26', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(50, 4, 'aarya1@seasiainfotech.com', '11111111', '2015-05-18 11:55:56', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(51, 2, 'stu@m3.com', '11111111', '2015-05-20 06:16:19', 'N', 'Y', 'stuM', '6530489D-9FDC-FF96-9ABE7D32FCD2E110', NULL, NULL, 'N'),
	(52, 1, 'tut@m3.com', '11111111', '2015-05-20 06:17:33', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(53, 3, 'tut@m3.com', '11111111', '2015-05-20 06:18:58', 'N', 'Y', 'partutu', '652FFD16-F76E-CC9A-29539757A669D1D8', NULL, NULL, 'N'),
	(54, 2, 'stu@m4.com', '11111111', '2015-05-21 09:26:49', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(55, 2, 'stustu@st.com', '111111111', '2015-05-22 06:49:52', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(56, 2, 'lkj@asd.com', '11111111', '2015-05-22 08:36:10', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(57, 2, 'gfhgfhf@fdgfdg.com', '11111111', '2015-05-22 09:07:59', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(58, 2, 'sbhfjksf@sdhsd.com', '11111111', '2015-05-22 09:22:01', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(59, 2, 'fgfdg@qwerewq.com', '11111111', '2015-05-22 09:23:04', 'N', 'Y', NULL, NULL, NULL, NULL, 'Y'),
	(60, 2, 'retet@sdfsf.com', 'aaaaaaaa', '2015-05-22 11:29:00', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(61, 2, 'dsfdfssd@sdsd.com', '11111111', '2015-05-22 11:36:20', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(62, 3, 'aaa@aaa.com', '11111111', '2015-05-28 12:23:15', 'N', 'Y', NULL, NULL, NULL, NULL, 'Y'),
	(63, 2, 'fffff@asd.dsa', '11111111', '2015-06-03 11:52:35', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(64, 2, 'testFIREBASE@dem.com', '11111111', '2015-06-04 04:50:45', 'N', 'Y', NULL, NULL, NULL, NULL, 'N'),
	(65, 2, 'testFire@dmm.com', '11111111', '2015-06-04 05:17:12', 'N', 'Y', NULL, NULL, 'MpChat-testFire@dmm.com', 'g6c9f2llc9pg', 'N'),
	(66, 2, 'final@fire.com', '11111111', '2015-06-04 05:26:27', 'N', 'Y', NULL, NULL, 'MpChat-fireUSer', '7vq1efnqsh79', 'N'),
	(67, 2, 'random@user.com', '11111111', '2015-06-04 05:36:48', 'N', 'Y', '', '', 'random@user.com', 'cn8ac2u430n1', 'N'),
	(68, 3, 'random@tut.com', '11111111', '2015-06-04 06:12:55', 'N', 'N', NULL, NULL, NULL, NULL, 'N'),
	(69, 3, 'tut@base.com', '11111111', '2015-06-04 06:37:02', 'N', 'Y', NULL, NULL, 'tut@base.com', 'si58sqb8add8', 'N'),
	(70, 3, 'tttt@sadasd.com', '11111111', '2015-06-04 06:44:55', 'N', 'N', NULL, NULL, 'tttt@sadasd.com', 'bkuekc0iappm', 'N'),
	(71, 3, 'kkk@ttt.com', '11111111', '2015-06-04 06:46:46', 'N', 'Y', 'smith', '64BC953C-F0A3-78F8-8BC1A29D2FBDF8A6', 'kkk@ttt.com', 'gs92m0382j37', 'N');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table miprofe.zone
CREATE TABLE IF NOT EXISTS `zone` (
  `zone_id` int(10) NOT NULL AUTO_INCREMENT,
  `country_code` char(2) COLLATE utf8_bin NOT NULL,
  `zone_name` varchar(35) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`zone_id`),
  KEY `idx_zone_name` (`zone_name`)
) ENGINE=InnoDB AUTO_INCREMENT=417 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table miprofe.zone: ~416 rows (approximately)
/*!40000 ALTER TABLE `zone` DISABLE KEYS */;
INSERT INTO `zone` (`zone_id`, `country_code`, `zone_name`) VALUES
	(1, 'AD', 'Europe/Andorra'),
	(2, 'AE', 'Asia/Dubai'),
	(3, 'AF', 'Asia/Kabul'),
	(4, 'AG', 'America/Antigua'),
	(5, 'AI', 'America/Anguilla'),
	(6, 'AL', 'Europe/Tirane'),
	(7, 'AM', 'Asia/Yerevan'),
	(8, 'AO', 'Africa/Luanda'),
	(9, 'AQ', 'Antarctica/McMurdo'),
	(10, 'AQ', 'Antarctica/Rothera'),
	(11, 'AQ', 'Antarctica/Palmer'),
	(12, 'AQ', 'Antarctica/Mawson'),
	(13, 'AQ', 'Antarctica/Davis'),
	(14, 'AQ', 'Antarctica/Casey'),
	(15, 'AQ', 'Antarctica/Vostok'),
	(16, 'AQ', 'Antarctica/DumontDUrville'),
	(17, 'AQ', 'Antarctica/Syowa'),
	(18, 'AQ', 'Antarctica/Troll'),
	(19, 'AR', 'America/Argentina/Buenos_Aires'),
	(20, 'AR', 'America/Argentina/Cordoba'),
	(21, 'AR', 'America/Argentina/Salta'),
	(22, 'AR', 'America/Argentina/Jujuy'),
	(23, 'AR', 'America/Argentina/Tucuman'),
	(24, 'AR', 'America/Argentina/Catamarca'),
	(25, 'AR', 'America/Argentina/La_Rioja'),
	(26, 'AR', 'America/Argentina/San_Juan'),
	(27, 'AR', 'America/Argentina/Mendoza'),
	(28, 'AR', 'America/Argentina/San_Luis'),
	(29, 'AR', 'America/Argentina/Rio_Gallegos'),
	(30, 'AR', 'America/Argentina/Ushuaia'),
	(31, 'AS', 'Pacific/Pago_Pago'),
	(32, 'AT', 'Europe/Vienna'),
	(33, 'AU', 'Australia/Lord_Howe'),
	(34, 'AU', 'Antarctica/Macquarie'),
	(35, 'AU', 'Australia/Hobart'),
	(36, 'AU', 'Australia/Currie'),
	(37, 'AU', 'Australia/Melbourne'),
	(38, 'AU', 'Australia/Sydney'),
	(39, 'AU', 'Australia/Broken_Hill'),
	(40, 'AU', 'Australia/Brisbane'),
	(41, 'AU', 'Australia/Lindeman'),
	(42, 'AU', 'Australia/Adelaide'),
	(43, 'AU', 'Australia/Darwin'),
	(44, 'AU', 'Australia/Perth'),
	(45, 'AU', 'Australia/Eucla'),
	(46, 'AW', 'America/Aruba'),
	(47, 'AX', 'Europe/Mariehamn'),
	(48, 'AZ', 'Asia/Baku'),
	(49, 'BA', 'Europe/Sarajevo'),
	(50, 'BB', 'America/Barbados'),
	(51, 'BD', 'Asia/Dhaka'),
	(52, 'BE', 'Europe/Brussels'),
	(53, 'BF', 'Africa/Ouagadougou'),
	(54, 'BG', 'Europe/Sofia'),
	(55, 'BH', 'Asia/Bahrain'),
	(56, 'BI', 'Africa/Bujumbura'),
	(57, 'BJ', 'Africa/Porto-Novo'),
	(58, 'BL', 'America/St_Barthelemy'),
	(59, 'BM', 'Atlantic/Bermuda'),
	(60, 'BN', 'Asia/Brunei'),
	(61, 'BO', 'America/La_Paz'),
	(62, 'BQ', 'America/Kralendijk'),
	(63, 'BR', 'America/Noronha'),
	(64, 'BR', 'America/Belem'),
	(65, 'BR', 'America/Fortaleza'),
	(66, 'BR', 'America/Recife'),
	(67, 'BR', 'America/Araguaina'),
	(68, 'BR', 'America/Maceio'),
	(69, 'BR', 'America/Bahia'),
	(70, 'BR', 'America/Sao_Paulo'),
	(71, 'BR', 'America/Campo_Grande'),
	(72, 'BR', 'America/Cuiaba'),
	(73, 'BR', 'America/Santarem'),
	(74, 'BR', 'America/Porto_Velho'),
	(75, 'BR', 'America/Boa_Vista'),
	(76, 'BR', 'America/Manaus'),
	(77, 'BR', 'America/Eirunepe'),
	(78, 'BR', 'America/Rio_Branco'),
	(79, 'BS', 'America/Nassau'),
	(80, 'BT', 'Asia/Thimphu'),
	(81, 'BW', 'Africa/Gaborone'),
	(82, 'BY', 'Europe/Minsk'),
	(83, 'BZ', 'America/Belize'),
	(84, 'CA', 'America/St_Johns'),
	(85, 'CA', 'America/Halifax'),
	(86, 'CA', 'America/Glace_Bay'),
	(87, 'CA', 'America/Moncton'),
	(88, 'CA', 'America/Goose_Bay'),
	(89, 'CA', 'America/Blanc-Sablon'),
	(90, 'CA', 'America/Toronto'),
	(91, 'CA', 'America/Nipigon'),
	(92, 'CA', 'America/Thunder_Bay'),
	(93, 'CA', 'America/Iqaluit'),
	(94, 'CA', 'America/Pangnirtung'),
	(95, 'CA', 'America/Resolute'),
	(96, 'CA', 'America/Atikokan'),
	(97, 'CA', 'America/Rankin_Inlet'),
	(98, 'CA', 'America/Winnipeg'),
	(99, 'CA', 'America/Rainy_River'),
	(100, 'CA', 'America/Regina'),
	(101, 'CA', 'America/Swift_Current'),
	(102, 'CA', 'America/Edmonton'),
	(103, 'CA', 'America/Cambridge_Bay'),
	(104, 'CA', 'America/Yellowknife'),
	(105, 'CA', 'America/Inuvik'),
	(106, 'CA', 'America/Creston'),
	(107, 'CA', 'America/Dawson_Creek'),
	(108, 'CA', 'America/Vancouver'),
	(109, 'CA', 'America/Whitehorse'),
	(110, 'CA', 'America/Dawson'),
	(111, 'CC', 'Indian/Cocos'),
	(112, 'CD', 'Africa/Kinshasa'),
	(113, 'CD', 'Africa/Lubumbashi'),
	(114, 'CF', 'Africa/Bangui'),
	(115, 'CG', 'Africa/Brazzaville'),
	(116, 'CH', 'Europe/Zurich'),
	(117, 'CI', 'Africa/Abidjan'),
	(118, 'CK', 'Pacific/Rarotonga'),
	(119, 'CL', 'America/Santiago'),
	(120, 'CL', 'Pacific/Easter'),
	(121, 'CM', 'Africa/Douala'),
	(122, 'CN', 'Asia/Shanghai'),
	(123, 'CN', 'Asia/Urumqi'),
	(124, 'CO', 'America/Bogota'),
	(125, 'CR', 'America/Costa_Rica'),
	(126, 'CU', 'America/Havana'),
	(127, 'CV', 'Atlantic/Cape_Verde'),
	(128, 'CW', 'America/Curacao'),
	(129, 'CX', 'Indian/Christmas'),
	(130, 'CY', 'Asia/Nicosia'),
	(131, 'CZ', 'Europe/Prague'),
	(132, 'DE', 'Europe/Berlin'),
	(133, 'DE', 'Europe/Busingen'),
	(134, 'DJ', 'Africa/Djibouti'),
	(135, 'DK', 'Europe/Copenhagen'),
	(136, 'DM', 'America/Dominica'),
	(137, 'DO', 'America/Santo_Domingo'),
	(138, 'DZ', 'Africa/Algiers'),
	(139, 'EC', 'America/Guayaquil'),
	(140, 'EC', 'Pacific/Galapagos'),
	(141, 'EE', 'Europe/Tallinn'),
	(142, 'EG', 'Africa/Cairo'),
	(143, 'EH', 'Africa/El_Aaiun'),
	(144, 'ER', 'Africa/Asmara'),
	(145, 'ES', 'Europe/Madrid'),
	(146, 'ES', 'Africa/Ceuta'),
	(147, 'ES', 'Atlantic/Canary'),
	(148, 'ET', 'Africa/Addis_Ababa'),
	(149, 'FI', 'Europe/Helsinki'),
	(150, 'FJ', 'Pacific/Fiji'),
	(151, 'FK', 'Atlantic/Stanley'),
	(152, 'FM', 'Pacific/Chuuk'),
	(153, 'FM', 'Pacific/Pohnpei'),
	(154, 'FM', 'Pacific/Kosrae'),
	(155, 'FO', 'Atlantic/Faroe'),
	(156, 'FR', 'Europe/Paris'),
	(157, 'GA', 'Africa/Libreville'),
	(158, 'GB', 'Europe/London'),
	(159, 'GD', 'America/Grenada'),
	(160, 'GE', 'Asia/Tbilisi'),
	(161, 'GF', 'America/Cayenne'),
	(162, 'GG', 'Europe/Guernsey'),
	(163, 'GH', 'Africa/Accra'),
	(164, 'GI', 'Europe/Gibraltar'),
	(165, 'GL', 'America/Godthab'),
	(166, 'GL', 'America/Danmarkshavn'),
	(167, 'GL', 'America/Scoresbysund'),
	(168, 'GL', 'America/Thule'),
	(169, 'GM', 'Africa/Banjul'),
	(170, 'GN', 'Africa/Conakry'),
	(171, 'GP', 'America/Guadeloupe'),
	(172, 'GQ', 'Africa/Malabo'),
	(173, 'GR', 'Europe/Athens'),
	(174, 'GS', 'Atlantic/South_Georgia'),
	(175, 'GT', 'America/Guatemala'),
	(176, 'GU', 'Pacific/Guam'),
	(177, 'GW', 'Africa/Bissau'),
	(178, 'GY', 'America/Guyana'),
	(179, 'HK', 'Asia/Hong_Kong'),
	(180, 'HN', 'America/Tegucigalpa'),
	(181, 'HR', 'Europe/Zagreb'),
	(182, 'HT', 'America/Port-au-Prince'),
	(183, 'HU', 'Europe/Budapest'),
	(184, 'ID', 'Asia/Jakarta'),
	(185, 'ID', 'Asia/Pontianak'),
	(186, 'ID', 'Asia/Makassar'),
	(187, 'ID', 'Asia/Jayapura'),
	(188, 'IE', 'Europe/Dublin'),
	(189, 'IL', 'Asia/Jerusalem'),
	(190, 'IM', 'Europe/Isle_of_Man'),
	(191, 'IN', 'Asia/Kolkata'),
	(192, 'IO', 'Indian/Chagos'),
	(193, 'IQ', 'Asia/Baghdad'),
	(194, 'IR', 'Asia/Tehran'),
	(195, 'IS', 'Atlantic/Reykjavik'),
	(196, 'IT', 'Europe/Rome'),
	(197, 'JE', 'Europe/Jersey'),
	(198, 'JM', 'America/Jamaica'),
	(199, 'JO', 'Asia/Amman'),
	(200, 'JP', 'Asia/Tokyo'),
	(201, 'KE', 'Africa/Nairobi'),
	(202, 'KG', 'Asia/Bishkek'),
	(203, 'KH', 'Asia/Phnom_Penh'),
	(204, 'KI', 'Pacific/Tarawa'),
	(205, 'KI', 'Pacific/Enderbury'),
	(206, 'KI', 'Pacific/Kiritimati'),
	(207, 'KM', 'Indian/Comoro'),
	(208, 'KN', 'America/St_Kitts'),
	(209, 'KP', 'Asia/Pyongyang'),
	(210, 'KR', 'Asia/Seoul'),
	(211, 'KW', 'Asia/Kuwait'),
	(212, 'KY', 'America/Cayman'),
	(213, 'KZ', 'Asia/Almaty'),
	(214, 'KZ', 'Asia/Qyzylorda'),
	(215, 'KZ', 'Asia/Aqtobe'),
	(216, 'KZ', 'Asia/Aqtau'),
	(217, 'KZ', 'Asia/Oral'),
	(218, 'LA', 'Asia/Vientiane'),
	(219, 'LB', 'Asia/Beirut'),
	(220, 'LC', 'America/St_Lucia'),
	(221, 'LI', 'Europe/Vaduz'),
	(222, 'LK', 'Asia/Colombo'),
	(223, 'LR', 'Africa/Monrovia'),
	(224, 'LS', 'Africa/Maseru'),
	(225, 'LT', 'Europe/Vilnius'),
	(226, 'LU', 'Europe/Luxembourg'),
	(227, 'LV', 'Europe/Riga'),
	(228, 'LY', 'Africa/Tripoli'),
	(229, 'MA', 'Africa/Casablanca'),
	(230, 'MC', 'Europe/Monaco'),
	(231, 'MD', 'Europe/Chisinau'),
	(232, 'ME', 'Europe/Podgorica'),
	(233, 'MF', 'America/Marigot'),
	(234, 'MG', 'Indian/Antananarivo'),
	(235, 'MH', 'Pacific/Majuro'),
	(236, 'MH', 'Pacific/Kwajalein'),
	(237, 'MK', 'Europe/Skopje'),
	(238, 'ML', 'Africa/Bamako'),
	(239, 'MM', 'Asia/Rangoon'),
	(240, 'MN', 'Asia/Ulaanbaatar'),
	(241, 'MN', 'Asia/Hovd'),
	(242, 'MN', 'Asia/Choibalsan'),
	(243, 'MO', 'Asia/Macau'),
	(244, 'MP', 'Pacific/Saipan'),
	(245, 'MQ', 'America/Martinique'),
	(246, 'MR', 'Africa/Nouakchott'),
	(247, 'MS', 'America/Montserrat'),
	(248, 'MT', 'Europe/Malta'),
	(249, 'MU', 'Indian/Mauritius'),
	(250, 'MV', 'Indian/Maldives'),
	(251, 'MW', 'Africa/Blantyre'),
	(252, 'MX', 'America/Mexico_City'),
	(253, 'MX', 'America/Cancun'),
	(254, 'MX', 'America/Merida'),
	(255, 'MX', 'America/Monterrey'),
	(256, 'MX', 'America/Matamoros'),
	(257, 'MX', 'America/Mazatlan'),
	(258, 'MX', 'America/Chihuahua'),
	(259, 'MX', 'America/Ojinaga'),
	(260, 'MX', 'America/Hermosillo'),
	(261, 'MX', 'America/Tijuana'),
	(262, 'MX', 'America/Santa_Isabel'),
	(263, 'MX', 'America/Bahia_Banderas'),
	(264, 'MY', 'Asia/Kuala_Lumpur'),
	(265, 'MY', 'Asia/Kuching'),
	(266, 'MZ', 'Africa/Maputo'),
	(267, 'NA', 'Africa/Windhoek'),
	(268, 'NC', 'Pacific/Noumea'),
	(269, 'NE', 'Africa/Niamey'),
	(270, 'NF', 'Pacific/Norfolk'),
	(271, 'NG', 'Africa/Lagos'),
	(272, 'NI', 'America/Managua'),
	(273, 'NL', 'Europe/Amsterdam'),
	(274, 'NO', 'Europe/Oslo'),
	(275, 'NP', 'Asia/Kathmandu'),
	(276, 'NR', 'Pacific/Nauru'),
	(277, 'NU', 'Pacific/Niue'),
	(278, 'NZ', 'Pacific/Auckland'),
	(279, 'NZ', 'Pacific/Chatham'),
	(280, 'OM', 'Asia/Muscat'),
	(281, 'PA', 'America/Panama'),
	(282, 'PE', 'America/Lima'),
	(283, 'PF', 'Pacific/Tahiti'),
	(284, 'PF', 'Pacific/Marquesas'),
	(285, 'PF', 'Pacific/Gambier'),
	(286, 'PG', 'Pacific/Port_Moresby'),
	(287, 'PG', 'Pacific/Bougainville'),
	(288, 'PH', 'Asia/Manila'),
	(289, 'PK', 'Asia/Karachi'),
	(290, 'PL', 'Europe/Warsaw'),
	(291, 'PM', 'America/Miquelon'),
	(292, 'PN', 'Pacific/Pitcairn'),
	(293, 'PR', 'America/Puerto_Rico'),
	(294, 'PS', 'Asia/Gaza'),
	(295, 'PS', 'Asia/Hebron'),
	(296, 'PT', 'Europe/Lisbon'),
	(297, 'PT', 'Atlantic/Madeira'),
	(298, 'PT', 'Atlantic/Azores'),
	(299, 'PW', 'Pacific/Palau'),
	(300, 'PY', 'America/Asuncion'),
	(301, 'QA', 'Asia/Qatar'),
	(302, 'RE', 'Indian/Reunion'),
	(303, 'RO', 'Europe/Bucharest'),
	(304, 'RS', 'Europe/Belgrade'),
	(305, 'RU', 'Europe/Kaliningrad'),
	(306, 'RU', 'Europe/Moscow'),
	(307, 'RU', 'Europe/Simferopol'),
	(308, 'RU', 'Europe/Volgograd'),
	(309, 'RU', 'Europe/Samara'),
	(310, 'RU', 'Asia/Yekaterinburg'),
	(311, 'RU', 'Asia/Omsk'),
	(312, 'RU', 'Asia/Novosibirsk'),
	(313, 'RU', 'Asia/Novokuznetsk'),
	(314, 'RU', 'Asia/Krasnoyarsk'),
	(315, 'RU', 'Asia/Irkutsk'),
	(316, 'RU', 'Asia/Chita'),
	(317, 'RU', 'Asia/Yakutsk'),
	(318, 'RU', 'Asia/Khandyga'),
	(319, 'RU', 'Asia/Vladivostok'),
	(320, 'RU', 'Asia/Sakhalin'),
	(321, 'RU', 'Asia/Ust-Nera'),
	(322, 'RU', 'Asia/Magadan'),
	(323, 'RU', 'Asia/Srednekolymsk'),
	(324, 'RU', 'Asia/Kamchatka'),
	(325, 'RU', 'Asia/Anadyr'),
	(326, 'RW', 'Africa/Kigali'),
	(327, 'SA', 'Asia/Riyadh'),
	(328, 'SB', 'Pacific/Guadalcanal'),
	(329, 'SC', 'Indian/Mahe'),
	(330, 'SD', 'Africa/Khartoum'),
	(331, 'SE', 'Europe/Stockholm'),
	(332, 'SG', 'Asia/Singapore'),
	(333, 'SH', 'Atlantic/St_Helena'),
	(334, 'SI', 'Europe/Ljubljana'),
	(335, 'SJ', 'Arctic/Longyearbyen'),
	(336, 'SK', 'Europe/Bratislava'),
	(337, 'SL', 'Africa/Freetown'),
	(338, 'SM', 'Europe/San_Marino'),
	(339, 'SN', 'Africa/Dakar'),
	(340, 'SO', 'Africa/Mogadishu'),
	(341, 'SR', 'America/Paramaribo'),
	(342, 'SS', 'Africa/Juba'),
	(343, 'ST', 'Africa/Sao_Tome'),
	(344, 'SV', 'America/El_Salvador'),
	(345, 'SX', 'America/Lower_Princes'),
	(346, 'SY', 'Asia/Damascus'),
	(347, 'SZ', 'Africa/Mbabane'),
	(348, 'TC', 'America/Grand_Turk'),
	(349, 'TD', 'Africa/Ndjamena'),
	(350, 'TF', 'Indian/Kerguelen'),
	(351, 'TG', 'Africa/Lome'),
	(352, 'TH', 'Asia/Bangkok'),
	(353, 'TJ', 'Asia/Dushanbe'),
	(354, 'TK', 'Pacific/Fakaofo'),
	(355, 'TL', 'Asia/Dili'),
	(356, 'TM', 'Asia/Ashgabat'),
	(357, 'TN', 'Africa/Tunis'),
	(358, 'TO', 'Pacific/Tongatapu'),
	(359, 'TR', 'Europe/Istanbul'),
	(360, 'TT', 'America/Port_of_Spain'),
	(361, 'TV', 'Pacific/Funafuti'),
	(362, 'TW', 'Asia/Taipei'),
	(363, 'TZ', 'Africa/Dar_es_Salaam'),
	(364, 'UA', 'Europe/Kiev'),
	(365, 'UA', 'Europe/Uzhgorod'),
	(366, 'UA', 'Europe/Zaporozhye'),
	(367, 'UG', 'Africa/Kampala'),
	(368, 'UM', 'Pacific/Johnston'),
	(369, 'UM', 'Pacific/Midway'),
	(370, 'UM', 'Pacific/Wake'),
	(371, 'US', 'America/New_York'),
	(372, 'US', 'America/Detroit'),
	(373, 'US', 'America/Kentucky/Louisville'),
	(374, 'US', 'America/Kentucky/Monticello'),
	(375, 'US', 'America/Indiana/Indianapolis'),
	(376, 'US', 'America/Indiana/Vincennes'),
	(377, 'US', 'America/Indiana/Winamac'),
	(378, 'US', 'America/Indiana/Marengo'),
	(379, 'US', 'America/Indiana/Petersburg'),
	(380, 'US', 'America/Indiana/Vevay'),
	(381, 'US', 'America/Chicago'),
	(382, 'US', 'America/Indiana/Tell_City'),
	(383, 'US', 'America/Indiana/Knox'),
	(384, 'US', 'America/Menominee'),
	(385, 'US', 'America/North_Dakota/Center'),
	(386, 'US', 'America/North_Dakota/New_Salem'),
	(387, 'US', 'America/North_Dakota/Beulah'),
	(388, 'US', 'America/Denver'),
	(389, 'US', 'America/Boise'),
	(390, 'US', 'America/Phoenix'),
	(391, 'US', 'America/Los_Angeles'),
	(392, 'US', 'America/Metlakatla'),
	(393, 'US', 'America/Anchorage'),
	(394, 'US', 'America/Juneau'),
	(395, 'US', 'America/Sitka'),
	(396, 'US', 'America/Yakutat'),
	(397, 'US', 'America/Nome'),
	(398, 'US', 'America/Adak'),
	(399, 'US', 'Pacific/Honolulu'),
	(400, 'UY', 'America/Montevideo'),
	(401, 'UZ', 'Asia/Samarkand'),
	(402, 'UZ', 'Asia/Tashkent'),
	(403, 'VA', 'Europe/Vatican'),
	(404, 'VC', 'America/St_Vincent'),
	(405, 'VE', 'America/Caracas'),
	(406, 'VG', 'America/Tortola'),
	(407, 'VI', 'America/St_Thomas'),
	(408, 'VN', 'Asia/Ho_Chi_Minh'),
	(409, 'VU', 'Pacific/Efate'),
	(410, 'WF', 'Pacific/Wallis'),
	(411, 'WS', 'Pacific/Apia'),
	(412, 'YE', 'Asia/Aden'),
	(413, 'YT', 'Indian/Mayotte'),
	(414, 'ZA', 'Africa/Johannesburg'),
	(415, 'ZM', 'Africa/Lusaka'),
	(416, 'ZW', 'Africa/Harare');
/*!40000 ALTER TABLE `zone` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
