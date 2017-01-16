package ch.iso.m426.view;

import ch.iso.m426.model.Card;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 * Created by Serafima on 15.01.2017.
 */
public class CardViewPane extends BorderPane{
    public CardViewPane() {
        super();

        TableView<Card> table = new CardTableView<Card>();
        this.setCenter(table);

    }


}
