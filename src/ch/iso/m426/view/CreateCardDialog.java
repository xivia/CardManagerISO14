package ch.iso.m426.view;

import ch.iso.m426.model.DatabaseHandler;
import ch.iso.m426.model.Card;
import com.sun.javafx.scene.control.behavior.TextAreaBehavior;
import com.sun.javafx.scene.control.skin.TextAreaSkin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


/**
 * Created by David on 08.01.2017.
 */
public class CreateCardDialog extends GridPane {

    //Indicates the ID of the Current loaded Card
    private int currentCardNr = -1;
    Label lblErrorBar = new Label("");
    Label lblCurrentCard = new Label("New Card");
    Label lblName = new Label("Name:");
    TextField tfName = new TextField();
    Label lblEdition = new Label("Edition:");
    TextField tfEdition = new TextField();
    Label lblColor = new Label("Color:");
    TextField tfColor = new TextField();
    Label lblManaCost = new Label("Mana:");
    TextField tfManaCost = new TextField();
    Label lblAttack = new Label("Attack:");
    Spinner spAttack = new Spinner(0, 100, 0);
    Label lblDefence = new Label("Defence:");
    Spinner spDefence = new Spinner(0, 100, 0);
    Label lblType = new Label("Types:");
    TextField[] tfTypes = new TextField[6];
    Label lblRuleText = new Label("Rule Text:");
    TextArea taRuleText = new TextArea();
    Label lblStoryText = new Label("Story Text:");
    TextArea taStoryText = new TextArea();
    Button btnAddCard = new Button("Add");
    Button btnEditCard = new Button("Edit");
    Button btnDeleteCard = new Button("Delete");
    CardTableView cardTableView = new CardTableView();
    Label lblSearchCard = new Label("Search");
    TextField tfSearchCard = new TextField();

    public CreateCardDialog() {

        for (int i = 0; i < 6; i++) {
            tfTypes[i] = new TextField();
        }

        for (Label i : new Label[]{lblErrorBar, lblName, lblEdition, lblColor, lblManaCost,
                lblAttack, lblDefence, lblType, lblRuleText, lblStoryText, lblSearchCard}) {
            //sets Label alignment in Cell
            GridPane.setHalignment(i, HPos.RIGHT);
            //sets Text alignment in Label
            i.setAlignment(Pos.CENTER_RIGHT);
            i.setMinWidth(40);
        }

        for (Control i : new Control[]{tfName, tfEdition, tfColor, tfManaCost, spAttack, spDefence, tfTypes[0],
                tfTypes[1], tfTypes[2], tfTypes[3], tfTypes[4], tfTypes[5], taRuleText, taStoryText, tfSearchCard}) {
            i.setMinWidth(10);
        }

        //the Grid Pane is always in the Center
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        ColumnConstraints columnConstraints = new ColumnConstraints();

        for (int i = 0; i < 9; i++) {
            getColumnConstraints().add(columnConstraints);
        }

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100f / 15f);

        for (int i = 0; i < 15; i++) {
            if (i != 1) {
                getRowConstraints().add(rowConstraints);
            } else {
                getRowConstraints().add(new RowConstraints(5));
            }
        }

        GridPane.setHalignment(lblCurrentCard, HPos.CENTER);
        lblCurrentCard.setFont(new Font("Arial", 20));
        lblCurrentCard.setAlignment(Pos.CENTER);
        add(lblCurrentCard, 2, 0, 3, 1);

        add(lblName, 0, 2);
        add(tfName, 1, 2);

        add(lblEdition, 0, 3);
        tfEdition.promptTextProperty().set("set-code(SOI,ORI...)");
        add(tfEdition, 1, 3);

        add(lblColor, 0, 4);
        tfColor.promptTextProperty().set("card-background");
        add(tfColor, 1, 4);

        add(lblManaCost, 0, 5);
        add(tfManaCost, 1, 5);

        lblAttack.setMinWidth(50);
        add(lblAttack, 0, 6);
        add(spAttack, 1, 6);

        lblDefence.setMinWidth(50);
        add(lblDefence, 0, 7);
        add(spDefence, 1, 7);


        add(lblType, 2, 2);
        for (int i = 0; i < 6; i++) {
            tfTypes[i].promptTextProperty().set("Subtype/Type " + (i + 1));
            add(tfTypes[i], 3, 2 + i);
        }

        lblRuleText.setMinWidth(60);
        add(lblRuleText, 4, 2);
        taRuleText.setPrefHeight(15);
        taRuleText.setPrefWidth(150);
        add(taRuleText, 5, 2, 3, 3);

        lblStoryText.setMinWidth(60);
        add(lblStoryText, 4, 5);
        taStoryText.setPrefHeight(15);
        taStoryText.setPrefWidth(150);
        add(taStoryText, 5, 5, 3, 2);

        btnAddCard.setOnAction(event -> addCard());
        btnAddCard.setPrefHeight(40);
        btnAddCard.setPrefWidth(100);
        add(btnAddCard, 5, 7, 1, 1);

