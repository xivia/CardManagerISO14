package ch.iso.m426.controller;

import ch.iso.m426.model.Deck;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.util.Arrays;

public class DeckManagementEventHandler {

    public static void manageDeck(BorderPane CardManagerBorderPane) {
        String [] decks = {"Deck1", "Deck2", "Deck3"};
        Text text = new Text(Arrays.toString(decks));
        CardManagerBorderPane.setCenter(text);
    }
}
