package ch.iso.m426.controller;

import ch.iso.m426.view.CardTableView;
import ch.iso.m426.view.CardViewPane;
import ch.iso.m426.view.CreateCardDialog;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.layout.BorderPane;




public class StartPageEventHandler implements EventHandler<ActionEvent>{

	private final BorderPane cardManagerBorderPane;

    public StartPageEventHandler(BorderPane cardManagerBorderPane) {
        this.cardManagerBorderPane = cardManagerBorderPane;
    }

    @Override
    public void handle(ActionEvent event) {
    	
    	
        this.cardManagerBorderPane.setCenter(new CardViewPane(cardManagerBorderPane));
        
        //Overwrite the Delete Button from DeckManager
        this.cardManagerBorderPane.setBottom(null);
    }
}
