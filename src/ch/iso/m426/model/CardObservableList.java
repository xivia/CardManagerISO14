package ch.iso.m426.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * Created by Serafima on 15.01.2017.
 */
public class CardObservableList {
    private static final ObservableList<Card> data = FXCollections.observableArrayList();
    private static TableView<Card> table = null;
    private static TextArea field = null;

    public static String selectedCardName = null;
    public static String selectedCardTypes = null;
    public static String selectedCardColor = null;
    public static String selectedCardMana = null;
    public static Byte selectedCardAttack = null;
    public static Byte selectedCardDefense = null;
    private static GridPane pane = null;
    private static Label labelTypes = null;
    private static Label labelColor= null;
    private static Label labelMana = null;
    private static Label labelAttack = null;
    private static Label labelDefense = null;

    public static ObservableList<Card> get() {
        return data;
    }

    public static TableView<Card> getTable() {
        return table;
    }

    public static void setTable(TableView<Card> table) {
        CardObservableList.table = table;
    }

    public static void saveData() {

    }

    public static void loadData() {

    }

    public static String getSelectedCardName() {
        return selectedCardName;
    }

    public static void setSelectedCardName(String selectedCardName) {
        CardObservableList.selectedCardName = selectedCardName;
    }

    public static String getSelectedCardTypes() {
        return selectedCardTypes;
    }

    public static void setSelectedCardTypes(String selectedCardTypes) {
        CardObservableList.selectedCardTypes = selectedCardTypes;
    }

    public static String getSelectedCardColor() {
        return selectedCardColor;
    }

    public static void setSelectedCardColor(String selectedCardColor) {
        CardObservableList.selectedCardColor = selectedCardColor;
    }

    public static String getSelectedCardMana() {
        return selectedCardMana;
    }

    public static void setSelectedCardMana(String selectedCardMana) {
        CardObservableList.selectedCardMana = selectedCardMana;
    }

    public static Byte getSelectedCardAttack() {
        return selectedCardAttack;
    }

    public static void setSelectedCardAttack(Byte selectedCardAttack) {
        CardObservableList.selectedCardAttack = selectedCardAttack;
    }

    public static Byte getSelectedCardDefense() {
        return selectedCardDefense;
    }

    public static void setSelectedCardDefense(Byte selectedCardDefense) {
        CardObservableList.selectedCardDefense = selectedCardDefense;
    }

    public static GridPane getPane() {
        return pane;
    }

    public static void setPane(GridPane pane) {
        CardObservableList.pane = pane;
    }

    public static Label getLabelTypes() {
        return labelTypes;
    }

    public static void setLabelTypes(Label labelTypes) {
        CardObservableList.labelTypes = labelTypes;
    }

    public static TextArea getField() {
        return field;
    }

    public static void setField(TextArea field) {
        CardObservableList.field = field;
    }

    public static Label getLabelColor() {
        return labelColor;
    }

    public static void setLabelColor(Label labelColor) {
        CardObservableList.labelColor = labelColor;
    }

    public static Label getLabelMana() {
        return labelMana;
    }

    public static void setLabelMana(Label labelMana) {
        CardObservableList.labelMana = labelMana;
    }

    public static Label getLabelAttack() {
        return labelAttack;
    }

    public static void setLabelAttack(Label labelAttack) {
        CardObservableList.labelAttack = labelAttack;
    }

    public static Label getLabelDefense() {
        return labelDefense;
    }

    public static void setLabelDefense(Label labelDefense) {
        CardObservableList.labelDefense = labelDefense;
    }
}
