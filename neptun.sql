-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Nov 23. 02:28
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
(1, '128B', 21),
(3, 'D1', 60),
(4, 'D2', 34),
(5, '128', 21);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `course`
--

CREATE TABLE `course` (
  `ID` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `description` text NOT NULL,
  `date` varchar(30) NOT NULL,
  `equipment_ID` int(10) NOT NULL,
  `classroom_ID` int(10) NOT NULL,
  `grade_ID` int(10) NOT NULL,
  `teacher_ID` int(10) NOT NULL,
  `student_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `course`
--

INSERT INTO `course` (`ID`, `name`, `description`, `date`, `equipment_ID`, `classroom_ID`, `grade_ID`, `teacher_ID`, `student_ID`) VALUES
(1, 'Angol', 'Angol tárgy teljesitéséhez xy kell', '2021.01.12', 1, 5, 2, 3, 1),
(3, 'Operácio Kutatás', 'A tárgy teljesitése 50%', '2021.01.12', 1, 4, 4, 2, 1);

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
(4, 'Földrajz');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `equipment`
--

CREATE TABLE `equipment` (
  `ID` int(30) NOT NULL,
  `designation` varchar(30) NOT NULL,
  `quantity` int(30) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `equipment`
--

INSERT INTO `equipment` (`ID`, `designation`, `quantity`, `description`) VALUES
(1, 'Projektor', 1, 'Hd projektor');

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
(1, 'valaki', '20251', 'AYIE0MS'),
(2, 'Janoska', '1999.09.12', 'JETTE1');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `teacher`
--

CREATE TABLE `teacher` (
  `ID` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `department_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `teacher`
--

INSERT INTO `teacher` (`ID`, `name`, `department_ID`) VALUES
(1, 'Dandóczi', 1),
(2, 'Réti Géza', 2),
(3, 'Tavas János', 3),
(43, 'John Doe', 4),
(52, 'Papp János', 2),
(53, 'Papika', 3),
(54, 'Toth Zoltán', 3),
(67, 'Totha', 3);

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
  ADD KEY `equipment_ID` (`equipment_ID`,`classroom_ID`,`grade_ID`,`teacher_ID`,`student_ID`);

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
  ADD PRIMARY KEY (`ID`);

--
-- A tábla indexei `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `department_ID` (`department_ID`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `classroom`
--
ALTER TABLE `classroom`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `course`
--
ALTER TABLE `course`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `department`
--
ALTER TABLE `department`
  MODIFY `ID` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `equipment`
--
ALTER TABLE `equipment`
  MODIFY `ID` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT a táblához `grade`
--
ALTER TABLE `grade`
  MODIFY `ID` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `student`
--
ALTER TABLE `student`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT a táblához `teacher`
--
ALTER TABLE `teacher`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_2` FOREIGN KEY (`equipment_ID`) REFERENCES `equipment` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `course_ibfk_3` FOREIGN KEY (`grade_ID`) REFERENCES `grade` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `course_ibfk_4` FOREIGN KEY (`classroom_ID`) REFERENCES `classroom` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `course_ibfk_5` FOREIGN KEY (`teacher_ID`) REFERENCES `teacher` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `course_ibfk_6` FOREIGN KEY (`student_ID`) REFERENCES `student` (`ID`) ON UPDATE CASCADE;

--
-- Megkötések a táblához `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`department_ID`) REFERENCES `department` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
