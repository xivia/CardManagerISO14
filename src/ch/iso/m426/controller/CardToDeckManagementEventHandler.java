package ch.iso.m426.controller;

import ch.iso.m426.model.DeckObservableList;
import ch.iso.m426.view.CardToDeckTableView;
import ch.iso.m426.view.EditDeckView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

/**
 * Created by Serafima on 27.02.2017.
 */
public class CardToDeckManagementEventHandler implements EventHandler<ActionEvent> {

    private final BorderPane cardManagerBorderPane;
    public String deckName;

    public CardToDeckManagementEventHandler(BorderPane cardManagerBorderPane) {
        this.cardManagerBorderPane = cardManagerBorderPane;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Selected deck name (edit): "+ DeckObservableList.getSelectedDeckName());
        if (DeckObservableList.getSelectedDeckName() != null) {
            this.cardManagerBorderPane.setCenter(new EditDeckView());
        }
    }
}
