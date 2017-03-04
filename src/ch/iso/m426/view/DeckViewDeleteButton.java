package ch.iso.m426.view;

import ch.iso.m426.controller.DeckViewDeleteButtonEventHandler;
import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.DeckObservableList;
import javafx.scene.control.Button;

public class DeckViewDeleteButton extends Button {

    public DeckViewDeleteButton(){
        super();

        this.setText(Constants.DELETE_BUTTON_TEXT);
        this.setOnAction(new DeckViewDeleteButtonEventHandler());
        this.setPrefWidth(Double.MAX_VALUE);

    }

}
