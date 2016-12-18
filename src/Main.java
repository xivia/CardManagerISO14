import ch.iso.m426.model.Card;
import ch.iso.m426.model.Deck;
import ch.iso.m426.model.Deck.FORMAT;

public class Main {

    public static void main(String[] args) {
        
        Deck deck = new Deck("Storm Crow Storm", FORMAT.MODERN, "Tier 1 Deck, Wins by turn 8. "); 
        
        for (int i = 0; i < s4; i++) {           
            deck.addCard(new Card("Storm Crow", new String[]{"Creature"}, new String[]{"Bird"}, "WeatherLight", "1U", 
                                  "Flying (This creature can't be blocked except by creatures with flying or reach.)",
                                  "FLAVORTEX",(byte) 1,(byte) 2));
        }
        
        for (int i = 0; i < 56; i++) {           
            deck.addCard(new Card("Island", new String[]{"Basic", "Land"}, new String[]{"Island"},"Weatherlight", "U",null,null,null,null));
        }   
        
        deck.saveDeck();
        deck.loadDeck();
        deck.printDeck();
        deck.printCard(3);
    }
}
