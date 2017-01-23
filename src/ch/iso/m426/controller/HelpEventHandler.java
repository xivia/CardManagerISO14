package ch.iso.m426.controller;

import ch.iso.m426.model.Deck;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;



public class HelpEventHandler implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Card Manager");
		alert.setContentText("Hier wird erklärt wie alles funktioniert.");

		alert.showAndWait();
		
	}



}
