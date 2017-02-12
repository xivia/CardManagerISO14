package ch.iso.m426.model;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHandler {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/cardmanager";

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
    public static void saveCard(Card card){
        int edition = getEditionNumber(card.edition);
        if(edition > -1) {
            try {
                Connection con = getDBConnection();
                Statement stmt = null;
                String query = "INSERT INTO `card` (`CardName`, `CardColor`, `CardMana`, `CardTyp`, `CardAttack`, `CardDefense`, `CardText`, `CardFlavorText`, `CardArtist`, `EdiID`) VALUES ('"
                        + card.name + "','" + card.color + "','" + card.manaCost + "','" + String.join("-", card.types) + "'," + card.attackValue + "," + card.defenceValue + ",'" + card.ruleText
                        + "','" + card.storyText + "','" + card.artistName + "'," + getEditionNumber(card.edition) + ");";
                stmt = con.createStatement();
                stmt.execute(query);
            } catch (SQLException e) {
                System.out.print(e);
            }
        }
        else{
            System.out.println("Couldn't find edition");
        }
    }

    public static Card getCard(int cardNr) throws SQLException {
        try {
            Connection con = getDBConnection();
            Statement stmt = null;
            String query = "SELECT * FROM card WHERE CardID = " + cardNr + ";";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
                Card card = new Card(
                        rs.getString("CardName"),
                        (String[]) Arrays.asList(rs.getString("CardTyp").split("\\s*-\\s*")).toArray(),
                        getCardEdition(cardNr),
                        rs.getString("CardColor"),
                        rs.getString("CardMana"),
                        rs.getString("CardText"),
                        rs.getString("CardFlavorText"),
                        rs.getString("CardArtist"),
                        rs.getByte("CardAttack"),
                        rs.getByte("CardDefense")
                );
                card.id = cardNr;
                return card;
        } catch (SQLException e ) {
            System.out.print(e);
        }
        return null;
    }

    public static String getCardEdition(int editionID) {
        try {
            Connection con = getDBConnection();
            Statement stmt = null;
            String query = "SELECT EdiName FROM `edition` WHERE EdiID = " + editionID + ";";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getString("EdiName");
        } catch (SQLException e ) {
            System.out.print(e);
        }

        return "";
    }

    public static int getEditionNumber(String edition) {
        try {
            Connection con = getDBConnection();
            Statement stmt = null;
            String query = "SELECT EdiID FROM `edition` WHERE EdiName LIKE '" + edition + "';";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getInt("EdiID");
        } catch (SQLException e) {
            System.out.print(e);
        }
        return -1;
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
                    DB_URL, DB_USER,DB_PASS);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

}
