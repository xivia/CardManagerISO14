package ch.iso.m426.view;

import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.Deck;
import ch.iso.m426.model.DeckObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeckTableView extends TableView<Deck> {

    public DeckTableView(){
        super();

        TableColumn<Deck, String> nameCol = new TableColumn<>(Constants.DECK_TABLE_VIEW_NAME);
        nameCol.setCellValueFactory(new PropertyValueFactory<Deck, String>("name"));
        nameCol.setPrefWidth(550);

        TableColumn<Deck, String> formatCol = new TableColumn<Deck, String>(Constants.DECK_TABLE_VIEW_FORMAT);
        formatCol.setCellValueFactory(new PropertyValueFactory<Deck, String>("format"));
        formatCol.setPrefWidth(200);

        DatabaseHandler.getAllDecks();

        this.getColumns().addAll(nameCol, formatCol);
        this.setItems(DeckObservableList.get());
        DeckObservableList.setTable(this);
    }
}