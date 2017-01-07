DROP DATABASE IF EXISTS `cardbank`;
CREATE DATABASE IF NOT EXISTS `cardbank` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `cardbank`;

--
-- Tabellenstruktur für Tabelle `manatyp`
--

CREATE TABLE `manatyp` (
  `ManaID` int(11) NOT NULL AUTO_INCREMENT,
  `ManaName` varchar(128) NOT NULL,
  PRIMARY KEY (`ManaID`)
);

--
-- Daten für Tabelle `manatyp`
--

INSERT INTO `manatyp` (`ManaID`, `ManaName`) VALUES
(1, 'Insel'),
(2, 'Sumpf'),
(3, 'Gebirge'),
(4, 'Ebene'),
(5, 'Wald'),
(6, 'Ödnis'),
(7, 'Farblos');

--
-- Tabellenstruktur für Tabelle `edition`
--

CREATE TABLE `edition` (
  `EdiID` int(11) NOT NULL AUTO_INCREMENT,
  `EdiName` varchar(100) NOT NULL,
  PRIMARY KEY (`EdiID`)
);

--
-- Daten für Tabelle `edition`
--

INSERT INTO `edition` (`EdiID`, `EdiName`) VALUES
(1, 'Zendikar'),
(2, 'Inistrad'),
(3, 'Kaladesh');


CREATE TABLE `card` (
  `CardID` int(11) NOT NULL AUTO_INCREMENT,
  `CardName` varchar(128) NOT NULL,
  `CardTyp` varchar(64) NOT NULL,
  `CardSubTyp` varchar(64) DEFAULT NULL,
  `CardAttack` int(11) DEFAULT NULL,
  `CardDefense` int(11) DEFAULT NULL,
  `CardText` text NOT NULL,
  `CardPicture` varchar(64) DEFAULT NULL,
  `CardArtist` varchar(128) DEFAULT NULL,
  `EdiID` int(11) NOT NULL,
  FOREIGN KEY (EdiID) REFERENCES edition(EdiID) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`CardID`)
);

--
-- RELATIONEN DER TABELLE `card`:
--   `EdiID`
--       `edition` -> `EdiID`
--

--
-- Daten für Tabelle `card`
--

INSERT INTO `card` (`CardID`, `CardName`, `CardTyp`, `CardSubTyp`, `CardAttack`, `CardDefense`, `CardText`, `CardPicture`, `CardArtist`, `EdiID`) VALUES
(1, 'Glücksrad', 'Hexerei', NULL, NULL, NULL, 'Alle Spieler müssen ihr Blatt abwerfen und sieben neue Karten ziehen', NULL, 'Daniel Gelon', 1);


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `manacard`
--

CREATE TABLE  `manacard` (
  `ManaCardID` int(11) NOT NULL AUTO_INCREMENT,
  `ManaID` int(11) NOT NULL,
  `CardID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  FOREIGN KEY (CardID) REFERENCES `card`(CardID) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (ManaID) REFERENCES manatyp(ManaID) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`ManaCardID`)
);

--
-- RELATIONEN DER TABELLE `manacard`:
--   `CardID`
--       `card` -> `CardID`
--   `ManaID`
--       `manatyp` -> `ManaID`
--

--
-- Daten für Tabelle `manacard`
--

INSERT INTO `manacard` (`ManaCardID`, `ManaID`, `CardID`, `quantity`) VALUES
(1, 3, 1, 1),
(2, 7, 1, 2);

