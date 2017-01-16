package ch.iso.m426.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

/**
 * Created by Serafima on 15.01.2017.
 */
public class CardObservableList {
    private static final ObservableList<Card> data = FXCollections.observableArrayList();
    private static TableView<Card> table = null;
    private static TextArea field = null;

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
}
