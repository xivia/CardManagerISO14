package ch.iso.m426.controller;


import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.HelpObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;




public class HelpAboutEventHandler implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Card Manager");
		
		DatabaseHandler.loadHelpData();
		alert.setContentText(HelpObservableList.get().get(1).getHelpText());

		alert.showAndWait();
		
	}



}
