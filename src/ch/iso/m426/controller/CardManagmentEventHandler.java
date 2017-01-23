package ch.iso.m426.controller;

import ch.iso.m426.view.CardTableView;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.layout.BorderPane;




public class CardManagmentEventHandler implements EventHandler<ActionEvent>{

	private final BorderPane cardManagerBorderPane;

    public CardManagmentEventHandler(BorderPane cardManagerBorderPane) {
        this.cardManagerBorderPane = cardManagerBorderPane;
    }

    @Override
    public void handle(ActionEvent event) {
        this.cardManagerBorderPane.setCenter(new CardTableView<Object>());
    }

}
