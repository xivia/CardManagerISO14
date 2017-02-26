package ch.iso.m426.view;

import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.DeckObservableList;
import javafx.scene.control.Button;

public class DeleteButton extends Button {

    public DeleteButton(){
        super();

        this.setText(Constants.DELETE_BUTTON_TEXT);
        this.setOnAction(event -> {
            try {
                String name = DeckObservableList.getTable().getSelectionModel().getSelectedItem().getName();
                DatabaseHandler.deleteDeck(name);
                DeckObservableList.get().clear();
                DatabaseHandler.getAllDecks();
            } catch (Exception e) {
                System.out.println(e);
            }

        });

    }

}
