package ch.iso.m426.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Created by Serafima on 27.02.2017.
 */
public class CardToDeckEditPane extends GridPane {

    public CardToDeckEditPane() {

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(20);
        this.setPadding(new Insets(25, 25, 25, 25));
        this.setPrefWidth(400);

        add(new Label("Selected card properties:"), 0, 1);
        add(new Label(" "), 0, 2);
        add(new Label("Type:"), 0, 3);          add(new Label("some type"), 1, 3);
        add(new Label("Color:"), 0, 4);         add(new Label("red"), 1, 4);
        add(new Label("Mana cost:"), 0, 5);     add(new Label("3"), 1, 5);
        add(new Label("Attack:"), 0, 6);        add(new Label("4"), 1, 6);
        add(new Label("Defence:"), 0, 7);       add(new Label("2"), 1, 7);
        //add(new Text("Here comes the card story. Lorem ipsum dolor sit amet..."), 1, 8);
        add(new Label("[REMOVE]"), 1, 9);

        add(new Label("Add cards:"), 0, 21);
        add(new Label("[DROPDOWN]"), 0, 20);    add(new Label("Add to deck"), 1, 20);

        //add(new Label("TODO: \n Add dropdown with all cards \n Add 'Add' button"), 0, 2);

    }

}
