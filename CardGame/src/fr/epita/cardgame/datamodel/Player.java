package fr.epita.cardgame.datamodel;


/**
 * @author Dogara Ishaku
 *
 */
public class Player {
    private String name;
    private int wins;
    private int looses;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLooses() {
        return looses;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", wins=" + wins + ", looses=" + looses + ", id=" + id + '}';
    }

    public void setLooses(int looses) {
        this.looses = looses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
