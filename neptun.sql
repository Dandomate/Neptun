-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Nov 15. 22:07
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
(2, '2', 120),
(3, 'D1', 60),
(4, 'D2', 34);

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
(2, 'Vendéglátás');

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
  `ID` int(11) NOT NULL,
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
  `ID` int(30) NOT NULL,
  `OM` bigint(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `birthDate` date NOT NULL,
  `neptunCode` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `student`
--

INSERT INTO `student` (`ID`, `OM`, `name`, `birthDate`, `neptunCode`) VALUES
(1, 762727364721, 'Papp János', '2023-11-22', 'AYIE0M'),
(2, 762727364722, 'Ádám Zsolt', '2023-11-29', 'AYIE2M'),
(3, 762727364733, 'Réti Géza', '2023-11-08', 'ASD1SD'),
(4, 762727364734, 'Molnár Réka', '2023-11-01', 'SDGF1S');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`ID`);

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
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `classroom`
--
ALTER TABLE `classroom`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `department`
--
ALTER TABLE `department`
  MODIFY `ID` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT a táblához `equipment`
--
ALTER TABLE `equipment`
  MODIFY `ID` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT a táblához `grade`
--
ALTER TABLE `grade`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `student`
--
ALTER TABLE `student`
  MODIFY `ID` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
