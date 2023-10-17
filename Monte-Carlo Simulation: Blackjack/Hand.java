/*
 * file name: Hand.java
 * author: Vivian Nguyen
 * Date: 09/18/2022
 */
import java.util.ArrayList;

public class Hand {
    
    ArrayList<Card> handArray;
    /**
         * Creates an empty hand as an ArrayList of Cards.  
         */
        public Hand(){
            this.handArray = new ArrayList<Card>();
        }

    /**
         * Removes any cards currently in the hand. 
         */
        public void reset(){
            this.handArray.clear();

        }

    /**
     * Adds the specified card to the hand.
     * @param card the card to be added to the hand
     */
    public void add(Card card){
        this.handArray.add(card);
    }

    /**
     * Returns the number of cards in the hand.
     * @return the number of cards in the hand
     */
    public int size(){
        return this.handArray.size();
    }

    /**
     * Returns the card in the hand specified by the given index. 
     * @param index the index of the card in the hand.
     * @return the card in the hand at the specified index.
     */
    public Card getCard(int index){
        return this.handArray.get(index);
    }

    /**
     * Returns the summed value over all cards in the hand.
     * @return the summed value over all cards in the hand
     */
    public int getTotalValue(){
        int sum = 0;
        for (Card card: handArray)
        {
            sum+=card.getValue();
        }
        return sum;
    }

    /**
     * Returns a string representation of the hand.
     * @return a string representation of the hand
     */
    public String toString(){
        String list = "";
        for (int i=0; i<this.handArray.size();i++){
            int val= this.handArray.get(i).getValue();
            String hand = Integer.toString(val);
            list+= (hand + " ");
            
        }
        return list;

        
    }

    public void clear() {
    }
    
}

