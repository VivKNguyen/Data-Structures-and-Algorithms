/*
 * filename: Deck.java
 * Author: Vivian Nguyen
 * Date: 09/18/2022
 */

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> deck;

    /**
     * Creates the underlying deck as an ArrayList of Card objects.
     * Calls build() as a subroutine to build the deck itself.
     */
    public Deck() {

        this.build();

    }

    /**
     * Builds the underlying deck as a standard 52 card deck.
     * Replaces any current deck stored.
     */
    public void build() {
        this.deck = new ArrayList<Card>();
        for (int i = 0; i < 4; i++) {

            for (int j = 2; j <= 9; j++) {
                this.deck.add(new Card(j));
            }
            this.deck.add(new Card(11));
        }

        for (int k = 0; k < 16; k++) {
            this.deck.add(new Card(10));
        }

    }

    /**
     * Returns the number of cards left in the deck.
     * 
     * @return the number of cards left in the deck
     */
    public int size() {
        return deck.size();
    }

    /**
     * Returns and removes the first card of the deck.
     * 
     * @return the first card of the deck
     */
    public Card deal() {
        return deck.remove(0);
    }

    /**
     * Shuffles the cards currently in the deck.
     * Using collections and random
     */
    public void shuffle() {
        ArrayList<Card> shuffle = new ArrayList<Card>();
        Random rand = new Random();
        // System.out.println("Deck before shuffled");
        // System.out.println(this.deck);
        while (this.deck.size() != 0) {
            int r = rand.nextInt(this.deck.size());
            // System.out.println("random index");
            // System.out.println(r);
            shuffle.add(new Card(this.deck.get(r).getValue()));
            // System.out.println("random card");
            // System.out.println(this.deck.get(r).getValue());
            this.deck.remove(r);
        }
        deck = shuffle;
        // System.out.println("Deck after shuffled");
        // System.out.println(this.deck);
    }

    /**
     * Returns a string representation of the deck.
     * 
     * @return a string representation of the deck
     */
    public String toString() {
        String deckCards = "Deck:";
        for (int i = 0; i < this.deck.size(); i++) {
            deckCards += (this.deck.get(i).getValue() + " ");

        }
        return deckCards;
    }

    public static void main(String[] args) {
        Deck deck1 = new Deck();
        System.out.println("Original deck: \n" + deck1);
        deck1.shuffle();
        System.out.println("Shuffled deck: \n" + deck1);
    }
}
