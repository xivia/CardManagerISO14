package ch.iso.m426.controller;

import ch.iso.m426.model.Deck;
import ch.iso.m426.view.DeckTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.util.Arrays;

public class DeckManagementEventHandler {

    public static void manageDeck(BorderPane CardManagerBorderPane) {
        CardManagerBorderPane.setCenter(new DeckTableView());
    }
}
