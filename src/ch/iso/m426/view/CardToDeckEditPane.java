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
    final ComboBox selCardBox = new ComboBox();
    Label labelTypes = new Label();
    Label labelMana = new Label();
    Label labelAttack = new Label();
    Label labelDefnce = new Label();

    public CardToDeckEditPane() {

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(20);
        this.setPadding(new Insets(25, 25, 25, 25));
        this.setPrefWidth(400);

        Button addToDeck = new Button("Add to deck");
        addToDeck.setOnAction( (ActionEvent event) ->{
            String name = (String)this.selCardBox.getValue();
            boolean exists = false;
            for (int i = 0; i < CardObservableList.get().size(); i++) {
                if (CardObservableList.get().get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                    exists = true;
                }
            }

            if (!exists) {
                DatabaseHandler.addCardToDeckByName(name);
                CardObservableList.get().clear();
                DatabaseHandler.getCardsToDeck();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Note:");
                alert.setHeaderText(null);
                alert.setContentText("The card \""+name+"\" already exists in this deck.");

                alert.showAndWait();
            }
        });

        Button removeFromDeck = new Button("Remove from deck");
        removeFromDeck.setOnAction( (ActionEvent event) ->{
                    Card card = DatabaseHandler.findCardByName(CardObservableList.getSelectedCardName());
                    CardObservableList.get().remove(card);
                    CardObservableList.get().clear();
                    DatabaseHandler.removeCardToDeckBYCardName(CardObservableList.getSelectedCardName());
                    DatabaseHandler.getCardsToDeck();
                }
        );


        /* COMBOBOX STUFF START */
        selCardBox.setPromptText("Select a card");
        selCardBox.setItems(DatabaseHandler.loadCardNamesToList());
        /* COMBOBOX STUFF END */

        add(new Label("Selected card properties:"), 0, 1);
        add(new Label(" "), 0, 2);
        add(new Label("Type:"), 0, 3);          add(labelTypes, 1, 3);
        add(new Label("Mana cost:"), 0, 4);     add(labelMana, 1, 4);
        add(new Label("Attack:"), 0, 5);        add(labelAttack, 1, 5);
        add(new Label("Defence:"), 0, 6);       add(labelDefnce, 1, 6);
        //add(new Text("Here comes the card story. Lorem ipsum dolor sit amet..."), 1, 8);
        add(removeFromDeck, 1, 9);

        add(selCardBox, 0, 20);    add(addToDeck, 1, 20);

        //add(new Label("TODO: \n Add dropdown with all cards \n Add 'Add' button"), 0, 2);

        CardObservableList.setPane(this);
        CardObservableList.setLabelTypes(labelTypes);
        CardObservableList.setLabelMana(labelMana);
        CardObservableList.setLabelAttack(labelAttack);
        CardObservableList.setLabelDefense(labelDefnce);

    }
}
