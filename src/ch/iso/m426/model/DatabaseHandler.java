package ch.iso.m426.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHandler {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/cardmanager?useSSL=false";

    static final String DB_USER = "root";
    static final String DB_PASS = "root";

    public DatabaseHandler() {}

    public static void getAllDecks(){
        try {
            Connection con = getDBConnection();
            Statement stmt;

            String query = "SELECT * FROM deck";

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("DeckName");
                String formatName = rs.getString("DeckFormat");
                Deck.FORMAT format = Deck.FORMAT.CASUAL;
                for(Deck.FORMAT c : Deck.FORMAT.values()){
                    if(c.name().equals(formatName)){
                        format = c;
                    }
                }
                Deck deck = new Deck(name, format);
                DeckObservableList.get().add(deck);
            }

        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    public static void getCardsToDeck(){
        try {
            Connection con = getDBConnection();
            Statement stmt;

            //String query = "SELECT * FROM card";
            String query = "SELECT * FROM card JOIN card_to_deck ON card.cardid=card_to_deck.cardid JOIN deck ON card_to_deck.deckid=deck.deckid WHERE card_to_deck.deckid=(SELECT deckid FROM deck WHERE deckname LIKE '"+DeckObservableList.getSelectedDeckName()+"');";

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            CardObservableList.get().clear();

            while (rs.next()) {
                System.out.println("card found");
                String name = rs.getString("CardName");
                String typeString = rs.getString("CardTyp");
                String color = rs.getString("CardColor");
                String mana = rs.getString("CardMana");
                String attack = rs.getString("CardAttack");
                String defence = rs.getString("CardDefense");
                /*String text = rs.getString("CardText");
                String flavor = rs.getString("CardFlavorText");
                String editionId = rs.getString("EdiId");*/

                Byte b = 1;
                String a[] = {"type1", "type2"};


                Byte attack_byte = (byte) Integer.parseInt(attack);
                Byte defence_byte = (byte) Integer.parseInt(defence);


                String[] types = typeString.split(", ");

                Card c = new Card(name, types, "none", color, mana, "none", "none", "none", attack_byte, defence_byte);
                //Card c = new Card(name, types, "none", color, mana, "none", "none", "none", b, b);
                //Card c = new Card("name", a, "edition", "erd", "waerqw", "", "", "", b, b);
                CardObservableList.get().add(c);
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }



    public static void saveDeck(Deck deck) throws Exception {
        if (!DatabaseHandler.isDeckNameUnique(deck.getName())){
            throw new Exception("Deck already exists");
        } else {
            Connection con = getDBConnection();
            Statement stmt = null;
            String query = "INSERT INTO deck (DeckName, DeckFormat) VALUES ('" + deck.getName() + "' , '" + deck.getFormat() + "');";
            stmt = con.createStatement();
            stmt.execute(query);
        }
    }

    public static void saveCard(Card card) throws Exception {

        if (!DatabaseHandler.isCardNameUnique(card.name)) {
            throw new Exception("Cardname already exists");
        } else {
            Connection con = getDBConnection();
            Statement stmt = null;
            String query = "INSERT INTO `card` (`CardName`, `CardColor`, `CardMana`, `CardTyp`, `CardAttack`, `CardDefense`, `CardText`, `CardFlavorText`, `CardArtist`, `EdiID`) VALUES ('"
                    + card.name.trim() + "','" + card.color + "','" + card.manaCost + "','" + String.join("-", card.types) + "'," + card.attackValue + "," + card.defenceValue + ",'" + card.ruleText
                    + "','" + card.storyText + "','" + card.artistName + "'," + getEditionNumber(card.edition) + ");";
            stmt = con.createStatement();
            stmt.execute(query);
        }
    }

    public static void updateCard(Card card) throws Exception {
        Connection con = getDBConnection();
        Statement stmt = null;
        String query = "UPDATE `card` SET " +
                "CardName = '" +
                card.name.trim() +
                "', CardMana = '" +
                card.manaCost +
                "', CardColor = '" +
                card.color +
                "', CardTyp = '" +
                String.join("-", card.types) +
                "', CardAttack = '" +
                card.attackValue +
                "', CardDefense = '" +
                card.defenceValue +
                "', CardText = '" +
                card.ruleText +
                "', CardFlavorText = '" +
                card.storyText +
                "', CardArtist = '" +
                card.artistName +
                "', EdiID = '" +
                getEditionNumber(card.edition) +
                "' WHERE CardID = '" + card.id + "';";
        stmt = con.createStatement();
        stmt.execute(query);
    }

    public static void deleteCard(int nr) throws SQLException {

        Connection con = getDBConnection();
        Statement stmt = null;
        String query = "DELETE FROM `card` WHERE CardID = " + nr;
        stmt = con.createStatement();
        stmt.execute(query);
    }

    public static void deleteDeck(String name) throws SQLException {

        Connection con = getDBConnection();
        Statement stmt = null;
        String query = "DELETE FROM deck WHERE DeckName = '" + name + "';";
        stmt = con.createStatement();
        stmt.execute(query);
    }

    private static boolean isCardNameUnique(String name) throws SQLException {
        Connection con = getDBConnection();
        Statement stmt = null;
        String query = "SELECT * FROM `card` where CardName LIKE '" + name.trim() + "' ;";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return false;
        }
        return true;
    }

    public static Card getCardByCardName(String name) throws Exception {
        Connection con = getDBConnection();
        Statement stmt = null;
        String query = "SELECT * FROM card WHERE CardName = '" + name + "';";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next()) {
            throw new Exception("Card doesn't exist");
        }
        return resultSetToCard(rs);
    }

    public static boolean isDeckNameUnique(String name) throws SQLException {
        Connection con = getDBConnection();
        Statement stmt = null;
        String query = "SELECT * FROM deck where DeckName LIKE '" + name.trim() + "' ;";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return false;
        }
        return true;
    }

    public static Card getCard(int cardNr) throws SQLException {
        Connection con = getDBConnection();
        Statement stmt = null;
        String query = "SELECT * FROM+" +
                " card WHERE CardID = " + cardNr + ";";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        Card card = resultSetToCard(rs);
        card.id = cardNr;
        return card;
    };

    public static List<String> get5SuggestedCardNames(String text) throws Exception {
        Connection con = getDBConnection();
        Statement stmt = null;
        String query = "SELECT * FROM card WHERE CardName LIKE '" + text +"%' ORDER BY CardName;";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<String> suggestions = new ArrayList<>();
        while (rs.next()) {
            suggestions.add(rs.getString("CardName"));
        }
        return suggestions;
    }

    private static Card resultSetToCard(ResultSet rs) throws SQLException {
        return new Card(
                rs.getInt("CardID"),
                rs.getString("CardName"),
                (String[]) Arrays.asList(rs.getString("CardTyp").split("\\s*-\\s*")).toArray(),
                getCardEdition(rs.getInt("CardID")),
                rs.getString("CardColor"),
                rs.getString("CardMana"),
                rs.getString("CardText"),
                rs.getString("CardFlavorText"),
                rs.getString("CardArtist"),
                rs.getByte("CardAttack"),
                rs.getByte("CardDefense"));
    }

    public static String getCardEdition(int editionID) throws SQLException {
        Connection con = getDBConnection();
        Statement stmt = null;
        String query = "SELECT EdiName FROM `edition` WHERE EdiID = " + editionID + ";";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        return rs.getString("EdiName");
    }

    public static int getEditionNumber(String edition) throws SQLException {
        Connection con = getDBConnection();
        Statement stmt = null;
        String query = "SELECT EdiID FROM `edition` WHERE EdiName LIKE '" + edition + "';";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return rs.getInt("EdiID");
        }
        return 1;
    }

    private static Connection getDBConnection() {

        Connection dbConnection = null;

        try {

            Class.forName(JDBC_DRIVER);

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASS);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

    public static Card findCardByName(String cardName) {
        Byte b = 1;
        String a[] = {"type1", "type2"};
        Card card = new Card("", a, "", "", "" ,"", "" ,"", b, b);
        try {
            Connection con = getDBConnection();
            Statement stmt;

            //String query = "SELECT * FROM card";
            String query = "SELECT * FROM card WHERE cardName like '"+cardName+"'";

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);



            while (rs.next()) {
                String name = rs.getString("CardName");
                String typeString = rs.getString("CardTyp");
                String color = rs.getString("CardColor");
                String mana = rs.getString("CardMana");
                String attack = rs.getString("CardAttack");
                String defence = rs.getString("CardDefense");
                Byte attack_byte = (byte) Integer.parseInt(attack);
                Byte defence_byte = (byte) Integer.parseInt(defence);
                String[] types = typeString.split(", ");

                card = new Card(name, types, "none", color, mana, "none", "none", "none", attack_byte, defence_byte);
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        return card;
    }

    public static void removeCardToDeckBYCardName(String cardName){
        System.out.println("Removing from deck: "+cardName+" / "+DeckObservableList.getSelectedDeckName());
        try {
            Connection con = getDBConnection();
            Statement stmt;
            String query = "DELETE ctd.* FROM card_to_deck AS ctd\n" +
                    "JOIN card AS c\n" +
                    "ON ctd.cardid=c.cardid\n" +
                    "JOIN deck AS d\n" +
                    "ON ctd.deckid=d.deckid\n" +
                    "WHERE c.cardname like '"+cardName+"' AND d.deckname LIKE '"+DeckObservableList.getSelectedDeckName()+"';";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    public static void addCardToDeckByName(String name) {
        try {
            Connection con = getDBConnection();
            Statement stmt;
            String query = "";
            stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO card_to_deck (cardid, deckid) \n" +
                    "VALUES (\n" +
                    "(SELECT cardid FROM card WHERE cardName LIKE '"+name+"'), \n" +
                    "(SELECT deckid FROM deck WHERE deckName LIKE '"+DeckObservableList.getSelectedDeckName()+"' ));");
        } catch(SQLException e) {
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No such card found");
            alert.setHeaderText(null);
            alert.setContentText("The card \""+name+"\" does not exist.");

            alert.showAndWait();
        }
    }

    public static ObservableList loadCardNamesToList() {
        List<String> cards = new ArrayList<>();
        try {
            Connection con = getDBConnection();
            Statement stmt;
            String query = "SELECT cardname FROM card";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    String name = rs.getString("CardName");
                    cards.add(name);
                }
            } else {
                cards.add("No cards exist");
            }

        } catch(SQLException e) {
            System.out.println(e);
        }
        ObservableList<String> data = FXCollections.observableList(cards);
        return data;
    }

}