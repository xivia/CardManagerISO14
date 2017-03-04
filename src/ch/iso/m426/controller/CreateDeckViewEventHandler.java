package ch.iso.m426.controller;


import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.Deck;
import ch.iso.m426.view.Constants;
import ch.iso.m426.view.CreateDeckView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class CreateDeckViewEventHandler implements EventHandler<ActionEvent> {

    private final CreateDeckView myCreateDeckView;

    public CreateDeckViewEventHandler(CreateDeckView myCreateDeckView) {
        this.myCreateDeckView = myCreateDeckView;
    }

    @Override
    public void handle(ActionEvent event) {

        String comboBoxValue;

        if(this.myCreateDeckView.getCmbFormat().getValue() != null) {
            comboBoxValue = this.myCreateDeckView.getCmbFormat().getValue().toString();
        } else {
            comboBoxValue = "";
        }

        String name = this.myCreateDeckView.getTfName().getText();
        Deck.FORMAT format = Deck.FORMAT.CASUAL;

        for(Deck.FORMAT f : Deck.FORMAT.values()) {
            if(f.name().equals(comboBoxValue)) {
                format = f;
            }
        }

        Deck deck = new Deck(name, format);

        try {
            DatabaseHandler.saveDeck(deck);
            this.myCreateDeckView.getStatusText().setText(Constants.CREATE_DECK_STATUS_CREATED);
        } catch (Exception e) {
            System.out.print(e);
            this.myCreateDeckView.getStatusText().setText(Constants.CREATE_DECK_STATUS_ALREADY_EXIST);
        }
    }
}
