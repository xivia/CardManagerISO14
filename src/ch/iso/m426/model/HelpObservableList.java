package ch.iso.m426.model;


import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class HelpObservableList {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	    static final String DB_URL = "jdbc:mysql://localhost/cardmanager?useSSL=false";
	
	    static final String DB_USER = "root";
	    static final String DB_PASS = "root";

	    private final static ObservableList<Help> help = FXCollections.observableArrayList();

	    public static ObservableList<Help> get(){
	        return help;
	    }


		public static void loadData() {
			try {
				Class.forName(JDBC_DRIVER);
				Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement stmt = conn.createStatement();
               
				ResultSet rs = stmt.executeQuery("SELECT HelpText FROM `help;");

			while (rs.next()) {
					String helpText = rs.getString("HelpText");
					help.add(new Help(helpText ));
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
