package ch.iso.m426.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHandler {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/cardmanager?useSSL=false";
    static final String DB_USER = "root";
    static final String DB_PASS = "root";
    static Connection dbConnection;

    public DatabaseHandler() {
    }

    private static Connection getDBConnection() throws SQLException {
        if (dbConnection != null && !dbConnection.isClosed()) {
            return dbConnection;
        }
        dbConnection = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public static void getAllDecks() {
        try {
            Connection con = getDBConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM deck");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("DeckName");
                String formatName = rs.getString("DeckFormat");
                Deck.FORMAT format = Deck.FORMAT.CASUAL;
                for (Deck.FORMAT c : Deck.FORMAT.values()) {
                    if (c.name().equals(formatName)) {
                        format = c;
                    }
                }
                Deck deck = new Deck(name, format);
                DeckObservableList.get().add(deck);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void saveDeck(Deck deck) throws Exception {
        if (!DatabaseHandler.isDeckNameUnique(deck.getName())) {
            throw new Exception("Deck already exists");
        } else {
            Connection con = getDBConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO deck (DeckName, DeckFormat) VALUES (?,?);");
            stmt.setString(1,deck.getName().trim());
            stmt.setString(2,deck.getFormat().toString());
            stmt.executeUpdate();
        }
    }

    public static void saveCard(Card card) throws Exception {
        if (!DatabaseHandler.isCardNameUnique(card.name)) {
            throw new Exception("Cardname already exists");
        } else {
            Connection con = getDBConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `card` (CardName, CardColor, CardMana, CardTyp, CardAttack," +
                            "CardDefense, CardText, CardFlavorText, CardArtist, EdiID) VALUES (?,?,?,?,?,?,?,?,?,?);");

            stmt.setString(1,card.name.trim());
            stmt.setString(2,card.color);
            stmt.setString(3,card.manaCost);
            stmt.setString(4,validateCreatureTypes(card.types));
            stmt.setInt(5,card.attackValue);
            stmt.setInt(6,card.defenceValue);
            stmt.setString(7,card.ruleText);
            stmt.setString(8,card.storyText);
            stmt.setString(9,card.artistName);
            stmt.setInt(10,getEditionNumber(card.edition));
            stmt.executeUpdate();
        }
    }

    public static void updateCard(Card card) throws Exception {
        Connection con = getDBConnection();
        PreparedStatement stmt = dbConnection.prepareStatement("UPDATE `card` SET CardName = ?, CardMana = ?, CardColor = ?, " +
                "CardTyp = ?, CardAttack = ?, CardDefense = ?, CardText = ?, CardFlavorText = ?, CardArtist = ?, EdiID = ? WHERE CardID = ?;");

        stmt.setString(1,card.name.trim());
        stmt.setString(2,card.color);
        stmt.setString(3,card.manaCost);
        stmt.setString(4,validateCreatureTypes(card.types));
        stmt.setInt(5,card.attackValue);
        stmt.setInt(6,card.defenceValue);
        stmt.setString(7,card.ruleText);
        stmt.setString(8,card.storyText);
        stmt.setString(9,card.artistName);
        stmt.setInt(10,getEditionNumber(card.edition));
        stmt.setInt(11, card.id);
        stmt.executeUpdate();
    }

    public static void deleteCard(int nr) throws SQLException {
        Connection con = getDBConnection();
        PreparedStatement stmt = dbConnection.prepareStatement("DELETE FROM `card` WHERE CardID = ?;");

        stmt.setInt(1,nr);
        stmt.executeUpdate();
    }

    public static void deleteDeck(String name) throws SQLException {
        Connection con = getDBConnection();
        PreparedStatement stmt = dbConnection.prepareStatement("DELETE FROM `deck` WHERE DeckName = ?;");

        stmt.setString(1,name.trim());
        stmt.executeUpdate();
    }

    private static boolean isCardNameUnique(String name) throws SQLException {
        Connection con = getDBConnection();
        PreparedStatement stmt = dbConnection.prepareStatement("SELECT CardName FROM `card` where CardName = ?;");

        stmt.setString(1,name.trim());
        ResultSet rs = stmt.executeQuery();

        return !rs.next();
    }

    public static boolean isDeckNameUnique(String name) throws SQLException {
        Connection con = getDBConnection();
        PreparedStatement stmt = dbConnection.prepareStatement("SELECT CardName FROM `card` where CardName = ?;");

        stmt.setString(1,name.trim());
        ResultSet rs = stmt.executeQuery();

        return !rs.next();
    }

    public static List<Card> getSuggestedCards(String text) throws Exception {
        List<Card> suggestions = new ArrayList<>();
        //Because it is a like statement precautions have to be made in case someone inserts one of these(! % _[).
        text = text.replace("!", "!!") .replace("%", "!%") .replace("_", "!_") .replace("[", "![");
        Connection con = getDBConnection();
        PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM card WHERE CardName LIKE ? ESCAPE '!' ORDER BY CardName;");

        stmt.setString(1, text + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            suggestions.add(resultSetToCard(rs));
        }
        return suggestions;
    }

    private static Card resultSetToCard(ResultSet rs) throws SQLException {
        return new Card(rs.getInt("CardID"),
                rs.getString("CardName"),
                rs.getString("CardTyp").split("\\s*-\\s*"),
                getCardEdition(rs.getInt("EdiID")),
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
        PreparedStatement stmt = con.prepareStatement("SELECT EdiName FROM `edition` WHERE EdiID = ?;");

        stmt.setInt(1,editionID);
        ResultSet rs = stmt.executeQuery();

        rs.next();

        return rs.getString("EdiName");
    }

    public static int getEditionNumber(String edition) throws SQLException {
        Connection con = getDBConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT EdiID FROM `edition` WHERE EdiName = ?;");

        stmt.setString(1, edition);
        ResultSet rs = stmt.executeQuery();

        return rs.next() ? rs.getInt("EdiID") : 1;
    }

    public static String validateCreatureTypes(String[] types) {
        for (int i = 0; i < types.length; i++) {
            types[i] = types[i].replace("-", "");
        }
        return String.join("-", types);
    }
}