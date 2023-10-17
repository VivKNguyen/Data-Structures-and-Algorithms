/*
 * file name: Blackjack.java
 *  Author: Vivian Nguyen
 * Date: 09/18/2022
 */

import java.util.ArrayList;

public class Blackjack {

    //initializing
    private Deck deck;
    private Hand pHand;
    private Hand dHand;
    private int cutoff;

    //constructor
    public Blackjack(int reshuffleCutoff) {
        this.deck = new Deck();
        this.deck.shuffle();
        this.cutoff = reshuffleCutoff;
        this.reset();

    }

    public Blackjack() {
        this.deck = new Deck();
        this.deck.shuffle();
        this.cutoff = 26;
        this.reset();

    }
    //gives the player and dealer new hands
    public void reset() {
        this.pHand = new Hand();
        this.dHand = new Hand();
        //if the deck gets too small, a new shuffled deck is created
        if (deck.size() < this.cutoff) {
            deck = new Deck();
            deck.build();
            this.deck.shuffle();
        }

    }
    //adds two cards to each hand
    public void deal() {
        for (int i = 0; i < 2; i++) {
            this.pHand.add(deck.deal());
            this.dHand.add(deck.deal());
        }

    }


    public boolean playerTurn() {
        //player gets a new card if the total value is under 16
        while (this.pHand.getTotalValue() < 16) {
            this.pHand.add(deck.deal());
        }
        //returns false if the player busts
        return !(this.pHand.getTotalValue() > 21);
    }

    public boolean dealerTurn() {
        //dealer gets a new card if the total value is under 17
        while (this.dHand.getTotalValue() < 17)
            this.dHand.add(deck.deal());
        //returns false if the dealer busts
        return !(this.dHand.getTotalValue() > 21);
    }

    public void setReshuffleCutoff(int cutoff) {
        this.cutoff = cutoff;
    }

    public int getReshuffleCutoff() {
        return this.cutoff;

    }

    public String toString() {
        //prints out the hands of each player in a presentable way
        String player = "Player's Hand:" + " " + this.pHand.toString() + "=" + this.pHand.getTotalValue();
        String dealer = "Dealer's Hand:" + " " + this.dHand.toString() + "=" + this.dHand.getTotalValue() + "\n";
        return player + "\n" + dealer;

    }

    public static void main(String[] args) {
        Blackjack b = new Blackjack(26);
        //Testing out the methods
            // System.out.println(b.deck);
            // b.deal();
            // b.playerTurn();
            // b.dealerTurn();
            // System.out.print(b);
        //calls the game function
        b.game(true);
    }

    public int game(boolean verbose) {
        Blackjack b = new Blackjack();
        b.reset();
        b.deal();

        b.playerTurn();
        b.dealerTurn();

        int result = 0;

        // player does not bust
        if (b.playerTurn() == true) {
            System.out.println("Player is staying with: " + b.pHand.getTotalValue());
            b.dealerTurn();
            // dealer does not bust
            if (b.dealerTurn() == true) {
                System.out.println("Dealer is staying with " + b.dHand.getTotalValue());
                // if the dealer has a greater hand value
                if (b.dHand.getTotalValue() > b.pHand.getTotalValue()) {
                    System.out.println("Dealer wins!" + "\n" + b.toString());
                    result = -1;

                }
                // if the player has a greater hand value
                if (b.dHand.getTotalValue() < b.pHand.getTotalValue()) {
                    System.out.println("Player wins!" + "\n"+ b.toString());
                    result = 1;

                }
                // if the values are equal
                if (b.dHand.getTotalValue() == b.pHand.getTotalValue()) {
                    System.out.println("Push!" + b.toString());
                    result = 0;
                }
                }
                }
                // if dealer busts
                if (b.dealerTurn() == false) {
                    System.out.println("Dealer busted! Player wins." + "\n"+ b.toString());
                    result = 1;
                }

                // if the player busts
            
        
        if (b.playerTurn() == false) {
            System.out.println("Player busted! Dealer wins." + "\n " + b.toString());
            result = -1;
        }

        // if the dealer busts
        if (verbose == true) {
            System.out.println("Final Player's Hand: " + b.pHand.toString() + "\n" +
                    "Final Dealer's Hand" + " : " + b.dHand.toString());
        }

        return result;

    }

}
