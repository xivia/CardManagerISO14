package ch.iso.m426.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Deck {

    public enum FORMAT { STANDARD, COMMANDER, MODERN, CASUAL }

    private String path;
    private String name;
    private String description;
    private FORMAT format;
    private List<Card> cardList;

    public Deck(){
        this.name = null;
        this.format = null;
        this.description = null;
        this.path = null;
        cardList = null;
    }

    public Deck(String name, FORMAT format, String description, String path){
        this.name = name;
        this.format = format;
        this.description = description;
        this.path = path;
        cardList = new ArrayList<Card>();
    }

    public Deck(String name, FORMAT format){
        this.name = name;
        this.format = format;
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    public void loadDeck(){
        cardList = new ArrayList<Card>();
        BufferedReader bufferedReader = null;
        String line = "";
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(path + name + ".csv")));
            String[] headerInformation = bufferedReader.readLine().split(";");
            name = headerInformation[0];
            format = FORMAT.valueOf(headerInformation[1]);

            while ((line = bufferedReader.readLine()) != null) {
                String[] cardInfo = line.split(";");
                cardList.add(
                        new Card(cardInfo[0],
                                cardInfo[1].split(":"),
                                cardInfo[3],
                                cardInfo[4],
                                cardInfo[5],
                                cardInfo[6],
                                cardInfo[7],
                                cardInfo[8],
                                (byte) Byte.parseByte(cardInfo[9]),
                                (byte) Byte.parseByte(cardInfo[10]))
                );
            }

            System.out.println("Deck:|" + name + "| has been loaded");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDeck(){
        try {
            PrintWriter printWriter = new PrintWriter(new File(path + name + ".csv"));
            StringBuilder sb = new StringBuilder();
            sb.append((name != null ? name : "?") + ";");
            sb.append((format != null ? format : "?") + ";");
            sb.append('\n');

            for (Card card : cardList) {
                sb.append((card.name != null ? card.name : "?") + ";");
                for(String type : card.types){
                    sb.append(type + ':');
                }
                sb.append(';');
                sb.append((card.color != null ? card.color : "?") + ";");
                sb.append((card.edition != null ? card.edition : "?") + ";");
                sb.append((card.manaCost != null ? card.manaCost : "?") + ";");
                sb.append((card.ruleText != null ? card.ruleText : "?") + ";");
                sb.append((card.storyText != null ? card.storyText : "?") + ";");
                sb.append((card.artistName != null ? card.artistName : "?") + ";");
                sb.append((card.attackValue != null ? card.attackValue : "0") + ";");
                sb.append((card.defenceValue != null ? card.defenceValue : "0") + ";");
                sb.append('\n');
            }

            printWriter.write(sb.toString());
            printWriter.close();
            System.out.println("Deck:|" + name + "| has been saved");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printDeck(){
        System.out.println("Deckname: " + name);
        System.out.println("Decktype: " + format);
        System.out.println("Description" + description);
        for (Card card : cardList) {
            System.out.println(card.name);
        }
    }

    public void printCard(int nr){
        cardList.get(nr).printCardInfo();
    }


    // returns all decks in the folder
    public void getAllDecks(String path) {

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        try {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile() && listOfFiles[i].getName().toLowerCase().endsWith(".csv")) {
                    String deck = listOfFiles[i].getName();
                    deck = deck.substring(0, deck.length() - 4);
                    System.out.println(deck);
                }
            }
        } catch (Exception e) {
            System.out.print("Error 404, Pfad existiert nicht oder ist fehlerhaft!");
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FORMAT getFormat() {
        return format;
    }

    public void setFormat(FORMAT format) {
        this.format = format;
    }

}
