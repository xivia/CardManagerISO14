package ch.iso.m426.controller;

import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.DeckObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DeckViewDeleteButtonEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(javafx.event.ActionEvent event) {
        try {
            String name = DeckObservableList.getTable().getSelectionModel().getSelectedItem().getName();
            DatabaseHandler.deleteDeck(name);
            DeckObservableList.get().clear();
            DatabaseHandler.getAllDecks();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
