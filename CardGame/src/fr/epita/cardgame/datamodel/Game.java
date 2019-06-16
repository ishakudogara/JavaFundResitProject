package fr.epita.cardgame.datamodel;

import fr.epita.cardgame.services.CardGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author Dogara Ishaku
 *
 */
public class Game {
    private List<ActivePlayer> player = new ArrayList<>();
    List<Card> cards = new ArrayList<>();
    private int rounds;

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public List<ActivePlayer> getPlayer() {
        return player;
    }

    public void setPlayer(List<ActivePlayer> player) {
        this.player = player;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    public void start(){
        int run = 0;
        while(run < 13){
            System.out.println("Round:"+(run+1));
            List<Integer> index = new ArrayList<>();
            for(int i=0;i<4;){
                int rand = generateRandomIntIntRange(0,cards.size()-1);
                if(!index.contains(rand)){
                    index.add(rand);
                    i++;
                }
            }
            List<Card> removeCards = new ArrayList<>();
            for(int i=0;i<4;i++){
                ActivePlayer player = this.player.get(i);
                Card getCard = cards.get(index.get(i));
                System.out.println(player.getPlayer().getName()+" has "+ getCard.getValueStr()+" "+getCard.getColor());
                
                removeCards.add(getCard);
            }
            int maxCard = getMaxCard(removeCards);
            ActivePlayer player = this.player.get(maxCard);
            List<Card> savedCards = player.getCards();
            savedCards.addAll(removeCards);
            player.setCards(savedCards);
            System.out.println("Round "+(run+1)+" winner "+player.getPlayer().getName());
            System.out.println("\n");
            cards.removeAll(removeCards);
            run++;
        }
        System.out.println("Results");
        System.out.println("____________________________________________________________________________");
        Player winner = winner().getPlayer();
        CardGame.updateWinner(winner);
        for(ActivePlayer player:this.player){
            Player player1 = player.getPlayer();
            if(winner != player1){
                CardGame.updateLooser(player1);
            }
        }
        System.out.println("\nWinner of the game:"+winner.getName());
    }
    public ActivePlayer winner(){
        ActivePlayer player = this.player.get(0);
        System.out.println(player.getPlayer().getName()+" has "+player.getCards().size()+" cards.");
        int size = player.getCards().size();
        for(int i=1;i<this.player.size();i++){
            System.out.println(this.player.get(i).getPlayer().getName()+" has "+this.player.get(i).getCards().size()+" cards.");
            if(this.player.get(i).getCards().size()>size){
                player = this.player.get(i);
                size = this.player.get(i).getCards().size();
            }
        }
        return player;
    }
    public static int getMaxCard(List<Card> cards){
        int max=cards.get(0).getValue();
        int maxIndex=0;
        for(int i=1;i<cards.size();i++){
            if(cards.get(i).getValue()>max){
                max=cards.get(i).getValue();
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public String toString() {
        return "Game{" + "player=" + player + ", cards=" + cards + ", rounds=" + rounds + '}';
    }
}
