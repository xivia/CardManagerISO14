package ch.iso.m426.view;
import ch.iso.m426.controller.CardManagementEventHandler;
import ch.iso.m426.controller.CardToDeckManagementEventHandler;
import ch.iso.m426.controller.DeckManagementEventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class CardViewPaneButtonBar extends HBox {
	private final BorderPane cardManagerBorderPane;
	
	public CardViewPaneButtonBar(BorderPane cardManagerBorderPane) {
		super();
		this.setPadding(new Insets(15, 12, 15, 12));
		this.setSpacing(10);
		this.cardManagerBorderPane = cardManagerBorderPane;
		Button buttonCard = new Button("Karten Verwalten");
		buttonCard.setPrefSize(150, 20);
		buttonCard.setOnAction(new CardManagementEventHandler(cardManagerBorderPane));
		

		Button buttonDeck = new Button("Deck Verwalten");
		buttonDeck.setPrefSize(150,20);
		buttonDeck.setOnAction(new DeckManagementEventHandler(this.cardManagerBorderPane));
		
		this.getChildren().addAll(buttonCard,buttonDeck);		

	
	}
}

