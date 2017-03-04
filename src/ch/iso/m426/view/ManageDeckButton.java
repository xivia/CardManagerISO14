package ch.iso.m426.view;

import ch.iso.m426.controller.CardToDeckManagementEventHandler;
import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.DeckObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class ManageDeckButton extends Button {

    public BorderPane cardManagerBorderPane;

    public ManageDeckButton(BorderPane cardManagerBorderPane){
        super();

        this.cardManagerBorderPane = cardManagerBorderPane;

        this.setText("Edit selected deck");
        this.setOnAction(event -> {
            try {
                new CardToDeckManagementEventHandler(cardManagerBorderPane);
            } catch (Exception e) {
                System.out.println(e);
            }

        });

    }

}
