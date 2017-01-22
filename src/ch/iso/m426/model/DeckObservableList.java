package ch.iso.m426.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DeckObservableList {

    private final static ObservableList<Deck> decks = FXCollections.observableArrayList();
    private static TableView<Deck> deckTable = null;

    public static ObservableList<Deck> get(){
        return decks;
    }

    public static TableView<Deck> getTable(){
        return deckTable;
    }

    public static void setTable(TableView<Deck> table) {
        DeckObservableList.deckTable = table;
    }

    public static void saveData() {

    }

    public static void loadData() {

    }
}