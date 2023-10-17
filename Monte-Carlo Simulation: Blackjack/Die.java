
import java.util.Random;

public class Die {

    private int sideUp;

    public Die() {
        this.sideUp = 0;
    } 

    public Die(int number) {
        sideUp = number;
    }

    public void roll() {
        Random generator = new Random();
        sideUp = generator.nextInt(1,7);
    }

    public int getSideUp(){
        return sideUp;


    }

    public void setSideUp(int newNumber){
        sideUp= newNumber;

    }

    public String toString() {

        return " "+ sideUp;
    }
    public static void main(String[] args) {

        Die myDie1 = new Die();
        Die myDie2 = new Die(6);

        // System.out.println("Die 1" + myDie1.getSideUp());
        // System.out.println("Die 2" + myDie2.getSideUp());

        // myDie1.setSideUp(5);
        myDie1.roll();
        System.out.println("Die1: " + myDie1);

        // myDie1.roll();
        // System.out.println("Die1: " + myDie1.getSideUp());
        // myDie1.roll();
        // System.out.println("Die1: " + myDie1.getSideUp());
    }

}