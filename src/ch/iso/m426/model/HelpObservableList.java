package ch.iso.m426.model;


import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class HelpObservableList {
	
	    private final static ObservableList<Help> help = FXCollections.observableArrayList();

	    public static ObservableList<Help> get(){
	        return help;
	    }
	}
