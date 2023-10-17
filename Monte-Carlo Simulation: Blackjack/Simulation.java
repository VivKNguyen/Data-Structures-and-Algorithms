/*
 * filename: Simulation.java
 * author: Vivian Nguyen
 * date: 09/18/2022
 */

public class Simulation {

    public static void main(String[]args){
        //creating a blackjack
        Blackjack bjack = new Blackjack();
        // referring to the result returned in the blackjack class
        int result = 0;
        // ints related to the score of the game
        int push=0;
        int dealerWin=0;
        int playerWin=0;
        // keeping track of the score
        for (int i=0;i<1000;i++){
            result = bjack.game(true);
            if (result ==0){
                push +=1;
                }
            else if (result==1){
                playerWin +=1;

                }
            else if (result==-1){
                dealerWin+=1;
                }

            }
        //printing out results
        System.out.println("Total Pushes:" + " " + push);
        System.out.println("Total Player Wins:" + " " + playerWin);
        System.out.println("Total Dealer Wins:" + " "+ dealerWin);

        //printing out percents
        System.out.println("Percentage of Pushes:" + " " +(push/1000.0));
        System.out.println("Percentage of Player Wins:" + " " +(playerWin/1000.0));
        System.out.println("Percentage of Dealer Wins:" + " " +(dealerWin/1000.0));
        }

    }
    

