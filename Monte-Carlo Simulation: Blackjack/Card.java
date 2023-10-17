/*
 * file name: Card.java
 * Author: Vivian Nguyen
 * Date: 09/18/2022
 
 */

public class Card {
    /**
     * The value of the card.
     */
    private int value; 

    /**
     * Constructs a card with the specified value.
     * @param val
     */
    public Card()
    {

        value=2;
    }

    public Card(int v){
        if(v>1 && v<12)
        value = v;
    }

    
    
/**
     * Returns the value of the card.
     * @return the value of the card
     */

    public int getValue(){

        return value;
    }

    /**
     * Returns a string representation of this card.
     * @return a string representation of this card
     */

    public String toString(){

        return Integer.toString(value);
    }
    public static void main(String[] args){
        Card myCard1= new Card();
        Card myCard2 = new Card(10);
        System.out.println(myCard1);

    }
    
}





   

    
