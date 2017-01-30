package ch.iso.m426.view;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new CardViewPane();
			
			// Place menu at Top
			root.setTop(new CardManagerMenu(root));

			// Set title
			primaryStage.setTitle("Card-Manager");

			Scene scene = new Scene(root,800,800);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
 	}
	
	public static void main(String[] args) {
		launch(args);
	}
}