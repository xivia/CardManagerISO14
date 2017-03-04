package ch.iso.m426.view;

import ch.iso.m426.model.Card;
import ch.iso.m426.model.CardObservableList;
import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.Deck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Created by Serafima on 27.02.2017.
 */
public class CardToDeckEditPane extends GridPane {
    TextField selCardName = new TextField();

    public CardToDeckEditPane() {

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(20);
        this.setPadding(new Insets(25, 25, 25, 25));
        this.setPrefWidth(400);

        Button addToDeck = new Button("Add to deck");
        addToDeck.setOnAction( (ActionEvent event) ->{
            String name = this.selCardName.getText();
            System.out.println(name);
            DatabaseHandler.addCardToDeckByName(name);
            CardObservableList.get().clear();
            DatabaseHandler.getCardsToDeck();
                }
        );

        Button removeFromDeck = new Button("Remove from deck");
        removeFromDeck.setOnAction( (ActionEvent event) ->{
                    Card card = DatabaseHandler.findCardByName(CardObservableList.getSelectedCardName());
                    CardObservableList.get().remove(card);
                    CardObservableList.get().clear();
                    DatabaseHandler.removeCardToDeckBYCardName(CardObservableList.getSelectedCardName());
                    DatabaseHandler.getCardsToDeck();
                }
        );

        add(new Label("Selected card properties:"), 0, 1);
        add(new Label(" "), 0, 2);
        add(new Label("Type:"), 0, 3);          add(new Label("some type"), 1, 3);
        add(new Label("Color:"), 0, 4);         add(new Label("red"), 1, 4);
        add(new Label("Mana cost:"), 0, 5);     add(new Label("3"), 1, 5);
        add(new Label("Attack:"), 0, 6);        add(new Label("4"), 1, 6);
        add(new Label("Defence:"), 0, 7);       add(new Label("2"), 1, 7);
        //add(new Text("Here comes the card story. Lorem ipsum dolor sit amet..."), 1, 8);
        add(removeFromDeck, 1, 9);

        add(new Label("Add cards:"), 0, 21);
        add(selCardName, 0, 20);    add(addToDeck, 1, 20);

        //add(new Label("TODO: \n Add dropdown with all cards \n Add 'Add' button"), 0, 2);

    }

    private void addCardToDeck() {
        String name = this.selCardName.getText();
        DatabaseHandler.addCardToDeckByName(name);
        CardObservableList.get().clear();
        DatabaseHandler.getCardsToDeck();
    }

}
