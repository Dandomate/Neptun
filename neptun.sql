-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Dec 01. 13:38
-- Kiszolgáló verziója: 10.4.28-MariaDB
-- PHP verzió: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `neptun`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `classroom`
--

CREATE TABLE `classroom` (
  `ID` int(20) NOT NULL,
  `door` varchar(10) NOT NULL,
  `space` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `classroom`
--

INSERT INTO `classroom` (`ID`, `door`, `space`) VALUES
(3, 'D1', 71),
(4, 'D2', 34),
(5, '128', 41),
(7, '123/B', 31),
(8, '123/B', 31),
(10, '021A', 20),
(11, '106C', 30);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `course`
--

CREATE TABLE `course` (
  `ID` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `day` varchar(30) NOT NULL,
  `equipment_ID` int(10) NOT NULL,
  `classroom_ID` int(10) NOT NULL,
  `teacher_ID` int(10) NOT NULL,
  `student_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `course`
--

INSERT INTO `course` (`ID`, `name`, `description`, `day`, `equipment_ID`, `classroom_ID`, `teacher_ID`, `student_ID`) VALUES
(2, 'Angol', '1-6 óráig', 'Hétfő', 7, 4, 1, 5),
(4, 'Program', '8-12 óráig', 'Hétfő', 7, 5, 3, 4),
(5, 'Progtech', '1-3 óráig', 'Hétfő', 7, 5, 3, 4),
(6, 'Angol', '1-3 óráig', 'Kedd', 8, 7, 68, 5),
(8, 'Angol', '12-2 óráig', 'Szerda', 10, 5, 3, 11),
(9, 'Földrajz', '12-2 óráig', 'Csütörtök', 11, 7, 68, 9),
(10, 'Rajz', '12-2 óráig', 'Péntek', 10, 11, 68, 2),
(11, 'Java 2', '12-4 óráig', 'Hétfő', 7, 4, 68, 11),
(12, 'ProgTech2', '3-4 óráig', 'Szerda', 10, 8, 68, 4),
(13, 'Angol', '1-3 óráig', 'Kedd', 8, 7, 68, 8),
(14, 'Angol', '1-3 óráig', 'Kedd', 8, 7, 68, 10);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `department`
--

CREATE TABLE `department` (
  `ID` int(30) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `department`
--

INSERT INTO `department` (`ID`, `name`) VALUES
(1, 'Programtervező informatikus'),
(2, 'Vendéglátás'),
(3, 'Rendszergazda'),
(4, 'Földrajz'),
(12, 'Túrizmus'),
(13, 'Tanárképző');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `equipment`
--

CREATE TABLE `equipment` (
  `ID` int(10) NOT NULL,
  `designation` varchar(30) NOT NULL,
  `quantity` int(10) NOT NULL,
  `description` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `equipment`
--

INSERT INTO `equipment` (`ID`, `designation`, `quantity`, `description`) VALUES
(7, 'Projektor', 1, 'HD'),
(8, 'Vászon', 1, '2mx1m'),
(10, 'Projektor', 1, 'Full HD'),
(11, 'Vászon', 1, '3x3');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `grade`
--

CREATE TABLE `grade` (
  `ID` int(30) NOT NULL,
  `grade` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `grade`
--

INSERT INTO `grade` (`ID`, `grade`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `student`
--

CREATE TABLE `student` (
  `ID` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `birth_date` varchar(11) NOT NULL,
  `neptun_code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `student`
--

INSERT INTO `student` (`ID`, `name`, `birth_date`, `neptun_code`) VALUES
(2, 'Janos Péter', '1973-04-12', 'JETTE1'),
(4, 'Iván Márk', '1999-01-12', 'AYIE0M'),
(5, 'Toth Áron', '2021-11-21', 'UHZTGN'),
(8, 'Kolompár László', '1988-11-30', 'KJUZHN'),
(9, 'Vicc Elek', '1967-06-12', 'AZUIEN'),
(10, 'Puskás Zsolt', '1997-09-15', 'UJHGTZ'),
(11, 'Karo Tihamér', '1999-01-12', 'KUZHJK');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `teacher`
--

CREATE TABLE `teacher` (
  `ID` int(10) NOT NULL,
  `neptun_code` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `department_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `teacher`
--

INSERT INTO `teacher` (`ID`, `neptun_code`, `name`, `department_ID`) VALUES
(1, 'AJUZEM', 'Kacsa Zoli', 3),
(2, 'SDFGHJ', 'Réti Géza', 2),
(3, 'DFGHJK', 'Példa Krisztián', 2),
(43, 'FGHJKL', 'John Doe', 4),
(52, 'XCVBNN', 'Papp János', 2),
(53, 'QWERTZ', 'Papp Pali', 3),
(54, 'UJNGRE', 'Toth Zoltán', 3),
(67, 'JKASEA', 'Molnár Krisztián', 3),
(68, 'ASDFGQ', 'Kardos Máté', 3),
(69, 'ASDFXX', 'Dandóczi Máté', 1);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`ID`);

--
-- A tábla indexei `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `teacher_ID` (`teacher_ID`),
  ADD KEY `equipment_ID` (`equipment_ID`),
  ADD KEY `classroom_ID` (`classroom_ID`),
  ADD KEY `student_ID` (`student_ID`);

--
-- A tábla indexei `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`ID`);

--
-- A tábla indexei `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`ID`);

--
-- A tábla indexei `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`ID`);

--
-- A tábla indexei `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `neptun_code` (`neptun_code`);

--
-- A tábla indexei `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `neptun_code` (`neptun_code`),
  ADD KEY `department_ID` (`department_ID`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `classroom`
--
ALTER TABLE `classroom`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT a táblához `course`
--
ALTER TABLE `course`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT a táblához `department`
--
ALTER TABLE `department`
  MODIFY `ID` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT a táblához `equipment`
--
ALTER TABLE `equipment`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT a táblához `grade`
--
ALTER TABLE `grade`
  MODIFY `ID` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `student`
--
ALTER TABLE `student`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT a táblához `teacher`
--
ALTER TABLE `teacher`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_5` FOREIGN KEY (`teacher_ID`) REFERENCES `teacher` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `course_ibfk_6` FOREIGN KEY (`equipment_ID`) REFERENCES `equipment` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `course_ibfk_7` FOREIGN KEY (`classroom_ID`) REFERENCES `classroom` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `course_ibfk_9` FOREIGN KEY (`student_ID`) REFERENCES `student` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`department_ID`) REFERENCES `department` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
