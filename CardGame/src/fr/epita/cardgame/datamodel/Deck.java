package fr.epita.cardgame.datamodel;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Dogara Ishaku
 *
 */
public class Deck {

    private final List<Card> cards = new ArrayList<>();
    //club, diamond, heart, spade
    String[] suits = {"club", "diamond", "heart", "spade"};
    String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King","Ace"};

    public Deck() {
        for(String suit:suits){
            for (int i = 0; i < 13; i++) {
                Card card = new Card();
                card.setColor(suit);
                card.setValue(i);
                card.setValueStr(ranks[i]);
                cards.add(card);
            }
        }
    }
    
    public List<Card> getCards() {
        return cards;
    }
}
