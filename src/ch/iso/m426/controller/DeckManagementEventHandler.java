package ch.iso.m426.controller;

import ch.iso.m426.view.DeckTableView;
import ch.iso.m426.view.DeckViewDeleteButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

public class DeckManagementEventHandler implements EventHandler<ActionEvent> {

    private final BorderPane cardManagerBorderPane;

    public DeckManagementEventHandler(BorderPane cardManagerBorderPane) {
        this.cardManagerBorderPane = cardManagerBorderPane;
    }

    @Override
    public void handle(ActionEvent event) {

        this.cardManagerBorderPane.setCenter(new DeckTableView());
        this.cardManagerBorderPane.setBottom(new DeckViewDeleteButton());
    }
}