        btnEditCard.setOnAction(event -> editCard());
        btnEditCard.setPrefHeight(40);
        btnEditCard.setPrefWidth(100);
        add(btnEditCard, 6, 7, 1, 1);

        btnDeleteCard.setOnAction(event -> deleteCard());
        btnDeleteCard.setPrefHeight(40);
        btnDeleteCard.setPrefWidth(100);
        add(btnDeleteCard, 7, 7, 1, 1);

        GridPane.setHalignment(lblErrorBar, HPos.CENTER);
        lblErrorBar.setStyle("-fx-text-fill: coral");
        add(lblErrorBar, 5, 8, 4, 1);

        lblSearchCard.setFont(new Font("Arial", 15));
        GridPane.setHalignment(lblSearchCard, HPos.CENTER);
        lblSearchCard.setAlignment(Pos.CENTER);
        add(lblSearchCard, 0, 8, 5, 1);

        tfSearchCard.promptTextProperty().set("Search using card name");
        add(tfSearchCard, 0, 9, 5, 1);

        cardTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        add(cardTableView, 0, 10, 9, 6);

        btnDeleteCard.setVisible(false);
        btnEditCard.setVisible(false);
        changeCardTableViewValues();

        for (TextArea i : new TextArea[]{taRuleText, taStoryText}) {
            //This code creates
            i.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.TAB) {
                    TextAreaSkin skin = (TextAreaSkin) i.getSkin();
                    if (skin.getBehavior() instanceof TextAreaBehavior) {
                        TextAreaBehavior behavior = skin.getBehavior();
                        if (event.isControlDown()) {
                            behavior.callAction("InsertTab");
                        } else {
                            behavior.callAction("TraverseNext");
                        }
                        event.consume();
                    }
                }
            });
        }

        // Whenever another item gets selected load the values into the respective fields.
        cardTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Card>() {
            @Override
            public void changed(ObservableValue<? extends Card> observable, Card oldValue, Card newValue) {
                // Your action here
                if (newValue != null) {
                    loadCard(newValue);
                }
            }
        });

        //Whenever someone types something into the textfield load all Cards from the database into the cardTableView
        tfSearchCard.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                changeCardTableViewValues();
            }
        });
    }

    private void changeCardTableViewValues() {
        try {
            cardTableView.insertCards(DatabaseHandler.getSuggestedCards(tfSearchCard.getText()));
        } catch (Exception e) {
            System.out.println(e);
            lblErrorBar.setText(e.getMessage());
        }
    }

    public String[] getTypes() {
        String[] types = new String[6];
        for (int i = 0; i < 6; i++) {
            types[i] = tfTypes[i].getText();
        }
        return types;
    }

    private void addCard() {
        try {
            DatabaseHandler.saveCard(new Card(tfName.getText().trim(), getTypes(), tfEdition.getText(), tfColor.getText(),
                    tfManaCost.getText(), taRuleText.getText(), taStoryText.getText(), "artistName",
                    (byte) (int) spAttack.getValue(), (byte) (int) spDefence.getValue()));
            changeCardTableViewValues();
            lblErrorBar.setText("");
        } catch (Exception e) {
            System.out.println(e);
            lblErrorBar.setText(e.getMessage());
        }
    }

    private void editCard() {
        try {
            DatabaseHandler.updateCard(new Card(currentCardNr, tfName.getText().trim(), getTypes(), tfEdition.getText(), tfColor.getText(),
                    tfManaCost.getText(), taRuleText.getText(), taStoryText.getText(), "artistName",
                    (byte) (int) spAttack.getValue(), (byte) (int) spDefence.getValue()));
            lblCurrentCard.setText(tfName.getText());
            changeCardTableViewValues();
            lblErrorBar.setText("");
        } catch (Exception e) {
            System.out.println(e);
            lblErrorBar.setText(e.getMessage());
        }
    }

    private void deleteCard() {
        try {
            DatabaseHandler.deleteCard(currentCardNr);
            lblCurrentCard.setText("New Card");
            btnDeleteCard.setVisible(false);
            btnEditCard.setVisible(false);
            changeCardTableViewValues();
            lblErrorBar.setText("");
        } catch (Exception e) {
            System.out.println(e);
            lblErrorBar.setText(e.getMessage());
        }
    }

    private void loadCard(Card card) {
        lblCurrentCard.setText(card.name);
        tfName.setText(card.name);
        tfEdition.setText(card.edition);
        tfColor.setText(card.color);
        tfManaCost.setText(card.manaCost);
        spAttack.getValueFactory().setValue((int) card.attackValue);
        spDefence.getValueFactory().setValue((int) card.defenceValue);
        for (int i = 0; i < 6; i++) {
            if (i < card.types.length) {
                tfTypes[i].setText(card.types[i]);
            } else {
                tfTypes[i].setText("");
            }
        }
        taRuleText.setText(card.ruleText);
        taStoryText.setText(card.storyText);
        currentCardNr = card.id;
        btnDeleteCard.setVisible(true);
        btnEditCard.setVisible(true);
        lblErrorBar.setText("");
    }
}