package ch.iso.m426.controller;

import ch.iso.m426.view.CreateDeckView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

public class CreateDecksEventHandler implements EventHandler<ActionEvent> {

    private final BorderPane cardManagerBorderPane;

    public CreateDecksEventHandler(BorderPane cardManagerBorderPane) {
        this.cardManagerBorderPane = cardManagerBorderPane;
    }

    @Override
    public void handle(ActionEvent event) {

        this.cardManagerBorderPane.setCenter(new CreateDeckView());
        this.cardManagerBorderPane.setBottom(null);
    }
}
