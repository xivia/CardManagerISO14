package ch.iso.m426.view;

import ch.iso.m426.model.Deck;
import ch.iso.m426.model.DeckObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeckTableView extends TableView<Deck> {

    public DeckTableView(){
        super();

        TableColumn<Deck, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Deck, String>("name"));
        nameCol.setPrefWidth(400);

        //TableColumn<Deck, String> formatCol = new TableColumn<Deck, String>("Format");
        //formatCol.setCellValueFactory(new PropertyValueFactory<Deck, String>("format"));
        //formatCol.setPrefWidth(400);

        DeckObservableList.get().add(new Deck("Bla", Deck.FORMAT.STANDARD, "Bla", ""));
        DeckObservableList.get().add(new Deck("Bsadadla", Deck.FORMAT.STANDARD, "Bldsfsda", ""));

        this.getColumns().addAll(nameCol/*,formatCol*/);
        this.setItems(DeckObservableList.get());
        DeckObservableList.setTable(this);
    }
}