package ch.iso.m426.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class CardManagerMenu extends MenuBar {

	public CardManagerMenu() {

		// Add MenuEntrys to MenuBar
		this.getMenus().addAll(makeMenuManager(), makeMenuHelp());
	}

	private Menu makeMenuManager() {

		// Menu Entry 1
		Menu manager = new Menu(Constants.MENU_TITLE_VERWALTEN);

		MenuItem managerCards = new MenuItem(Constants.SUB_MENU_TITLE_MANAGER_CARDS);
		MenuItem managerDeck = new MenuItem(Constants.SUB_MENU_TITLE_MANAGER_DECK);
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
