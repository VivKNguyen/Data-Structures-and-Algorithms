/*
 * filename: LifeSimulation.java
 * author: Vivian Nguyen
 * date: 09/26/2022
 * CS231 Lab Section B
 * Project 2: Conway's Game of Life
 */


public class LifeSimulation {

    Landscape scape;
    LandscapeDisplay display;
    
    
    public LifeSimulation(int rows, int cols){
        
        scape = new Landscape(rows, cols);
        display = new LandscapeDisplay(scape, 6);
        
        
    }

    public static void main(String[] args) throws InterruptedException {
        // EXTENSION 1, implementing command line arguments
          //These instructions will be printed out if there are not enough command line arguments
        if (args.length == 0){
            System.out.println("Please input command line arguments.");
            System.out.println("Command line should look like: " + "\n" + "java LifeSimulation<numRows> <numCols> <Iters> ");
            System.exit(0);
            
        }
        else if (args.length < 3) {
            System.out.println("Error: Missing command line arguments!");
            System.out.println("Command line should look like: " + "\n" + "java LifeSimulation<numRows> <numCols> <Iters> ");
            System.exit(0);
        }
        
        //turning the string args into primitive ints and then back so it could be used in args
        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);
        int iters = Integer.parseInt(args[2]);
        
        
        //creating a new landscape
        Landscape scape = new Landscape(rows, cols);
        LandscapeDisplay display = new LandscapeDisplay(scape, 7);

        //creating the animation
        //will iterate through the rules iters amount of times
        for (int i = 0 ; i < iters + 1 ; i++){
            Thread.sleep(100);
            scape.advance();
            display.repaint();
            //saves images
            display.saveImage( "data/life_frame_" + String.format( "%03d", i ) + ".png" );

            }

        }
}

