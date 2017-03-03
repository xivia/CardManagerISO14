package ch.iso.m426.view;

import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.Card;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


/**
 * Created by David on 08.01.2017.
 */
public class CreateCardDialog extends GridPane {
    private int currentCardNr = -1;

    Label lblErrorBar = new Label("");
    Label lblCurrentCard = new Label("New Card");
    Label lblName = new Label("Card Name:");
    TextField tfName = new TextField();
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
    TextField[] tfTypes = new TextField[6];
    Label lblRuleText = new Label("Rule Text");
    TextArea taRuleText = new TextArea();
    Label lblStoryText = new Label("Story Text");
    TextArea taStoryText = new TextArea();
    Button btnAddCard = new Button("Add");
    Button btnEditCard = new Button("Edit");
    Button btnDeleteCard = new Button("Delete");
    ComboBox chFindCard = new ComboBox();
    Button btnloadCardIfExist = new Button("Load Card");

    public CreateCardDialog() {
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(100f / 8f);
        for(int i = 0; i < 8;i++) {
            getColumnConstraints().add(col);
        }

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(100f / 15f);
        for(int i = 0;i < 10;i++){
            getRowConstraints().add(row);
        }


        add(lblCurrentCard, 3, 0,1,2);
        add(lblName, 0, 2);
        add(tfName, 1, 2);
        add(lblEdition, 0, 3);
        add(tfEdition, 1, 3);
        add(lblColor, 0, 4);
        add(tfColor, 1, 4);
        add(lblManaCost, 0, 5);
        add(tfManaCost, 1, 5);
        add(lblAttack, 0, 6);
        add(tfAttack, 1, 6);
        add(lblDefence, 0, 7);
        add(tfDefence, 1, 7);
        add(lblType, 3, 2);
        for(int i = 0; i < 6; i++){
            tfTypes[i] = new TextField();
            add(tfTypes[i], 4, 2 + i);
        }

        taRuleText.setPrefHeight(15);
        taRuleText.setPrefWidth(150);
        add(lblRuleText,5, 2);
        add(taRuleText, 6, 2, 2, 3);

        taStoryText.setPrefHeight(15);
        taStoryText.setPrefWidth(150);
        add(lblStoryText, 5, 5);
        add(taStoryText, 6, 5, 2, 3);

        btnAddCard.setOnAction(event -> addCard());
        btnAddCard.setPrefHeight(40);
        btnAddCard.setPrefWidth(100);
        add(btnAddCard, 0, 8, 1, 1);

        btnEditCard.setOnAction(event -> editCard());
        btnEditCard.setPrefHeight(40);
        btnEditCard.setPrefWidth(100);
        add(btnEditCard,1,8,1,1);

        btnDeleteCard.setOnAction(event -> deleteCard());
        btnDeleteCard.setPrefHeight(40);
        btnDeleteCard.setPrefWidth(100);
        add(btnDeleteCard,2,8,1,1);

        chFindCard.setEditable(true);
        //Additional Feature: autocomplete card search
        //TODO Ask Born why combobox is not editable after selecting item.
        /*chFindCard.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setTop5SuggestedNames();
            }
        });*/
        chFindCard.setOnAction(event -> setTop5SuggestedNames());
        add(chFindCard,4,8,2,1);

        btnloadCardIfExist.setOnAction(event -> loadCard());
        btnloadCardIfExist.setPrefHeight(40);
        btnloadCardIfExist.setPrefWidth(100);
        add(btnloadCardIfExist,6,8,1,1);

        add(lblErrorBar, 2,9,3,1);

        btnDeleteCard.setVisible(false);
        btnEditCard.setVisible(false);
    }

    public String[] getTypes() {
        String[] s = new String[6];
        for (int i = 0; i < 6; i++) {
            s[i] = tfTypes[i].getText();
        }
        return s;
    }

    private void addCard() {
        try {
            DatabaseHandler.saveCard(new Card(tfName.getText().trim(), getTypes(), tfEdition.getText(), tfColor.getText(),
                    tfManaCost.getText(), taRuleText.getText(), taStoryText.getText(), "artistName",
                    (byte) (int) tfAttack.getValue(), (byte) (int) tfDefence.getValue()));
        }
        catch(Exception e){
            System.out.println(e);
            lblErrorBar.setText(e.getMessage());
        }
    }

    private void editCard() {
        try {
            DatabaseHandler.updateCard(new Card(currentCardNr,tfName.getText().trim(), getTypes(), tfEdition.getText(), tfColor.getText(),
                    tfManaCost.getText(), taRuleText.getText(), taStoryText.getText(), "artistName",
                    (byte) (int) tfAttack.getValue(), (byte) (int) tfDefence.getValue()));
            lblCurrentCard.setText(tfName.getText());
        }
        catch(Exception e){
            System.out.println(e);
            lblErrorBar.setText(e.getMessage());
        }
    }

    private void deleteCard(){
        try {
            DatabaseHandler.deleteCard(currentCardNr);
            lblCurrentCard.setText("New Card");
            btnDeleteCard.setVisible(false);
            btnEditCard.setVisible(false);
        }
        catch(Exception e){
            System.out.println(e);
            lblErrorBar.setText(e.getMessage());
        }
    }

    private void loadCard(){
        try {
            fillValuesIntoField(DatabaseHandler.getCardByCardName(chFindCard.getEditor().getText()));
            lblCurrentCard.setText(chFindCard.getEditor().getText());
            chFindCard.getEditor().clear();
            btnDeleteCard.setVisible(true);
            btnEditCard.setVisible(true);
        }
        catch(Exception e){
            System.out.println(e);
            lblErrorBar.setText(e.getMessage());
        }
    }

    private void setTop5SuggestedNames(){
        try {
            chFindCard.setItems(FXCollections.observableList(DatabaseHandler.get5SuggestedCardNames(chFindCard.getEditor().getText())));
        }
        catch(Exception e){
            System.out.println(e);
            lblErrorBar.setText(e.getMessage());
        }
    }

    private void fillValuesIntoField(Card card){
        tfName.setText(card.name);
        tfEdition.setText(card.edition);
        tfColor.setText(card.color);
        tfManaCost.setText(card.manaCost);
        tfAttack.getValueFactory().setValue((int)card.attackValue);
        tfDefence.getValueFactory().setValue((int)card.defenceValue);
        for(int i = 0; i < 6 && i < card.types.length; i++){
            tfTypes[i].setText(card.types[i]);
        }
        taRuleText.setText(card.ruleText);
        taStoryText.setText(card.storyText);
        currentCardNr = card.id;
    }
}