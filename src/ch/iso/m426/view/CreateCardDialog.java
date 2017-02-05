package ch.iso.m426.view;

        import ch.iso.m426.model.DatabaseHandler;
        import ch.iso.m426.model.Card;
        import ch.iso.m426.model.Deck;
        import javafx.geometry.Insets;
        import javafx.geometry.Pos;
        import javafx.scene.control.*;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.Background;
        import javafx.scene.layout.GridPane;

        import javax.xml.crypto.Data;
        import java.awt.*;
        import java.util.ArrayList;
        import java.util.List;

        import static java.awt.Color.*;

/**
 * Created by David on 08.01.2017.
 */
public class CreateCardDialog extends GridPane {
    private Label lblName = new Label("Card Name:");
    private TextField tfName = new TextField();
    Label lblEdition = new Label("Edition");
    TextField tfEdition = new TextField();
    Label lblColor = new Label("Color");
    TextField tfColor = new TextField();
    Label lblManaCost = new Label("Mana Cost");
    TextField tfManaCost = new TextField();
    Label lblAttack = new Label("Attack");
    Spinner tfAttack = new Spinner(0,100,0);
    Label lblDefence = new Label("Defence");
    Spinner tfDefence = new Spinner(0,100,0);
    Label lblType = new Label("Types:");
    TextField tfType1 = new TextField();
    TextField tfType2 = new TextField();
    TextField tfType3 = new TextField();
    TextField tfType4 = new TextField();
    TextField tfType5 = new TextField();
    TextField tfType6 = new TextField();
    Label lblRuleText = new Label("Rule Text");
    TextArea taRuleText = new TextArea();
    Label lblStoryText = new Label("Story Text");
    TextArea taStoryText = new TextArea();
    Button btnAddCard = new Button("Add Card");

    public CreateCardDialog() {

        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        add(lblName, 0, 0);
        add(tfName, 1, 0);
        add(lblEdition, 0, 1);
        add(tfEdition, 1, 1);
        add(lblColor, 0, 2);
        add(tfColor, 1, 2);
        add(lblManaCost, 0, 3);
        add(tfManaCost, 1, 3);
        add(lblAttack, 0, 4);
        add(tfAttack, 1, 4);
        add(lblDefence, 0, 5);
        add(tfDefence, 1, 5);
        add(lblType, 2, 0);
        add(tfType1, 3, 0);
        add(tfType2, 3, 1);
        add(tfType3, 3, 2);
        add(tfType4, 3, 3);
        add(tfType5, 3, 4);
        add(tfType6, 3, 5);

        taRuleText.setPrefHeight(15);
        taRuleText.setPrefWidth(150);
        add(lblRuleText, 4, 0);
        add(taRuleText, 5, 0, 1, 3);

        taStoryText.setPrefHeight(15);
        taStoryText.setPrefWidth(150);
        add(lblStoryText, 4, 4);
        add(taStoryText, 5, 4, 1, 3);

        btnAddCard.setOnAction(event -> addCardIfValid());
        btnAddCard.setPrefHeight(40);
        btnAddCard.setPrefWidth(100);
        add(btnAddCard, 2, 7, 4, 2);
    }

    public String[] getTypes() {
        return new String[]{tfType1.getText(), tfType2.getText(), tfType3.getText(), tfType4.getText(), tfType5.getText(), tfType6.getText()};
    }

    private void addCardIfValid() {
        if (DatabaseHandler.getEditionNumber(tfEdition.getText()) == - 1) {
            System.out.println("Edition doesn't exist");
        } else {
            DatabaseHandler.saveCard(new Card(tfName.getText(), getTypes(), tfEdition.getText(), tfColor.getText(),
                    tfManaCost.getText(), taRuleText.getText(), taStoryText.getText(), "artistName",
                    (byte)(int)tfAttack.getValue(),(byte)(int)tfDefence.getValue()));
        }
    }
}
