package ch.iso.m426.view;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			// Place menu at Top
			root.setTop(new CardManagerMenu(root));
			
			// Place Titlescreen at Center
			root.setCenter(new CardViewPane(root));

			
			// Set title
			primaryStage.setTitle(Constants.WINDOW_TITLE);
			primaryStage.setMinHeight(500);
			primaryStage.setMinWidth(500);

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