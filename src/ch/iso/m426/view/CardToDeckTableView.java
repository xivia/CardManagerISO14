package ch.iso.m426.view;

import ch.iso.m426.controller.CardRowListener;
import ch.iso.m426.model.Card;
import ch.iso.m426.model.CardObservableList;
import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.Deck;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Callback;

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

        TableColumn<Card, String> factoryCol = new TableColumn<Card, String>("");
        factoryCol.setCellValueFactory(new PropertyValueFactory<Card, String>(""));
        factoryCol.setPrefWidth(0);

        Callback<TableColumn<Card, String>, TableCell<Card, String>> cellFactory = new Callback<TableColumn<Card, String>, TableCell<Card, String>>() {
            @Override
            public TableCell<Card, String> call(TableColumn<Card, String> param) {

                final TableCell<Card, String> cell = new TableCell<Card, String>() {

                    public void updateItem(String item, boolean empty) {
                        //super.updateItem(item, empty);
                        if (empty) {
                            //System.out.println("empty");
                        } else {
                            super.getTableView().getSelectionModel().getSelectedIndices().addListener(new CardRowListener());
                        }
                    }

                };

                return cell;
            }
        };
        factoryCol.setCellFactory(cellFactory);

        // some static data in the list
        String a[] = {"type1", "type2"};
        Byte b = 1;
        //CardObservableList.get().add(new Card("Test", a, "edition", "red", "2", "rule", "text", "artist", b, b));
        //CardObservableList.get().add(new Card("Test2", a, "edition", "red", "2", "rule", "text", "artist", b, b));

        DatabaseHandler.getCardsToDeck();

        this.getColumns().addAll(nameCol, factoryCol);
        this.setItems(CardObservableList.get());
        CardObservableList.setTable(this);

    }

}
