package ch.iso.m426.view;

import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.Deck;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.lang.invoke.LambdaMetafactory;

public class CreateDeckView extends GridPane{
    private Label lblName = new Label("Deck Name:");
    private TextField tfName = new TextField();
    private Label lblFormat = new Label("Deck Format:");
    private TextField tfFormat = new TextField();
    private Button btnAddDeck = new Button("Add Deck");

    public CreateDeckView(){
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        add(lblName, 0, 0);
        add(tfName, 1, 0);
        add(lblFormat, 0, 1);
        add(tfFormat, 1, 1);

        btnAddDeck.setOnAction(event -> addDeck());
        btnAddDeck.setPrefHeight(40);
        btnAddDeck.setPrefWidth(100);
        add(btnAddDeck, 1, 2);
    }

    private void addDeck() {

        String name = this.tfName.getText();

        Deck deck = new Deck(name, Deck.FORMAT.CASUAL);
        DatabaseHandler.saveDeck(deck);
    }
}
