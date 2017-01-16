package ch.iso.m426.view;

import ch.iso.m426.model.Card;
import ch.iso.m426.model.CardObservableList;
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

        TableColumn<Card, String> subtypeCol = new TableColumn<Card, String>("Subtype");
        subtypeCol.setCellValueFactory(new PropertyValueFactory<Card, String>("subtypes"));
        subtypeCol.setPrefWidth(120);

        TableColumn<Card, String> editionCol = new TableColumn<Card, String>("Edition");
        editionCol.setCellValueFactory(new PropertyValueFactory<Card, String>("edition"));
        editionCol.setPrefWidth(120);

        TableColumn<Card, String> manaCol = new TableColumn<Card, String>("Mana");
        manaCol.setCellValueFactory(new PropertyValueFactory<Card, String>("manaCOst"));
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

        this.getColumns().addAll(nameCol, typeCol, subtypeCol, editionCol, manaCol, ruleCol, storyCol, attackCol, defCol);
        this.setItems(CardObservableList.get());
        CardObservableList.setTable(this);

    }

}
