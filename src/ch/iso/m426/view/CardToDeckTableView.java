package ch.iso.m426.view;

import ch.iso.m426.model.Card;
import ch.iso.m426.model.CardObservableList;
import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.Deck;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.StrokeLineCap;

/**
 * Created by Serafima on 27.02.2017.
 */
public class CardToDeckTableView<T> extends TableView<Card> {
    public String deckName;

    public CardToDeckTableView(){
        super();

        TableColumn<Card, String> nameCol = new TableColumn<Card, String>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Card, String>("name"));
        nameCol.setPrefWidth(500);

        /*TableColumn<Card, String> manaCol = new TableColumn<Card, String>("Mana");
        manaCol.setCellValueFactory(new PropertyValueFactory<Card, String>("manaCost"));
        manaCol.setPrefWidth(120);

        TableColumn<Card, String> attackCol = new TableColumn<Card, String>("Attack");
        attackCol.setCellValueFactory(new PropertyValueFactory<Card, String>("attackValue"));
        attackCol.setPrefWidth(120);

        TableColumn<Card, String> defCol = new TableColumn<Card, String>("Defence");
        defCol.setCellValueFactory(new PropertyValueFactory<Card, String>("defenceValue"));
        defCol.setPrefWidth(120);*/


        // some static data in the list
        String a[] = {"type1", "type2"};
        Byte b = 1;
        //CardObservableList.get().add(new Card("Test", a, "edition", "red", "2", "rule", "text", "artist", b, b));
        //CardObservableList.get().add(new Card("Test2", a, "edition", "red", "2", "rule", "text", "artist", b, b));

        DatabaseHandler.getCardsToDeck();

        this.getColumns().addAll(nameCol);
        this.setItems(CardObservableList.get());
        CardObservableList.setTable(this);

    }

}
