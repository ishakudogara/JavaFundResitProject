package fr.epita.cardgame.datamodel;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Dogara Ishaku
 *
 */
public class ActivePlayer {

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    private Player player;
    List<Card> cards = new ArrayList<>();

    @Override
    public String toString() {
        return "ActivePlayer{" + "player=" + player.toString() + ", cards=" + cards + '}';
    }
}
