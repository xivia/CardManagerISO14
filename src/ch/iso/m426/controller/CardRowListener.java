package ch.iso.m426.controller;

import ch.iso.m426.model.CardObservableList;
import ch.iso.m426.model.Deck;
import ch.iso.m426.model.DeckObservableList;
import javafx.collections.ListChangeListener;

/**
 * Created by Serafima on 04.03.2017.
 */
public class CardRowListener implements ListChangeListener<Integer> {

    @Override
    public void onChanged(javafx.collections.ListChangeListener.Change<? extends Integer> c) {
        if (c.getList().size()==1){
            String cardName = CardObservableList.get().get(c.getList().get(0)).getName();
            String cardTypes = CardObservableList.get().get(c.getList().get(0)).getTypes();
            Byte cardAttack = CardObservableList.get().get(c.getList().get(0)).getAttackValue();
            Byte cardDefense = CardObservableList.get().get(c.getList().get(0)).getDefenceValue();
            String cardMana = CardObservableList.get().get(c.getList().get(0)).getManaCost();
            CardObservableList.setSelectedCardName(cardName);
            CardObservableList.setSelectedCardTypes(cardTypes);
            CardObservableList.setSelectedCardAttack(cardAttack);
            CardObservableList.setSelectedCardDefense(cardDefense);
            CardObservableList.setSelectedCardMana(cardMana);
        }

    }

}
