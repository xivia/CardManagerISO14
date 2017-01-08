package ch.iso.m426.controller;

import ch.iso.m426.model.Deck;

public class ManagementEventHandler {

    public static void manageDeck() {
        Deck deck = new Deck();
        deck.getAllDecks("/home/darobba/Dokumente/Test");
    }
}
