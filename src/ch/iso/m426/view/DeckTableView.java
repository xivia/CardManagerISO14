package ch.iso.m426.view;

import ch.iso.m426.controller.DeckRowListener;
import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.Deck;
import ch.iso.m426.model.DeckObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class DeckTableView extends TableView<Deck> {

    public DeckTableView(){
        super();

        TableColumn<Deck, String> nameCol = new TableColumn<>(Constants.DECK_TABLE_VIEW_NAME);
        nameCol.setCellValueFactory(new PropertyValueFactory<Deck, String>("name"));
        nameCol.setPrefWidth(400);

        TableColumn<Deck, String> formatCol = new TableColumn<Deck, String>(Constants.DECK_TABLE_VIEW_FORMAT);
        formatCol.setCellValueFactory(new PropertyValueFactory<Deck, String>("format"));
        formatCol.setPrefWidth(400);

        TableColumn<Deck, String> factoryCol = new TableColumn<>("");
        factoryCol.setCellValueFactory(new PropertyValueFactory<Deck, String>(""));
        factoryCol.setPrefWidth(0);


        Callback<TableColumn<Deck, String>, TableCell<Deck, String>> cellFactory = new Callback<TableColumn<Deck, String>, TableCell<Deck, String>>() {
            @Override
            public TableCell<Deck, String> call(TableColumn<Deck, String> param) {

                final TableCell<Deck, String> cell = new TableCell<Deck, String>() {

                    public void updateItem(String item, boolean empty) {
                        //super.updateItem(item, empty);
                        if (empty) {
                            //System.out.println("empty");
                        } else {
                            super.getTableView().getSelectionModel().getSelectedIndices().addListener(new DeckRowListener());
                        }
                    }

                };

                return cell;
            }
        };

        factoryCol.setCellFactory(cellFactory);

        DatabaseHandler.getAllDecks();

        System.out.println("Scene window........"+this.getScene());

        this.getColumns().addAll(nameCol, formatCol, factoryCol);
        this.setItems(DeckObservableList.get());
        DeckObservableList.setTable(this);
    }
}