package ch.iso.m426.view;

import ch.iso.m426.controller.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class CardManagerMenu extends MenuBar {

	private final BorderPane cardManagerBorderPane;

	public CardManagerMenu(BorderPane cardManagerBorderPane) {

		this.cardManagerBorderPane = cardManagerBorderPane;

		// Add MenuEntrys to MenuBar
		this.getMenus().addAll(makeMenuManager(), makeMenuHelp());
	}

	private Menu makeMenuManager() {

		// Menu Entry 1
		Menu manager = new Menu(Constants.MENU_TITLE_MANAGE);

		MenuItem createCards = new MenuItem(Constants.SUB_MENU_TITLE_CREATE_CARDS);
		createCards.setOnAction(new CreateCardsEventHandler(cardManagerBorderPane));

		MenuItem createDecks = new MenuItem(Constants.SUB_MENU_TITLE_CREATE_DECKS);
		createDecks.setOnAction(new CreateDecksEventHandler(cardManagerBorderPane));

		MenuItem managerCards = new MenuItem(Constants.SUB_MENU_TITLE_MANAGER_CARDS);
		managerCards.setOnAction(new CardManagementEventHandler(cardManagerBorderPane));
		
		MenuItem managerDeck = new MenuItem(Constants.SUB_MENU_TITLE_MANAGER_DECK);
		managerDeck.setOnAction(new DeckManagementEventHandler(this.cardManagerBorderPane));

		MenuItem managerExit = new MenuItem(Constants.SUB_MENU_TITLE_MANAGER_EXIT);
		managerExit.setOnAction(new ExitEventHandler());

		//TODO: remove this menu element after testing
		MenuItem c2dTest = new MenuItem("Show c2d table view (TEST)");
		c2dTest.setOnAction(new CardToDeckManagementEventHandler(cardManagerBorderPane));

		// Add Items to MenuEntrys
		manager.getItems().addAll(createCards, createDecks, managerCards, managerDeck, managerExit, c2dTest);

		return manager;
	}

	private Menu makeMenuHelp() {
		
		// Menu Entry 2
		Menu help = new Menu(Constants.MENU_TITLE_HELP);

		MenuItem helpHelp = new MenuItem(Constants.SUB_MENU_TITLE_HELP_HELP);
		helpHelp.setOnAction(new HelpEventHandler());
		MenuItem helpAbout = new MenuItem(Constants.SUB_MENU_TITLE_HELP_ABOUT);
		helpAbout.setOnAction(new HelpAboutEventHandler());
		
		// Add Items to MenuEntrys
		help.getItems().addAll(helpHelp, helpAbout);
		
		return help;
	}

}