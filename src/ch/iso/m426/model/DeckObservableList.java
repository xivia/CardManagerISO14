package ch.iso.m426.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

public class DeckObservableList {

    private final static ObservableList<Deck> decks = FXCollections.observableArrayList();
    private static TableView<Deck> deckTable = null;
    public static String selectedDeckName = null;

    public static ObservableList<Deck> get(){
        return decks;
    }

    public static TableView<Deck> getTable(){
        return deckTable;
    }

    public static void setTable(TableView<Deck> table) {

        DeckObservableList.get().clear();
        DatabaseHandler.getAllDecks();
        DeckObservableList.deckTable = table;
    }

    public static String getSelectedDeckName() {
        return selectedDeckName;
    }

    public static void setSelectedDeckName(String selectedDeckName) {
        DeckObservableList.selectedDeckName = selectedDeckName;
    }

}
