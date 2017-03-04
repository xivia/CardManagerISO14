package ch.iso.m426.controller;

import ch.iso.m426.model.Deck;
import ch.iso.m426.model.DeckObservableList;
import javafx.collections.ListChangeListener;

/**
 * Created by Serafima on 04.03.2017.
 */
public class DeckRowListener implements ListChangeListener<Integer> {

    @Override
    public void onChanged(javafx.collections.ListChangeListener.Change<? extends Integer> c) {
        if (c.getList().size()==1){
            String deckName = DeckObservableList.get().get(c.getList().get(0)).getName();
            System.out.print("Selected Deck name: "+deckName);
            DeckObservableList.setSelectedDeckName(deckName);
        }

    }

}
