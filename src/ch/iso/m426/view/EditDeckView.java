package ch.iso.m426.view;

import com.sun.org.apache.xpath.internal.operations.String;
import javafx.scene.layout.BorderPane;

/**
 * Created by Serafima on 27.02.2017.
 */
public class EditDeckView extends BorderPane {

    public int selectedDeckId;
    public String deckName;

    public EditDeckView() {
        CardToDeckTableView c2dView = new CardToDeckTableView();
        CardToDeckEditPane  c2dPane = new CardToDeckEditPane();
        this.setCenter(c2dView);
        this.setRight(c2dPane);

    }
}
