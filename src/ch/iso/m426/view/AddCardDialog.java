package ch.iso.m426.view;

import ch.iso.m426.model.Card;
import ch.iso.m426.model.Deck;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class AddCardDialog extends GridPane{
    public AddCardDialog(Deck deck){

        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        Label lblName = new Label("Card Name:");
        TextField tfName = new TextField();
        add(lblName, 0, 0);
        add(tfName, 1, 0);

        Label lblEdition = new Label("Edition");
        TextField tfEdition = new TextField();
        add(lblEdition, 0, 1);
        add(tfEdition,1,1);

        Label lblManaCost = new Label("Mana Cost");
        TextField tfManaCost = new TextField();
        add(lblManaCost, 0,2);
        add(tfManaCost,1,2);

        Label lblAttackValue = new Label("Attack");
        TextField tfAttackValue = new TextField();
        add(lblAttackValue, 0, 3);
        add(tfAttackValue,1,3);

        Label lblDefenceValue = new Label("Defence");
        TextField tfDefenceValue = new TextField();
        add(lblDefenceValue, 0, 4);
        add(tfDefenceValue,1,4);

        Label lblType = new Label("Types:");
        TextField tfType1 = new TextField();
        TextField tfType2 = new TextField();
        TextField tfType3 = new TextField();
        add(lblType, 2, 0);
        add(tfType1, 3, 0);
        add(tfType2, 3, 1);
        add(tfType3, 3, 2);

        Label lblSubType = new Label("Subtypes:");
        TextField tfSubType1 = new TextField();
        TextField tfSubType2 = new TextField();
        TextField tfSubType3 = new TextField();
        add(lblSubType, 2, 3);
        add(tfSubType1, 3, 3);
        add(tfSubType2, 3, 4);
        add(tfSubType3, 3, 5);

        Label lblRuleText = new Label("Rule Text");
        TextArea taRuleText = new TextArea();
        taRuleText.setPrefHeight(15);
        taRuleText.setPrefWidth(150);
        add(lblRuleText, 4, 0);
        add(taRuleText,5,0,1,3);

        Label lblStoryText = new Label("Story Text");
        TextArea taStoryText = new TextArea();
        taStoryText.setPrefHeight(15);
        taStoryText.setPrefWidth(150);
        add(lblStoryText, 4, 4);
        add(taStoryText,5,4,1,3);

        Button btnAddCard = new Button("Add Card");
        btnAddCard.setOnAction(event ->
                deck.addCard(new Card(tfName.getText(), new String[] {tfType1.getText(),tfType2.getText(),tfType3.getText()},
                             new String[] {tfSubType1.getText(),tfSubType2.getText(),tfSubType3.getText()}, tfEdition.getText(),
                             tfManaCost.getText(), taRuleText.getText(),taStoryText.getText(),
                             Byte.parseByte(tfAttackValue.getText()),Byte.parseByte(tfDefenceValue.getText()))));
        btnAddCard.setPrefHeight(40);
        btnAddCard.setPrefWidth(100);
        add(btnAddCard, 2, 7, 4, 2 );
    }
}
