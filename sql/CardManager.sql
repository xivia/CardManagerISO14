
--
-- Datenbank: `cardmanager`
--
DROP DATABASE IF EXISTS cardmanager;
CREATE DATABASE IF NOT EXISTS `cardmanager` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `cardmanager`;


--
-- Tabellenstruktur für Tabelle `edition`
--

CREATE TABLE `edition` (
  `EdiID` int(11) NOT NULL AUTO_INCREMENT,
  `EdiName` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`EdiID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


--
-- Daten für Tabelle `edition`
--

INSERT INTO `edition` (`EdiID`, `EdiName`) VALUES
(1, 'Unknown'),
(2, 'ISD'),
(3, 'DKA'),
(4, 'AVR'),
(5, 'RTR'),
(6, 'GTC'),
(7, 'DGM'),
(8, 'THS'),
(9, 'BNG'),
(10, 'JOU'),
(11, 'KTK'),
(12, 'FRF'),
(13, 'DTK'),
(14, 'BFZ'),
(15, 'OGW'),
(16, 'SOI'),
(17, 'EMN'),
(18, 'KLD'),
(19, 'AER'),
(20, 'AKH'),
(21, 'HOU');

-- --------------------------------------------------------

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `card`
--

CREATE TABLE `card` (
  `CardID` int(11) NOT NULL AUTO_INCREMENT,
  `CardColor` varchar(126) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardName` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardMana` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardTyp` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardAttack` int(11) DEFAULT NULL,
  `CardDefense` int(11) DEFAULT NULL,
  `CardText` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardFlavorText` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `CardArtist` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EdiID` int(11) NOT NULL,
  FOREIGN KEY (`EdiID`) REFERENCES `edition`(`EdiID`) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`CardID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- RELATIONEN DER TABELLE `card`:
--   `EdiID`
--       `edition` -> `EdiID`
--   `EdiID`
--       `edition` -> `EdiID`
--

--
-- Daten für Tabelle `card`
--

INSERT INTO `card` (`CardColor`, `CardName`, `CardMana`, `CardTyp`, `CardAttack`, `CardDefense`, `CardText`, `CardFlavorText`, `CardArtist`, `EdiID`) VALUES
('', 'Glücksrad', '2R', 'Hexerei', NULL, NULL, 'Alle Spieler müssen ihr Blatt abwerfen und sieben neue Karten ziehen', '', 'Daniel Gelon', 1);

--
-- Tabellenstruktur für Tabelle `deck`
--

CREATE TABLE `deck` (
  `DeckID` int(11) NOT NULL AUTO_INCREMENT,
  `DeckName` varchar(126) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DeckFormat` varchar(126) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`DeckID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Daten für Tabelle `deck`
--

INSERT INTO `deck` (`DeckID`, `DeckName`, `DeckFormat`) VALUES
(1, 'Whel Deck', 'Commander');

-- --------------------------------------------------------


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `carddeck`
--

CREATE TABLE `carddeck`  (
  `DeckCardID` int(11) NOT NULL AUTO_INCREMENT,
  `DeckID` int(11) NOT NULL,
  `CardID` int(11) NOT NULL, 
  FOREIGN KEY (`DeckID`) REFERENCES `deck`(`DeckID`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`CardID`) REFERENCES `card`(`CardID`) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`DeckCardID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Daten für Tabelle `carddeck`
--

INSERT INTO `carddeck` (`DeckCardID`, `DeckID`, `CardID`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `help`
--

CREATE TABLE `help` (
  `HelpID` int(11) NOT NULL AUTO_INCREMENT,
  `HelpText` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`HelpID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Daten für Tabelle `help`
--

INSERT INTO `help` (`HelpID`, `HelpText`) VALUES
(1, 'Dieses Programm ist selbsterklärent ;)'),
(2, 'Dieses Programm ist selbsterklärent ;)');


