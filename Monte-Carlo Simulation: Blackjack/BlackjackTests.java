/*
file name:      BlackjackTests.java
Authors:        Max Bender & Naser Al Madi
last modified:  8/28/2022

How to run:     java -ea BlackjackTests
*/


public class BlackjackTests {




    public static void blackjackTests() {

        // case 1: testing Blackjack() and Blackjack(i)
        
            // set up
            Blackjack bj1 = new Blackjack(26);
            Blackjack bj2 = new Blackjack(26);

        // deals for two different blackjacks and plays the turns for each
        bj1.deal();
        bj1.playerTurn();
        bj1.dealerTurn();

        bj2.deal();
        bj2.playerTurn();
        bj2.dealerTurn();

       

        
            // verify
            //testing that two games are actually being conducted and are different
            System.out.print(bj1 +" does not equal " + "\n" +bj2);

            // test
            assert bj1 != null : "Error in Blackjack::Blackjack()";
            assert bj2 != null : "Error in Blackjack::Blackjack()";
            
            

        


       
        System.out.println("*** Done testing Blackjack! ***\n");
    }


    public static void main(String[] args) {

        blackjackTests();
    }
}