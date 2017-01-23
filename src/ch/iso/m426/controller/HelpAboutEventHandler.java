package ch.iso.m426.controller;

import ch.iso.m426.model.Deck;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;



public class HelpAboutEventHandler implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Card Manager");
		alert.setContentText("Das ist ein Card Manager. Dieser wurde von 4 genialen K�pfen gemacht:David Bart, David Wyss, Serafima und Ugur.");

		alert.showAndWait();
		
	}



}
