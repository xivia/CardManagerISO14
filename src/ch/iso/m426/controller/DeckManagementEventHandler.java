package ch.iso.m426.controller;

import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.view.DeckTableView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class DeckManagementEventHandler implements EventHandler<ActionEvent> {

    private final BorderPane cardManagerBorderPane;

    public DeckManagementEventHandler(BorderPane cardManagerBorderPane) {
        this.cardManagerBorderPane = cardManagerBorderPane;
    }

    @Override
    public void handle(ActionEvent event) {

        DeckTableView deckTableView = new DeckTableView();
        Button button = new Button();
        button.setText("Löschen");
        //button.setOnAction(DatabaseHandler.deleteDeck(deckTableView.getSelected Item));

        this.cardManagerBorderPane.setCenter(deckTableView);
        this.cardManagerBorderPane.setBottom(button);
    }
}
