package fr.epita.cardgame.datamodel;


/**
 * @author Dogara Ishaku
 *
 */
public class Card {
    private int value;
    private String color;
    private String valueStr;

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Card{" + "value=" + value + ", color=" + color + ", valueStr=" + valueStr + '}';
    }
    
}
