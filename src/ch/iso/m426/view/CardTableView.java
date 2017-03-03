package ch.iso.m426.view;

import ch.iso.m426.model.Card;
import ch.iso.m426.model.CardObservableList;
import ch.iso.m426.model.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serafima on 15.01.2017.
 */
public class CardTableView<T> extends TableView<Card> {
    public CardTableView() {
        super();

        TableColumn<Card, String> nameCol = new TableColumn<Card, String>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Card, String>("name"));
        nameCol.setMinWidth(50);

        TableColumn<Card, String> typeCol = new TableColumn<Card, String>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<Card, String>("types"));
        typeCol.setMinWidth(50);

        TableColumn<Card, String> editionCol = new TableColumn<Card, String>("Edition");
        editionCol.setCellValueFactory(new PropertyValueFactory<Card, String>("edition"));
        editionCol.setMinWidth(40);

        TableColumn<Card, String> manaCol = new TableColumn<Card, String>("Mana");
        manaCol.setCellValueFactory(new PropertyValueFactory<Card, String>("manaCost"));
        manaCol.setMinWidth(30);

        TableColumn<Card, String> ruleCol = new TableColumn<Card, String>("Rule");
        ruleCol.setCellValueFactory(new PropertyValueFactory<Card, String>("ruleText"));
        ruleCol.setMinWidth(80);

        TableColumn<Card, String> storyCol = new TableColumn<Card, String>("Story");
        storyCol.setCellValueFactory(new PropertyValueFactory<Card, String>("storyText"));
        storyCol.setMinWidth(60);

        TableColumn<Card, String> attackCol = new TableColumn<Card, String>("Atk");
        attackCol.setCellValueFactory(new PropertyValueFactory<Card, String>("attackValue"));
        attackCol.setMinWidth(30);

        TableColumn<Card, String> defCol = new TableColumn<Card, String>("Def");
        defCol.setCellValueFactory(new PropertyValueFactory<Card, String>("defenceValue"));
        defCol.setMinWidth(30);

        this.getColumns().addAll(nameCol, typeCol, editionCol, manaCol, ruleCol, storyCol, attackCol, defCol);
    }

    public void insertCards(List<Card> entries) {

        this.setItems(FXCollections.observableList(entries));
    }
}