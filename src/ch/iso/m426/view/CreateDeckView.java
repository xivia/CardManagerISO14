package ch.iso.m426.view;

import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.Deck;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.lang.invoke.LambdaMetafactory;

public class CreateDeckView extends GridPane{
    private Label lblName = new Label(Constants.CREATE_DECK_LABEL_NAME);
    private TextField tfName = new TextField();
    private Label lblFormat = new Label(Constants.CREATE_DECK_LABEL_FORMAT);
    private ComboBox cmbFormat = new ComboBox(FXCollections.observableArrayList(Deck.FORMAT.values()));
    private Button btnAddDeck = new Button(Constants.CREATE_DECK_BUTTON);
    private Text statusText = new Text();

    public CreateDeckView(){
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        add(lblName, 0, 0);
        add(tfName, 1, 0);
        add(lblFormat, 0, 1);
        add(cmbFormat, 1, 1);

        btnAddDeck.setOnAction(event -> addDeck());
        btnAddDeck.setPrefHeight(40);
        btnAddDeck.setPrefWidth(100);
        add(btnAddDeck, 1, 2);

        add(statusText, 3, 1);
    }

    private void addDeck() {
        String comboBoxValue;
        if(this.cmbFormat.getValue() != null) {
            comboBoxValue = this.cmbFormat.getValue().toString();
        } else {
            comboBoxValue = "";
        }

        String name = this.tfName.getText();
        Deck.FORMAT format = Deck.FORMAT.CASUAL;

        for(Deck.FORMAT f : Deck.FORMAT.values()) {
            if(f.name().equals(comboBoxValue)) {
                format = f;
            }
        }

        Deck deck = new Deck(name, format);
        try {
            DatabaseHandler.saveDeck(deck);
            statusText.setText(Constants.CREATE_DECK_STATUS_CREATED);
        } catch (Exception e) {
            System.out.print(e);
            statusText.setText(Constants.CREATE_DECK_STATUS_ALREADY_EXIST);
        }
    }
}
