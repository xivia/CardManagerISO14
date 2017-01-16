package ch.iso.m426.view;

import ch.iso.m426.controller.DeckManagementEventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class CardManagerMenu extends MenuBar {

	private final BorderPane CardManagerBorderPane;

	public CardManagerMenu(BorderPane CardManagerBorderPane) {

		this.CardManagerBorderPane = CardManagerBorderPane;

		// Add MenuEntrys to MenuBar
		this.getMenus().addAll(makeMenuManager(), makeMenuHelp());
	}

	private Menu makeMenuManager() {

		// Menu Entry 1
		Menu manager = new Menu(Constants.MENU_TITLE_MANAGE);

		MenuItem managerCards = new MenuItem(Constants.SUB_MENU_TITLE_MANAGER_CARDS);

		MenuItem managerDeck = new MenuItem(Constants.SUB_MENU_TITLE_MANAGER_DECK);
		managerDeck.setOnAction(event -> DeckManagementEventHandler.manageDeck(this.CardManagerBorderPane));

		MenuItem managerExit = new MenuItem(Constants.SUB_MENU_TITLE_MANAGER_EXIT);
		
		// Add Items to MenuEntrys
		manager.getItems().addAll(managerCards, managerDeck, managerExit);

		return manager;
	}

	private Menu makeMenuHelp() {
		
		// Menu Entry 2
		Menu help = new Menu(Constants.MENU_TITLE_HELP);

		MenuItem helpHelp = new MenuItem(Constants.SUB_MENU_TITLE_HELP_HELP);
		MenuItem helpAbout = new MenuItem(Constants.SUB_MENU_TITLE_HELP_ABOUT);
		
		// Add Items to MenuEntrys
		help.getItems().addAll(helpHelp, helpAbout);
		
		return help;
	}

}