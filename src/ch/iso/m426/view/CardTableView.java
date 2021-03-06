package ch.iso.m426.view;

import ch.iso.m426.controller.DeckRowListener;
import ch.iso.m426.model.Card;
import ch.iso.m426.model.CardObservableList;
import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.Deck;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * Created by Serafima on 15.01.2017.
 */
public class CardTableView<T> extends TableView<Card> {
    public CardTableView(){
        super();

        TableColumn<Card, String> nameCol = new TableColumn<Card, String>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Card, String>("name"));
        nameCol.setPrefWidth(120);

        TableColumn<Card, String> typeCol = new TableColumn<Card, String>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<Card, String>("types"));
        typeCol.setPrefWidth(120);

        TableColumn<Card, String> editionCol = new TableColumn<Card, String>("Edition");
        editionCol.setCellValueFactory(new PropertyValueFactory<Card, String>("edition"));
        editionCol.setPrefWidth(120);

        TableColumn<Card, String> manaCol = new TableColumn<Card, String>("Mana");
        manaCol.setCellValueFactory(new PropertyValueFactory<Card, String>("manaCost"));
        manaCol.setPrefWidth(120);

        TableColumn<Card, String> ruleCol = new TableColumn<Card, String>("Rule");
        ruleCol.setCellValueFactory(new PropertyValueFactory<Card, String>("ruleText"));
        ruleCol.setPrefWidth(120);

        TableColumn<Card, String> storyCol = new TableColumn<Card, String>("Story");
        storyCol.setCellValueFactory(new PropertyValueFactory<Card, String>("storyText"));
        storyCol.setPrefWidth(120);

        TableColumn<Card, String> attackCol = new TableColumn<Card, String>("Attack");
        attackCol.setCellValueFactory(new PropertyValueFactory<Card, String>("attackValue"));
        attackCol.setPrefWidth(120);

        TableColumn<Card, String> defCol = new TableColumn<Card, String>("Defence");
        defCol.setCellValueFactory(new PropertyValueFactory<Card, String>("defenceValue"));
        defCol.setPrefWidth(120);

        // some static data in the list
        //String a[] = {"type1", "type2"};
        //Byte b = 1;
        //CardObservableList.get().add(new Card("Test", a, a, "edition 1", "2", "rule", "text", b, b));
        //CardObservableList.get().add(new Card("Test", a, a, "edition 2", "mana cost", "rule", "text", b, b));

        CardObservableList.get().clear();
        DatabaseHandler.getAllCards();
        this.getColumns().addAll(nameCol, typeCol, editionCol, manaCol, ruleCol, storyCol, attackCol, defCol);
        this.setItems(CardObservableList.get());
        CardObservableList.setTable(this);

    }

}
